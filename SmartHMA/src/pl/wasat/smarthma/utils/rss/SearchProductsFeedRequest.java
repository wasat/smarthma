package pl.wasat.smarthma.utils.rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import pl.wasat.smarthma.model.feed.Entry;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLngBounds;
import com.octo.android.robospice.request.okhttp.OkHttpSpiceRequest;

public class SearchProductsFeedRequest extends OkHttpSpiceRequest<List<Entry>> {

	private LatLngBounds geoSearchParam;
	private String parentIdParam;

	/**
	 * 
	 */
	public SearchProductsFeedRequest(Entry collectionEntry, LatLngBounds geoBox) {
		super(null);
		parentIdParam = getParetnId(collectionEntry);
		geoSearchParam = geoBox;

	}

	private static final String FEDEO_SEARCH_URL = "http://geo.spacebel.be/opensearch/request/?httpAccept=application/atom%2Bxml"
			+ "&startRecord=1"
			+ "&maximumRecords=10"
			+ "&startDate=2009-06-14T06:00:00Z&endDate=2009-06-14T10:00:00Z";

	/**
	 * @param collectionEntry
	 * @return
	 */
	private String getParetnId(Entry collectionEntry) {
		String fullIdentifier = collectionEntry.getIdentifier();
		String[] idArr = fullIdentifier.split("urn:ogc:def:");
		String entryIdentifier = idArr[idArr.length - 1];
		return entryIdentifier;
	}

	private String buildUrl() {
		String finalUrl = FEDEO_SEARCH_URL + "&parentIdentifier="
				+ parentIdParam + "&box=" + geoSearchParam.southwest.latitude
				+ "," + geoSearchParam.southwest.longitude + ","
				+ geoSearchParam.northeast.latitude + ","
				+ geoSearchParam.northeast.longitude + "&recordSchema=om";
		Log.i("URL", finalUrl);

		return finalUrl;

	}

	@Override
	public List<Entry> loadDataFromNetwork() throws Exception {

		RssEoHandler rh = null;
		try {
			// With Uri.Builder class we can build our url is a safe manner
			Uri.Builder uriBuilder = Uri.parse(buildUrl()).buildUpon();
			URI uri = new URI(uriBuilder.build().toString());

			HttpURLConnection connection = getOkHttpClient().open(uri.toURL());
			InputStream in = null;
			try {
				// Read the response.
				in = connection.getInputStream();
			} finally {
				if (in != null) {
				}
			}

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			rh = new RssEoHandler();

			xr.setContentHandler(rh);
			InputSource insour = new InputSource(in);
			xr.parse(insour);

			Log.i("ASYNC", "PARSING FINISHED");

		} catch (IOException e) {
			Log.e("RSS Handler IO", e.toString());
		} catch (SAXException e) {
			Log.e("RSS Handler SAX", e.toString());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			Log.e("RSS Handler Parser Config", e.toString());
		}
		return rh.getFeeds().getEntries();

	}
}
