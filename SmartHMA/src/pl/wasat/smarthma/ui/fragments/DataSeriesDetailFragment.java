package pl.wasat.smarthma.ui.fragments;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.DbAdapter;
import pl.wasat.smarthma.model.dataseries.Entry;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link DataSeriesDetailFragment.OnDataSeriesDetailFragmentInteractionListener} interface to
 * handle interaction events. Use the
 * {@link DataSeriesDetailFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class DataSeriesDetailFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	//private static final String ARG_PARAM1 = "param1";
	//private static final String ARG_PARAM2 = "param2";
	public static final String ARG_ITEM_ID = "item_id";

	// TODO: Rename and change types of parameters
	//private String mParam1;
	//private String mParam2;

	private OnDataSeriesDetailFragmentInteractionListener mListener;
	private Entry displayedEntry;
	private DbAdapter db;

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
	public static DataSeriesDetailFragment newInstance(String param1,
			String param2) {
		DataSeriesDetailFragment fragment = new DataSeriesDetailFragment();
		Bundle args = new Bundle();
		//args.putString(ARG_PARAM1, param1);
		//args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public DataSeriesDetailFragment() {
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			//mParam1 = getArguments().getString(ARG_PARAM1);
			//mParam2 = getArguments().getString(ARG_PARAM2);
		}
		
		db = new DbAdapter(getActivity());
		if (getArguments().containsKey(Entry.KEY_RSS_ENTRY)) {
			displayedEntry = (Entry) getArguments().getSerializable(
					Entry.KEY_RSS_ENTRY);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_dataseries_detail,
				container, false);
		if (displayedEntry != null) {
			String title = displayedEntry.getTitle();
			String pubDate = "This EO data ware published: "
					+ displayedEntry.getPublished() + " and updated: "
					+ displayedEntry.getUpdated();
			
//			String pubDate = displayedEntry.getPublished();
//			SimpleDateFormat df = new SimpleDateFormat(
//					"EEE, dd MMM yyyy kk:mm:ss Z", Locale.ENGLISH);
//			try {
//				Date pDate = df.parse(pubDate);
//				pubDate = "This post was published "
//						+ DateUtils.getDateDifference(pDate) + " updated "
//						+ displayedEntry.getUpdated();
//			} catch (ParseException e) {
//				//Log.i("DATE PARSING", "Error parsing date..");
//				pubDate = "This post was published: "
//						+ displayedEntry.getPublished() + "and updated: "
//						+ displayedEntry.getUpdated();
//			}

			String content = displayedEntry.getEncodedContent();
			((TextView) rootView.findViewById(R.id.dataseries_name))
					.setText(title);
			((TextView) rootView.findViewById(R.id.dataseries_dates))
					.setText(pubDate);
			WebView detailWebView = (WebView)rootView.findViewById(R.id.dataseries_detail);
			detailWebView.loadData(content, "text/html","UTF-8");
		}
		return rootView;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onDataSeriesDetailFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnDataSeriesDetailFragmentInteractionListener) activity;
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
		Log.d("item ID : ", "onOptionsItemSelected Item ID" + id);
		if (id == R.id.actionbar_saveoffline) {
			Toast.makeText(getActivity().getApplicationContext(),
					"This metadata has been saved offline.",
					Toast.LENGTH_LONG).show();
			return true;
		}
//		else if (id == R.id.actionbar_markunread) {
//			db.openToWrite();
//			db.markAsUnread(displayedEntry.getGuid());
//			db.close();
//			displayedEntry.setRead(false);
//			DataSeriesListAdapter adapter = (DataSeriesListAdapter) ((DataSeriesListFragment) getActivity()
//					.getSupportFragmentManager().findFragmentById(
//							R.id.dataseries_list)).getListAdapter();
//			adapter.notifyDataSetChanged();
//			return true;
//		} 
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
	public interface OnDataSeriesDetailFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onDataSeriesDetailFragmentInteraction(Uri uri);
	}

}
