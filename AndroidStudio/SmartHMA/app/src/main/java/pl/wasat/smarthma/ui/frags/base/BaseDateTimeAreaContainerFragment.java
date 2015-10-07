package pl.wasat.smarthma.ui.frags.base;

import java.util.Calendar;
import java.util.Locale;

import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.time.DateUtils;
import pl.wasat.smarthma.utils.time.SimpleDate;

/**
 * Created by Daniel on 2015-07-23 00:44.
 * Part of the project  SmartHMA
 */
public class BaseDateTimeAreaContainerFragment extends BaseParametersPickerFragment {

    protected void obtainGlobalSettings() {
        GlobalPreferences globalPreferences = new GlobalPreferences(getActivity());
        if (globalPreferences.getIsParamsSaved()) {
            loadSharedData();
        } else {
            obtainGooglePosition();
            setInitDateTime();
        }
    }

    protected void loadSharedData() {
        loadDateTimePrefs();
        setDateTime();

        loadBboxPrefs();

        // loadSearchQuery();
    }

/*    private void loadSearchQuery()
    {
        String query = sharedPrefs.getQueryPrefs();
    }*/

    private void loadDateTimePrefs() {
        String startDateTime = sharedPrefs.getStartDateTimePrefs();
        setStartCalendar(startDateTime);

        String endDateTime = sharedPrefs.getEndDateTimePrefs();
        setEndCalendar(endDateTime);
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

    //This function is more read but not made all stuff
/*    protected void loadDateTimePrefs() {
        SimpleDate simpleStartDateTime = new SimpleDate(sharedPrefs.getStartDateTimePrefs());
        String startDateToSet = DateUtils.calendarToDateString(simpleStartDateTime.getCalendar());
        String startTimeToSet = DateUtils.calendarToTimeString(simpleStartDateTime.getCalendar());
        tvStartDate.setText(startDateToSet);
        tvStartTime.setText(startTimeToSet);

        SimpleDate simpleEndDateTime = new SimpleDate(sharedPrefs.getEndDateTimePrefs());
        String endDateToSet = DateUtils.calendarToDateString(simpleEndDateTime.getCalendar());
        String endTimeToSet = DateUtils.calendarToTimeString(simpleEndDateTime.getCalendar());
        tvEndDate.setText(endDateToSet);
        tvEndTime.setText(endTimeToSet);
    }*/


    //Area BBOX Container
    private void loadBboxPrefs() {

        float west = sharedPrefs.getBboxPrefs()[0];
        float south = sharedPrefs.getBboxPrefs()[1];
        float east = sharedPrefs.getBboxPrefs()[2];
        float north = sharedPrefs.getBboxPrefs()[3];

        setBounds(west, south, east, north);

/*        tvAreaNELat.setText(String.format(Locale.UK, "% 4f", north));
        tvAreaNELon.setText(String.format(Locale.UK, "% 4f", east));
        tvAreaSWLat.setText(String.format(Locale.UK, "% 4f", south));
        tvAreaSWLon.setText(String.format(Locale.UK, "% 4f", west));*/
    }

    private void setBounds(float west, float south, float east, float north) {
        String westStr = String.valueOf(west);
        String southStr = String.valueOf(south);
        String eastStr = String.valueOf(east);
        String northStr = String.valueOf(north);

        setBounds(westStr, southStr, eastStr, northStr);
    }

    public void setBounds(String boundingBox) {
        String[] bbox = boundingBox.split(",");
        if (bbox.length >= 4) {
            setBounds(bbox[0], bbox[1], bbox[2], bbox[3]);
        }
    }

    protected void setBounds(float[] bbox) {
        String bboxWest = String.valueOf(bbox[0]);
        String bboxSouth = String.valueOf(bbox[1]);
        String bboxEast = String.valueOf(bbox[2]);
        String bboxNorth = String.valueOf(bbox[3]);
        setBounds(bboxWest, bboxSouth, bboxEast, bboxNorth);
    }

    private void setBounds(String bboxWest, String bboxSouth, String bboxEast, String bboxNorth) {
        tvAreaSWLat.setText(bboxSouth);
        tvAreaSWLon.setText(bboxWest);
        tvAreaNELat.setText(bboxNorth);
        tvAreaNELon.setText(bboxEast);

        sharedPrefs.setBboxPrefs(bboxWest, bboxSouth, bboxEast, bboxNorth);
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

        tvAreaSWLat.setText(bboxSouth);
        tvAreaSWLon.setText(bboxWest);
        tvAreaNELat.setText(bboxNorth);
        tvAreaNELon.setText(bboxEast);

        sharedPrefs.setBboxPrefs(bboxWest, bboxSouth, bboxEast, bboxNorth);
    }

}
