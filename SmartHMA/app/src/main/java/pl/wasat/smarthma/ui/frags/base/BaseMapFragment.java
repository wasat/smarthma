package pl.wasat.smarthma.ui.frags.base;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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

    public OnBaseMapFragmentListener mListener;
    protected GoogleMap mMap;
    protected int mapMode;
    protected LatLngBounds targetBounds;
    /**
     * reference to Google Maps object
     */
    private SupportMapFragment supportMapFrag;
    private GoogleApiClient mGoogleApiClient;
    /**
     * broadcast receiver
     */
    private BroadcastReceiver mReceiver;
    private OnBaseMapFragmentPublicListener publicListener;

    public BaseMapFragment() {
    }

    public static BaseMapFragment newInstance(
            OnBaseMapFragmentPublicListener listener) {
        BaseMapFragment baseMapFragment = new BaseMapFragment();
        baseMapFragment.publicListener = listener;
        return baseMapFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startCreateMap(savedInstanceState);
    }

    private void startCreateMap(Bundle savedInstanceState) {
        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getActivity());
        if (status == ConnectionResult.SUCCESS) {
            supportMapFrag = this;

            setUpMapIfNeeded();
            if (savedInstanceState != null) {
                mMap = supportMapFrag.getMap();
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
        }
        if (mMap != null) {
            setUpMap();
        }
    }

    private void setUpMap() {
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);

        obtainMapType();

        if (targetBounds == null) obtainGooglePosition();

        sendSupportMapReadyCallback();
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

    private void obtainGooglePosition() {
        GoogleLocProviderImpl googleLocProvider = new GoogleLocProviderImpl(getActivity()) {
            @Override
            public void onLocationReceived(Location receivedLoc) {
                animateToCurrentPosition(receivedLoc);
            }
        };
        googleLocProvider.start();
    }

    private void sendSupportMapReadyCallback() {
        Fragment fragment = this;
        if (fragment instanceof OnBaseMapFragmentListener) {
            ((OnBaseMapFragmentListener) fragment).onBaseSupportMapReady();
        } else {
            publicListener.onBaseSupportMapPublicReady();
        }
    }

    private void setupOSM() {
        TileProvider osmTileProvider = TileProviderFactory.getOSMTileProvider();
        mMap.addTileOverlay(new TileOverlayOptions().tileProvider(
                osmTileProvider).zIndex(0));
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

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();

    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle dataBundle) {
    }

    @Override
    public void onConnectionSuspended(int cause) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
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

    public void animateWhenMapIsReady(final int padding) {
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                animateToBounds(padding);
            }
        });
    }

    private void animateToBounds(int padding) {
        if (targetBounds != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(
                    targetBounds, padding));
        }
    }

    public void setTargetBounds(LatLngBounds bounds) {
        targetBounds = bounds;
    }

    /**
     * Listener interface to tell when the map is ready
     */
    public interface OnBaseMapFragmentListener {

        void onBaseSupportMapReady();
    }
}
