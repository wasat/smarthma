package pl.wasat.smarthma.ui.frags.browse;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.DataSeriesListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.ui.frags.BaseSpiceListFragment;
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
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";

	// TODO: Rename and change types of parameters
	private String mParam1;

	private static final String STATE_ACTIVATED_POSITION = "activated_position";
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
	public static DataSeriesListFragment newInstance(String param1) {
		DataSeriesListFragment fragment = new DataSeriesListFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
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
			mParam1 = getArguments().getString(ARG_PARAM1);
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
		if (mParam1 != null) {
			loadDataSeriesFeedResponse(mParam1);
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
			loadDataSeriesFeedResponse(mParam1);
			return true;
		}
		return false;
	}

	private void updateEOListViewContent(List<Entry> dataSeriesFeedList) {
		if (dataSeriesFeedList.isEmpty()) {
            //noinspection ConstantConditions
            getView().setVisibility(View.GONE);

			// String searchFail = "Nothing to display. Please search again.";
			//
			// FailureFragment failureFragment = FailureFragment
			// .newInstance(searchFail);
			// getActivity().getSupportFragmentManager().beginTransaction()
			// .replace(R.id.dataseries_detail_container, failureFragment)
			// .commit();
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
			// loadingView.setVisibility(View.GONE);
            //noinspection ConstantConditions
            getView().setVisibility(View.VISIBLE);
		}

	}

	/**
	 * 
	 */
	private void loadDataSeriesFeedResponse(String feedSearch) {
		if (feedSearch != null) {

			FedeoRequest req = new FedeoRequest();
			req.setDefaultParams();
			req.setParentIdentifier(feedSearch);

			getActivity().setProgressBarIndeterminateVisibility(true);
			getSpiceManager().execute(new FedeoSearchRequest(req), this);
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
		BrowseDataSeriesIntroFragment browseDataSeriesIntroFragment = BrowseDataSeriesIntroFragment
				.newInstance(dataSeriesFeeds);
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.dataseries_detail_container,
						browseDataSeriesIntroFragment)
				.addToBackStack("BrowseDataSeriesIntroFragment").commit();

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
