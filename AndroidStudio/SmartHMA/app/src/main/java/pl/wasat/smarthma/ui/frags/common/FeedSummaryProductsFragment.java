package pl.wasat.smarthma.ui.frags.common;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.om.EntryOM;
import pl.wasat.smarthma.model.om.Footprint;
import pl.wasat.smarthma.model.om.Pos;
import pl.wasat.smarthma.ui.frags.base.BaseFeedSummaryFragment;
import pl.wasat.smarthma.utils.draw.MapDrawings;

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
    protected void setupMapObjects(GoogleMap googleMap) {
        super.setupMapObjects(googleMap);

        drawAllFootprint(googleMap);
    }


    private void drawAllFootprint(GoogleMap googleMap) {

        MapDrawings mapDrawings = new MapDrawings();

        for (EntryOM entry : resultFeed.getEntriesEO()) {
            List<Pos> footprintPosList = obtainFootprintPoints(entry);

            ArrayList<LatLng> footprintPoints = new ArrayList<>();
            for (int i = 0; i < footprintPosList.size() - 1; i++) {
                footprintPoints.add(footprintPosList.get(i).getLatLng().getGoogleLatLon());
            }
            if (footprintPoints.size() > 0) {
                PolygonOptions polygon = mapDrawings.drawArea(footprintPoints, Color.BLUE);
                googleMap.addPolygon(polygon);
            }
        }
    }

    private List<Pos> obtainFootprintPoints(EntryOM entry) {
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
    }
}
