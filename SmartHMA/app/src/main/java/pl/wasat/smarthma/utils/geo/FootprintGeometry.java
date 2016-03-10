package pl.wasat.smarthma.utils.geo;

import android.location.Location;

import java.util.ArrayList;

import pl.wasat.smarthma.utils.obj.LatLngExt;

/**
 * Created by Daniel on 2016-03-09.
 * This file is a part of module SmartHMA project.
 */

public class FootprintGeometry {
    private LatLngExt footprintCenter;
    private ArrayList<LatLngExt> footprintPoints;
    //private ArrayList<Float> bearingsList = new ArrayList<>();
    private ArrayList<LatLngExt> corners = new ArrayList<>();

    private LatLngExt center;
    private float[] bearingsArray;
    private float initBearing;
    private float width;
    private float height;

    public FootprintGeometry(ArrayList<LatLngExt> footprintPoints, LatLngExt footprintCenter) {
        this.footprintPoints = footprintPoints;
        this.footprintCenter = footprintCenter;
    }

    public FootprintGeometry invoke() {
        validateGeometry();
        validateCenter(footprintCenter);
        generateBearings();
        return this;
    }

    public LatLngExt getCenter() {
        return center;
    }

    public float[] getBearingsArray() {
        if (bearingsArray == null) generateBearings();
        return bearingsArray;
    }

    public float getInitBearing() {
        return initBearing;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    private void validateGeometry() {
        float bearingTmp = 1000;

        for (int i = 0; i < footprintPoints.size() - 1; i++) {
            LatLngExt ftCurr = footprintPoints.get(i);
            LatLngExt ftNext = footprintPoints.get(i + 1);
            float[] res = new float[3];
            Location.distanceBetween(ftCurr.latitude, ftCurr.longitude, ftNext.latitude, ftNext.longitude, res);

            float normBear = Math.abs(res[1]);
            if (Math.abs(normBear - bearingTmp) > 10) {
                corners.add(ftCurr);
                //Log.i("FTPT", "Bear: " + res[1] + ", " + res[2] + ", Pos: " + String.valueOf(ftCurr.latitude) + ", " + String.valueOf(ftCurr.longitude));
            }
            bearingTmp = normBear;
        }
    }

    public void validateCenter(LatLngExt footprintCenter) {
        if (footprintCenter != null && (footprintCenter.latitude != 0 && footprintCenter.longitude != 0)) {
            center = footprintCenter;
        } else {
            double latCenter = 0;
            double lngCenter = 0;
            for (LatLngExt cor : corners) {
                latCenter = cor.latitude / 4;
                lngCenter = cor.longitude / 4;
            }
            center = new LatLngExt(latCenter, lngCenter);
        }
    }

    public void generateBearings() {
        double oneLat = corners.get(0).latitude;
        double oneLng = corners.get(0).longitude;
        double twoLat = corners.get(1).latitude;
        double twoLng = corners.get(1).longitude;
        double threeLat = corners.get(2).latitude;
        double threeLng = corners.get(2).longitude;
        double fourLat = corners.get(3).latitude;
        double fourLng = corners.get(3).longitude;

        bearingsArray = new float[8];

        float[] results12 = new float[3];
        Location.distanceBetween(oneLat, oneLng, twoLat, twoLng, results12);
        bearingsArray[0] = results12[1] + 180;
        bearingsArray[1] = results12[2] + 180;
        height = results12[0];

        float[] results23 = new float[3];
        Location.distanceBetween(twoLat, twoLng, threeLat, threeLng, results23);
        bearingsArray[2] = results23[1] + 270;
        bearingsArray[3] = results23[2] + 270;

        float[] results43 = new float[3];
        Location.distanceBetween(fourLat, fourLng, threeLat, threeLng, results43);
        bearingsArray[4] = results43[1] + 180;
        bearingsArray[5] = results43[2] + 180;

        float[] results14 = new float[3];
        Location.distanceBetween(oneLat, oneLng, fourLat, fourLng, results14);
        bearingsArray[6] = results14[1] + 270;
        bearingsArray[7] = results14[2] + 270;

        width = results14[0];
        initBearing = ((results14[1] + results14[2]) / 2) - 90;
    }


}
