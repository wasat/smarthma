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

import com.amazon.geo.mapsv2.model.UrlTileProvider;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * The type Amzn wms tile provider.
 */
public abstract class AmznWMSTileProvider extends UrlTileProvider {

    /**
     * The constant MINX.
     */
// array indexes for array to hold bounding boxes.
    static final int MINX = 0;
    /**
     * The Maxx.
     */
    static final int MAXX = 1;
    /**
     * The Miny.
     */
    static final int MINY = 2;
    /**
     * The Maxy.
     */
    static final int MAXY = 3;
    // Web Mercator n/w corner of the map.
    private static final double[] TILE_ORIGIN = {-20037508.34789244, 20037508.34789244};
    //array indexes for that data
    private static final int ORIG_X = 0;
    private static final int ORIG_Y = 1; // "
    // Size of square world map in meters, using Web Mercator projection.
    private static final double MAP_SIZE = 20037508.34789244 * 2;
    // cql filters
    private String cqlString = "";

    /**
     * Instantiates a new Amzn wms tile provider.
     *
     * @param x the x
     * @param y the y
     */
// Construct with tile size in pixels, normally 256, see parent class.
    public AmznWMSTileProvider(int x, int y) {
        super(x, y);
    }

    /**
     * Gets cql.
     *
     * @return the cql
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    protected String getCql() throws UnsupportedEncodingException {
        return URLEncoder.encode(cqlString, "UTF-8");
    }

    /**
     * Sets cql.
     *
     * @param c the c
     */
    public void setCql(String c) {
        cqlString = c;
    }

    /**
     * Get bounding box double [ ].
     *
     * @param x    the x
     * @param y    the y
     * @param zoom the zoom
     * @return the double [ ]
     */
// Return a web Mercator bounding box given tile x/y indexes and a zoom
    // level.
    double[] getBoundingBox(int x, int y, int zoom) {
        double tileSize = MAP_SIZE / Math.pow(2, zoom);
        double minx = TILE_ORIGIN[ORIG_X] + x * tileSize;
        double maxx = TILE_ORIGIN[ORIG_X] + (x + 1) * tileSize;
        double miny = TILE_ORIGIN[ORIG_Y] - (y + 1) * tileSize;
        double maxy = TILE_ORIGIN[ORIG_Y] - y * tileSize;

        double[] bbox = new double[4];
        bbox[MINX] = minx;
        bbox[MINY] = miny;
        bbox[MAXX] = maxx;
        bbox[MAXY] = maxy;

        return bbox;
    }

}

