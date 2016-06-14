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

package pl.wasat.smarthma.ui.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.SearchListAdapter;
import pl.wasat.smarthma.database.FavouritesDbAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.ui.activities.base.ExtendedBaseCollectionsActivity;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment;
import pl.wasat.smarthma.ui.frags.search.SearchListFragmentOffline;
import pl.wasat.smarthma.ui.menus.SearchOfflineCollectionsMenuHandler;
import pl.wasat.smarthma.utils.obj.LatLngExt;

/**
 * Activity used to browse and manage saved collections.
 */
public class FavouriteCollectionsActivity extends ExtendedBaseCollectionsActivity {

    private FavouritesDbAdapter dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        assert actionBar != null;

        TextView title = (TextView) findViewById(R.id.action_bar_title);
        title.setText(getString(R.string.activity_name_favourite_collections_results));

        dba = new FavouritesDbAdapter(this);

        commonMenuHandler = new SearchOfflineCollectionsMenuHandler(this, R.id.menu_button);

        SearchListFragmentOffline searchListFragment = SearchListFragmentOffline
                .newInstance(stopNewSearch);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container,
                        searchListFragment).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        stopNewSearch = resultCode == RESULT_OK && data.getBooleanExtra(Const.KEY_INTENT_RETURN_STOP_SEARCH, true);

        SearchListFragmentOffline searchListFrag = (SearchListFragmentOffline) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container);
        if (searchListFrag != null) {
            Bundle bundle = searchListFrag.getArguments();
            bundle.putBoolean(Const.KEY_INTENT_RETURN_STOP_SEARCH,
                    stopNewSearch);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.wasat.smarthma.ui.fragments.SearchListFragment.
     * OnSearchListFragmentListener
     * #onSearchListFragmentItemSelected(java.lang.String)
     */
    @Override
    public void onSearchListFragmentItemSelected(String id) {
        EntryISO selectedEntry = (EntryISO) ((SearchListFragmentOffline) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter().getItem(Integer.parseInt(id));

        if (selectedEntry.isNotRead()) {
            selectedEntry.setRead();
            dba.openToWrite();
            dba.replaceEntry(selectedEntry);
            dba.close();
        }

        SearchListAdapter adapter = (SearchListAdapter) ((SearchListFragmentOffline) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter();
        adapter.notifyDataSetChanged();

        CollectionDetailsFragment collectionDetailsFragment = CollectionDetailsFragment
                .newInstance(selectedEntry);
        String fragmentTag = CollectionDetailsFragment.class.getSimpleName();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        collectionDetailsFragment,
                        fragmentTag)
                .addToBackStack(fragmentTag)
                .commit();
    }

    @Override
    public void onSearchListFragmentRightSwipeItem(EntryISO selectedEntry) {

    }

    @Override
    public void onSearchListFragmentLeftSwipeItem(EntryISO selectedEntry) {

    }

    /**
     * Clear list.
     */
    public void clearList() {
        dba.openToWrite();
        dba.clearCollections();
        dba.close();
        getListFragment().clearEntries();
        refreshList();
    }

    /**
     * Returns the list fragment associated with this object.
     *
     * @return A list fragment.
     */
    public SearchListFragmentOffline getListFragment() {
        return ((SearchListFragmentOffline) getSupportFragmentManager().findFragmentById(R.id.activity_base_list_container));
    }

    /**
     * Refreshes contents of the collections list.
     */
    public void refreshList() {
        SearchListAdapter adapter = (SearchListAdapter) ((SearchListFragmentOffline) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMapFragmentAreaInputChange(int areaType) {

    }

    @Override
    public void onMapFragmentPointAndRadiusSend(LatLngExt center, float radius) {

    }


}
