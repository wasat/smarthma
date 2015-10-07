package pl.wasat.smarthma.parser.missions.EsaFutureMissions;

import android.content.Context;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.SimpleMissionInterface;
import pl.wasat.smarthma.parser.model.Category;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel paduch on 2015-08-11 00:09.
 * Part of the project  SmartHMA
 */
public class EsaFutureMissions extends BaseParser implements SimpleMissionInterface {
    public final static int CATEGORY_ID = 1;

    public EsaFutureMissions(String pageUrl, Context context) {
        super(pageUrl, context);
        String TITLE = "ESA Future Missions";
        parserDb.addCategory(new Category(CATEGORY_ID, TITLE, ""));

    }

    @Override
    public void mainContent() {
        final int NO_MISSION = -1;
        int ITEMS_COUNT = 1;
        ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT);
        for (Pair item : list) {
            parserDb.addPage(new Page(CATEGORY_ID, NO_MISSION, (String) item.title, (String) item.content));
        }
        String arrayName = "_56_INSTANCE_hH2r_tempArray";
        ArrayList<Pair> imgList = super.getJsPage(JS_POSITION, arrayName);
        int mission_id = 9;
        for (Pair pair :
                imgList) {
            String finalContents = ((String) pair.content).substring(0, ((String) pair.content).length() - 2);

            finalContents = finalContents.replace("\\n", "");
            finalContents = finalContents.replace("\\t", "");
            finalContents = finalContents.replace("\\", "");

            ArrayList<String> urlList = getImageList(Jsoup.parse(finalContents));
            String IMG_NAME = "MISSION_IMG";
            for (String url :
                    urlList) {
                parserDb.addPage(new Page(CATEGORY_ID, mission_id, IMG_NAME, url));
                mission_id++;
            }
        }
    }

    private ArrayList<String> getImageList(Document document) {
        ArrayList<String> contentList = new ArrayList<>();
        Elements contents = document.select(imageListClass);
        for (Element content : contents) {
            contentList.add(content.html());
        /* if(contentList.size() >= maxItems){
                break;
   }*/
        }
        ArrayList<String> finalList = new ArrayList<>();
        for (String singleEl :
                contentList) {

            Document doc = Jsoup.parseBodyFragment(singleEl);
            Element el = doc.select("a img").first();
            finalList.add(el.attr("src"));
        }
        //Swap earth care image with biomass image since we don't have biomass in the list
        if (finalList.size() == 3) {
            Collections.swap(finalList, 1, 2);
        }
        return finalList;
/*
  return contentList;
*/
    }


}
