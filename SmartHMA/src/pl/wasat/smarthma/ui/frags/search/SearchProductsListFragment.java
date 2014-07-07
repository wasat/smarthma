package pl.wasat.smarthma.ui.frags.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.ProductsListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.services.SearchProductsHttpSpiceService;
import pl.wasat.smarthma.ui.frags.FailureFragment;
import pl.wasat.smarthma.utils.rss.SearchProductsFeedRequest;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLngBounds;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.exception.RequestCancelledException;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link SearchProductsListFragment.OnSearchProductsListFragmentListener}
 * interface to handle interaction events. Use the
 * {@link SearchProductsListFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class SearchProductsListFragment extends ListFragment implements
		RequestListener<List<Entry>> {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String KEY_COLL_ENTRY = "pl.wasat.samrthma.KEY_COLL_ENTRY";
	private static final String KEY_PROD_BOUNDS = "pl.wasat.samrthma.KEY_PROD_BOUNDS";

	// TODO: Rename and change types of parameters
	private LatLngBounds paramGeoBounds;
	private Entry paramCollEntry;

	private OnSearchProductsListFragmentListener mListener;

	private static final String STATE_ACTIVATED_POSITION = "activated_position";
	private int mActivatedPosition = ListView.INVALID_POSITION;
	private SpiceManager searchProductsSpiceManager = new SpiceManager(
			SearchProductsHttpSpiceService.class);

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param bounds
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment SearchProductsFeedsFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static SearchProductsListFragment newInstance(Entry collectionEntry,
			LatLngBounds bounds) {
		SearchProductsListFragment fragment = new SearchProductsListFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_COLL_ENTRY, collectionEntry);
		args.putParcelable(KEY_PROD_BOUNDS, bounds);
		fragment.setArguments(args);
		return fragment;
	}

	public SearchProductsListFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			paramGeoBounds = getArguments().getParcelable(KEY_PROD_BOUNDS);
			paramCollEntry = (Entry) getArguments().getSerializable(
					KEY_COLL_ENTRY);
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(String id) {
		if (mListener != null) {
			mListener.onSearchProductsListFragmentItemSelected(id);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnSearchProductsListFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnSearchProductsFeedFragmentListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void onStart() {
		super.onStart();
		searchProductsSpiceManager.start(getActivity());
		// TODO: Find solution - why fragment is called twice
		if (paramGeoBounds != null) {
			loadSearchProductsFeedResponse(paramCollEntry, paramGeoBounds);
		}
	}

	@Override
	public void onStop() {
		if (searchProductsSpiceManager.isStarted()) {
			searchProductsSpiceManager.shouldStop();
		}
		super.onStop();
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);
		mListener.onSearchProductsListFragmentItemSelected(String
				.valueOf(position));
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	public void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}
		mActivatedPosition = position;
	}

	private void updateSearchProductsListViewContent(
			List<Entry> searchProductsFeedList) {
		if (searchProductsFeedList.isEmpty()) {
			getView().setVisibility(View.GONE);

			String searchFail = getActivity().getString(
					R.string.nothing_to_display_please_search_again_);

			FailureFragment failureFragment = FailureFragment
					.newInstance(searchFail);

			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.search_list_container, failureFragment)
					.commit();
		} else {

			for (Entry a : searchProductsFeedList) {
				EoDbAdapter dba = new EoDbAdapter(getActivity());
				dba.openToRead();
				Entry fetchedSearch = dba.getBlogListing(a.getGuid());
				dba.close();
				if (fetchedSearch == null) {
					dba = new EoDbAdapter(getActivity());
					dba.openToWrite();
					dba.insertBlogListing(a.getGuid());
					dba.close();
				} else {
					a.setDbId(fetchedSearch.getDbId());
					a.setOffline(fetchedSearch.isOffline());
					a.setRead(fetchedSearch.isRead());
				}
			}
			ProductsListAdapter adapter = new ProductsListAdapter(
					getActivity(), searchProductsFeedList);
			this.setListAdapter(adapter);
			adapter.notifyDataSetChanged();
			getView().setVisibility(View.VISIBLE);
		}

	}

	/**
	 * 
	 */
	private void loadSearchProductsFeedResponse(Entry collEntry,
			LatLngBounds geoBounds) {
		if (geoBounds != null) {
			getActivity().setProgressBarIndeterminateVisibility(true);
			searchProductsSpiceManager.execute(new SearchProductsFeedRequest(
					collEntry, geoBounds), this);
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
	public interface OnSearchProductsListFragmentListener {
		// TODO: Update argument type and name
		public void onSearchProductsListFragmentItemSelected(String id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.request.listener.RequestListener#onRequestFailure
	 * (com.octo.android.robospice.persistence.exception.SpiceException)
	 */
	@Override
	public void onRequestFailure(SpiceException arg0) {
		// arg0.getCause();
		if (arg0.getCause() instanceof HttpClientErrorException) {
			HttpClientErrorException exception = (HttpClientErrorException) arg0
					.getCause();
			if (exception.getStatusCode().equals(HttpStatus.SC_UNAUTHORIZED)) {
			} else if (exception.getStatusCode().equals(
					HttpStatus.SC_SERVICE_UNAVAILABLE)) {
				// String response = exception.getResponseBodyAsString();
			} else {
			}
		} else if (arg0 instanceof RequestCancelledException) {
		} else {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.request.listener.RequestListener#onRequestSuccess
	 * (java.lang.Object)
	 */
	@Override
	public void onRequestSuccess(List<Entry> searchProductFeeds) {
		getActivity().setProgressBarIndeterminateVisibility(false);
		Toast.makeText(getActivity(), R.string.ok_, Toast.LENGTH_SHORT).show();
		if (searchProductFeeds == null) {
			searchProductFeeds = new ArrayList<Entry>();
		}
		updateSearchProductsListViewContent(searchProductFeeds);

	}

}
