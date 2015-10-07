package pl.wasat.smarthma.parser.missions.EsaEoMissions;

import android.content.Context;

import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.MissionInterface;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.ThirdPartyMissions;
import pl.wasat.smarthma.parser.model.Mission;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel paduch on 2015-08-07 00:09.
 * Part of the project  SmartHMA
 */
public class ProbaV extends BaseParser implements MissionInterface {
    private final static int MISSION_ID = 2;
    private final int THIRD_PARTY_MISSION_ID = 29;
    private final static String TITLE = "Proba-V";

    public ProbaV(String pageUrl, Context context) {
        super(pageUrl, context);
        final int THIRD_PARTY_MISSION_ID = 29;
        parserDb.addMission(new Mission(MISSION_ID, EsaEoMissions.CATEGORY_ID, TITLE));
        parserDb.addMission(new Mission(THIRD_PARTY_MISSION_ID, ThirdPartyMissions.CATEGORY_ID, TITLE));


    }

    public void getImageOfTheWeek() {
        int IMG_OF_THE_WEEK_COUNT = 42;
        //Pair pair =
        super.getImageListPage(IMG_OF_THE_WEEK, IMG_OF_THE_WEEK_COUNT, true);

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
    public void mainContent() {

        int ITEMS_COUNT = 3;
        ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT/*, exclude*/);
        for (Pair item : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) item.title, (String) item.content));
            parserDb.addPage(new Page(ThirdPartyMissions.CATEGORY_ID, THIRD_PARTY_MISSION_ID, (String) item.title, (String) item.content));
        }


    }

    @Override
    public void news() {
    }

    @Override
    public void milestones() {
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
    public void faq() {
        String FAQ = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/proba-v/faqs";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, FAQ_TITLE, FAQ));
        parserDb.addPage(new Page(ThirdPartyMissions.CATEGORY_ID, THIRD_PARTY_MISSION_ID, FAQ_TITLE, FAQ));
    }

    @Override
    public void other() {
    }
}
