package pl.wasat.smarthma.utils.text;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
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

    public static String cleanDirName(String dirName) {
        return dirName
                .replaceFirst("urn:ogc:def:", "")
                .replaceAll(":", "_")
                .replaceAll("\\.", "_")
                .replaceAll(" ", "_");
    }

    public static String inStreamReaderToString(InputStream stream) throws IOException {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, "UTF8");
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }

    public static String inStreamToStringBuffer(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int i; (i = in.read(b)) != -1; ) {
            out.append(new String(b, 0, i));
        }
        return out.toString();
    }

    public static String inStreamToIOUtilsString(InputStream inputStream) {
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(inputStream, writer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    public static String inStreamToStringBuilder(InputStream inputStream) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        return inputStringBuilder.toString();
    }

}
