package pl.wasat.smarthma.ui.frags.search;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.ProductsListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.model.eo.Pos;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.ui.frags.BaseSpiceListFragment;
import pl.wasat.smarthma.ui.frags.FailureFragment;
import pl.wasat.smarthma.utils.rss.SearchProductsFeedRequest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link SearchProductsListFragment.OnSearchProductsListFragmentListener}
 * interface to handle interaction events. Use the
 * {@link SearchProductsListFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class SearchProductsListFragment extends BaseSpiceListFragment {
	private static final String KEY_COLL_ENTRY = "pl.wasat.samrthma.KEY_COLL_ENTRY";
	private static final String KEY_PROD_BOUNDS = "pl.wasat.samrthma.KEY_PROD_BOUNDS";
	
	private LatLngBounds paramGeoBounds;
	private Entry paramCollEntry;

	private OnSearchProductsListFragmentListener mListener;

	private static final String STATE_ACTIVATED_POSITION = "activated_position";
	private int mActivatedPosition = ListView.INVALID_POSITION;


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
		// TODO: Find solution - why fragment is called twice
		if (paramGeoBounds != null) {
			loadSearchProductsFeedResponse(paramCollEntry, paramGeoBounds);
		}
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

			getSpiceManager().execute(
					new SearchProductsFeedRequest(collEntry, geoBounds), this);
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

		public void onSearchProductsListFragmentFootprintSend(
				ArrayList<List<Pos>> footPrints);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.request.listener.RequestListener#onRequestSuccess
	 * (java.lang.Object)
	 */
	@Override
	public void onRequestSuccess(Feed searchProductFeeds) {
		getActivity().setProgressBarIndeterminateVisibility(false);
		Toast.makeText(getActivity(), R.string.ok_, Toast.LENGTH_SHORT).show();
		if (searchProductFeeds == null) {
			searchProductFeeds = new Feed();
		}
		updateSearchProductsListViewContent(searchProductFeeds.getEntries());
		ArrayList<List<Pos>> footPrints = getFootprints(searchProductFeeds
				.getEntries());
		mListener.onSearchProductsListFragmentFootprintSend(footPrints);

	}

	/**
	 * @param searchProductFeeds
	 * @return
	 */
	private ArrayList<List<Pos>> getFootprints(List<Entry> searchProductFeeds) {
		ArrayList<List<Pos>> footPrintsArr = new ArrayList<List<Pos>>();
		for (int i = 0; i < searchProductFeeds.size(); i++) {
			footPrintsArr.add(searchProductFeeds.get(i).getEarthObservation()
					.getFeatureOfInterest().getFootprint().getMultiExtentOf()
					.getMultiSurface().getSurfaceMembers().getPolygon()
					.getExterior().getLinearRing().getPosList());
		}
		return footPrintsArr;
	}


}
