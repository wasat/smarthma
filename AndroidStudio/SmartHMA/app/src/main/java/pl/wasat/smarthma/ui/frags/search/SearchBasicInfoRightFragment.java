package pl.wasat.smarthma.ui.frags.search;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.customviews.SmHmaTimePickerDialog;
import pl.wasat.smarthma.customviews.TimePicker;
import pl.wasat.smarthma.database.SearchHistory;
import pl.wasat.smarthma.database.SearchParams;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment;
import pl.wasat.smarthma.utils.loc.LocManager;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.time.SimpleDate;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link SearchBasicInfoRightFragment.OnSearchBasicInfoRightFragmentListener}
 * interface to handle interaction events. Use the
 * {@link SearchBasicInfoRightFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class SearchBasicInfoRightFragment extends Fragment {
    private static final String KEY_COLLECTION_NAME = "pl.wasat.smarthma.COLLECTION_NAME";
    private static final String KEY_BUTTON_TAG = "pl.wasat.smarthma.KEY_BUTTON_TAG";

    private String paramCollName;

    private TextView tvAreaSWLat;
    private TextView tvAreaSWLon;
    private TextView tvAreaNELat;
    private TextView tvAreaNELon;

    private static TextView tvCatalogName;

    private static Calendar calStart;
    private static Calendar calEnd;
    private static Button btnFromDate;
    private static Button btnFromTime;
    private static Button btnToDate;
    private static Button btnToTime;

    private boolean areaBoundsUpdated = false;

    private OnSearchBasicInfoRightFragmentListener mListener;
    private static SharedPrefs sharedPrefs;

    private static final CharSequence[] cataloguesList = {"EOP:ESA:FEDEO",
            "EOP:ESA:FEDEO:COLLECTIONS", "EOP:ESA:GPOD-EO", "EOP:ESA:EO-VIRTUAL-ARCHIVE4",
            "EOP:ESA:REFERENCEDATA"};

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment SearchBasicInfoRightFragment.
     */
    public static SearchBasicInfoRightFragment newInstance() {
        SearchBasicInfoRightFragment fragment = new SearchBasicInfoRightFragment();
        Bundle args = new Bundle();
        args.putString(KEY_COLLECTION_NAME, "EO Dataset Series Search");
        fragment.setArguments(args);
        return fragment;
    }

    public SearchBasicInfoRightFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paramCollName = getArguments().getString(KEY_COLLECTION_NAME);
        }

        sharedPrefs = new SharedPrefs(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(
                R.layout.fragment_search_right_basic, container, false);

        tvCatalogName = (TextView) rootView
                .findViewById(R.id.search_frag_right_basic_tv_catalog_name);

        tvCatalogName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showCatalogueListDialog();
            }
        });

        sharedPrefs.setParentIdPrefs(tvCatalogName.getText().toString());

        ((TextView) rootView.findViewById(R.id.search_frag_right_basic_tv_name))
                .setText(paramCollName);
        tvAreaSWLat = (TextView) rootView
                .findViewById(R.id.search_frag_right_basic_tv_area_sw_lat);
        tvAreaSWLon = (TextView) rootView
                .findViewById(R.id.search_frag_right_basic_tv_area_sw_lon);
        tvAreaNELat = (TextView) rootView
                .findViewById(R.id.search_frag_right_basic_tv_area_ne_lat);
        tvAreaNELon = (TextView) rootView
                .findViewById(R.id.search_frag_right_basic_tv_area_ne_lon);

        LinearLayout areaLayout = (LinearLayout) rootView
                .findViewById(R.id.search_frag_right_basic_layout_area);
        areaLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Const.IS_KINDLE) {
                    AmznAreaPickerMapFragment areaPickerMapFragment = AmznAreaPickerMapFragment
                            .newInstance();
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.activity_base_details_container,
                                    areaPickerMapFragment)
                            .addToBackStack("AreaPickerMapFragment").commit();
                } else {
                    AreaPickerMapFragment areaPickerMapFragment = AreaPickerMapFragment
                            .newInstance();
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.activity_base_details_container,
                                    areaPickerMapFragment)
                            .addToBackStack("AreaPickerMapFragment").commit();
                }

            }
        });

        btnFromDate = (Button) rootView
                .findViewById(R.id.search_frag_right_basic_buton_time_from_date);
        btnFromDate.setTag("btnFromDate");
        btnFromDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerDialog(btnFromDate);

            }
        });
        btnFromTime = (Button) rootView
                .findViewById(R.id.search_frag_right_basic_buton_time_from_time);
        btnFromTime.setTag("btnFromTime");
        btnFromTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showTimePickerDialog(btnFromTime);

            }
        });
        btnToDate = (Button) rootView
                .findViewById(R.id.search_frag_right_basic_buton_time_to_date);
        btnToDate.setTag("btnToDate");
        btnToDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerDialog(btnToDate);

            }
        });
        btnToTime = (Button) rootView
                .findViewById(R.id.search_frag_right_basic_buton_time_to_time);
        btnToTime.setTag("btnToTime");
        btnToTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showTimePickerDialog(btnToTime);

            }
        });

        updateSearchAreaBounds();
        setInitDateTime();

        SearchHistory searchHistory = new SearchHistory(getActivity());
        ArrayList<SearchParams> searchHistoryList = searchHistory.getSearchHistoryList(true);
        if (!searchHistoryList.isEmpty()) {
            SearchParams searchParams = searchHistoryList.get(0);
            setCatalogue(searchParams.getCatalogue());
            setBbox(searchParams.getBbox());
            setStartDate(searchParams.getStartDate());
            setEndDate(searchParams.getEndDate());
        }

        return rootView;
    }

    @Override
    public void onResume() {
        sharedPrefs.setParentIdPrefs(tvCatalogName.getText().toString());
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnSearchBasicInfoRightFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSearchBasicInfoRightFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSearchBasicInfoRightFragmentListener {
    }

    private void updateSearchAreaBounds() {
        String bboxWest;
        String bboxSouth;
        String bboxEast;
        String bboxNorth;

        LocManager locManager = new LocManager(getActivity());
        Location location = locManager.getAvailableLocation();

        if (location != null) {
            bboxWest = String.format(Locale.UK, "% 4f",
                    (float) location.getLongitude() - 0.5);
            bboxSouth = String.format(Locale.UK, "% 4f",
                    (float) (location.getLatitude() - 0.5));
            bboxEast = String.format(Locale.UK, "% 4f",
                    (float) location.getLongitude() + 0.5);
            bboxNorth = String.format(Locale.UK, "% 4f",
                    (float) location.getLatitude() + 0.5);
        } else {
            bboxWest = "0.0";
            bboxSouth = "0.0";
            bboxEast = "0.0";
            bboxNorth = "0.0";
        }

        tvAreaSWLat.setText(bboxSouth);
        tvAreaSWLon.setText(bboxWest);
        tvAreaNELat.setText(bboxNorth);
        tvAreaNELon.setText(bboxEast);

        sharedPrefs.setBboxPrefs(bboxWest, bboxSouth, bboxEast, bboxNorth);

        areaBoundsUpdated = true;
    }

    public boolean getAreaBoundsUpdated() {
        return areaBoundsUpdated;
    }

    public void setAreaBoundsUpdated(boolean areaBoundsUpdated) {
        this.areaBoundsUpdated = areaBoundsUpdated;
    }

    private static Calendar getCalStart() {
        return calStart;
    }

    private static Calendar getCalEnd() {
        return calEnd;
    }

    /**
     *
     */
    private void setInitDateTime() {

        calStart = Calendar.getInstance();
        calStart.roll(Calendar.MONTH, -1);
        btnFromDate.setText(formatDate(calStart));
        btnFromTime.setText(formatTime(calStart));

        calEnd = Calendar.getInstance();
        btnToDate.setText(formatDate(calEnd));
        btnToTime.setText(formatTime(calEnd));

        sharedPrefs.setDateTimePrefs(setDtISO(calStart), setDtISO(calEnd));
    }

    void showCatalogueListDialog() {
        CatalogueListDialogFragment listDialFrag = new CatalogueListDialogFragment();
        listDialFrag.show(getActivity().getSupportFragmentManager(),
                "CatalogueListDialogFragment");
    }

    void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putString(KEY_BUTTON_TAG, (String) v.getTag());
        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(),
                "datePicker");
    }

    void showTimePickerDialog(View v) {
        DialogFragment newFragment = new MyTimePickerFragment();
        Bundle args = new Bundle();
        args.putString(KEY_BUTTON_TAG, (String) v.getTag());
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
                buttonTag = getArguments().getString(KEY_BUTTON_TAG);
            }

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            if (buttonTag.equalsIgnoreCase("btnFromDate")) {
                calStart.set(year, month, day);
                String dateToSet = formatDate(calStart);
                btnFromDate.setText(dateToSet);

            } else if (buttonTag.equalsIgnoreCase("btnToDate")) {
                calEnd.set(year, month, day);
                String dateToSet = formatDate(calEnd);
                btnToDate.setText(dateToSet);
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
                buttonTag = getArguments().getString(KEY_BUTTON_TAG);
            }
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            int second = c.get(Calendar.SECOND);

            // Create a new instance of TimePickerDialog and return it
            return new SmHmaTimePickerDialog(getActivity(), this, hour, minute,
                    second, true);

        }

        /*
         * (non-Javadoc)
         *
         * @see com.ikovac.timepickerwithseconds.view.MyTimePickerDialog.
         * OnTimeSetListener
         * #onTimeSet(com.ikovac.timepickerwithseconds.view.TimePicker, int,
         * int, int)
         */
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute,
                              int seconds) {

            if (buttonTag.equalsIgnoreCase("btnFromTime")) {
                calStart.set(calStart.get(Calendar.YEAR),
                        calStart.get(Calendar.MONTH),
                        calStart.get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
                        seconds);
                String timeToSet = formatTime(calStart);
                btnFromTime.setText(timeToSet);

            } else if (buttonTag.equalsIgnoreCase("btnToTime")) {
                calEnd.set(calEnd.get(Calendar.YEAR),
                        calEnd.get(Calendar.MONTH),
                        calEnd.get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
                        seconds);
                String timeToSet = formatTime(calEnd);
                btnToTime.setText(timeToSet);
            }
            sharedPrefs.setDateTimePrefs(setDtISO(calStart), setDtISO(calEnd));
        }
    }

    public static class CatalogueListDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.eo_catalogue_list_title).setItems(
                    cataloguesList, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            setCatalogue(which);
                        }
                    });
            return builder.create();
        }
    }

    private static void setCatalogue(int which) {
        tvCatalogName.setText(cataloguesList[which]);
        sharedPrefs.setParentIdPrefs(cataloguesList[which].toString());
    }

    private static void setDateTime(Calendar calStart, Calendar calEnd) {
        SearchBasicInfoRightFragment.calStart = calStart;
        SearchBasicInfoRightFragment.calEnd = calEnd;

        String dateToSet = formatDate(calStart);
        btnFromDate.setText(dateToSet);
        String timeToSet = formatTime(calStart);
        btnFromTime.setText(timeToSet);

        String dateToSet2 = formatDate(calEnd);
        btnToDate.setText(dateToSet2);
        String timeToSet2 = formatTime(calEnd);
        btnToTime.setText(timeToSet2);

        sharedPrefs.setDateTimePrefs(setDtISO(calStart), setDtISO(calEnd));
    }

    void setBounds(String bboxWest, String bboxSouth, String bboxEast, String bboxNorth) {
        tvAreaSWLat.setText(bboxSouth);
        tvAreaSWLon.setText(bboxWest);
        tvAreaNELat.setText(bboxNorth);
        tvAreaNELon.setText(bboxEast);

        sharedPrefs.setBboxPrefs(bboxWest, bboxSouth, bboxEast, bboxNorth);
    }

    public void setCatalogue(String catalogue) {
        CharSequence[] cataloguesList = getCataloguesList();
        for (int i = 0; i < cataloguesList.length; i++) {
            if (catalogue.equalsIgnoreCase(cataloguesList[i].toString())) {
                setCatalogue(i);
            }
        }
    }

    public void setBbox(String boundingBox) {
        String[] bbox = boundingBox.split(",");
        setBounds(bbox[0], bbox[1], bbox[2], bbox[3]);
        areaBoundsUpdated = true;
    }

    public void setStartDate(String startDate) {
        Calendar calStart = Calendar.getInstance();
        SimpleDate dateStart = new SimpleDate(startDate);
        calStart.set(dateStart.getYear(), dateStart.getMonth() - 1, dateStart.getDay(),
                dateStart.getHours(), dateStart.getMinutes(), dateStart.getSeconds());
        setDateTime(calStart, getCalEnd());
    }

    public void setEndDate(String endDate) {
        Calendar calEnd = Calendar.getInstance();
        SimpleDate dateEnd = new SimpleDate(endDate);
        calEnd.set(dateEnd.getYear(), dateEnd.getMonth() - 1, dateEnd.getDay(),
                dateEnd.getHours(), dateEnd.getMinutes(), dateEnd.getSeconds());
        setDateTime(getCalStart(), calEnd);
    }

    /**
     * @param cal - Calendar
     * @return - String with date
     */
    private static String formatDate(Calendar cal) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        return df.format(cal.getTime());
    }

    /**
     * @param cal - Calendar
     * @return - String of time
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

    /**
     * @param bounds - Bounding Box
     */
    public void updateCollectionsAreaBounds(LatLngBoundsExt bounds) {

        String bboxWest = String.format(Locale.UK, "% 4f",
                (float) bounds.southwest.longitude);
        String bboxSouth = String.format(Locale.UK, "% 4f",
                (float) bounds.southwest.latitude);
        String bboxEast = String.format(Locale.UK, "% 4f",
                (float) bounds.northeast.longitude);
        String bboxNorth = String.format(Locale.UK, "% 4f",
                (float) bounds.northeast.latitude);

        tvAreaSWLat.setText(bboxSouth);
        tvAreaSWLon.setText(bboxWest);
        tvAreaNELat.setText(bboxNorth);
        tvAreaNELon.setText(bboxEast);

        sharedPrefs.setBboxPrefs(bboxWest, bboxSouth, bboxEast, bboxNorth);

        areaBoundsUpdated = true;
    }

    @SuppressWarnings("SameReturnValue")
    private static CharSequence[] getCataloguesList() {
        return cataloguesList;
    }
}
