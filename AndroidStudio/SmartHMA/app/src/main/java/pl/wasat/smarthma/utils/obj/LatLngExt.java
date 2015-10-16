package pl.wasat.smarthma.utils.obj;

import java.io.Serializable;

import pl.wasat.smarthma.helper.Const;

/**
 * Created by Daniel on 2015-03-03 15:39.
 * Part of the project  SmartHMA
 */
public class LatLngExt implements Serializable {
    public transient double latitude;
    public transient double longitude;

    private static final long serialVersionUID = 1L;

    public LatLngExt(Double lat, Double lng) {
        latitude = lat;
        longitude = lng;
    }

    public LatLngExt(com.amazon.geo.mapsv2.model.LatLng latLng) {
        longitude = latLng.longitude;
        latitude = latLng.latitude;
    }

    public LatLngExt(com.google.android.gms.maps.model.LatLng latLng) {
        longitude = latLng.longitude;
        latitude = latLng.latitude;
    }

    public LatLngExt(float south, float west) {
        latitude = south;
        longitude = west;
    }

    public com.amazon.geo.mapsv2.model.LatLng getAmznLatLon() {
        if (Const.IS_KINDLE) {
            return new com.amazon.geo.mapsv2.model.LatLng(latitude, longitude);
        } else {
            return null;
        }
    }

    public com.google.android.gms.maps.model.LatLng getGoogleLatLon() {
        if (!Const.IS_KINDLE) {
            return new com.google.android.gms.maps.model.LatLng(latitude, longitude);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "LatLngExt{" +
                latitude + ", " +
                longitude + "}";
    }
}

