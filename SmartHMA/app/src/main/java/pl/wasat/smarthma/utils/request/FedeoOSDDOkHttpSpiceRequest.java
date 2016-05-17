/*
package pl.wasat.smarthma.utils.request;

import android.net.Uri;
import android.util.Log;

import com.octo.android.robospice.request.okhttp.OkHttpSpiceRequest;
import com.squareup.okhttp.OkUrlFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.utils.rss.OSDDHandler;

public class FedeoOSDDOkHttpSpiceRequest extends OkHttpSpiceRequest<OpenSearchDescription> {

    private final String osddUrl;

    public FedeoOSDDOkHttpSpiceRequest(String genericOsddUrl) {
        super(null);
        osddUrl = genericOsddUrl;
    }

    @Override
    public OpenSearchDescription loadDataFromNetwork() throws Exception {

        OSDDHandler rh = null;
        try {
            Log.i("OSDD_URL", osddUrl);

            Uri.Builder uriBuilder = Uri.parse(osddUrl).buildUpon();
            URI uri = new URI(uriBuilder.build().toString());
            HttpURLConnection connection = new OkUrlFactory(getOkHttpClient()).open(uri.toURL());

            InputStream inStreamFeed = connection.getInputStream();

            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            rh = new OSDDHandler();
            xr.setContentHandler(rh);
            InputSource inSour = new InputSource(inStreamFeed);
            xr.parse(inSour);
            inStreamFeed.close();
            Log.i("ASYNC", "PARSING FINISHED");
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        }
        return rh != null ? rh.getOSDD() : null;
    }

}





*/
