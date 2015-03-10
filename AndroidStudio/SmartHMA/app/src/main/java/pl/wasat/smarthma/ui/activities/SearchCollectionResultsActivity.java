package pl.wasat.smarthma.ui.activities;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.SearchListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment.OnAmznAreaPickerMapFragmentListener;
import pl.wasat.smarthma.kindle.AmznExtendedMapFragment;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.model.om.Footprint;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment.OnBaseShowProductsListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment.OnCollectionDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.common.ExtendedMapFragment;
import pl.wasat.smarthma.ui.frags.common.MetadataISOFragment;
import pl.wasat.smarthma.ui.frags.common.MetadataISOFragment.OnMetadataISOFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment.OnSearchListFragmentListener;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;

public class SearchCollectionResultsActivity extends BaseSmartHMActivity
        implements OnSearchListFragmentListener,
        OnBaseShowProductsListFragmentListener,
        OnAreaPickerMapFragmentListener, OnAmznAreaPickerMapFragmentListener, OnMetadataISOFragmentListener,
        OnCollectionDetailsFragmentListener {

    private EoDbAdapter dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the action bar
        ActionBar actionBar = getActionBar();

        // Enabling Back navigation on Action Bar icon
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        handleIntent(getIntent());
        dba = new EoDbAdapter(this);
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

    /**
     * Handling intent data
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            /**
             * Use this query to display search results like 1. Getting the data
             * from SQLite and showing in listview 2. Making webrequest and
             * displaying the data For now we just display the query only
             */

            SharedPrefs sharedPrefs = new SharedPrefs(this);
            sharedPrefs.setQueryPrefs(query);

            FedeoRequest fedeoRequest = new FedeoRequest();
            fedeoRequest.buildFromShared(this);

            SearchListFragment searchListFragment = SearchListFragment
                    .newInstance(fedeoRequest, stopNewSearch);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_base_list_container,
                            searchListFragment).commit();
        }
        if (intent.getAction().equals(Const.KEY_ACTION_SEARCH_MISSION_DATA)) {
            String query = intent.getStringExtra(Const.KEY_INTENT_QUERY);
            SharedPrefs sharedPrefs = new SharedPrefs(this);
            sharedPrefs.setParentIdPrefs("EOP:ESA:FEDEO");
            sharedPrefs.setQueryPrefs(query);

            FedeoRequest fedeoRequest = new FedeoRequest();
            fedeoRequest.buildFromShared(this);

            SearchListFragment searchListFragment = SearchListFragment
                    .newInstance(fedeoRequest, stopNewSearch);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_base_list_container,
                            searchListFragment).commit();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.app.FragmentActivity#onBackPressed()
     */
    @Override
    public void onBackPressed() {
        //Log.d("ZX", "onBackPressed()");
        FragmentManager fm = getSupportFragmentManager();
        int bsec = fm.getBackStackEntryCount();
        String bstEntry = fm.getBackStackEntryAt(bsec - 1).getName();
        //Log.d("ZX", bstEntry);
        if (bsec > 1) {
            while (bsec > 1) {
                fm.popBackStackImmediate();
                bsec = fm.getBackStackEntryCount();
            }
        } else {
            finish();
            if (bstEntry.equalsIgnoreCase("FeedSummarySearchFragment")) {
                // Restart SearchActivity.
                Intent intent = new Intent(this, SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                super.onBackPressed();
            }
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

        // mark metadata as read
        dba.openToWrite();
        dba.markAsRead(selectedEntry.getGuid());
        dba.close();
        selectedEntry.setRead(true);
        SearchListAdapter adapter = (SearchListAdapter) ((SearchListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter();
        adapter.notifyDataSetChanged();

        CollectionDetailsFragment searchResultCollectionDetailsFragment = CollectionDetailsFragment
                .newInstance(selectedEntry);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        searchResultCollectionDetailsFragment,
                        "SearchResultCollectionDetailsFragment")
                .addToBackStack("SearchResultCollectionDetailsFragment")
                .commit();

    }

    @Override
    public void onMapFragmentBoundsChange(LatLngBoundsExt bounds) {

        callUpdateCollectionsBounds(bounds);

    }

    @Override
    public void onAmznMapFragmentBoundsChange(LatLngBoundsExt bounds) {
        callUpdateCollectionsBounds(bounds);
    }

    private void callUpdateCollectionsBounds(LatLngBoundsExt bounds) {
        CollectionDetailsFragment searchResultCollectionDetailsFragment = (CollectionDetailsFragment) getSupportFragmentManager()
                .findFragmentByTag("SearchResultCollectionDetailsFragment");
        if (searchResultCollectionDetailsFragment != null) {
            searchResultCollectionDetailsFragment.updateAreaBounds(bounds);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.wasat.smarthma.ui.fragments.SearchProductsFeedsFragment.
     * OnSearchProductsFeedFragmentListener
     * #onSearchProductsFeedFragmentItemSelected(java.lang.String)
     */
    @Override
    public void onBaseShowProductsListFragmentItemSelected(String id) {
    }


    /*
     * (non-Javadoc)
     *
     * @see pl.wasat.smarthma.ui.frags.search.SearchProductsListFragment.
     * OnSearchProductsListFragmentListener
     * #onSearchProductsListFragmentFootprintSend(java.util.ArrayList)
     */
    @Override
    public void onBaseShowProductsListFragmentFootprintSend(
            ArrayList<Footprint> footPrints) {
        if (Const.IS_KINDLE) {
            AmznExtendedMapFragment extendedMapFragment = (AmznExtendedMapFragment) getSupportFragmentManager()
                    .findFragmentByTag("ExtendedMapFragment");
            if (extendedMapFragment != null) {
                extendedMapFragment.showFootPrints(null);
            }
        } else {
            ExtendedMapFragment extendedMapFragment = (ExtendedMapFragment) getSupportFragmentManager()
                    .findFragmentByTag("ExtendedMapFragment");
            if (extendedMapFragment != null) {
                extendedMapFragment.showFootPrints(null);
            }
        }


    }

    @Override
    public void onCollectionDetailsFragmentShowProducts(String parentID) {
        Intent showProductsIntent = new Intent(this,
                ProductsBrowserActivity.class);
        showProductsIntent.putExtra(Const.KEY_INTENT_PARENT_ID, parentID);
        startActivityForResult(showProductsIntent, REQUEST_NEW_SEARCH);
    }

    @Override
    public void onCollectionDetailsFragmentShowMetadata(EntryISO displayedEntry) {
        MetadataISOFragment metadataISOFragment = MetadataISOFragment
                .newInstance(displayedEntry);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        metadataISOFragment, "MetadataISOFragment")
                .addToBackStack("MetadataISOFragment").commit();

    }

    @Override
    public void onMetadataISOFragmentInteraction() {

    }


}