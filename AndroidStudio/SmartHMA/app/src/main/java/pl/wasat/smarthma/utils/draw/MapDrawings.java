package pl.wasat.smarthma.utils.draw;

import android.graphics.Color;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

/**
 * Created by Daniel on 2015-09-19 10:54.
 * Part of the project  SmartHMA
 */
public class MapDrawings {

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

    public PolygonOptions drawArea(float[] bbox) {
        ArrayList<LatLng> polygonPtsList = new ArrayList<>();
        polygonPtsList.add(new LatLng(bbox[1], bbox[0]));
        polygonPtsList.add(new LatLng(bbox[1], bbox[2]));
        polygonPtsList.add(new LatLng(bbox[3], bbox[2]));
        polygonPtsList.add(new LatLng(bbox[3], bbox[0]));

        return drawArea(polygonPtsList);
    }

    public CircleOptions drawPoints(ArrayList<LatLng> markedPtList) {
        CircleOptions circle = new CircleOptions();
        int size = 0;
        if (markedPtList != null) {
            size = markedPtList.size();
        }
        for (int i = 0; i < size; i++) {
            circle.center(markedPtList.get(i));
            circle.radius(0.3);
            circle.strokeColor(Color.YELLOW);
            circle.fillColor(Color.GRAY);
            circle.strokeWidth(7);

        }
        return circle;
    }

}
