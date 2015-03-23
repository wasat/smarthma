package pl.wasat.smarthma.utils.rss;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.feed.Feed;

public class FedeoSearchRequest extends GoogleHttpClientSpiceRequest<Feed> {

    private final FedeoRequest fedeoRequest;
    private int schemaMode;

    /**
     *
     */
    public FedeoSearchRequest(FedeoRequest fedeoRequest, int schema) {
        super(null);
        this.fedeoRequest = fedeoRequest;
        this.schemaMode = schema;

    }

    public FedeoSearchRequest(String fedeoUrl) {
        super(null);
        this.fedeoRequest = new FedeoRequest();
        fedeoRequest.setUrl(fedeoUrl);
    }

    @Override
    public Feed loadDataFromNetwork() throws Exception {

        Feed feed = null;
        switch (schemaMode) {
            case 1:
                feed = parseISOFeed();
                break;
            case 2:
                feed = parseOMFeed();
                break;
        }
        return feed;

    }

    private Feed parseOMFeed() throws IOException {
        OMDataHandler rh = null;
        try {
            HttpRequest request = getHttpRequestFactory().buildGetRequest(
                    new GenericUrl(fedeoRequest.getUrl()));
            HttpResponse response = request.execute();

            InputStream in;
            in = response.getContent();

            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            rh = new OMDataHandler();
            xr.setContentHandler(rh);
            InputSource inputSource = new InputSource(in);
            xr.parse(inputSource);

            Log.i("ASYNC", "PARSING FINISHED");
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        }
        //noinspection ConstantConditions
        return rh.getFeeds();
    }

    private Feed parseISOFeed() throws IOException {
        ISODataHandler rh = null;
        try {
            HttpRequest request = getHttpRequestFactory().buildGetRequest(
                    new GenericUrl(fedeoRequest.getUrl()));
            HttpResponse response = request.execute();

            InputStream in;
            in = response.getContent();

            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            rh = new ISODataHandler();
            xr.setContentHandler(rh);
            InputSource inSource = new InputSource(in);
            xr.parse(inSource);

            Log.i("ASYNC", "PARSING FINISHED");
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        }
        //noinspection ConstantConditions
        return rh.getFeeds();
    }
}
