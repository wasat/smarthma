package pl.wasat.smarthma.utils.io;

import com.google.android.gms.maps.GoogleMap;

import org.acra.ACRA;

public class AcraExtension {
    private static void mapLifeCycle(String place) {
        ACRA.getErrorReporter().putCustomData(
                System.currentTimeMillis() + "-map_called", place);
    }

    private static void mapState(String instance) {
        ACRA.getErrorReporter().putCustomData(
                System.currentTimeMillis() + "-map_instance", instance);
    }

    public static void mapCustomLog(String place, GoogleMap map) {
        mapLifeCycle(place);
        mapState(checkInstance(map));
    }

    public static void mapCustomLog(String place, com.amazon.geo.mapsv2.AmazonMap map) {
        mapLifeCycle(place);
        mapState(checkInstance(map));
    }

    private static String checkInstance(GoogleMap map) {
        String instance;
        if (map != null) {
            instance = map.toString();
        } else {
            instance = "Null";
        }
        return instance;
    }

    private static String checkInstance(com.amazon.geo.mapsv2.AmazonMap map) {
        String instance;
        if (map != null) {
            instance = map.toString();
        } else {
            instance = "Null";
        }
        return instance;
    }
}
