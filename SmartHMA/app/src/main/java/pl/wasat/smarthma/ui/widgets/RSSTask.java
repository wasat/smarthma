/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import pl.wasat.smarthma.model.news.NewsArticle;
import pl.wasat.smarthma.utils.rss.NewsRssHandler;

/**
 * Created by Dark Mark on 17/04/2015 01:33.
 * Part of the project  SmartHMA
 */
class RSSTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        String feed = (String) objects[0];
        URL url;
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            url = new URL(feed);
            NewsRssHandler rh = new NewsRssHandler();

            xr.setContentHandler(rh);
            xr.parse(new InputSource(url.openStream()));

            List<NewsArticle> articles = rh.getRssNews().getChannel().getItems();
            articles = articles.subList(0, Const.MAX_WIDGET_ENTRIES);
            ((RemoteFetchService) objects[1]).refreshList(articles);
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
