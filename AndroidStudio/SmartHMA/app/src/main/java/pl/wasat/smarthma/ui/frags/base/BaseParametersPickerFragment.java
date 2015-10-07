package pl.wasat.smarthma.ui.frags.base;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment;
import pl.wasat.smarthma.ui.frags.common.DatePickerFragment;
import pl.wasat.smarthma.ui.frags.common.TimePickerFragment;
import pl.wasat.smarthma.utils.loc.GoogleLocProviderImpl;
import pl.wasat.smarthma.utils.time.DateUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseParametersPickerFragment extends BaseSpiceFragment {

    static Calendar calStart;
    static Calendar calEnd;

    static TextView tvStartDate;
    static TextView tvStartTime;
    static TextView tvEndDate;
    static TextView tvEndTime;

    TextView tvAreaSWLat;
    TextView tvAreaSWLon;
    TextView tvAreaNELat;
    TextView tvAreaNELon;

    static SharedPrefs sharedPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefs = new SharedPrefs(getActivity());
    }

    void setInitDateTime() {

        calStart = Calendar.getInstance();
        calStart.roll(Calendar.MONTH, -1);
        tvStartDate.setText(DateUtils.calendarToDateString(calStart));
        tvStartTime.setText(DateUtils.calendarToTimeString(calStart));

        calEnd = Calendar.getInstance();
        tvEndDate.setText(DateUtils.calendarToDateString(calEnd));
        tvEndTime.setText(DateUtils.calendarToTimeString(calEnd));

        sharedPrefs.setDateTimePrefs(DateUtils.calendarToISO(calStart), DateUtils.calendarToISO(calEnd));
    }

    void showDatePickerDialog(Calendar calendar, View v) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putString(Const.KEY_DATE_PICKER_DT_VIEW_TAG, (String) v.getTag());
        args.putSerializable(Const.KEY_DATE_TIME_PICKER_CALENDAR, calendar);
        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(),
                "datePicker");
    }

    void showTimePickerDialog(Calendar calendar, View v) {
        DialogFragment newFragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putString(Const.KEY_DATE_PICKER_DT_VIEW_TAG, (String) v.getTag());
        args.putSerializable(Const.KEY_DATE_TIME_PICKER_CALENDAR, calendar);
        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(),
                "timePicker");
    }

    public void setDateValues(Calendar calendar, String viewTag) {

        if (viewTag.equalsIgnoreCase("tvStartDate")) {
            calStart = calendar;
            String dateToSet = DateUtils.calendarToDateString(calStart);
            tvStartDate.setText(dateToSet);

        } else if (viewTag.equalsIgnoreCase("tvEndDate")) {
            calEnd = calendar;
            String dateToSet = DateUtils.calendarToDateString(calEnd);
            tvEndDate.setText(dateToSet);
        }
        sharedPrefs.setDateTimePrefs(DateUtils.calendarToISO(calStart), DateUtils.calendarToISO(calEnd));
    }

    public void setTimeValues(Calendar calendar, String timeViewTag) {
        if (timeViewTag.equalsIgnoreCase("tvStartTime")) {
            calStart = calendar;
            String timeToSet = DateUtils.calendarToTimeString(calStart);
            tvStartTime.setText(timeToSet);

        } else if (timeViewTag.equalsIgnoreCase("tvEndTime")) {
            calEnd = calendar;
            String timeToSet = DateUtils.calendarToTimeString(calEnd);
            tvEndTime.setText(timeToSet);
        }
        sharedPrefs.setDateTimePrefs(DateUtils.calendarToISO(calStart), DateUtils.calendarToISO(calEnd));
    }

    void checkDeviceAndLoadMapPicker(int container_id) {
        if (Const.IS_KINDLE) {
            openAreaPickerAmazonMapFragment(container_id);
        } else {
            openAreaPickerGoogleMapFragment(container_id);
        }
    }

    private void openAreaPickerAmazonMapFragment(int container_id) {
        AmznAreaPickerMapFragment areaPickerMapFragment = AmznAreaPickerMapFragment
                .newInstance();
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(container_id,
                        areaPickerMapFragment)
                .addToBackStack("AreaPickerMapFragment").commit();
    }

    private void openAreaPickerGoogleMapFragment(int container_id) {
        AreaPickerMapFragment areaPickerMapFragment = AreaPickerMapFragment
                .newInstance();
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(container_id,
                        areaPickerMapFragment)
                .addToBackStack("AreaPickerMapFragment")
                .commit();

    }

    void obtainGooglePosition() {
        GoogleLocProviderImpl googleLocProvider = new GoogleLocProviderImpl(getActivity()) {
            @Override
            public void onLocationReceived(Location receivedLoc) {
                updateSearchAreaBounds(receivedLoc);
            }
        };
        googleLocProvider.start();
    }


    private void updateSearchAreaBounds(Location location) {
        String bboxWest;
        String bboxSouth;
        String bboxEast;
        String bboxNorth;

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
            //EUROPE
            bboxWest = Const.EU_BBOX_WEST;
            bboxSouth = Const.EU_BBOX_SOUTH;
            bboxEast = Const.EU_BBOX_EAST;
            bboxNorth = Const.EU_BBOX_NORTH;
        }

        tvAreaSWLat.setText(bboxSouth);
        tvAreaSWLon.setText(bboxWest);
        tvAreaNELat.setText(bboxNorth);
        tvAreaNELon.setText(bboxEast);

        sharedPrefs.setBboxPrefs(bboxWest, bboxSouth, bboxEast, bboxNorth);

        //areaBoundsUpdated = true;
    }

}
