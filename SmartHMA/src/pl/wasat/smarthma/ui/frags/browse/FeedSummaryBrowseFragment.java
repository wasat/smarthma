package pl.wasat.smarthma.ui.frags.browse;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.ui.frags.base.BaseFeedSummaryFragment;
import android.os.Bundle;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link BaseFeedSummaryFragment.OnFeedSummaryFragmentListener} interface to
 * handle interaction events. Use the {@link FeedSummaryBrowseFragment#newInstance}
 * factory method to create an instance of this fragment.
 * 
 */
public class FeedSummaryBrowseFragment extends BaseFeedSummaryFragment {

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param paramSearchFeeds
	 *            Parameter 1. 
	 * @return A new instance of fragment FeedSummaryFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static FeedSummaryBrowseFragment newInstance(Feed feedParam) {
		FeedSummaryBrowseFragment fragment = new FeedSummaryBrowseFragment();
		Bundle args = new Bundle();
		args.putSerializable(BaseFeedSummaryFragment.KEY_FEED_SUMMARY, feedParam);
		fragment.setArguments(args);
		return fragment;
	}

	public FeedSummaryBrowseFragment() {
		// Required empty public constructor
	}
	/* (non-Javadoc)
	 * @see pl.wasat.smarthma.ui.frags.base.FeedSummaryFragment#loadNavSearch(java.lang.String)
	 */
	@Override
	public void loadNavSearch(String linkHref) {
		FedeoRequest request = new FedeoRequest();
		request.setUrl(linkHref);

		DataSeriesListFragment dataseriesListFragment = DataSeriesListFragment
				.newInstance(request);
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.dataseries_list, dataseriesListFragment)
				.commit();
		super.loadNavSearch(linkHref);
	}



}
