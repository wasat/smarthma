package pl.wasat.smarthma.ui.menus;

import android.view.View;
import android.widget.LinearLayout;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.activities.SearchActivity;

/**
 * Used for handling menu in the SearchActivity class.
 */
public class SearchMenuHandler extends MenuHandler {
    public SearchMenuHandler(SearchActivity activity, int menuButtonID) {
        super(activity, menuButtonID);
    }

    /**
     * Inflates the popup window layout.
     */
    protected void inflateLayout() {
        layout = layoutInflater.inflate(R.layout.popup_menu_search, null);
    }

    /**
     * Creates on click listeners for specific buttons.
     */
    protected void addListeners() {
        LinearLayout searchHistoryLayout = (LinearLayout) layout.findViewById(R.id.popup_search_history_layout);
        clickableViews.add(searchHistoryLayout);
        searchHistoryLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SearchParametersDialog newFragment = new SearchParametersDialog();
                newFragment.setActivity((SearchActivity) activity);
                newFragment.show(activity.getSupportFragmentManager(), "Search_Parameters");
                popupWindow.dismiss();
                popupWindow = null;
            }
        });

        LinearLayout searchLayout = (LinearLayout) layout.findViewById(R.id.popup_search_layout);
        clickableViews.add(searchLayout);
        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // nothing yet
            }
        });

        addCommonListeners();
    }
}
