package pl.wasat.smarthma.parser.missions.EsaFutureMissions;

import android.content.Context;

import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.MissionInterface;
import pl.wasat.smarthma.parser.model.Mission;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel paduch on 2015-08-11.
 */
public class Adm extends BaseParser implements MissionInterface {
    final int ITEMS_COUNT = 2;
    public final static int MISSION_ID = 14;
    public final static String TITLE = "Adm-Aeolus";

    public Adm(String pageUrl, Context context) {
        super(pageUrl, context);
        parserDb.addMission(new Mission(MISSION_ID, EsaFutureMissions.CATEGORY_ID, TITLE));
    }

    @Override
    public void mainContent() {
        int except = 1;
        ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT, except);
        for (Pair item : list) {
            parserDb.addPage(new Page(EsaFutureMissions.CATEGORY_ID, MISSION_ID, (String) item.title, (String) item.content));
        }
    }


    @Override
    public void news() {
    }

    @Override
    public void milestones() {
        Pair pair = super.getSimplePage(MILESTONES);
        parserDb.addPage(new Page(EsaFutureMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
    }

    @Override
    public void imageOfTheWeek() {

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
        final String OBJECTIVES = "mission-objectives";
        Pair pair = super.getSimplePage(OBJECTIVES);
        parserDb.addPage(new Page(EsaFutureMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
    }

    @Override
    public void satellite() {

    }

    @Override
    public void groundSegment() {

    }

    @Override
    public void instruments() {

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

    }

    @Override
    public void industry() {

    }
}
