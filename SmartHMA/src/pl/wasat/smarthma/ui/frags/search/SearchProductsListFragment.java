package pl.wasat.smarthma.ui.frags.search;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment;
import pl.wasat.smarthma.ui.frags.common.FailureFragment;
import android.os.Bundle;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link SearchProductsListFragment.OnBaseShowProductsListFragmentListener}
 * interface to handle interaction events. Use the
 * {@link SearchProductsListFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class SearchProductsListFragment extends BaseShowProductsListFragment {
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
	public static SearchProductsListFragment newInstance(
			FedeoRequest fedeoRequest) {
		SearchProductsListFragment fragment = new SearchProductsListFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_PARAM_FEDEO_REQUEST, fedeoRequest);
		fragment.setArguments(args);
		return fragment;
	}

	public SearchProductsListFragment() {
		// Required empty public constructor
	}

	
	
	/* (non-Javadoc)
	 * @see pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment#loadProductItemDetails(pl.wasat.smarthma.model.feed.Entry)
	 */
	@Override
	public void loadProductItemDetails(Entry entry) {
		SearchProductDetailsFragment fragment = SearchProductDetailsFragment
				.newInstance(entry);
		getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.search_results_detail_container, fragment)
				.commit();

		super.loadProductItemDetails(entry);
	}

	/* (non-Javadoc)
	 * @see pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment#loadSearchResultProductsIntroDetailsFrag(pl.wasat.smarthma.model.feed.Feed)
	 */
	@Override
	public void loadSearchResultProductsIntroDetailsFrag(Feed searchProductFeeds) {
		FeedSummarySearchFragment productsIntroFragment = FeedSummarySearchFragment
				.newInstance(searchProductFeeds);
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.search_results_detail_container,
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
				.replace(R.id.search_results_list_container, failureFragment)
				.commit();
		super.loadFailureFrag();
	}

}
