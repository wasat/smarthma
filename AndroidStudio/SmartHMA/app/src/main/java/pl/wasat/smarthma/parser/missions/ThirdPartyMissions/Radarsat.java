package pl.wasat.smarthma.parser.missions.ThirdPartyMissions;

import android.content.Context;

import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.SimpleMissionInterface;
import pl.wasat.smarthma.parser.model.Mission;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel on 2015-08-11 00:09.
 * Part of the project  SmartHMA
 */
public class Radarsat extends BaseParser implements SimpleMissionInterface {
    private final static int MISSION_ID = 30;
    private final static String TITLE = "Radarsat";

    public Radarsat(String pageUrl, Context context) {
        super(pageUrl, context);
        parserDb.addMission(new Mission(MISSION_ID, ThirdPartyMissions.CATEGORY_ID, TITLE));

    }

    @Override
    public void mainContent() {
        int ITEMS_COUNT = 2;
        ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT);
        for (Pair item : list) {
            parserDb.addPage(new Page(ThirdPartyMissions.CATEGORY_ID, MISSION_ID, (String) item.title, (String) item.content));
        }
    }
}
