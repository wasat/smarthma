package pl.wasat.smarthma.ui.frags.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.adapter.SearchListAdapter;
import pl.wasat.smarthma.database.SearchHistory;
import pl.wasat.smarthma.database.SearchParams;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.helper.DataSorter;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.ui.frags.base.BaseSpiceListFragment;
import pl.wasat.smarthma.ui.frags.common.FailureFragment;
import pl.wasat.smarthma.utils.rss.FedeoSearchRequest;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link SearchListFragment.OnSearchListFragmentListener} interface to handle
 * interaction events. Use the {@link SearchListFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class SearchListFragment extends BaseSpiceListFragment {
    private static final String KEY_PARAM_SEARCH_FEDEO_REQUEST = "pl.wasat.smarthma.KEY_PARAM_SEARCH_FEDEO_REQUEST";
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    private FedeoRequest searchRequest;

    private int mActivatedPosition = ListView.INVALID_POSITION;

    private OnSearchListFragmentListener mListener;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param fedeoRequest Parameter 2.
     * @return A new instance of fragment SearchListFragment.
     */
    public static SearchListFragment newInstance(FedeoRequest fedeoRequest, Boolean stopNewSearch) {
        SearchListFragment fragment = new SearchListFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_PARAM_SEARCH_FEDEO_REQUEST, fedeoRequest);
        args.putBoolean(Const.KEY_INTENT_RETURN_STOP_SEARCH, stopNewSearch);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchListFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchRequest = (FedeoRequest) getArguments().getSerializable(
                    KEY_PARAM_SEARCH_FEDEO_REQUEST);
            SearchHistory searchHistory = new SearchHistory(getActivity());
            searchHistory.addSearchParameters(new SearchParams(searchRequest));
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
            mListener = (OnSearchListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSearchListFragmentListener");
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
        stopSearch = getArguments().getBoolean(Const.KEY_INTENT_RETURN_STOP_SEARCH);
        if (searchRequest != null && !stopSearch) {
            loadSearchFeedResponse(searchRequest);
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position,
                                long id) {
        super.onListItemClick(listView, view, position, id);
        mListener.onSearchListFragmentItemSelected(String.valueOf(position));
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

    void setActivatedPosition(int position) {
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
        switch (id) {
            case R.id.action_sort_by_title_asc:
                SmartHMApplication.sortingType = Const.SORT_BY_TITLE_ASCENDING;
                refreshList();
                break;
            case R.id.action_sort_by_title_desc:
                SmartHMApplication.sortingType = Const.SORT_BY_TITLE_DESCENDING;
                refreshList();
                break;
            case R.id.action_sort_by_date_asc:
                SmartHMApplication.sortingType = Const.SORT_BY_DATE_ASCENDING;
                refreshList();
                break;
            case R.id.action_sort_by_date_desc:
                SmartHMApplication.sortingType = Const.SORT_BY_DATE_DESCENDING;
                refreshList();
                break;
            case R.id.actionbar_refresh:
                loadSearchFeedResponse(searchRequest);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    void refreshList() {
        loadSearchFeedResponse(searchRequest);
        Toast.makeText(getActivity(), getActivity().getString(R.string.refreshing_list), Toast.LENGTH_LONG).show();
    }

    private void updateSearchListViewContent(List<EntryISO> searchFeedList) {
        View view = getView();
        if (view != null) {
            if (searchFeedList.isEmpty()) {
                view.setVisibility(View.GONE);

                String searchFail = getActivity().getString(
                        R.string.nothing_to_display_please_search_again_);

                FailureFragment failureFragment = FailureFragment
                        .newInstance(searchFail);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_base_details_container,
                                failureFragment).commit();
            } else {
                SearchListAdapter adapter = new SearchListAdapter(getActivity(),
                        searchFeedList);
                this.setListAdapter(adapter);
                adapter.notifyDataSetChanged();
                SearchListAdapter adapter1 = adapter;
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     *
     */
    private void loadSearchFeedResponse(FedeoRequest feedSearchRequest) {
        if (feedSearchRequest != null) {
            getActivity().setProgressBarIndeterminateVisibility(true);
            getSpiceManager().execute(
                    new FedeoSearchRequest(feedSearchRequest, 1), this);
        }
    }

    /**
     * @param searchResultFeed - Dataseries Feed
     */
    private void showDataSeriesIntro(Feed searchResultFeed) {
        FeedSummarySearchCollectionFragment feedSummarySearchCollectionFragment = FeedSummarySearchCollectionFragment
                .newInstance(searchResultFeed);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        feedSummarySearchCollectionFragment, "FeedSummarySearchFragment")
                .addToBackStack("FeedSummarySearchFragment").commit();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSearchListFragmentListener {
        public void onSearchListFragmentItemSelected(String id);
    }

    @Override
    public void onRequestSuccess(Feed searchFeeds) {
        List<EntryISO> entries = searchFeeds.getEntriesISO();
        DataSorter sorter = new DataSorter();
        sorter.sort(entries);

        getActivity().setProgressBarIndeterminateVisibility(false);
        updateSearchListViewContent(searchFeeds.getEntriesISO());

        showDataSeriesIntro(searchFeeds);

    }

}
