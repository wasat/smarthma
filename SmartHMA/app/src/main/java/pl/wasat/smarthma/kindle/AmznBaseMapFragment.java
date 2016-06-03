package pl.wasat.smarthma.kindle;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.CameraUpdateFactory;
import com.amazon.geo.mapsv2.SupportMapFragment;
import com.amazon.geo.mapsv2.model.CameraPosition;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.LatLngBounds;
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

    public OnBaseMapFragmentListener mListener;
    private AmazonMap mMap;
    private LatLngBounds targetBounds;
    private SupportMapFragment supportMapFrag;
    private OnBaseMapFragmentPublicListener publicListener;
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
        }
        if (mMap != null) {
            setUpMap();
        }
    }

    private void setUpMap() {
        mMap.setMyLocationEnabled(true);
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
        AmznBaseMapFragment fragment = this;
        if (fragment instanceof OnBaseMapFragmentListener) {
            ((OnBaseMapFragmentListener) fragment).onBaseSupportMapReady();
        } else {
            publicListener.onBaseSupportMapPublicReady();
        }
    }

    private void setupOSM() {
        TileProvider osmTileProvider = AmznTileProviderFactory.getOSMTileProvider();
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
    public void onPause() {
        super.onPause();

        if (mReceiver != null) {
            LocalBroadcastManager.getInstance(getActivity())
                    .unregisterReceiver(mReceiver);
            mReceiver = null;
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
