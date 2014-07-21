/*package pl.wasat.smarthma.utils.rss;

import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import pl.wasat.smarthma.model.feed.Feed;
import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

public class SearchCollectionsFeedRequest extends
		GoogleHttpClientSpiceRequest<Feed> {

	private String searchUrlFinal;

	*//**
	 * 
	 *//*
	public SearchCollectionsFeedRequest(String searchUrl) {
		super(Feed.class);
		searchUrlFinal = searchUrl;
	}
	
	@Override
	public Feed loadDataFromNetwork() throws Exception {
		SmartHmaEoHandler rh = null;
		try {
			
			Log.i("URL", searchUrlFinal);
			
			HttpRequest request = getHttpRequestFactory().buildGetRequest(
					new GenericUrl(searchUrlFinal));
			// request.setThrowExceptionOnExecuteError(false);
			HttpResponse response = request.execute();

			InputStream in = null;
			try {
				in = response.getContent();
			} finally {
				if (in != null) {
				}
			}

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
		return rh.getFeeds();

	}
}
*/