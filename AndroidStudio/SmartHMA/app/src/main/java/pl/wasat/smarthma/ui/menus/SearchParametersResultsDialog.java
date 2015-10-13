package pl.wasat.smarthma.ui.menus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.SearchHistory;
import pl.wasat.smarthma.ui.activities.SearchActivity;

/**
 * Handles user interaction related to search history management.
 */
public class SearchParametersResultsDialog extends DialogFragment {
    private SearchActivity activity;
    private int type = 0;

    public void setParameters(SearchActivity activity, int type) {
        this.activity = activity;
        this.type = type;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        SearchHistory searchHistory = new SearchHistory(getActivity());
        ArrayList<String> results = null;
        CharSequence title = null;

        if (type == 0) {
            results = searchHistory.getQueries(true);
            title = getString(R.string.search_history_pick_query);
        } else if (type == 1) {
            results = searchHistory.getCatalogues(true);
            title = getString(R.string.search_history_pick_catalogue);
        } else if (type == 2) {
            results = searchHistory.getBboxs(true);
            title = getString(R.string.search_history_pick_bbox);
        } else if (type == 3) {
            results = searchHistory.getStartDates(true);
            title = getString(R.string.search_history_pick_start_date);
        } else if (type == 4) {
            results = searchHistory.getEndDates(true);
            title = getString(R.string.search_history_pick_end_date);
        }

        final ArrayList<String> finalResults = results;

        assert results != null;
        final CharSequence[] resultsSequences = new CharSequence[results.size()];
        for (int i = 0; i < resultsSequences.length; i++) {
            resultsSequences[i] = results.get(i);
        }

        builder.setTitle(title)
                .setItems(resultsSequences, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Log.d("ZX", which + "");
                        try {
                            if (type == 0) {
                                activity.setQuery(finalResults.get(which));
                            } else if (type == 1) {
                                activity.setCatalogue(finalResults.get(which));
                            } else if (type == 2) {
                                activity.setBbox(finalResults.get(which));
                            } else if (type == 3) {
                                activity.setStartDate(finalResults.get(which));
                            } else if (type == 4) {
                                activity.setEndDate(finalResults.get(which));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
