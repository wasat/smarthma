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

package pl.wasat.smarthma.ui.menus;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.activities.FavouriteCollectionsActivity;
import pl.wasat.smarthma.ui.activities.SearchCollectionResultsActivity;
import pl.wasat.smarthma.ui.dialogs.SortCollectionsDialog;

/**
 * Used for handling menu in the SearchCollectionResultsActivity class.
 */
public class SearchCollectionsMenuHandler extends MenuHandler {
    /**
     * Instantiates a new Search collections menu handler.
     *
     * @param activity     the activity
     * @param menuButtonID the menu button id
     */
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
                activity.startActivity(intent);
                popupWindow.dismiss();
                popupWindow = null;
            }
        });

        addCommonListeners();
    }
}
