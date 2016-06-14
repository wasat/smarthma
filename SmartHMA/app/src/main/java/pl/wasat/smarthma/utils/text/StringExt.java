/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    /**
     * Format dist string.
     *
     * @param value the value
     * @return the string
     */
    public static String formatDist(Float value) {
        return String.format(Locale.US, "%.3f", value);
    }

    /**
     * Format xy string.
     *
     * @param value the value
     * @return the string
     */
    public static String formatXY(Double value) {
        return String.format(Locale.US, "%.2f", value);
    }

    /**
     * Format lat lng string.
     *
     * @param value the value
     * @return the string
     */
    public static String formatLatLng(Double value) {
        return String.format(Locale.US, "%.6f", value);
    }

    /**
     * Format lat lng string.
     *
     * @param value the value
     * @return the string
     */
    public static String formatLatLng(Float value) {
        return String.format(Locale.US, "%.6f", value);
    }

    /**
     * Format url string.
     *
     * @param url the url
     * @return the string
     */
    public static String formatUrl(String url) {
        return url.replaceAll("&", NEW_LINE + "&").replaceAll("\\?", NEW_LINE + "?");
    }

    /**
     * Clean dir name string.
     *
     * @param dirName the dir name
     * @return the string
     */
    public static String cleanDirName(String dirName) {
        return dirName
                .replaceFirst("urn:ogc:def:", "")
                .replaceAll(":", "_")
                .replaceAll("\\.", "_")
                .replaceAll(" ", "_");
    }

    /**
     * In stream reader to string string.
     *
     * @param stream the stream
     * @return the string
     * @throws IOException the io exception
     */
    public static String inStreamReaderToString(InputStream stream) throws IOException {
        int n;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, "UTF8");
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }

    /**
     * In stream to string buffer string.
     *
     * @param in the in
     * @return the string
     * @throws IOException the io exception
     */
    public static String inStreamToStringBuffer(InputStream in) throws IOException {
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        for (int i; (i = in.read(b)) != -1; ) {
            out.append(new String(b, 0, i));
        }
        return out.toString();
    }

    /**
     * In stream to io utils string string.
     *
     * @param inputStream the input stream
     * @return the string
     */
    public static String inStreamToIOUtilsString(InputStream inputStream) {
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(inputStream, writer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    /**
     * In stream to string builder string.
     *
     * @param inputStream the input stream
     * @return the string
     * @throws IOException the io exception
     */
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
