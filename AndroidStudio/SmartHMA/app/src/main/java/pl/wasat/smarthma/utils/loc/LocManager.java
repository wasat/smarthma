package pl.wasat.smarthma.utils.loc;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class LocManager {

    private final Context context;

    public LocManager(Context context) {
        super();
        this.context = context;
    }

    public Location getAvailableLocation() {
        LocationManager locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_LOW);

        Location location;

        String bestProvider = locationManager.getBestProvider(criteria, true);

        if (bestProvider == null || bestProvider.isEmpty()) {
            bestProvider = "DummyProvider";
            location = new Location(bestProvider);
            location.setLatitude(0.0);
            location.setLongitude(0.0);
        } else {
            location = locationManager.getLastKnownLocation(bestProvider);
        }
        return location;
    }

}
