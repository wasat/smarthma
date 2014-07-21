/*package pl.wasat.smarthma.utils.rss;

import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.model.feed.Feed;
import android.util.Log;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

public class SearchProductsFeedRequest extends GoogleHttpClientSpiceRequest<Feed> {

	private LatLngBounds geoSearchParam;
	private String parentIdParam;

	*//**
	 * 
	 *//*
	public SearchProductsFeedRequest(Entry collectionEntry, LatLngBounds geoBox) {
		super(null);
		parentIdParam = getParetnId(collectionEntry);
		geoSearchParam = geoBox;

	}

	private static final String FEDEO_SEARCH_URL = "http://geo.spacebel.be/opensearch/request/?httpAccept=application/atom%2Bxml"
			+ "&startRecord=1"
			+ "&maximumRecords=10"
			+ "&startDate=2009-06-14T06:00:00Z&endDate=2009-06-14T10:00:00Z";

	*//**
	 * @param collectionEntry
	 * @return
	 *//*
	private String getParetnId(Entry collectionEntry) {
		String fullIdentifier = collectionEntry.getIdentifier();
		String[] idArr = fullIdentifier.split("urn:ogc:def:");
		String entryIdentifier = idArr[idArr.length - 1];
		return entryIdentifier;
	}

	private String buildUrl() {
		String finalUrl = FEDEO_SEARCH_URL + "&parentIdentifier="
				+ parentIdParam + "&box=" + geoSearchParam.southwest.longitude
				+ "," + geoSearchParam.southwest.latitude + ","
				+ geoSearchParam.northeast.longitude + ","
				+ geoSearchParam.northeast.latitude + "&recordSchema=om";
		Log.i("URL", finalUrl);

		return finalUrl;

	}

	@Override
	public Feed loadDataFromNetwork() throws Exception {

		SmartHmaEoHandler rh = null;
		try {
			HttpRequest request = getHttpRequestFactory().buildGetRequest(
					new GenericUrl(buildUrl()));
			//request.setThrowExceptionOnExecuteError(false);
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