package pl.wasat.smarthma.utils.draw;

import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

/**
 * Created by Daniel on 2015-09-19 10:54.
 * Part of the project  SmartHMA
 */
public class MapDrawings {

    public PolygonOptions drawArea(float[] bbox) {
        ArrayList<LatLng> polygonPtsList = new ArrayList<>();
        polygonPtsList.add(new LatLng(bbox[1], bbox[0]));
        polygonPtsList.add(new LatLng(bbox[1], bbox[2]));
        polygonPtsList.add(new LatLng(bbox[3], bbox[2]));
        polygonPtsList.add(new LatLng(bbox[3], bbox[0]));

        return drawArea(polygonPtsList);
    }

    public PolygonOptions drawArea(ArrayList markedPtList) {
        return drawArea(markedPtList, Color.RED);
    }

    @SuppressWarnings("unchecked")
    public PolygonOptions drawArea(ArrayList markedPtList, int color) {
        PolygonOptions rectOptions = new PolygonOptions();
        if (markedPtList.size() > 0) {
            rectOptions.addAll(markedPtList);
            rectOptions.strokeColor(color);
            rectOptions.strokeWidth(4);
            rectOptions.fillColor(Color.TRANSPARENT);
            rectOptions.zIndex(3);
        }
        return rectOptions;
    }

    public GoogleMap drawPoints(ArrayList<LatLng> markedPtList, GoogleMap mMap) {
        if (markedPtList != null) {
            for (LatLng point : markedPtList) {
                mMap.addCircle(drawPoint(point));
            }
        }
        return mMap;
    }

    public CircleOptions drawPoint(LatLng point) {
        CircleOptions circle = new CircleOptions();
        circle.center(point);
        circle.radius(5);
        circle.strokeColor(Color.YELLOW);
        circle.fillColor(Color.GRAY);
        circle.strokeWidth(7);
        circle.zIndex(5);
        return circle;
    }

    public CircleOptions drawPointAndRadiusArea(ArrayList<LatLng> markedPtList) {
        LatLng center = markedPtList.get(0);
        LatLng secPt = markedPtList.get(1);
        float[] res = new float[3];
        Location.distanceBetween(center.latitude, center.longitude, secPt.latitude, secPt.longitude, res);
        double radiusInMeters = res[0];
        return drawPointAndRadiusArea(center, radiusInMeters);
    }

    @NonNull
    public CircleOptions drawPointAndRadiusArea(LatLng center, double radiusInMeters) {
        int strokeColor = 0xFF0000FF; //red outline
        int shadeColor = 0x110000FF; //opaque red fill

        return new CircleOptions()
                .center(center)
                .radius(radiusInMeters)
                .fillColor(shadeColor)
                .strokeColor(strokeColor)
                .strokeWidth(6)
                .zIndex(4);
    }

    public void removeCircles(ArrayList<Circle> circleList) {
        if (circleList != null) {
            for (Circle circle : circleList) {
                circle.remove();
            }
        }
    }

    public void removePolygon(Polygon areaPolygon) {
        if (areaPolygon != null) areaPolygon.remove();
    }
}
