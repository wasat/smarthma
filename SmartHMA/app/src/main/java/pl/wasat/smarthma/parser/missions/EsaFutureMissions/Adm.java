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

package pl.wasat.smarthma.parser.missions.EsaFutureMissions;

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
public class Adm extends BaseParser implements MissionInterface {
    /**
     * The constant MISSION_ID.
     */
    public final static int MISSION_ID = 14;
    private final static String TITLE = "Adm-Aeolus";
    private static final String MISSION_OBJECTIVES = "mission-objectives";

    /**
     * Instantiates a new Adm.
     *
     * @param pageUrl the page url
     * @param context the context
     */
    public Adm(String pageUrl, Context context) {
        super(pageUrl, context);
        parserDb.addMission(new Mission(MISSION_ID, EsaFutureMissions.CATEGORY_ID, TITLE));
    }

    @Override
    public void mainContent() {
        int except = 1;
        int ITEMS_COUNT = 2;
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
        final String OBJECTIVES = MISSION_OBJECTIVES;
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
