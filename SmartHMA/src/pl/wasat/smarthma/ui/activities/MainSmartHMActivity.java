package pl.wasat.smarthma.ui.activities;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.interfaces.OnCollectionsListSelectionListener;
import pl.wasat.smarthma.ui.frags.GalleryFragment;
import pl.wasat.smarthma.ui.frags.MapSearchFragment;
import pl.wasat.smarthma.ui.frags.MapSearchFragment.OnMapSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.browse.CollectionsGroupListFragment;
import pl.wasat.smarthma.ui.frags.browse.CollectionsListFragment.OnFragmentInteractionListener;
import roboguice.util.temp.Ln;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLngBounds;

public class MainSmartHMActivity extends BaseSmartHMActivity implements
		OnCollectionsListSelectionListener, OnFragmentInteractionListener,
		OnMapSearchFragmentListener {

	private ProgressDialog initSpinner;
	private ProgressBar progressBarWmsLoad;
	private InitialisationReceiver initReceiver;
	private SpinnerStateReceiver spinnerStateRec;
	private boolean isWmsLoading = false;

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private static boolean TWO_PANEL_MODE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Ln.getConfig().setLoggingLevel(Log.ERROR);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_two_panel);

		if (savedInstanceState != null) {
			// isMenuEnabled =
			// savedInstanceState.getBoolean(KEY_STATE_MENU_ENABLED);
			// visibleWorskpace =
			// savedInstanceState.getString(KEY_VISIBLE_WORKSPACE);
		}
		ViewGroup topLayout = (ViewGroup) findViewById(R.id.left_panel_map);
		topLayout.requestTransparentRegion(topLayout);

		progressBarWmsLoad = (ProgressBar) findViewById(R.id.progressBarWmsLoad);

		if (findViewById(R.id.right_list_container) != null) {
			TWO_PANEL_MODE = true;
			loadRightListPanel();
			//loadGalleryPanel();
			loadMapView();
		}
	}

	@Override
	protected void onResume() {
		initReceiver = new InitialisationReceiver();
		registerReceiver(initReceiver, new IntentFilter(
				Const.KEY_SERVICE_INTENTFILTER_NOTIFICATION));

		spinnerStateRec = new SpinnerStateReceiver();
		registerReceiver(spinnerStateRec, new IntentFilter(
				Const.KEY_MAP_SPINNER_INTENTFILTER_NOTIFICATION));

		super.onResume();
	}

	@Override
	protected void onPause() {
		disableProgressBar();
		unregisterReceiver(initReceiver);
		unregisterReceiver(spinnerStateRec);
		super.onPause();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// outState.putBoolean(KEY_STATE_MENU_ENABLED, isMenuEnabled);
		// outState.putString(KEY_VISIBLE_WORKSPACE, visibleWorskpace);
		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_eo_map, menu);

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
		// menu.setGroupEnabled(R.id.menu_group_gis, isMenuEnabled);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_pref1:
			// showThreatsDialog();
			break;
		case R.id.action_pref2:
			// showWorkspaceDialog();
			break;
		case R.id.action_pref3:
			break;
		case R.id.action_clear_all_settings:
			break;
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
		CollectionsGroupListFragment rightListFragment = new CollectionsGroupListFragment();
		Bundle args = new Bundle();
		rightListFragment.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.right_list_container, rightListFragment)
				.addToBackStack("CollGroupListFrag").commit();
	}

	private void loadGalleryPanel() {
		GalleryFragment galleryFragment = new GalleryFragment();
		Bundle args = new Bundle();
		galleryFragment.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.gallery_bottom_panel, galleryFragment).commit();
	}

	private void loadMapView() {
		MapSearchFragment mapSearchFragment = MapSearchFragment.newInstance(
        );

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.left_panel_map, mapSearchFragment)
				.addToBackStack("MapSearchFragment").commit();
	}

	/** for fragment to find out if activity is in two-pane mode */
	@Override
	public boolean isTwoPaneMode() {
		return TWO_PANEL_MODE;
	}

	@Override
	public void onCollectionSelected(Integer chosenCollectionId) {

		if (chosenCollectionId == -1) {
			Toast.makeText(MainSmartHMActivity.this,
					R.string.specific_collection_does_not_exist,
					Toast.LENGTH_LONG).show();
			return;
		}

//		if (urlArgs == null) {
//			urlArgs = "";
//		} else {
//			urlArgs = "&time=" + urlArgs;
//		}

		MapSearchFragment gisFrag = (MapSearchFragment) getSupportFragmentManager()
				.findFragmentById(R.id.left_panel_map);

		//if (gisFrag != null) {
			// If frag is available, we're in two-pane layout...
			// Call a method in the MapFragment to update its content
		//} else {
			// Otherwise, we're in the one-pane layout and must swap frags...
			// Create fragment and give it an argument for the selected data
			MapSearchFragment newGisFrag = new MapSearchFragment();
			Bundle args = new Bundle();

			newGisFrag.setArguments(args);

			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();

			// Replace whatever is in the fragment_container view with this
			// fragment,
			// and add the transaction to the back stack so the user can
			// navigate back
			transaction.replace(R.id.left_panel_map, newGisFrag);
			// transaction.addToBackStack(null);
			transaction.commit();
		//}
	}

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

		/**
		 * @param intent
		 */
		private void postReceivingData(Intent intent) {
			disableProgressBar();
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				int resultCode = bundle
						.getInt(Const.REQUEST_CODE_SERVICE_RESULT);
				if (resultCode == RESULT_OK) {
					Toast.makeText(MainSmartHMActivity.this,
							R.string.download_explain_doc_complete,
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainSmartHMActivity.this,
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.fragments.CustomizedListViewFragment.
	 * OnFragmentInteractionListener#onFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onFragmentInteraction() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.fragments.MapSearchFragment.
	 * OnMapSearchFragmentInteractionListener
	 * #onMapSearchFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onMapSearchFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapSearchFragmentBoundsChange(LatLngBounds bounds) {
		// TODO Auto-generated method stub

	}

}
