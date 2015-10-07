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
public class Sentinel1 extends BaseParser implements MissionInterface {
    private final static int MISSION_ID = 0;
    private final static String TITLE = "SENTINEL-1";

    public Sentinel1(String pageUrl, Context context) {
        super(pageUrl, context);
        parserDb.addMission(new Mission(MISSION_ID, EsaEoMissions.CATEGORY_ID, TITLE));
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
    public void mainContent() {
        //final ArrayList<Integer> EXCLUDE_ITEM = new ArrayList<>(Arrays.asList(1,2));

        int ITEMS_COUNT = 2;
        ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT);
        for (Pair item : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) item.title, (String) item.content));
        }
    }

    @Override
    public void news() {
        String NEWS = "";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, NEWS_TITLE, NEWS));
    }

    @Override
    public void milestones() {
        String MILESTONES = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/sentinel-1/milestones";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, MILESTONES_TITLE, MILESTONES));
    }

    @Override
    public void faq() {
    }

    @Override
    public void other() {
    }
}
