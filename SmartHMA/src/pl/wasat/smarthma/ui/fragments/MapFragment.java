package pl.wasat.smarthma.ui.fragments;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.utils.wms.TileProviderFactory;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

/**
 * A Google map fragment showing parties on a map.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class MapFragment extends SupportMapFragment {
	private final static String TAG = "MapActivity";
	private static final String KEY_MAP_ID_LAYERS_ARRAY = "pl.wasat.smarthma.KEY_MAP_ID_LAYERS_ARRAY";

	/** reference to Google Maps object */
	private GoogleMap mMap;

	// private ArrayList<TileOverlay> wmsOverlaysArray;

	/** broadcast receiver */
	private BroadcastReceiver mReceiver;

	// public String wmsToDisplay = "";

	public MapFragment() {
	}

	/** called when this fragment is attached to an activity */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Activities containing this fragment must implement its callbacks.
		// if (!(activity instanceof Callbacks)) {
		// throw new
		// IllegalStateException("Activity must implement fragment's callbacks.");
		// }
		// mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	/**
	 * called after the activity has been created so we can initialize the map
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// restore selected party if any
		// NOTE: this doesn't work because if you use setRetainInstance(true) to
		// let GoogleMaps
		// retain internal state so it doesn't have to re-init so much:
		// http://stackoverflow.com/questions/11353075/how-can-i-maintain-fragment-state-when-added-to-the-back-stack
		// http://stackoverflow.com/questions/15545214/android-using-savedinstancestate-with-fragments

		int status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getActivity());
		if (status == ConnectionResult.SUCCESS) {
			GoogleMap mapFragment = getMap();
			if (mapFragment != null) {
				setUpMapIfNeeded();
				if (savedInstanceState != null) {
					// Reincarnated activity. The obtained map is the same map
					// instance in the previous activity life cycle.
					// There is no need to reinitialize it if setRetainInstance
					// is set.
					// However, you still have to add all your listeners to it
					// later.
					mMap = getMap();
					ArrayList<Integer> layerIds = savedInstanceState
							.getIntegerArrayList(KEY_MAP_ID_LAYERS_ARRAY);
					//showVisibleLayers(layerIds);

				} else {
					// First incarnation of this activity.
					// set retaininstance to minimize rotation time w/ google
					// maps
					// this.setRetainInstance(true);
				}
				// setUpMapIfNeeded();
			} else {
				Log.e(TAG,
						getActivity().getString(
								R.string.couldn_t_find_google_map_fragment));
			}
		} else {
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status,
					getActivity(), 42);
			dialog.show();
		}

	}

	/**
	 * @param layerIds
	 */
/*	private void showVisibleLayers(ArrayList<Integer> layerIds) {
		if (!SmartHMApplication.GlobalEOData.isEmpty() && !layerIds.isEmpty()) {
			for (int i = 0; i < layerIds.size(); i++) {
				int layerId = layerIds.get(i);
				TileOverlay gisOverlay = SmartHMApplication.GlobalEOData.get(
						layerId).getMapOverlay();
				gisOverlay = setupWMS(SmartHMApplication.GlobalEOData.get(
						layerId).getWmsUrl());
				SmartHMApplication.GlobalEOData.get(layerId).setMapOverlay(
						gisOverlay);
			}
		}
	}*/

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// outState.putBoolean(KEY_STATE_MENU_ENABLED, isMenuEnabled);

/*		outState.putIntegerArrayList(KEY_MAP_ID_LAYERS_ARRAY,
				findVisibleLayers());*/
		super.onSaveInstanceState(outState);
	}

/*	private ArrayList<Integer> findVisibleLayers() {
		ArrayList<Integer> indexArr = new ArrayList<Integer>();
		for (int i = 0; i < SmartHMApplication.GlobalEOData.size(); i++) {
			if (SmartHMApplication.GlobalEOData.get(i).isChecked()) {
				indexArr.add(SmartHMApplication.GlobalEOData.get(i).getId());
			}
		}
		return indexArr;
	}*/

	@Override
	public void onPause() {
		super.onPause();

		if (mReceiver != null) {
			// unhook the receiver
			LocalBroadcastManager.getInstance(getActivity())
					.unregisterReceiver(mReceiver);
			mReceiver = null;
		}
	}

	@Override
	public void onResume() {

		super.onResume();
	}

	private void setUpMapIfNeeded() {
		if (mMap == null) {
			mMap = getMap();
			// Check if we were successful in obtaining the map.
		}
		if (mMap != null) {
			setUpMap();
		} else {
			Log.e(TAG, getActivity().getString(R.string.map_was_null));
		}

	}

	private void setUpMap() {
		setupOSM();

		mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		mMap.setMyLocationEnabled(true);

		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
				Const.CENTER_LAYER, 10);
		mMap.animateCamera(cameraUpdate);

		mMap.setOnMapLongClickListener(new OnMapLongClickListener() {
			@Override
			public void onMapLongClick(LatLng point) {
/*				String info = "\n"
						+ getActivity().getString(
								R.string.smarthmas_layers_visible) + "\n";
				for (int i = 0; i < SmartHMApplication.GlobalEOData.size(); i++) {
					if (SmartHMApplication.GlobalEOData.get(i).isChecked()) {
						info = info
								+ "- "
								+ SmartHMApplication.GlobalEOData.get(i)
										.getTitle() + "\n";
					}
				}
				Toast.makeText(getActivity(), info, Toast.LENGTH_LONG).show();*/
			}
		});
	}

	private void setupOSM() {
		TileProvider osmTileProvider = TileProviderFactory.getOSMTileProvider();
		mMap.addTileOverlay(new TileOverlayOptions()
				.tileProvider(osmTileProvider));

	}

/*	public void toogleMapOverlay(Integer layerId, String urlArgs) {
		TileOverlay gisOverlay = SmartHMApplication.GlobalEOData.get(layerId)
				.getMapOverlay();
		if (SmartHMApplication.GlobalEOData.get(layerId).isChecked()
				&& SmartHMApplication.GlobalEOData.get(layerId)
						.getMapOverlay() == null) {
			gisOverlay = setupWMS(SmartHMApplication.GlobalEOData.get(layerId)
					.getWmsUrl() + urlArgs);
			SmartHMApplication.GlobalEOData.get(layerId).setMapOverlay(
					gisOverlay);
		}
		if (!SmartHMApplication.GlobalEOData.get(layerId).isChecked()
				&& SmartHMApplication.GlobalEOData.get(layerId)
						.getMapOverlay() != null) {
			gisOverlay.remove();
			SmartHMApplication.GlobalEOData.get(layerId).setMapOverlay(null);
		}
	}*/

	public TileOverlay setupWMS(String wmsUrl) {
		TileOverlay wmsTileOverlay = null;
		if (wmsUrl != null) {
			if (!wmsUrl.isEmpty()) {

				TileProvider wmsTileProvider;
				wmsTileProvider = TileProviderFactory.getWmsTileProvider(
						wmsUrl, getActivity());
				wmsTileOverlay = mMap.addTileOverlay(new TileOverlayOptions()
						.tileProvider(wmsTileProvider));
			}
		}
		return wmsTileOverlay;
	}

/*	public void clearAllWmsOverlay() {

		for (int i = 0; i < SmartHMApplication.GlobalEOData.size(); i++) {
			if (SmartHMApplication.GlobalEOData.get(i).isChecked()) {
				SmartHMApplication.GlobalEOData.get(i).toggleChecked();
			}
			if (SmartHMApplication.GlobalEOData.get(i).getMapOverlay() != null) {
				SmartHMApplication.GlobalEOData.get(i).getMapOverlay()
						.remove();
				SmartHMApplication.GlobalEOData.get(i).setMapOverlay(null);
			}

		}
	}*/

}
