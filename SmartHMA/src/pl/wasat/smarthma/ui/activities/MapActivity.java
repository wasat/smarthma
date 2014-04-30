package pl.wasat.smarthma.ui.activities;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.interfaces.OnCollectionsListSelectionListener;
import pl.wasat.smarthma.services.SmartHMAOkHttpSpiceService;
import pl.wasat.smarthma.ui.fragments.CollectionListFragment;
import pl.wasat.smarthma.ui.fragments.MapFragment;
import pl.wasat.smarthma.utils.http.ExplainDocRequest;
import pl.wasat.smarthma.utils.xml.XMLParser;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class MapActivity extends FragmentActivity implements
		OnCollectionsListSelectionListener, RequestListener<String> {

	private ProgressDialog initSpinner;
	private ProgressBar progressBarWmsLoad;
	private InitialisationReceiver initReceiver;
	private SpinnerStateReceiver spinnerStateRec;
	// private boolean isMenuEnabled = false;
	boolean isWmsLoading = false;
	// private String visibleWorskpace;

	private SpiceManager spiceManager = new SpiceManager(
			SmartHMAOkHttpSpiceService.class);

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	public static boolean TWO_PANEL_MODE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_gis_map);
		// visibleWorskpace = "all";

		if (savedInstanceState != null) {
			// isMenuEnabled =
			// savedInstanceState.getBoolean(KEY_STATE_MENU_ENABLED);
			// visibleWorskpace =
			// savedInstanceState.getString(KEY_VISIBLE_WORKSPACE);
		} else {
			clearAllVisibleLayers();
		}

		// NOTE: activity_party_map layout is aliased in
		// res/values-large/refs.xml
		// and res/values-sw600dp/refs.xml to switch to
		// activity_party_twopane.xml

		// workaround for black map...see
		// http://code.google.com/p/gmaps-api-issues/issues/detail?id=4639
		ViewGroup topLayout = (ViewGroup) findViewById(R.id.gis_map);
		topLayout.requestTransparentRegion(topLayout);

		progressBarWmsLoad = (ProgressBar) findViewById(R.id.progressBarWmsLoad);

		if (findViewById(R.id.my_list_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			TWO_PANEL_MODE = true;

			replaceListFragment();
		}

		performExplainDocRequest();
		// startLoadCapabilities();
	}

	@Override
	protected void onResume() {
		initReceiver = new InitialisationReceiver();
		registerReceiver(initReceiver, new IntentFilter(
				Const.KEY_SERVICE_INTENTFILTER_NOTIFICATION));

		spinnerStateRec = new SpinnerStateReceiver();
		registerReceiver(spinnerStateRec, new IntentFilter(
				Const.KEY_MAP_SPINNER_INTENTFILTER_NOTIFICATION));
		// updatingTimer = new Timer();

		super.onResume();
	}

	@Override
	protected void onPause() {
		disableProgressBar();
		unregisterReceiver(initReceiver);
		unregisterReceiver(spinnerStateRec);
		// updatingTimer.cancel();
		super.onPause();
	}

	@Override
	protected void onStart() {
		spiceManager.start(this);
		super.onStart();
	}

	@Override
	protected void onStop() {
		spiceManager.shouldStop();
		super.onStop();
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
		inflater.inflate(R.menu.menu_gis_map, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// menu.setGroupEnabled(R.id.menu_group_gis, isMenuEnabled);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void supportInvalidateOptionsMenu() {
		// TODO Auto-generated method stub
		super.supportInvalidateOptionsMenu();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add_threats:
			// showThreatsDialog();
			break;
		case R.id.action_add_workspace:
			// showWorkspaceDialog();
			break;
		case R.id.action_add_all_layers:
			// visibleWorskpace = "all";
			// openWMSLayerListActivity(visibleWorskpace);
			break;
		case R.id.action_clear_all_layers:
			clearAllVisibleLayers();
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
	 * private void startLoadCapabilities() { long diff =
	 * System.currentTimeMillis() - SCGIS.DataAge; if (SCGIS.ALLayers.isEmpty()
	 * || diff > 1800000) { Intent intent = new Intent(this,
	 * CapabilitiesService.class); startService(intent); initProgressBar(); }
	 * else { isMenuEnabled = true; // disableProgressBar(); } }
	 */

	/*
	 * private void showThreatsDialog() { ThreatsDialogFragment
	 * threatsDialFragment = new ThreatsDialogFragment();
	 * threatsDialFragment.show(getSupportFragmentManager(), THREATS_DIALOG); }
	 */

	/*
	 * public void showWorkspaceDialog() { DialogFragment wsdFragment = new
	 * WorkspaceDialogFragment(); wsdFragment.show(getSupportFragmentManager(),
	 * WORKSPACE_DIALOG); }
	 */
	/*
	 * private void openWMSLayerListActivity(String workspace) { if
	 * (TWO_PANEL_MODE) { //onDialogListClick(workspace);
	 * 
	 * } else { Intent wmsLayerListActivityIntent = new
	 * Intent(GisMapActivity.this, WMSLayerListActivity.class);
	 * wmsLayerListActivityIntent.putExtra(
	 * Const.KEY_LIST_WORKSPACE_NAME_TO_LOAD, workspace);
	 * startActivityForResult(wmsLayerListActivityIntent,
	 * Const.REQUEST_CODE_MAP_ADD_LAYER); } }
	 */

	/**
	 * 
	 */
	private void replaceListFragment() {
		CollectionListFragment fragment = new CollectionListFragment();
		Bundle args = new Bundle();
		// args.putString(Const.KEY_LIST_WORKSPACE_NAME_TO_LOAD,
		// visibleWorskpace);
		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.my_list_container, fragment).commit();
	}

	/*
	 * @Override public void onActivityResult(int requestCode, int resultCode,
	 * Intent data) { if (requestCode == Const.REQUEST_CODE_MAP_ADD_LAYER &&
	 * resultCode == FragmentActivity.RESULT_OK) { ArrayList<Integer> layerIds =
	 * data .getIntegerArrayListExtra(Const.KEY_LIST_ID_LAYERS_TO_DISPLAY); if
	 * (layerIds != null) { for (int i = 0; i < layerIds.size(); i++) {
	 * onCollectionSelected(layerIds.get(i), null); } } }
	 * super.onActivityResult(requestCode, resultCode, data); }
	 */

	private void clearAllVisibleLayers() {
		/*
		 * MapFragment mapFrag = (MapFragment) getSupportFragmentManager()
		 * .findFragmentById(R.id.gis_map);
		 * 
		 * if (mapFrag != null) { mapFrag.clearAllWmsOverlay(); }
		 */

		if (TWO_PANEL_MODE) {
			replaceListFragment();
		}
		/*
		 * else { // Otherwise, we're in the one-pane layout and must swap
		 * frags... // Create fragment and give it an argument for the selected
		 * article GisMapFragment newGisFrag = new GisMapFragment(); Bundle args
		 * = new Bundle(); args.putString(Const.KEY_MAP_LAYER_TO_DISPLAY, "");
		 * newGisFrag.setArguments(args);
		 * 
		 * FragmentTransaction transaction = getSupportFragmentManager()
		 * .beginTransaction(); transaction.replace(R.id.gis_map, newGisFrag);
		 * transaction.commit(); }
		 */

	}

	/** for fragment to find out if activity is in two-pane mode */
	@Override
	public boolean isTwoPaneMode() {
		return TWO_PANEL_MODE;
	}

	@Override
	public void onCollectionSelected(Integer chosenCollectionId, String urlArgs) {

		if (chosenCollectionId == -1) {
			Toast.makeText(MapActivity.this,
					R.string.specific_layer_does_not_exist, Toast.LENGTH_LONG)
					.show();
			return;
		}

		if (urlArgs == null) {
			urlArgs = "";
		} else {
			urlArgs = "&time=" + urlArgs;
		}

		MapFragment gisFrag = (MapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.gis_map);

		if (gisFrag != null) {
			// If article frag is available, we're in two-pane layout...
			// Call a method in the ArticleFragment to update its content
			//gisFrag.toogleMapOverlay(chosenCollectionId, urlArgs);
			// loadLegend(SCGIS.ALLayers.get(chosenLayerId).getLegendUrl());
		} else {
			// Otherwise, we're in the one-pane layout and must swap frags...
			// Create fragment and give it an argument for the selected article
			MapFragment newGisFrag = new MapFragment();
			Bundle args = new Bundle();
/*			args.putString(Const.KEY_MAP_LAYER_TO_DISPLAY,
					SmartHMApplication.GlobalEOData.get(chosenCollectionId)
							.getWmsUrl() + urlArgs);*/
			newGisFrag.setArguments(args);

			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();

			// Replace whatever is in the fragment_container view with this
			// fragment,
			// and add the transaction to the back stack so the user can
			// navigate back
			transaction.replace(R.id.gis_map, newGisFrag);
			// transaction.addToBackStack(null);
			transaction.commit();
		}
	}

	/*
	 * private void loadLegend(String legend) { legend = legend +
	 * "&service=WMS"; Picasso.with(this).load(legend).into(imgLegend); }
	 */

	/*
	 * @Override public void onDialogListClick(String workspaceItem) {
	 * visibleWorskpace = workspaceItem; WMSLayerListFragment listFrag = new
	 * WMSLayerListFragment(); Bundle args = new Bundle();
	 * args.putString(Const.KEY_LIST_WORKSPACE_NAME_TO_LOAD, workspaceItem);
	 * listFrag.setArguments(args);
	 * 
	 * FragmentTransaction transaction = getSupportFragmentManager()
	 * .beginTransaction(); transaction.replace(R.id.my_list_container,
	 * listFrag); // transaction.addToBackStack(null); transaction.commit(); }
	 */

	/*
	 * @Override public void onTimeDialogListClick(WMSLayer threatLayer, int
	 * timeItem) { String timeStr = ""; if (threatLayer.getTimes().length ==
	 * timeItem) { timeStr = "current"; } else { timeStr =
	 * threatLayer.getTimes()[timeItem]; } threatLayer.setOverlied(true);
	 * onLayerSelected(threatLayer.getId(), timeStr); }
	 */

	private void initProgressBar() {
		initSpinner = new ProgressDialog(this);
		initSpinner.setMessage(getString(R.string.data_is_loading_please_wait));
		initSpinner.setCancelable(false);
		initSpinner.show();
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
					Toast.makeText(MapActivity.this,
							R.string.download_capabilities_complete,
							Toast.LENGTH_SHORT).show();
					if (TWO_PANEL_MODE) {
						replaceListFragment();
					}
					// isMenuEnabled = true;
				} else {
					Toast.makeText(MapActivity.this, R.string.download_failed,
							Toast.LENGTH_LONG).show();
				}
			}
			supportInvalidateOptionsMenu();
		}
	};

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

	private void performExplainDocRequest() {

		MapActivity.this.setProgressBarIndeterminateVisibility(true);

		ExplainDocRequest request = new ExplainDocRequest();
		spiceManager.execute(request, this);

	}

	@Override
	public void onRequestFailure(SpiceException spiceException) {
		Toast.makeText(MapActivity.this,
				"Error: " + spiceException.getMessage(), Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onRequestSuccess(String result) {
		MapActivity.this.setProgressBarIndeterminateVisibility(false);
		Toast.makeText(MapActivity.this, "OK!!! ", Toast.LENGTH_SHORT).show();
		XMLParser xmlResult = new XMLParser();
		xmlResult.parseXml(result);
		xmlResult.setDataGlobals();

	}

}
