package pl.wasat.smarthma.utils.obj;

/**
 * Created by Daniel Z. on 04.03.15.
 * Wasat Sp. z o.o.
 */
public class LatLngBoundsExt {
<<<<<<< HEAD
<<<<<<< HEAD
    public final LatLngExt southwest;
    public final LatLngExt northeast;

    public LatLngBoundsExt(com.google.android.gms.maps.model.LatLngBounds areaBounds) {
        //com.google.android.gms.maps.model.LatLngBounds googleBounds = areaBounds;
=======
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    public LatLngExt southwest;
    public LatLngExt northeast;

    private com.amazon.geo.mapsv2.model.LatLngBounds amznBounds;
    private com.google.android.gms.maps.model.LatLngBounds googleBounds;

    public LatLngBoundsExt(com.google.android.gms.maps.model.LatLngBounds areaBounds) {
        googleBounds = areaBounds;
<<<<<<< HEAD
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
        southwest = new LatLngExt(areaBounds.southwest);
        northeast = new LatLngExt(areaBounds.northeast);
    }

    public LatLngBoundsExt(com.amazon.geo.mapsv2.model.LatLngBounds areaBounds) {
<<<<<<< HEAD
<<<<<<< HEAD
        //com.amazon.geo.mapsv2.model.LatLngBounds amznBounds = areaBounds;
=======
        amznBounds = areaBounds;
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
        amznBounds = areaBounds;
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
        southwest = new LatLngExt(areaBounds.southwest);
        northeast = new LatLngExt(areaBounds.northeast);
    }

    public LatLngBoundsExt(LatLngExt latLngSW, LatLngExt latLngNE) {
        southwest = latLngSW;
        northeast = latLngNE;
    }

/*    public com.google.android.gms.maps.model.LatLngBounds getGoogleBounds() {
        return googleBounds;
    }

    public com.amazon.geo.mapsv2.model.LatLngBounds getAmznBounds() {
        return amznBounds;
    }*/


}
