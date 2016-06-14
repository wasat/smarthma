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

package pl.wasat.smarthma.utils.rss;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.news.Channel;
import pl.wasat.smarthma.model.news.NewsArticle;
import pl.wasat.smarthma.model.news.Rss;

/**
 * The type News rss handler.
 */
public class NewsRssHandler extends DefaultHandler {

    private final List<NewsArticle> articleList = new ArrayList<>();

    private Rss rss;
    private Channel channel;
    private String category;
    private String language;
    private String ttl;
    private String copyright;
    private String managingEditor;
    private String title;
    private String description;
    private Link link;
    private String pubDate;
    private String lastBuildDate;
    private String docs;
    private boolean isInItem;
    private List<NewsArticle> items;
    private NewsArticle newsArticle;
    private String titleNewsItem;
    private String descriptionNewsItem;
    private Link linkNewsItem;
    private String pubDateNewsItem;
    private String guid;

    // Feed and Article objects to use for temporary storage
    //private NewsArticle currentArticle = new NewsArticle();
    // Number of articles added so far
    //private int articlesAdded = 0;
    // Current characters being accumulated
    private StringBuffer chars = new StringBuffer();

    /*
     * This method is called everytime a start element is found (an opening XML
     * marker) here we always reset the characters StringBuffer as we are only
     * currently interested in the the text values stored at leaf nodes
     *
     * (non-Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String uri, String localName, String qName,
                             Attributes atts) throws SAXException {
        super.startElement(uri, localName, qName, atts);
        chars = new StringBuffer();

        if (localName.equalsIgnoreCase("rss")) {
            rss = new Rss();
        } else if (localName.equalsIgnoreCase("channel")) {
            channel = new Channel();
            items = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("link")) {
            link = new Link();
            link.setHref(atts.getValue("href"));
            link.setRel(atts.getValue("rel"));
            link.setTitle(atts.getValue("title"));
            link.setType(atts.getValue("type"));
            if (isInItem) {
                linkNewsItem = new Link();
                linkNewsItem.setHref(atts.getValue("href"));
                linkNewsItem.setRel(atts.getValue("rel"));
                linkNewsItem.setTitle(atts.getValue("title"));
                linkNewsItem.setType(atts.getValue("type"));
            }
        } else if (localName.equalsIgnoreCase("item")) {
            newsArticle = new NewsArticle();
            isInItem = true;
        }
    }

    /*
     * This method is called everytime an end element is found (a closing XML
     * marker) here we check what element is being closed, if it is a relevant
     * leaf node that we are checking, such as Title, then we get the characters
     * we have accumulated in the StringBuffer and set the current Article's
     * title to the value
     *
     * If this is closing the "entry", it means it is the end of the article, so
     * we add that to the list and then reset our Article object for the next
     * one on the stream
     *
     *
     * (non-Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (localName.equalsIgnoreCase("rss")) {
            rss.setChannel(channel);
        } else if (localName.equalsIgnoreCase("channel")) {
            channel.setCategory(category);
            channel.setCopyright(copyright);
            channel.setDescription(description);
            channel.setDocs(docs);
            channel.setLanguage(language);
            channel.setLastBuildDate(lastBuildDate);
            channel.setManagingEditor(managingEditor);
            channel.setPubDate(pubDate);
            channel.setTitle(title);
            channel.setTtl(ttl);
            channel.setLink(link);
            channel.setItems(items);
        } else if (localName.equalsIgnoreCase("category")) {
            category = chars.toString();
        } else if (localName.equalsIgnoreCase("language")) {
            language = chars.toString();
        } else if (localName.equalsIgnoreCase("ttl")) {
            ttl = chars.toString();
        } else if (localName.equalsIgnoreCase("copyright")) {
            copyright = chars.toString();
        } else if (localName.equalsIgnoreCase("managingeditor")) {
            managingEditor = chars.toString();
        } else if (localName.equalsIgnoreCase("title")) {
            title = chars.toString();
        } else if (localName.equalsIgnoreCase("description")) {
            description = chars.toString();
        } else if (localName.equalsIgnoreCase("link")) {
            link.setHref(chars.toString());
        } else if (localName.equalsIgnoreCase("pubdate")) {
            pubDate = chars.toString();
        } else if (localName.equalsIgnoreCase("lastbuilddate")) {
            lastBuildDate = chars.toString();
        } else if (localName.equalsIgnoreCase("docs")) {
            docs = chars.toString();
        }

        if (isInItem) {
            if (localName.equalsIgnoreCase("item")) {
                newsArticle.setTitle(titleNewsItem);
                newsArticle.setDescription(descriptionNewsItem);
                newsArticle.setEncodedContent(descriptionNewsItem);
                newsArticle.setLink(linkNewsItem);
                newsArticle.setGuid(guid);
                newsArticle.setPubDate(pubDateNewsItem);
                items.add(newsArticle);
                isInItem = false;
            } else if (localName.equalsIgnoreCase("title")) {
                titleNewsItem = chars.toString();
            } else if (localName.equalsIgnoreCase("description")) {
                descriptionNewsItem = chars.toString();
            } else if (localName.equalsIgnoreCase("link")) {
                linkNewsItem.setHref(chars.toString());
            } else if (localName.equalsIgnoreCase("guid")) {
                guid = chars.toString();
            } else if (localName.equalsIgnoreCase("pubdate")) {
                pubDateNewsItem = chars.toString();
            }
        }

/*        // Check if looking for article, and if article is complete
        if (localName.equalsIgnoreCase("item")) {
            articleList.add(currentArticle);
            currentArticle = new NewsArticle();
            // Lets check if we've hit our limit on number of articles
            articlesAdded++;
        }*/
    }

    /*
     * This method is called when characters are found in between XML markers,
     * however, there is no guarantee that this will be called at the end of the
     * node, or that it will be called only once , so we just accumulate these
     * and then deal with them in endElement() to be sure we have all the text
     *
     * (non-Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    public void characters(char ch[], int start, int length) {
        chars.append(new String(ch, start, length).trim());
    }

    /**
     * Gets rss news.
     *
     * @return the rss news
     */
    public Rss getRssNews() {
        return this.rss;
    }
}