package pl.wasat.smarthma.parser.missions.HistoricalMissions;

import android.content.Context;

import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.SimpleMissionInterface;
import pl.wasat.smarthma.parser.model.Mission;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel on 2015-08-13.
 */
public class Alos extends BaseParser implements SimpleMissionInterface {
	public final static int MISSION_ID = 43;
	public final static String TITLE = "Alos";

	final int ITEMS_COUNT = 1;

	final String DESIGN = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/design";
	final String CONCEPT = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/concept";
	final String OPERATIONS = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/operations";
	final String APPLICATIONS = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/applications";
	final String OBSERVATION_STRATEGY = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/observation-strategy";
	final String CVGC = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/calibration-validation-quality-control";
	final String INSTRUMENTS = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos/instruments";

	final String DESIGN_TITLE = "Design";
	final String CONCEPT_TITLE = "Concept";
	final String OPERATIONS_TITLE = "Operations";
	final String APPLICATIONS_TITLE = "Applications";
	final String OBSERVATION_STRATEGY_TITLE = "Observation Strategy";
	final String GVGC_TITLE = "Gvgc";
	final String INSTRUMENTS_TITLE = "Instruments";


	public Alos(String pageUrl, Context context) {
		super(pageUrl, context);
		parserDb.addMission(new Mission(MISSION_ID, HistoricalMissions.CATEGORY_ID, TITLE));

	}

	@Override
	public void mainContent() {
		ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT);
		for(Pair item : list){
			parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, (String)item.title,  (String)item.content ));
		}

		parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, DESIGN_TITLE,  DESIGN ));
		parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, CONCEPT_TITLE,  CONCEPT ));
		parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, OPERATIONS_TITLE,  OPERATIONS));
		parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, APPLICATIONS_TITLE,  APPLICATIONS));
		parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, OBSERVATION_STRATEGY_TITLE,  OBSERVATION_STRATEGY ));
		parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, GVGC_TITLE,  GVGC_TITLE ));
		parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, INSTRUMENTS_TITLE,  INSTRUMENTS ));

	}
}
