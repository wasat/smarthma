package pl.wasat.smarthma.kindle;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

import com.amazon.geo.mapsv2.AmazonMap;
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
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.utils.loc.GoogleLocProviderImpl;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link pl.wasat.smarthma.kindle.AmznBaseMapFragment.OnBaseMapFragmentListener} interface to handle interaction
 * events. Use the {@link AmznBaseMapFragment#newInstance} factory method to create
 * an instance of this fragment.
 */
public class AmznBaseMapFragment extends SupportMapFragment {

    private SupportMapFragment supportMapFrag;
    AmazonMap mMap;
    public OnBaseMapFragmentListener mListener;
    private OnBaseMapFragmentPublicListener publicListener;
    protected LatLngBounds targetBounds;
    private BroadcastReceiver mReceiver;

    public AmznBaseMapFragment() {
    }

    public static AmznBaseMapFragment newInstance(
            OnBaseMapFragmentPublicListener listener) {
        AmznBaseMapFragment baseMapFragment = new AmznBaseMapFragment();
        baseMapFragment.publicListener = listener;
        return baseMapFragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        startCreateMap(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mReceiver != null) {
            LocalBroadcastManager.getInstance(getActivity())
                    .unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }

    private void startCreateMap(Bundle savedInstanceState) {
        int status = AmazonMapsRuntimeUtil
                .isAmazonMapsRuntimeAvailable(getActivity());
        if (status == ConnectionResult.SUCCESS) {
            supportMapFrag = this;

            setUpMapIfNeeded();
            if (savedInstanceState != null) {
                mMap = supportMapFrag.getMap();
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
        //mMap.setMapType(AmazonMap.MAP_TYPE_NONE);
        mMap.setMyLocationEnabled(true);

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);

        obtainMapType();

        if (targetBounds == null) obtainGooglePosition();

        sendSupportMapReadyCallback();

/*        setupOSM();
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
                mMap.setMapType(AmazonMap.MAP_TYPE_NONE);
                break;
            case 1:
                mMap.setMapType(AmazonMap.MAP_TYPE_NONE);
                setupOSM();
                break;
            case 2:
                mMap.setMapType(AmazonMap.MAP_TYPE_SATELLITE);
                break;
            case 3:
                mMap.setMapType(AmazonMap.MAP_TYPE_HYBRID);
                break;
            case 4:
                mMap.setMapType(AmazonMap.MAP_TYPE_TERRAIN);
                break;
            default:
                break;
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
        mMap.setOnMapLoadedCallback(new AmazonMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                animateToBounds(padding);
            }
        });
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

}
