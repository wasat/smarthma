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

package pl.wasat.smarthma.ui.frags.common;

import android.graphics.Color;
import android.os.Bundle;

import com.amazon.geo.mapsv2.AmazonMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.kindle.AmznMapDrawings;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.ui.frags.base.BaseFeedSummaryFragment;
import pl.wasat.smarthma.utils.draw.MapDrawings;
import pl.wasat.smarthma.utils.obj.LatLngExt;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the {@link FeedSummaryProductsFragment#newInstance}
 * factory method to create an instance of this fragment.
 */
public class FeedSummaryProductsFragment extends BaseFeedSummaryFragment {

    /**
     * Instantiates a new Feed summary products fragment.
     */
    public FeedSummaryProductsFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param feedParam Parameter 1.
     * @return A new instance of fragment FeedSummaryFragment.
     */
    public static FeedSummaryProductsFragment newInstance(Feed feedParam) {
        FeedSummaryProductsFragment fragment = new FeedSummaryProductsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BaseFeedSummaryFragment.KEY_FEED_SUMMARY, feedParam);
        fragment.setArguments(args);
        return fragment;
    }

    /* (non-Javadoc)
     * @see pl.wasat.smarthma.ui.frags.base.FeedSummaryFragment#loadNavSearch(java.lang.String)
     */
    @Override
    public void loadNavSearch(String linkHref) {
        super.loadNavSearch(linkHref);
        FedeoRequestParams request = new FedeoRequestParams(null);
        request.setUrl(linkHref);

        ProductsListFragment productsListFragment = ProductsListFragment
                .newInstance(request, null);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container, productsListFragment)
                .commit();

    }

    @Override
    protected void setupAmazonMapObjects(AmazonMap amazonMap) {
        super.setupAmazonMapObjects(amazonMap);
        drawAllAmznFootprint(amazonMap);
    }

    @Override
    protected void setupGoogleMapObjects(GoogleMap googleMap) {
        super.setupGoogleMapObjects(googleMap);
        drawAllGoogleFootprint(googleMap);
    }

    private void drawAllGoogleFootprint(GoogleMap googleMap) {

        MapDrawings mapDrawings = new MapDrawings();

        for (Entry entry : resultFeed.getEntries()) {
            ArrayList<LatLng> footprintPoints = castToGoogleLatLonArray(entry.getSimpleMetadata().getFootprint());
            if (footprintPoints.size() > 0) {
                com.google.android.gms.maps.model.PolygonOptions polygon = mapDrawings.drawArea(footprintPoints, Color.BLUE);
                googleMap.addPolygon(polygon);
            }
        }
    }

    private ArrayList<LatLng> castToGoogleLatLonArray(ArrayList<LatLngExt> latLngExtArrayList) {
        ArrayList<LatLng> latLngs = new ArrayList<>();
        for (LatLngExt latLngExt : latLngExtArrayList) {
            latLngs.add(latLngExt.getGoogleLatLon());
        }
        return latLngs;
    }

    private void drawAllAmznFootprint(AmazonMap amazonMap) {

        AmznMapDrawings amznMapDrawings = new AmznMapDrawings();

        for (Entry entry : resultFeed.getEntries()) {
            ArrayList<com.amazon.geo.mapsv2.model.LatLng> footprintPoints = castToAmazonLatLonArray(entry.getSimpleMetadata().getFootprint());
            if (footprintPoints.size() > 0) {
                com.amazon.geo.mapsv2.model.PolygonOptions polygon = amznMapDrawings.drawArea(footprintPoints, Color.BLUE);
                amazonMap.addPolygon(polygon);
            }
        }
    }

    private ArrayList<com.amazon.geo.mapsv2.model.LatLng> castToAmazonLatLonArray(ArrayList<LatLngExt> latLngExtArrayList) {
        ArrayList<com.amazon.geo.mapsv2.model.LatLng> latLngs = new ArrayList<>();
        for (LatLngExt latLngExt : latLngExtArrayList) {
            latLngs.add(latLngExt.getAmznLatLon());
        }
        return latLngs;
    }
}
