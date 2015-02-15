package pl.wasat.smarthma.helper;

public class Const {

    public static final String IMG_URL = "http://89.250.194.14/smarthma/img/";
    public static final String HTTP_FEDEO_BASE_URL = "http://geo.spacebel.be/opensearch/request/";
    public static final String HTTP_SMAAD_BASE_URL = "http://smaad.spacebel.be/opensearch/request/";
    private static final String HTTP_ESA_BASE_URL = "http://fedeo.esa.int/opensearch/request/";

    public static final String HTTP_BASE_URL = HTTP_ESA_BASE_URL;

    public static final String URL_PARM_QUERY = "&query=";
    public static final String URL_PARM_END_DATE = "&endDate=";
    public static final String URL_PARM_START_DATE = "&startDate=";
    public static final String URL_PARM_MAX_REC = "&maximumRecords=";
    public static final String URL_PARM_START_RECORD = "&startRecord=";
    public static final String URL_PARM_TYPE = "&type=collection";
    public static final String URL_PARM_HTTP_ACCEPT = "?httpAccept=application/atom%2Bxml";
    public static final String URL_PARM_PARENT_ID = "&parentIdentifier=";
    public static final String URL_PARM_BBOX = "&bbox=";

    public static final String KEY_LIST_WORKSPACE_NAME_TO_LOAD = "pl.wasat.smarthma.KEY_LIST_WORKSPACE_NAME_TO_LOAD";
    public static final String KEY_LIST_ID_LAYERS_TO_DISPLAY = "pl.wasat.smarthma.KEY_LIST_ID_LAYERS_TO_DISPLAY";

    public static final int REQUEST_CODE_MAP_ADD_LAYER = 100;

    public static final String KEY_MAP_LAYER_TO_DISPLAY = "pl.wasat.smarthma.KEY_MAP_LAYER_TO_DISPLAY";
    public static final String KEY_MAP_SPINNER_INTENTFILTER_NOTIFICATION = "pl.wasat.smarthma.KEY_MAP_SPINNER_INTENTFILTER_NOTIFICATION";
    public static final String KEY_MAP_WMS_LOAD_STATE = "pl.wasat.smarthma.KEY_MAP_WMS_LOAD_STATE";
    public static final String KEY_INTENT_PARENT_ID = "pl.wasat.smarthma.KEY_INTENT_PARENT_ID";
    public static final String KEY_INTENT_RETURN_STOP_SEARCH = "pl.wasat.smarthma.KEY_INTENT_RETURN_STOP_SEARCH";
    public static final String KEY_INTENT_QUERY = "pl.wasat.smarthma.KEY_INTENT_QUERY";
    public static final String KEY_ACTION_SEARCH_MISSION_DATA = "pl.wasat.smarthma.KEY_ACTION_SEARCH_MISSION_DATA";

    public static final String REQUEST_CODE_SERVICE_RESULT = "pl.wasat.smarthma.REQUEST_CODE_SERVICE_RESULT";
    public static final String KEY_SERVICE_INTENTFILTER_NOTIFICATION = "pl.wasat.smarthma.KEY_SERVICE_INTENTFILTER_NOTIFICATION";

    public static final String KEY_PREF_FILE = "SmartHMAPrefFile";
    public static final String KEY_PREF_DATETIME_START = "pl.wasat.smarthma.KEY_PREF_TIME_START";
    public static final String KEY_PREF_DATETIME_END = "pl.wasat.smarthma.KEY_PREF_TIME_END";
    public static final String KEY_PREF_PARENT_ID = "pl.wasat.smarthma.KEY_PREF_PARENT_ID";
    public static final String KEY_PREF_BBOX_WEST = "pl.wasat.smarthma.KEY_PREF_BBOX_WEST";
    public static final String KEY_PREF_BBOX_SOUTH = "pl.wasat.smarthma.KEY_PREF_BBOX_SOUTH";
    public static final String KEY_PREF_BBOX_EAST = "pl.wasat.smarthma.KEY_PREF_BBOX_EAST";
    public static final String KEY_PREF_BBOX_NORTH = "pl.wasat.smarthma.KEY_PREF_BBOX_NORTH";
    public static final String KEY_PREF_QUERY = "pl.wasat.smarthma.KEY_PREF_QUERY";

    public static final int SORT_BY_TITLE_ASCENDING = 0;
    public static final int SORT_BY_TITLE_DESCENDING = 1;
    public static final int SORT_BY_DATE_ASCENDING = 2;
    public static final int SORT_BY_DATE_DESCENDING = 3;

    public static final String DATE_NULL = "0000-00-00T00:00:00Z";
}
