package pl.wasat.smarthma.utils.text;

import java.util.Locale;

/**
 * Created by Daniel on 2015-06-02 00:59.
 * Part of the SmartHMA project
 */
public class StringExt {
    private static final String NEW_LINE = System.getProperty("line.separator");


    public static String formatXY(Double value) {

        return String.format(Locale.US, "%.2f", value);
    }

    public static String formatLatLng(Double value) {
        //if (Double.isNaN(value)) return "null";
        return String.format(Locale.US, "%.6f", value);
    }

    public static String formatUrl(String url) {
        //String formattedUrl = "";
        return url.replaceAll("&", NEW_LINE + "&").replaceAll("\\?", NEW_LINE + "?");
        //return formattedUrl;
    }

}
