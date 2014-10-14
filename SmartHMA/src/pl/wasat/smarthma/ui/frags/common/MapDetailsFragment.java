/*package pl.wasat.smarthma.ui.frags.common;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.model.eo.Footprint;
import pl.wasat.smarthma.model.eo.Pos;
import pl.wasat.smarthma.ui.frags.base.BaseMapFragment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolygonOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

*//**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link QuicklookMapFragment.OnMapFragmentListener} interface to handle
 * interaction events. Use the {@link MapDetailsFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 *//*
public class MapDetailsFragment extends BaseMapFragment implements Target {

	private static final int BEARING_LEVEL_VALUE = 0;

	private OnMapDetailsFragmentListener mListener;

	private LatLng qLookCenter;
	private float qLookWidth;
	private float qLookHeight;
	private float qLookBearing;

	*//**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param mapMode
	 * 
	 * @return A new instance of fragment BaseMapFragment.
	 *//*
	public static MapDetailsFragment newInstance(int mapMode) {
		MapDetailsFragment fragment = new MapDetailsFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_MAP_MODE, mapMode);
		fragment.setArguments(args);
		return fragment;
	}

	public MapDetailsFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnMapDetailsFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnMapDetailsFragmentListener");
		}
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		if (mListener != null) {
			mListener.onMapReady(mapMode);
		}
	}

	*//**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 *//*
	public interface OnMapDetailsFragmentListener {
		public void onMapReady(int mapMode);
	}

	private void buildFootprintBounds(List<LatLng> footprintPoints) {
		LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
		for (int j = 0; j < footprintPoints.size(); j++) {
			boundsBuilder.include(footprintPoints.get(j));
		}
		targetBounds = boundsBuilder.build();
	}

	*//**
	 * @param footprints
	 *//*
	public void showFootPrints(Footprint footprint) {
		ArrayList<LatLng> footprintPoints = extractLatLngFootprint(footprint);
		buildFootprintBounds(footprintPoints);
		drawFootprint(footprintPoints);
	}

	private ArrayList<LatLng> extractLatLngFootprint(Footprint footprint) {
		List<Pos> footprintPosList = footprint.getMultiExtentOf()
				.getMultiSurface().getSurfaceMembers().getPolygon()
				.getExterior().getLinearRing().getPosList();
		if (footprintPosList.isEmpty()) {
			String posStr = footprint.getMultiExtentOf().getMultiSurface()
					.getSurfaceMembers().getPolygon().getExterior()
					.getLinearRing().getPosString().getPointsString();
			footprintPosList = footprint.getMultiExtentOf().getMultiSurface()
					.getSurfaceMembers().getPolygon().getExterior()
					.getLinearRing().setPosList(posStr);
		}

		ArrayList<LatLng> footprintPoints = new ArrayList<LatLng>();
		float prevBearing = 0;

		for (int i = 0; i < footprintPosList.size() - 1; i++) {
			double currLat = footprintPosList.get(i).getLatLng().latitude;
			double currLng = footprintPosList.get(i).getLatLng().longitude;
			double nextLat = footprintPosList.get(i + 1).getLatLng().latitude;
			double nextLng = footprintPosList.get(i + 1).getLatLng().longitude;

			float[] results = new float[3];

			Location.distanceBetween(currLat, currLng, nextLat, nextLng,
					results);

			if (Math.abs(results[2] - prevBearing) >= BEARING_LEVEL_VALUE) {
				footprintPoints.add(footprintPosList.get(i).getLatLng());
			}
			prevBearing = results[2];
		}

		return footprintPoints;
	}

	private void drawFootprint(ArrayList<LatLng> footprintPoints) {
		if (footprintPoints.size() > 0) {
			PolygonOptions rectOptions = new PolygonOptions();
			rectOptions.addAll(footprintPoints);
			rectOptions.strokeColor(Color.HSVToColor(new float[] {
					179 + (9 * 4), 1, 1 })); // change 4 to variable;
			rectOptions.strokeWidth(4);
			rectOptions.geodesic(true);
			rectOptions.zIndex(1);
			mMap.addPolygon(rectOptions);
		}
	}

	private void calcQuickLookParams(Footprint footprint,
			ArrayList<LatLng> footprintPoints) {
		double oneLat = footprintPoints.get(0).latitude;
		double oneLng = footprintPoints.get(0).longitude;
		double twoLat = footprintPoints.get(1).latitude;
		double twoLng = footprintPoints.get(1).longitude;
		double fourLat = footprintPoints.get(3).latitude;
		double fourLng = footprintPoints.get(3).longitude;
		float[] results = new float[3];

		qLookCenter = footprint.getCenterOf().getPoint().getPos().getLatLng();

		Location.distanceBetween(oneLat, oneLng, twoLat, twoLng, results);
		qLookHeight = results[0];

		Location.distanceBetween(oneLat, oneLng, fourLat, fourLng, results);
		qLookWidth = results[0];
		qLookBearing = ((results[1] + results[2]) / 2) - 90;

	}

	public void showQuicklookOnMap(String url, Footprint footprint) {

		ArrayList<LatLng> footprintPoints = extractLatLngFootprint(footprint);

		buildFootprintBounds(footprintPoints);

		calcQuickLookParams(footprint, footprintPoints);
		//drawFootprint(footprintPoints);

		Target quicklookTarget = this;
		Picasso.with(getActivity()).load(url).into(quicklookTarget);
	}

	@Override
	public void onPrepareLoad(Drawable arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onBitmapLoaded(Bitmap bitmap, LoadedFrom arg1) {
		BitmapDescriptor image = BitmapDescriptorFactory.fromBitmap(bitmap);

		GroundOverlayOptions groundOverlay = new GroundOverlayOptions()
				.image(image).position(qLookCenter, qLookWidth, qLookHeight)
				.bearing(qLookBearing).zIndex(2).transparency((float) 0.25);

		mMap.addGroundOverlay(groundOverlay);
	}

	@Override
	public void onBitmapFailed(Drawable arg0) {
		// TODO Auto-generated method stub

	}

}
*/