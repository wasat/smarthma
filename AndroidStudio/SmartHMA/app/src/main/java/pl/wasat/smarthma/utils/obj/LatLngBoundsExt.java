package pl.wasat.smarthma.utils.obj;

import com.google.android.gms.maps.model.LatLngBounds;

import pl.wasat.smarthma.helper.Const;

/**
 * Created by Daniel Z. on 04.03.15.
 * Wasat Sp. z o.o.
 */
public class LatLngBoundsExt {
    public final LatLngExt southwest;
    public final LatLngExt northeast;
    public com.google.android.gms.maps.model.LatLngBounds googleLatLngBounds;
    public com.amazon.geo.mapsv2.model.LatLngBounds amznLatLngBounds;


    public LatLngBoundsExt(com.google.android.gms.maps.model.LatLngBounds areaBounds) {
        southwest = new LatLngExt(areaBounds.southwest);
        northeast = new LatLngExt(areaBounds.northeast);
        googleLatLngBounds = areaBounds;
    }

    public LatLngBoundsExt(com.amazon.geo.mapsv2.model.LatLngBounds areaBounds) {
        southwest = new LatLngExt(areaBounds.southwest);
        northeast = new LatLngExt(areaBounds.northeast);
        amznLatLngBounds = areaBounds;
    }

    public LatLngBoundsExt(LatLngExt latLngSW, LatLngExt latLngNE) {
        southwest = latLngSW;
        northeast = latLngNE;
        if (Const.IS_KINDLE) {
            amznLatLngBounds = new com.amazon.geo.mapsv2.model.LatLngBounds(latLngSW.getAmznLatLon(), latLngNE.getAmznLatLon());
        } else {
            googleLatLngBounds = new LatLngBounds(latLngSW.getGoogleLatLon(), latLngNE.getGoogleLatLon());
        }
    }

/*    public com.google.android.gms.maps.model.LatLngBounds getGoogleLatLngBoundsExt()
    {
        com.google.android.gms.maps.model.LatLngBounds.Builder boundsBuilder = new com.google.android.gms.maps.model.LatLngBounds.Builder();
        boundsBuilder.include(new LatLng(bbox[1], bbox[0]));
        boundsBuilder.include(new LatLng(bbox[3], bbox[2]));
        return null;
    }*/

    @Override
    public String toString() {
        return "LatLngBoundsExt{" +
                "southwest=" + southwest +
                ", northeast=" + northeast +
                '}';
    }
}
