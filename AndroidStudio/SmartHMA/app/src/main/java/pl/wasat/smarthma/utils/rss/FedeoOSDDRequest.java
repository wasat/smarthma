package pl.wasat.smarthma.utils.rss;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pl.wasat.smarthma.model.osdd.OpenSearchDescription;

public class FedeoOSDDRequest extends GoogleHttpClientSpiceRequest<OpenSearchDescription> {

    private final String osddUrl;

    public FedeoOSDDRequest(String descUrl) {
        super(null);
        this.osddUrl = descUrl;
    }

    @Override
    public OpenSearchDescription loadDataFromNetwork() throws Exception {

        OSDDHandler rh = null;
        try {

            Log.i("OSDD_URL", osddUrl);
            //HttpRequest request = getHttpRequestFactory().buildGetRequest(
            //        new GenericUrl(fedeoRequest.getDescUrl()));

            HttpRequest request = getHttpRequestFactory().buildGetRequest(
                    new GenericUrl(osddUrl));

            HttpResponse response = request.execute();

            InputStream in;
            in = response.getContent();

            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            rh = new OSDDHandler();
            xr.setContentHandler(rh);
            InputSource inSour = new InputSource(in);
            xr.parse(inSour);

            Log.i("ASYNC", "PARSING FINISHED");
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        }
        //noinspection ConstantConditions
        return rh.getOSDD();
    }

}





