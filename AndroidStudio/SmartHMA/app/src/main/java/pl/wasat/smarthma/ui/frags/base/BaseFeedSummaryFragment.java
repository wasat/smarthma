package pl.wasat.smarthma.ui.frags.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Global;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.om.EntryOM;

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

    private Spinner spnSortType;


	public BaseFeedSummaryFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			resultFeed = (Feed) getArguments()
					.getSerializable(KEY_FEED_SUMMARY);

            //List<EntryOM> entries = resultFeed.getEntriesEO();
            //sorter.sort(entries);
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

        spnSortType = (Spinner) rootView
                .findViewById(R.id.search_frag_ds_intro_spinner_sort_type);

		initUITexts();
		initUIButtons();

		return rootView;
	}

	/**
	 * 
	 */
	private void initUITexts() {
		tvTitle.setText(resultFeed.getTitle());
		tvSearchTerms.setText(resultFeed.getQuery().getSearchTerms());
		tvParentName.setText(resultFeed.getQuery().getDcSubject());
		tvTimeStart.setText(resultFeed.getQuery().getTimeStart());
		tvTimeEnd.setText(resultFeed.getQuery().getTimeEnd());
		tvArea.setText(resultFeed.getQuery().getGeoBox());
		tvUpdated.setText(resultFeed.getUpdated());
		tvTotal.setText(resultFeed.getTotalResults().getText());
		tvItemsFrom.setText(resultFeed.getStartIndex().getText());
		int toVal = Integer.valueOf(resultFeed.getStartIndex().getText())
				+ Integer.valueOf(resultFeed.getItemsPerPage().getText()) - 1;
		if (toVal > Integer.valueOf(resultFeed.getTotalResults().getText())) {
			toVal = Integer.valueOf(resultFeed.getTotalResults().getText());
		}
		tvItemsTo.setText(String.valueOf(toVal));
	}

	private void initUIButtons() {
		int linkSize = resultFeed.getLink().size();

		for (int i = 0; i < linkSize; i++) {
			String linkRel = resultFeed.getLink().get(i).getRel();
			final int incFinal = i;

			if (linkRel.equalsIgnoreCase("first")) {
				btnFirst.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String linkHref = resultFeed.getLink().get(incFinal)
								.getHref();
						loadNavSearch(linkHref);
					}
				});
			} else if (linkRel.equalsIgnoreCase("previous")) {
				btnPrev.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String linkHref = resultFeed.getLink().get(incFinal)
								.getHref();
						loadNavSearch(linkHref);
					}
				});
			} else if (linkRel.equalsIgnoreCase("self")) {
				btnReload.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String linkHref = resultFeed.getLink().get(incFinal)
								.getHref();
						loadNavSearch(linkHref);
					}
				});
			} else if (linkRel.equalsIgnoreCase("next")) {
				btnNext.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String linkHref = resultFeed.getLink().get(incFinal)
								.getHref();
						loadNavSearch(linkHref);
					}
				});
			} else if (linkRel.equalsIgnoreCase("last")) {
				btnLast.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String linkHref = resultFeed.getLink().get(incFinal)
								.getHref();
						loadNavSearch(linkHref);
					}
				});
			}
		}

        spnSortType.setSelection(Global.sortingType, false);
        spnSortType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position != Global.sortingType)
                {
                    Global.sortingType = position;
                    btnReload.performClick();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
	}

	protected void loadNavSearch(String linkHref) {
	}

}
