package pl.wasat.smarthma.ui.frags.common;

import android.os.Bundle;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.ui.frags.base.BaseFeedSummaryFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the {@link FeedSummaryProductsFragment#newInstance}
 * factory method to create an instance of this fragment.
 */
public class FeedSummaryProductsFragment extends BaseFeedSummaryFragment {

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param feedParam Parameter 1.
     * @return A new instance of fragment FeedSummaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedSummaryProductsFragment newInstance(Feed feedParam) {
        FeedSummaryProductsFragment fragment = new FeedSummaryProductsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BaseFeedSummaryFragment.KEY_FEED_SUMMARY, feedParam);
        fragment.setArguments(args);
        return fragment;
    }

    public FeedSummaryProductsFragment() {
        // Required empty public constructor
    }

    /* (non-Javadoc)
     * @see pl.wasat.smarthma.ui.frags.base.FeedSummaryFragment#loadNavSearch(java.lang.String)
     */
    @Override
    public void loadNavSearch(String linkHref) {
        FedeoRequest request = new FedeoRequest();
        request.setUrl(linkHref);

        ProductsListFragment productsListFragment = ProductsListFragment
                .newInstance(request);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container, productsListFragment)
                .commit();
        super.loadNavSearch(linkHref);
    }


}
