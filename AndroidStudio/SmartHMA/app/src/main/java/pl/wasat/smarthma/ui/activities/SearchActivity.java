package pl.wasat.smarthma.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.SearchHistory;
import pl.wasat.smarthma.database.SearchParams;
=======
import pl.wasat.smarthma.R;
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
import pl.wasat.smarthma.R;
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
import pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment.OnAmznAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchBasicInfoRightFragment;
import pl.wasat.smarthma.ui.frags.search.SearchBasicInfoRightFragment.OnSearchBasicInfoRightFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchFragment;
import pl.wasat.smarthma.ui.frags.search.SearchFragment.OnSearchFragmentListener;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import roboguice.util.temp.Ln;

public class SearchActivity extends BaseSmartHMActivity implements
        OnSearchBasicInfoRightFragmentListener, OnSearchFragmentListener,
        OnAreaPickerMapFragmentListener, OnAmznAreaPickerMapFragmentListener {

    private SearchBasicInfoRightFragment rightPanel;
    private SearchFragment leftPanel;
<<<<<<< HEAD
<<<<<<< HEAD
    private static final int MENU_QUERY_IDS = 1000;
    private static final int MENU_CATALOGUE_IDS = 1100;
    private static final int MENU_BBOX_IDS = 1200;
    private static final int MENU_STARTDATE_IDS = 1300;
    private static final int MENU_ENDDATE_IDS = 1400;
    private static final int MENU_CLEAR_ID = 2000;
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Ln.getConfig().setLoggingLevel(Log.ERROR);
        super.onCreate(savedInstanceState);

        if (findViewById(R.id.activity_base_list_container) != null) {

            loadRightPanel();
            loadLeftPanel();
<<<<<<< HEAD
<<<<<<< HEAD
=======
            leftPanel.setRightPanel(rightPanel);
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
            leftPanel.setRightPanel(rightPanel);
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
        }

        //refreshParameters();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_eo_map_twopane, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        SearchHistory searchHistory = new SearchHistory(this);
        //ArrayList<SearchParams> searchHistoryList = searchHistory.getSearchHistoryList(true);
        ArrayList<String> queries = searchHistory.getQueries(true);
        ArrayList<String> catalogues = searchHistory.getCatalogues(true);
        ArrayList<String> bboxs = searchHistory.getBboxs(true);
        ArrayList<String> startDates = searchHistory.getStartDates(true);
        ArrayList<String> endDates = searchHistory.getEndDates(true);

        //Log.d("ZX", "item: "+menu.getItem(0).getSubMenu().getItem(0).getTitle());
        SubMenu searchMenu = menu.getItem(0).getSubMenu();
        searchMenu.clear();

        SubMenu pickQueriesMenu = searchMenu.addSubMenu(getString(R.string.search_history_pick_query));
        for (String str : queries) {
            pickQueriesMenu.add(MENU_QUERY_IDS, Menu.NONE, Menu.NONE, str);
        }

        SubMenu pickCataloguesMenu = searchMenu.addSubMenu(getString(R.string.search_history_pick_catalogue));
        for (String str : catalogues) {
            pickCataloguesMenu.add(MENU_CATALOGUE_IDS, Menu.NONE, Menu.NONE, str);
        }

        SubMenu pickBboxsMenu = searchMenu.addSubMenu(getString(R.string.search_history_pick_bbox));
        for (String str : bboxs) {
            pickBboxsMenu.add(MENU_BBOX_IDS, Menu.NONE, Menu.NONE, str);
        }

        SubMenu pickStartDatesMenu = searchMenu.addSubMenu(getString(R.string.search_history_pick_start_date));
        for (String str : startDates) {
            pickStartDatesMenu.add(MENU_STARTDATE_IDS, Menu.NONE, Menu.NONE, str);
        }

        SubMenu pickEndDatesMenu = searchMenu.addSubMenu(getString(R.string.search_history_pick_end_date));
        for (String str : endDates) {
            pickEndDatesMenu.add(MENU_ENDDATE_IDS, Menu.NONE, Menu.NONE, str);
        }

        MenuItem clearItem = searchMenu.add(Menu.NONE, MENU_CLEAR_ID, Menu.NONE, getString(R.string.search_history_clear));

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        String test = item.getMenuInfo().toString();
        if (test != null)
        {
            Log.d("ZX", test);
        }
        */
        String value;
        switch (item.getItemId()) {
            case MENU_CLEAR_ID:
                SearchHistory searchHistory = new SearchHistory(this);
                searchHistory.clearHistory();
                break;
            case R.id.action_exit:
                moveTaskToBack(true);
                finish();
                break;
            default:
                switch (item.getGroupId()) {
                    case MENU_QUERY_IDS:
                        value = item.getTitle().toString();
                        setQuery(value);
                        break;
                    case MENU_CATALOGUE_IDS:
                        value = item.getTitle().toString();
                        setCatalogue(value);
                        break;
                    case MENU_BBOX_IDS:
                        value = item.getTitle().toString();
                        setBbox(value);
                        break;
                    case MENU_STARTDATE_IDS:
                        value = item.getTitle().toString();
                        setStartDate(value);
                        break;
                    case MENU_ENDDATE_IDS:
                        value = item.getTitle().toString();
                        setEndDate(value);
                        break;
                    default:
                        return super.onOptionsItemSelected(item);
                }
        }
        return true;
    }

    public void refreshParameters() {
        try {
            SearchHistory searchHistory = new SearchHistory(this);
            SearchParams searchParams = searchHistory.getSearchHistoryList(true).get(0);
            if (searchParams != null) {
                setQuery(searchParams.getSearchPhrase());
                setCatalogue(searchParams.getCatalogue());
                setBbox(searchParams.getBbox());
                setStartDate(searchParams.getStartDate());
                setEndDate(searchParams.getEndDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setQuery(String query) {
        if (leftPanel != null) {
            leftPanel.setQuery(query);
        }
    }

    private void setCatalogue(String catalogue) {
        if (rightPanel != null) {
            rightPanel.setCatalogue(catalogue);
        }
    }

    private void setBbox(String boundingBox) {
        if (rightPanel != null) {
            rightPanel.setBbox(boundingBox);
        }
    }

    private void setStartDate(String startDate) {
        if (rightPanel != null) {
            rightPanel.setStartDate(startDate);
        }
    }

    private void setEndDate(String endDate) {
        if (rightPanel != null) {
            rightPanel.setEndDate(endDate);
        }
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
        rightPanel = rightInfoFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container, rightInfoFragment,
                        "SearchBasicInfoRightFragment").commit();
    }

    private void loadLeftPanel() {
        SearchFragment searchLeftFragment = SearchFragment.newInstance();
        leftPanel = searchLeftFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        searchLeftFragment, "SearchFragment")
                .addToBackStack("SearchFragment").commit();
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
    public void onMapFragmentBoundsChange(LatLngBoundsExt bounds) {
        callUpdateCollectionsBounds(bounds);
    }
<<<<<<< HEAD

    @Override
    public void onAmznMapFragmentBoundsChange(LatLngBoundsExt bounds) {
        callUpdateCollectionsBounds(bounds);
    }

=======

    @Override
<<<<<<< HEAD
=======
    public void onMapFragmentBoundsChange(LatLngBoundsExt bounds) {
        callUpdateCollectionsBounds(bounds);
    }

    @Override
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    public void onAmznMapFragmentBoundsChange(LatLngBoundsExt bounds) {
        callUpdateCollectionsBounds(bounds);
    }

<<<<<<< HEAD
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    private void callUpdateCollectionsBounds(LatLngBoundsExt bounds) {
        SearchBasicInfoRightFragment searchBasicInfoRightFragment = (SearchBasicInfoRightFragment) getSupportFragmentManager()
                .findFragmentByTag("SearchBasicInfoRightFragment");
        if (searchBasicInfoRightFragment != null) {
            searchBasicInfoRightFragment.updateCollectionsAreaBounds(bounds);
        }
    }


}
