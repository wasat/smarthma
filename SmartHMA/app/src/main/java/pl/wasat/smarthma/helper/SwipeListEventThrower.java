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

package pl.wasat.smarthma.helper;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.interfaces.OnSlideElementListener;

/**
 * The type Swipe list event thrower.
 */
public class SwipeListEventThrower {
    //list of catchers & corresponding function to add/remove them in the list
    private final List<OnSlideElementListener> listeners = new ArrayList<>();

    /**
     * Add throw listener.
     *
     * @param toAdd the to add
     */
    public void addThrowListener(OnSlideElementListener toAdd) {
        listeners.add(toAdd);
    }

    /**
     * Throw.
     *
     * @param swipeRight the swipe right
     * @param position   the position
     */
//Set of functions that Throw Events.
    public void Throw(boolean swipeRight, int position) {
        for (OnSlideElementListener hl : listeners) hl.Catch(swipeRight, position);
    }
}