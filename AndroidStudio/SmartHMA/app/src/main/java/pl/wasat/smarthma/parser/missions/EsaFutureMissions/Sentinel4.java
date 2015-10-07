package pl.wasat.smarthma.parser.missions.EsaFutureMissions;

import android.content.Context;

import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.SimpleMissionInterface;
import pl.wasat.smarthma.parser.model.Mission;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel paduch on 2015-08-11 00:09.
 * Part of the project  SmartHMA
 */
public class Sentinel4 extends BaseParser implements SimpleMissionInterface {

    private final static int MISSION_ID = 11;
    private final static String TITLE = "Sentinel-4";

    public Sentinel4(String pageUrl, Context context) {
        super(pageUrl, context);
        parserDb.addMission(new Mission(MISSION_ID, EsaFutureMissions.CATEGORY_ID, TITLE));
    }

    @Override
    public void mainContent() {
        String arrayName = "_56_INSTANCE_4mLS_tempArray";
        ArrayList<Pair> list = super.getJsPage(JS_POSITION, arrayName);
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                parserDb.addPage(new Page(EsaFutureMissions.CATEGORY_ID, MISSION_ID, MAIN_INFO + list.get(i).title, sanitizeJsContents((String) list.get(i).content)));
            }
        }
    }
}
