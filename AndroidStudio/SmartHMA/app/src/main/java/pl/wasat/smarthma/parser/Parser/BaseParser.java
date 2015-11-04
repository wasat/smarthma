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


    protected final ParserDb parserDb;
    protected String pageUrl;
    private final Whitelist whitelist;

    /**
     * Html and css classes for parsing
     */

    private String contentClass = ".journal-content-article";
    protected String titleClass = ".portlet-title";
    protected final String imageListClass = ".asset-abstract";
    protected final String getAll = "?_101_INSTANCE_oSgkQIfPm75k_delta=90";

    protected final int JS_POSITION = 9;
    protected final String NEWS_TITLE = "News";
    protected final String MILESTONES_TITLE = "Milestones";
    protected final String FAQ_TITLE = "Faq";
    public static final String IMG_NAME = "MISSION_IMG";
    protected final String MAIN_INFO = "[MAIN_INFO]";

    /**
     * Sections urls
     */
    protected final String OVERVIEW = "overview";
    protected final String OBJECTIVES = "objectives";
    protected final String GROUND_SEGMENT = "ground-segment";
    protected final String INSTRUMENTS = "instruments";
    protected final String SATELLITE = "satellite";
    protected final String SCIENTIFIC_REQUIREMENTS = "scientific-requirements";
    protected final String OPERATIONS = "operations";
    protected final String IMG_OF_THE_WEEK = "image-of-the-week-archive";
    protected final String SCIENCE = "science";
    protected final String HISTORY = "history";
    protected final String APPLICATIONS = "applications";
    protected final String MILESTONES = "milestones";
    protected final String INDUSTRY = "industry";


    /**
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
     * @return Pair<StringString> with title and contents
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
     * @return ArrayList<Pair> list of pairs containing title - contents
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
     * @return ArrayList<Pair> list of pairs containing title - contents
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
     * @return ArrayList<Pair> list of pairs containing title - contents
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
     * @param position  Position of javascript <script> </script> code containing array with data,
     *                  should be defined as constant
     * @param arrayName Name of the array containing data
     * @return Pair<StringString> with title and contents
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
                //	System.out.println(m.group().substring(m.group().indexOf("=") + 1));
                /*String[] parts = m.group().split("=");
                contentList.add(parts[1].substring(2, parts[1].length() - 1));*/
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
     * @param position  Position of javascript <script> </script> code containing array with data,
     *                  should be defined as constant
     * @param arrayName Name of the array containing data
     * @return Pair<StringString> with title and contents
     */
    protected ArrayList<Pair> getJsPage(int position, String arrayName) {
        return getJsPage("", position, arrayName);
    }


    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getContentClass() {
        return contentClass;
    }

    public void setContentClass(String contentClass) {
        this.contentClass = contentClass;
    }

    public String getTitleClass() {
        return titleClass;
    }

    public void setTitleClass(String titleClass) {
        this.titleClass = titleClass;
    }


    protected String imageListToContentString(Pair pair) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList list = (ArrayList) pair.content;
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i));
        }
        return stringBuilder.toString();
    }

    public String complexPageToString(ArrayList<Pair> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i).title);
            stringBuilder.append(list.get(i).content);
        }
        return stringBuilder.toString();
    }


    public void printImageList(Pair pair) {
        //System.out.println(pair.title);
        ArrayList list = (ArrayList) pair.content;
        for (int i = 0; i < list.size(); i++) {
            //System.out.println(list.get(i));
        }

        //System.out.println(list.size());
    }

    public void printComplexPage(ArrayList<Pair> list) {
        for (int i = 0; i < list.size(); i++) {
            //System.out.println(list.get(i).title);
            //System.out.println(list.get(i).content);
        }
    }

    /**
     * Gets urls of images from html
     *
     * @param contentsList list of webpage section previously parsed
     * @return list of images url, used for missions list thumbnails
     */
    protected ArrayList<String> getImageList(ArrayList<String> contentsList) {
        ArrayList<String> finalList = new ArrayList<>();
        for (String singleEl :
                contentsList) {

            Document doc = Jsoup.parseBodyFragment(singleEl);
            Element el = doc.select("a img").first();
            finalList.add(/*MAIN_PAGE_URL +*/ el.attr("src"));
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
