package pl.wasat.smarthma.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;

import java.util.Calendar;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.SearchHistory;
import pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment.OnAmznAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.base.BaseSearchSideParametersFragment;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.DatePickerFragment.OnDatePickerFragmentListener;
import pl.wasat.smarthma.ui.frags.common.TimePickerFragment.OnTimePickerFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchAdvancedParametersFragment;
import pl.wasat.smarthma.ui.frags.search.SearchAdvancedParametersFragment.OnSearchAdvancedParametersFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchBasicParametersFragment;
import pl.wasat.smarthma.ui.frags.search.SearchFragment;
import pl.wasat.smarthma.ui.frags.search.SearchFragment.OnSearchFragmentListener;
import pl.wasat.smarthma.ui.menus.MenuHandler;
import pl.wasat.smarthma.ui.menus.SearchMenuHandler;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import roboguice.util.temp.Ln;

public class SearchActivity extends BaseSmartHMActivity implements
        OnSearchAdvancedParametersFragmentListener, OnSearchFragmentListener, OnDatePickerFragmentListener, OnTimePickerFragmentListener,
        OnAreaPickerMapFragmentListener, OnAmznAreaPickerMapFragmentListener {

    private BaseSearchSideParametersFragment sideParamsPanel;
    private SearchFragment searchMainPanel;
    private MenuHandler menuHandler;
    private static final int MENU_QUERY_IDS = 1000;
    private static final int MENU_CATALOGUE_IDS = 1100;
    private static final int MENU_BBOX_IDS = 1200;
    private static final int MENU_STARTDATE_IDS = 1300;
    private static final int MENU_ENDDATE_IDS = 1400;
    private static final int MENU_CLEAR_ID = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Ln.getConfig().setLoggingLevel(Log.ERROR);
        super.onCreate(savedInstanceState);

        if (findViewById(R.id.activity_base_list_container) != null) {
            loadBasicParamsFragment();
            loadMainSearchPanel();
        }

        menuHandler = new SearchMenuHandler(this, R.id.menu_button);

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

        //menuHandler.loadMenuView();
        SearchHistory searchHistory = new SearchHistory(this);

        SubMenu searchMenu = menu.getItem(0).getSubMenu();
        searchHistory.createSearchMenu(searchMenu, MENU_QUERY_IDS, MENU_CATALOGUE_IDS, MENU_BBOX_IDS, MENU_STARTDATE_IDS, MENU_ENDDATE_IDS, MENU_CLEAR_ID);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.REQUEST_CODE_GLOBAL_SETTINGS) {
            //sideParamsPanel.checkDeviceAndLoadMapPicker(R.id.activity_base_details_container);
        }
    }*/

    public void setQuery(String query) {
        if (searchMainPanel != null) {
            searchMainPanel.setQuery(query);
        }
    }

    public void setCatalogue(String catalogue) {
        if (sideParamsPanel != null) {
            sideParamsPanel.setCatalogue(catalogue);
        }
    }

    public void setBbox(String boundingBox) {
        if (sideParamsPanel != null) {
            sideParamsPanel.setBounds(boundingBox);
        }
    }

    public void setStartDate(String startDate) {
        if (sideParamsPanel != null) {
            sideParamsPanel.setStartCalendar(startDate);
        }
    }

    public void setEndDate(String endDate) {
        if (sideParamsPanel != null) {
            sideParamsPanel.setEndCalendar(endDate);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.app.FragmentActivity#onBackPressed()
     */
    @Override
    public void onBackPressed() {
        if (menuHandler.isPopupWindowVisible()) {
            menuHandler.dismissPopupWindow();
            return;
        }

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

    private void loadMainSearchPanel() {
        SearchFragment searchLeftFragment = SearchFragment.newInstance();
        searchMainPanel = searchLeftFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        searchLeftFragment, "SearchFragment")
                .addToBackStack("SearchFragment").commit();
    }

    /**
     *
     */
    private void loadBasicParamsFragment() {
        SearchBasicParametersFragment searchBasicParametersFragment = SearchBasicParametersFragment
                .newInstance();
        sideParamsPanel = searchBasicParametersFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container, searchBasicParametersFragment,
                        "SearchBasicParametersFragment").commit();
    }

    private void loadAdvancedParamsFragment() {
        SearchAdvancedParametersFragment searchAdvancedParametersFragment = SearchAdvancedParametersFragment
                .newInstance();
        sideParamsPanel = searchAdvancedParametersFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container, searchAdvancedParametersFragment,
                        "SearchAdvancedParametersFragment").commit();
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

    @Override
    public void onAmznMapFragmentBoundsChange(LatLngBoundsExt bounds) {
        callUpdateCollectionsBounds(bounds);
    }

    private void callUpdateCollectionsBounds(LatLngBoundsExt bounds) {
        //    SearchBasicParametersFragment searchBasicParametersFragment = (SearchBasicParametersFragment) getSupportFragmentManager()
        //           .findFragmentByTag("SearchBasicParametersFragment");
        if (sideParamsPanel != null) {
            sideParamsPanel.updateAreaBounds(bounds);
        }
    }


    @Override
    public void onSearchAdvancedParamsFragmentEditTextChange(String parameterKey, String parameterValue) {
        searchMainPanel.setAdditionalParams(parameterKey, parameterValue);
    }

    @Override
    public void onDatePickerFragmentDateChoose(Calendar calendar, String viewTag) {
        sideParamsPanel.setDateValues(calendar, viewTag);
    }

    @Override
    public void onTimePickerFragmentTimeChoose(Calendar calendar, String viewTag) {
        sideParamsPanel.setTimeValues(calendar, viewTag);
    }

    @Override
    public void onSearchFragmentBasicParamsChoose() {
        loadBasicParamsFragment();
    }

    @Override
    public void onSearchFragmentAdvanceParamsChoose() {
        loadAdvancedParamsFragment();
    }

    @Override
    public void onSearchFragmentStartSearchingWithButton(Intent searchIntent) {
        startActivityForResult(searchIntent, REQUEST_NEW_SEARCH);
    }


    public MenuHandler getMenuHandler() {
        return menuHandler;
    }
}
