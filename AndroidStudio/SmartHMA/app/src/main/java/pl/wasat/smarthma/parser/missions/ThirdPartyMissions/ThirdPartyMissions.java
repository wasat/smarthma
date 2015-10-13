package pl.wasat.smarthma.parser.missions.ThirdPartyMissions;

import android.content.Context;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.SimpleMissionInterface;
import pl.wasat.smarthma.parser.model.Category;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel paduch on 2015-08-11 00:09.
 * Part of the project  SmartHMA
 */
public class ThirdPartyMissions extends BaseParser implements SimpleMissionInterface {

    public final static int CATEGORY_ID = 2;


    public ThirdPartyMissions(String pageUrl, Context context) {
        super(pageUrl, context);
        String CONTENT = "";
        String TITLE = "3rd Party Missions";
        parserDb.addCategory(new Category(CATEGORY_ID, TITLE, CONTENT));

    }

    @Override
    public void mainContent() {
        final String SPOT1_IMG = "/documents/10174/1822598/SPOT-1/7867abc8-3b7e-4966-a438-2aaf6751c54b?t=1427887067639";
        final String SPOT2_IMG = "/documents/10174/1822702/SPOT-2/a9de0195-f48c-42df-a6e2-472c21a28952?t=1427889478138";
        final String SPOT3_IMG = "/documents/10174/1822750/SPOT-3/1fecd37a-bf61-461a-b932-b615439d6ae4?t=1427890753920";
        final String SPOT4_IMG = "/image/image_gallery?img_id=24236";
        final String SPOT5_IMG = "/image/image_gallery?img_id=15167";
        final String SPOT67_IMG = "/documents/10174/1822887/SPOT-6-7/f1669c60-3f29-4a0a-a6e0-10affd1b4509?t=1427894703559";

        final int NO_MISSION = -1;
        String CURRENT_MISSIONS = "current-missions";
        Pair<String, ArrayList<String>> current = getImageListPage(CURRENT_MISSIONS, 21, false);
        String HISTORICAL_MISSIONS = "historical-missions";
        Pair<String, ArrayList<String>> historical = super.getImageListPage(HISTORICAL_MISSIONS, 9, false);
        String POTENTIAL_MISSIONS = "potential-missions";
        Pair<String, ArrayList<String>> potential = super.getImageListPage(POTENTIAL_MISSIONS, 6, false);
        parserDb.addPage(new Page(ThirdPartyMissions.CATEGORY_ID, NO_MISSION, current.title, super.imageListToContentString(current)));
        parserDb.addPage(new Page(ThirdPartyMissions.CATEGORY_ID, NO_MISSION, historical.title, super.imageListToContentString(historical)));
        parserDb.addPage(new Page(ThirdPartyMissions.CATEGORY_ID, NO_MISSION, potential.title, super.imageListToContentString(potential)));
        ArrayList<String> urlList = super.getImageList(current.content);
        int mission_id = 16;
        int iterator = 0;
        String img_name = "MISSION_IMG";
        for (int i = 0; i < urlList.size() + 6; i++) {
            //obrazki do misji spot
            if (mission_id == Spot1.MISSION_ID) {
                parserDb.addPage(new Page(CATEGORY_ID, Spot1.MISSION_ID, img_name, SPOT1_IMG));
            } else if (mission_id == Spot2.MISSION_ID) {
                parserDb.addPage(new Page(CATEGORY_ID, Spot2.MISSION_ID, img_name, SPOT2_IMG));
            } else if (mission_id == Spot3.MISSION_ID) {
                parserDb.addPage(new Page(CATEGORY_ID, Spot3.MISSION_ID, img_name, SPOT3_IMG));
            } else if (mission_id == Spot4.MISSION_ID) {
                parserDb.addPage(new Page(CATEGORY_ID, Spot4.MISSION_ID, img_name, SPOT4_IMG));
            } else if (mission_id == Spot5.MISSION_ID) {
                parserDb.addPage(new Page(CATEGORY_ID, Spot5.MISSION_ID, img_name, SPOT5_IMG));
            } else if (mission_id == Spot67.MISSION_ID) {
                parserDb.addPage(new Page(CATEGORY_ID, Spot67.MISSION_ID, img_name, SPOT67_IMG));
            } else {
                parserDb.addPage(new Page(CATEGORY_ID, mission_id, img_name, urlList.get(iterator)));
                iterator++;
            }
            mission_id++;
        }
    }

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
            Log.d("imgListSize", String.valueOf(contents.size()));
            for (int i = 1; i < contents.size(); i++) {
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
