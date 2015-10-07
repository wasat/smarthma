package pl.wasat.smarthma.parser.missions.EsaEoMissions;

import android.content.Context;

import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.MissionInterface;
import pl.wasat.smarthma.parser.model.Mission;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel paduch on 2015-08-10 00:09.
 * Part of the project  SmartHMA
 */
public class Swarm extends BaseParser implements MissionInterface {
    private final static String TITLE = "Swarm";


    private final static int MISSION_ID = 1;

    public Swarm(String pageUrl, Context context) {
        super(pageUrl, context);
        parserDb.addMission(new Mission(MISSION_ID, EsaEoMissions.CATEGORY_ID, TITLE));

    }

    @Override
    public void mainContent() {
        int ITEMS_COUNT = 1;
        ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT);
        for (Pair item : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) item.title, (String) item.content));
        }
    }

    @Override
    public void news() {
        String NEWS = "https://earth.esa.int/eodisp-portlets/eodisp-rss?tags=news,swarm";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, NEWS_TITLE, NEWS));
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
    public void milestones() {
    }

    @Override
    public void faq() {
        String FAQ = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/swarm/faqs";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, FAQ_TITLE, FAQ));
    }

    @Override
    public void other() {
        final String DATA_HANDBOOK_TITLE = "Product Data Handbook";
        final String DATA_ACCESS_TITLE = "Data Access";

        String DATA_HANDBOOK = "http://swarm-wiki.spacecenter.dk/mediawiki-1.21.1/index.php/Main_Page";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, DATA_HANDBOOK_TITLE, DATA_HANDBOOK));
        String DATA_ACCESS = "https://earth.esa.int/web/guest/swarm/data-access";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, DATA_ACCESS_TITLE, DATA_ACCESS));
    }
}
