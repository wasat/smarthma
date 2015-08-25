package pl.wasat.smarthma.database;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.model.FedeoRequestParams;

/**
 * A representation of user search parameters.
 */
public class SearchParams {
    private final String searchPhrase;
    private final String catalogue;
    private final String bbox;
    private final String startDate;
    private final String endDate;

    public SearchParams(FedeoRequestParams request) {
        searchPhrase = request.getQuery();
        catalogue = request.getParams().get("parentIdentifier");
        bbox = request.getBbox();
        startDate = request.getStartDate();
        endDate = request.getEndDate();
    }

    public SearchParams(String searchPhrase, String catalogue, String bbox, String startDate, String endDate) {
        this.searchPhrase = searchPhrase;
        this.catalogue = catalogue;
        this.bbox = bbox;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    /**
     * Returns values stored in this object in text form, separated by spaces.
     */
    public String toString() {
        //return searchPhrase + " " +catalogue + " " + bbox + " " + startDate + " " + endDate;
        return getSearchPhraseWithPrefix() + "; "
                + getCatalogueWithPrefix() + "; "
                + getBboxWithPrefix() + "; "
                + SmartHMApplication.appSingleton.getString(R.string.time_period) + ": " + getStartDate() + " - " + getEndDate();
    }

    /**
     * Returns user search query parameter stored in this object.
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
     * @return a String representation of the selected bounding box
     */
    public String getBbox() {
        if (catalogue != null) {
            return bbox;
        } else {
            return "NullBbox";
        }
    }

    /**
     * Returns the start date parameter stored in this object.
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
     * Returns user search query parameter preceded by a short description.
     * @return a String representation of the search query
     */
    private String getSearchPhraseWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.query) + ": " + getSearchPhrase();
    }

    /**
     * Returns catalogue name parameter preceded by a short description.
     * @return a String representation of the catalogue name
     */
    private String getCatalogueWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.catalogue) + ": " + getCatalogue();
    }

    /**
     * Returns user selected bounding box preceded by a short description.
     * @return a String representation of the selected bounding box
     */
    private String getBboxWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.bbox) + ": " + getBbox();
    }

    /**
     * Returns the start date parameter preceded by a short description.
     * @return a String representation of the start date
     */
    public String getStartDateWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.start_date) + ": " + getStartDate();
    }

    /**
     * Returns the end date parameter preceded by a short description.
     * @return a String representation of the end date
     */
    public String getEndDateWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.end_date) + ": " + getEndDate();
    }
}
