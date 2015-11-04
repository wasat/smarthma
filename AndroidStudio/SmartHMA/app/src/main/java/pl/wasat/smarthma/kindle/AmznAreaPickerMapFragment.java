package pl.wasat.smarthma.kindle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.model.CameraPosition;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.LatLngBounds;
import com.amazon.geo.mapsv2.model.Polygon;

import java.util.ArrayList;

import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment.OnAmznAreaPickerMapFragmentListener} interface to handle
 * interaction events. Use the {@link AmznAreaPickerMapFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class AmznAreaPickerMapFragment extends AmznBaseMapFragment implements AmznBaseMapFragment.OnBaseMapFragmentListener {

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
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
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
        obtainBoundsFromShared();
        prepareArea();

    }


    @Override
    public void onPause() {
        postDrawArea();
        super.onPause();
    }

    @Override
    public void onBaseSupportMapReady() {
        animateWhenMapIsReady(0);
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
        mMap.setOnCameraChangeListener(new AmazonMap.OnCameraChangeListener() {

            @Override
            public void onCameraChange(CameraPosition arg0) {
                if (markedPtList.isEmpty()) {
                    areaBounds = mMap.getProjection().getVisibleRegion().latLngBounds;
                    LatLngBoundsExt areaBoundsExt = new LatLngBoundsExt(areaBounds);
                    mListener.onAmznMapFragmentBoundsChange(areaBoundsExt);
                }
            }
        });

        mMap.setOnMapLongClickListener(new AmazonMap.OnMapLongClickListener() {

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
        AmznMapDrawings mapDrawings = new AmznMapDrawings();
        areaPolygon = mMap.addPolygon(mapDrawings.drawArea(markedPtList));
        mMap.addCircle(mapDrawings.drawPoints(markedPtList));
        //drawArea();
        // drawPoints();
    }


    private void obtainBoundsFromShared() {
        SharedPrefs sharedPrefs = new SharedPrefs(getActivity());
        float[] bbox = sharedPrefs.getBboxPrefs();

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        boundsBuilder.include(new LatLng(bbox[1], bbox[0]));
        boundsBuilder.include(new LatLng(bbox[3], bbox[2]));

        targetBounds = boundsBuilder.build();
    }

}
