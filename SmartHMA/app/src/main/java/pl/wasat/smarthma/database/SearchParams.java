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

package pl.wasat.smarthma.database;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.osdd.OSDDMatcher;

/**
 * A representation of user search parameters.
 */
public class SearchParams {
    private final String searchPhrase;
    private final String catalogue;
    private final String bbox;
    private final String startDate;
    private final String endDate;

    /**
     * Instantiates a new Search params.
     *
     * @param request the request
     */
    public SearchParams(FedeoRequestParams request) {
        searchPhrase = request.getParams().get(OSDDMatcher.PARAM_KEY_QUERY);
        catalogue = request.getParams().get("parentIdentifier");
        bbox = request.getBbox();
        startDate = request.getStartDate();
        endDate = request.getEndDate();
    }

    /**
     * Instantiates a new Search params.
     *
     * @param searchPhrase the search phrase
     * @param catalogue    the catalogue
     * @param bbox         the bbox
     * @param startDate    the start date
     * @param endDate      the end date
     */
    public SearchParams(String searchPhrase, String catalogue, String bbox, String startDate, String endDate) {
        this.searchPhrase = searchPhrase;
        this.catalogue = catalogue;
        this.bbox = bbox;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the start date parameter preceded by a short description.
     *
     * @return a String representation of the start date
     */
    public String getStartDateWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.start_date) + ": " + getStartDate();
    }

    /**
     * Returns the end date parameter preceded by a short description.
     *
     * @return a String representation of the end date
     */
    public String getEndDateWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.end_date) + ": " + getEndDate();
    }

    @Override
    /**
     * Returns values stored in this object in text form, separated by spaces.
     */
    public String toString() {
        return getSearchPhraseWithPrefix() + "; "
                + getCatalogueWithPrefix() + "; "
                + getBboxWithPrefix() + "; "
                + SmartHMApplication.appSingleton.getString(R.string.time_period) + ": " + getStartDate() + " - " + getEndDate();
    }

    /**
     * Returns user search query parameter preceded by a short description.
     *
     * @return a String representation of the search query
     */
    private String getSearchPhraseWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.query) + ": " + getSearchPhrase();
    }

    /**
     * Returns catalogue name parameter preceded by a short description.
     *
     * @return a String representation of the catalogue name
     */
    private String getCatalogueWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.catalogue) + ": " + getCatalogue();
    }

    /**
     * Returns user selected bounding box preceded by a short description.
     *
     * @return a String representation of the selected bounding box
     */
    private String getBboxWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.bbox) + ": " + getBbox();
    }

    /**
     * Returns the start date parameter stored in this object.
     *
     * @return a String representation of the start date
     */
    public String getStartDate() {
        if (catalogue != null) {
            return startDate;
        } else {
            return "NullStartDate";
        }
    }

    /**
     * Returns the end date parameter stored in this object.
     *
     * @return a String representation of the end date
     */
    public String getEndDate() {
        if (catalogue != null) {
            return endDate;
        } else {
            return "NullEndDate";
        }
    }

    /**
     * Returns user search query parameter stored in this object.
     *
     * @return a String representation of the search query
     */
    public String getSearchPhrase() {
        if (searchPhrase != null) {
            return searchPhrase;
        } else {
            return "NullSearchPhrase";
        }
    }

    /**
     * Returns catalogue name parameter stored in this object.
     *
     * @return a String representation of the catalogue name
     */
    public String getCatalogue() {
        if (catalogue != null) {
            return catalogue;
        } else {
            return "NullCatalogue";
        }
    }

    /**
     * Returns user selected bounding box parameter stored in this object.
     *
     * @return a String representation of the selected bounding box
     */
    public String getBbox() {
        if (catalogue != null) {
            return bbox;
        } else {
            return "NullBbox";
        }
    }
}
