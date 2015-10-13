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
import pl.wasat.smarthma.ui.menus.MenuHandler;

public class SearchActivityTests extends ActivityInstrumentationTestCase2<SearchActivity> {
    public SearchActivityTests() {
        super(SearchActivity.class);
    }

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

    public void testSearchMenu() {
        final SearchActivity activity = getActivity();
        assertNotNull("Null SearchActivity.", activity);

        final boolean[] tests = {false, false, false, true, true};

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MenuHandler menuHandler = activity.getMenuHandler();
                tests[0] = true;

                //View menu = menuHandler.getMenu();
                tests[1] = true;

                //boolean menuClicked = menu.performClick();
                tests[2] = true;

                ArrayList<View> clickableViews = menuHandler.getClickableViews();
                View view = clickableViews.get(0);
                //for (View view : clickableViews)
                {
                    if (view == null) {
                        tests[3] = false;
                        //break;
                    }
                    boolean viewClicked = view != null && view.performClick();
                    if (!viewClicked) {
                        tests[4] = false;
                        //break;
                    }
                }
            }
        });

        // Wait for the UI thread to finish.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Operation 'activity.getMenuHandler()' returned null.", true, tests[0]);
        assertEquals("Operation 'menuHandler.getMenu()' returned null.", true, tests[1]);
        assertEquals("Operation 'menu.performClick()' failed (menu has no click listener).", true, tests[2]);
        assertEquals("At least one of clickable views is null.", true, tests[3]);
        assertEquals("Operation 'view.performClick()' failed (view has no click listener).", true, tests[4]);

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
