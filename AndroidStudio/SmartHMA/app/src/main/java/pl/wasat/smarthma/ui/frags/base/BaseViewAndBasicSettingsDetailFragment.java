package pl.wasat.smarthma.ui.frags.base;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wunderlist.slidinglayer.SlidingLayer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.customviews.SmHmaTimePickerDialog;
import pl.wasat.smarthma.customviews.TimePicker;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.obj.LatLngExt;
import pl.wasat.smarthma.utils.time.DateUtils;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link BaseViewAndBasicSettingsDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseViewAndBasicSettingsDetailFragment extends BaseSpiceFragment {
    protected static final String KEY_COLLECTION_ENTRY = "pl.wasat.smarthma.KEY_COLLECTION_ENTRY";
    private static final String KEY_TEXTVIEW_TAG = "pl.wasat.smarthma.KEY_TEXTVIEW_TAG";

    private TextView tvAreaSWLat;
    private TextView tvAreaSWLon;
    private TextView tvAreaNELat;
    private TextView tvAreaNELon;

    protected static TextView tvParentId;

    private static Calendar calStart;
    private static Calendar calEnd;
    private static TextView tvFromDate;
    private static TextView tvFromTime;
    private static TextView tvToDate;
    private static TextView tvToTime;

    protected Button btnShowProducts;
    protected Button btnShowMetadata;

    protected SlidingLayer mSlidingLayer;
    protected LinearLayout layoutSpinners;

    protected View rootView;

    protected EntryISO displayedISOEntry;
    protected String collectionName;
    private static SharedPrefs sharedPrefs;
    private LatLngBoundsExt geoBounds = null;


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
        // Required empty public constructor
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

        mSlidingLayer = (SlidingLayer) rootView.findViewById(R.id.slidingLayer1);

        //txtParamsSliderHeader = (TextView) rootView.findViewById(R.id.txt_slider_params_header);

        tvParentId = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_parent_id);

        if (displayedISOEntry != null) {
            final String title = displayedISOEntry.getTitle();
/*            String dateStr = displayedISOEntry.getDate().toString();
            if (dateStr.isEmpty()) {
                dateStr = "1970-01-01T00:00:00.000Z";
            } else {
                dateStr = displayedISOEntry.getDate().getCIDate().getDateInCIDate().getDateGco().getText();
            }*/
            final String pubDate = "This data were published: "
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

        // sharedPrefs.setParentIdPrefs(tvParentId.getText().toString());
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
                if (Const.IS_KINDLE) {
                    AmznAreaPickerMapFragment areaPickerMapFragment = AmznAreaPickerMapFragment
                            .newInstance();
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frag_search_res_coll_det_layout_top,
                                    areaPickerMapFragment)
                            .addToBackStack("AreaPickerMapFragment").commit();
                } else {
                    AreaPickerMapFragment areaPickerMapFragment = AreaPickerMapFragment
                            .newInstance();
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frag_search_res_coll_det_layout_top,
                                    areaPickerMapFragment)
                            .addToBackStack("AreaPickerMapFragment").commit();
                }


            }
        });

        tvFromDate = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_time_from_date);
        tvFromDate.setTag("tvFromDate");
        tvFromDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerDialog(tvFromDate);

            }
        });
        tvFromTime = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_time_from_time);
        tvFromTime.setTag("tvFromTime");
        tvFromTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showTimePickerDialog(tvFromTime);

            }
        });
        tvToDate = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_time_to_date);
        tvToDate.setTag("tvToDate");
        tvToDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerDialog(tvToDate);

            }
        });
        tvToTime = (TextView) rootView
                .findViewById(R.id.frag_search_res_coll_det_tv_time_to_time);
        tvToTime.setTag("tvToTime");
        tvToTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showTimePickerDialog(tvToTime);

            }
        });

        setInitDateTime();

        getParentIdPrefs();
        getBboxPrefs();
        getDateTimePrefs();

        layoutSpinners = (LinearLayout) rootView.findViewById(R.id.layout_param_sliders);
/*
        spinnerCount = (Spinner) rootView.findViewById(R.id.spinnerCount);
        spinnerSearchTerms = (Spinner) rootView.findViewById(R.id.spinnerSearchTerms);
        spinnerStartId = (Spinner) rootView.findViewById(R.id.spinnerStartId);
        spinnerTitle = (Spinner) rootView.findViewById(R.id.spinnerTitle);
        spinnerType = (Spinner) rootView.findViewById(R.id.spinnerType);
        spinnerOrbitNo = (Spinner) rootView.findViewById(R.id.spinnerOrbitNo);
        spinnerOrganisationName = (Spinner) rootView.findViewById(R.id.spinnerOrganisationName);
        spinnerParentId = (Spinner) rootView.findViewById(R.id.spinnerParentId);
        spinnerProductType = (Spinner) rootView.findViewById(R.id.spinnerProductType);
        spinnerLat = (Spinner) rootView.findViewById(R.id.spinnerLat);
        spinnerLon = (Spinner) rootView.findViewById(R.id.spinnerLon);
        spinnerGeoName = (Spinner) rootView.findViewById(R.id.spinnerGeoName);
        spinnerRadius = (Spinner) rootView.findViewById(R.id.spinnerRadius);
        spinnerUid = (Spinner) rootView.findViewById(R.id.spinnerUid);
        spinnerClassifiedAs = (Spinner) rootView.findViewById(R.id.spinnerClassifiedAs);
        spinnerRecordSchema = (Spinner) rootView.findViewById(R.id.spinnerRecordSchema);
*/

        btnShowProducts = (Button) rootView
                .findViewById(R.id.frag_search_res_coll_det_btn_search_product);
        btnShowMetadata = (Button) rootView.findViewById(R.id.frag_search_res_coll_det_btn_show_meta);

        return rootView;
    }

    private void getDateTimePrefs() {
        tvFromDate.setText(sharedPrefs.getStartDateTimePrefs());
        tvToDate.setText(sharedPrefs.getEndDateTimePrefs());
    }

    private void getParentIdPrefs() {
        tvParentId.setText(sharedPrefs.getParentIdPrefs());
    }

    private void getBboxPrefs() {

        float west = sharedPrefs.getBboxPrefs()[0];
        float south = sharedPrefs.getBboxPrefs()[1];
        float east = sharedPrefs.getBboxPrefs()[2];
        float north = sharedPrefs.getBboxPrefs()[3];

        tvAreaNELat.setText(String.format(Locale.UK, "% 4f", north));
        tvAreaNELon.setText(String.format(Locale.UK, "% 4f", east));
        tvAreaSWLat.setText(String.format(Locale.UK, "% 4f", south));
        tvAreaSWLon.setText(String.format(Locale.UK, "% 4f", west));

        geoBounds = new LatLngBoundsExt(new LatLngExt(south, west), new LatLngExt(north,
                east));

    }

    /**
     * @param bounds bounding box
     */
    public void updateAreaBounds(LatLngBoundsExt bounds) {
        geoBounds = bounds;
        String west = String.format(Locale.UK, "% 4f",
                bounds.southwest.longitude);
        String south = String.format(Locale.UK, "% 4f",
                bounds.southwest.latitude);
        String east = String.format(Locale.UK, "% 4f",
                bounds.northeast.longitude);
        String north = String.format(Locale.UK, "% 4f",
                bounds.northeast.latitude);

        tvAreaSWLon.setText(west);
        tvAreaSWLat.setText(south);
        tvAreaNELon.setText(east);
        tvAreaNELat.setText(north);

        sharedPrefs.setBboxPrefs(west, south, east, north);
    }

    /**
     *
     */
    private void setInitDateTime() {
        calStart = Calendar.getInstance();
        calEnd = Calendar.getInstance();
    }

    private void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TEXTVIEW_TAG, (String) v.getTag());
        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(),
                "datePicker");
    }

    private void showTimePickerDialog(View v) {
        DialogFragment newFragment = new MyTimePickerFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TEXTVIEW_TAG, (String) v.getTag());
        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(),
                "timePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        private String buttonTag;

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            if (getArguments() != null) {
                buttonTag = getArguments().getString(KEY_TEXTVIEW_TAG);
            }

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            if (buttonTag.equalsIgnoreCase("tvFromDate")) {
                calStart.set(year, month, day);
                // String dateToSet = formatDate(calStart);
                String dateToSet = setDtISO(calStart);
                tvFromDate.setText(dateToSet);

            } else if (buttonTag.equalsIgnoreCase("tvToDate")) {
                calEnd.set(year, month, day);
                // String dateToSet = formatDate(calEnd);
                String dateToSet = setDtISO(calEnd);
                tvToDate.setText(dateToSet);
            }
            sharedPrefs.setDateTimePrefs(setDtISO(calStart), setDtISO(calEnd));

        }
    }

    public static class MyTimePickerFragment extends DialogFragment implements
            SmHmaTimePickerDialog.OnTimeSetListener {

        private String buttonTag;

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker

            if (getArguments() != null) {
                buttonTag = getArguments().getString(KEY_TEXTVIEW_TAG);
            }
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            int second = c.get(Calendar.SECOND);

            // Create a new instance of TimePickerDialog and return it
            return new SmHmaTimePickerDialog(getActivity(), this, hour, minute,
                    second, true);

        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute,
                              int seconds) {

            if (buttonTag.equalsIgnoreCase("tvFromTime")) {
                calStart.set(calStart.get(Calendar.YEAR),
                        calStart.get(Calendar.MONTH),
                        calStart.get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
                        seconds);
                String timeToSet = formatTime(calStart);
                tvFromTime.setText(timeToSet);

            } else if (buttonTag.equalsIgnoreCase("tvToTime")) {
                calEnd.set(calEnd.get(Calendar.YEAR),
                        calEnd.get(Calendar.MONTH),
                        calEnd.get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
                        seconds);
                String timeToSet = formatTime(calEnd);
                tvToTime.setText(timeToSet);
            }
            sharedPrefs.setDateTimePrefs(setDtISO(calStart), setDtISO(calEnd));
        }
    }

    /**
     * @param cal calendar
     * @return Time in String format
     */
    private static String formatTime(Calendar cal) {
        SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm:ss", Locale.UK);
        return dfTime.format(cal.getTime());
    }

    private static String setDtISO(Calendar cal) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.UK);
        return df.format(cal.getTime());

    }

}
