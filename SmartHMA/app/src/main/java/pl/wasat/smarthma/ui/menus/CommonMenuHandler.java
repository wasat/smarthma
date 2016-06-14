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

import android.support.v4.app.FragmentActivity;
import android.view.View;

import pl.wasat.smarthma.R;

/**
 * Used for handling menu in the FragmentActivity class.
 */
public class CommonMenuHandler extends MenuHandler {
    /**
     * Instantiates a new Common menu handler.
     *
     * @param activity     the activity
     * @param menuButtonID the menu button id
     */
    public CommonMenuHandler(FragmentActivity activity, int menuButtonID) {
        super(activity, menuButtonID);
    }

    /**
     * Instantiates a new Common menu handler.
     *
     * @param rootView    the root view
     * @param activity    the activity
     * @param menu_button the menu button
     */
    public CommonMenuHandler(View rootView, FragmentActivity activity, int menu_button) {
        super(rootView, activity, menu_button);
    }

    /**
     * Inflates the popup window layout.
     */
    protected void inflateLayout() {
        layout = layoutInflater.inflate(R.layout.popup_menu_common, null);
    }

    /**
     * Creates on click listeners for specific buttons.
     */
    protected void addListeners() {
        addCommonListeners();
    }
}
