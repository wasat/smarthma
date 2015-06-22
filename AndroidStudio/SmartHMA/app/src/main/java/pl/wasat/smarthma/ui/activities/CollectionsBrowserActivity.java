package pl.wasat.smarthma.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.DataSeriesListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment.OnAmznAreaPickerMapFragmentListener;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.browse.CollectionsListFragment;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesListFragment;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesListFragment.OnDataSeriesListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment.OnCollectionDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MetadataISOFragment.OnMetadataISOFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment.OnSearchListFragmentListener;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;


public class CollectionsBrowserActivity extends BaseCollectionsActivity implements
        OnDataSeriesListFragmentListener,
        OnAreaPickerMapFragmentListener, OnAmznAreaPickerMapFragmentListener, OnSearchListFragmentListener,
        OnCollectionDetailsFragmentListener, OnMetadataISOFragmentListener {

    // private boolean mTwoPane;
    private EoDbAdapter dba;

    public CollectionsBrowserActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        String collectionName = intent
                .getStringExtra(CollectionsListFragment.KEY_COLLECTIONS_NAME);


        SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
        sharedPrefs.setParentIdPrefs(collectionName);
        sharedPrefs.setQueryPrefs("");

        FedeoRequestParams fedeoRequestParams = new FedeoRequestParams();
        fedeoRequestParams.buildFromShared(this);

        dba = new EoDbAdapter(this);

        if (findViewById(R.id.activity_base_details_container) != null) {
            DataSeriesListFragment dsListFragment = DataSeriesListFragment
                    .newInstance(fedeoRequestParams, stopNewSearch);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_base_list_container, dsListFragment)
                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        stopNewSearch = resultCode == RESULT_OK && data.getBooleanExtra(Const.KEY_INTENT_RETURN_STOP_SEARCH, true);

        DataSeriesListFragment dtSeriesListFragment = (DataSeriesListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container);
        if (dtSeriesListFragment != null) {
            Bundle bundle = dtSeriesListFragment.getArguments();
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
        FragmentManager fm = getSupportFragmentManager();
        int bsec = fm.getBackStackEntryCount();
        if (bsec > 1) {
            while (bsec > 1) {
                fm.popBackStackImmediate();
                bsec = fm.getBackStackEntryCount();
            }
        } else {
            finish();
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
        EntryISO selectedEntry = (EntryISO) ((DataSeriesListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter().getItem(Integer.parseInt(id));

        // mark metadata as read
        dba.openToWrite();
        dba.markAsRead(selectedEntry.getGuid());
        dba.close();
        selectedEntry.setRead(true);
        DataSeriesListAdapter adapter = (DataSeriesListAdapter) ((DataSeriesListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter();
        adapter.notifyDataSetChanged();

    }

    /*
     * (non-Javadoc)
     *
     * @see pl.wasat.smarthma.ui.fragments.DataSeriesListFragment.
     * OnDataSeriesListFragmentListener
     * #onDataSeriesFragmentItemSelected(java.lang.String)
     */
    @Override
    public void onDataSeriesFragmentItemSelected(String id) {

        FragmentManager fm = getSupportFragmentManager();
        DataSeriesListFragment dataseriesListFragment = (DataSeriesListFragment) fm
                .findFragmentById(R.id.activity_base_list_container);
        EntryISO selectedEntry = (EntryISO) dataseriesListFragment.getListAdapter()
                .getItem(Integer.parseInt(id));

        // mark metadata as read
        dba.openToWrite();
        dba.markAsRead(selectedEntry.getGuid());
        dba.close();
        selectedEntry.setRead(true);
        DataSeriesListAdapter adapter = (DataSeriesListAdapter) ((DataSeriesListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter();
        adapter.notifyDataSetChanged();

        CollectionDetailsFragment collectionDetailsFragment = CollectionDetailsFragment
                .newInstance(selectedEntry);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        collectionDetailsFragment, "CollectionDetailsFragment")
                .addToBackStack("CollectionDetailsFragment").commit();

    }

    @Override
    public void onMapFragmentBoundsChange(LatLngBoundsExt bounds) {
        callUpdateDetailFrag(bounds);
    }

    @Override
    public void onAmznMapFragmentBoundsChange(LatLngBoundsExt bounds) {
        callUpdateDetailFrag(bounds);
    }

    private void callUpdateDetailFrag(LatLngBoundsExt bounds) {
        CollectionDetailsFragment collectionDetailsFragment = (CollectionDetailsFragment) getSupportFragmentManager()
                .findFragmentByTag("CollectionDetailsFragment");

        if (collectionDetailsFragment != null) {
            collectionDetailsFragment.updateAreaBounds(bounds);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.wasat.smarthma.ui.frags.search.SearchResultCollectionDetailsFragment
     * .OnSearchResultCollectionDetailsFragmentListener
     * #onSearchResultCollectionDetailsFragmentShowProducts
     * (pl.wasat.smarthma.model.FedeoRequest)
     */
    @Override
    public void onCollectionDetailsFragmentShowProducts(FedeoRequestParams fedeoSearchProductsParams) {
        startSearchingProductsProcess(fedeoSearchProductsParams);
    }


    @Override
    public void onCollectionDetailsFragmentShowMetadata(EntryISO displayedEntry) {
        loadIsoMetadataFragment(displayedEntry);
    }


}
