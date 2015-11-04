package pl.wasat.smarthma.ui.frags.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wunderlist.slidinglayer.SlidingLayer;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.utils.time.DateUtils;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link BaseViewAndBasicSettingsDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseViewAndBasicSettingsDetailFragment extends BaseDateTimeAreaContainerFragment {
    protected static final String KEY_COLLECTION_ENTRY = "pl.wasat.smarthma.KEY_COLLECTION_ENTRY";

    protected static TextView tvParentId;

    protected Button btnShowProducts;
    protected Button btnShowMetadata;

    protected SlidingLayer mSlidingLayer;
    protected LinearLayout layoutSpinners;

    protected View rootView;

    protected EntryISO displayedISOEntry;
    protected String collectionName;

    // private LatLngBoundsExt geoBounds = null;


    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param collectionEntry Parameter 1.
     * @return A new instance of fragment
     * BaseViewAndBasicSettingsDetailFragment.
     */
    public static BaseViewAndBasicSettingsDetailFragment newInstance(
            EntryISO collectionEntry) {
        BaseViewAndBasicSettingsDetailFragment fragment = new BaseViewAndBasicSettingsDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_COLLECTION_ENTRY, collectionEntry);
        fragment.setArguments(args);
        return fragment;
    }

    public BaseViewAndBasicSettingsDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            displayedISOEntry = (EntryISO) getArguments().getSerializable(
                    KEY_COLLECTION_ENTRY);
        }
        sharedPrefs = new SharedPrefs(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String parentID = "";

        rootView = inflater.inflate(
                R.layout.fragment_map_and_basic_settings_detail, container,
                false);
        //TODO INFOAPPS
        mSlidingLayer = (SlidingLayer) rootView.findViewById(R.id.sliding_layer_with_params);

        tvParentId = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_parent_id);

        if (displayedISOEntry != null) {
            final String title = displayedISOEntry.getTitle();
            final String pubDate = "These data were published: "
                    + DateUtils.getISOPubDate(displayedISOEntry) + " and updated: "
                    + displayedISOEntry.getUpdated();
            parentID = displayedISOEntry.getIdentifier();

            String content = displayedISOEntry.getSummary().getCdata();
            ((TextView) rootView
                    .findViewById(R.id.frag_search_res_coll_det_tv_coll_name))
                    .setText(title);
            ((TextView) rootView
                    .findViewById(R.id.frag_search_res_coll_det_tv_col_dates))
                    .setText(pubDate);
            WebView detailWebView = (WebView) rootView
                    .findViewById(R.id.frag_search_res_coll_det_web);
            detailWebView.loadData(content, "text/html", "UTF-8");

            tvParentId.setText(parentID);
        } else {
            ((TextView) rootView
                    .findViewById(R.id.frag_search_res_coll_det_tv_coll_name))
                    .setText(collectionName);
        }
        sharedPrefs.setParentIdPrefs(parentID);

        tvAreaSWLat = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_area_sw_lat);
        tvAreaSWLon = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_area_sw_lon);
        tvAreaNELat = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_area_ne_lat);
        tvAreaNELon = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_area_ne_lon);

        LinearLayout areaLayout = (LinearLayout) rootView
                .findViewById(R.id.frag_search_res_coll_det_layout_area);
        areaLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDeviceAndLoadMapPicker(R.id.frag_search_res_coll_det_layout_top);
            }
        });

        tvStartDate = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_time_from_date);
        tvStartDate.setTag("tvStartDate");
        tvStartDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerDialog(calStart, tvStartDate);
            }
        });
        tvStartTime = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_time_from_time);
        tvStartTime.setTag("tvStartTime");
        tvStartTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showTimePickerDialog(calStart, tvStartTime);
            }
        });
        tvEndDate = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_time_to_date);
        tvEndDate.setTag("tvEndDate");
        tvEndDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerDialog(calEnd, tvEndDate);

            }
        });
        tvEndTime = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_time_to_time);
        tvEndTime.setTag("tvEndTime");
        tvEndTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showTimePickerDialog(calEnd, tvEndTime);

            }
        });

        loadParentIdPrefs();

        //obtainGlobalSettings();

        //loadBboxPrefs();
        //loadDateTimePrefs();

        layoutSpinners = (LinearLayout) rootView.findViewById(R.id.layout_param_sliders);

        btnShowProducts = (Button) rootView
                .findViewById(R.id.frag_search_res_coll_det_btn_search_product);
        btnShowMetadata = (Button) rootView.findViewById(R.id.frag_search_res_coll_det_btn_show_meta);

        return rootView;
    }


    private void loadParentIdPrefs() {
        tvParentId.setText(sharedPrefs.getParentIdPrefs());
    }

}
