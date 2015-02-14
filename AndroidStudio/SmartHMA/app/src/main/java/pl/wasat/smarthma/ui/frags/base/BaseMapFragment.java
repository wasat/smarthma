package pl.wasat.smarthma.ui.frags.base;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

import pl.wasat.smarthma.interfaces.OnBaseMapFragmentPublicListener;
import pl.wasat.smarthma.utils.io.AcraExtension;
import pl.wasat.smarthma.utils.loc.LocManager;
import pl.wasat.smarthma.utils.wms.TileProviderFactory;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link pl.wasat.smarthma.ui.frags.base.BaseMapFragment.OnBaseMapFragmentListener} interface to handle interaction
 * events. Use the {@link BaseMapFragment#newInstance} factory method to create
 * an instance of this fragment.
 * 
 */
public class BaseMapFragment extends SupportMapFragment implements
		GooglePlayServicesClient.ConnectionCallbacks,
		GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener {

	protected static final String KEY_MAP_MODE = "pl.wasat.smarthma.KEY_MAP_MODE";
    private static final String KEY_PARAM_PUBLIC_LISTENER = "pl.wasat.samrthma.KEY_PARAM_PUBLIC_LISTENER";


	/** reference to Google Maps object */
	private SupportMapFragment supportMapFrag;
	protected GoogleMap mMap;
	// private LocationClient mLocationClient;

	private GoogleApiClient mGoogleApiClient;

	/** broadcast receiver */
	private BroadcastReceiver mReceiver;

	public OnBaseMapFragmentListener mListener;
	private OnBaseMapFragmentPublicListener publicListener;

	protected int mapMode;

	private LatLngBounds targetBounds;

    public BaseMapFragment() {
    }

	public static BaseMapFragment newInstance(
			OnBaseMapFragmentPublicListener listener) {
        BaseMapFragment baseMapFragment = new BaseMapFragment();
        baseMapFragment.publicListener = listener;
       // Bundle args = new Bundle();
        //args.putSerializable(KEY_PARAM_PUBLIC_LISTENER, listener);
        //baseMapFragment.setArguments(args);
        return baseMapFragment;
		//return new BaseMapFragment(listener);
	}



	// constructor
	//public BaseMapFragment(OnBaseMapFragmentPublicListener ml) {
	//	this.publicListener = ml;
	//}

    private void callBaseMapFragment(OnBaseMapFragmentPublicListener ml) {
    	this.publicListener = ml;
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AcraExtension.mapCustomLog("BaseMap.onCreate", mMap);

		if (getArguments() != null) {
			// mapMode = getArguments().getInt(KEY_MAP_MODE);
		}

		mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
				.addApi(LocationServices.API).addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this).build();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		AcraExtension.mapCustomLog("BaseMap.onActivityCreated", mMap);
		Log.i("BASE_MAP", "onActivityCreated");

		startCreateMap(savedInstanceState);
		// sendSupportMapReady();
	}

	@Override
	public void onStart() {
		super.onStart();
		// Connect the client.
		mGoogleApiClient.connect();
	}

	@Override
	public void onStop() {
		// Disconnecting the client invalidates it.
		mGoogleApiClient.disconnect();
		super.onStop();
	}

	@Override
	public void onConnected(Bundle dataBundle) {
		AcraExtension.mapCustomLog("BaseMap.onConnected", mMap);

		Location location = LocationServices.FusedLocationApi
				.getLastLocation(mGoogleApiClient);
		LatLng latLng = new LatLng(location.getLatitude(),
				location.getLongitude());
		CameraUpdate cameraUpdate = CameraUpdateFactory
				.newLatLngZoom(latLng, 4);
		mMap.animateCamera(cameraUpdate);
	}

	private void startCreateMap(Bundle savedInstanceState) {
		// restore selected party if any
		// NOTE: this doesn't work because if you use setRetainInstance(true) to
		// let GoogleMaps
		// retain internal state so it doesn't have to re-init so much:
		// http://stackoverflow.com/questions/11353075/how-can-i-maintain-fragment-state-when-added-to-the-back-stack
		// http://stackoverflow.com/questions/15545214/android-using-savedinstancestate-with-fragments

		AcraExtension.mapCustomLog("BaseMap.startCreateMap", mMap);

		int status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getActivity());
		if (status == ConnectionResult.SUCCESS) {
			supportMapFrag = this;

			setUpMapIfNeeded();
			if (savedInstanceState != null) {
				// Reincarnated activity. The obtained map is the same map
				// instance in the previous activity life cycle.
				// There is no need to reinitialize it if setRetainInstance
				// is set.
				// However, you still have to add all your listeners to it
				// later.
				mMap = supportMapFrag.getMap();

				AcraExtension.mapCustomLog(
						"BaseMap.startCreateMap.sIStNotNull", mMap);
			}
		} else {
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status,
					getActivity(), 42);
			dialog.show();
		}
	}

	private void setUpMapIfNeeded() {
		if (mMap == null) {
			mMap = supportMapFrag.getMap();
			// Check if we were successful in obtaining the map.
			AcraExtension
					.mapCustomLog("BaseMap.setUpMapIfNeeded.mapNull", mMap);
		}
		if (mMap != null) {
			AcraExtension.mapCustomLog("BaseMap.setUpMapIfNeeded.mapNotNull",
					mMap);
			setUpMap();
		}

	}

	private void setUpMap() {
		AcraExtension.mapCustomLog("BaseMap.setUpMap", mMap);
		mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
		mMap.setMyLocationEnabled(true);

		mMap.getUiSettings().setZoomControlsEnabled(true);
		mMap.getUiSettings().setZoomGesturesEnabled(true);
		mMap.getUiSettings().setRotateGesturesEnabled(true);

		setupOSM();

		if (targetBounds == null) {
			animateToCurrentPosition();
		}

		sendSupportMapReadyCallback();

		mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
			@Override
			public void onMapLoaded() {
				animateToBounds();
			}
		});

	}

	/*
	 * private void sendSupportMapReadyCallback() { Fragment fragment = this; if
	 * (fragment != null) { if (fragment instanceof OnBaseMapFragmentListener) {
	 * ((OnBaseMapFragmentListener) fragment).onBaseSupportMapReady();
	 * Log.i("BASE_MAP", "onActivityCreated.Listener"); } } }
	 */

	private void sendSupportMapReadyCallback() {
		Fragment fragment = this;
		if (fragment instanceof OnBaseMapFragmentListener) {
			((OnBaseMapFragmentListener) fragment).onBaseSupportMapReady();
			Log.i("BASE_MAP", "onActivityCreated.Listener");
		} else {
			publicListener.onBaseSupportMapPublicReady();
		}
	}

	private void setupOSM() {
		AcraExtension.mapCustomLog("BaseMap.setupOSM", mMap);

		TileProvider osmTileProvider = TileProviderFactory.getOSMTileProvider();
		mMap.addTileOverlay(new TileOverlayOptions().tileProvider(
				osmTileProvider).zIndex(0));
	}

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

	private void animateToCurrentPosition() {

		// ------------------Zooming camera to position user-----------------
		LocManager locManager = new LocManager(getActivity());
		Location location = locManager.getAvailableLocation();

		if (location != null) {
			int mapZoom;
			if (location.getLatitude() == 0 && location.getLongitude() == 0) {
				mapZoom = 0;
			} else {
				mapZoom = 8;
			}

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(location.getLatitude(), location
							.getLongitude())) // Sets the center of the map to
												// location user
					.zoom(mapZoom) // Sets the zoom
					.build(); // Creates a CameraPosition from the builder
			mMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));
		}
	}

	private void animateToBounds() {
		if (targetBounds != null) {
			mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(
					targetBounds, 75));
		}
	}

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
	public void onDisconnected() {
		// TODO Auto-generated method stub

	}

	/**
	 * Listener interface to tell when the map is ready
	 */
	public static interface OnBaseMapFragmentListener {

		void onBaseSupportMapReady();
	}

	public void setTargetBounds(LatLngBounds bounds) {
		targetBounds = bounds;
	}

	@Override
	public void onConnectionSuspended(int cause) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub

	}

}
