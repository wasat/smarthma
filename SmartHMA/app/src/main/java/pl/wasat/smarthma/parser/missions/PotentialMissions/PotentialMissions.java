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

package pl.wasat.smarthma.parser.missions.PotentialMissions;

import android.content.Context;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.Parser.Pair;
import pl.wasat.smarthma.parser.missions.SimpleMissionInterface;
import pl.wasat.smarthma.parser.model.Category;
import pl.wasat.smarthma.parser.model.Page;

/**
 * Created by marcel on 2015-08-13 00:09.
 * Part of the project  SmartHMA
 */
public class PotentialMissions extends BaseParser implements SimpleMissionInterface {

    /**
     * The constant CATEGORY_ID.
     */
    public final static int CATEGORY_ID = 4;

    /**
     * Instantiates a new Potential missions.
     *
     * @param pageUrl the page url
     * @param context the context
     */
    public PotentialMissions(String pageUrl, Context context) {
        super(pageUrl, context);
    }

    @Override
    public void mainContent() {
        int ITEMS_COUNT = 5;
        Pair<String, ArrayList<String>> pair = getImageListPage(ITEMS_COUNT, false);
        String contents = super.imageListToContentString(pair);
        parserDb.addCategory(new Category(CATEGORY_ID, pair.title, contents));
        ArrayList<String> urlList = super.getImageList(pair.content);
        int mission_id = 53;
        String img_name = "MISSION_IMG";
        for (String url :
                urlList) {
            parserDb.addPage(new Page(CATEGORY_ID, mission_id, img_name, url));
            //System.out.println(new Page(CATEGORY_ID, mission_id, img_name, url).toString());
            mission_id++;
        }
    }

    public Pair<String, ArrayList<String>> getImageListPage(String pageName, int maxItems, boolean fullPage) {

        String title;
        ArrayList<String> contentList = new ArrayList<>();
        Document doc;

        try {
            if (pageName.equals("")) {
                if (fullPage) {
                    doc = Jsoup.connect(pageUrl + "/" + getAll).get();
                } else {
                    doc = Jsoup.connect(pageUrl).get();
                }
            } else {
                if (fullPage) {
                    doc = Jsoup.connect(pageUrl + "/" + pageName + getAll).get();
                } else {
                    doc = Jsoup.connect(pageUrl + "/" + pageName).get();
                }
            }
            title = doc.select(titleClass).first().text();
            Elements contents = doc.select(imageListClass);
            Log.d("imgListSize", String.valueOf(contents.size()));
            for (int i = 1; i < contents.size(); i++) {
                contentList.add(contents.get(i).html());

                //System.out.println(getImageSource(content.html()));
                if (contentList.size() >= maxItems) {
                    break;
                }
            }
        } catch (IOException e) {
            Log.d(BaseParser.class.toString(), e.toString());
            title = "";
        }
        return new Pair<>(title, contentList);
    }
}
