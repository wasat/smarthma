package pl.wasat.smarthma.ui.menus;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.ui.activities.GlobalSettingsActivity;
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
                Log.d("ZX", "Search layout");
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

        LinearLayout settingsLayout = (LinearLayout) layout.findViewById(R.id.popup_menu_item_settings);
        clickableViews.add(settingsLayout);
        settingsLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(activity, GlobalSettingsActivity.class);
                activity.startActivityForResult(intent, Const.REQUEST_CODE_GLOBAL_SETTINGS);
                popupWindow.dismiss();
                popupWindow = null;
            }
        });
    }
}
