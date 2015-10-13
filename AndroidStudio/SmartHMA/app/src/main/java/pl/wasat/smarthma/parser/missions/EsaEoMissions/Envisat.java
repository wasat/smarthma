package pl.wasat.smarthma.parser.missions.EsaEoMissions;

import android.content.Context;

import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.MissionInterface;
import pl.wasat.smarthma.parser.model.Mission;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel paduch on 2015-08-11 00:09.
 * Part of the project  SmartHMA
 */
public class Envisat extends BaseParser implements MissionInterface {

    private final int JS_POSITION = 9;
    private final static int MISSION_ID = 6;
    private final static String TITLE = "Envisat";


    public Envisat(String pageUrl, Context context) {
        super(pageUrl, context);
        parserDb.addMission(new Mission(MISSION_ID, EsaEoMissions.CATEGORY_ID, TITLE));
    }

    @Override
    public void history() {
        Pair pair = super.getSimplePage(HISTORY);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
    }

    @Override
    public void industry() {
        String arrayName = "_56_INSTANCE_Vji5_tempArray";
        ArrayList<Pair> list = super.getJsPage(INDUSTRY, JS_POSITION, arrayName);
        for (Pair pair : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        }
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
        String arrayName = "_56_INSTANCE_zD94_tempArray";
        ArrayList<Pair> list = super.getJsPage(OBJECTIVES, JS_POSITION, arrayName);
        for (Pair pair : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        }
    }

    @Override
    public void satellite() {
        String arrayName = "_56_INSTANCE_MPm6_tempArray";
        ArrayList<Pair> list = super.getJsPage(SATELLITE, JS_POSITION, arrayName);
        for (Pair pair : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        }
    }

    @Override
    public void groundSegment() {
        final String GROUND_SEGMENT = "satellite/ground-segment";
        String arrayName = "_56_INSTANCE_A7vw_tempArray";
        ArrayList<Pair> list = super.getJsPage(GROUND_SEGMENT, JS_POSITION, arrayName);
        for (Pair pair : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        }
    }

    @Override
    public void instruments() {
        final String ASAR_NAME = "Asar";
        final String MERIS_NAME = "Meris";
        final String AATSR_NAME = "Aatsr";
        final String RA2_NAME = "Ra-2";
        final String MWR_NAME = "Mwr";
        final String GOMOS_NAME = "Gomos";
        final String MIPAS_NAME = "Mipas";
        final String SCIAMACHY_NAME = "Sciamachy";
        final String DORIS_NAME = "Doris";
        final String LRR_NAME = "Lrr";

        Pair pair = super.getSimplePage(INSTRUMENTS);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));

        String ASAR = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/envisat/instruments/asar";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, ASAR_NAME, ASAR));
        String MERIS = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/envisat/instruments/meris";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, MERIS_NAME, MERIS));
        String AATSR = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/envisat/instruments/aatsr";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, AATSR_NAME, AATSR));
        String RA2 = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/envisat/instruments/ra-2";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, RA2_NAME, RA2));
        String MWR = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/envisat/instruments/mwr";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, MWR_NAME, MWR));
        String GOMOS = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/envisat/instruments/gomos";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, GOMOS_NAME, GOMOS));
        String MIPAS = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/envisat/instruments/mipas";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, MIPAS_NAME, MIPAS));
        String SCIAMACHY = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/envisat/instruments/sciamachy";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, SCIAMACHY_NAME, SCIAMACHY));
        String DORIS = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/envisat/instruments/doris";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, DORIS_NAME, DORIS));
        String LRR = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/envisat/instruments/lrr";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, LRR_NAME, LRR));
    }

    @Override
    public void scientificRequirements() {

    }

    @Override
    public void operations() {
        String arrayName = "_56_INSTANCE_wHK8_tempArray";
        ArrayList<Pair> list = super.getJsPage(OPERATIONS, JS_POSITION, arrayName);
        for (Pair pair : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        }
    }

    @Override
    public void applications() {
        Pair pair = super.getSimplePage(APPLICATIONS);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
    }

    @Override
    public void science() {

    }
}
