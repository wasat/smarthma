package pl.wasat.smarthma.kindle;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.CameraUpdate;
import com.amazon.geo.mapsv2.CameraUpdateFactory;
import com.amazon.geo.mapsv2.SupportMapFragment;
import com.amazon.geo.mapsv2.model.CameraPosition;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.LatLngBounds;
import com.amazon.geo.mapsv2.model.TileOverlay;
import com.amazon.geo.mapsv2.model.TileOverlayOptions;
import com.amazon.geo.mapsv2.model.TileProvider;
import com.amazon.geo.mapsv2.util.AmazonMapsRuntimeUtil;
import com.amazon.geo.mapsv2.util.ConnectionResult;

import pl.wasat.smarthma.interfaces.OnBaseMapFragmentPublicListener;
import pl.wasat.smarthma.utils.loc.LocManager;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link pl.wasat.smarthma.kindle.AmznBaseMapFragment.OnBaseMapFragmentListener} interface to handle interaction
 * events. Use the {@link AmznBaseMapFragment#newInstance} factory method to create
 * an instance of this fragment.
 */
public class AmznBaseMapFragment extends SupportMapFragment
// implements
// GooglePlayServicesClient.ConnectionCallbacks,
// GoogleApiClient.ConnectionCallbacks,
// GoogleApiClient.OnConnectionFailedListener
{

    // protected static final String KEY_MAP_MODE = "pl.wasat.smarthma.KEY_MAP_MODE";

    /**
     * reference to Google Maps object
     */
    private SupportMapFragment supportMapFrag;
    // protected GoogleMap mMap;
<<<<<<< HEAD
    AmazonMap mMap;
=======
    protected AmazonMap mMap;
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    // private LocationClient mLocationClient;

    // private GoogleApiClient mGoogleApiClient;

    /**
     * broadcast receiver
     */
    private BroadcastReceiver mReceiver;

    public OnBaseMapFragmentListener mListener;
    private OnBaseMapFragmentPublicListener publicListener;

    // protected int mapMode;

    private LatLngBounds targetBounds;

/*	public static BaseMapFragment newInstance(
            OnBaseMapFragmentPublicListener listener) {
		return new BaseMapFragment(listener);
	}*/

    public AmznBaseMapFragment() {
    }

    public static AmznBaseMapFragment newInstance(
            OnBaseMapFragmentPublicListener listener) {
        AmznBaseMapFragment baseMapFragment = new AmznBaseMapFragment();
        baseMapFragment.publicListener = listener;
        return baseMapFragment;
    }


    // constructor
    //public BaseMapFragment(OnBaseMapFragmentPublicListener ml) {
    //	this.publicListener = ml;
    //}

/*    private void callBaseMapFragment(OnBaseMapFragmentPublicListener ml) {
        this.publicListener = ml;
    }*/

<<<<<<< HEAD
/*    @Override
=======
    @Override
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AcraExtension.mapCustomLog("BaseMap.onCreate", mMap);

<<<<<<< HEAD
*//*        if (getArguments() != null) {
            // mapMode = getArguments().getInt(KEY_MAP_MODE);
        }*//*
=======
        if (getArguments() != null) {
            // mapMode = getArguments().getInt(KEY_MAP_MODE);
        }
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4

        // mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
        // .addApi(LocationServices.API).addConnectionCallbacks(this)
        // .addOnConnectionFailedListener(this).build();
<<<<<<< HEAD
    }*/
=======
    }
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //AcraExtension.mapCustomLog("BaseMap.onActivityCreated", mMap);
        Log.i("BASE_MAP", "onActivityCreated");

        startCreateMap(savedInstanceState);
        // sendSupportMapReady();
    }

<<<<<<< HEAD
/*    @Override
=======
    @Override
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    public void onStart() {
        super.onStart();
        // Connect the client.
        // mGoogleApiClient.connect();
<<<<<<< HEAD
    }*/

/*    @Override
=======
    }

    @Override
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    public void onStop() {
        // Disconnecting the client invalidates it.
        // mGoogleApiClient.disconnect();
        super.onStop();
<<<<<<< HEAD
    }*/
=======
    }
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4

    // @Override
    // public void onConnected(Bundle dataBundle) {
    // AcraExtension.mapCustomLog("BaseMap.onConnected", mMap);
    //
    // Location location = LocationServices.FusedLocationApi
    // .getLastLocation(mGoogleApiClient);
    // LatLng latLng = new LatLng(location.getLatitude(),
    // location.getLongitude());
    // CameraUpdate cameraUpdate = CameraUpdateFactory
    // .newLatLngZoom(latLng, 4);
    // mMap.animateCamera(cameraUpdate);
    // }

    private void startCreateMap(Bundle savedInstanceState) {
        //AcraExtension.mapCustomLog("BaseMap.startCreateMap", mMap);

        // if (checkForAmazonMaps()) {
        // // Maps should be available. Code
        // // to get a reference to the map and proceed
        // // normally goes here...
        //
        // supportMapFrag = this;
        //
        // setUpMapIfNeeded();
        // if (savedInstanceState != null) {
        // mMap = supportMapFrag.getMap();
        //
        // AcraExtension.mapCustomLog(
        // "BaseMap.startCreateMap.sIStNotNull", mMap);
        // }
        // } else {
        // // Maps is not available. Get the exact result
        // // code and write it in a log message
        // int cr = AmazonMapsRuntimeUtil
        // .isAmazonMapsRuntimeAvailable(getActivity());
        // String msg = getString(R.string.no_maps) + " ConnectionResult = "
        // + cr;
        // Dialog dialog = AmazonMapsRuntimeUtil.getErrorDialog(status,
        // getActivity(), 42);
        // dialog.show();
        // }

        int status = AmazonMapsRuntimeUtil
                .isAmazonMapsRuntimeAvailable(getActivity());
        if (status == ConnectionResult.SUCCESS) {
            supportMapFrag = this;

            setUpMapIfNeeded();
            if (savedInstanceState != null) {
                mMap = supportMapFrag.getMap();

                //AcraExtension.mapCustomLog("BaseMap.startCreateMap.sIStNotNull", mMap);
            }
        } else {
            Dialog dialog = AmazonMapsRuntimeUtil.getErrorDialog(status,
                    getActivity(), 42);
            dialog.show();
        }
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = supportMapFrag.getMap();
            // Check if we were successful in obtaining the map.
            //AcraExtension.mapCustomLog("BaseMap.setUpMapIfNeeded.mapNull", mMap);
        }
        if (mMap != null) {
            //AcraExtension.mapCustomLog("BaseMap.setUpMapIfNeeded.mapNotNull",mMap);
            setUpMap();
        }

    }

    private void setUpMap() {
        //AcraExtension.mapCustomLog("BaseMap.setUpMap", mMap);
        mMap.setMapType(AmazonMap.MAP_TYPE_NONE);
        mMap.setMyLocationEnabled(true);

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);

        setupOSM();
        findStartLocation();

        if (targetBounds == null) {
            animateToCurrentPosition();
        }

        sendSupportMapReadyCallback();

        mMap.setOnMapLoadedCallback(new AmazonMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                animateToBounds();
            }
        });

    }

//	private boolean checkForAmazonMaps() {
//		// Check for the presence of Amazon Maps on the device
//
//		return AmazonMapsRuntimeUtil
//				.isAmazonMapsRuntimeAvailable(getActivity()) == ConnectionResult.SUCCESS;
//	}

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
        //AcraExtension.mapCustomLog("BaseMap.setupOSM", mMap);

        TileProvider osmTileProvider = AmznTileProviderFactory.getOSMTileProvider();
        mMap.addTileOverlay(new TileOverlayOptions().tileProvider(
                osmTileProvider).zIndex(0));
    }

    public TileOverlay setupWMS(String wmsUrl) {

        com.amazon.geo.mapsv2.model.TileOverlay wmsTileOverlay = null;
        if (wmsUrl != null) {
            if (!wmsUrl.isEmpty()) {

                TileProvider wmsTileProvider;
                wmsTileProvider = AmznTileProviderFactory.getWmsTileProvider(
                        wmsUrl, getActivity());
                wmsTileOverlay = mMap.addTileOverlay(new TileOverlayOptions()
                        .tileProvider(wmsTileProvider));
            }
        }
        return wmsTileOverlay;
    }

    private void findStartLocation() {

        // Obtain the last known location
        final LocationManager locationManager = (LocationManager) getActivity()
                .getSystemService(Context.LOCATION_SERVICE);
        Location bestLocation = null;
        for (String provider : locationManager.getProviders(true)) {
            final Location location = locationManager
                    .getLastKnownLocation(provider);
            if (location != null
                    && (bestLocation == null || location.getAccuracy() < bestLocation
                    .getAccuracy())) {
                // Note: we might want to ignore 'stale' location readings
                bestLocation = location;
            }
        }

        if (bestLocation == null) {
            Toast.makeText(getActivity(), "Could not obtain location",
                    Toast.LENGTH_LONG).show();
        } else {
            final LatLng myLocation = new LatLng(bestLocation.getLatitude(),
                    bestLocation.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                    myLocation, 4);
            mMap.animateCamera(cameraUpdate);
        }
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

    // @Override
    // public void onDisconnected() {
    // // TODO Auto-generated method stub
    //
    // }

    /**
     * Listener interface to tell when the map is ready
     */
    public static interface OnBaseMapFragmentListener {

        void onBaseSupportMapReady();
    }

    public void setTargetBounds(LatLngBounds bounds) {
        targetBounds = bounds;
    }

	/*
     * @Override public void onConnectionSuspended(int cause) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

    // @Override
    // public void onConnectionFailed(ConnectionResult result) {
    // // TODO Auto-generated method stub
    //
    // }

}
