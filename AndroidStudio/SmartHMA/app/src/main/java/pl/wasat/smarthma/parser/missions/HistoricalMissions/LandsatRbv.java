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
public class LandsatRbv extends BaseParser implements SimpleMissionInterface {
    public final static int MISSION_ID = 47;
    public final static String TITLE = "Landsat RBV";

    final int ITEMS_COUNT = 3;

    public LandsatRbv(String pageUrl, Context context) {
        super(pageUrl, context);
        parserDb.addMission(new Mission(MISSION_ID, HistoricalMissions.CATEGORY_ID, TITLE));

    }

    @Override
    public void mainContent() {
        ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT);
        for (Pair item : list) {
            parserDb.addPage(new Page(HistoricalMissions.CATEGORY_ID, MISSION_ID, (String) item.title, (String) item.content));
        }
    }
}
