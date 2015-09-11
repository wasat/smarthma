package pl.wasat.smarthma.parser.missions.EsaEoMissions;

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
public class Smos extends BaseParser implements MissionInterface {
	final int ITEMS_COUNT = 1;
	final String INSTRUMENT = "instrument";
	public final static int MISSION_ID = 4;
	final static String TITLE = "Smos";
	final String SPACE_SEGMENT = "space-segment";

	public Smos(String pageUrl, Context context) {
		super(pageUrl, context);
		parserDb.addMission(new Mission(MISSION_ID, EsaEoMissions.CATEGORY_ID, TITLE));

	}

	@Override
	public void mainContent() {
		ArrayList<Pair> list = super.getComplexPage(ITEMS_COUNT);
		for(Pair item : list){
			parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String)item.title,  (String)item.content ));
		}
	}

	@Override
	public void news() {

	}

	@Override
	public void milestones() {

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
		Pair pair = super.getSimplePage(OBJECTIVES);
		parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID,(String) pair.title, (String)pair.content));
	}

	@Override
	public void satellite() {
		Pair pair = super.getSimplePage(SPACE_SEGMENT + "/" + SATELLITE);
		parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID,(String) pair.title, (String)pair.content));
	}

	@Override
	public void groundSegment() {

	}

	@Override
	public void instruments() {
		Pair pair = super.getSimplePage(SPACE_SEGMENT + "/" + INSTRUMENT);
		parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID,(String) pair.title, (String)pair.content));
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

	@Override
	public void applications() {

	}

	@Override
	public void scientificRequirements() {
		Pair pair = super.getSimplePage(SCIENTIFIC_REQUIREMENTS);
		parserDb.addPage( new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID,(String) pair.title, (String)pair.content));
	}

	@Override
	public void operations() {
		Pair pair = super.getSimplePage(OPERATIONS);
		parserDb.addPage( new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID,(String) pair.title, (String)pair.content));
	}
}
