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

package pl.wasat.smarthma.helper;

/**
 * The type Const.
 */
public class Const {
    /**
     * The constant SMARTHMA_PATH.
     */
    public static final String SMARTHMA_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/SMARTHMA/";
    /**
     * The constant SMARTHMA_PATH_EO_DATA.
     */
    public static final String SMARTHMA_PATH_EO_DATA = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/SMARTHMA/EO_DATA/";
    /**
     * The constant SMARTHMA_PATH_LOGS.
     */
    public static final String SMARTHMA_PATH_LOGS = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/SMARTHMA/LOGS/";
    /**
     * The constant SMARTHMA_PATH_TEMP.
     */
    public static final String SMARTHMA_PATH_TEMP = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/SMARTHMA/TEMP/";

    /**
     * The constant IMG_URL.
     */
    public static final String IMG_URL = "http://89.250.194.14/smarthma/img/";
    private static final String HTTP_ESA_BASE_URL = "http://fedeo.esa.int/opensearch";
    private static final String HTTP_SPACEBEL_BASE_URL = "http://geo.spacebel.be/opensearch";
    private static final String HTTP_SMAAD_BASE_URL = "http://smaad.spacebel.be/opensearch";
    private static final String HTTP_OBEOS_BASE_URL = "http://obeos.spacebel.be/opensearch";

    private static String HTTP_BASE_URL = HTTP_ESA_BASE_URL;

    /**
     * The constant FEDEO_SEARCH_BASE_URL.
     */
    public static String FEDEO_SEARCH_BASE_URL = HTTP_BASE_URL + "/request/";
    /**
     * The constant OSDD_BASE_URL.
     */
    public static String OSDD_BASE_URL = HTTP_BASE_URL + "/description.xml?";

    /**
     * The constant URL_ESA_NEWS_1.
     */
    public static final String URL_ESA_NEWS_1 = "http://www.esa.int/rssfeed/EOB";
    /**
     * The constant URL_ESA_NEWS_2.
     */
    public static final String URL_ESA_NEWS_2 = "https://earth.esa.int/eodisp-portlets/eodisp-rss?tags=news";
    /**
     * The constant URL_ESA_NEWS_3.
     */
    public static final String URL_ESA_NEWS_3 = "http://www.esa.int/rssfeed/TopNews";
    /**
     * The constant URL_ESA_NEWS_ENVISAT.
     */
    public static final String URL_ESA_NEWS_ENVISAT = "https://earth.esa.int/eodisp-portlets/eodisp-rss?tags=news,envisat";
    /**
     * The constant URL_ESA_NEWS_ERS.
     */
    public static final String URL_ESA_NEWS_ERS = "https://earth.esa.int/eodisp-portlets/eodisp-rss?tags=news,ers";
    /**
     * The constant URL_ESA_NEWS_GOCE.
     */
    public static final String URL_ESA_NEWS_GOCE = "https://earth.esa.int/eodisp-portlets/eodisp-rss?tags=news,goce";
    /**
     * The constant URL_ESA_NEWS_SMOS.
     */
    public static final String URL_ESA_NEWS_SMOS = "https://earth.esa.int/eodisp-portlets/eodisp-rss?tags=news,smos";
    /**
     * The constant URL_ESA_NEWS_CRYOSAT.
     */
    public static final String URL_ESA_NEWS_CRYOSAT = "https://earth.esa.int/eodisp-portlets/eodisp-rss?tags=news,cryosat";
    /**
     * The constant URL_ESA_NEWS_CRYOSAT_OP.
     */
    public static final String URL_ESA_NEWS_CRYOSAT_OP = "https://earth.esa.int/eodisp-portlets/eodisp-rss?tags=cryosat-operational-news";
    /**
     * The constant URL_ESA_NEWS_PROBA_V.
     */
    public static final String URL_ESA_NEWS_PROBA_V = "https://earth.esa.int/eodisp-portlets/eodisp-rss?tags=news,proba-v";
    /**
     * The constant URL_ESA_NEWS_SWARM.
     */
    public static final String URL_ESA_NEWS_SWARM = "https://earth.esa.int/eodisp-portlets/eodisp-rss?tags=news,swarm";
    /**
     * The constant URL_ESA_NEWS_USER_SRV.
     */
    public static final String URL_ESA_NEWS_USER_SRV = "https://earth.esa.int/eodisp-portlets/eodisp-rss?tags=news,mission,operational";
    /**
     * The constant URL_ESA_NEWS_SSE.
     */
    public static final String URL_ESA_NEWS_SSE = "https://earth.esa.int/eodisp-portlets/eodisp-rss?tags=news,rssn";

    /**
     * The constant KEY_DATE_PICKER_DT_VIEW_TAG.
     */
    public static final String KEY_DATE_PICKER_DT_VIEW_TAG = "pl.wasat.smarthma.KEY_DATE_PICKER_DT_VIEW_TAG";
    /**
     * The constant KEY_DATE_TIME_PICKER_CALENDAR.
     */
    public static final String KEY_DATE_TIME_PICKER_CALENDAR = "pl.wasat.smarthma.KEY_DATE_TIME_PICKER_CALENDAR";
    /**
     * The constant KEY_MAP_SPINNER_INTENTFILTER_NOTIFICATION.
     */
    public static final String KEY_MAP_SPINNER_INTENTFILTER_NOTIFICATION = "pl.wasat.smarthma.KEY_MAP_SPINNER_INTENTFILTER_NOTIFICATION";
    /**
     * The constant KEY_MAP_WMS_LOAD_STATE.
     */
    public static final String KEY_MAP_WMS_LOAD_STATE = "pl.wasat.smarthma.KEY_MAP_WMS_LOAD_STATE";
    /**
     * The constant KEY_INTENT_RETURN_STOP_SEARCH.
     */
    public static final String KEY_INTENT_RETURN_STOP_SEARCH = "pl.wasat.smarthma.KEY_INTENT_RETURN_STOP_SEARCH";
    /**
     * The constant KEY_INTENT_MISSION_NAME.
     */
    public static final String KEY_INTENT_MISSION_NAME = "pl.wasat.smarthma.KEY_INTENT_MISSION_NAME";
    /**
     * The constant KEY_INTENT_MISSION_PARAM.
     */
    public static final String KEY_INTENT_MISSION_PARAM = "pl.wasat.smarthma.KEY_INTENT_MISSION_PARAM";
    /**
     * The constant KEY_INTENT_FEDEO_REQUEST_PARAMS.
     */
    public static final String KEY_INTENT_FEDEO_REQUEST_PARAMS = "pl.wasat.smarthma.KEY_INTENT_FEDEO_REQUEST_PARAMS";
    /**
     * The constant KEY_INTENT_FEDEO_REQUEST_PARAMS_EXTRA.
     */
    public static final String KEY_INTENT_FEDEO_REQUEST_PARAMS_EXTRA = "pl.wasat.smarthma.KEY_INTENT_FEDEO_REQUEST_PARAMS_EXTRA";
    /**
     * The constant KEY_INTENT_FEDEO_REQUEST_PARAMS_OSDD.
     */
    public static final String KEY_INTENT_FEDEO_REQUEST_PARAMS_OSDD = "pl.wasat.smarthma.KEY_INTENT_FEDEO_REQUEST_PARAMS_OSDD";
    /**
     * The constant KEY_INTENT_FEDEO_OSDD_URL.
     */
    public static final String KEY_INTENT_FEDEO_OSDD_URL = "pl.wasat.smarthma.KEY_INTENT_FEDEO_OSDD_URL";
    /**
     * The constant KEY_INTENT_FEDEO_REQUEST_URL.
     */
    public static final String KEY_INTENT_FEDEO_REQUEST_URL = "pl.wasat.smarthma.KEY_INTENT_FEDEO_REQUEST_URL";
    /**
     * The constant KEY_INTENT_CLOUD_PRODUCT_NAME.
     */
    public static final String KEY_INTENT_CLOUD_PRODUCT_NAME = "pl.wasat.smarthma.KEY_INTENT_CLOUD_PRODUCT_NAME";
    /**
     * The constant KEY_INTENT_CLOUD_PRODUCT_URL.
     */
    public static final String KEY_INTENT_CLOUD_PRODUCT_URL = "pl.wasat.smarthma.KEY_INTENT_CLOUD_PRODUCT_URL";
    /**
     * The constant KEY_INTENT_CLOUD_PRODUCT_METADATA.
     */
    public static final String KEY_INTENT_CLOUD_PRODUCT_METADATA = "pl.wasat.smarthma.KEY_INTENT_CLOUD_PRODUCT_METADATA";

    /**
     * The constant KEY_ACTION_SEARCH_MISSION_DATA.
     */
    public static final String KEY_ACTION_SEARCH_MISSION_DATA = "pl.wasat.smarthma.KEY_ACTION_SEARCH_MISSION_DATA";
    /**
     * The constant KEY_ACTION_SEARCH_COLLECTIONS.
     */
    public static final String KEY_ACTION_SEARCH_COLLECTIONS = "pl.wasat.smarthma.KEY_ACTION_SEARCH_COLLECTIONS";
    /**
     * The constant KEY_ACTION_BROADCAST_FEDEO_REQUEST.
     */
    public static final String KEY_ACTION_BROADCAST_FEDEO_REQUEST = "pl.wasat.smarthma.KEY_ACTION_BROADCAST_FEDEO_REQUEST";
    /**
     * The constant KEY_ACTION_CLOUD_DOWNLOAD_SERVICE.
     */
    public static final String KEY_ACTION_CLOUD_DOWNLOAD_SERVICE = "pl.wasat.smarthma.KEY_ACTION_CLOUD_DOWNLOAD_SERVICE";

    /**
     * The constant REQUEST_CODE_GLOBAL_SETTINGS.
     */
    public static final int REQUEST_CODE_GLOBAL_SETTINGS = 100;
    /**
     * The constant REQUEST_CODE_DOWNLOAD.
     */
    public static final int REQUEST_CODE_DOWNLOAD = 101;

    /**
     * The constant KEY_PREF_FILE.
     */
    public static final String KEY_PREF_FILE = "SmartHMAPrefFile";
    /**
     * The constant KEY_PREF_CATALOGUE.
     */
    public static final String KEY_PREF_CATALOGUE = "pl.wasat.smarthma.KEY_PREF_CATALOGUE";
    /**
     * The constant KEY_PREF_DATETIME_START.
     */
    public static final String KEY_PREF_DATETIME_START = "pl.wasat.smarthma.KEY_PREF_TIME_START";
    /**
     * The constant KEY_PREF_DATETIME_END.
     */
    public static final String KEY_PREF_DATETIME_END = "pl.wasat.smarthma.KEY_PREF_TIME_END";
    /**
     * The constant KEY_PREF_PARENT_ID.
     */
    public static final String KEY_PREF_PARENT_ID = "pl.wasat.smarthma.KEY_PREF_PARENT_ID";
    /**
     * The constant KEY_PREF_BBOX_WEST.
     */
    public static final String KEY_PREF_BBOX_WEST = "pl.wasat.smarthma.KEY_PREF_BBOX_WEST";
    /**
     * The constant KEY_PREF_BBOX_SOUTH.
     */
    public static final String KEY_PREF_BBOX_SOUTH = "pl.wasat.smarthma.KEY_PREF_BBOX_SOUTH";
    /**
     * The constant KEY_PREF_BBOX_EAST.
     */
    public static final String KEY_PREF_BBOX_EAST = "pl.wasat.smarthma.KEY_PREF_BBOX_EAST";
    /**
     * The constant KEY_PREF_BBOX_NORTH.
     */
    public static final String KEY_PREF_BBOX_NORTH = "pl.wasat.smarthma.KEY_PREF_BBOX_NORTH";
    /**
     * The constant KEY_PREF_QUERY.
     */
    public static final String KEY_PREF_QUERY = "pl.wasat.smarthma.KEY_PREF_QUERY";
    /**
     * The constant KEY_PREF_URL.
     */
    public static final String KEY_PREF_URL = "pl.wasat.smarthma.KEY_PREF_URL";
    /**
     * The constant KEY_PREF_AREA_TYPE.
     */
    public static final String KEY_PREF_AREA_TYPE = "pl.wasat.smarthma.KEY_PREF_AREA_TYPE";
    /**
     * The constant KEY_PREF_AREA_USE.
     */
    public static final String KEY_PREF_AREA_USE = "pl.wasat.smarthma.KEY_PREF_AREA_USE";
    /**
     * The constant KEY_PREF_TIME_USE.
     */
    public static final String KEY_PREF_TIME_USE = "pl.wasat.smarthma.KEY_PREF_TIME_USE";
    /**
     * The constant KEY_PREF_CENTER_LAT.
     */
    public static final String KEY_PREF_CENTER_LAT = "pl.wasat.smarthma.KEY_PREF_CENTER_LAT";
    /**
     * The constant KEY_PREF_CENTER_LNG.
     */
    public static final String KEY_PREF_CENTER_LNG = "pl.wasat.smarthma.KEY_PREF_CENTER_LNG";
    /**
     * The constant KEY_PREF_RADIUS.
     */
    public static final String KEY_PREF_RADIUS = "pl.wasat.smarthma.KEY_PREF_RADIUS";
    /**
     * The constant KEY_PREF_LOGIN.
     */
    public static final String KEY_PREF_LOGIN = "pl.wasat.smarthma.KEY_PREF_LOGIN";
    /**
     * The constant KEY_PREF_PASSWORD.
     */
    public static final String KEY_PREF_PASSWORD = "pl.wasat.smarthma.KEY_PREF_PASSWORD";

    /**
     * The constant KEY_PREF_GLOBAL_LAST_SYNC_TIME.
     */
    public static final String KEY_PREF_GLOBAL_LAST_SYNC_TIME = "pl.wasat.smarthma.KEY_PREF_GLOBAL_LAST_SYNC_TIME";

    /**
     * The constant SORT_BY_TITLE_ASCENDING.
     */
    public static final int SORT_BY_TITLE_ASCENDING = 0;
    /**
     * The constant SORT_BY_TITLE_DESCENDING.
     */
    public static final int SORT_BY_TITLE_DESCENDING = 1;
    /**
     * The constant SORT_BY_DATE_ASCENDING.
     */
    public static final int SORT_BY_DATE_ASCENDING = 2;
    /**
     * The constant SORT_BY_DATE_DESCENDING.
     */
    public static final int SORT_BY_DATE_DESCENDING = 3;

    /**
     * The constant DATE_NULL.
     */
    public static final String DATE_NULL = "0000-00-00T00:00:00Z";

    /**
     * The constant EU_BBOX_WEST.
     */
    public static final String EU_BBOX_WEST = "-25.116961";
    /**
     * The constant EU_BBOX_SOUTH.
     */
    public static final String EU_BBOX_SOUTH = "35.538673";
    /**
     * The constant EU_BBOX_EAST.
     */
    public static final String EU_BBOX_EAST = "51.479717";
    /**
     * The constant EU_BBOX_NORTH.
     */
    public static final String EU_BBOX_NORTH = "68.482834";

    /**
     * The constant MAX_SEARCH_HISTORY_ENTRIES.
     */
    public static final int MAX_SEARCH_HISTORY_ENTRIES = 5;
    /**
     * The constant MAX_WIDGET_ENTRIES.
     */
    public static final int MAX_WIDGET_ENTRIES = 10;

    /**
     * The constant IS_KINDLE.
     */
    public static boolean IS_KINDLE = false;

/*    public static String getOsddBaseUrl() {
        return OSDD_BASE_URL;
    }*/
/*
    public static void setHttpEsaBaseUrl() {
        HTTP_BASE_URL = HTTP_ESA_BASE_URL;
        setHttpBaseUrls();
    }

    public static void setHttpSpacebelBaseUrl() {
        HTTP_BASE_URL = HTTP_SPACEBEL_BASE_URL;
        setHttpBaseUrls();
    }

    public static void setHttpSmaadBaseUrl() {
        HTTP_BASE_URL = HTTP_SMAAD_BASE_URL;
        setHttpBaseUrls();
    }

    public static void setHttpObeosBaseUrl() {
        HTTP_BASE_URL = HTTP_OBEOS_BASE_URL;
        setHttpBaseUrls();
    }*/

    /**
     * Sets http base url.
     *
     * @param host the host
     */
    public static void setHttpBaseUrl(String host) {
        HTTP_BASE_URL = host;
        setHttpBaseUrls();
    }

    private static void setHttpBaseUrls() {
        OSDD_BASE_URL = HTTP_BASE_URL + "/description.xml?";
        FEDEO_SEARCH_BASE_URL = HTTP_BASE_URL + "/request/";
    }

}
