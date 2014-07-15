package pl.wasat.smarthma.ui.frags.search;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.feed.Entry;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLngBounds;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link CollectionItemRightFragment.OnCollectionItemRightFragmentListener}
 * interface to handle interaction events. Use the
 * {@link CollectionItemRightFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class CollectionItemRightFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String KEY_COLLECTION_ENTRY = "pl.wasat.smarthma.COLLECTION_ENTRY";
	private static final String KEY_COLLECTION_NAME = "pl.wasat.smarthma.COLLECTION_NAME";
	private static final String KEY_COLLECTION_DATES = "pl.wasat.smarthma.COLLECTION_DATES";

	// TODO: Rename and change types of parameters
	private Entry paramCollEntry;
	private String paramCollName;
	private String paramCollDates;

	private TextView tvAreaSWLat;
	private TextView tvAreaSWLon;
	private TextView tvAreaNELat;
	private TextView tvAreaNELon;
	private Button buttonSearchProd;

	LatLngBounds geoBounds = null;

	private OnCollectionItemRightFragmentListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param collectionName
	 *            Parameter 1.
	 * @param collectionDates
	 *            Parameter 2.
	 * @return A new instance of fragment CollectionItemRightFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static CollectionItemRightFragment newInstance(
			Entry collectionEntry, String collectionName, String collectionDates) {
		CollectionItemRightFragment fragment = new CollectionItemRightFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_COLLECTION_ENTRY, collectionEntry);
		args.putString(KEY_COLLECTION_NAME, collectionName);
		args.putString(KEY_COLLECTION_DATES, collectionDates);
		fragment.setArguments(args);
		return fragment;
	}

	public CollectionItemRightFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			paramCollEntry = (Entry) getArguments().getSerializable(
					KEY_COLLECTION_ENTRY);
			paramCollName = getArguments().getString(KEY_COLLECTION_NAME);
			paramCollDates = getArguments().getString(KEY_COLLECTION_DATES);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_collection_item_right, container, false);

		((TextView) rootView
				.findViewById(R.id.coll_item_right_frag_tv_coll_name))
				.setText(paramCollName);
		((TextView) rootView
				.findViewById(R.id.coll_item_right_frag_tv_coll_dates))
				.setText(paramCollDates);

		tvAreaSWLat = (TextView) rootView
				.findViewById(R.id.coll_item_right_frag_tv_area_sw_lat);
		tvAreaSWLon = (TextView) rootView
				.findViewById(R.id.coll_item_right_frag_tv_area_sw_lon);
		tvAreaNELat = (TextView) rootView
				.findViewById(R.id.coll_item_right_frag_tv_area_ne_lat);
		tvAreaNELon = (TextView) rootView
				.findViewById(R.id.coll_item_right_frag_tv_area_ne_lon);

		buttonSearchProd = (Button) rootView
				.findViewById(R.id.coll_item_right_frag_button_search_prod);
		buttonSearchProd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startSearchProducts();

			}
		});

		return rootView;

	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onCollectionItemRightFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnCollectionItemRightFragmentListener) activity;
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

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnCollectionItemRightFragmentListener {
		// TODO: Update argument type and name
		public void onCollectionItemRightFragmentInteraction(Uri uri);
	}

	public void updateProductAreaBounds(LatLngBounds bounds) {
		geoBounds = bounds;
		tvAreaSWLat.setText(String.valueOf(bounds.southwest.latitude));
		tvAreaSWLon.setText(String.valueOf(bounds.southwest.longitude));
		tvAreaNELat.setText(String.valueOf(bounds.northeast.latitude));
		tvAreaNELon.setText(String.valueOf(bounds.northeast.longitude));
	}

	/**
	 * 
	 */
	protected void startSearchProducts() {
		SearchProductsListFragment searchProductsFeedsFragment = SearchProductsListFragment
				.newInstance(paramCollEntry, geoBounds);
		getActivity().getSupportFragmentManager().beginTransaction()
				.add(R.id.search_list_container, searchProductsFeedsFragment)
				.commit();
	}
}
