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

package pl.wasat.smarthma.utils.io;

import com.google.android.gms.maps.GoogleMap;

import org.acra.ACRA;

/**
 * The type Acra extension.
 */
class AcraExtension {
    private static final String NULL = "Null";
    private static final String MAP_INSTANCE = "-map_instance";
    private static final String MAP_CALLED = "-map_called";

    /**
     * Map custom log.
     *
     * @param place the place
     * @param map   the map
     */
    public static void mapCustomLog(String place, GoogleMap map) {
        mapLifeCycle(place);
        mapState(checkInstance(map));
    }

    private static void mapLifeCycle(String place) {
        ACRA.getErrorReporter().putCustomData(
                System.currentTimeMillis() + MAP_CALLED, place);
    }

    private static void mapState(String instance) {
        ACRA.getErrorReporter().putCustomData(
                System.currentTimeMillis() + MAP_INSTANCE, instance);
    }

    /**
     * @param map GoogleMap object
     * @return String instance
     */
    private static String checkInstance(GoogleMap map) {
        String instance;
        if (map != null) {
            instance = map.toString();
        } else {
            instance = NULL;
        }
        return instance;
    }

    /**
     * Map custom log.
     *
     * @param place the place
     * @param map   the map
     */
    public static void mapCustomLog(String place, com.amazon.geo.mapsv2.AmazonMap map) {
        mapLifeCycle(place);
        mapState(checkInstance(map));
    }

    private static String checkInstance(com.amazon.geo.mapsv2.AmazonMap map) {
        String instance;
        if (map != null) {
            instance = map.toString();
        } else {
            instance = NULL;
        }
        return instance;
    }
}
