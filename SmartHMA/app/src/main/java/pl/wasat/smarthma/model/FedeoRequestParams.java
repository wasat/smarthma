/**
 *
 */
package pl.wasat.smarthma.model;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;

import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.helper.enums.Opts;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.osdd.OSDDMatcher;
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.obj.LatLngExt;
import pl.wasat.smarthma.utils.text.StringExt;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 18-07-2014
 */
@SuppressWarnings("WeakerAccess")
public class FedeoRequestParams extends OSDDMatcher implements Serializable {

    private static final long serialVersionUID = 1L;

    public static boolean IS_BUILD_FROM_SHARED;
    private static Boolean IS_URL_TEMPLATE;
    private HashMap<String, String> params;
    private String url;
    private String startDate;
    private String endDate;
    private String parentIdentifier;
    private String bbox;

    private String templateUrl;

    /**
     *
     */
    public FedeoRequestParams() {
        this.params = new HashMap<>();
        IS_URL_TEMPLATE = true;
        IS_BUILD_FROM_SHARED = true;
        setDefaultParams();
    }

    public FedeoRequestParams(Boolean isUrlTemplate) {
        this.params = new HashMap<>();
        IS_URL_TEMPLATE = isUrlTemplate;
        IS_BUILD_FROM_SHARED = true;
        setDefaultParams();
    }

    public FedeoRequestParams(boolean initAreaTime, boolean isUrlTemplate) {
        this.params = new HashMap<>();
        IS_URL_TEMPLATE = isUrlTemplate;
        IS_BUILD_FROM_SHARED = true;
        setDefaultParams();
        if (initAreaTime) buildFromShared();
    }

    private void setDefaultParams() {
        GlobalPreferences globalPreferences = new GlobalPreferences(SmartHMApplication.getAppContext());

        this.params.put(PARAM_KEY_START_RECORD, globalPreferences.getFedeoStartRecord());
        this.params.put(PARAM_KEY_MAX_RECORDS, globalPreferences.getFedeoMaxRecords());
        this.params.put(PARAM_KEY_START_PAGE, globalPreferences.getFedeoStartPage());
        this.params.put(PARAM_KEY_RECORD_SCHEMA, PARAM_VALUE_SERVER_CHOICE);
        //this.params.put(PARAM_KEY_RECORD_SCHEMA, PARAM_VALUE_ISO);
    }

    private void buildFromShared() {
        SharedPrefs sharedPrefs = new SharedPrefs(SmartHMApplication.getAppContext());

        if (sharedPrefs.getTimeUse()) {
            setStartDate(sharedPrefs.getStartDateTimePrefs());
            setEndDate(sharedPrefs.getEndDateTimePrefs());
        }

        if (sharedPrefs.getAreaUse()) {
            switch (sharedPrefs.getAreaType()) {
                case Opts.AREA_POLYGON:
                    float[] bbox = sharedPrefs.getBboxPrefs();
                    setBbox(bbox[0], bbox[1], bbox[2], bbox[3]);
                    break;
                case Opts.AREA_PT_RADIUS:
                    float[] center = sharedPrefs.getCenterPrefs();
                    float radius = sharedPrefs.getRadiusPrefs();
                    String centerLat = StringExt.formatLatLng(center[0]);
                    String centerLng = StringExt.formatLatLng(center[1]);
                    String radiusStr = StringExt.formatDist(radius);

                    this.params.put(PARAM_KEY_LAT, centerLat);
                    this.params.put(PARAM_KEY_LNG, centerLng);
                    this.params.put(PARAM_KEY_RADIUS, radiusStr);
                    break;
            }
        }
    }


    private void buildUrl() {
        if (IS_URL_TEMPLATE == null) return;

        if (IS_BUILD_FROM_SHARED) buildFromShared();

        if (IS_URL_TEMPLATE) {
            String tmpUrl = templateUrl.replaceAll("[?]\\}", "\\}");
            for (String osddKey : params.keySet()) {
                tmpUrl = tmpUrl.replace(osddKey, params.get(osddKey));
            }
            tmpUrl = tmpUrl.replaceAll("\\{.*?\\}", "");

            String[] partsUrl = tmpUrl.split("&");
            String finalUrl = partsUrl[0];
            for (int i = 1; i < partsUrl.length; i++) {
                if (!partsUrl[i].endsWith("=")) finalUrl = finalUrl + "&" + partsUrl[i];
            }
            this.url = finalUrl;
        } else {
            params.put("httpAccept", Link.TYPE_ATOM_XML);
            String url = Const.FEDEO_SEARCH_BASE_URL + "?";
            for (HashMap.Entry<String, String> entry : params.entrySet()) {
                String key = getParamName(entry.getKey());
                String value = entry.getValue();
                if (value != null) {
                    url = url + key + "=" + value + "&";
                }
            }
            url = url.substring(0, url.length() - 1);
            this.url = url;
        }
    }


    private void validateUrl() {
        this.url = this.url.replaceAll("\\+", "%2B");
        this.url = this.url.replaceAll(" ", "%2B");
    }

    public String getUrl() {
        buildUrl();
        validateUrl();
        Log.i("URL", url);
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        if (this.params == null) this.params = params;
        else {
            for (String key : params.keySet()) {
                addOsddValue(key, params.get(key));
            }
        }
    }

    public void addOsddValue(String key, String value) {
        if (this.params == null) this.params = new HashMap<>();
        this.params.put(key, value);
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
        addOsddValue(PARAM_KEY_START_DATE, startDate);
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
        addOsddValue(PARAM_KEY_END_DATE, endDate);
    }

    /**
     * @return the parentIdentifier
     */
    public String getParentIdentifier() {
        return parentIdentifier;
    }

    /**
     * @param parentIdentifier the parentIdentifier to set
     */
    public void setParentIdentifier(String parentIdentifier) {
        if (parentIdentifier.isEmpty()) parentIdentifier = "EOP:ESA:FEDEO";

        String urn = parentIdentifier.substring(0, 4);
        String baseParentIdentifier = parentIdentifier;
        if (urn.equalsIgnoreCase("urn:")) {
            baseParentIdentifier = getParetnId(parentIdentifier);
        }
        this.parentIdentifier = baseParentIdentifier;
    }

    /**
     * @return the box
     */
    public String getBbox() {
        return bbox;
    }

    /**
     * @param bbox the box to set
     */
    private void setBbox(String bbox) {
        this.bbox = bbox;
        addOsddValue(PARAM_KEY_BBOX, bbox);
    }

    /**
     * @param swLon - South West Longitude
     * @param swLat - South West Latitude
     * @param neLon - North East Longitude
     * @param neLat - North East Latitude
     */
    private void setBbox(Float swLon, Float swLat, Float neLon, Float neLat) {
        String bbox = swLon + "," + swLat + "," + neLon + "," + neLat;
        setBbox(bbox);
    }

    /**
     * @param southwest - South West Coordinates
     * @param northeast - North East Coordinates
     */
    private void setBbox(LatLngExt southwest, LatLngExt northeast) {
        setBbox((float) southwest.longitude, (float) southwest.latitude,
                (float) northeast.longitude, (float) northeast.latitude);
    }

    /**
     * @param bbox - Bounding Box
     */
    public void setBbox(LatLngBoundsExt bbox) {
        setBbox(bbox.southwest, bbox.northeast);
    }

    /**
     * @param urn - URN
     * @return String with Parent ID
     */
    private String getParetnId(String urn) {
        String[] idArr = urn.split("urn:ogc:def:");
        return idArr[idArr.length - 1];
    }

}
