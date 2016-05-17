package pl.wasat.smarthma.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.NewsArticleListAdapter;
import pl.wasat.smarthma.database.NewsDbAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.news.Channel;
import pl.wasat.smarthma.model.news.NewsArticle;
import pl.wasat.smarthma.model.news.Rss;
import pl.wasat.smarthma.ui.frags.news.NewsListFragment;
import pl.wasat.smarthma.utils.rss.NewsRssHandler;

public class NewsRssServiceNoAsync {

    private final ProgressDialog progress;
    private final NewsListFragment articleListFrag;

    public NewsRssServiceNoAsync(NewsListFragment articleListFragment) {
        Context context = articleListFragment.getActivity();
        articleListFrag = articleListFragment;
        progress = new ProgressDialog(context);
        progress.setMessage(context.getString(R.string.loading));
    }

    public void exec() {
        onPreExecute();
        Rss mainRss = doInBackground(Const.URL_ESA_NEWS_1);
        Channel mainChannel = mainRss != null ? mainRss.getChannel() : null;
        List<NewsArticle> mainArtList = mainChannel != null ? mainChannel.getItems() : null;
        List<NewsArticle> mainArtList2 = getNewsListFromUrl(Const.URL_ESA_NEWS_USER_SRV);
        List<NewsArticle> mainArtList3 = getNewsListFromUrl(Const.URL_ESA_NEWS_2);

        if (mainArtList != null) {
            mainArtList.addAll(mainArtList2);
            mainArtList.addAll(mainArtList3);
            onPostExecute(mainArtList);
        }
    }

    private List<NewsArticle> getNewsListFromUrl(String url) {
        Rss rss = doInBackground(url);
        Channel channel = rss != null ? rss.getChannel() : null;
        return channel != null ? channel.getItems() : null;
    }

    private void onPreExecute() {
        progress.show();
    }

    private Rss doInBackground(String... urls) {
        String feed = urls[0];

        URL url;
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            url = new URL(feed);
            NewsRssHandler rh = new NewsRssHandler();

            xr.setContentHandler(rh);
            xr.parse(new InputSource(url.openStream()));

            return rh.getRssNews();
        } catch (IOException e) {
            Log.e("RSS Handler IO", e.getMessage() + " >> " + e.toString());
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        }
        return null;
    }

    private void onPostExecute(final List<NewsArticle> newsArticles) {
        articleListFrag.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (NewsArticle a : newsArticles) {
                    NewsDbAdapter dba = new NewsDbAdapter(articleListFrag
                            .getActivity());
                    dba.openToRead();
                    NewsArticle fetchedArticle = dba.getBlogListing(a.getGuid());
                    dba.close();
                    if (fetchedArticle == null) {
                        dba = new NewsDbAdapter(articleListFrag.getActivity());
                        dba.openToWrite();
                        dba.insertBlogListing(a.getGuid());
                        dba.close();
                    } else {
                        a.setDbId(fetchedArticle.getDbId());
                        a.setOffline(fetchedArticle.isOffline());
                        a.setRead(fetchedArticle.isRead());
                    }
                }
                final NewsArticleListAdapter adapter = new NewsArticleListAdapter(
                        articleListFrag.getActivity(), R.layout.view_cell_article, newsArticles);
                adapter.setListener(new OnSlideElementListener() {
                    @Override
                    public void Catch(boolean swipeRight, int position) {
                        if (swipeRight)
                            Toast.makeText(adapter.getContext(),
                                    adapter.getContext().getString(R.string.swipe_right)
                                            + position, Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(adapter.getContext(),
                                    adapter.getContext().getString(R.string.swipe_left)
                                            + position, Toast.LENGTH_SHORT).show();
                    }
                });
                articleListFrag.setListAdapter(adapter);
                adapter.notifyDataSetChanged();
                articleListFrag.setListAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
        progress.dismiss();
    }
}