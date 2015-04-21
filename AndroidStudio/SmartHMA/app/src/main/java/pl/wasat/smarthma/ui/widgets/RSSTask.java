package pl.wasat.smarthma.ui.widgets;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pl.wasat.smarthma.model.NewsArticle;
import pl.wasat.smarthma.utils.rss.NewsRssHandler;

/**
 * Created by Dark Mark on 17/04/2015.
 */
public class RSSTask extends AsyncTask
{
    @Override
    protected Object doInBackground(Object[] objects)
    {
        Log.d("ZX", "doInBackground()");
        Log.d("ZX", "1");
        String feed = (String)objects[0];
        URL url;
        try {
            Log.d("ZX", "2");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            Log.d("ZX", "3");
            url = new URL(feed);
            Log.d("ZX", url.toString());
            Log.d("ZX", "4");
            NewsRssHandler rh = new NewsRssHandler();

            Log.d("ZX", "5");
            xr.setContentHandler(rh);
            Log.d("ZX", "6");
            xr.parse(new InputSource(url.openStream()));

            Log.d("ZX", "7");
            Log.e("ASYNC", "PARSING FINISHED");
            List<NewsArticle> articles = rh.getArticleList();
            //((RSSWidgetProvider)objects[1]).refreshList(articles);
            ((RemoteFetchService)objects[1]).refreshList(articles);
            /*
            ArrayList<ListItem> listItemList = (ArrayList<ListItem>)objects[1];
            for (NewsArticle article : articles)
            {
                String title = article.getTitle();
                String date = article.getPubDate();
                ListItem listItem = new ListItem();
                listItem.heading = "Heading";
                listItem.content = " This is the content of the app widget listview.Nice content though";
                if (title != null && date != null)
                {
                    Log.d("ZX", title+" "+date);
                    listItem.heading = title;
                    listItem.content = date;
                }
                listItemList.add(listItem);
            }
            */
            return articles;

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
}
