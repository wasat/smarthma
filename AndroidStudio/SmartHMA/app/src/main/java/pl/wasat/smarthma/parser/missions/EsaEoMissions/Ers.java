package pl.wasat.smarthma.parser.missions.EsaEoMissions;

import android.content.Context;

import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.MissionInterface;
import pl.wasat.smarthma.parser.model.Mission;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel on 2015-08-11 00:09.
 * Part of the project  SmartHMA
 */
public class Ers extends BaseParser implements MissionInterface {

    final String RA = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/ers/instruments/ra";
    final String ATSR = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/ers/instruments/atsr";
    final String GOME = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/ers/instruments/gome";
    final String MWR = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/ers/instruments/mwr";
    final String SAR = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/ers/instruments/sar";
    final String WS = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/ers/instruments/ws";
    final String PRARE = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/ers/instruments/prare";
    private final static int MISSION_ID = 8;
    private final static String TITLE = "Ers";

    public Ers(String pageUrl, Context context) {
        super(pageUrl, context);
        parserDb.addMission(new Mission(MISSION_ID, EsaEoMissions.CATEGORY_ID, TITLE));

    }

    @Override
    public void mainContent() {
        int exclude = 1;
        int ITEMS_COUNT = 1;
        ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT, exclude);
        for (Pair item : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) item.title, (String) item.content));
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

    }

    @Override
    public void groundSegment() {
        Pair pair = super.getSimplePage(GROUND_SEGMENT);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
    }

    @Override
    public void instruments() {
        Pair pair = super.getSimplePage(INSTRUMENTS);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
    }

    @Override
    public void scientificRequirements() {

    }

    @Override
    public void operations() {
        Pair pair = super.getSimplePage(OPERATIONS);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
    }

    @Override
    public void applications() {

    }

    @Override
    public void science() {

    }

    @Override
    public void history() {

    }

    @Override
    public void industry() {

    }

}
