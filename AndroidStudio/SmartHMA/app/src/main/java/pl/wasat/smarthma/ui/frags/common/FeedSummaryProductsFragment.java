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

    public FeedSummaryProductsFragment() {
    }

    /* (non-Javadoc)
     * @see pl.wasat.smarthma.ui.frags.base.FeedSummaryFragment#loadNavSearch(java.lang.String)
     */
    @Override
    public void loadNavSearch(String linkHref) {
        FedeoRequestParams request = new FedeoRequestParams();
        request.setUrl(linkHref);

        ProductsListFragment productsListFragment = ProductsListFragment
                .newInstance(request);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container, productsListFragment)
                .commit();
        super.loadNavSearch(linkHref);
    }

    @Override
    protected void setupGoogleMapObjects(GoogleMap googleMap) {
        super.setupGoogleMapObjects(googleMap);

        drawAllGoogleFootprint(googleMap);
    }


    private void drawAllGoogleFootprint(GoogleMap googleMap) {

        MapDrawings mapDrawings = new MapDrawings();

        for (Entry entry : resultFeed.getEntries()) {
            //List<Pos> footprintPosList = obtainFootprintPoints(entry);


            //ArrayList<LatLng> footprintPoints = (ArrayList<LatLng>) (Object) entry.getSimpleMetadata().getFootprint();
            ArrayList<LatLng> footprintPoints = castToGoogleLatLonArray(entry.getSimpleMetadata().getFootprint());
/*            for (int i = 0; i < footprintPosList.size() - 1; i++) {
                footprintPoints.add(footprintPosList.get(i).getLatLng().getGoogleLatLon());
            }*/
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

    @Override
    protected void setupAmazonMapObjects(AmazonMap amazonMap) {
        super.setupAmazonMapObjects(amazonMap);
        drawAllAmznFootprint(amazonMap);
    }


    private void drawAllAmznFootprint(AmazonMap amazonMap) {

        AmznMapDrawings amznMapDrawings = new AmznMapDrawings();

        for (Entry entry : resultFeed.getEntries()) {
            //List<Pos> footprintPosList = obtainFootprintPoints(entry);


            //ArrayList<LatLng> footprintPoints = (ArrayList<LatLng>) (Object) entry.getSimpleMetadata().getFootprint();
            ArrayList<com.amazon.geo.mapsv2.model.LatLng> footprintPoints = castToAmazonLatLonArray(entry.getSimpleMetadata().getFootprint());
/*            for (int i = 0; i < footprintPosList.size() - 1; i++) {
                footprintPoints.add(footprintPosList.get(i).getLatLng().getGoogleLatLon());
            }*/
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
    /*
    private List<Pos> obtainFootprintPoints(Entry entry) {
        List<Pos> footprintPosList = new ArrayList<>();
        if (entry.getEarthObservation().getFeatureOfInterest() == null) return footprintPosList;
        Footprint footprint = entry.getEarthObservation()
                .getFeatureOfInterest().getFootprint();
        footprintPosList = footprint.getMultiExtentOf()
                .getMultiSurface().getSurfaceMembers().getPolygon()
                .getExterior().getLinearRing().getPosList();
        if (footprintPosList.isEmpty()) {
            String posStr = footprint.getMultiExtentOf().getMultiSurface()
                    .getSurfaceMembers().getPolygon().getExterior()
                    .getLinearRing().getPosString().getPointsString();
            footprintPosList = footprint.getMultiExtentOf().getMultiSurface()
                    .getSurfaceMembers().getPolygon().getExterior()
                    .getLinearRing().setPosList(posStr);
        }
        return footprintPosList;
    }*/
}
