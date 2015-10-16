package pl.wasat.smarthma.ui.activities;

import android.app.ActionBar;
import android.app.SearchManager;
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
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment.OnBaseShowProductsListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment.OnCollectionDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.common.DatePickerFragment.OnDatePickerFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MetadataISOFragment;
import pl.wasat.smarthma.ui.frags.common.TimePickerFragment.OnTimePickerFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchListFragmentOffline;
import pl.wasat.smarthma.ui.frags.search.SearchListFragmentOffline.OnSearchListFragmentListener;
import pl.wasat.smarthma.ui.menus.MenuHandler;
import pl.wasat.smarthma.ui.menus.SearchOfflineCollectionsMenuHandler;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;

public class FavouriteCollectionsActivity extends BaseSmartHMActivity
        implements OnSearchListFragmentListener,
        OnBaseShowProductsListFragmentListener,
        OnAreaPickerMapFragmentListener, OnAmznAreaPickerMapFragmentListener,
        OnCollectionDetailsFragmentListener, OnDatePickerFragmentListener, OnTimePickerFragmentListener {

    private FavouritesDbAdapter dba;
    private CollectionDetailsFragment collectionDetailsFragment;
    private MenuHandler menuHandler;
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

        menuHandler = new SearchOfflineCollectionsMenuHandler(this, R.id.menu_button);

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

    /**
     * Handling intent data
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);

            SharedPrefs sharedPrefs = new SharedPrefs(this);
            sharedPrefs.setQueryPrefs(query);

            FedeoRequestParams fedeoRequestParams = new FedeoRequestParams();
            fedeoRequestParams.setQuery(query);

            SearchListFragmentOffline searchListFragment = SearchListFragmentOffline
                    .newInstance(stopNewSearch);
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

            FedeoRequestParams fedeoRequestParams = new FedeoRequestParams();

            SearchListFragmentOffline searchListFragment = SearchListFragmentOffline
                    .newInstance(stopNewSearch);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_base_list_container,
                            searchListFragment).commit();
        }
        if (intent.getAction().equals(Const.KEY_ACTION_SEARCH_COLLECTIONS)) {
            FedeoRequestParams fedeoRequestParams = (FedeoRequestParams) intent.getSerializableExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS);

            SearchListFragmentOffline searchListFragment = SearchListFragmentOffline
                    .newInstance(stopNewSearch);
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
        try {
            Log.d("ZX", "SearchCollectionResultsActivity onBackPressed");

            if (menuHandler.isPopupWindowVisible()) {
                menuHandler.dismissPopupWindow();
                return;
            }

            if (dismissMenuOnBackPressed()) return;

            Log.d("ZX", "1");
            FragmentManager fm = getSupportFragmentManager();
            Log.d("ZX", "2");
            int bsec = fm.getBackStackEntryCount();
            Log.d("ZX", "3");
            String bstEntry = fm.getBackStackEntryAt(bsec - 1).getName();
            Log.d("ZX", "4");
            if (bsec > 1) {
                Log.d("ZX", "41");
                while (bsec > 1) {
                    fm.popBackStackImmediate();
                    bsec = fm.getBackStackEntryCount();
                }
            } else {
                Log.d("ZX", "42");
                finish();
                Log.d("ZX", "43");
                if (bstEntry.equalsIgnoreCase("FeedSummarySearchFragment")) {
                    Log.d("ZX", "431");
                    Intent intent = new Intent(this, SearchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Log.d("ZX", "432");
                    super.onBackPressed();
                }
            }
            Log.d("ZX", "5");
        } catch (Exception e) {
            System.out.println(e);
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
/*        if (Const.IS_KINDLE) {
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
        }*/
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

    public SearchListFragmentOffline getListFragment() {
        return ((SearchListFragmentOffline) getSupportFragmentManager().findFragmentById(R.id.activity_base_list_container));
    }

    public void refreshList() {
        SearchListAdapter adapter = (SearchListAdapter) ((SearchListFragmentOffline) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container))
                .getListAdapter();
        adapter.notifyDataSetChanged();
    }
}