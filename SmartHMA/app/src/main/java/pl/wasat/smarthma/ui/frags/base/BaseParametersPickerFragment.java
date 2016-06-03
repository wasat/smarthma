package pl.wasat.smarthma.ui.frags.base;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.obj.LatLngExt;
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
    static SharedPrefs sharedPrefs;
    CheckBox checkBoxArea;
    CheckBox checkBoxTime;
    TextView tvAreaTitleNEPt;
    TextView tvAreaNEPtLat;
    TextView tvAreaNEPtLon;
    TextView tvAreaTitleSWRad;
    TextView tvAreaSWRadLat;
    TextView tvAreaSWKmLon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefs = new SharedPrefs(getActivity());
    }

    void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);

        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;

            for (int idx = 0; idx < group.getChildCount(); idx++) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
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
                DatePickerFragment.class.getSimpleName());
    }

    void showTimePickerDialog(Calendar calendar, View v) {
        DialogFragment newFragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putString(Const.KEY_DATE_PICKER_DT_VIEW_TAG, (String) v.getTag());
        args.putSerializable(Const.KEY_DATE_TIME_PICKER_CALENDAR, calendar);
        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(),
                TimePickerFragment.class.getSimpleName());
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
                        areaPickerMapFragment, AreaPickerMapFragment.class.getSimpleName())
                .addToBackStack(AreaPickerMapFragment.class.getSimpleName()).commit();
    }

    private void openAreaPickerGoogleMapFragment(int container_id) {
        AreaPickerMapFragment areaPickerMapFragment = AreaPickerMapFragment
                .newInstance();
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(container_id,
                        areaPickerMapFragment)
                .addToBackStack(AreaPickerMapFragment.class.getSimpleName())
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

        tvAreaSWRadLat.setText(bboxSouth);
        tvAreaSWKmLon.setText(bboxWest);
        tvAreaNEPtLat.setText(bboxNorth);
        tvAreaNEPtLon.setText(bboxEast);

        sharedPrefs.setBboxPrefs(bboxWest, bboxSouth, bboxEast, bboxNorth);
    }

    LatLngBoundsExt convertPtAndRadiusToBounds(LatLngExt center, double radius) {
        LatLngExt southwest = computeOffset(center, radius * Math.sqrt(2.0), 225);
        LatLngExt northeast = computeOffset(center, radius * Math.sqrt(2.0), 45);
        return new LatLngBoundsExt(southwest, northeast);
    }

    private LatLngExt computeOffset(LatLngExt from, double distance, double heading) {
        distance /= 6371009.0D;
        heading = Math.toRadians(heading);
        double fromLat = Math.toRadians(from.latitude);
        double fromLng = Math.toRadians(from.longitude);
        double cosDistance = Math.cos(distance);
        double sinDistance = Math.sin(distance);
        double sinFromLat = Math.sin(fromLat);
        double cosFromLat = Math.cos(fromLat);
        double sinLat = cosDistance * sinFromLat + sinDistance * cosFromLat * Math.cos(heading);
        double dLng = Math.atan2(sinDistance * cosFromLat * Math.sin(heading), cosDistance - sinFromLat * sinLat);
        return new LatLngExt(Math.toDegrees(Math.asin(sinLat)), Math.toDegrees(fromLng + dLng));
    }
}
