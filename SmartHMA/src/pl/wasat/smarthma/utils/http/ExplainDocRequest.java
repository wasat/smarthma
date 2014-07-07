package pl.wasat.smarthma.utils.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;

import org.apache.commons.io.IOUtils;

import pl.wasat.smarthma.helper.Const;
import android.net.Uri;

import com.octo.android.robospice.request.okhttp.OkHttpSpiceRequest;

public class ExplainDocRequest extends OkHttpSpiceRequest<String> {

	public ExplainDocRequest() {
		super(String.class);
	}

	@Override
	public String loadDataFromNetwork() throws Exception {

		// With Uri.Builder class we can build our url is a safe manner
		Uri.Builder uriBuilder = Uri.parse(Const.URL_EXPLAIN_DOC).buildUpon();
		URI uri = new URI(uriBuilder.build().toString());

		HttpURLConnection connection = getOkHttpClient().open(uri.toURL());
		InputStream in = null;

		try {
			// Read the response.
			in = connection.getInputStream();
			return IOUtils.toString(in, "UTF-8");
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
