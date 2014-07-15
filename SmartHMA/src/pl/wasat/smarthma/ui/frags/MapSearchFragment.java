package pl.wasat.smarthma.ui.frags;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.model.eo.Pos;
import pl.wasat.smarthma.utils.wms.TileProviderFactory;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MapSearchFragment.OnMapSearchFragmentListener} interface to handle
 * interaction events. Use the {@link MapSearchFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class MapSearchFragment extends SupportMapFragment implements
		GooglePlayServicesClient.ConnectionCallbacks {
	// private static final String ARG_PARAM1 = "param1";
	// private static final String ARG_PARAM2 = "param2";
	private static final String MAP_FRAGMENT_TAG = "MapSearchFragment";

	// TODO: Rename and change types of parameters
	// private String mParam1;
	// private String mParam2;

	/** reference to Google Maps object */
	private SupportMapFragment supportMapFrag;
	private GoogleMap mMap;
	private LocationClient mLocationClient;

	/** broadcast receiver */
	private BroadcastReceiver mReceiver;

	private OnMapSearchFragmentListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment MapSearchFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static MapSearchFragment newInstance(String param1, String param2) {
		MapSearchFragment fragment = new MapSearchFragment();
		// Bundle args = new Bundle();
		// args.putString(ARG_PARAM1, param1);
		// args.putString(ARG_PARAM2, param2);
		// fragment.setArguments(args);
		return fragment;
	}

	public MapSearchFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			// mParam1 = getArguments().getString(ARG_PARAM1);
			// mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		// Inflate the layout for this fragment
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

	}

	/**
	 * called after the activity has been created so we can initialize the map
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		startCreateMap(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnMapSearchFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
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
				.newLatLngZoom(latLng, 7);
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

			// GoogleMap mapFragment = this.getMap();
			if (supportMapFrag == null) {
				// To programmatically add the map, we first create a
				// SupportMapFragment.
				supportMapFrag = SupportMapFragment.newInstance();

				// Then we add it using a FragmentTransaction.
				FragmentTransaction fragmentTransaction = getActivity()
						.getSupportFragmentManager().beginTransaction();
				fragmentTransaction.add(android.R.id.content, supportMapFrag,
						MAP_FRAGMENT_TAG);
				fragmentTransaction.commit();
			} else {

				setUpMapIfNeeded();
				if (savedInstanceState != null) {
					// Reincarnated activity. The obtained map is the same map
					// instance in the previous activity life cycle.
					// There is no need to reinitialize it if setRetainInstance
					// is set.
					// However, you still have to add all your listeners to it
					// later.
					mMap = getMap();

				} else {
					// First incarnation of this activity.
					// set retaininstance to minimize rotation time w/ google
					// maps
					// this.setRetainInstance(true);
				}
				// setUpMapIfNeeded();
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
		} else {
		}

	}

	private void setUpMap() {
		mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
		mMap.setMyLocationEnabled(true);

		mMap.getUiSettings().setZoomControlsEnabled(true);
		mMap.getUiSettings().setZoomGesturesEnabled(true);
		// mMap.getUiSettings().setMyLocationButtonEnabled(true);
		mMap.getUiSettings().setRotateGesturesEnabled(true);

		setupOSM();

		// CameraUpdate cameraUpdate =
		// CameraUpdateFactory.newLatLngZoom(Const.CENTER_LAYER, 7);
		// mMap.animateCamera(cameraUpdate);

		// ------------------Zooming camera to position user-----------------
		LocationManager locationManager = (LocationManager) getActivity()
				.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_LOW);

		Location location = locationManager
				.getLastKnownLocation(locationManager.getBestProvider(criteria,
						true));
		if (location != null) {
			// mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
			// location.getLatitude(), location.getLongitude()), 13));

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(location.getLatitude(), location
							.getLongitude())) // Sets the center of the map to
												// location user
					.zoom(12) // Sets the zoom
					.build(); // Creates a CameraPosition from the builder
			mMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));

		}

		// ---------------Zooming camera to position user--------

		mMap.setOnMapLongClickListener(new OnMapLongClickListener() {
			@Override
			public void onMapLongClick(LatLng point) {
			}
		});

		mMap.setOnCameraChangeListener(new OnCameraChangeListener() {

			@Override
			public void onCameraChange(CameraPosition arg0) {
				// Move camera.
				// Remove listener to prevent position reset on camera move.
				LatLngBounds bounds = mMap.getProjection().getVisibleRegion().latLngBounds;
				mListener.onMapSearchFragmentBoundsChange(bounds);
			}
		});
	}

	private void setupOSM() {
		TileProvider osmTileProvider = TileProviderFactory.getOSMTileProvider();
		mMap.addTileOverlay(new TileOverlayOptions()
				.tileProvider(osmTileProvider).zIndex(0));
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

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onMapSearchFragmentInteraction(uri);
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
		// TODO: Update argument type and name
		public void onMapSearchFragmentInteraction(Uri uri);

		public void onMapSearchFragmentBoundsChange(LatLngBounds bounds);
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param footPrints
	 */
	public void showFootPrints(ArrayList<List<Pos>> footPrints) {

		for (int i = 0; i < footPrints.size(); i++) {
			ArrayList<LatLng> footPrintPoints = new ArrayList<LatLng>();

			for (int j = 0; j < footPrints.get(i).size(); j++) {
				String posStr = footPrints.get(i).get(j).get__text();
				LatLng ftPt = new LatLng(Double.valueOf(posStr.split(" ")[0]),
						Double.valueOf(posStr.split(" ")[1]));
				footPrintPoints.add(ftPt);
			}

			if (footPrintPoints.size() > 0) {
				PolygonOptions rectOptions = new PolygonOptions();
				rectOptions.addAll(footPrintPoints);
				rectOptions.strokeColor(Color.BLUE);
				rectOptions.strokeWidth(4);
				rectOptions.fillColor(Color.LTGRAY);
				rectOptions.geodesic(true);
				rectOptions.zIndex(1);
				mMap.addPolygon(rectOptions);
			}
		}

	}

}
