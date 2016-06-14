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

package pl.wasat.smarthma.model.osdd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016-02-04.
 * This file is a part of module SmartHMA project.
 */
public class OSDDMatcher {

    /**
     * The constant PARAM_NAME_PARENT_IDENTIFIER.
     */
    public static final String PARAM_NAME_PARENT_IDENTIFIER = "parentIdentifier";
    /**
     * The constant PARAM_NAME_TYPE.
     */
    public static final String PARAM_NAME_TYPE = "type";
    /**
     * The constant PARAM_NAME_RECORD_SCHEMA.
     */
    public static final String PARAM_NAME_RECORD_SCHEMA = "recordSchema";
    /**
     * The constant PARAM_NAME_START_RECORD.
     */
    public static final String PARAM_NAME_START_RECORD = "startRecord";
    /**
     * The constant PARAM_NAME_START_PAGE.
     */
    public static final String PARAM_NAME_START_PAGE = "startPage";
    /**
     * The constant PARAM_NAME_MAX_RECORDS.
     */
    public static final String PARAM_NAME_MAX_RECORDS = "maximumRecords";
    /**
     * The constant PARAM_NAME_START_DATE.
     */
    public static final String PARAM_NAME_START_DATE = "startDate";
    /**
     * The constant PARAM_NAME_END_DATE.
     */
    public static final String PARAM_NAME_END_DATE = "endDate";
    /**
     * The constant PARAM_NAME_BBOX.
     */
    public static final String PARAM_NAME_BBOX = "bbox";
    /**
     * The constant PARAM_NAME_LAT.
     */
    public static final String PARAM_NAME_LAT = "lat";
    /**
     * The constant PARAM_NAME_LNG.
     */
    public static final String PARAM_NAME_LNG = "lon";
    /**
     * The constant PARAM_NAME_RADIUS.
     */
    public static final String PARAM_NAME_RADIUS = "radius";
    /**
     * The constant PARAM_NAME_QUERY.
     */
    public static final String PARAM_NAME_QUERY = "query";
    /**
     * The constant PARAM_NAME_PLATFORM.
     */
    public static final String PARAM_NAME_PLATFORM = "platform";
    /**
     * The constant PARAM_NAME_USERNAME.
     */
    public static final String PARAM_NAME_USERNAME = "username";
    /**
     * The constant PARAM_NAME_PASSWORD.
     */
    public static final String PARAM_NAME_PASSWORD = "password";

    //public static final String PARAM_NAME_  = "";
    //public static final String PARAM_NAME_  = "";

    //public static final String PARAM_KEY_  = "";
    //public static final String PARAM_KEY_  = "";

    /**
     * The constant PARAM_KEY_BBOX.
     */
    public static final String PARAM_KEY_BBOX = "{geo:box}";
    /**
     * The constant PARAM_KEY_LAT.
     */
    public static final String PARAM_KEY_LAT = "{geo:lat}";
    /**
     * The constant PARAM_KEY_LNG.
     */
    public static final String PARAM_KEY_LNG = "{geo:lon}";
    /**
     * The constant PARAM_KEY_RADIUS.
     */
    public static final String PARAM_KEY_RADIUS = "{geo:radius}";
    /**
     * The constant PARAM_KEY_END_DATE.
     */
    public static final String PARAM_KEY_END_DATE = "{time:end}";
    /**
     * The constant PARAM_KEY_START_DATE.
     */
    public static final String PARAM_KEY_START_DATE = "{time:start}";
    /**
     * The constant PARAM_KEY_START_PAGE.
     */
    public static final String PARAM_KEY_START_PAGE = "{startPage}";
    /**
     * The constant PARAM_KEY_RECORD_SCHEMA.
     */
    public static final String PARAM_KEY_RECORD_SCHEMA = "{sru:recordSchema}";
    /**
     * The constant PARAM_KEY_MAX_RECORDS.
     */
    public static final String PARAM_KEY_MAX_RECORDS = "{count}";
    /**
     * The constant PARAM_KEY_START_RECORD.
     */
    public static final String PARAM_KEY_START_RECORD = "{startIndex}";
    /**
     * The constant PARAM_KEY_QUERY.
     */
    public static final String PARAM_KEY_QUERY = "{searchTerms}";
    /**
     * The constant PARAM_KEY_PUBLISHER.
     */
    public static final String PARAM_KEY_PUBLISHER = "{dc:publisher}";
    /**
     * The constant PARAM_KEY_TITLE.
     */
    public static final String PARAM_KEY_TITLE = "{dc:title}";
    /**
     * The constant PARAM_KEY_ORGANISATION.
     */
    public static final String PARAM_KEY_ORGANISATION = "{eo:organisationName}";
    /**
     * The constant PARAM_KEY_PLATFORM.
     */
    public static final String PARAM_KEY_PLATFORM = "{eo:platform}";
    /**
     * The constant PARAM_KEY_INSTRUMENT.
     */
    public static final String PARAM_KEY_INSTRUMENT = "{eo:instrument}";
    /**
     * The constant PARAM_KEY_USERNAME.
     */
    public static final String PARAM_KEY_USERNAME = "{wsse:Username}";
    /**
     * The constant PARAM_KEY_PASSWORD.
     */
    public static final String PARAM_KEY_PASSWORD = "{wsse:Password}";

    /**
     * The constant PARAM_VALUE_COLLECTION.
     */
    public static final String PARAM_VALUE_COLLECTION = "collection";
    /**
     * The constant PARAM_VALUE_SERVER_CHOICE.
     */
    public static final String PARAM_VALUE_SERVER_CHOICE = "server-choice";
    /**
     * The constant PARAM_VALUE_ISO.
     */
    public static final String PARAM_VALUE_ISO = "iso";
    /**
     * The constant PARAM_VALUE_DATASET.
     */
    public static final String PARAM_VALUE_DATASET = "dataset";


    /**
     * Find parent id param parameter.
     *
     * @param osddParams the osdd params
     * @return the parameter
     */
    public static Parameter findParentIdParam(List<Parameter> osddParams) {
        if (osddParams != null) {
            for (Parameter param : osddParams) {
                if (param.getName().equalsIgnoreCase(PARAM_NAME_PARENT_IDENTIFIER)) return param;
            }
        }
        return null;
    }

    /**
     * Generate option values list list.
     *
     * @param osddParams the osdd params
     * @param paramKey   the param key
     * @return the list
     */
    public static List<String> generateOptionValuesList(List<Parameter> osddParams, String paramKey) {
        List<String> optLabels = new ArrayList<>();
        if (osddParams != null) {
            for (Parameter param : osddParams) {
                if (param.getValue().equalsIgnoreCase(paramKey)) {
                    for (Option opt : param.getOptions()) {
                        optLabels.add(opt.getValue());
                    }
                    return optLabels;
                }
            }
        }
        return optLabels;
    }

    /**
     * Find option value string.
     *
     * @param options the options
     * @param label   the label
     * @return the string
     */
    public static String findOptionValue(List<Option> options, String label) {
        for (Option opt : options) {
            if (opt.getLabel().equalsIgnoreCase(label)) return opt.getValue();
        }
        return "";
    }

    /**
     * Gets param name.
     *
     * @param paramKey the param key
     * @return the param name
     */
    public static String getParamName(String paramKey) {
        String paramName;
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
