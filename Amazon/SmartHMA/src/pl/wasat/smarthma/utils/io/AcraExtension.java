package pl.wasat.smarthma.utils.io;

import org.acra.ACRA;

import com.amazon.geo.mapsv2.AmazonMap;

public class AcraExtension {
	public static void mapLifeCycle(String place) {
		ACRA.getErrorReporter().putCustomData(
				System.currentTimeMillis() + "-map_called", place);
	}

	public static void mapState(String instance) {
		ACRA.getErrorReporter().putCustomData(
				System.currentTimeMillis() + "-map_instance", instance);
	}

	public static void mapCustomLog(String place, AmazonMap map) {
		mapLifeCycle(place);
		mapState(checkInstance(map));
	}

	private static String checkInstance(AmazonMap map) {
		String instance;
		if (map != null) {
			instance = map.toString();
		} else {
			instance = "Null";
		}
		return instance;

	}
}
