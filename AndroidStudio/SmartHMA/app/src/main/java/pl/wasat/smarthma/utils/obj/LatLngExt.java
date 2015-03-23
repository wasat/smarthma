package pl.wasat.smarthma.utils.obj;

import pl.wasat.smarthma.helper.Const;

/**
 * Created by Daniel on 2015-03-03 15:39.
 * Part of the project  SmartHMA
 */
public class LatLngExt {
<<<<<<< HEAD
    public final double latitude;
    public final double longitude;
=======
    public double latitude;
    public double longitude;
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4

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

}

