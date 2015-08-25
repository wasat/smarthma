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

import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.NewsArticle;
import pl.wasat.smarthma.utils.rss.NewsRssHandler;

/**
 * Created by Dark Mark on 17/04/2015 01:33.
 * Part of the project  SmartHMA
 */
class RSSTask extends AsyncTask
{
    @Override
    protected Object doInBackground(Object[] objects)
    {
        Log.d("ZX", "doInBackground()");
        String feed = (String)objects[0];
        URL url;
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            url = new URL(feed);
            Log.d("ZX", url.toString());
            NewsRssHandler rh = new NewsRssHandler();

            xr.setContentHandler(rh);
            xr.parse(new InputSource(url.openStream()));

            Log.i("ASYNC", "PARSING FINISHED");
            List<NewsArticle> articles = rh.getArticleList();
            articles = articles.subList(0, Const.MAX_WIDGET_ENTRIES);
            ((RemoteFetchService)objects[1]).refreshList(articles);

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
