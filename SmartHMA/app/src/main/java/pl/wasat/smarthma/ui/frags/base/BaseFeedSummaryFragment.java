package pl.wasat.smarthma.ui.frags.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.amazon.geo.mapsv2.AmazonMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.IntroGridAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.helper.enums.Opts;
import pl.wasat.smarthma.kindle.AmznMapDrawings;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.utils.draw.MapDrawings;
import pl.wasat.smarthma.utils.obj.LatLngBoundsExt;
import pl.wasat.smarthma.utils.obj.LatLngExt;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class BaseFeedSummaryFragment extends Fragment {
    protected static final String KEY_FEED_SUMMARY = "pl.wasat.smarthma.KEY_FEED_SUMMARY";

    protected Feed resultFeed;
    private TextView tvTitle;
    private TextView tvItemsFrom;
    private TextView tvItemsTo;
    private TextView tvItemsTotal;

    private View btnFirst;
    private View btnPrev;
    private View btnReload;
    private View btnNext;
    private View btnLast;

    public BaseFeedSummaryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resultFeed = (Feed) getArguments()
                    .getSerializable(KEY_FEED_SUMMARY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_search_data_series_intro, container, false);

        tvTitle = (TextView) rootView
                .findViewById(R.id.search_frag_ds_intro_title);
        tvItemsFrom = (TextView) rootView
                .findViewById(R.id.search_frag_ds_intro_items_from_value);
        tvItemsTo = (TextView) rootView
                .findViewById(R.id.search_frag_ds_intro_items_to_value);
        tvItemsTotal = (TextView) rootView
                .findViewById(R.id.search_frag_ds_intro_items_of_total_value);

        btnFirst = rootView
                .findViewById(R.id.search_frag_ds_intro_button_first);
        btnPrev = rootView
                .findViewById(R.id.search_frag_ds_intro_button_prev);
        btnReload = rootView
                .findViewById(R.id.search_frag_ds_intro_button_reload);
        btnNext = rootView
                .findViewById(R.id.search_frag_ds_intro_button_next);
        btnLast = rootView
                .findViewById(R.id.search_frag_ds_intro_button_last);

        initUITexts();
        initUIButtons();

        setUpStaticMap();

        ArrayList<String> adapterNamesList = resultFeed.getQuery().getParamNameList();
        ArrayList<String> adapterValuesList = resultFeed.getQuery().getParamValueList();
/*        adapterNamesList.add("generated");
        adapterValuesList.add("-  " + resultFeed.getUpdated());*/

        ArrayList<String> adapterTooltipsList = new ArrayList<>();
        for (int i = 0; i < adapterNamesList.size(); i++) {
            adapterTooltipsList.add("Greetings from " + i);
        }

        IntroGridAdapter adapter = new IntroGridAdapter(getActivity(), adapterNamesList, adapterValuesList, adapterTooltipsList);
        ListView gridView = (ListView) rootView.findViewById(R.id.intro_grid_layout);
        gridView.setAdapter(adapter);

        return rootView;
    }

    /**
     *
     */
    private void initUITexts() {
        int startIdx = castToInt(resultFeed.getStartIndex().getText());
        int itemsPerPage = castToInt(resultFeed.getItemsPerPage().getText());
        int totalRes = castToInt(resultFeed.getTotalResults().getText());

        int toVal = startIdx + itemsPerPage - 1;
        if (toVal > totalRes) toVal = totalRes;
        if (totalRes == 0) {
            toVal = totalRes;
            startIdx = totalRes;
        }
        tvTitle.setText(resultFeed.getTitle());
        tvItemsFrom.setText(String.valueOf(startIdx));
        tvItemsTo.setText(String.valueOf(toVal));
        tvItemsTotal.setText(String.valueOf(totalRes));
    }

    private void initUIButtons() {
        int linkSize = resultFeed.getLink().size();

        for (int i = 0; i < linkSize; i++) {
            final Link link = resultFeed.getLink().get(i);
            String linkRel = link.getRel();

            if (linkRel.equalsIgnoreCase(Link.REL_FIRST)) {
                btnFirst.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String linkHref = link.getHref();
                        loadNavSearch(linkHref);
                    }
                });
            } else if (linkRel.equalsIgnoreCase(Link.REL_PREVIOUS)) {
                btnPrev.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String linkHref = link.getHref();
                        loadNavSearch(linkHref);
                    }
                });
            } else if (linkRel.equalsIgnoreCase(Link.REL_SELF)) {
                btnReload.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String linkHref = link.getHref();
                        loadNavSearch(linkHref);
                    }
                });
            } else if (linkRel.equalsIgnoreCase(Link.REL_NEXT)) {
                btnNext.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String linkHref = link.getHref();
                        loadNavSearch(linkHref);
                    }
                });
            } else if (linkRel.equalsIgnoreCase(Link.REL_LAST)) {
                btnLast.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String linkHref = link.getHref();
                        loadNavSearch(linkHref);
                    }
                });
            }
        }
    }


    private void setUpStaticMap() {
        if (Const.IS_KINDLE) setUpStaticAmazonMap();
        else setUpStaticGoogleMap();
    }

    private int castToInt(String value) {
        int resInt = 0;
        if (!value.equals("") && value.matches("^\\d+$")) {
            resInt = Integer.valueOf(value);
        }
        return resInt;
    }

    protected void loadNavSearch(String linkHref) {
    }

    private void setUpStaticAmazonMap() {
        com.amazon.geo.mapsv2.SupportMapFragment supportMapFragment = com.amazon.geo.mapsv2.SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map_area_bounds, supportMapFragment);
        fragmentTransaction.commit();

        supportMapFragment.getMapAsync(new com.amazon.geo.mapsv2.OnMapReadyCallback() {
            @Override
            public void onMapReady(final AmazonMap amazonMap) {
                amazonMap.setOnMapLoadedCallback(new AmazonMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        setupAmazonMapObjects(amazonMap);
                    }
                });
            }
        });

    }

    private void setUpStaticGoogleMap() {
        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map_area_bounds, supportMapFragment);
        fragmentTransaction.commit();

        supportMapFragment.getMapAsync(new com.google.android.gms.maps.OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        setupGoogleMapObjects(googleMap);
                    }
                });
            }
        });
    }

    protected void setupAmazonMapObjects(AmazonMap amazonMap) {
        amazonMap.getUiSettings().setAllGesturesEnabled(false);
        amazonMap.getUiSettings().setZoomControlsEnabled(false);

        SharedPrefs sharedPrefs = new SharedPrefs(getActivity());
        if (sharedPrefs.getAreaUse()) {
            //Centre of EU
            LatLngBoundsExt latLngBoundsExt = obtainBoundsFromShared();
            amazonMap.moveCamera(com.amazon.geo.mapsv2.CameraUpdateFactory.newLatLngBounds(latLngBoundsExt.amznLatLngBounds, 30));

/*            AmznMapDrawings amznMapDrawings = new AmznMapDrawings();
            float[] bbox = sharedPrefs.getBboxPrefs();
            amazonMap.addPolygon(amznMapDrawings.drawArea(bbox));*/

            drawBounds(amazonMap, sharedPrefs);
        }
    }

    protected void setupGoogleMapObjects(GoogleMap googleMap) {
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(false);

        SharedPrefs sharedPrefs = new SharedPrefs(getContext());
        if (sharedPrefs.getAreaUse()) {
            //Centre of EU
            LatLngBoundsExt latLngBoundsExt = obtainBoundsFromShared();
            googleMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds(latLngBoundsExt.googleLatLngBounds, 30));

            drawBounds(googleMap, sharedPrefs);
        }
    }

    private void drawBounds(GoogleMap googleMap, SharedPrefs sharedPrefs) {
        MapDrawings mapDrawings = new MapDrawings();
        switch (sharedPrefs.getAreaType()) {
            case Opts.AREA_POLYGON:
                float[] bbox = sharedPrefs.getBboxPrefs();
                googleMap.addPolygon(mapDrawings.drawArea(bbox));
                break;
            case Opts.AREA_PT_RADIUS:
                float[] center = sharedPrefs.getCenterPrefs();
                float radius = sharedPrefs.getRadiusPrefs();
                googleMap.addCircle(mapDrawings.drawPointAndRadiusArea(new LatLng(center[0], center[1]), radius));
                break;
            default:
                break;
        }
    }

    private void drawBounds(AmazonMap amazonMap, SharedPrefs sharedPrefs) {
        AmznMapDrawings mapDrawings = new AmznMapDrawings();
        switch (sharedPrefs.getAreaType()) {
            case Opts.AREA_POLYGON:
                float[] bbox = sharedPrefs.getBboxPrefs();
                amazonMap.addPolygon(mapDrawings.drawArea(bbox));
                break;
            case Opts.AREA_PT_RADIUS:
                float[] center = sharedPrefs.getCenterPrefs();
                float radius = sharedPrefs.getRadiusPrefs();
                amazonMap.addCircle(mapDrawings.drawPointAndRadiusArea(new com.amazon.geo.mapsv2.model.LatLng(center[0], center[1]), radius));
                break;
            default:
                break;
        }
    }

    private LatLngBoundsExt obtainBoundsFromShared() {
        SharedPrefs sharedPrefs = new SharedPrefs(getActivity());
        float[] bbox = sharedPrefs.getBboxPrefs();
        LatLngExt sw = new LatLngExt(bbox[1], bbox[0]);
        LatLngExt ne = new LatLngExt(bbox[3], bbox[2]);

        return new LatLngBoundsExt(sw, ne);
    }
}

