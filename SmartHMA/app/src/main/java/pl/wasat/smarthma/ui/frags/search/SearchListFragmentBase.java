package pl.wasat.smarthma.ui.frags.search;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.SearchListAdapter;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.ui.frags.base.BaseSpiceListFragment;
import pl.wasat.smarthma.ui.frags.common.FailureFragment;

public abstract class SearchListFragmentBase extends BaseSpiceListFragment {
    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    FedeoRequestParams searchRequest;
    List<EntryISO> entries;
    FeedSummarySearchCollectionFragment feedSummarySearchCollectionFragment;
    OnSearchListFragmentListener mListener;
    private int mActivatedPosition = ListView.INVALID_POSITION;

    public SearchListFragmentBase() {
        setHasOptionsMenu(true);
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

/*    @Override
    public void onStart() {
        super.onStart();

        // TODO: Find solution - why fragment is called twice
        stopSearch = getArguments().getBoolean(Const.KEY_INTENT_RETURN_STOP_SEARCH);
        if (searchRequest != null && !stopSearch) {
            //loadSearchFeedResponse(searchRequest);
        }
    }*/

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }
        mActivatedPosition = position;
    }

    @Override
    public void onRequestSuccess(Feed searchFeeds) {
    }

    public List<EntryISO> getEntries() {
        return entries;
    }

/*    private void loadSearchFeedResponse(FedeoRequestParams feedSearchRequest) {
    }*/

    void showFailureFragment() {
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

    void attachListAdapter(final List<EntryISO> searchFeedList) {
        SearchListAdapter adapter = new SearchListAdapter(getActivity(),
                searchFeedList);
        adapter.setListener(new OnSlideElementListener() {
            @Override
            public void Catch(boolean swipeRight, int position) {
                if (swipeRight) {
                    Toast.makeText(getContext(),
                            getContext().getString(R.string.swipe_right)
                                    + position, Toast.LENGTH_SHORT).show();
                    mListener.onSearchListFragmentRightSwipeItem(searchFeedList.get(position));
                } else {
                    Toast.makeText(getContext(),
                            getContext().getString(R.string.swipe_left)
                                    + position, Toast.LENGTH_SHORT).show();
                    mListener.onSearchListFragmentLeftSwipeItem(searchFeedList.get(position));
                }
            }
        });
        this.setListAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * @param searchResultFeed - Dataseries Feed
     */
    void showDataSeriesIntro(Feed searchResultFeed) {
        feedSummarySearchCollectionFragment = FeedSummarySearchCollectionFragment
                .newInstance(searchResultFeed);
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

        void onSearchListFragmentRightSwipeItem(EntryISO selectedEntry);

        void onSearchListFragmentLeftSwipeItem(EntryISO selectedEntry);
    }
}
