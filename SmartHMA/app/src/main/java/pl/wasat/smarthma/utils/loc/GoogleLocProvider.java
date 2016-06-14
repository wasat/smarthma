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

package pl.wasat.smarthma.utils.loc;

import android.location.Location;

/**
 * Created by Daniel on 2015-05-27 00:05.
 * Part of the project SmartHMA
 */
interface GoogleLocProvider {

    /**
     * Start.
     */
    void start();

    /**
     * On location received.
     *
     * @param receivedLocation the received location
     */
    void onLocationReceived(Location receivedLocation);

    /**
     * Stop.
     */
    void stop();

}
