package pl.wasat.smarthma.database;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.model.FedeoRequest;

/**
 * Created by Dark Mark on 24/02/2015.
 * ETI
 */
public class SearchParams {
    private final String searchPhrase;
    private final String catalogue;
    private final String bbox;
    private final String startDate;
    private final String endDate;

    public SearchParams(FedeoRequest request) {
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
    public String toString() {
        //return searchPhrase + " " +catalogue + " " + bbox + " " + startDate + " " + endDate;
        return SmartHMApplication.appSingleton.getString(R.string.query) + ": " + searchPhrase + "; "
                + SmartHMApplication.appSingleton.getString(R.string.catalogue) + ": " + catalogue + "; "
                + "BBOX: " + bbox + "; "
                + SmartHMApplication.appSingleton.getString(R.string.time_period) + ": " + startDate + " - " + endDate;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public String getBbox() {
        return bbox;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getSearchPhraseWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.query) + ": " + getSearchPhrase();
    }

    public String getCatalogueWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.catalogue) + ": " + getCatalogue();
    }

    public String getBboxWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.bbox) + ": " + getBbox();
    }

    public String getStartDateWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.start_date) + ": " + getStartDate();
    }

    public String getEndDateWithPrefix() {
        return SmartHMApplication.appSingleton.getString(R.string.end_date) + ": " + getEndDate();
    }
}
