package pl.wasat.smarthma.ui.frags.common;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

import pl.wasat.smarthma.ui.frags.base.BaseMapFragment;
import pl.wasat.smarthma.ui.frags.base.BaseMapFragment.OnBaseMapFragmentListener;
import pl.wasat.smarthma.utils.io.AcraExtension;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener} interface to handle
 * interaction events. Use the {@link AreaPickerMapFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class AreaPickerMapFragment extends BaseMapFragment implements OnBaseMapFragmentListener {

	private OnAreaPickerMapFragmentListener mListener;

	private LatLngBounds areaBounds;
	private ArrayList<LatLng> markedPtList;
	private LatLngBounds.Builder areaBoundsBuilder;

	private Polygon areaPolygon;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * @return A new instance of fragment BaseMapFragment.
	 */
	public static AreaPickerMapFragment newInstance() {
		return new AreaPickerMapFragment();
	}

	public AreaPickerMapFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		AcraExtension.mapCustomLog("AreaMap.onAttach", mMap);
		try {
			mListener = (OnAreaPickerMapFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnAreaPickerMapFragmentListener");
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
		AcraExtension.mapCustomLog("AreaMap.onCreate", mMap);
		prepareArea();
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("MAP","onActivityCreated");
		AcraExtension.mapCustomLog("AreaMap.onActivityCreated", mMap);
		
	}

	@Override
	public void onPause() {
		postDrawArea();
		AcraExtension.mapCustomLog("AreaMap.onPause", mMap);
		
		super.onPause();
	}

	@Override
	public void onBaseSupportMapReady() {
		AcraExtension.mapCustomLog("AreaMap.onBaseSupportMapReady", mMap);
		setMapListeners();
		
	}
	
	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnAreaPickerMapFragmentListener {
		public void onMapFragmentBoundsChange(LatLngBounds bounds);
	}

	private void setMapListeners() {
		AcraExtension.mapCustomLog("AreaMap.setMapListeners", mMap);
		Log.i("MAP","setMapListeners");
		mMap.setOnCameraChangeListener(new OnCameraChangeListener() {

			@Override
			public void onCameraChange(CameraPosition arg0) {
				if (markedPtList.isEmpty()) {
					areaBounds = mMap.getProjection().getVisibleRegion().latLngBounds;
					mListener.onMapFragmentBoundsChange(areaBounds);
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
		markedPtList = new ArrayList<LatLng>();
		areaBoundsBuilder = new LatLngBounds.Builder();
	}

	private void postDrawArea() {
		if (!markedPtList.isEmpty()) {
			areaBounds = areaBoundsBuilder.build();
			mListener.onMapFragmentBoundsChange(areaBounds);
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
