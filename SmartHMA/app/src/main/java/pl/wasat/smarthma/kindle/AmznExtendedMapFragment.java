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

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.model.BitmapDescriptor;
import com.amazon.geo.mapsv2.model.BitmapDescriptorFactory;
import com.amazon.geo.mapsv2.model.GroundOverlay;
import com.amazon.geo.mapsv2.model.GroundOverlayOptions;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.LatLngBounds;
import com.amazon.geo.mapsv2.model.PolygonOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.interfaces.OnBaseMapFragmentPublicListener;
import pl.wasat.smarthma.model.entry.SimpleMetadata;
import pl.wasat.smarthma.utils.geo.FootprintGeometry;
import pl.wasat.smarthma.utils.obj.LatLngExt;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link pl.wasat.smarthma.kindle.AmznExtendedMapFragment.OnAmznExtendedMapFragmentListener}
 * interface to handle interaction events. Use the
 * {@link AmznExtendedMapFragment#newInstance} factory method to create an instance
 * of this fragment.
 */
public class AmznExtendedMapFragment extends Fragment implements
        OnBaseMapFragmentPublicListener, Target, Transformation,
        OnSeekBarChangeListener {

    private static final int BEARING_LEVEL_VALUE = 0;
    private static final int START_OPACITY = 25;

    private AmznBaseMapFragment baseMapFragment;
    private AmazonMap mMap;
    private GroundOverlay groundOverlay;
    private SeekBar seekBarOpacity;
    private BitmapDescriptor qLookImage;

    private OnAmznExtendedMapFragmentListener mListener;

    private LatLng qLookCenter;
    private float qLookWidth;
    private float qLookHeight;
    private float qLookBearing;
    private float[] qlookBearingsArray;
    private int qlookBearingIdx = 0;

    /**
     * Instantiates a new Amzn extended map fragment.
     */
    public AmznExtendedMapFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment MapExtendedFragment.
     */
// TODO: Rename and change types and number of parameters
    public static AmznExtendedMapFragment newInstance() {
        return new AmznExtendedMapFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnAmznExtendedMapFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnAmznExtendedMapFragmentListener.class.getSimpleName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map_extended,
                container, false);

        seekBarOpacity = (SeekBar) rootView
                .findViewById(R.id.seekBar_opacity_overlay);
        seekBarOpacity.setOnSeekBarChangeListener(this);

        ImageButton imgBtnRotate = (ImageButton) rootView.findViewById(R.id.frag_map_ext_btn_ic_rotate);
        imgBtnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateQuickLook();
            }
        });

        final ImageButton imgBtnFit = (ImageButton) rootView.findViewById(R.id.frag_map_ext_btn_ic_fit);
        imgBtnFit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fitQuicklook();
                changeFitIcon(imgBtnFit);
            }
        });

        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getChildFragmentManager();
        baseMapFragment = (AmznBaseMapFragment) fm
                .findFragmentById(R.id.frag_support_map_base);
        if (baseMapFragment == null) {
            baseMapFragment = AmznBaseMapFragment.newInstance(this);
            fm.beginTransaction()
                    .replace(R.id.frag_support_map_base, baseMapFragment)
                    .commit();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onBaseSupportMapPublicReady() {
        mMap = baseMapFragment.getMap();
        if (mListener != null) {
            mListener.onAmznMapReady();
        }
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, LoadedFrom arg1) {
        qLookImage = BitmapDescriptorFactory.fromBitmap(bitmap);

        GroundOverlayOptions groundOverlayOpt = new GroundOverlayOptions()
                .image(qLookImage)
                .position(qLookCenter, qLookWidth, qLookHeight)
                .bearing(qLookBearing).zIndex(2)
                .transparency((float) START_OPACITY / 100);

        try {
            groundOverlay = mMap.addGroundOverlay(groundOverlayOpt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        seekBarOpacity.setProgress(START_OPACITY);
    }

    @Override
    public void onBitmapFailed(Drawable arg0) {

    }

    @Override
    public void onPrepareLoad(Drawable arg0) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {

        loadModifiedGroundOverlay();

/*        if (groundOverlay != null) {
            int opacity = seekBarOpacity.getProgress();
            groundOverlay.remove();

            GroundOverlayOptions groundOverlayOpt = new GroundOverlayOptions()
                    .image(qLookImage)
                    .position(qLookCenter, qLookWidth, qLookHeight)
                    .bearing(qLookBearing).zIndex(2)
                    .transparency((float) opacity / 100);

            groundOverlay = mMap.addGroundOverlay(groundOverlayOpt);
        }*/

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public Bitmap transform(Bitmap source) {
        int x = (int) ((source.getWidth()) / 1.5);
        int y = (int) ((source.getHeight()) / 1.5);
        Bitmap result = Bitmap.createScaledBitmap(source, x, y, false);
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override
    public String key() {
        return "SmartHMA";
    }

    private void changeFitIcon(ImageButton imgBtnFit) {
        if (qlookBearingIdx == 0 || qlookBearingIdx == 1)
            imgBtnFit.setBackgroundResource(R.drawable.ic_qlook_fit_left);
        else if (qlookBearingIdx == 2 || qlookBearingIdx == 3)
            imgBtnFit.setBackgroundResource(R.drawable.ic_qlook_fit_bottom);
        else if (qlookBearingIdx == 4 || qlookBearingIdx == 5)
            imgBtnFit.setBackgroundResource(R.drawable.ic_qlook_fit_right);
        else if (qlookBearingIdx == 6 || qlookBearingIdx == 7)
            imgBtnFit.setBackgroundResource(R.drawable.ic_qlook_fit_top);
    }

    /**
     * Show foot prints.
     *
     * @param footprint the footprint
     */
    public void showFootPrints(ArrayList<LatLngExt> footprint) {
        ArrayList<LatLng> footprintPoints = extractLatLngFootprint(footprint);
        buildFootprintBounds(footprintPoints);
        drawFootprint(footprintPoints);
    }



    private ArrayList<LatLng> extractLatLngFootprint(ArrayList<LatLngExt> footprint) {
        ArrayList<LatLng> footprintPoints = new ArrayList<>();
        float prevBearing = 0;

        for (int i = 0; i < footprint.size() - 1; i++) {
            double currLat = footprint.get(i).latitude;
            double currLng = footprint.get(i).longitude;
            double nextLat = footprint.get(i + 1).latitude;
            double nextLng = footprint.get(i + 1).longitude;

            float[] results = new float[3];

            Location.distanceBetween(currLat, currLng, nextLat, nextLng,
                    results);

            if (Math.abs(results[2] - prevBearing) >= BEARING_LEVEL_VALUE) {
                footprintPoints.add(footprint.get(i).getAmznLatLon());
            }
            prevBearing = results[2];
        }
        return footprintPoints;
    }

    private void buildFootprintBounds(List<LatLng> footprintPoints) {
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        for (int j = 0; j < footprintPoints.size(); j++) {
            boundsBuilder.include(footprintPoints.get(j));
        }
        baseMapFragment.setTargetBounds(boundsBuilder.build());
        baseMapFragment.animateWhenMapIsReady(75);
    }

    private void drawFootprint(ArrayList<LatLng> footprintPoints) {
        if (footprintPoints.size() > 0) {
            PolygonOptions rectOptions = new PolygonOptions();
            rectOptions.addAll(footprintPoints);
            rectOptions.strokeColor(Color.HSVToColor(new float[]{
                    179 + (9 * 4), 1, 1})); // change 4 to variable;
            rectOptions.strokeWidth(4);
            rectOptions.geodesic(true);
            rectOptions.zIndex(1);
            mMap.addPolygon(rectOptions);
        }
    }

    /**
     * Show quicklook on map.
     *
     * @param simpleMetadata the simple metadata
     */
    public void showQuicklookOnMap(SimpleMetadata simpleMetadata) {

        ArrayList<LatLng> footprintPoints = castToAmznLatLonArray(simpleMetadata.getFootprint());
        LatLngExt center = simpleMetadata.getFootprintCenter();
        String url = simpleMetadata.getQuickLookUrl();

        buildFootprintBounds(footprintPoints);

        calcQuickLookParams(center, footprintPoints);
        drawFootprint(footprintPoints);

        Target quicklookTarget = this;
        if (!url.isEmpty()) {
            Picasso.with(getActivity())
                    .load(url)
                    .transform(this)
                    .into(quicklookTarget);
        }
/*        OkHttpClient client = new OkHttpClient();
        client.setProtocols(Collections.singletonList(Protocol.HTTP_1_1));
        final Picasso picasso = new Picasso.Builder(getActivity())
                .downloader(new OkHttpDownloader(client))
                .build();*/

        Picasso.with(getActivity()).load(url).transform(this)
                .into(quicklookTarget);
    }

    private ArrayList<LatLng> castToAmznLatLonArray(ArrayList<LatLngExt> latLngExtArrayList) {
        ArrayList<LatLng> latLngs = new ArrayList<>();
        for (LatLngExt latLngExt : latLngExtArrayList) {
            latLngs.add(latLngExt.getAmznLatLon());
        }
        return latLngs;
    }

    private ArrayList<LatLngExt> castToLatLngExtArray(ArrayList<LatLng> latLngs) {
        ArrayList<LatLngExt> latLngExtArray = new ArrayList<>();
        for (LatLng latLng : latLngs) {
            latLngExtArray.add(new LatLngExt(latLng));
        }
        return latLngExtArray;
    }

    private void calcQuickLookParams(LatLngExt footprintCenter,
                                     ArrayList<LatLng> footprintPoints) {
        //TODO - process quicklook if points > 5
        FootprintGeometry footprintGeometry = new FootprintGeometry(castToLatLngExtArray(footprintPoints), footprintCenter).invoke();
        qLookCenter = footprintGeometry.getCenter().getAmznLatLon();
        qlookBearingsArray = footprintGeometry.getBearingsArray();
        qLookWidth = footprintGeometry.getWidth();
        qLookHeight = footprintGeometry.getHeight();
        qLookBearing = footprintGeometry.getInitBearing();

        for (int i = 0; i < qlookBearingsArray.length; i++) {
            float b = qlookBearingsArray[i];
            Log.i("BEAR_ALL", i + " - " + String.valueOf(b));
        }
    }


    @SuppressWarnings("SuspiciousNameCombination")
    private void rotateQuickLook() {
        qLookBearing = qLookBearing + 90;
        float modQLWidth = qLookWidth;
        qLookWidth = qLookHeight;
        qLookHeight = modQLWidth;
        loadModifiedGroundOverlay();
    }


    private void fitQuicklook() {
        qLookBearing = qlookBearingsArray[qlookBearingIdx];
        Log.i("FIT", String.valueOf(qLookBearing));
        loadModifiedGroundOverlay();
        if (qlookBearingIdx <= 6)
            qlookBearingIdx = qlookBearingIdx + 1;
        else
            qlookBearingIdx = 0;
    }

    private void loadModifiedGroundOverlay() {
        if (groundOverlay != null) {
            int opacity = seekBarOpacity.getProgress();
            groundOverlay.remove();

            GroundOverlayOptions groundOverlayOpt = new GroundOverlayOptions()
                    .image(qLookImage)
                    .position(qLookCenter, qLookWidth, qLookHeight)
                    .bearing(qLookBearing)
                    .zIndex(2)
                    .anchor(0.5f, 0.5f)
                    .transparency((float) opacity / 100);

            groundOverlay = mMap.addGroundOverlay(groundOverlayOpt);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAmznExtendedMapFragmentListener {
        /**
         * On amzn map ready.
         */
        void onAmznMapReady();
    }
}
