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

import pl.wasat.smarthma.model.dataseries.Entry;
import android.net.Uri;
import android.util.Log;

import com.octo.android.robospice.request.okhttp.OkHttpSpiceRequest;

public class SearchFeedRequest extends OkHttpSpiceRequest<List<Entry>> {

	private String urlSearchParam;

	/**
	 * 
	 */
	public SearchFeedRequest(String urlSearchParameter) {
		super(null);
		urlSearchParam = urlSearchParameter;
	}

	private static final String FEDEO_SEARCH_URL = "http://geo.spacebel.be/opensearch/request/?httpAccept=application/atom%2Bxml&type=collection&startRecord=1&maximumRecords=10&query=";

	@Override
	public List<Entry> loadDataFromNetwork() throws Exception {

		try {
			// With Uri.Builder class we can build our url is a safe manner
			Uri.Builder uriBuilder = Uri.parse(
					FEDEO_SEARCH_URL + urlSearchParam).buildUpon();
			URI uri = new URI(uriBuilder.build().toString());

			HttpURLConnection connection = getOkHttpClient().open(uri.toURL());
			InputStream in = null;
			try {
				// Read the response.
				in = connection.getInputStream();
			} finally {
				if (in != null) {
					// in.close();
				}
			}

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			RssEoHandler rh = new RssEoHandler();

			xr.setContentHandler(rh);
			InputSource insour = new InputSource(in);
			xr.parse(insour);

			Log.i("ASYNC", "PARSING FINISHED");
			return rh.getEntryList();

		} catch (IOException e) {
			Log.e("RSS Handler IO", e.getMessage() + " >> " + e.toString());
		} catch (SAXException e) {
			Log.e("RSS Handler SAX", e.toString());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			Log.e("RSS Handler Parser Config", e.toString());
		}
		return null;

	}
}
