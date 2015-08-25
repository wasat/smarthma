package pl.wasat.smarthma.ui.frags.browse;

import android.os.Bundle;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.ui.frags.base.BaseFeedSummaryFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the {@link FeedSummaryBrowseCollectionsFragment#newInstance}
 * factory method to create an instance of this fragment.
 */
public class FeedSummaryBrowseCollectionsFragment extends BaseFeedSummaryFragment {

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param feedParam Parameter 1.
     * @return A new instance of fragment FeedSummaryFragment.
     */
    public static FeedSummaryBrowseCollectionsFragment newInstance(Feed feedParam) {
        FeedSummaryBrowseCollectionsFragment fragment = new FeedSummaryBrowseCollectionsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BaseFeedSummaryFragment.KEY_FEED_SUMMARY, feedParam);
        fragment.setArguments(args);
        return fragment;
    }

    public FeedSummaryBrowseCollectionsFragment() {
    }

    /* (non-Javadoc)
     * @see pl.wasat.smarthma.ui.frags.base.FeedSummaryFragment#loadNavSearch(java.lang.String)
     */
    @Override
    public void loadNavSearch(String linkHref) {
        FedeoRequestParams request = new FedeoRequestParams();
        request.setUrl(linkHref);

        DataSeriesListFragment dataseriesListFragment = DataSeriesListFragment
                .newInstance(request, false);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container, dataseriesListFragment)
                .commit();
        super.loadNavSearch(linkHref);
    }


}
