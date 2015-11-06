package pl.wasat.smarthma.utils.rss;

import android.content.Context;
import android.util.Log;

import com.google.api.client.http.HttpResponseException;
import com.octo.android.robospice.persistence.exception.SpiceException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.utils.io.FilesWriter;
import pl.wasat.smarthma.utils.time.DateUtils;

/**
 * Created by Daniel on 2015-09-22 21:49.
 * Part of the project  SmartHMA
 */
public class SpiceExceptionHandler {
    private final SpiceException spiceException;
    private final Context context;
    private String exTextMessage;
    private String exRawMessage;
    private String exRawCause;

    public SpiceExceptionHandler(Context context, SpiceException spiceException) {
        this.context = context;
        this.spiceException = spiceException;
    }

    public void invoke() {
        if (spiceException == null) {
            exTextMessage = "Unknown Exception. Please see exception details.";
            exRawMessage = "Exception with wrong body";
            exRawCause = "Probably a wrong exception request status";
        } else if (spiceException.getCause() instanceof HttpResponseException) {
            exRawMessage = spiceException.getMessage();
            exRawCause = spiceException.getCause().toString();
            FedeoExceptionHandler fedHr = null;
            try {
                String inStr;

                HttpResponseException exception = (HttpResponseException) spiceException
                        .getCause();
                inStr = exception.getContent();

                SAXParserFactory spf = SAXParserFactory.newInstance();
                SAXParser sp = spf.newSAXParser();
                XMLReader xr = sp.getXMLReader();

                fedHr = new FedeoExceptionHandler();

                xr.setContentHandler(fedHr);
                InputSource inputSource = new InputSource(new StringReader(
                        inStr));
                xr.parse(inputSource);

            } catch (IOException e) {
                Log.e("RSS Handler IO", e.toString());
            } catch (SAXException e) {
                Log.e("RSS Handler SAX", e.toString());
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                Log.e("RSS Parser Config", e.toString());
            }

            assert fedHr != null;
            exTextMessage = fedHr.getFedeoException().getExceptionReport()
                    .getException().getExceptionText().getText();

        } else {
            exTextMessage = context.getString(R.string.wide_area_or_timespan);
            exRawMessage = spiceException.getMessage();
            exRawCause = spiceException.getCause().toString();
        }

        writeExceptionToLog();
    }

    private void writeExceptionToLog() {
        GlobalPreferences globalPreferences = new GlobalPreferences(context);
        if (globalPreferences.getIsDebugMode()) {
            FilesWriter filesWriter = new FilesWriter();
            filesWriter.appendLogToFile(buildExceptionLog(), "log_errors.txt");
        }
    }

    private String buildExceptionLog() {
        String NEW_LINE = System.getProperty("line.separator");
        String timestamp = DateUtils.timestampToDateTimeStr(System.currentTimeMillis());
        String url = obtainFormattedUrl().replaceAll(NEW_LINE, "");
        if (exRawCause == null) exRawCause = "";
        return timestamp + " URL: " + url + NEW_LINE +
                timestamp + " EXCEPTION: " + exRawCause.replaceAll(NEW_LINE + NEW_LINE, NEW_LINE) +
                NEW_LINE + NEW_LINE;
    }

    private String obtainFormattedUrl() {
        SharedPrefs sharedPrefs = new SharedPrefs(context);
        return sharedPrefs.getUrlPrefs();
    }


    public String getExTextMessage() {
        return exTextMessage;
    }

    public String getExRawMessage() {
        return exRawMessage;
    }

    public String getExRawCause() {
        return exRawCause;
    }
}
