package pl.wasat.smarthma.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ExpandableListView;

import pl.wasat.smarthma.ui.activities.MissionsActivity;
import pl.wasat.smarthma.ui.frags.missions.MissionsExtListFragment;

/**
 * Created by Dark Mark on 17/08/2015 00:09.
 * Part of the project  SmartHMA
 */
public class MissionsActivityTests extends ActivityInstrumentationTestCase2<MissionsActivity> {
    public MissionsActivityTests() {
        super(MissionsActivity.class);
    }

    public void testItems() {
        final MissionsActivity activity = getActivity();
        assertNotNull("Null MissionsActivity.", activity);

        final boolean[] tests = {false, false, false};

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    MissionsExtListFragment fragment = activity.getExtendedListFragment();
                    tests[0] = true;

                    ExpandableListView list = fragment.getExpListView();
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

        assertEquals("Operation 'activity.getExtendedListFragment()' returned null.", true, tests[0]);
        assertEquals("Operation 'fragment.getExpListView()' returned null.", true, tests[1]);
        assertEquals("Operation 'list.performItemClick()' failed.", true, tests[2]);
    }
}
