package pl.wasat.smarthma.ui.frags.search;

import android.os.Bundle;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.ui.frags.base.BaseFeedSummaryFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the {@link FeedSummarySearchCollectionFragment#newInstance}
 * factory method to create an instance of this fragment.
 */
public class FeedSummarySearchCollectionFragment extends BaseFeedSummaryFragment {

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param feedParam Parameter 1.
     * @return A new instance of fragment FeedSummaryFragment.
     */
    public static FeedSummarySearchCollectionFragment newInstance(Feed feedParam) {
        FeedSummarySearchCollectionFragment fragment = new FeedSummarySearchCollectionFragment();
        Bundle args = new Bundle();
        args.putSerializable(BaseFeedSummaryFragment.KEY_FEED_SUMMARY, feedParam);
        fragment.setArguments(args);
        return fragment;
    }

    public FeedSummarySearchCollectionFragment() {
    }


    /* (non-Javadoc)
     * @see pl.wasat.smarthma.ui.frags.base.FeedSummaryFragment#loadNavSearch(java.lang.String)
     */
    @Override
    public void loadNavSearch(String linkHref) {
        FedeoRequestParams request = new FedeoRequestParams();
        request.setUrl(linkHref);

        SearchListFragment searchListFragment = SearchListFragment
                .newInstance(request, false);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container, searchListFragment)
                .commit();
        super.loadNavSearch(linkHref);
    }


}
