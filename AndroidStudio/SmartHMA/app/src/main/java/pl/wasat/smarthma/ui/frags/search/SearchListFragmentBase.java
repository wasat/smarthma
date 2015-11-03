package pl.wasat.smarthma.ui.frags.search;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.SearchListAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.ui.frags.base.BaseSpiceListFragment;
import pl.wasat.smarthma.ui.frags.common.FailureFragment;

public abstract class SearchListFragmentBase extends BaseSpiceListFragment {
    protected static final String STATE_ACTIVATED_POSITION = "activated_position";
    protected FedeoRequestParams searchRequest;
    protected List<EntryISO> entries;
    protected int mActivatedPosition = ListView.INVALID_POSITION;
    protected FeedSummarySearchCollectionFragment feedSummarySearchCollectionFragment;
    protected OnSearchListFragmentListener mListener;

    public SearchListFragmentBase() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState
                    .getInt(STATE_ACTIVATED_POSITION));
        }

        getListView().setDivider(null);
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }
        mActivatedPosition = position;
    }

    protected void showFailureFragment() {
        String searchFail = getActivity().getString(
                R.string.nothing_to_display_please_search_again_);

        FailureFragment failureFragment = FailureFragment
                .newInstance(searchFail);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        failureFragment).commit();
    }

    protected void attachListAdapter(List<EntryISO> searchFeedList)
    {
        SearchListAdapter adapter = new SearchListAdapter(getActivity(),
                searchFeedList);
        this.setListAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void loadSearchFeedResponse(FedeoRequestParams feedSearchRequest) {
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
        void onSearchListFragmentItemSelected(String id);
    }

    /**
     * @param searchResultFeed - Dataseries Feed
     */
    protected void showDataSeriesIntro(Feed searchResultFeed) {
        feedSummarySearchCollectionFragment = FeedSummarySearchCollectionFragment
                .newInstance(searchResultFeed);
    }

    @Override
    public void onRequestSuccess(Feed searchFeeds) {
    }

    public List<EntryISO> getEntries() {
        return entries;
    }
}
