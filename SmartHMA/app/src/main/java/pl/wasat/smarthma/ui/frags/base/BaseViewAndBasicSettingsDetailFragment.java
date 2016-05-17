package pl.wasat.smarthma.ui.frags.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.wunderlist.slidinglayer.SlidingLayer;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.model.osdd.Parameter;
import pl.wasat.smarthma.model.osdd.Url;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.utils.request.FedeoOSDDRequest;
import pl.wasat.smarthma.utils.time.DateUtils;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link BaseViewAndBasicSettingsDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseViewAndBasicSettingsDetailFragment extends BaseDateTimeAreaContainerFragment implements RequestListener<OpenSearchDescription> {

    protected static final String KEY_COLLECTION_ENTRY = "pl.wasat.smarthma.KEY_COLLECTION_ENTRY";

    protected static TextView tvParentId;

    protected Button btnShowProducts;
    protected Button btnShowMetadata;

    protected SlidingLayer mSlidingLayer;
    protected LinearLayout layoutSpinners;

    protected View rootView;

    protected EntryISO displayedISOEntry;
    protected String collectionName;

    protected List<Parameter> osddParamsList;
    protected FedeoRequestParams fedeoRequestParams;

    public BaseViewAndBasicSettingsDetailFragment() {
    }

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
        mSlidingLayer = (SlidingLayer) rootView.findViewById(R.id.sliding_layer_with_params);

        tvParentId = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_parent_id);

        if (displayedISOEntry != null) {
            final String title = displayedISOEntry.getTitle();
            final String pubDate = String.format(getString(R.string.these_data_are_published_and_updated),
                    DateUtils.getISOPubDate(displayedISOEntry),
                    displayedISOEntry.getUpdated());
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
            detailWebView.setBackgroundColor(Color.TRANSPARENT);
            detailWebView.loadData(content, "text/html", "UTF-8");

            tvParentId.setText(parentID);
        } else {
            ((TextView) rootView
                    .findViewById(R.id.frag_search_res_coll_det_tv_coll_name))
                    .setText(collectionName);
        }
        sharedPrefs.setParentIdPrefs(parentID);

        final LinearLayout areaLayout = (LinearLayout) rootView
                .findViewById(R.id.frag_search_res_coll_det_layout_area);
        areaLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDeviceAndLoadMapPicker(R.id.frag_search_res_coll_det_layout_top);
            }
        });
        final LinearLayout areaValuesLayout = (LinearLayout) rootView
                .findViewById(R.id.frag_search_res_coll_det_layout_area_values);
        checkBoxArea = (CheckBox) rootView
                .findViewById(R.id.frag_search_res_coll_det_checkbox_area);
        checkBoxArea.setChecked(sharedPrefs.getTimeUse());
        checkBoxArea.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setAreaViewParameters(areaValuesLayout, areaLayout, checkBoxArea.isChecked());
            }
        });
        setAreaViewParameters(areaValuesLayout, areaLayout, sharedPrefs.getAreaUse());
        tvAreaTitleNEPt = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_area_title_ne_pt);
        tvAreaNEPtLat = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_area_ne_lat);
        tvAreaNEPtLon = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_area_ne_lon);
        tvAreaTitleSWRad = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_area_title_sw_rad);
        tvAreaSWRadLat = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_area_sw_lat);
        tvAreaSWKmLon = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_area_sw_lon);

        final LinearLayout timeValuesLayout = (LinearLayout) rootView
                .findViewById(R.id.frag_search_res_coll_det_layout_time_values);
        checkBoxTime = (CheckBox) rootView
                .findViewById(R.id.frag_search_res_coll_det_checkbox_time);
        checkBoxTime.setChecked(sharedPrefs.getTimeUse());
        checkBoxTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeViewParameters(timeValuesLayout, checkBoxTime.isChecked());
            }
        });
        setTimeViewParameters(timeValuesLayout, sharedPrefs.getTimeUse());
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

        layoutSpinners = (LinearLayout) rootView.findViewById(R.id.layout_param_sliders);

        btnShowProducts = (Button) rootView
                .findViewById(R.id.frag_search_res_coll_det_btn_search_product);
        btnShowMetadata = (Button) rootView.findViewById(R.id.frag_search_res_coll_det_btn_show_meta);

        return rootView;
    }


    protected void startAsyncLoadOsddData() {
        String url = getOsddSearchLink();
        startAsyncLoadOsddData(url);
    }

    public void startAsyncLoadOsddData(String osddUrl) {
        if (!osddUrl.isEmpty()) {
            //GenericUrl fedeoDescUrl = new GenericUrl(osddUrl);
            getActivity().setProgressBarIndeterminateVisibility(true);
            getSpiceManager().execute(new FedeoOSDDRequest(osddUrl),
                    this);
        }
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        parseRequestFailure(spiceException);
    }

    @Override
    public void onRequestSuccess(OpenSearchDescription openSearchDescription) {
        loadRequestSuccess(openSearchDescription);
        obtainGlobalSettings();
    }

    private void loadRequestSuccess(OpenSearchDescription osdd) {
        getActivity().setProgressBarIndeterminateVisibility(false);
        if (osdd != null) {
            loadOsddParameters(osdd, Link.REL_RESULTS);
        }
    }

    protected void loadOsddParameters(OpenSearchDescription osdd, String relResults) {
        osddParamsList = new ArrayList<>();
        fedeoRequestParams = new FedeoRequestParams();

        for (Url osddUrl : osdd.getUrl()) {
            if (osddUrl.getType().equalsIgnoreCase(Link.TYPE_ATOM_XML) &&
                    osddUrl.getRel().equalsIgnoreCase(relResults)) {
                defineOsddSearchParams(osddUrl);
            }
        }
    }

    private void defineOsddSearchParams(Url osddUrl) {
        osddParamsList = osddUrl.getParameters();
        fedeoRequestParams.setTemplateUrl(osddUrl.getTemplate());
    }

    private void loadParentIdPrefs() {
        tvParentId.setText(sharedPrefs.getParentIdPrefs());
    }

    public String getOsddSearchLink() {
        if (displayedISOEntry != null) {
            return displayedISOEntry.getSpecLink(Link.REL_SEARCH, Link.TYPE_OSDD_XML);
        } else {
            return ""; //Const.OSDD_BASE_URL;
        }
    }
}
