package pl.wasat.smarthma.ui.frags.search;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment;
import android.app.Activity;
import android.net.Uri;
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
 * {@link SearchDetailFragment.OnSearchDetailFragmentListener} interface to
 * handle interaction events. Use the {@link SearchDetailFragment#newInstance}
 * factory method to create an instance of this fragment.
 * 
 */
public class SearchDetailFragment extends Fragment {

	private OnSearchDetailFragmentListener mListener;
	private Entry displayedEntry;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @return A new instance of fragment SearchDetailFragment.
	 */
	public static SearchDetailFragment newInstance() {
		SearchDetailFragment fragment = new SearchDetailFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public SearchDetailFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
            if (getArguments().containsKey(Entry.KEY_RSS_ENTRY)) {
                displayedEntry = (Entry) getArguments().getSerializable(
                        Entry.KEY_RSS_ENTRY);
            }
        }

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_search_detail,
				container, false);

		if (displayedEntry != null) {
			final String title = displayedEntry.getTitle();
			final String pubDate = "This data were published: "
					+ displayedEntry.getPublished() + " and updated: "
					+ displayedEntry.getUpdated();

			String content = displayedEntry.getSummary();
			((TextView) rootView.findViewById(R.id.search_fragment_detail_name))
					.setText(title);
			((TextView) rootView
					.findViewById(R.id.search_fragment_detail_dates))
					.setText(pubDate);
			WebView detailWebView = (WebView) rootView
					.findViewById(R.id.search_fragment_detail_content);
			detailWebView.loadData(content, "text/html", "UTF-8");

			Button searchButton = (Button) rootView
					.findViewById(R.id.search_detail_button_search_product);
			searchButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					loadSearchParameters(title, pubDate);
				}
			});

			for (Link entityLink : displayedEntry.getLinks()) {
				if (entityLink.get_rel().equalsIgnoreCase("search")) {
					searchButton.setEnabled(true);
				}

			}

			Button showMapButton = (Button) rootView
					.findViewById(R.id.search_detail_button_show_map);
			showMapButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					AreaPickerMapFragment areaPickerMapFragment = AreaPickerMapFragment
							.newInstance();
					getActivity()
							.getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.activity_base_details_container,
									areaPickerMapFragment)
							.addToBackStack("AreaPickerMapFragment").commit();

				}
			});
		}
		return rootView;
	}
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onSearchDetailFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnSearchDetailFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnSearchDetailFragmentListener");
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
	public interface OnSearchDetailFragmentListener {
		public void onSearchDetailFragmentInteraction(Uri uri);
	}

	private void loadSearchParameters(String title, String pubDate) {

		AreaPickerMapFragment areaPickerMapFragment = AreaPickerMapFragment
				.newInstance();
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_details_container,
						areaPickerMapFragment, "AreaPickerMapFragment")
				.addToBackStack("AreaPickerMapFragment").commit();

	}

}
