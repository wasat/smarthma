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
 * Created by marcel on 2015-08-11 00:09.
 * Part of the project  SmartHMA
 */
public class Proba1 extends BaseParser implements MissionInterface {
    final String NEWS = "";
    private final static int MISSION_ID = 7;
    private final int THIRD_PARTY_MISSION_ID = 28;
    private final static String TITLE = "Proba-1";


    public Proba1(String pageUrl, Context context) {
        super(pageUrl, context);


        parserDb.addMission(new Mission(MISSION_ID, EsaEoMissions.CATEGORY_ID, TITLE));
        parserDb.addMission(new Mission(THIRD_PARTY_MISSION_ID, ThirdPartyMissions.CATEGORY_ID, TITLE));

    }

    @Override
    public void mainContent() {
        int ITEMS_COUNT = 1;
        ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT);
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
    public void faq() {

    }

    @Override
    public void other() {

    }

    @Override
    public void overview() {

    }

    @Override
    public void objectives() {

    }

    @Override
    public void satellite() {
        Pair pair = super.getSimplePage(SATELLITE);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        parserDb.addPage(new Page(ThirdPartyMissions.CATEGORY_ID, THIRD_PARTY_MISSION_ID, (String) pair.title, (String) pair.content));
    }

    @Override
    public void groundSegment() {
        Pair pair = super.getSimplePage(GROUND_SEGMENT);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        parserDb.addPage(new Page(ThirdPartyMissions.CATEGORY_ID, THIRD_PARTY_MISSION_ID, (String) pair.title, (String) pair.content));


    }

    @Override
    public void instruments() {
        final String CHRIS_TITLE = "Chris";
        final String HRC_TITLE = "Hrc";
        Pair pair = super.getSimplePage(INSTRUMENTS);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));

        String CHRIS = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/proba/instruments/chris";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, CHRIS_TITLE, CHRIS));
        String HRC = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/proba/instruments/hrc";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, HRC_TITLE, HRC));

        parserDb.addPage(new Page(ThirdPartyMissions.CATEGORY_ID, THIRD_PARTY_MISSION_ID, (String) pair.title, (String) pair.content));

        parserDb.addPage(new Page(ThirdPartyMissions.CATEGORY_ID, THIRD_PARTY_MISSION_ID, CHRIS_TITLE, CHRIS));
        parserDb.addPage(new Page(ThirdPartyMissions.CATEGORY_ID, THIRD_PARTY_MISSION_ID, HRC_TITLE, HRC));

    }

    @Override
    public void scientificRequirements() {

    }

    @Override
    public void operations() {

    }

    @Override
    public void applications() {

    }

    @Override
    public void science() {

    }

    @Override
    public void history() {
        Pair pair = super.getSimplePage(HISTORY);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        parserDb.addPage(new Page(ThirdPartyMissions.CATEGORY_ID, THIRD_PARTY_MISSION_ID, (String) pair.title, (String) pair.content));

    }

    @Override
    public void industry() {

    }


}
