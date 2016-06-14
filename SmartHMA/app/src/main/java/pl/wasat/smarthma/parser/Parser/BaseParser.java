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

package pl.wasat.smarthma.parser.Parser;

import android.content.Context;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.wasat.smarthma.parser.database.ParserDb;


/**
 * Created by marcel paduch on 2015-08-07.
 * <p/>
 * Purpose of this class is to provide methods and constants for parsing contents of each mission
 */
public abstract class BaseParser {

    /**
     * The Parser db.
     */
    protected final ParserDb parserDb;
    /**
     * The Page url.
     */
    protected String pageUrl;
    private final Whitelist whitelist;

    /**
     * Html and css classes for parsing
     */

    private String contentClass = ".journal-content-article";
    /**
     * The Title class.
     */
    protected String titleClass = ".portlet-title";
    /**
     * The Image list class.
     */
    protected final String imageListClass = ".asset-abstract";
    /**
     * The Get all.
     */
    protected final String getAll = "?_101_INSTANCE_oSgkQIfPm75k_delta=90";

    /**
     * The Js position.
     */
    protected final int JS_POSITION = 9;
    /**
     * The News title.
     */
    protected final String NEWS_TITLE = "News";
    /**
     * The Milestones title.
     */
    protected final String MILESTONES_TITLE = "Milestones";
    /**
     * The Faq title.
     */
    protected final String FAQ_TITLE = "Faq";
    /**
     * The constant IMG_NAME.
     */
    public static final String IMG_NAME = "MISSION_IMG";
    /**
     * The Main info.
     */
    protected final String MAIN_INFO = "[MAIN_INFO]";

    /**
     * Sections urls
     */
    protected final String OVERVIEW = "overview";
    /**
     * The Objectives.
     */
    protected final String OBJECTIVES = "objectives";
    /**
     * The Ground segment.
     */
    protected final String GROUND_SEGMENT = "ground-segment";
    /**
     * The Instruments.
     */
    protected final String INSTRUMENTS = "instruments";
    /**
     * The Satellite.
     */
    protected final String SATELLITE = "satellite";
    /**
     * The Scientific requirements.
     */
    protected final String SCIENTIFIC_REQUIREMENTS = "scientific-requirements";
    /**
     * The Operations.
     */
    protected final String OPERATIONS = "operations";
    /**
     * The Img of the week.
     */
    protected final String IMG_OF_THE_WEEK = "image-of-the-week-archive";
    /**
     * The Science.
     */
    protected final String SCIENCE = "science";
    /**
     * The History.
     */
    protected final String HISTORY = "history";
    /**
     * The Applications.
     */
    protected final String APPLICATIONS = "applications";
    /**
     * The Milestones.
     */
    protected final String MILESTONES = "milestones";
    /**
     * The Industry.
     */
    protected final String INDUSTRY = "industry";

    /**
     * Instantiates a new Base parser.
     *
     * @param pageUrl Full url of the webpage to parse
     * @param context context
     */
    protected BaseParser(String pageUrl, Context context) {
        this.pageUrl = pageUrl;
        parserDb = new ParserDb(context);
        parserDb.open();
        whitelist = Whitelist.basic().addTags("table", "tbody", "td", "tfoot", "th", "thead", "tr");
    }

    /**
     * Parse simple page(one title - contents section)
     *
     * @param pageName Last part of webpage url, should be defined as constant
     * @return Pair<StringString>   with title and contents
     */
    protected Pair<String, String> getSimplePage(String pageName) {
        String title;
        String content;
        Document doc;
        try {
            if (pageName.equals("")) {
                doc = Jsoup.connect(pageUrl).get();
            } else {
                doc = Jsoup.connect(pageUrl + "/" + pageName).get();
            }
            title = doc.select(titleClass).first().text();
            content = doc.select(contentClass).first().html();
        } catch (IOException e) {
            Log.d(BaseParser.class.toString(), e.toString());
            title = "";
            content = "";
        }
        return new Pair<>(title, content);
    }


    /**
     * Parse webpage containing several title - contents sections. Uses pageUrl as url
     *
     * @param itemsCount Number of sections on webpage
     * @return ArrayList<Pair>   list of pairs containing title - contents
     */
    protected ArrayList<Pair> getComplexPage(int itemsCount) {
        ArrayList<String> contentList = new ArrayList<>();
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<Pair> finalList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(pageUrl).get();
            Elements contents = doc.select(contentClass);
            for (Element content : contents) {
                contentList.add(Jsoup.clean(content.html(), whitelist));
            }
            contents = doc.select(titleClass);
            for (Element content : contents) {
                titleList.add(MAIN_INFO + " " + Jsoup.clean(content.html(), whitelist));
            }
            for (int i = 0; i < itemsCount; i++) {
                finalList.add(new Pair<>(titleList.get(i), contentList.get(i)));
            }
        } catch (IOException e) {
            Log.d(BaseParser.class.toString(), e.toString());
            finalList = null;
        }
        return finalList;
    }

    /**
     * Parse webpage containing several title - contents sections. Uses pageUrl as url
     *
     * @param itemsCount  Number of sections on webpage
     * @param excludeList List of positions(starting with zero) of excluded items
     * @return ArrayList<Pair>   list of pairs containing title - contents
     */
    public ArrayList<Pair> getComplexPage(int itemsCount, ArrayList<Integer> excludeList) {
        ArrayList<String> contentList = new ArrayList<>();
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<Pair> finalList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(pageUrl).get();
            Elements contents = doc.select(contentClass);
            for (Element content : contents) {
                contentList.add(Jsoup.clean(content.html(), whitelist));
            }
            contents = doc.select(titleClass);
            for (Element content : contents) {
                titleList.add(MAIN_INFO + " " + Jsoup.clean(content.html(), whitelist));
            }
            for (int i = 0; i < itemsCount; i++) {
                if (!excludeList.contains(i)) {
                    finalList.add(new Pair<>(titleList.get(i), contentList.get(i)));
                }
            }
        } catch (IOException e) {
            Log.d(BaseParser.class.toString(), e.toString());
            finalList = null;
        }
        return finalList;
    }

    /**
     * Parse webpage containing several title - contents sections. Uses pageUrl as url
     *
     * @param itemsCount Number of sections on webpage
     * @param exclude    Position of one item to exclude
     * @return ArrayList<Pair>   list of pairs containing title - contents
     */
    protected ArrayList<Pair> getComplexPage(int itemsCount, int exclude) {
        ArrayList<String> contentList = new ArrayList<>();
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<Pair> finalList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(pageUrl).get();
            Elements contents = doc.select(contentClass);
            for (Element content : contents) {
                contentList.add(Jsoup.clean(content.html(), whitelist));
            }
            contents = doc.select(titleClass);
            for (Element content : contents) {
                titleList.add(MAIN_INFO + " " + Jsoup.clean(content.html(), whitelist));
            }
            for (int i = 0; i < itemsCount; i++) {
                if (i != exclude) {
                    finalList.add(new Pair<>(titleList.get(i), contentList.get(i)));
                }
            }
        } catch (IOException e) {
            Log.d(BaseParser.class.toString(), e.toString());
            finalList = null;
        }
        return finalList;
    }


    /**
     * Parse list of images with contents
     *
     * @param pageName Last part of webpage url, should be defined as constant
     * @param maxItems Number of items to get
     * @param fullPage Set to true if webpage has pagination and you want all pages, otherwise false
     * @return Pair containing title of the list and arraList containing items
     */
    protected Pair<String, ArrayList<String>> getImageListPage(String pageName, int maxItems, boolean fullPage) {
        String title;
        ArrayList<String> contentList = new ArrayList<>();
        Document doc;

        try {
            if (pageName.equals("")) {
                if (fullPage) {
                    doc = Jsoup.connect(pageUrl + "/" + getAll).get();
                } else {
                    doc = Jsoup.connect(pageUrl).get();
                }
            } else {
                if (fullPage) {
                    doc = Jsoup.connect(pageUrl + "/" + pageName + getAll).get();
                } else {
                    doc = Jsoup.connect(pageUrl + "/" + pageName).get();
                }
            }
            title = doc.select(titleClass).first().text();
            Elements contents = doc.select(imageListClass);
            for (Element content : contents) {
                contentList.add(content.html());
                if (contentList.size() >= maxItems) {
                    break;
                }
            }
        } catch (IOException e) {
            Log.d(BaseParser.class.toString(), e.toString());
            title = "";
        }
        return new Pair<>(title, contentList);
    }

    /**
     * Parse list of images with contents
     *
     * @param maxItems Number of items to get
     * @param fullPage Set to true if webpage has pagination and you want all pages, otherwise false
     * @return Pair containing title of the list and arraList containing items
     */
    protected Pair<String, ArrayList<String>> getImageListPage(int maxItems, boolean fullPage) {
        return getImageListPage("", maxItems, fullPage);
    }

    /**
     * Get contents of webpage containing sections dynamically loaded using javascript
     *
     * @param pageName  Last part of webpage url, should be defined as constant
     * @param position  Position of javascript <script> </script> code containing array with data,                  should be defined as constant
     * @param arrayName Name of the array containing data
     * @return Pair<StringString>   with title and contents
     */
    protected ArrayList<Pair> getJsPage(String pageName, int position, String arrayName) {
        final int PRECEDING_CHARS = 3;
        final int FOLLOWING_CHARS = 2;
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> contentList = new ArrayList<>();
        ArrayList<Pair> finalList = new ArrayList<>();
        Document doc;
        try {
            if (pageName.equals("")) {
                doc = Jsoup.connect(pageUrl).get();
            } else {
                doc = Jsoup.connect(pageUrl + "/" + pageName).get();
            }
            Element script = doc.select("script").get(position);

            String jsNavigationClass = ".tabbed-navigation";
            Element nav = doc.select(jsNavigationClass).first();
            Elements li = nav.select("li");
            for (int i = 0; i < li.size(); i++) {
                titleList.add(li.get(i).text());
            }
            Pattern p = Pattern.compile(arrayName + "\\[\"[0-9]\"\\] = \".+?\";");
            Matcher m = p.matcher(script.html());

            while (m.find()) {
                contentList.add(m.group().substring(m.group().indexOf("=") + PRECEDING_CHARS, m.group().length() - FOLLOWING_CHARS));
            }
        } catch (IOException e) {
            Log.d(BaseParser.class.toString(), e.toString());
        }
        for (int i = 0; i < titleList.size(); i++) {
            finalList.add(new Pair<>(titleList.get(i), contentList.get(i)));
        }
        return finalList;
    }

    /**
     * Get contents of webpage containing sections dynamically loaded using javascript
     *
     * @param position  Position of javascript <script> </script> code containing array with data,                  should be defined as constant
     * @param arrayName Name of the array containing data
     * @return Pair<StringString>   with title and contents
     */
    protected ArrayList<Pair> getJsPage(int position, String arrayName) {
        return getJsPage("", position, arrayName);
    }

    /**
     * Gets page url.
     *
     * @return the page url
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * Sets page url.
     *
     * @param pageUrl the page url
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    /**
     * Gets content class.
     *
     * @return the content class
     */
    public String getContentClass() {
        return contentClass;
    }

    /**
     * Sets content class.
     *
     * @param contentClass the content class
     */
    public void setContentClass(String contentClass) {
        this.contentClass = contentClass;
    }

    /**
     * Gets title class.
     *
     * @return the title class
     */
    public String getTitleClass() {
        return titleClass;
    }

    /**
     * Sets title class.
     *
     * @param titleClass the title class
     */
    public void setTitleClass(String titleClass) {
        this.titleClass = titleClass;
    }

    /**
     * Image list to content string string.
     *
     * @param pair the pair
     * @return the string
     */
    protected String imageListToContentString(Pair pair) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList list = (ArrayList) pair.content;
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i));
        }
        return stringBuilder.toString();
    }

    /**
     * Complex page to string string.
     *
     * @param list the list
     * @return the string
     */
    public String complexPageToString(ArrayList<Pair> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i).title);
            stringBuilder.append(list.get(i).content);
        }
        return stringBuilder.toString();
    }

    /**
     * Gets urls of images from html
     *
     * @param contentsList list of webpage section previously parsed
     * @return list of images url, used for missions list thumbnails
     */
    protected ArrayList<String> getImageList(ArrayList<String> contentsList) {
        ArrayList<String> finalList = new ArrayList<>();
        for (String singleEl : contentsList) {
            try {
                Document doc = Jsoup.parseBodyFragment(singleEl);
                Element el = doc.select("a img").first();
                finalList.add(/*MAIN_PAGE_URL +*/ el.attr("src"));
            } catch (Exception ignored) {
            }
        }
        return finalList;
    }

    /**
     * Removes whitespaces and images from html that was parsed from javascript
     *
     * @param javascript html to be sanitized
     * @return sanitized html
     */
    protected String sanitizeJsContents(String javascript) {
        javascript = javascript.replace("\\n", "");
        javascript = javascript.replace("\\t", "");
        javascript = javascript.replace("\\", "");
        javascript = Jsoup.clean(javascript, whitelist);
        return javascript;
    }
}
