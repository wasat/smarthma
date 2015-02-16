package pl.wasat.smarthma.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.model.LatLngBounds;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchBasicInfoRightFragment;
import pl.wasat.smarthma.ui.frags.search.SearchBasicInfoRightFragment.OnSearchBasicInfoRightFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchFragment;
import pl.wasat.smarthma.ui.frags.search.SearchFragment.OnSearchFragmentListener;
import roboguice.util.temp.Ln;

public class SearchActivity extends BaseSmartHMActivity implements
        OnSearchBasicInfoRightFragmentListener, OnSearchFragmentListener,
        OnAreaPickerMapFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Ln.getConfig().setLoggingLevel(Log.ERROR);
        super.onCreate(savedInstanceState);

        if (findViewById(R.id.activity_base_list_container) != null) {

            loadRightPanel();
            loadLeftPanel();

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_eo_map_twopane, menu);

        // Associate searchable configuration with the SearchView
/*		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));*/

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
/*		case R.id.action_pref1:
            break;
		case R.id.action_pref2:
			break;
		case R.id.action_pref3:
			break;
		case R.id.action_clear_all_settings:
			break;*/
            case R.id.action_exit:
                moveTaskToBack(true);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.app.FragmentActivity#onBackPressed()
     */
    @Override
    public void onBackPressed() {
        Log.i("BACK", "Search back pressed");
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

    /**
     *
     */
    private void loadRightPanel() {
        SearchBasicInfoRightFragment rightInfoFragment = SearchBasicInfoRightFragment
                .newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container, rightInfoFragment,
                        "SearchBasicInfoRightFragment").commit();
    }

    private void loadLeftPanel() {
        SearchFragment searchLeftFragment = SearchFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        searchLeftFragment, "SearchFragment")
                .addToBackStack("SearchFragment").commit();
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.wasat.smarthma.ui.frags.search.SearchBasicInfoRightFragment.
     * OnSearchBasicInfoRightFragmentListener
     * #onSearchBasicInfoRightFragmentInteraction(android.net.Uri)
     */
    @Override
    public void onSearchBasicInfoRightFragmentInteraction(Uri uri) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.wasat.smarthma.ui.frags.search.SearchFragment.OnSearchFragmentListener
     * #onSearchFragmentInteraction(android.net.Uri)
     */
    @Override
    public void onSearchFragmentInteraction(Uri uri) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.wasat.smarthma.ui.frags.MapFragment.OnMapFragmentListener
     * #onMapFragmentBoundsChange
     * (com.google.android.gms.maps.model.LatLngBounds)
     */
    @Override
    public void onMapFragmentBoundsChange(LatLngBounds bounds) {

        SearchBasicInfoRightFragment searchBasicInfoRightFragment = (SearchBasicInfoRightFragment) getSupportFragmentManager()
                .findFragmentByTag("SearchBasicInfoRightFragment");

        if (searchBasicInfoRightFragment != null) {
            // If article frag is available, we're in two-pane layout...
            // Call a method in the ArticleFragment to update its content
            searchBasicInfoRightFragment.updateCollectionsAreaBounds(bounds);
        }
    }


}
