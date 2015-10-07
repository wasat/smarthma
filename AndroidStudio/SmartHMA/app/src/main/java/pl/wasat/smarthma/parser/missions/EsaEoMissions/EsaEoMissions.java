package pl.wasat.smarthma.parser.missions.EsaEoMissions;

import android.content.Context;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.MissionInterface;
import pl.wasat.smarthma.parser.model.Category;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel paduch on 2015-08-10 00:09.
 * Part of the project  SmartHMA
 */
public class EsaEoMissions extends BaseParser implements MissionInterface {

    public final static int CATEGORY_ID = 0;

    public EsaEoMissions(String pageUrl, Context context) {
        super(pageUrl, context);
    }

    @Override
    public void mainContent() {
        int ITEMS_COUNT = 9;
        Pair<String, ArrayList<String>> pair = super.getImageListPage(ITEMS_COUNT, false);
        String contents = super.imageListToContentString(pair);
        parserDb.addCategory(new Category(CATEGORY_ID, pair.title, contents));
        ArrayList<String> urlList = getImageList(pair.content);
        int mission_id = 0;

        for (String url :
                urlList) {
            parserDb.addPage(new Page(CATEGORY_ID, mission_id, IMG_NAME, url));
            mission_id++;
        }
    }

    @Override
    public void history() {

    }

    @Override
    public void industry() {

    }

    @Override
    public void science() {

    }

    @Override
    public void applications() {

    }

    @Override
    public void scientificRequirements() {

    }

    @Override
    public void operations() {

    }

    @Override
    public void overview() {

    }

    @Override
    public void objectives() {

    }

    @Override
    public void satellite() {

    }

    @Override
    public void groundSegment() {

    }

    @Override
    public void instruments() {

    }

    @Override
    public void news() {
    }

    @Override
    public void milestones() {
    }

    @Override
    public void faq() {
    }

    @Override
    public void other() {
    }

    public Pair<String, ArrayList<String>> getImageListPage(String pageName, int maxItems, boolean fullPage) {

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
            Log.d("imgListSize", String.valueOf(contents.size()));
            for (int i = 1; i < contents.size(); i += 2) {
                contentList.add(contents.get(i).html());

                //System.out.println(getImageSource(content.html()));
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
}
