package pl.wasat.smarthma.ui.frags.browse;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.DataSeriesListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.ui.frags.base.BaseSpiceListFragment;
import pl.wasat.smarthma.utils.rss.FedeoSearchRequest;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link DataSeriesListFragment.OnDataSeriesListFragmentListener} interface to
 * handle interaction events. Use the {@link DataSeriesListFragment#newInstance}
 * factory method to create an instance of this fragment.
 * 
 */
public class DataSeriesListFragment extends BaseSpiceListFragment {

	private FedeoRequest browseRequest;

	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	private static final String KEY_PARAM_BROWSE_FEDEO_REQUEST = "pl.wasat.smarthma.KEY_PARAM_BROWSE_FEDEO_REQUEST";
	private int mActivatedPosition = ListView.INVALID_POSITION;

	private OnDataSeriesListFragmentListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment DataSeriesListFragment.
	 */
	public static DataSeriesListFragment newInstance(FedeoRequest fedeoRequest) {
		DataSeriesListFragment fragment = new DataSeriesListFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_PARAM_BROWSE_FEDEO_REQUEST, fedeoRequest);
		fragment.setArguments(args);
		return fragment;
	}

	public DataSeriesListFragment() {
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			browseRequest = (FedeoRequest) getArguments().getSerializable(KEY_PARAM_BROWSE_FEDEO_REQUEST);
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

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnDataSeriesListFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnDataSeriesListFragmentListener");
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
		if (browseRequest != null) {
			loadDataSeriesFeedResponse(browseRequest);
		}
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);
		mListener.onDataSeriesFragmentItemSelected(String.valueOf(position));
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

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_list_refresh, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.actionbar_refresh) {
			loadDataSeriesFeedResponse(browseRequest);
			return true;
		}
		return false;
	}

	private void updateEOListViewContent(List<Entry> dataSeriesFeedList) {
		if (dataSeriesFeedList.isEmpty()) {
			getView().setVisibility(View.GONE);
		} else {

			for (Entry a : dataSeriesFeedList) {
				EoDbAdapter dba = new EoDbAdapter(getActivity());
				dba.openToRead();
				Entry fetchedDataSeries = dba.getBlogListing(a.getGuid());
				dba.close();
				if (fetchedDataSeries == null) {
					dba = new EoDbAdapter(getActivity());
					dba.openToWrite();
					dba.insertBlogListing(a.getGuid());
					dba.close();
				} else {
					a.setDbId(fetchedDataSeries.getDbId());
					a.setOffline(fetchedDataSeries.isOffline());
					a.setRead(fetchedDataSeries.isRead());
				}
			}
			DataSeriesListAdapter adapter = new DataSeriesListAdapter(
					getActivity(), dataSeriesFeedList);
			this.setListAdapter(adapter);
			adapter.notifyDataSetChanged();
			getView().setVisibility(View.VISIBLE);
		}

	}

	/**
	 * 
	 */
	private void loadDataSeriesFeedResponse(FedeoRequest browseRequest) {
		if (browseRequest != null) {

			getActivity().setProgressBarIndeterminateVisibility(true);
			getSpiceManager().execute(new FedeoSearchRequest(browseRequest), this);
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
	public void onRequestSuccess(Feed dataSeriesFeeds) {
		getActivity().setProgressBarIndeterminateVisibility(false);
		Toast.makeText(getActivity(), "OK!!! ", Toast.LENGTH_SHORT).show();
		updateEOListViewContent(dataSeriesFeeds.getEntries());

		loadIntroFeedInfo(dataSeriesFeeds);
	}

	/**
	 * @param dataSeriesFeeds
	 * 
	 */
	private void loadIntroFeedInfo(Feed dataSeriesFeeds) {
		FeedSummaryBrowseFragment feedSummaryBrowseFragment = (FeedSummaryBrowseFragment) FeedSummaryBrowseFragment
				.newInstance(dataSeriesFeeds);
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_details_container,
						feedSummaryBrowseFragment)
				.addToBackStack("FeedSummaryBrowseFragment").commit();

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

	public interface OnDataSeriesListFragmentListener {
		// TODO: Update argument type and name
		public void onDataSeriesFragmentItemSelected(String id);
	}

}
