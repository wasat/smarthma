/**
 *
 */
package pl.wasat.smarthma.model;

import android.content.SharedPreferences;
import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;

import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.obj.LatLngExt;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 18-07-2014
 */
public class FedeoRequestParams implements Serializable {

    private static final long serialVersionUID = 1L;
    private HashMap<String, String> params;
    private HashMap<String, String> paramsExtra;
    private String url;
    private String httpAccept;
    private String type;
    private String startRecord;
    private String maximumRecords;
    private String startDate;
    private String endDate;
    private String parentIdentifier;
    private String bbox;
    private String recordSchema;
    private String query;

    private String descUrl;
    private String templateUrl;

    /**
     *
     */
    public FedeoRequestParams() {
        this.params = new HashMap<>();
        setDefaultParams();
    }

    private void setDefaultParams() {
        this.httpAccept = "application/atom%2Bxml";
        this.startRecord = "1";
        this.maximumRecords = "20";
        this.startDate = "2011-07-23T00:00:00Z";
        this.endDate = "2014-07-23T00:00:00Z";
        this.bbox = "20,50,21,51";
        this.recordSchema = "server-choice";
        //this.recordSchema = "om";
        this.params.put("httpAccept", httpAccept);
        this.params.put("startRecord", startRecord);
        this.params.put("maximumRecords", maximumRecords);
        this.params.put("startDate", startDate);
        this.params.put("endDate", endDate);
        this.params.put("bbox", bbox);
        this.params.put("recordSchema", recordSchema);
        this.params.put("query", query);

    }

    private void buildFromShared() {
        SharedPreferences prefs = SmartHMApplication.getAppContext().getSharedPreferences(
                Const.KEY_PREF_FILE, 0);

        setStartDate(prefs.getString(Const.KEY_PREF_DATETIME_START, "0"));
        setEndDate(prefs.getString(Const.KEY_PREF_DATETIME_END, "0"));
        setBbox(prefs.getFloat(Const.KEY_PREF_BBOX_WEST, -180),
                prefs.getFloat(Const.KEY_PREF_BBOX_SOUTH, -90),
                prefs.getFloat(Const.KEY_PREF_BBOX_EAST, 180),
                prefs.getFloat(Const.KEY_PREF_BBOX_NORTH, 90));
    }

    private void buildUrl() {

        buildFromShared();

        String url;
        url = Const.HTTP_BASE_URL + "?";
        for (HashMap.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null) {
                url = url + key + "=" + value + "&";
            }
        }
        if (paramsExtra != null) {
            for (HashMap.Entry<String, String> entry : paramsExtra.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!value.isEmpty()) {
                    url = url + key + "=" + value + "&";
                }
            }
        }
        url = url.substring(0, url.length() - 1);

        Log.i("URL", url);
        this.url = url;
    }

    /**
     * @return String url
     */
    public String getUrl() {
        if (url == null) buildUrl();
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

    /**
     * @return the params
     */
    public HashMap<String, String> getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public HashMap<String, String> getParamsExtra() {
        return paramsExtra;
    }

    public void setParamsExtra(HashMap<String, String> paramsExtra) {
        this.paramsExtra = paramsExtra;
        this.setRecordSchema(paramsExtra.get("recordSchema"));
        cleanExtraParams("recordSchema");
    }

    private void cleanExtraParams(String valueToRemove) {
        paramsExtra.remove(valueToRemove);
    }

    /**
     * @return the httpAccept
     */
    public String getHttpAccept() {
        return httpAccept;
    }

    /**
     * @param httpAccept the httpAccept to set
     */
    public void setHttpAccept(String httpAccept) {
        this.httpAccept = httpAccept;
        this.params.put("httpAccept", httpAccept);
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
        this.params.put("type", type);
    }

    /**
     * @return the startRecord
     */
    public String getStartRecord() {
        return startRecord;
    }

    /**
     * @param startRecord the startRecord to set
     */
    public void setStartRecord(String startRecord) {
        this.startRecord = startRecord;
        this.params.put("startRecord", startRecord);
    }

    /**
     * @return the maximumRecords
     */
    public String getMaximumRecords() {
        return maximumRecords;
    }

    /**
     * @param maximumRecords the maximumRecords to set
     */
    public void setMaximumRecords(String maximumRecords) {
        this.maximumRecords = maximumRecords;
        this.params.put("maximumRecords", maximumRecords);
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
    private void setStartDate(String startDate) {
        this.startDate = startDate;
        this.params.put("startDate", startDate);
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
    private void setEndDate(String endDate) {
        this.endDate = endDate;
        this.params.put("endDate", endDate);
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
        this.params.put("parentIdentifier", baseParentIdentifier);
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
        this.params.put("bbox", bbox);
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
     * @return the recordSchema
     */
    public String getRecordSchema() {
        return recordSchema;
    }

    /**
     * @param recordSchema the recordSchema to set
     */
    private void setRecordSchema(String recordSchema) {
        this.recordSchema = recordSchema;
        this.params.put("recordSchema", recordSchema);
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
        this.params.put("query", query);
    }


    public String getDescUrl() {
        return descUrl;
    }

    public void setDescUrl(String descUrl) {
        this.descUrl = descUrl;
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
