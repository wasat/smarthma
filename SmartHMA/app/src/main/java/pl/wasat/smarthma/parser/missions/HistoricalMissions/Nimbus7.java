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
public class Nimbus7 extends BaseParser implements SimpleMissionInterface {
    private final static int MISSION_ID = 49;
    private final static String TITLE = "Nimbus-7";

    public Nimbus7(String pageUrl, Context context) {
        super(pageUrl, context);
        parserDb.addMission(new Mission(MISSION_ID, HistoricalMissions.CATEGORY_ID, TITLE));

    }

    @Override
    public void mainContent() {
        int ITEMS_COUNT = 2;
        ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT);
        for (Pair item : list) {
            parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, (String) item.title, (String) item.content));
        }
    }
}
