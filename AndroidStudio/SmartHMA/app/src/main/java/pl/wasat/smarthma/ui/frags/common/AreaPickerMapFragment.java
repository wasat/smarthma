package pl.wasat.smarthma.ui.frags.common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polygon;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.base.BaseMapFragment;
import pl.wasat.smarthma.ui.frags.base.BaseMapFragment.OnBaseMapFragmentListener;
import pl.wasat.smarthma.utils.draw.MapDrawings;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener} interface to handle
 * interaction events. Use the {@link AreaPickerMapFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class AreaPickerMapFragment extends BaseMapFragment implements OnBaseMapFragmentListener {

    private OnAreaPickerMapFragmentListener mListener;

    private LatLngBounds areaBounds;
    private ArrayList<LatLng> markedPtList;
    private LatLngBounds.Builder areaBoundsBuilder;

    private Polygon areaPolygon;

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
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

        targetBounds = boundsBuilder.build();
    }

    private void prepareArea() {
        markedPtList = new ArrayList<>();
        areaBoundsBuilder = new LatLngBounds.Builder();
    }

    @Override
    public void onBaseSupportMapReady() {
        animateWhenMapIsReady(0);
        setMapListeners();
    }

    private void setMapListeners() {
        mMap.setOnCameraChangeListener(new OnCameraChangeListener() {

            @Override
            public void onCameraChange(CameraPosition arg0) {
                if (markedPtList.isEmpty()) {
                    areaBounds = mMap.getProjection().getVisibleRegion().latLngBounds;
                    LatLngBoundsExt areaBoundsExt = new LatLngBoundsExt(areaBounds);
                    mListener.onMapFragmentBoundsChange(areaBoundsExt);
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
        mMap.addCircle(mapDrawings.drawPoints(markedPtList));
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
    }
}
