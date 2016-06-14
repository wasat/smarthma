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
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import java.util.Calendar;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.SearchListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment.OnAmznAreaPickerMapFragmentListener;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.model.osdd.OSDDMatcher;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.activities.base.BaseSmartHMActivity;
import pl.wasat.smarthma.ui.dialogs.LoginDialogFragment.OnLoginDialogFragmentListener;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment.OnCollectionDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.common.DatePickerFragment.OnDatePickerFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MetadataISOFragment;
import pl.wasat.smarthma.ui.frags.common.TimePickerFragment.OnTimePickerFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment;
import pl.wasat.smarthma.ui.frags.search.SearchListFragmentBase;
import pl.wasat.smarthma.ui.menus.MenuHandler;
import pl.wasat.smarthma.ui.menus.SearchCollectionsMenuHandler;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.obj.LatLngExt;

/**
 * The type Search collection results activity.
 */
public class SearchCollectionResultsActivity extends BaseSmartHMActivity
        implements SearchListFragmentBase.OnSearchListFragmentListener,
        OnAreaPickerMapFragmentListener, OnAmznAreaPickerMapFragmentListener,
        OnCollectionDetailsFragmentListener, OnDatePickerFragmentListener, OnTimePickerFragmentListener, OnLoginDialogFragmentListener {

    private EoDbAdapter dba;
    private CollectionDetailsFragment collectionDetailsFragment;
    private MenuHandler menuHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        assert actionBar != null;

        TextView title = (TextView) findViewById(R.id.action_bar_title);
        title.setText(getString(R.string.activity_name_search_collections_results));

        handleIntent(getIntent());
        dba = new EoDbAdapter(this);

        menuHandler = new SearchCollectionsMenuHandler(this, R.id.menu_button);
    }

    /**
     * Handling intent data
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);

            SharedPrefs sharedPrefs = new SharedPrefs(this);
            sharedPrefs.setQueryPrefs(query);

            FedeoRequestParams fedeoRequestParams = (FedeoRequestParams)
                    intent.getSerializableExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS_OSDD);
            fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_KEY_QUERY, query);

            SearchListFragment searchListFragment = SearchListFragment
                    .newInstance(fedeoRequestParams, stopNewSearch);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_base_list_container,
                            searchListFragment).commit();
        }
        if (intent.getAction().equals(Const.KEY_ACTION_SEARCH_MISSION_DATA)) {

            String mission = intent.getStringExtra(Const.KEY_INTENT_MISSION_NAME);
            boolean isPlatformParamSet = intent.getBooleanExtra(Const.KEY_INTENT_MISSION_PARAM, false);
            FedeoRequestParams fedeoRequestParams = new FedeoRequestParams(false, false);
            FedeoRequestParams.IS_BUILD_FROM_SHARED = false;
            fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_NAME_PARENT_IDENTIFIER, "EOP:ESA:FEDEO");
            fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_NAME_TYPE, OSDDMatcher.PARAM_VALUE_COLLECTION);

            if (isPlatformParamSet) {
                fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_NAME_PLATFORM, mission);
            } else {
                fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_NAME_QUERY, mission);
            }

            SearchListFragment searchListFragment = SearchListFragment
                    .newInstance(fedeoRequestParams, stopNewSearch);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_base_list_container,
                            searchListFragment).commit();
        }
        if (intent.getAction().equals(Const.KEY_ACTION_SEARCH_COLLECTIONS)) {
            FedeoRequestParams fedeoRequestParams = (FedeoRequestParams) intent.getSerializableExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS);
            SearchListFragment searchListFragment = SearchListFragment
                    .newInstance(fedeoRequestParams, stopNewSearch);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_base_list_container,
                            searchListFragment).commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        stopNewSearch = resultCode == RESULT_OK && data.getBooleanExtra(Const.KEY_INTENT_RETURN_STOP_SEARCH, true);

        SearchListFragment searchListFrag = (SearchListFragment) getSupportFragmentManager()
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
     * @see android.support.v4.app.FragmentActivity#onBackPressed()
     */
    @Override
    public void onBackPressed() {
        try {
            if (menuHandler.isPopupWindowVisible()) {
                menuHandler.dismissPopupWindow();
                return;
            }
            if (dismissMenuOnBackPressed()) return;

            FragmentManager fm = getSupportFragmentManager();
            int bsec = fm.getBackStackEntryCount();
            String bstEntry = fm.getBackStackEntryAt(bsec - 1).getName();
            if (bsec > 1) {
                if (bstEntry.equalsIgnoreCase(CollectionDetailsFragment.class.getSimpleName())) {
                    while (bsec > 1) {
                        fm.popBackStackImmediate();
                        bsec = fm.getBackStackEntryCount();
                    }
                } else {
                    while (!bstEntry.equalsIgnoreCase(CollectionDetailsFragment.class.getSimpleName())) {
                        fm.popBackStackImmediate();
                        bsec = fm.getBackStackEntryCount();
                        bstEntry = fm.getBackStackEntryAt(bsec - 1).getName();
                    }
                }
            } else {
                finish();
                super.onBackPressed();
            }
        } catch (Exception e) {
            super.onBackPressed();
        }
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
        EntryISO selectedEntry = (EntryISO) ((SearchListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter().getItem(Integer.parseInt(id));

        dba.openToWrite();
        dba.markAsRead(selectedEntry.getGuid());
        dba.close();
        selectedEntry.setRead();
        SearchListAdapter adapter = (SearchListAdapter) ((SearchListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter();
        adapter.notifyDataSetChanged();

        collectionDetailsFragment = CollectionDetailsFragment
                .newInstance(selectedEntry);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        collectionDetailsFragment,
                        CollectionDetailsFragment.class.getSimpleName())
                .addToBackStack(CollectionDetailsFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onSearchListFragmentRightSwipeItem(EntryISO selectedEntry) {
        FedeoRequestParams fedeoSearchProductsParams = new FedeoRequestParams();
        String osddUrl = selectedEntry.getSpecLink(Link.REL_SEARCH, Link.TYPE_OSDD_XML);
        startProductSearchIntent(fedeoSearchProductsParams, osddUrl);
    }

    @Override
    public void onSearchListFragmentLeftSwipeItem(EntryISO selectedEntry) {
        startMetadataFragment(selectedEntry);
    }

    private void startMetadataFragment(EntryISO displayedEntry) {
        MetadataISOFragment metadataISOFragment = MetadataISOFragment
                .newInstance(displayedEntry);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        metadataISOFragment, MetadataISOFragment.class.getSimpleName())
                .addToBackStack(MetadataISOFragment.class.getSimpleName())
                .commit();
    }

    private void startProductSearchIntent(FedeoRequestParams fedeoSearchProductsParams, String osddUrl) {
        Intent showProductsIntent = new Intent(this,
                ProductsBrowserActivity.class);
        showProductsIntent.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS, fedeoSearchProductsParams);
        showProductsIntent.putExtra(Const.KEY_INTENT_FEDEO_OSDD_URL, osddUrl);
        startActivityForResult(showProductsIntent, REQUEST_NEW_SEARCH);
    }

    @Override
    public void onMapFragmentBoundsChange(LatLngBoundsExt bounds) {
        callUpdateCollectionsBounds(bounds);
    }

    @Override
    public void onMapFragmentAreaInputChange(int areaType) {
        changeAreaInputView(areaType);
    }

    @Override
    public void onMapFragmentPointAndRadiusSend(LatLngExt center, float radius) {
        updatePointAndRadiusValues(center, radius);
    }

    @Override
    public void onAmznMapFragmentBoundsChange(LatLngBoundsExt bounds) {
        callUpdateCollectionsBounds(bounds);
    }

    @Override
    public void onAmznMapFragmentAreaInputChange(int areaType) {
        changeAreaInputView(areaType);
    }

    @Override
    public void onAmznMapFragmentPointAndRadiusSend(LatLngExt center, float radius) {
        updatePointAndRadiusValues(center, radius);
    }

    @Override
    public void onCollectionDetailsFragmentShowProducts(FedeoRequestParams fedeoSearchProductsParams) {
        startProductSearchIntent(fedeoSearchProductsParams, null);
    }

    @Override
    public void onCollectionDetailsFragmentShowMetadata(EntryISO displayedEntry) {
        startMetadataFragment(displayedEntry);
    }

    @Override
    public void onDatePickerFragmentDateChoose(Calendar calendar, String viewTag) {
        collectionDetailsFragment.setDateValues(calendar, viewTag);
    }

    @Override
    public void onTimePickerFragmentTimeChoose(Calendar calendar, String viewTag) {
        collectionDetailsFragment.setTimeValues(calendar, viewTag);
    }

    @Override
    public void onLoginDialogSignIn(String login, String password) {
        CollectionDetailsFragment collectionDetailsFragment = (CollectionDetailsFragment) getSupportFragmentManager()
                .findFragmentByTag(CollectionDetailsFragment.class.getSimpleName());
        if (collectionDetailsFragment != null) {
            collectionDetailsFragment.sendLoginValues(login, password);
        }
    }

    /**
     * Gets list fragment.
     *
     * @return the list fragment
     */
    public SearchListFragment getListFragment() {
        return ((SearchListFragment) getSupportFragmentManager().findFragmentById(R.id.activity_base_list_container));
    }

    /**
     * Refresh list.
     */
    public void refreshList() {
        SearchListAdapter adapter = (SearchListAdapter) ((SearchListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter();
        adapter.notifyDataSetChanged();
    }

    private void callUpdateCollectionsBounds(LatLngBoundsExt bounds) {
        CollectionDetailsFragment collectionDetailsFragment = (CollectionDetailsFragment) getSupportFragmentManager()
                .findFragmentByTag(CollectionDetailsFragment.class.getSimpleName());
        if (collectionDetailsFragment != null) {
            collectionDetailsFragment.updateAreaBounds(bounds);
        }
    }

    private void changeAreaInputView(int areaType) {
        CollectionDetailsFragment collectionDetailsFragment = (CollectionDetailsFragment) getSupportFragmentManager()
                .findFragmentByTag(CollectionDetailsFragment.class.getSimpleName());
        if (collectionDetailsFragment != null) {
            collectionDetailsFragment.loadProperAreaView(areaType);
        }
    }


    private void updatePointAndRadiusValues(LatLngExt center, float radius) {
        CollectionDetailsFragment collectionDetailsFragment = (CollectionDetailsFragment) getSupportFragmentManager()
                .findFragmentByTag(CollectionDetailsFragment.class.getSimpleName());
        if (collectionDetailsFragment != null) {
            collectionDetailsFragment.updateAreaPtAndRadius(center, radius);
        }
    }
}