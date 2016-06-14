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

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ExpandableListView;

import pl.wasat.smarthma.ui.activities.MissionsActivity;
import pl.wasat.smarthma.ui.frags.missions.MissionsExtListFragment;

/**
 * Created by Dark Mark on 17/08/2015 00:09.
 * Part of the project  SmartHMA
 */
public class MissionsActivityTests extends ActivityInstrumentationTestCase2<MissionsActivity> {
    /**
     * Instantiates a new Missions activity tests.
     */
    public MissionsActivityTests() {
        super(MissionsActivity.class);
    }

    /**
     * Test items.
     */
    public void testItems() {
        final MissionsActivity activity = getActivity();
        assertNotNull("Null MissionsActivity.", activity);

        final boolean[] tests = {false, false, false};

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    MissionsExtListFragment fragment = activity.getMissionsExtListFragment();
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
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Operation 'activity.getMissionsExtListFragment()' returned null.", true, tests[0]);
        assertEquals("Operation 'fragment.getExpListView()' returned null.", true, tests[1]);
        assertEquals("Operation 'list.performItemClick()' failed.", true, tests[2]);
    }
}
