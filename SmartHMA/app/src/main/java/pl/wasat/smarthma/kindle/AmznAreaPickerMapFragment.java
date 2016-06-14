/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wasat.smarthma.kindle;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.CameraUpdateFactory;
import com.amazon.geo.mapsv2.OnMapReadyCallback;
import com.amazon.geo.mapsv2.SupportMapFragment;
import com.amazon.geo.mapsv2.model.CameraPosition;
import com.amazon.geo.mapsv2.model.Circle;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.LatLngBounds;
import com.amazon.geo.mapsv2.model.Polygon;
import com.amazon.geo.mapsv2.model.TileOverlayOptions;
import com.amazon.geo.mapsv2.model.TileProvider;
import com.amazon.geo.mapsv2.util.AmazonMapsRuntimeUtil;
import com.amazon.geo.mapsv2.util.ConnectionResult;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.enums.Opts;
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.utils.geo.SphericalUtil;
import pl.wasat.smarthma.utils.loc.GoogleLocProviderImpl;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.obj.LatLngExt;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment.OnAmznAreaPickerMapFragmentListener} interface to handle
 * interaction events. Use the {@link AmznAreaPickerMapFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class AmznAreaPickerMapFragment extends Fragment implements
        OnMapReadyCallback {

    private static final int PERMISSIONS_REQUEST_FINE_LOC = 0x11;
    private OnAmznAreaPickerMapFragmentListener mListener;

    private ImageButton imgBtnArea;

    private LatLngBounds areaBounds;
    private ArrayList<LatLng> markedPtList;
    private LatLngBounds.Builder areaBoundsBuilder;

    private AmazonMap mMap;
    private Polygon areaPolygon;
    private SupportMapFragment supportMapFrag;
    private int areaDrawType;
    private ArrayList<Circle> circleList;

    /**
     * Instantiates a new Amzn area picker map fragment.
     */
    public AmznAreaPickerMapFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment BaseMapFragment.
     */
    public static AmznAreaPickerMapFragment newInstance() {
        return new AmznAreaPickerMapFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnAmznAreaPickerMapFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnAmznAreaPickerMapFragmentListener.class.getSimpleName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map_area_picker,
                container, false);

        FragmentManager childFm = getChildFragmentManager();

        supportMapFrag = (SupportMapFragment) childFm
                .findFragmentById(R.id.frag_support_map_base);
        if (supportMapFrag == null) {
            startCreateMap(savedInstanceState);
            childFm.beginTransaction()
                    .replace(R.id.frag_support_map_base, supportMapFrag)
                    .commit();
        } else {
            startCreateMap(savedInstanceState);
        }

/*        PlaceAutocompleteFragment autocompleteFragment = new PlaceAutocompleteFragment();
        android.app.FragmentManager fm = getActivity().getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.autocomplete_fragment, autocompleteFragment)
                .commit();
        autocompleteFragment.setOnPlaceSelectedListener(this);*/

        imgBtnArea = (ImageButton) rootView.findViewById(R.id.frag_map_area_btn_ic_draw_area);
        imgBtnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAreaTypeAndIcon(imgBtnArea);
            }
        });
        initMapObjects();

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        obtainBoundsFromShared();
        prepareArea();
    }

    @Override
    public void onPause() {
        postDrawArea();
        super.onPause();
    }

/*    @Override
    public void onPlaceSelected(Place place) {
        if (place.getViewport() != null) {
            areaBounds = place.getViewport();
            animateToBounds(30);
        } else {
            animateToCurrentPosition(place.getLatLng());
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(AmazonMap amazonMap) {
        mMap = amazonMap;
        setUpMap();
        initCallbacksWhenMapIsReady(0);
        setOnLongPressMapListener();
    }

    private void startCreateMap(Bundle savedInstanceState) {
        int status = AmazonMapsRuntimeUtil
                .isAmazonMapsRuntimeAvailable(getActivity());
        if (status == ConnectionResult.SUCCESS) {
            supportMapFrag = new SupportMapFragment();

            setUpMapIfNeeded();
            if (savedInstanceState != null) {
                supportMapFrag.getMapAsync(this);
            }
        } else {
            Dialog dialog = AmazonMapsRuntimeUtil.getErrorDialog(status,
                    getActivity(), 42);
            dialog.show();
        }
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            supportMapFrag.getMapAsync(this);
        }
        if (mMap != null) {
            setUpMap();
        }
    }

    private void setUpMap() {
        checkLocationPermission();
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);

        obtainMapType();

        if (areaBounds == null) obtainGooglePosition();
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
        TileProvider osmTileProvider = AmznTileProviderFactory.getOSMTileProvider();
        mMap.addTileOverlay(new TileOverlayOptions().tileProvider(
                osmTileProvider).zIndex(0));
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

    private void obtainBoundsFromShared() {
        SharedPrefs sharedPrefs = new SharedPrefs(getActivity());
        float[] bbox = sharedPrefs.getBboxPrefs();

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        boundsBuilder.include(new LatLng(bbox[1], bbox[0]));
        boundsBuilder.include(new LatLng(bbox[3], bbox[2]));

        areaBounds = boundsBuilder.build();
    }

    private void initMapObjects() {
        clearAreaObjs();
        SharedPrefs sharedPrefs = new SharedPrefs(getActivity());
        areaDrawType = sharedPrefs.getAreaType();
        chooseAndShowAreaIcon();
        mListener.onAmznMapFragmentAreaInputChange(areaDrawType);
        circleList = new ArrayList<>();
    }

    private void chooseAndShowAreaIcon() {
        switch (areaDrawType) {
            case Opts.AREA_POLYGON:
                imgBtnArea.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_area_point_radius));
                break;
            case Opts.AREA_PT_RADIUS:
                imgBtnArea.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_area_bounds));
                break;
            default:
                break;
        }
    }

    private void changeAreaTypeAndIcon(ImageButton imgBtnArea) {
        clearAreaObjs();
        Drawable.ConstantState btnState = imgBtnArea.getDrawable().getConstantState();
        if (btnState.equals(ContextCompat.getDrawable(getActivity(), R.drawable.ic_area_point_radius).getConstantState())) {
            imgBtnArea.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_area_bounds));
            areaDrawType = Opts.AREA_PT_RADIUS;
            mListener.onAmznMapFragmentAreaInputChange(areaDrawType);
        } else {
            imgBtnArea.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_area_point_radius));
            areaDrawType = Opts.AREA_POLYGON;
            mListener.onAmznMapFragmentAreaInputChange(areaDrawType);
            LatLngBoundsExt areaBoundsExt = new LatLngBoundsExt(areaBounds);
            mListener.onAmznMapFragmentBoundsChange(areaBoundsExt);
        }
    }

    private void animateToCurrentPosition(Location location) {
        if (location != null) {
            animateToCurrentPosition(location.getLatitude(), location.getLongitude());
        }
    }

/*    private void animateToCurrentPosition(com.google.android.gms.maps.model.LatLng latLng) {
        if (latLng != null) {
            animateToCurrentPosition(latLng.latitude, latLng.longitude);
        }
    }*/

    private void animateToCurrentPosition(double lat, double lon) {
        int mapZoom;
        if (lat == 0 && lon == 0) {
            mapZoom = 0;
        } else {
            mapZoom = 10;
        }

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(lat, lon)) // Sets the center of the map to location user
                .zoom(mapZoom) // Sets the zoom
                .build(); // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    private void animateToBounds(int padding) {
        if (areaBounds != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(
                    areaBounds, padding));
        }
    }

    private void initCallbacksWhenMapIsReady(final int padding) {
        mMap.setOnMapLoadedCallback(new AmazonMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                animateToBounds(padding);
                setMapListeners();
            }
        });
    }

    private void setMapListeners() {
        mMap.setOnCameraChangeListener(new AmazonMap.OnCameraChangeListener() {

            @Override
            public void onCameraChange(CameraPosition arg0) {
                if (markedPtList.isEmpty()) {
                    areaBounds = mMap.getProjection().getVisibleRegion().latLngBounds;
                    LatLngBoundsExt areaBoundsExt = new LatLngBoundsExt(areaBounds);
                    if (areaDrawType == Opts.AREA_POLYGON)
                        mListener.onAmznMapFragmentBoundsChange(areaBoundsExt);
                }
            }
        });
    }

    private void setOnLongPressMapListener() {
        mMap.setOnMapLongClickListener(new AmazonMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng point) {
                if (areaDrawType == Opts.AREA_POLYGON) {
                    drawPolygonArea(point);
                } else {
                    drawPointAndRadius(point);
                }
            }
        });
    }

    private void drawPolygonArea(LatLng point) {
        addLatLng(point);
        redrawMap();
        postDrawArea();
    }

    private void addLatLng(LatLng pressPt) {
        if (pressPt != null) {
            markedPtList.add(pressPt);
            areaBoundsBuilder.include(pressPt);
        }
    }

    private void redrawMap() {
        if (areaPolygon != null) {
            areaPolygon.remove();
        }
        AmznMapDrawings mapDrawings = new AmznMapDrawings();
        areaPolygon = mMap.addPolygon(mapDrawings.drawArea(markedPtList));
        mMap = mapDrawings.drawPoints(markedPtList, mMap);
    }

    private void drawPointAndRadius(LatLng point) {
        AmznMapDrawings mapDrawings = new AmznMapDrawings();
        addLatLng(point);
        if (markedPtList.size() == 1) {
            mapDrawings.removeCircles(circleList);
            mListener.onAmznMapFragmentPointAndRadiusSend(new LatLngExt(point), 0);
        } else if (markedPtList.size() == 2) {
            circleList.add(mMap.addCircle(mapDrawings.drawPointAndRadiusArea(markedPtList)));
            mListener.onAmznMapFragmentPointAndRadiusSend(new LatLngExt(markedPtList.get(0)), calcRadius());
            areaBounds = convertPtAndRadiusToBounds(markedPtList.get(0), calcRadius()).amznLatLngBounds;
            markedPtList = new ArrayList<>();
        } else {
            clearAreaObjs();
        }
        circleList.add(mMap.addCircle(mapDrawings.drawPoint(point)));
    }

    private float calcRadius() {
        LatLng center = markedPtList.get(0);
        LatLng secPt = markedPtList.get(1);
        float[] res = new float[3];
        Location.distanceBetween(center.latitude, center.longitude, secPt.latitude, secPt.longitude, res);
        return res[0];
    }

    private LatLngBoundsExt convertPtAndRadiusToBounds(LatLng center, double radius) {
        LatLngExt southwest = SphericalUtil.computeOffset(new LatLngExt(center), radius * Math.sqrt(2.0), 225);
        LatLngExt northeast = SphericalUtil.computeOffset(new LatLngExt(center), radius * Math.sqrt(2.0), 45);
        return new LatLngBoundsExt(southwest, northeast);
    }

    private void clearAreaObjs() {
        AmznMapDrawings mapDrawings = new AmznMapDrawings();
        mapDrawings.removeCircles(circleList);
        mapDrawings.removePolygon(areaPolygon);
        markedPtList = new ArrayList<>();
    }

    private void prepareArea() {
        markedPtList = new ArrayList<>();
        areaBoundsBuilder = new LatLngBounds.Builder();
    }

    private void postDrawArea() {
        if (!markedPtList.isEmpty()) {
            areaBounds = areaBoundsBuilder.build();
            LatLngBoundsExt areaBoundsExt = new LatLngBoundsExt(areaBounds);
            mListener.onAmznMapFragmentBoundsChange(areaBoundsExt);
        }
    }

    private void checkLocationPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_FINE_LOC);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_FINE_LOC: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAmznAreaPickerMapFragmentListener {
        /**
         * On amzn map fragment bounds change.
         *
         * @param bounds the bounds
         */
        void onAmznMapFragmentBoundsChange(LatLngBoundsExt bounds);

        /**
         * On amzn map fragment area input change.
         *
         * @param areaType the area type
         */
        void onAmznMapFragmentAreaInputChange(int areaType);

        /**
         * On amzn map fragment point and radius send.
         *
         * @param center the center
         * @param radius the radius
         */
        void onAmznMapFragmentPointAndRadiusSend(LatLngExt center, float radius);

    }

}
