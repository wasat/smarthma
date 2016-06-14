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

package pl.wasat.smarthma.kindle;

import android.content.Context;
import android.content.Intent;

import org.acra.ACRA;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import pl.wasat.smarthma.helper.Const;

/**
 * The type Amzn tile provider factory.
 */
class AmznTileProviderFactory {

    private static final String URL_BASE_OSM = "http://a.tile.openstreetmap.org/{z}/{x}/{y}.png";

    /**
     * Gets wms tile provider.
     *
     * @param WMS_name the wms name
     * @param context  the context
     * @return the wms tile provider
     */
    public static AmznWMSTileProvider getWmsTileProvider(final String WMS_name,
                                                         final Context context) {
        return new AmznWMSTileProvider(256, 256) {

            @Override
            public synchronized URL getTileUrl(int x, int y, int zoom) {
                double[] bbox = getBoundingBox(x, y, zoom);
                String s = String.format(Locale.US, WMS_name, bbox[MINX],
                        bbox[MINY], bbox[MAXX], bbox[MAXY]);
                URL url;
                try {
                    url = new URL(s);
                    sendWmsLoadState(context);
                } catch (MalformedURLException e) {
                    ACRA.getErrorReporter().handleSilentException(e);
                    throw new AssertionError(e);
                }
                return url;
            }
        };
    }

    private static void sendWmsLoadState(Context con) {
        Intent intent = new Intent(
                Const.KEY_MAP_SPINNER_INTENTFILTER_NOTIFICATION);
        intent.putExtra(Const.KEY_MAP_WMS_LOAD_STATE, true);
        con.sendBroadcast(intent);
    }

    /**
     * Gets osm wms tile provider.
     *
     * @param WMS_name the wms name
     * @return the osm wms tile provider
     */
    public static AmznWMSTileProvider getOsmWmsTileProvider(final String WMS_name) {

        return new AmznWMSTileProvider(256, 256) {

            @Override
            public synchronized URL getTileUrl(int x, int y, int zoom) {
                double[] bbox = getBoundingBox(x, y, zoom);
                String s = String.format(Locale.US, WMS_name, bbox[MINX],
                        bbox[MINY], bbox[MAXX], bbox[MAXY]);
                URL url;
                try {
                    url = new URL(s);
                } catch (MalformedURLException e) {
                    ACRA.getErrorReporter().handleSilentException(e);
                    throw new AssertionError(e);
                }
                return url;
            }
        };
    }

    /**
     * Gets osm tile provider.
     *
     * @return the osm tile provider
     */
    public static AmznWMSTileProvider getOSMTileProvider() {

        return new AmznWMSTileProvider(256, 256) {

            @Override
            public URL getTileUrl(int x, int y, int zoom) {
                try {
                    return new URL(URL_BASE_OSM.replace("{z}", "" + zoom)
                            .replace("{x}", "" + x).replace("{y}", "" + y));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    ACRA.getErrorReporter().handleSilentException(e);
                }
                return null;
            }
        };

    }
}

