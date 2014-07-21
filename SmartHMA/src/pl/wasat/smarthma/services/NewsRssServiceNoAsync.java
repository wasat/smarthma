package pl.wasat.smarthma.services;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import pl.wasat.smarthma.adapter.NewsArticleListAdapter;
import pl.wasat.smarthma.database.NewsDbAdapter;
import pl.wasat.smarthma.model.NewsArticle;
import pl.wasat.smarthma.ui.frags.news.NewsListFragment;
import pl.wasat.smarthma.utils.rss.NewsRssHandler;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;


public class NewsRssServiceNoAsync  {

	private final ProgressDialog progress;
    private final NewsListFragment articleListFrag;

	public NewsRssServiceNoAsync(NewsListFragment articleListFragment) {
        Context context = articleListFragment.getActivity();
		articleListFrag = articleListFragment;
		progress = new ProgressDialog(context);
		progress.setMessage("Loading...");

	}

	
	public void exec()
	{
		onPreExecute();
		List<NewsArticle> art = doInBackground(NewsListFragment.BLOG_URL);
		onPostExecute(art);
	}

	void onPreExecute() {
		Log.e("ASYNC", "PRE EXECUTE");
		progress.show();
	}


	void onPostExecute(final List<NewsArticle> articles) {
		Log.e("ASYNC", "POST EXECUTE");
		articleListFrag.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				for (NewsArticle a : articles){
					Log.d("DB", "Searching DB for GUID: " + a.getGuid());
					NewsDbAdapter dba = new NewsDbAdapter(articleListFrag.getActivity());
		            dba.openToRead();
		            NewsArticle fetchedArticle = dba.getBlogListing(a.getGuid());
		            dba.close();
					if (fetchedArticle == null){
						Log.d("DB", "Found entry for first time: " + a.getTitle());
						dba = new NewsDbAdapter(articleListFrag.getActivity());
			            dba.openToWrite();
			            dba.insertBlogListing(a.getGuid());
			            dba.close();
					}else{
						a.setDbId(fetchedArticle.getDbId());
						a.setOffline(fetchedArticle.isOffline());
						a.setRead(fetchedArticle.isRead());
					}
				}
				NewsArticleListAdapter adapter = new NewsArticleListAdapter(articleListFrag.getActivity(), articles);
				articleListFrag.setListAdapter(adapter);
				adapter.notifyDataSetChanged();
				
			}
		});
		progress.dismiss();
	}



	List<NewsArticle> doInBackground(String... urls) {
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


			Log.e("ASYNC", "PARSING FINISHED");
			return rh.getArticleList();

		} catch (IOException e) {
			Log.e("RSS Handler IO", e.getMessage() + " >> " + e.toString());
		} catch (SAXException e) {
			Log.e("RSS Handler SAX", e.toString());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			Log.e("RSS Handler Parser Config", e.toString());
		}

		return null;

	}
}