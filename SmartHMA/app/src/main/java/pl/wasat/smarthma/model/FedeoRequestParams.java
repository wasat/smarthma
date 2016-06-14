/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * The type Fedeo request params.
 *
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 18-07-2014
 */
@SuppressWarnings("WeakerAccess")
public class FedeoRequestParams extends OSDDMatcher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The constant IS_BUILD_FROM_SHARED.
     */
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
     * Instantiates a new Fedeo request params.
     */
    public FedeoRequestParams() {
        this.params = new HashMap<>();
        IS_URL_TEMPLATE = true;
        IS_BUILD_FROM_SHARED = true;
        setDefaultParams();
    }

    /**
     * Instantiates a new Fedeo request params.
     *
     * @param isUrlTemplate the is url template
     */
    public FedeoRequestParams(Boolean isUrlTemplate) {
        this.params = new HashMap<>();
        IS_URL_TEMPLATE = isUrlTemplate;
        IS_BUILD_FROM_SHARED = true;
        setDefaultParams();
    }

    /**
     * Instantiates a new Fedeo request params.
     *
     * @param initAreaTime  the init area time
     * @param isUrlTemplate the is url template
     */
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

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        buildUrl();
        validateUrl();
        Log.i("URL", url);
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets template url.
     *
     * @return the template url
     */
    public String getTemplateUrl() {
        return templateUrl;
    }

    /**
     * Sets template url.
     *
     * @param templateUrl the template url
     */
    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    /**
     * Gets params.
     *
     * @return the params
     */
    public HashMap<String, String> getParams() {
        return params;
    }

    /**
     * Sets params.
     *
     * @param params the params
     */
    public void setParams(HashMap<String, String> params) {
        if (this.params == null) this.params = params;
        else {
            for (String key : params.keySet()) {
                addOsddValue(key, params.get(key));
            }
        }
    }

    /**
     * Add osdd value.
     *
     * @param key   the key
     * @param value the value
     */
    public void addOsddValue(String key, String value) {
        if (this.params == null) this.params = new HashMap<>();
        this.params.put(key, value);
    }

    /**
     * Gets start date.
     *
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
        addOsddValue(PARAM_KEY_START_DATE, startDate);
    }

    /**
     * Gets end date.
     *
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
        addOsddValue(PARAM_KEY_END_DATE, endDate);
    }

    /**
     * Gets parent identifier.
     *
     * @return the parentIdentifier
     */
    public String getParentIdentifier() {
        return parentIdentifier;
    }

    /**
     * Sets parent identifier.
     *
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
     * Gets bbox.
     *
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
     * Sets bbox.
     *
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
