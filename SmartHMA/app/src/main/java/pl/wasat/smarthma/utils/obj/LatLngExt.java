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

package pl.wasat.smarthma.utils.obj;

import java.io.Serializable;

import pl.wasat.smarthma.helper.Const;

/**
 * Created by Daniel on 2015-03-03 15:39.
 * Part of the project  SmartHMA
 */
public class LatLngExt implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * The Latitude.
     */
    public final transient double latitude;
    /**
     * The Longitude.
     */
    public final transient double longitude;

    /**
     * Instantiates a new Lat lng ext.
     *
     * @param lat the lat
     * @param lng the lng
     */
    public LatLngExt(Double lat, Double lng) {
        latitude = lat;
        longitude = lng;
    }

    /**
     * Instantiates a new Lat lng ext.
     *
     * @param latLng the lat lng
     */
    public LatLngExt(com.amazon.geo.mapsv2.model.LatLng latLng) {
        longitude = latLng.longitude;
        latitude = latLng.latitude;
    }

    /**
     * Instantiates a new Lat lng ext.
     *
     * @param latLng the lat lng
     */
    public LatLngExt(com.google.android.gms.maps.model.LatLng latLng) {
        longitude = latLng.longitude;
        latitude = latLng.latitude;
    }

    /**
     * Instantiates a new Lat lng ext.
     *
     * @param south the south
     * @param west  the west
     */
    public LatLngExt(float south, float west) {
        latitude = south;
        longitude = west;
    }

    /**
     * Gets amzn lat lon.
     *
     * @return the amzn lat lon
     */
    public com.amazon.geo.mapsv2.model.LatLng getAmznLatLon() {
        if (Const.IS_KINDLE) {
            return new com.amazon.geo.mapsv2.model.LatLng(latitude, longitude);
        } else {
            return null;
        }
    }

    /**
     * Gets google lat lon.
     *
     * @return the google lat lon
     */
    public com.google.android.gms.maps.model.LatLng getGoogleLatLon() {
        if (!Const.IS_KINDLE) {
            return new com.google.android.gms.maps.model.LatLng(latitude, longitude);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "LatLngExt{" +
                latitude + ", " +
                longitude + "}";
    }
}

