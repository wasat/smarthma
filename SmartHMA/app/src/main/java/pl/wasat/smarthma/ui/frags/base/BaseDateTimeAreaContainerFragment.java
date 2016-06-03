package pl.wasat.smarthma.ui.frags.base;

import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Locale;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.enums.Opts;
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.obj.LatLngExt;
import pl.wasat.smarthma.utils.text.StringExt;
import pl.wasat.smarthma.utils.time.DateUtils;
import pl.wasat.smarthma.utils.time.SimpleDate;

import static pl.wasat.smarthma.helper.enums.Opts.AREA_PT_RADIUS;

/**
 * Created by Daniel on 2015-07-23 00:44.
 * Part of the project  SmartHMA
 */
public class BaseDateTimeAreaContainerFragment extends BaseParametersPickerFragment {

    public void setBounds(String boundingBox) {
        String[] bbox = boundingBox.split(",");
        if (bbox.length >= 4) {
            setBounds(bbox[0], bbox[1], bbox[2], bbox[3]);
        }
    }

    /**
     * @param bounds - Bounding Box
     */
    public void updateAreaBounds(LatLngBoundsExt bounds) {

        String bboxWest = String.format(Locale.UK, "% 4f",
                (float) bounds.southwest.longitude);
        String bboxSouth = String.format(Locale.UK, "% 4f",
                (float) bounds.southwest.latitude);
        String bboxEast = String.format(Locale.UK, "% 4f",
                (float) bounds.northeast.longitude);
        String bboxNorth = String.format(Locale.UK, "% 4f",
                (float) bounds.northeast.latitude);

        tvAreaSWRadLat.setText(bboxSouth);
        tvAreaSWKmLon.setText(bboxWest);
        tvAreaNEPtLat.setText(bboxNorth);
        tvAreaNEPtLon.setText(bboxEast);

        sharedPrefs.setBboxPrefs(bboxWest, bboxSouth, bboxEast, bboxNorth);
    }

    public void updateAreaPtAndRadius(LatLngExt center, float radius) {

        String centerLat = StringExt.formatLatLng(center.latitude);
        String centerLng = StringExt.formatLatLng(center.longitude);
        String radiusStr = StringExt.formatDist(radius / 1000);

        tvAreaNEPtLat.setText(centerLat);
        tvAreaNEPtLon.setText(centerLng);
        tvAreaSWRadLat.setText(radiusStr);
        tvAreaSWKmLon.setText(R.string.km);

        LatLngBoundsExt circleAreaBounds = convertPtAndRadiusToBounds(center, radius);
        putBoundsToShared(circleAreaBounds);

        sharedPrefs.setCenterPrefs((float) center.latitude, (float) center.longitude);
        sharedPrefs.setRadiusPrefs(radius);
    }


    private void putBoundsToShared(LatLngBoundsExt bounds) {
        String bboxWest = String.valueOf(bounds.southwest.longitude);
        String bboxSouth = String.valueOf(bounds.southwest.latitude);
        String bboxEast = String.valueOf(bounds.northeast.longitude);
        String bboxNorth = String.valueOf(bounds.northeast.latitude);

        sharedPrefs.setBboxPrefs(bboxWest, bboxSouth, bboxEast, bboxNorth);
    }

    protected void obtainGlobalSettings() {
        GlobalPreferences globalPreferences = new GlobalPreferences(getActivity());
        if (globalPreferences.getIsParamsSaved()) {
            loadSharedData();
        } else {
            obtainGooglePosition();
            setInitDateTime();
        }
    }

    void loadSharedData() {
        loadDateTimePrefs();
        setDateTime();
        loadAreaView();

    }

    private void loadDateTimePrefs() {
        String startDateTime = sharedPrefs.getStartDateTimePrefs();
        setStartCalendar(startDateTime);

        String endDateTime = sharedPrefs.getEndDateTimePrefs();
        setEndCalendar(endDateTime);
    }

    private void setDateTime() {
        String startDateToSet = DateUtils.calendarToDateString(calStart);
        tvStartDate.setText(startDateToSet);
        String startTimeToSet = DateUtils.calendarToTimeString(calStart);
        tvStartTime.setText(startTimeToSet);

        String endDateToSet = DateUtils.calendarToDateString(calEnd);
        tvEndDate.setText(endDateToSet);
        String endTimeToSet = DateUtils.calendarToTimeString(calEnd);
        tvEndTime.setText(endTimeToSet);

        sharedPrefs.setDateTimePrefs(DateUtils.calendarToISO(calStart), DateUtils.calendarToISO(calEnd));
    }

    private void loadAreaView() {
        loadProperAreaView(sharedPrefs.getAreaType());
    }

    public void loadProperAreaView(int areaType) {
        switch (areaType) {
            case Opts.AREA_POLYGON:
                tvAreaTitleNEPt.setText(getResources().getText(R.string.ne_));
                tvAreaTitleSWRad.setText(getResources().getText(R.string.sw_));
                loadBboxPrefs();
                break;
            case AREA_PT_RADIUS:
                tvAreaTitleNEPt.setText(getResources().getString(R.string.center));
                tvAreaTitleSWRad.setText(getResources().getString(R.string.radius));
                loadPointRadiusPrefs();
                break;
            default:
                break;
        }
        sharedPrefs.setAreaType(areaType);
    }

    //Area BBOX Container
    private void loadBboxPrefs() {

        float west = sharedPrefs.getBboxPrefs()[0];
        float south = sharedPrefs.getBboxPrefs()[1];
        float east = sharedPrefs.getBboxPrefs()[2];
        float north = sharedPrefs.getBboxPrefs()[3];

        setBounds(west, south, east, north);
    }

    private void loadPointRadiusPrefs() {
        float[] center = sharedPrefs.getCenterPrefs();
        float radius = sharedPrefs.getRadiusPrefs();
        updateAreaPtAndRadius(new LatLngExt(center[0], center[1]), radius);
    }

    //Date and Time Container
    public void setStartCalendar(String startDate) {
        calStart = Calendar.getInstance();
        try {
            SimpleDate dateStart = new SimpleDate(startDate);
            calStart.set(dateStart.getYear(), dateStart.getMonth() - 1, dateStart.getDay(),
                    dateStart.getHours(), dateStart.getMinutes(), dateStart.getSeconds());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEndCalendar(String endDate) {
        calEnd = Calendar.getInstance();
        try {
            SimpleDate dateEnd = new SimpleDate(endDate);
            calEnd.set(dateEnd.getYear(), dateEnd.getMonth() - 1, dateEnd.getDay(),
                    dateEnd.getHours(), dateEnd.getMinutes(), dateEnd.getSeconds());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setBounds(float west, float south, float east, float north) {
        String westStr = String.valueOf(west);
        String southStr = String.valueOf(south);
        String eastStr = String.valueOf(east);
        String northStr = String.valueOf(north);

        setBounds(westStr, southStr, eastStr, northStr);
    }

    private void setBounds(String bboxWest, String bboxSouth, String bboxEast, String bboxNorth) {
        tvAreaSWRadLat.setText(bboxSouth);
        tvAreaSWKmLon.setText(bboxWest);
        tvAreaNEPtLat.setText(bboxNorth);
        tvAreaNEPtLon.setText(bboxEast);

        sharedPrefs.setBboxPrefs(bboxWest, bboxSouth, bboxEast, bboxNorth);
    }

    void setTimeViewParameters(LinearLayout timeValuesLayout, boolean enabled) {
        //boolean checkValue = checkBoxTime.isChecked();
        checkBoxTime.setChecked(enabled);
        enableDisableView(timeValuesLayout, enabled);
        sharedPrefs.setTimeUse(enabled);
    }

    void setAreaViewParameters(LinearLayout areaValuesLayout, LinearLayout areaLayout, boolean enabled) {
        //boolean checkValue = sharedPrefs.getAreaUse();
        checkBoxArea.setChecked(enabled);
        enableDisableView(areaValuesLayout, enabled);
        areaLayout.setClickable(enabled);
        sharedPrefs.setAreaUse(enabled);
    }

}
