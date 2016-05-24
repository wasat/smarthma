package pl.wasat.smarthma.ui.frags.common;

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
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.enums.Opts;
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.utils.draw.MapDrawings;
import pl.wasat.smarthma.utils.geo.SphericalUtil;
import pl.wasat.smarthma.utils.loc.GoogleLocProviderImpl;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.obj.LatLngExt;
import pl.wasat.smarthma.utils.wms.TileProviderFactory;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener} interface to handle
 * interaction events. Use the {@link AreaPickerMapFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class AreaPickerMapFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, PlaceSelectionListener {

    private OnAreaPickerMapFragmentListener mListener;

    private ImageButton imgBtnArea;

    private LatLngBounds areaBounds;
    private ArrayList<LatLng> markedPtList;
    private LatLngBounds.Builder areaBoundsBuilder;

    private Polygon areaPolygon;

    protected GoogleMap mMap;
    //protected LatLngBounds targetBounds;

    private SupportMapFragment supportMapFrag;
    private GoogleApiClient mGoogleApiClient;

    private int areaDrawType;
    private ArrayList<Circle> circleList;

    public AreaPickerMapFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment AreaPickerMapFragment.
     */
    public static AreaPickerMapFragment newInstance() {
        return new AreaPickerMapFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnAreaPickerMapFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnAreaPickerMapFragmentListener.class.getSimpleName());
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

/*       PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                fm.findFragmentById(R.id.autocomplete_fragment);*/

        PlaceAutocompleteFragment autocompleteFragment = new PlaceAutocompleteFragment();
        android.app.FragmentManager fm = getActivity().getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.autocomplete_fragment, autocompleteFragment)
                .commit();
        autocompleteFragment.setOnPlaceSelectedListener(this);

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

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();

        obtainBoundsFromShared();
        prepareArea();
    }


    @Override
    public void onPause() {
        postDrawArea();
        super.onPause();
    }

    @Override
    public void onPlaceSelected(Place place) {
        if (place.getViewport() != null) {
            areaBounds = place.getViewport();
            animateToBounds(30);
        } else {
            animateToCurrentPosition(place.getLatLng());
        }
    }

    @Override
    public void onError(Status status) {
        Toast.makeText(getActivity(), "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onConnected(Bundle dataBundle) {
    }

    @Override
    public void onConnectionSuspended(int cause) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();
        initCallbacksWhenMapIsReady(0);
        setOnLongPressMapListener();
    }


    private void startCreateMap(Bundle savedInstanceState) {
        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getActivity());
        if (status == ConnectionResult.SUCCESS) {
            supportMapFrag = new SupportMapFragment();

            setUpMapIfNeeded();
            if (savedInstanceState != null) {
                supportMapFrag.getMapAsync(this);
            }
        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status,
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

        if (areaBounds == null) obtainGooglePosition();
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

    private void initMapObjects() {
        clearAreaObjs();
        SharedPrefs sharedPrefs = new SharedPrefs(getActivity());
        areaDrawType = sharedPrefs.getAreaType();
        chooseAndShowAreaIcon();
        mListener.onMapFragmentAreaInputChange(areaDrawType);
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
            mListener.onMapFragmentAreaInputChange(areaDrawType);
        } else {
            imgBtnArea.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_area_point_radius));
            areaDrawType = Opts.AREA_POLYGON;
            mListener.onMapFragmentAreaInputChange(areaDrawType);
            LatLngBoundsExt areaBoundsExt = new LatLngBoundsExt(areaBounds);
            mListener.onMapFragmentBoundsChange(areaBoundsExt);
        }
    }


    private void setupOSM() {
        TileProvider osmTileProvider = TileProviderFactory.getOSMTileProvider();
        mMap.addTileOverlay(new TileOverlayOptions().tileProvider(
                osmTileProvider).zIndex(0));
    }

    private void animateToCurrentPosition(Location location) {
        if (location != null) {
            animateToCurrentPosition(location.getLatitude(), location.getLongitude());
        }
    }

    private void animateToCurrentPosition(LatLng latLng) {
        if (latLng != null) {
            animateToCurrentPosition(latLng.latitude, latLng.longitude);
        }
    }

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

    public void initCallbacksWhenMapIsReady(final int padding) {
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                animateToBounds(padding);
                setMapListeners();
            }
        });
    }

    private void animateToBounds(int padding) {
        if (areaBounds != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(
                    areaBounds, padding));
        }
    }

    private void postDrawArea() {
        if (!markedPtList.isEmpty()) {
            areaBounds = areaBoundsBuilder.build();
            LatLngBoundsExt areaBoundsExt = new LatLngBoundsExt(areaBounds);
            mListener.onMapFragmentBoundsChange(areaBoundsExt);
        }
    }

    private void obtainBoundsFromShared() {
        SharedPrefs sharedPrefs = new SharedPrefs(getActivity());
        float[] bbox = sharedPrefs.getBboxPrefs();

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        boundsBuilder.include(new LatLng(bbox[1], bbox[0]));
        boundsBuilder.include(new LatLng(bbox[3], bbox[2]));

        areaBounds = boundsBuilder.build();
    }

    private void prepareArea() {
        markedPtList = new ArrayList<>();
        areaBoundsBuilder = new LatLngBounds.Builder();
    }

    private void setMapListeners() {
        mMap.setOnCameraChangeListener(new OnCameraChangeListener() {

            @Override
            public void onCameraChange(CameraPosition arg0) {
                if (markedPtList.isEmpty()) {
                    areaBounds = mMap.getProjection().getVisibleRegion().latLngBounds;
                    LatLngBoundsExt areaBoundsExt = new LatLngBoundsExt(areaBounds);
                    if (areaDrawType == Opts.AREA_POLYGON)
                        mListener.onMapFragmentBoundsChange(areaBoundsExt);
                }
            }
        });
    }

    private void setOnLongPressMapListener() {
        mMap.setOnMapLongClickListener(new OnMapLongClickListener() {
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
        MapDrawings mapDrawings = new MapDrawings();
        areaPolygon = mMap.addPolygon(mapDrawings.drawArea(markedPtList));
        mMap = mapDrawings.drawPoints(markedPtList, mMap);
    }

    private void drawPointAndRadius(LatLng point) {
        MapDrawings mapDrawings = new MapDrawings();
        addLatLng(point);
        if (markedPtList.size() == 1) {
            mapDrawings.removeCircles(circleList);
            mListener.onMapFragmentPointAndRadiusSend(new LatLngExt(point), 0);
        } else if (markedPtList.size() == 2) {
            circleList.add(mMap.addCircle(mapDrawings.drawPointAndRadiusArea(markedPtList)));
            mListener.onMapFragmentPointAndRadiusSend(new LatLngExt(markedPtList.get(0)), calcRadius());
            areaBounds = convertPtAndRadiusToBounds(markedPtList.get(0), calcRadius()).googleLatLngBounds;
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
        MapDrawings mapDrawings = new MapDrawings();
        mapDrawings.removeCircles(circleList);
        mapDrawings.removePolygon(areaPolygon);
        markedPtList = new ArrayList<>();
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
    public interface OnAreaPickerMapFragmentListener {
        void onMapFragmentBoundsChange(LatLngBoundsExt bounds);

        void onMapFragmentAreaInputChange(int areaType);

        void onMapFragmentPointAndRadiusSend(LatLngExt center, float radius);
    }
}
