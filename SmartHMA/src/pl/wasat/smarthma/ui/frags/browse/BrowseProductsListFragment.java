package pl.wasat.smarthma.ui.frags.browse;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment;
import pl.wasat.smarthma.ui.frags.common.FailureFragment;
import pl.wasat.smarthma.ui.frags.search.SearchProductsListFragment;
import android.os.Bundle;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link SearchProductsListFragment.OnBaseShowProductsListFragmentListener}
 * interface to handle interaction events. Use the
 * {@link BrowseProductsListFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class BrowseProductsListFragment extends BaseShowProductsListFragment {
	private static final String KEY_PARAM_FEDEO_REQUEST = "pl.wasat.samrthma.KEY_PARAM_FEDEO_REQUEST";

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
	public static BrowseProductsListFragment newInstance(
			FedeoRequest fedeoRequest) {
		BrowseProductsListFragment fragment = new BrowseProductsListFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_PARAM_FEDEO_REQUEST, fedeoRequest);
		fragment.setArguments(args);
		return fragment;
	}

	public BrowseProductsListFragment() {
		// Required empty public constructor
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment#
	 * loadProductItemDetails(pl.wasat.smarthma.model.feed.Entry)
	 */
	@Override
	public void loadProductItemDetails(Entry entry) {
		BrowseProductDetailsFragment fragment = BrowseProductDetailsFragment
				.newInstance(entry);
		getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.activity_base_details_container, fragment)
				.commit();

		super.loadProductItemDetails(entry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment#
	 * loadSearchResultProductsIntroDetailsFrag
	 * (pl.wasat.smarthma.model.feed.Feed)
	 */
	@Override
	public void loadSearchResultProductsIntroDetailsFrag(Feed searchProductFeeds) {
		FeedSummaryBrowseFragment productsIntroFragment = FeedSummaryBrowseFragment
				.newInstance(searchProductFeeds);
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_details_container,
						productsIntroFragment, "ProductsIntroFragment")
				.addToBackStack("ProductsIntroFragment").commit();
		super.loadSearchResultProductsIntroDetailsFrag(searchProductFeeds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment#loadFailureFrag
	 * ()
	 */
	@Override
	public void loadFailureFrag() {
		String searchFail = getActivity().getString(
				R.string.nothing_to_display_please_search_again_);

		FailureFragment failureFragment = FailureFragment
				.newInstance(searchFail);

		getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.activity_base_list_container, failureFragment).commit();
		super.loadFailureFrag();
	}

}
