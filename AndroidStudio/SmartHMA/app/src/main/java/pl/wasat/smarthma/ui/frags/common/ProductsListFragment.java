package pl.wasat.smarthma.ui.frags.common;

import android.os.Bundle;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.om.EntryOM;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link ProductsListFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class ProductsListFragment extends BaseShowProductsListFragment {
	private static final String KEY_PARAM_FEDEO_REQUEST = "pl.wasat.samrthma.KEY_PARAM_FEDEO_REQUEST";

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param fedeoRequest
	 *            Parameter 1.
	 * @return A new instance of fragment SearchProductsFeedsFragment.
	 */
	public static ProductsListFragment newInstance(
			FedeoRequest fedeoRequest) {
		ProductsListFragment fragment = new ProductsListFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_PARAM_FEDEO_REQUEST, fedeoRequest);
		fragment.setArguments(args);
		return fragment;
	}

	public ProductsListFragment() {
		// Required empty public constructor
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment#
	 * loadProductItemDetails(pl.wasat.smarthma.model.eo.Entry)
	 */
	@Override
	public void loadProductItemDetails(EntryOM entry) {
		ProductDetailsFragment productDetailsFragment = ProductDetailsFragment
				.newInstance(entry);
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_details_container,
						productDetailsFragment,
						"ProductDetailsFragment")
				.addToBackStack("ProductDetailsFragment").commit();

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
		FeedSummaryProductsFragment productsIntroFragment = FeedSummaryProductsFragment
				.newInstance(searchProductFeeds);
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.activity_base_details_container,
						productsIntroFragment, "FeedSummaryProductsFragment")
				.addToBackStack("FeedSummaryProductsFragment").commit();
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
				.replace(R.id.activity_base_list_container, failureFragment)
				.commit();
		super.loadFailureFrag();
	}

}
