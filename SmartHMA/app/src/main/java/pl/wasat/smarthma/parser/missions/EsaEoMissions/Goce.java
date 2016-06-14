/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
public class Goce extends BaseParser implements MissionInterface {
    private final static int MISSION_ID = 6;
    private final static String TITLE = "Goce";

    private final int JS_POSITION = 9;

    /**
     * Instantiates a new Goce.
     *
     * @param pageUrl the page url
     * @param context the context
     */
    public Goce(String pageUrl, Context context) {
        super(pageUrl, context);
        parserDb.addMission(new Mission(MISSION_ID, EsaEoMissions.CATEGORY_ID, TITLE));

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
        String arrayName = "_56_INSTANCE_d9Ul_tempArray";
        ArrayList<Pair> list = super.getJsPage(OBJECTIVES, JS_POSITION, arrayName);
        for (Pair pair : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        }
    }

    @Override
    public void satellite() {
        String arrayName = "_56_INSTANCE_9jMi_tempArray";
        ArrayList<Pair> list = super.getJsPage(SATELLITE, JS_POSITION, arrayName);
        for (Pair pair : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        }
    }

    @Override
    public void groundSegment() {

        Pair pair = super.getSimplePage(SATELLITE + "/" + GROUND_SEGMENT);
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
    }

    @Override
    public void instruments() {
        final String EEG_TITLE = "Eeg";
        final String SSTI_TITLE = "Ssti";
        Pair pair = super.getSimplePage(INSTRUMENTS);

        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        String EGG = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/goce/instruments/egg";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, EEG_TITLE, EGG));
        String SSTI = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/goce/instruments/ssti";
        parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, SSTI_TITLE, SSTI));
    }

    @Override
    public void scientificRequirements() {

    }

    @Override
    public void operations() {

    }

    @Override
    public void applications() {
        String arrayName = "_56_INSTANCE_8ScT_tempArray";
        ArrayList<Pair> list = super.getJsPage(APPLICATIONS, JS_POSITION, arrayName);
        for (Pair pair : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        }
    }

    @Override
    public void science() {
        String arrayName = "_56_INSTANCE_4yXL_tempArray";
        ArrayList<Pair> list = super.getJsPage(SCIENCE, JS_POSITION, arrayName);
        for (Pair pair : list) {
            parserDb.addPage(new Page(EsaEoMissions.CATEGORY_ID, MISSION_ID, (String) pair.title, (String) pair.content));
        }

    }

    @Override
    public void history() {

    }

    @Override
    public void industry() {

    }
}
