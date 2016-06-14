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

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.interfaces.OnCollectionsListSelectionListener;
import pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment.OnAmznAreaPickerMapFragmentListener;
import pl.wasat.smarthma.kindle.AmznBaseMapFragment;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.ui.activities.base.BaseCollectionsActivity;
import pl.wasat.smarthma.ui.frags.browse.BrowseCollectionFirstDetailFragment;
import pl.wasat.smarthma.ui.frags.browse.CollectionEmptyDetailsFragment;
import pl.wasat.smarthma.ui.frags.browse.CollectionEmptyDetailsFragment.OnCollectionEmptyDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.browse.CollectionsGroupListFragment;
import pl.wasat.smarthma.ui.frags.browse.CollectionsListFragment;
import pl.wasat.smarthma.ui.frags.browse.CollectionsListFragment.OnCollectionsListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment.OnCollectionDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.common.DatePickerFragment.OnDatePickerFragmentListener;
import pl.wasat.smarthma.ui.frags.common.TimePickerFragment.OnTimePickerFragmentListener;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.obj.LatLngExt;
import roboguice.util.temp.Ln;

/**
 * The type Collections definition activity.
 */
public class CollectionsDefinitionActivity extends BaseCollectionsActivity
        implements OnCollectionsListSelectionListener,
        OnCollectionsListFragmentListener,
        OnCollectionDetailsFragmentListener,
        OnCollectionEmptyDetailsFragmentListener,
        OnAreaPickerMapFragmentListener,
        OnAmznAreaPickerMapFragmentListener,
        OnDatePickerFragmentListener, OnTimePickerFragmentListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private static boolean TWO_PANEL_MODE;
    private BrowseCollectionFirstDetailFragment browseCollectionFirstDetailFragment;
    private CollectionsGroupListFragment collectionsGroupListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Ln.getConfig().setLoggingLevel(Log.ERROR);
        super.onCreate(savedInstanceState);

        TextView text = (TextView) findViewById(R.id.action_bar_title);
        text.setText(R.string.searched_collections);

        if (findViewById(R.id.activity_base_details_container) != null) {
            TWO_PANEL_MODE = true;
            loadLeftListPanel();
            loadMapWithBasicSettingsView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_eo_map_twopane, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_exit:
                moveTaskToBack(true);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     *
     */
    private void loadLeftListPanel() {
        collectionsGroupListFragment = new CollectionsGroupListFragment();
        Bundle args = new Bundle();
        collectionsGroupListFragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container,
                        collectionsGroupListFragment).commit();
    }

    private void loadMapWithBasicSettingsView() {
        browseCollectionFirstDetailFragment = BrowseCollectionFirstDetailFragment
                .newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        browseCollectionFirstDetailFragment,
                        BrowseCollectionFirstDetailFragment.class.getSimpleName()).commit();
    }

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.app.FragmentActivity#onBackPressed()
     */
    @Override
    public void onBackPressed() {
        if (dismissMenuOnBackPressed()) return;

        FragmentManager fm = getSupportFragmentManager();
        int bsec = fm.getBackStackEntryCount();
        if (isBackStackEmpty(bsec)) return;

        String bstEntryName = fm.getBackStackEntryAt(bsec - 1).getName();
        if (bstEntryName.equalsIgnoreCase(CollectionEmptyDetailsFragment.class.getSimpleName())
                || bstEntryName.equalsIgnoreCase(CollectionDetailsFragment.class.getSimpleName())) {
            while (bsec > 0) {
                fm.popBackStackImmediate();
                bsec = fm.getBackStackEntryCount();
            }
        } else if (bstEntryName.equalsIgnoreCase(AreaPickerMapFragment.class.getSimpleName())) {
            while (bstEntryName.equalsIgnoreCase(AreaPickerMapFragment.class.getSimpleName())) {
                fm.popBackStackImmediate();
                bsec = fm.getBackStackEntryCount();
                if (isBackStackEmpty(bsec)) return;
                bstEntryName = fm.getBackStackEntryAt(bsec - 1).getName();
            }
        } else if (bstEntryName.equalsIgnoreCase(CollectionsListFragment.class.getSimpleName())) {
            fm.popBackStackImmediate();
        } else {
            finish();
            super.onBackPressed();
        }
    }

    private boolean isBackStackEmpty(int bsec) {
        if (bsec == 0) {
            finish();
            super.onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onCollectionSelected(Integer chosenCollectionId) {

        if (chosenCollectionId == -1) {
            Toast.makeText(CollectionsDefinitionActivity.this,
                    R.string.specific_collection_does_not_exist,
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (Const.IS_KINDLE) {
            AmznBaseMapFragment amznBaseMapFrag = new AmznBaseMapFragment();
            Bundle args = new Bundle();
            amznBaseMapFrag.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.activity_base_details_container, amznBaseMapFrag);
            transaction.commit();
        } else {
            AreaPickerMapFragment areaPickerMapFragment = new AreaPickerMapFragment();
            Bundle args = new Bundle();
            areaPickerMapFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.activity_base_details_container,
                    areaPickerMapFragment, AreaPickerMapFragment.class.getSimpleName());
            transaction
                    .addToBackStack(AreaPickerMapFragment.class.getSimpleName())
                    .commit();
        }
    }

    /**
     * for fragment to find out if activity is in two-pane mode
     */
    @Override
    public boolean isTwoPaneMode() {
        return TWO_PANEL_MODE;
    }

    @Override
    public void onMapFragmentBoundsChange(LatLngBoundsExt bounds) {
        callUpdateFirstDetailFrag(bounds);
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
        callUpdateFirstDetailFrag(bounds);
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
    public void onCollectionDetailsFragmentShowProducts(FedeoRequestParams fedeoRequestParams) {
        startSearchingProductsProcess(fedeoRequestParams);
    }

    @Override
    public void onCollectionDetailsFragmentShowMetadata(EntryISO displayedEntry) {
        loadIsoMetadataFragment(displayedEntry);
    }

    @Override
    public void onCollectionEmptyDetailsFragmentShowProducts(FedeoRequestParams fedeoRequestParams) {
        startSearchingProductsProcess(fedeoRequestParams);
    }

    @Override
    public void onCollectionEmptyDetailsFragmentShowCollections(FedeoRequestParams fedeoRequestParams) {
        startSearchingCollectionsProcess(fedeoRequestParams);
    }

    @Override
    public void onDatePickerFragmentDateChoose(Calendar calendar, String viewTag) {
        browseCollectionFirstDetailFragment.setDateValues(calendar, viewTag);
    }

    @Override
    public void onTimePickerFragmentTimeChoose(Calendar calendar, String viewTag) {
        browseCollectionFirstDetailFragment.setTimeValues(calendar, viewTag);
    }

    /**
     * Gets collections group list fragment.
     *
     * @return the collections group list fragment
     */
    public CollectionsGroupListFragment getCollectionsGroupListFragment() {
        return collectionsGroupListFragment;
    }

    private void callUpdateFirstDetailFrag(LatLngBoundsExt bounds) {
        BrowseCollectionFirstDetailFragment browseCollectionFirstDetailFragment = (BrowseCollectionFirstDetailFragment) getSupportFragmentManager()
                .findFragmentByTag(BrowseCollectionFirstDetailFragment.class.getSimpleName());
        if (browseCollectionFirstDetailFragment != null) {
            browseCollectionFirstDetailFragment.updateAreaBounds(bounds);
        }

        //TODO - Make it more universal and differentiate from which fragment it is called
        CollectionEmptyDetailsFragment collectionEmptyDetailsFragment = (CollectionEmptyDetailsFragment) getSupportFragmentManager()
                .findFragmentByTag(CollectionEmptyDetailsFragment.class.getSimpleName());
        if (collectionEmptyDetailsFragment != null) {
            collectionEmptyDetailsFragment.updateAreaBounds(bounds);
        }
    }

    private void changeAreaInputView(int areaType) {
        BrowseCollectionFirstDetailFragment browseCollectionFirstDetailFragment = (BrowseCollectionFirstDetailFragment) getSupportFragmentManager()
                .findFragmentByTag(BrowseCollectionFirstDetailFragment.class.getSimpleName());
        if (browseCollectionFirstDetailFragment != null) {
            browseCollectionFirstDetailFragment.loadProperAreaView(areaType);
        }

        //TODO - Make it more universal and differentiate from which fragment it is called
        CollectionEmptyDetailsFragment collectionEmptyDetailsFragment = (CollectionEmptyDetailsFragment) getSupportFragmentManager()
                .findFragmentByTag(CollectionEmptyDetailsFragment.class.getSimpleName());
        if (collectionEmptyDetailsFragment != null) {
            collectionEmptyDetailsFragment.loadProperAreaView(areaType);
        }
    }

    private void updatePointAndRadiusValues(LatLngExt center, float radius) {
        BrowseCollectionFirstDetailFragment browseCollectionFirstDetailFragment = (BrowseCollectionFirstDetailFragment) getSupportFragmentManager()
                .findFragmentByTag(BrowseCollectionFirstDetailFragment.class.getSimpleName());
        if (browseCollectionFirstDetailFragment != null) {
            browseCollectionFirstDetailFragment.updateAreaPtAndRadius(center, radius);
        }

        //TODO - Make it more universal and differentiate from which fragment it is called
        CollectionEmptyDetailsFragment collectionEmptyDetailsFragment = (CollectionEmptyDetailsFragment) getSupportFragmentManager()
                .findFragmentByTag(CollectionEmptyDetailsFragment.class.getSimpleName());
        if (collectionEmptyDetailsFragment != null) {
            collectionEmptyDetailsFragment.updateAreaPtAndRadius(center, radius);
        }
    }
}
