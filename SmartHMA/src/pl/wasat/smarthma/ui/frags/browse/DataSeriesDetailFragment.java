package pl.wasat.smarthma.ui.frags.browse;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.ui.frags.common.MapSearchFragment;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link DataSeriesDetailFragment.OnDataSeriesDetailFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link DataSeriesDetailFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class DataSeriesDetailFragment extends Fragment {
	public static final String ARG_ITEM_ID = "item_id";

	private Entry displayedEntry;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @return A new instance of fragment DataSeriesDetailFragment.
	 */
	public static DataSeriesDetailFragment newInstance() {
		DataSeriesDetailFragment fragment = new DataSeriesDetailFragment();
		Bundle args = new Bundle();
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
		}

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
			final String title = displayedEntry.getTitle();
			final String pubDate = "This EO data ware published: "
					+ displayedEntry.getPublished() + " and updated: "
					+ displayedEntry.getUpdated();

			String content = displayedEntry.getSummary();
			((TextView) rootView.findViewById(R.id.dataseries_name))
					.setText(title);
			((TextView) rootView.findViewById(R.id.dataseries_dates))
					.setText(pubDate);
			WebView detailWebView = (WebView) rootView
					.findViewById(R.id.dataseries_detail);
			detailWebView.loadData(content, "text/html", "UTF-8");

			Button searchButton = (Button) rootView
					.findViewById(R.id.ds_detail_button_search_product);
			searchButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					loadSearchParameters(title, pubDate);

				}

			});
		}
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnDataSeriesDetailFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
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
		} else {
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
		public void onDataSeriesDetailFragmentInteraction();
	}

	private void loadSearchParameters(String title, String pubDate) {

		MapSearchFragment mapSearchFragment = MapSearchFragment.newInstance(0);
		getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.activity_base_details_container, mapSearchFragment)
				.addToBackStack("MapSearchFragment").commit();

	}

}
