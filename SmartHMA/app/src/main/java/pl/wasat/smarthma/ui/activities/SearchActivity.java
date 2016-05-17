package pl.wasat.smarthma.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.api.client.http.GenericUrl;

import java.util.Calendar;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.SearchHistory;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment.OnAmznAreaPickerMapFragmentListener;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.ui.activities.base.BaseSmartHMActivity;
import pl.wasat.smarthma.ui.frags.base.BaseSearchSideParametersFragment;
import pl.wasat.smarthma.ui.frags.base.BaseSearchSideParametersFragment.OnBaseSearchSideParametersFragmentListener;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.DatePickerFragment.OnDatePickerFragmentListener;
import pl.wasat.smarthma.ui.frags.common.TimePickerFragment.OnTimePickerFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchAdvancedParametersFragment;
import pl.wasat.smarthma.ui.frags.search.SearchBasicParametersFragment;
import pl.wasat.smarthma.ui.frags.search.SearchFragment;
import pl.wasat.smarthma.ui.frags.search.SearchFragment.OnSearchFragmentListener;
import pl.wasat.smarthma.ui.menus.MenuHandler;
import pl.wasat.smarthma.ui.menus.SearchMenuHandler;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.obj.LatLngExt;
import roboguice.util.temp.Ln;

public class SearchActivity extends BaseSmartHMActivity implements OnBaseSearchSideParametersFragmentListener,
        OnSearchFragmentListener, OnDatePickerFragmentListener, OnTimePickerFragmentListener,
        OnAreaPickerMapFragmentListener, OnAmznAreaPickerMapFragmentListener, SearchAdvancedParametersFragment.EndpointsListDialogFragment.OnEndpointsListDialogFragListener {

    private static final int MENU_QUERY_IDS = 1000;
    private static final int MENU_CATALOGUE_IDS = 1100;
    private static final int MENU_BBOX_IDS = 1200;
    private static final int MENU_STARTDATE_IDS = 1300;
    private static final int MENU_ENDDATE_IDS = 1400;
    private static final int MENU_CLEAR_ID = 2000;
    private BaseSearchSideParametersFragment sideParamsPanel;
    private SearchFragment searchMainPanel;
    private FedeoRequestParams fedeoRequestParams;

    @Override
    public void startActivity(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            intent.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS_OSDD, fedeoRequestParams);
        }
        super.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Ln.getConfig().setLoggingLevel(Log.ERROR);
        super.onCreate(savedInstanceState);

        if (findViewById(R.id.activity_base_list_container) != null) {
            loadBasicParamsFragment();
            loadMainSearchPanel();
        }
        commonMenuHandler = new SearchMenuHandler(this, R.id.menu_button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_eo_map_twopane, menu);
        return super.onCreateOptionsMenu(menu);
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
                        SearchBasicParametersFragment.class.getSimpleName())
                .commit();
    }

    private void loadMainSearchPanel() {
        SearchFragment searchLeftFragment = SearchFragment.newInstance();
        searchMainPanel = searchLeftFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        searchLeftFragment, SearchFragment.class.getSimpleName())
                .addToBackStack(SearchFragment.class.getSimpleName())
                .commit();
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        SearchHistory searchHistory = new SearchHistory(this);

        SubMenu searchMenu = menu.getItem(0).getSubMenu();
        searchHistory.createSearchMenu(searchMenu, MENU_QUERY_IDS, MENU_CATALOGUE_IDS, MENU_BBOX_IDS, MENU_STARTDATE_IDS, MENU_ENDDATE_IDS, MENU_CLEAR_ID);

        return super.onPrepareOptionsMenu(menu);
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

    private void updatePointAndRadiusValues(LatLngExt center, float radius) {
        if (sideParamsPanel != null) {
            sideParamsPanel.updateAreaPtAndRadius(center, radius);
        }
    }

    private void callUpdateCollectionsBounds(LatLngBoundsExt bounds) {
        if (sideParamsPanel != null) {
            sideParamsPanel.updateAreaBounds(bounds);
        }
    }

    private void changeAreaInputView(int areaType) {
        if (sideParamsPanel != null) {
            sideParamsPanel.loadProperAreaView(areaType);
        }
    }


    @Override
    public void onBaseSearchSideParametersFragmentFedeoRequestParamsOsddChange(FedeoRequestParams fedeoRequestParams) {
        searchMainPanel.setFedeoRequestParams(fedeoRequestParams);
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

    private void loadAdvancedParamsFragment() {
        SearchAdvancedParametersFragment searchAdvancedParametersFragment = SearchAdvancedParametersFragment
                .newInstance();
        sideParamsPanel = searchAdvancedParametersFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container, searchAdvancedParametersFragment,
                        SearchAdvancedParametersFragment.class.getSimpleName()).commit();
    }

    @Override
    public void onSearchFragmentStartSearchingWithButton(Intent searchIntent) {
        startActivityForResult(searchIntent, REQUEST_NEW_SEARCH);
    }

    @Override
    public void onSearchFragmentSendFedeoParams(FedeoRequestParams fedeoRequestParams) {
        this.fedeoRequestParams = fedeoRequestParams;
    }

    @Override
    public void OnEndpointsListDialogFragClose() {
        sideParamsPanel.startAsyncLoadOsddData(new GenericUrl(Const.OSDD_BASE_URL));
    }

    public MenuHandler getMenuHandler() {
        return commonMenuHandler;
    }
}
