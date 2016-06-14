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
import android.widget.ListView;

import pl.wasat.smarthma.adapter.CollectionsGroupListAdapter;
import pl.wasat.smarthma.ui.activities.CollectionsDefinitionActivity;
import pl.wasat.smarthma.ui.frags.browse.CollectionsGroupListFragment;

/**
 * The type Collections definition activity tests.
 */
public class CollectionsDefinitionActivityTests extends ActivityInstrumentationTestCase2<CollectionsDefinitionActivity> {
    /**
     * Instantiates a new Collections definition activity tests.
     */
    public CollectionsDefinitionActivityTests() {
        super(CollectionsDefinitionActivity.class);
    }

    /**
     * Test results.
     */
    public void testResults() {
        final CollectionsDefinitionActivity activity = getActivity();
        assertNotNull("Null CollectionsDefinitionActivity.", activity);

        final boolean[] tests = {false, false, false, false, false, false, false, false, false};

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    CollectionsGroupListFragment fragment = activity.getCollectionsGroupListFragment();
                    tests[0] = true;

                    ListView list = fragment.getCollectionsGroupListView();
                    tests[1] = true;

                    CollectionsGroupListAdapter adapter = (CollectionsGroupListAdapter) list.getAdapter();
                    tests[2] = true;

                    int position = 0;
                    View view = adapter.getView(position, null, null);
                    tests[3] = true;

                    long itemId = adapter.getItemId(position);
                    tests[4] = true;

                    list.performItemClick(view, position, itemId);
                    tests[5] = true;

                    //CollectionsListFragment fragment2 = fragment.getCollectionsListFragment();
                    tests[6] = true;

                    //ListView list2 = fragment2.getList();
                    tests[7] = true;

                    //list2.performItemClick(list2.getAdapter().getView(position, null, null),
                    //        position, list2.getAdapter().getItemId(position));
                    //tests[5] = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Wait for the UI thread to finish.
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Operation 'activity.getCollectionsGroupListFragment()' returned null.", true, tests[0]);
        assertEquals("Operation 'fragment.getCollectionsGroupListView()' returned null.", true, tests[1]);
        assertEquals("Operation 'list.getAdapter()' returned null.", true, tests[2]);
        assertEquals("Operation 'adapter.getView()' returned null.", true, tests[3]);
        assertEquals("Operation 'adapter.getItemId()' returned null.", true, tests[4]);
        assertEquals("Operation 'list.performItemClick()' failed.", true, tests[5]);
        assertEquals("Operation 'fragment.getCollectionsListFragment()' returned null.", true, tests[6]);
        assertEquals("Operation 'fragment2.getList()' returned null.", true, tests[7]);
    }
}
