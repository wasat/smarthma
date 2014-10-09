package pl.wasat.smarthma.ui.frags.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.model.eo.Footprint;
import pl.wasat.smarthma.model.eo.Pos;
import pl.wasat.smarthma.utils.wms.TileProviderFactory;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MapSearchFragment.OnMapSearchFragmentListener} interface to handle
 * interaction events. Use the {@link MapSearchFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class MapSearchFragment extends SupportMapFragment implements
		GooglePlayServicesClient.ConnectionCallbacks, Target {

	private static final String KEY_MAP_MODE = "pl.wasat.smarthma.KEY_MAP_MODE";
	private static final int BEARING_LEVEL_VALUE = 0;

	/** reference to Google Maps object */
	private SupportMapFragment supportMapFrag;
	private GoogleMap mMap;
	private LocationClient mLocationClient;

	/** broadcast receiver */
	private BroadcastReceiver mReceiver;

	private OnMapSearchFragmentListener mListener;

	private int mapMode;

	private LatLngBounds targetBounds;

	private LatLng qLookCenter;

	private float qLookWidth;

	private float qLookHeight;

	private float qLookBearing;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param mapMode
	 * 
	 * @return A new instance of fragment MapSearchFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static MapSearchFragment newInstance(int mapMode) {
		MapSearchFragment fragment = new MapSearchFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_MAP_MODE, mapMode);
		fragment.setArguments(args);
		return fragment;
	}

	public MapSearchFragment() {
		// super();
		// checkMapFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mapMode = getArguments().getInt(KEY_MAP_MODE);
		}
	}

	/**
	 * called after the activity has been created so we can initialize the map
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		startCreateMap(savedInstanceState);

		if (mListener != null) {
			mListener.onMapReady(mapMode);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnMapSearchFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnMapSearchFragmentListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void onConnected(Bundle dataBundle) {
		// Display the connection status
		Location location = mLocationClient.getLastLocation();
		LatLng latLng = new LatLng(location.getLatitude(),
				location.getLongitude());
		CameraUpdate cameraUpdate = CameraUpdateFactory
				.newLatLngZoom(latLng, 4);
		mMap.animateCamera(cameraUpdate);
	}

	private void startCreateMap(Bundle savedInstanceState) {
		// restore selected party if any
		// NOTE: this doesn't work because if you use setRetainInstance(true) to
		// let GoogleMaps
		// retain internal state so it doesn't have to re-init so much:
		// http://stackoverflow.com/questions/11353075/how-can-i-maintain-fragment-state-when-added-to-the-back-stack
		// http://stackoverflow.com/questions/15545214/android-using-savedinstancestate-with-fragments

		int status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getActivity());
		if (status == ConnectionResult.SUCCESS) {
			supportMapFrag = this;

			setUpMapIfNeeded();
			if (savedInstanceState != null) {
				// Reincarnated activity. The obtained map is the same map
				// instance in the previous activity life cycle.
				// There is no need to reinitialize it if setRetainInstance
				// is set.
				// However, you still have to add all your listeners to it
				// later.
				mMap = supportMapFrag.getMap();

			} else {
				// First incarnation of this activity.
				// set retaininstance to minimize rotation time w/ google
				// maps
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
			// Check if we were successful in obtaining the map.
		}
		if (mMap != null) {
			setUpMap();
		}

	}

	private void setUpMap() {
		mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
		mMap.setMyLocationEnabled(true);

		mMap.getUiSettings().setZoomControlsEnabled(true);
		mMap.getUiSettings().setZoomGesturesEnabled(true);
		mMap.getUiSettings().setRotateGesturesEnabled(true);

		setupOSM();

		if (targetBounds == null) {
			animateToCurrentPosition();
		}

		mMap.setOnMapLongClickListener(new OnMapLongClickListener() {
			@Override
			public void onMapLongClick(LatLng point) {
			}
		});

		mMap.setOnCameraChangeListener(new OnCameraChangeListener() {

			@Override
			public void onCameraChange(CameraPosition arg0) {
				LatLngBounds bounds = mMap.getProjection().getVisibleRegion().latLngBounds;
				mListener.onMapSearchFragmentBoundsChange(bounds);
			}
		});

		mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
			@Override
			public void onMapLoaded() {
				animateToBounds();
			}
		});
	}

	private void setupOSM() {
		TileProvider osmTileProvider = TileProviderFactory.getOSMTileProvider();
		mMap.addTileOverlay(new TileOverlayOptions().tileProvider(
				osmTileProvider).zIndex(0));
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

	private void animateToCurrentPosition() {
		// ------------------Zooming camera to position user-----------------
		LocationManager locationManager = (LocationManager) getActivity()
				.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_LOW);

		Location location = locationManager
				.getLastKnownLocation(locationManager.getBestProvider(criteria,
						true));
		if (location != null) {

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(location.getLatitude(), location
							.getLongitude())) // Sets the center of the map to
												// location user
					.zoom(8) // Sets the zoom
					.build(); // Creates a CameraPosition from the builder
			mMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));
		}
	}

	private void buildFootprintBounds(List<LatLng> footprintPoints) {
		LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
		// String tempStr = "";
		for (int j = 0; j < footprintPoints.size(); j++) {
			boundsBuilder.include(footprintPoints.get(j));
			// tempStr = tempStr + footprintPoints.get(j).latitude + "," +
			// footprintPoints.get(j).longitude + ";"
			// + SystemUtils.LINE_SEPARATOR;
		}
		targetBounds = boundsBuilder.build();

		// writeToSDFile(tempStr);
	}

	private void writeToSDFile(String strToWrite) {

		File root = android.os.Environment.getExternalStorageDirectory();

		File dir = new File(root.getAbsolutePath() + "/download");
		dir.mkdirs();
		File file = new File(dir, "FootprintData.txt");

		try {
			FileOutputStream f = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(f);
			pw.println(strToWrite);
			pw.flush();
			pw.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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

	private void animateToBounds() {
		if (targetBounds != null) {
			mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(
					targetBounds, 50));
		}
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

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnMapSearchFragmentListener {

		public void onMapSearchFragmentBoundsChange(LatLngBounds bounds);

		public void onMapReady(int mapMode);
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param footprints
	 */
	public void showFootPrints(Footprint footprint) {

		ArrayList<LatLng> footprintPoints = extractLatLngFootprint(footprint);
		buildFootprintBounds(footprintPoints);
		drawFootprint(footprintPoints);

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

		// GroundOverlayOptions groundOverlay = new GroundOverlayOptions()
		// .image(image).positionFromBounds(targetBounds).zIndex(2)
		// .transparency((float) 0.60);

		GroundOverlayOptions groundOverlay = new GroundOverlayOptions()
				.image(image).position(qLookCenter, qLookWidth, qLookHeight)
				.bearing(qLookBearing).zIndex(2).transparency((float) 0.25);

		mMap.addGroundOverlay(groundOverlay);
		// animateToBounds();
	}

	@Override
	public void onBitmapFailed(Drawable arg0) {
		// TODO Auto-generated method stub

	}

}
