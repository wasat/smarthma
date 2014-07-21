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

public class DataSeriesFeedRequest extends GoogleHttpClientSpiceRequest<Feed> {

	private String urlSearchParam;

	public DataSeriesFeedRequest(String urlSearchParameter) {
		super(null);
		urlSearchParam = urlSearchParameter;
	}

	private static final String FEDEO_DATASERIES_SEARCH_URL = "http://geo.spacebel.be/opensearch/request/?httpAccept=application/atom%2Bxml"
			+ "&startRecord=1"
			+ "&maximumRecords=10"
			+ "&recordSchema=om"
			+ "&startDate=2014-04-23T00:00:00Z&endDate=2014-05-22T00:00:00Z"
			+ "&parentIdentifier=";

	@Override
	public Feed loadDataFromNetwork() throws Exception {

		SmartHmaEoHandler rh = null;
		try {
			HttpRequest request = getHttpRequestFactory()
					.buildGetRequest(
							new GenericUrl(FEDEO_DATASERIES_SEARCH_URL
									+ urlSearchParam));
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