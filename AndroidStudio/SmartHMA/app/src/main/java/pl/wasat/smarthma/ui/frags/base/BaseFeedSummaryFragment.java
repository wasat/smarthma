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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.IntroGridAdapter;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.utils.draw.MapDrawings;

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
        adapterNamesList.add("generated");
        adapterValuesList.add("-  " + resultFeed.getUpdated());

        IntroGridAdapter adapter = new IntroGridAdapter(getActivity(), adapterNamesList, adapterValuesList);
        ListView gridView = (ListView) rootView.findViewById(R.id.intro_grid_layout);
        gridView.setAdapter(adapter);

        return rootView;
    }

/*    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Fragment fragment = (getChildFragmentManager().findFragmentById(R.id.mapFromRegionDetails));
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }*/

    private void setUpStaticMap() {
        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mapFromRegionDetails, supportMapFragment);
        fragmentTransaction.commit();

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        setupMapObjects(googleMap);
                    }
                });
            }
        });
    }

    protected void setupMapObjects(GoogleMap googleMap) {
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(false);

        //Centre of EU
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(obtainBoundsFromShared(), 30));

        MapDrawings mapDrawings = new MapDrawings();
        SharedPrefs sharedPrefs = new SharedPrefs(getActivity());
        float[] bbox = sharedPrefs.getBboxPrefs();
        googleMap.addPolygon(mapDrawings.drawArea(bbox));
    }

    private LatLngBounds obtainBoundsFromShared() {
        SharedPrefs sharedPrefs = new SharedPrefs(getActivity());
        float[] bbox = sharedPrefs.getBboxPrefs();

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        boundsBuilder.include(new LatLng(bbox[1], bbox[0]));
        boundsBuilder.include(new LatLng(bbox[3], bbox[2]));

        return boundsBuilder.build();

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
            String linkRel = resultFeed.getLink().get(i).getRel();
            final int incFinal = i;

            if (linkRel.equalsIgnoreCase("first")) {
                btnFirst.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String linkHref = resultFeed.getLink().get(incFinal)
                                .getHref();
                        loadNavSearch(linkHref);
                    }
                });
            } else if (linkRel.equalsIgnoreCase("previous")) {
                btnPrev.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String linkHref = resultFeed.getLink().get(incFinal)
                                .getHref();
                        loadNavSearch(linkHref);
                    }
                });
            } else if (linkRel.equalsIgnoreCase("self")) {
                btnReload.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String linkHref = resultFeed.getLink().get(incFinal)
                                .getHref();
                        loadNavSearch(linkHref);
                    }
                });
            } else if (linkRel.equalsIgnoreCase("next")) {
                btnNext.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String linkHref = resultFeed.getLink().get(incFinal)
                                .getHref();
                        loadNavSearch(linkHref);
                    }
                });
            } else if (linkRel.equalsIgnoreCase("last")) {
                btnLast.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String linkHref = resultFeed.getLink().get(incFinal)
                                .getHref();
                        loadNavSearch(linkHref);
                    }
                });
            }
        }
    }

    protected void loadNavSearch(String linkHref) {
    }

    private int castToInt(String value) {
        int resInt = 0;
        if (!value.equals("") && value.matches("^\\d+$")) {
            resInt = Integer.valueOf(value);
        }
        return resInt;
    }
}

