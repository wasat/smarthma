package pl.wasat.smarthma.utils.rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.model.feed.Feed;
import android.net.Uri;
import android.util.Log;

import com.octo.android.robospice.request.okhttp.OkHttpSpiceRequest;

public class DataSeriesFeedRequest extends OkHttpSpiceRequest<List<Entry>> {

	private String urlSearchParam;

	/**
	 * 
	 */
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
	public List<Entry> loadDataFromNetwork() throws Exception {

		RssEoHandler rh = null;
		try {
			// With Uri.Builder class we can build our url is a safe manner
			Uri.Builder uriBuilder = Uri.parse(
					FEDEO_DATASERIES_SEARCH_URL + urlSearchParam).buildUpon();
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

			Feed feedTmp = rh.getFeeds();

			return feedTmp.getEntries();

		} catch (IOException e) {
			Log.e("RSS Handler IO", e.getMessage() + " >> " + e.toString());
			return new ArrayList<Entry>();
		} catch (SAXException e) {
			Log.e("RSS Handler SAX", e.toString());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			Log.e("RSS Handler Parser Config", e.toString());
		}
		return null;

	}
}
