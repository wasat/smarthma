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

import pl.wasat.smarthma.model.dataseries.Entry;
import android.net.Uri;
import android.util.Log;

import com.octo.android.robospice.request.okhttp.OkHttpSpiceRequest;

public class DataSeriesFeedRequest extends OkHttpSpiceRequest<List<Entry>> {

	private String urlSearchParam;

	/**
	 * @param clazz
	 */
	// public DataSeriesFeedRequest(Class<List<Entry>> clazz) {
	// super(clazz);
	// // TODO Auto-generated constructor stub
	// }

	/**
	 * 
	 */
	public DataSeriesFeedRequest(String urlSearchParameter) {
		super(null);
		urlSearchParam = urlSearchParameter;
	}

	private static final String FEDEO_DATASERIES_SEARCH_URL = "http://geo.spacebel.be/opensearch/request/?httpAccept=application/atom%2Bxml"
			+ "&startRecord=1" + "&maximumRecords=10" + "&parentIdentifier=";

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
					// in.close();
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
			return rh.getEntryList();

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
