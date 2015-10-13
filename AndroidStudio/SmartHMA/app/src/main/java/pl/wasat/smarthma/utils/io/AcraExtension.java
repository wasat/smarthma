package pl.wasat.smarthma.utils.io;

import com.google.android.gms.maps.GoogleMap;

import org.acra.ACRA;

public class AcraExtension {
    public static final String NULL = "Null";
    public static final String MAP_INSTANCE = "-map_instance";
    public static final String MAP_CALLED = "-map_called";

    private static void mapLifeCycle(String place) {
        ACRA.getErrorReporter().putCustomData(
                System.currentTimeMillis() + MAP_CALLED, place);
    }

    private static void mapState(String instance) {
        ACRA.getErrorReporter().putCustomData(
                System.currentTimeMillis() + MAP_INSTANCE, instance);
    }

    public static void mapCustomLog(String place, GoogleMap map) {
        mapLifeCycle(place);
        mapState(checkInstance(map));
    }

    public static void mapCustomLog(String place, com.amazon.geo.mapsv2.AmazonMap map) {
        mapLifeCycle(place);
        mapState(checkInstance(map));
    }

    /**
     * @param map GoogleMap object
     * @return String instance
     */
    private static String checkInstance(GoogleMap map) {
        String instance;
        if (map != null) {
            instance = map.toString();
        } else {
            instance = NULL;
        }
        return instance;
    }

    private static String checkInstance(com.amazon.geo.mapsv2.AmazonMap map) {
        String instance;
        if (map != null) {
            instance = map.toString();
        } else {
            instance = NULL;
        }
        return instance;
    }
}
