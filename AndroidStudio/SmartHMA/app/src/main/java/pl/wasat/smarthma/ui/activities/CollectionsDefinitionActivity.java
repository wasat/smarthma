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
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLngBounds;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.interfaces.OnCollectionsListSelectionListener;
import pl.wasat.smarthma.ui.frags.base.BaseMapFragment;
import pl.wasat.smarthma.ui.frags.browse.BrowseCollectionFirstDetailFragment;
import pl.wasat.smarthma.ui.frags.browse.CollectionsGroupListFragment;
import pl.wasat.smarthma.ui.frags.browse.CollectionsListFragment.OnCollectionsListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener;
import roboguice.util.temp.Ln;

public class CollectionsDefinitionActivity extends BaseSmartHMActivity
        implements OnCollectionsListSelectionListener,
        OnCollectionsListFragmentListener, OnAreaPickerMapFragmentListener {

    //private ProgressDialog initSpinner;
    //private ProgressBar progressBarWmsLoad;
    //private InitialisationReceiver initReceiver;
    //private SpinnerStateReceiver spinnerStateRec;
    //private boolean isWmsLoading = false;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private static boolean TWO_PANEL_MODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Ln.getConfig().setLoggingLevel(Log.ERROR);
        super.onCreate(savedInstanceState);

        // ViewGroup topLayout = (ViewGroup) findViewById(R.id.left_panel_map);
        // topLayout.requestTransparentRegion(topLayout);

        //progressBarWmsLoad = (ProgressBar) findViewById(R.id.progressBarWmsLoad);

        if (findViewById(R.id.activity_base_details_container) != null) {
            TWO_PANEL_MODE = true;
            loadRightListPanel();
            loadMapWithBasicSettingsView();
        }
    }

    @Override
    protected void onResume() {
/*		initReceiver = new InitialisationReceiver();
        registerReceiver(initReceiver, new IntentFilter(
				Const.KEY_SERVICE_INTENTFILTER_NOTIFICATION));

		spinnerStateRec = new SpinnerStateReceiver();
		registerReceiver(spinnerStateRec, new IntentFilter(
				Const.KEY_MAP_SPINNER_INTENTFILTER_NOTIFICATION));*/

        super.onResume();
    }

    @Override
    protected void onPause() {
        //disableProgressBar();
        //unregisterReceiver(initReceiver);
        //unregisterReceiver(spinnerStateRec);
        super.onPause();
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
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));

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
            // showThreatsDialog();
			break;
		case R.id.action_pref2:
			// showWorkspaceDialog();
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
        FragmentManager fm = getSupportFragmentManager();
        int bsec = fm.getBackStackEntryCount();
        if (bsec > 1) {
            fm.popBackStack();
        } else {
            finish();
            super.onBackPressed();
        }
    }

    /**
     *
     */
    private void loadRightListPanel() {
        CollectionsGroupListFragment collectionsGroupListFragment = new CollectionsGroupListFragment();
        Bundle args = new Bundle();
        collectionsGroupListFragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container,
                        collectionsGroupListFragment).commit();
    }

    private void loadMapWithBasicSettingsView() {
        BrowseCollectionFirstDetailFragment browseCollectionFirstDetailFragment = BrowseCollectionFirstDetailFragment
                .newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        browseCollectionFirstDetailFragment,
                        "BrowseCollectionFirstDetailFragment").commit();
    }

    /**
     * for fragment to find out if activity is in two-pane mode
     */
    @Override
    public boolean isTwoPaneMode() {
        return TWO_PANEL_MODE;
    }

    @Override
    public void onCollectionSelected(Integer chosenCollectionId) {

        if (chosenCollectionId == -1) {
            Toast.makeText(CollectionsDefinitionActivity.this,
                    R.string.specific_collection_does_not_exist,
                    Toast.LENGTH_LONG).show();
            return;
        }

        BaseMapFragment newGisFrag = new BaseMapFragment();
        Bundle args = new Bundle();

        newGisFrag.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        // Replace whatever is in the fragment_container view with this
        // fragment,
        // and add the transaction to the back stack so the user can
        // navigate back
        transaction.replace(R.id.activity_base_details_container, newGisFrag);
        transaction.commit();

    }

    /*
        private void disableProgressBar() {
            if (initSpinner != null) {
                if (initSpinner.isShowing()) {
                    initSpinner.dismiss();
                }
            }
        }

        private class InitialisationReceiver extends BroadcastReceiver {

            @Override
            public void onReceive(Context context, Intent intent) {
                postReceivingData(intent);
            }

            private void postReceivingData(Intent intent) {
                disableProgressBar();
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    int resultCode = bundle
                            .getInt(Const.REQUEST_CODE_SERVICE_RESULT);
                    if (resultCode == RESULT_OK) {
                        Toast.makeText(CollectionsDefinitionActivity.this,
                                R.string.download_explain_doc_complete,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CollectionsDefinitionActivity.this,
                                R.string.download_failed, Toast.LENGTH_LONG).show();
                    }
                }
                supportInvalidateOptionsMenu();
            }
        }

        private class SpinnerStateReceiver extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                isWmsLoading = intent.getBooleanExtra(Const.KEY_MAP_WMS_LOAD_STATE,
                        false);
                new AsyncShowWmsSpinner().execute();
            }
        }

        private class AsyncShowWmsSpinner extends AsyncTask<String, Void, Void> {

            @Override
            protected void onPreExecute() {
                progressBarWmsLoad.setVisibility(View.VISIBLE);
            }

            @Override
            protected Void doInBackground(String... strTimes) {
                while (isWmsLoading) {
                    try {
                        Thread.sleep(5000);
                        isWmsLoading = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                progressBarWmsLoad.setVisibility(View.GONE);
                super.onPostExecute(result);
            }
        }

        */
    @Override
    public void onMapFragmentBoundsChange(LatLngBounds bounds) {
        BrowseCollectionFirstDetailFragment browseCollectionFirstDetailFragment = (BrowseCollectionFirstDetailFragment) getSupportFragmentManager()
                .findFragmentByTag("BrowseCollectionFirstDetailFragment");

        if (browseCollectionFirstDetailFragment != null) {
            // If article frag is available, we're in two-pane layout...
            // Call a method in the ArticleFragment to update its content
            browseCollectionFirstDetailFragment.updateAreaBounds(bounds);
        }
    }

    @Override
    public void onFragmentInteraction() {
        // TODO Auto-generated method stub

    }

}
