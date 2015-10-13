package pl.wasat.smarthma.parser.missions.EsaEoMissions;

import android.content.Context;

import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.MissionInterface;
import pl.wasat.smarthma.parser.model.Mission;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel on 2015-08-10 00:09.
 * Part of the project  SmartHMA
 */
public class CryoSat extends BaseParser implements MissionInterface {
    private final static int MISSION_ID = 3;
    private final static String TITLE = "CryoSat";

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

    public CryoSat(String pageUrl, Context context) {
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
    public void overview() {
        Pair pair = super.getSimplePage(OVERVIEW);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
    }

    @Override
    public void objectives() {
        Pair pair = super.getSimplePage(OBJECTIVES);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));

    }

    @Override
    public void scientificRequirements() {

    }

    @Override
    public void operations() {

    }

    @Override
    public void satellite() {
        Pair pair = super.getSimplePage(SATELLITE);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
    }

    @Override
    public void groundSegment() {
        Pair pair = super.getSimplePage(GROUND_SEGMENT);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
    }

    @Override
    public void instruments() {
        final String SIRAL_NAME = "Siral";
        Pair pair = super.getSimplePage(INSTRUMENTS);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        String siralUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/cryosat/instruments/siral";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, SIRAL_NAME, siralUrl));
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
}
