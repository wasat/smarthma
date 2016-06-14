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

import com.google.android.gms.maps.model.LatLngBounds;

import pl.wasat.smarthma.helper.Const;

/**
 * Created by Daniel Z. on 04.03.15.
 * Wasat Sp. z o.o.
 */
public class LatLngBoundsExt {
    /**
     * The Southwest.
     */
    public final LatLngExt southwest;
    /**
     * The Northeast.
     */
    public final LatLngExt northeast;
    /**
     * The Google lat lng bounds.
     */
    public com.google.android.gms.maps.model.LatLngBounds googleLatLngBounds;
    /**
     * The Amzn lat lng bounds.
     */
    public com.amazon.geo.mapsv2.model.LatLngBounds amznLatLngBounds;


    /**
     * Instantiates a new Lat lng bounds ext.
     *
     * @param areaBounds the area bounds
     */
    public LatLngBoundsExt(com.google.android.gms.maps.model.LatLngBounds areaBounds) {
        southwest = new LatLngExt(areaBounds.southwest);
        northeast = new LatLngExt(areaBounds.northeast);
        googleLatLngBounds = areaBounds;
    }

    /**
     * Instantiates a new Lat lng bounds ext.
     *
     * @param areaBounds the area bounds
     */
    public LatLngBoundsExt(com.amazon.geo.mapsv2.model.LatLngBounds areaBounds) {
        southwest = new LatLngExt(areaBounds.southwest);
        northeast = new LatLngExt(areaBounds.northeast);
        amznLatLngBounds = areaBounds;
    }

    /**
     * Instantiates a new Lat lng bounds ext.
     *
     * @param latLngSW the lat lng sw
     * @param latLngNE the lat lng ne
     */
    public LatLngBoundsExt(LatLngExt latLngSW, LatLngExt latLngNE) {
        southwest = latLngSW;
        northeast = latLngNE;
        if (Const.IS_KINDLE) {
            amznLatLngBounds = new com.amazon.geo.mapsv2.model.LatLngBounds(latLngSW.getAmznLatLon(), latLngNE.getAmznLatLon());
        } else {
            googleLatLngBounds = new LatLngBounds(latLngSW.getGoogleLatLon(), latLngNE.getGoogleLatLon());
        }
    }

    @Override
    public String toString() {
        return "LatLngBoundsExt{" +
                "southwest=" + southwest +
                ", northeast=" + northeast +
                '}';
    }
}
