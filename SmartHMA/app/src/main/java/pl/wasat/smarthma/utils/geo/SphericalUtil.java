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

package pl.wasat.smarthma.utils.geo;

import pl.wasat.smarthma.utils.obj.LatLngExt;

/**
 * Created by Daniel on 2016-03-09.
 * This file is a part of module SmartHMA project.
 */
public class SphericalUtil {

        /**
         * Compute offset lat lng ext.
         *
         * @param from     the from
         * @param distance the distance
         * @param heading  the heading
         * @return the lat lng ext
         */
        public static LatLngExt computeOffset(LatLngExt from, double distance, double heading) {
        distance /= 6371009.0D;
        heading = Math.toRadians(heading);
        double fromLat = Math.toRadians(from.latitude);
        double fromLng = Math.toRadians(from.longitude);
        double cosDistance = Math.cos(distance);
        double sinDistance = Math.sin(distance);
        double sinFromLat = Math.sin(fromLat);
        double cosFromLat = Math.cos(fromLat);
        double sinLat = cosDistance * sinFromLat + sinDistance * cosFromLat * Math.cos(heading);
        double dLng = Math.atan2(sinDistance * cosFromLat * Math.sin(heading), cosDistance - sinFromLat * sinLat);
        return new LatLngExt(Math.toDegrees(Math.asin(sinLat)), Math.toDegrees(fromLng + dLng));
    }
}
