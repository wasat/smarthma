package pl.wasat.smarthma.ui.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.SearchListAdapter;
import pl.wasat.smarthma.database.FavouritesDbAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment.OnAmznAreaPickerMapFragmentListener;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment.OnBaseShowProductsListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment.OnCollectionDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.common.DatePickerFragment.OnDatePickerFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MetadataISOFragment;
import pl.wasat.smarthma.ui.frags.common.TimePickerFragment.OnTimePickerFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchListFragmentBase;
import pl.wasat.smarthma.ui.frags.search.SearchListFragmentOffline;
import pl.wasat.smarthma.ui.menus.SearchOfflineCollectionsMenuHandler;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;

/**
 * Activity used to browse and manage saved collections.
 */
public class FavouriteCollectionsActivity extends BaseSmartHMActivity
        implements SearchListFragmentBase.OnSearchListFragmentListener,
        OnBaseShowProductsListFragmentListener,
        OnAreaPickerMapFragmentListener, OnAmznAreaPickerMapFragmentListener,
        OnCollectionDetailsFragmentListener, OnDatePickerFragmentListener, OnTimePickerFragmentListener {

    private FavouritesDbAdapter dba;
    private CollectionDetailsFragment collectionDetailsFragment;
    private SearchListFragmentOffline searchListFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        assert actionBar != null;

        TextView title = (TextView) findViewById(R.id.action_bar_title);
        title.setText(getString(R.string.activity_name_favourite_collections_results));

        //handleIntent(getIntent());
        dba = new FavouritesDbAdapter(this);

        commonMenuHandler = new SearchOfflineCollectionsMenuHandler(this, R.id.menu_button);

        /*
        searchListFrag = (SearchListFragmentOffline) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container);
        */

        Log.d("ZX", "Creating SearchListFragmentOffline.");
        SearchListFragmentOffline searchListFragment = SearchListFragmentOffline
                .newInstance(stopNewSearch);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container,
                        searchListFragment).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("ZX", "onActivityResult");
        stopNewSearch = resultCode == RESULT_OK && data.getBooleanExtra(Const.KEY_INTENT_RETURN_STOP_SEARCH, true);

        searchListFrag = (SearchListFragmentOffline) getSupportFragmentManager()
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
        if (dismissMenuOnBackPressed()) return;
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
        Log.d("ZX", "onSearchListFragmentItemSelected()");
        EntryISO selectedEntry = (EntryISO) ((SearchListFragmentOffline) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter().getItem(Integer.parseInt(id));

        if (selectedEntry.isNotRead()) {
            selectedEntry.setRead(true);

            dba.openToWrite();
            //dba.markAsRead(selectedEntry.getGuid());
            dba.replaceEntry(selectedEntry);
            dba.close();
        }

        SearchListAdapter adapter = (SearchListAdapter) ((SearchListFragmentOffline) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter();
        adapter.notifyDataSetChanged();

        collectionDetailsFragment = CollectionDetailsFragment
                .newInstance(selectedEntry);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        collectionDetailsFragment,
                        "CollectionDetailsFragment")
                .addToBackStack("CollectionDetailsFragment")
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
        CollectionDetailsFragment collectionDetailsFragment = (CollectionDetailsFragment) getSupportFragmentManager()
                .findFragmentByTag("CollectionDetailsFragment");
        if (collectionDetailsFragment != null) {
            collectionDetailsFragment.updateAreaBounds(bounds);
        }
    }


    /*
     * (non-Javadoc)
     *
     * @see pl.wasat.smarthma.ui.frags.search.SearchProductsListFragment.
     * OnSearchProductsListFragmentListener
     * #onSearchProductsListFragmentFootprintSend(java.util.ArrayList)
     */
    @Override
    public void onBaseShowProductsListFragmentFootprintSend() {
    }

    @Override
    public void onCollectionDetailsFragmentShowProducts(FedeoRequestParams fedeoSearchProductsParams) {
        Intent showProductsIntent = new Intent(this,
                ProductsBrowserActivity.class);
        showProductsIntent.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS, fedeoSearchProductsParams);
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
    public void onDatePickerFragmentDateChoose(Calendar calendar, String viewTag) {
        collectionDetailsFragment.setDateValues(calendar, viewTag);
    }

    @Override
    public void onTimePickerFragmentTimeChoose(Calendar calendar, String viewTag) {
        collectionDetailsFragment.setTimeValues(calendar, viewTag);
    }

    /**
     * Returns the list fragment associated with this object.
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
}