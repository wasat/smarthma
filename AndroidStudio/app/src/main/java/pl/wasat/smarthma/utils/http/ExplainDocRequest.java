package pl.wasat.smarthma.utils.http;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import pl.wasat.smarthma.helper.Const;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

public class ExplainDocRequest extends GoogleHttpClientSpiceRequest<String> {

	public ExplainDocRequest() {
		super(String.class);
	}

	@Override
	public String loadDataFromNetwork() throws Exception {
		HttpRequest request = getHttpRequestFactory()
				.buildGetRequest(
						new GenericUrl(Const.HTTP_FEDEO_BASE_URL));
		HttpResponse response = request.execute();
		
		InputStream in = null;

		try {
			// Read the response.
			in = response.getContent();
			return IOUtils.toString(in, "UTF-8");
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
