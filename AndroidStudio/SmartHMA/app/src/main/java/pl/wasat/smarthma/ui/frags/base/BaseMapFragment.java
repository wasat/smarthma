package pl.wasat.smarthma.ui.frags.base;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
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
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.utils.loc.GoogleLocProviderImpl;
import pl.wasat.smarthma.utils.wms.TileProviderFactory;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link pl.wasat.smarthma.ui.frags.base.BaseMapFragment.OnBaseMapFragmentListener} interface to handle interaction
 * events. Use the {@link BaseMapFragment#newInstance} factory method to create
 * an instance of this fragment.
 */
public class BaseMapFragment extends SupportMapFragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    /**
     * reference to Google Maps object
     */
    private SupportMapFragment supportMapFrag;
    protected GoogleMap mMap;

    private GoogleApiClient mGoogleApiClient;

    /**
     * broadcast receiver
     */
    private BroadcastReceiver mReceiver;

    public OnBaseMapFragmentListener mListener;
    private OnBaseMapFragmentPublicListener publicListener;

    protected int mapMode;

    protected LatLngBounds targetBounds;

    public BaseMapFragment() {
        //AcraExtension.mapCustomLog("BaseMap.construct", mMap);
    }

    public static BaseMapFragment newInstance(
            OnBaseMapFragmentPublicListener listener) {
        BaseMapFragment baseMapFragment = new BaseMapFragment();
        baseMapFragment.publicListener = listener;
        return baseMapFragment;
    }

    private void callBaseMapFragment(OnBaseMapFragmentPublicListener ml) {
        this.publicListener = ml;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AcraExtension.mapCustomLog("BaseMap.onCreate", mMap);

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startCreateMap(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
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
        //AcraExtension.mapCustomLog("BaseMap.onConnected", mMap);

        //obtainGooglePosition();
    }

    private void startCreateMap() {
        //AcraExtension.mapCustomLog("BaseMap.startCreateMap", mMap);

        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getActivity());
        if (status == ConnectionResult.SUCCESS) {
            supportMapFrag = this;
            setUpMapIfNeeded();
            mMap = supportMapFrag.getMap();

            //AcraExtension.mapCustomLog(
            //        "BaseMap.startCreateMap.sIStNotNull", mMap);

        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status,
                    getActivity(), 42);
            dialog.show();
        }
    }

    private void startCreateMap(Bundle savedInstanceState) {
        //AcraExtension.mapCustomLog("BaseMap.startCreateMap", mMap);

        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getActivity());
        if (status == ConnectionResult.SUCCESS) {
            supportMapFrag = this;

            setUpMapIfNeeded();
            if (savedInstanceState != null) {
                mMap = supportMapFrag.getMap();

                //AcraExtension.mapCustomLog(
                //        "BaseMap.startCreateMap.sIStNotNull", mMap);
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
            // AcraExtension
            //         .mapCustomLog("BaseMap.setUpMapIfNeeded.mapNull", mMap);
        }
        if (mMap != null) {
            // AcraExtension.mapCustomLog("BaseMap.setUpMapIfNeeded.mapNotNull",
            //        mMap);
            setUpMap();
        }

    }

    private void setUpMap() {
        //AcraExtension.mapCustomLog("BaseMap.setUpMap", mMap);

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);

        obtainMapType();

        if (targetBounds == null) obtainGooglePosition();

        sendSupportMapReadyCallback();

/*        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                animateToBounds();
            }
        });*/
    }

    private void sendSupportMapReadyCallback() {
        Fragment fragment = this;
        if (fragment instanceof OnBaseMapFragmentListener) {
            ((OnBaseMapFragmentListener) fragment).onBaseSupportMapReady();
            //Log.i("BASE_MAP", "onActivityCreated.Listener");
        } else {
            publicListener.onBaseSupportMapPublicReady();
        }
    }

    private void obtainMapType() {
        GlobalPreferences globalPreferences = new GlobalPreferences(getActivity());
        int mapType = globalPreferences.getMapType();

        //TODO replace switch-case with mapType int
        switch (mapType) {
            case 0:
                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                setupOSM();
                break;
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case 3:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case 4:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            default:
                break;
        }
    }

    private void setupOSM() {
        //AcraExtension.mapCustomLog("BaseMap.setupOSM", mMap);

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

    private void obtainGooglePosition() {
        GoogleLocProviderImpl googleLocProvider = new GoogleLocProviderImpl(getActivity()) {
            @Override
            public void onLocationReceived(Location receivedLoc) {
                animateToCurrentPosition(receivedLoc);
            }
        };
        googleLocProvider.start();
    }

    private void animateToCurrentPosition(Location location) {

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

    private void animateToBounds(int padding) {
        if (targetBounds != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(
                    targetBounds, padding));
        }
    }

    public void animateWhenMapIsReady(final int padding) {
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                animateToBounds(padding);
            }
        });
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

    /**
     * Listener interface to tell when the map is ready
     */
    public interface OnBaseMapFragmentListener {

        void onBaseSupportMapReady();
    }

    public void setTargetBounds(LatLngBounds bounds) {
        targetBounds = bounds;
    }

    @Override
    public void onConnectionSuspended(int cause) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
    }

}
