package pl.wasat.smarthma.utils.rss;

import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.feed.Feed;
import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

public class FedeoSearchRequest extends GoogleHttpClientSpiceRequest<Feed> {

	private final FedeoRequest fedeoRequest;

	/**
	 * 
	 */
	public FedeoSearchRequest(FedeoRequest fedeoRequest) {
		super(null);
		this.fedeoRequest = fedeoRequest;

	}
	
	public FedeoSearchRequest(String fedeoUrl) {
		super(null);
		this.fedeoRequest = new FedeoRequest();
		fedeoRequest.setUrl(fedeoUrl);
	}

	@Override
	public Feed loadDataFromNetwork() throws Exception {

		SmartHmaEoHandler rh = null;
		try {
			HttpRequest request = getHttpRequestFactory().buildGetRequest(
					new GenericUrl(fedeoRequest.getUrl()));
			HttpResponse response = request.execute();

			InputStream in = null;
            in = response.getContent();

            SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			rh = new SmartHmaEoHandler();

			xr.setContentHandler(rh);
			InputSource insour = new InputSource(in);
			xr.parse(insour);

			Log.i("ASYNC", "PARSING FINISHED");
		}

		catch (SAXException e) {
			Log.e("RSS Handler SAX", e.toString());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			Log.e("RSS Handler Parser Config", e.toString());
		}
        //noinspection ConstantConditions
        return rh.getFeeds();

	}
}
