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
import pl.wasat.smarthma.ui.activities.FavouriteProductsActivity;
import pl.wasat.smarthma.ui.activities.SearchActivity;
import pl.wasat.smarthma.ui.dialogs.SearchParametersDialog;

/**
 * Used for handling menu in the SearchActivity class.
 */
public class SearchMenuHandler extends MenuHandler {
    /**
     * Instantiates a new Search menu handler.
     *
     * @param activity     the activity
     * @param menuButtonID the menu button id
     */
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

        LinearLayout favouriteCollectionsLayout = (LinearLayout) layout.findViewById(R.id.popup_favourite_collections_layout);
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

        LinearLayout favouriteProductsLayout = (LinearLayout) layout.findViewById(R.id.popup_favourite_products_layout);
        clickableViews.add(favouriteProductsLayout);
        favouriteProductsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, FavouriteProductsActivity.class);
                activity.startActivity(intent);
                popupWindow.dismiss();
                popupWindow = null;
            }
        });

        addCommonListeners();
    }
}
