package pl.wasat.smarthma.kindle;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.amazon.geo.mapsv2.AmazonMap.OnCameraChangeListener;
import com.amazon.geo.mapsv2.AmazonMap.OnMapLongClickListener;
import com.amazon.geo.mapsv2.model.CameraPosition;
import com.amazon.geo.mapsv2.model.CircleOptions;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.LatLngBounds;
import com.amazon.geo.mapsv2.model.Polygon;
import com.amazon.geo.mapsv2.model.PolygonOptions;

import java.util.ArrayList;

import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment.OnAmznAreaPickerMapFragmentListener} interface to handle
 * interaction events. Use the {@link AmznAreaPickerMapFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class AmznAreaPickerMapFragment extends AmznBaseMapFragment implements AmznBaseMapFragment.OnBaseMapFragmentListener {

    public static final String SET_MAP_LISTENERS = "setMapListeners";
    public static final String MAP = "MAP";
    public static final String ON_ACTIVITY_CREATED = "onActivityCreated";
    private OnAmznAreaPickerMapFragmentListener mListener;

    private LatLngBounds areaBounds;
    private ArrayList<LatLng> markedPtList;
    private LatLngBounds.Builder areaBoundsBuilder;

    private Polygon areaPolygon;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment BaseMapFragment.
     */
    public static AmznAreaPickerMapFragment newInstance() {
        return new AmznAreaPickerMapFragment();
    }

    public AmznAreaPickerMapFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //AcraExtension.mapCustomLog("AreaMap.onAttach", mMap);
        try {
            mListener = (OnAmznAreaPickerMapFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAmznAreaPickerMapFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // mListener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AcraExtension.mapCustomLog("AreaMap.onCreate", mMap);
        prepareArea();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Log.i(MAP, ON_ACTIVITY_CREATED);
        //AcraExtension.mapCustomLog("AreaMap.onActivityCreated", mMap);

    }

    @Override
    public void onPause() {
        postDrawArea();
        //AcraExtension.mapCustomLog("AreaMap.onPause", mMap);

        super.onPause();
    }

    @Override
    public void onBaseSupportMapReady() {
        //AcraExtension.mapCustomLog("AreaMap.onBaseSupportMapReady", mMap);
        setMapListeners();

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
        void onAmznMapFragmentBoundsChange(LatLngBoundsExt bounds);
    }

    private void setMapListeners() {
        //AcraExtension.mapCustomLog("AreaMap.setMapListeners", mMap);
        //Log.i(MAP, SET_MAP_LISTENERS);
        mMap.setOnCameraChangeListener(new OnCameraChangeListener() {

            @Override
            public void onCameraChange(CameraPosition arg0) {
                if (markedPtList.isEmpty()) {
                    areaBounds = mMap.getProjection().getVisibleRegion().latLngBounds;
                    LatLngBoundsExt areaBoundsExt = new LatLngBoundsExt(areaBounds);
                    mListener.onAmznMapFragmentBoundsChange(areaBoundsExt);
                }
            }
        });

        mMap.setOnMapLongClickListener(new OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point) {
                addLatLng(point);
                redrawMap();
                postDrawArea();
            }
        });
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
        drawArea();
        drawPoints();
    }

    private void drawArea() {
        if (markedPtList.size() > 0) {
            PolygonOptions rectOptions = new PolygonOptions();
            rectOptions.addAll(markedPtList);
            rectOptions.strokeColor(Color.RED);
            rectOptions.strokeWidth(4);
            rectOptions.fillColor(Color.TRANSPARENT);
            rectOptions.zIndex(3);

            areaPolygon = mMap.addPolygon(rectOptions);
        }
    }

    private void drawPoints() {
        int size = 0;
        if (markedPtList != null) {
            size = markedPtList.size();
        }
        for (int i = 0; i < size; i++) {

            CircleOptions circle = new CircleOptions();
            circle.center(markedPtList.get(i));
            circle.radius(0.3);
            circle.strokeColor(Color.YELLOW);
            circle.fillColor(Color.GRAY);
            circle.strokeWidth(7);
            mMap.addCircle(circle);
        }
    }


}
