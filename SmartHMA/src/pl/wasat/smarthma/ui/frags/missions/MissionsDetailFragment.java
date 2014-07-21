package pl.wasat.smarthma.ui.frags.missions;

import pl.wasat.smarthma.R;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MissionsDetailFragment.OnMissionsDetailFragmentListener}
 * interface to handle interaction events. Use the
 * {@link MissionsDetailFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class MissionsDetailFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	//public static final String ARG_ITEM_ID = "item_id";

	private OnMissionsDetailFragmentListener mListener;


	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment DataSeriesDetailFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static MissionsDetailFragment newInstance(
			) {
		MissionsDetailFragment fragment = new MissionsDetailFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public MissionsDetailFragment() {
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_missions_detail,
				container, false);
			WebView detailWebView = (WebView) rootView
					.findViewById(R.id.missions_frag_detail_web);
			detailWebView.loadUrl("https://earth.esa.int/web/guest/missions");

		
		return rootView;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onMissionsDetailFragmentInteraction();
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnMissionsDetailFragmentListener) activity;
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
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_detail_twopane, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.actionbar_saveoffline) {
			Toast.makeText(getActivity().getApplicationContext(),
					"This metadata has been saved offline.", Toast.LENGTH_LONG)
					.show();
			return true;
		}
		else {
			return super.onOptionsItemSelected(item);
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
	public interface OnMissionsDetailFragmentListener {
		// TODO: Update argument type and name
		public void onMissionsDetailFragmentInteraction();
	}

//	private void loadSearchParameters(String title, String pubDate) {
//
//		MapSearchFragment mapSearchFragment = MapSearchFragment.newInstance(
//				null, null);
//		getActivity().getSupportFragmentManager().beginTransaction()
//				.replace(R.id.dataseries_detail_container, mapSearchFragment)
//				.addToBackStack("MapSearchFragment").commit();
//
//		CollectionItemRightFragment collectionItemRightFragment = CollectionItemRightFragment
//				.newInstance(displayedEntry, title, pubDate);
//		getActivity()
//				.getSupportFragmentManager()
//				.beginTransaction()
//				.add(R.id.dataseries_list,
//						collectionItemRightFragment,"CollectionItemRightFragment")
//				.addToBackStack("CollectionItemRightFragment").commit();
//
//	}
	
}
