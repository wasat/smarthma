package pl.wasat.smarthma.ui.frags.base;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.feed.Feed;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class BaseFeedSummaryFragment extends Fragment {
	protected static final String KEY_FEED_SUMMARY = "pl.wasat.smarthma.KEY_FEED_SUMMARY";

	private Feed resultFeed;
	private TextView tvTitle;
	private TextView tvSearchTerms;
	private TextView tvParentName;
	private TextView tvTimeStart;
	private TextView tvTimeEnd;
	private TextView tvArea;
	private TextView tvUpdated;
	private TextView tvTotal;
	private TextView tvItemsFrom;
	private TextView tvItemsTo;

	private Button btnFirst;
	private Button btnPrev;
	private Button btnReload;
	private Button btnNext;
	private Button btnLast;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			resultFeed = (Feed) getArguments()
					.getSerializable(KEY_FEED_SUMMARY);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(
				R.layout.fragment_search_data_series_intro, container, false);

		tvTitle = (TextView) rootView
				.findViewById(R.id.search_frag_ds_intro_title);
		tvSearchTerms = (TextView) rootView
				.findViewById(R.id.search_frag_ds_intro_search_terms_value);
		tvParentName = (TextView) rootView
				.findViewById(R.id.search_frag_ds_intro_collection_value);
		tvTimeStart = (TextView) rootView
				.findViewById(R.id.search_frag_ds_intro_time_start_value);
		tvTimeEnd = (TextView) rootView
				.findViewById(R.id.search_frag_ds_intro_time_end_value);
		tvArea = (TextView) rootView
				.findViewById(R.id.search_frag_ds_intro_area_value);
		tvUpdated = (TextView) rootView
				.findViewById(R.id.search_frag_ds_intro_updated_value);
		tvTotal = (TextView) rootView
				.findViewById(R.id.search_frag_ds_intro_total_value);
		tvItemsFrom = (TextView) rootView
				.findViewById(R.id.search_frag_ds_intro_items_from_value);
		tvItemsTo = (TextView) rootView
				.findViewById(R.id.search_frag_ds_intro_items_to_value);

		btnFirst = (Button) rootView
				.findViewById(R.id.search_frag_ds_intro_button_first);
		btnPrev = (Button) rootView
				.findViewById(R.id.search_frag_ds_intro_button_prev);
		btnReload = (Button) rootView
				.findViewById(R.id.search_frag_ds_intro_button_reload);
		btnNext = (Button) rootView
				.findViewById(R.id.search_frag_ds_intro_button_next);
		btnLast = (Button) rootView
				.findViewById(R.id.search_frag_ds_intro_button_last);

		initUITexts();
		initUIButtons();

		return rootView;
	}

	/**
	 * 
	 */
	private void initUITexts() {
		tvTitle.setText(resultFeed.getTitle());
		tvSearchTerms.setText(resultFeed.getQuery().get_searchTerms());
		tvParentName.setText(resultFeed.getQuery().get_dc_subject());
		tvTimeStart.setText(resultFeed.getQuery().get_time_start());
		tvTimeEnd.setText(resultFeed.getQuery().get_time_end());
		tvArea.setText(resultFeed.getQuery().get_geo_box());
		tvUpdated.setText(resultFeed.getUpdated());
		tvTotal.setText(resultFeed.getTotalResults().get__text());
		tvItemsFrom.setText(resultFeed.getStartIndex().get__text());
		int toVal = Integer.valueOf(resultFeed.getStartIndex().get__text())
				+ Integer.valueOf(resultFeed.getItemsPerPage().get__text()) - 1;
		if (toVal > Integer.valueOf(resultFeed.getTotalResults().get__text())) {
			toVal = Integer.valueOf(resultFeed.getTotalResults().get__text());
		}
		tvItemsTo.setText(String.valueOf(toVal));
	}

	private void initUIButtons() {
		int linkSize = resultFeed.getLink().size();

		for (int i = 0; i < linkSize; i++) {
			String linkRel = resultFeed.getLink().get(i).get_rel();
			final int incFinal = i;

			if (linkRel.equalsIgnoreCase("first")) {
				btnFirst.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String linkHref = resultFeed.getLink().get(incFinal)
								.get_href();
						loadNavSearch(linkHref);
					}
				});
			} else if (linkRel.equalsIgnoreCase("previous")) {
				btnPrev.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String linkHref = resultFeed.getLink().get(incFinal)
								.get_href();
						loadNavSearch(linkHref);
					}
				});
			} else if (linkRel.equalsIgnoreCase("self")) {
				btnReload.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String linkHref = resultFeed.getLink().get(incFinal)
								.get_href();
						loadNavSearch(linkHref);
					}
				});
			} else if (linkRel.equalsIgnoreCase("next")) {
				btnNext.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String linkHref = resultFeed.getLink().get(incFinal)
								.get_href();
						loadNavSearch(linkHref);
					}
				});
			} else if (linkRel.equalsIgnoreCase("last")) {
				btnLast.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String linkHref = resultFeed.getLink().get(incFinal)
								.get_href();
						loadNavSearch(linkHref);
					}
				});
			}
		}
	}

	protected void loadNavSearch(String linkHref) {
	}

}
