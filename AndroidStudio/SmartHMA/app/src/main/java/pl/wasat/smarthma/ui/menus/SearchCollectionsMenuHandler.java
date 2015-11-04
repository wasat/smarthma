package pl.wasat.smarthma.ui.menus;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.activities.FavouriteCollectionsActivity;
import pl.wasat.smarthma.ui.activities.SearchCollectionResultsActivity;

/**
 * Used for handling menu in the SearchCollectionResultsActivity class.
 */
public class SearchCollectionsMenuHandler extends MenuHandler {
    public SearchCollectionsMenuHandler(SearchCollectionResultsActivity activity, int menuButtonID) {
        super(activity, menuButtonID);
    }

    /**
     * Inflates the popup window layout.
     */
    protected void inflateLayout() {
        layout = layoutInflater.inflate(R.layout.popup_menu_search_collections, null);
    }

    /**
     * Creates on click listeners for specific buttons.
     */
    protected void addListeners() {
        LinearLayout sortLayout = (LinearLayout) layout.findViewById(R.id.popup_sort_layout);
        clickableViews.add(sortLayout);
        sortLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SortCollectionsDialog newFragment = new SortCollectionsDialog();
                newFragment.setActivity((SearchCollectionResultsActivity) activity);
                newFragment.show(activity.getSupportFragmentManager(), "Sorting_Options");
                popupWindow.dismiss();
                popupWindow = null;
            }
        });

        LinearLayout favouriteCollectionsLayout = (LinearLayout) layout.findViewById(R.id.popup_favourites_layout);
        clickableViews.add(favouriteCollectionsLayout);
        favouriteCollectionsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, FavouriteCollectionsActivity.class);
                //intent.setClass(activity, GlobalSettingsActivity.class);
                Log.d("ZX", "Starting FavouriteCollectionsActivity...");
                activity.startActivity(intent);
                //activity.startActivityForResult(intent, Const.REQUEST_CODE_GLOBAL_SETTINGS);
                popupWindow.dismiss();
                popupWindow = null;
            }
        });

        addCommonListeners();
    }
}
