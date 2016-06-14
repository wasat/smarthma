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

package pl.wasat.smarthma.tests;

/**
 * Created by Dark Mark on 2015-07-20 22:38.
 * Part of the project  SmartHMA
 */

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import java.util.ArrayList;

import pl.wasat.smarthma.database.SearchHistory;
import pl.wasat.smarthma.database.SearchParams;
import pl.wasat.smarthma.ui.activities.SearchActivity;
import pl.wasat.smarthma.ui.menus.SearchMenuHandler;

/**
 * The type Search activity tests.
 */
public class SearchActivityTests extends ActivityInstrumentationTestCase2<SearchActivity> {
    /**
     * Instantiates a new Search activity tests.
     */
    public SearchActivityTests() {
        super(SearchActivity.class);
    }

    /**
     * Test search history.
     */
    public void testSearchHistory() {
        SearchActivity activity = getActivity();
        assertNotNull("Null SearchActivity.", activity);

        String expected = "spot";

        SearchHistory searchHistory = new SearchHistory(activity.getApplicationContext());
        searchHistory.clearHistory();
        searchHistory.addSearchParameters(new SearchParams(expected, null, null, null, null));
        ArrayList<String> queries = searchHistory.getQueries(true);
        assertNotNull("Operation 'searchHistory.getQueries()' returned null.", queries);

        String result = queries.get(0);
        assertEquals("Wrong result for operation 'searchHistory.getQueries()'.", expected, result);
    }

    /**
     * Test search menu.
     */
    public void testSearchMenu() {
        final SearchActivity activity = getActivity();
        assertNotNull("Null SearchActivity.", activity);

        final boolean[] tests = {false, false, false, false, true, true, true};

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SearchMenuHandler menuHandler = (SearchMenuHandler) activity.getMenuHandler();
                tests[0] = true;

                View menu = menuHandler.getMenu();
                tests[1] = true;

                boolean menuClicked = menu.performClick();
                tests[2] = true;

                ArrayList<View> clickableViews = menuHandler.getClickableViews();
                tests[3] = true;

                if (clickableViews.size() <= 0)
                {
                    tests[4] = false;
                    return;
                }

                View view = clickableViews.get(0);
                //for (View view : clickableViews)
                {
                    if (view == null) {
                        tests[5] = false;
                        //break;
                    }
                    if (!view.performClick())
                    {
                        tests[6] = false;
                    }
                }
            }
        });

        // Wait for the UI thread to finish.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Operation 'activity.getMenuHandler()' returned null.", true, tests[0]);
        assertEquals("Operation 'menuHandler.getMenu()' returned null.", true, tests[1]);
        assertEquals("Operation 'menu.performClick()' failed (menu has no click listener).", true, tests[2]);
        assertEquals("Operation 'menuHandler.getClickableViews()' returned null.", true, tests[3]);
        assertEquals("The list of clickable views is empty.", true, tests[4]);
        assertEquals("At least one of clickable views is null.", true, tests[5]);
        assertEquals("Operation 'view.performClick()' failed (view has no click listener).", true, tests[6]);

        //getInstrumentation().runOnMainSync(new Runnable()
        //{
        //    @Override
        //    public void run()
        //    {
        //        //nameEditText.requestFocus();
        //    }
        //});
    }
}
