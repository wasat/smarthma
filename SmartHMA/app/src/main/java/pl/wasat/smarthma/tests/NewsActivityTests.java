package pl.wasat.smarthma.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import pl.wasat.smarthma.ui.activities.NewsActivity;
import pl.wasat.smarthma.ui.frags.news.NewsListFragment;

/**
 * Created by Dark Mark on 17/08/2015 00:09.
 * Part of the project  SmartHMA
 */
public class NewsActivityTests extends ActivityInstrumentationTestCase2<NewsActivity> {
    public NewsActivityTests() {
        super(NewsActivity.class);
    }

    public void testItems() {
        final NewsActivity activity = getActivity();
        assertNotNull("Null NewsActivity.", activity);

        final boolean[] tests = {false, false, false};

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    NewsListFragment fragment = activity.getNewsListFragment();
                    tests[0] = true;

                    ListView list = fragment.getListView();
                    tests[1] = true;

                    int position = 0;
                    list.performItemClick(list.getAdapter().getView(position, null, null),
                            position, list.getAdapter().getItemId(position));
                    tests[2] = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Wait for the UI thread to finish.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Operation 'activity.getNewsListFragment()' returned null.", true, tests[0]);
        assertEquals("Operation 'fragment.getListView()' returned null.", true, tests[1]);
        assertEquals("Operation 'list.performItemClick()' failed.", true, tests[2]);
    }
}
