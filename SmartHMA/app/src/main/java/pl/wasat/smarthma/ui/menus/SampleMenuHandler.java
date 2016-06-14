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

import android.view.View;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.activities.StartActivity;

/**
 * Used for handling menu in the StartActivity class.
 */
class SampleMenuHandler extends MenuHandler {
    /**
     * Instantiates a new Sample menu handler.
     *
     * @param rootView     the root view
     * @param activity     the activity
     * @param menuButtonID the menu button id
     */
    public SampleMenuHandler(View rootView, StartActivity activity, int menuButtonID) {
        super(rootView, activity, menuButtonID);
    }

    protected void inflateLayout() {
        layout = layoutInflater.inflate(R.layout.popup_menu, null);
    }
}
