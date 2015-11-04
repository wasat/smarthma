package pl.wasat.smarthma.ui.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

/**
 * Activity used to browse and manage saved collections.
 */
public class FavouriteCollectionsActivity extends ExtendedBaseCollectionsActivity {

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

    public void clearList()
    {
        dba.openToWrite();
        dba.clearCollections();
        dba.close();
        getListFragment().clearEntries();
        refreshList();
    }
}
