package pl.wasat.smarthma.model.osdd;

import java.util.List;

/**
 * Created by Daniel on 2016-02-04.
 * This file is a part of module SmartHMA project.
 */
public class OSDDMatcher {

    //http://fedeo.esa.int/opensearch/request/?
    // httpAccept=application/atom%2Bxml&
    // parentIdentifier=EOP:SPOT:ALL&
    // startRecord={startIndex?}&
    // startPage={startPage?}&
    // maximumRecords={count?}&
    // startDate={time:start}&
    // endDate={time:end}&
    // bbox={geo:box?}&name={geo:name?}&lat={geo:lat?}&lon={geo:lon?}&radius={geo:radius?}&
    // uid={geo:uid?}&productType={eo:productType?}&platform={eo:platform?}&platformSerialIdentifier={eo:platformSerialIdentifier?}&
    // instrument={eo:instrument?}&sensorType={eo:sensorType?}&compositeType={eo:compositeType?}&processingLevel={eo:processingLevel?}&
    // orbitType={eo:orbitType?}&resolution={eo:resolution?}&productionStatus={eo:productionStatus?}&acquisitionType={eo:acquisitionType?}&
    // orbitNumber={eo:orbitNumber?}&orbitDirection={eo:orbitDirection?}&track={eo:track?}&frame={eo:frame?}&
    // swathIdentifier={eo:swathIdentifier?}&cloudCover={eo:cloudCover?}&snowCover={eo:snowCover?}&acquisitionStation={eo:acquisitionStation?}&
    // productQualityStatus={eo:productQualityStatus?}&processorName={eo:processorName?}&sensorMode={eo:sensorMode?}&
    // archivingCenter={eo:archivingCenter?}&acquisitionSubType={eo:acquisitionSubType?}&
    // startTimeFromAscendingNode={eo:startTimeFromAscendingNode?}&completionTimeFromAscendingNode={eo:completionTimeFromAscendingNode?}&
    // illuminationAzimuthAngle={eo:illuminationAzimuthAngle?}&illuminationElevationAngle={eo:illuminationElevationAngle?}&
    // recordSchema={sru:recordSchema?}

    public static final String PARAM_NAME_PARENT_IDENTIFIER = "parentIdentifier";
    public static final String PARAM_NAME_TYPE = "type";
    public static final String PARAM_NAME_RECORD_SCHEMA = "recordSchema";
    public static final String PARAM_NAME_START_RECORD = "startRecord";
    public static final String PARAM_NAME_START_PAGE = "startPage";
    public static final String PARAM_NAME_MAX_RECORDS = "maximumRecords";
    public static final String PARAM_NAME_START_DATE = "startDate";
    public static final String PARAM_NAME_END_DATE = "endDate";
    public static final String PARAM_NAME_BBOX = "bbox";
    public static final String PARAM_NAME_LAT = "lat";
    public static final String PARAM_NAME_LNG = "lon";
    public static final String PARAM_NAME_RADIUS = "radius";
    public static final String PARAM_NAME_QUERY = "query";
    public static final String PARAM_NAME_PLATFORM = "platform";
    public static final String PARAM_NAME_USERNAME = "username";
    public static final String PARAM_NAME_PASSWORD = "password";

    //public static final String PARAM_NAME_  = "";
    //public static final String PARAM_NAME_  = "";
    //public static final String PARAM_NAME_  = "";
    //public static final String PARAM_NAME_  = "";

    //public static final String PARAM_KEY_  = "";
    //public static final String PARAM_KEY_  = "";

    public static final String PARAM_KEY_BBOX = "{geo:box}";
    public static final String PARAM_KEY_LAT = "{geo:lat}";
    public static final String PARAM_KEY_LNG = "{geo:lon}";
    public static final String PARAM_KEY_RADIUS = "{geo:radius}";
    public static final String PARAM_KEY_END_DATE = "{time:end}";
    public static final String PARAM_KEY_START_DATE = "{time:start}";
    public static final String PARAM_KEY_START_PAGE = "{startPage}";
    public static final String PARAM_KEY_RECORD_SCHEMA = "{sru:recordSchema}";
    public static final String PARAM_KEY_MAX_RECORDS = "{count}";
    public static final String PARAM_KEY_START_RECORD = "{startIndex}";
    public static final String PARAM_KEY_QUERY = "{searchTerms}";
    public static final String PARAM_KEY_PUBLISHER = "{dc:publisher}";
    public static final String PARAM_KEY_TITLE = "{dc:title}";
    public static final String PARAM_KEY_ORGANISATION = "{eo:organisationName}";
    public static final String PARAM_KEY_PLATFORM = "{eo:platform}";
    public static final String PARAM_KEY_USERNAME = "{wsse:Username}";
    public static final String PARAM_KEY_PASSWORD = "{wsse:Password}";

    public static final String PARAM_VALUE_COLLECTION = "collection";
    public static final String PARAM_VALUE_SERVER_CHOICE = "server-choice";
    public static final String PARAM_VALUE_ISO = "iso";
    public static final String PARAM_VALUE_DATASET = "dataset";


    public static Parameter findParentIdParam(List<Parameter> osddParams) {
        if (osddParams != null) {
            for (Parameter param : osddParams) {
                if (param.getName().equalsIgnoreCase(PARAM_NAME_PARENT_IDENTIFIER)) return param;
            }
        }
        return null;
    }

    public static String findOptionValue(List<Option> options, String label) {
        for (Option opt : options) {
            if (opt.getLabel().equalsIgnoreCase(label)) return opt.getValue();
        }
        return "";
    }

    public static String getParamName(String paramKey) {
        String paramName = "";
        switch (paramKey) {
            case PARAM_KEY_BBOX:
                paramName = PARAM_NAME_BBOX;
                break;
            case PARAM_KEY_END_DATE:
                paramName = PARAM_NAME_END_DATE;
                break;
            case PARAM_KEY_LAT:
                paramName = PARAM_NAME_LAT;
                break;
            case PARAM_KEY_LNG:
                paramName = PARAM_NAME_LNG;
                break;
            case PARAM_KEY_RADIUS:
                paramName = PARAM_NAME_RADIUS;
                break;
            case PARAM_KEY_MAX_RECORDS:
                paramName = PARAM_NAME_MAX_RECORDS;
                break;
            case PARAM_KEY_RECORD_SCHEMA:
                paramName = PARAM_NAME_RECORD_SCHEMA;
                break;
            case PARAM_KEY_START_DATE:
                paramName = PARAM_NAME_START_DATE;
                break;
            case PARAM_KEY_START_PAGE:
                paramName = PARAM_NAME_START_PAGE;
                break;
            case PARAM_KEY_START_RECORD:
                paramName = PARAM_NAME_START_RECORD;
                break;
            default:
                paramName = paramKey;
                break;
        }
        return paramName;
    }
}
