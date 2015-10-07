package pl.wasat.smarthma.parser.missions.HistoricalMissions;

import android.content.Context;

import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.SimpleMissionInterface;
import pl.wasat.smarthma.parser.model.Mission;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel on 2015-08-13 00:09.
 * Part of the project  SmartHMA
 */
public class Alos extends BaseParser implements SimpleMissionInterface {
    private final static int MISSION_ID = 43;
    private final static String TITLE = "Alos";

    final String CVGC = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/calibration-validation-quality-control";


    public Alos(String pageUrl, Context context) {
        super(pageUrl, context);
        parserDb.addMission(new Mission(MISSION_ID, HistoricalMissions.CATEGORY_ID, TITLE));

    }

    @Override
    public void mainContent() {
        int ITEMS_COUNT = 1;
        ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT);
        for (Pair item : list) {
            parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, (String) item.title, (String) item.content));
        }

        String DESIGN = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/design";
        String DESIGN_TITLE = "Design";
        parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, DESIGN_TITLE, DESIGN));
        String CONCEPT = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/concept";
        String CONCEPT_TITLE = "Concept";
        parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, CONCEPT_TITLE, CONCEPT));
        String OPERATIONS = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/operations";
        String OPERATIONS_TITLE = "Operations";
        parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, OPERATIONS_TITLE, OPERATIONS));
        String APPLICATIONS = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/applications";
        String APPLICATIONS_TITLE = "Applications";
        parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, APPLICATIONS_TITLE, APPLICATIONS));
        String OBSERVATION_STRATEGY = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/observation-strategy";
        String OBSERVATION_STRATEGY_TITLE = "Observation Strategy";
        parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, OBSERVATION_STRATEGY_TITLE, OBSERVATION_STRATEGY));
        String GVGC_TITLE = "Gvgc";
        parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, GVGC_TITLE, GVGC_TITLE));
        String INSTRUMENTS = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/instruments";
        String INSTRUMENTS_TITLE = "Instruments";
        parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, INSTRUMENTS_TITLE, INSTRUMENTS));

    }
}
