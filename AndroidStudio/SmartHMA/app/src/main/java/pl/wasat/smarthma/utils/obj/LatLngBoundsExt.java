package pl.wasat.smarthma.utils.obj;

/**
 * Created by Daniel Z. on 04.03.15.
 * Wasat Sp. z o.o.
 */
public class LatLngBoundsExt {
    public final LatLngExt southwest;
    public final LatLngExt northeast;

    public LatLngBoundsExt(com.google.android.gms.maps.model.LatLngBounds areaBounds) {
        southwest = new LatLngExt(areaBounds.southwest);
        northeast = new LatLngExt(areaBounds.northeast);
    }

    public LatLngBoundsExt(com.amazon.geo.mapsv2.model.LatLngBounds areaBounds) {
        southwest = new LatLngExt(areaBounds.southwest);
        northeast = new LatLngExt(areaBounds.northeast);
    }

    public LatLngBoundsExt(LatLngExt latLngSW, LatLngExt latLngNE) {
        southwest = latLngSW;
        northeast = latLngNE;
    }

}
