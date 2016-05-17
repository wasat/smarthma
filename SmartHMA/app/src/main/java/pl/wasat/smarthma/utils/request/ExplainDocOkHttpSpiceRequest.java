/*
package pl.wasat.smarthma.utils.request;

import android.net.Uri;

import com.octo.android.robospice.request.okhttp.OkHttpSpiceRequest;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;

import pl.wasat.smarthma.helper.Const;

public class ExplainDocOkHttpSpiceRequest extends OkHttpSpiceRequest<String> {

    public ExplainDocOkHttpSpiceRequest() {
        super(String.class);
    }

    @Override
    public String loadDataFromNetwork() throws Exception {
*/
/*        HttpRequest request = getHttpRequestFactory()
                .buildGetRequest(
                        new GenericUrl(Const.FEDEO_SEARCH_BASE_URL));
        HttpResponse response = request.execute();*//*


        Uri.Builder uriBuilder = Uri.parse(Const.FEDEO_SEARCH_BASE_URL).buildUpon();
        URI uri = new URI(uriBuilder.build().toString());

        OkHttpClient okHttpClient = getOkHttpClient();
        //OkHttpClient okHttpClient = getUnsafeOkHttpClient();
        setOkHttpClient(okHttpClient);
        OkUrlFactory okUrlFactory = new OkUrlFactory(okHttpClient);
        HttpURLConnection connection = okUrlFactory.open(uri.toURL());
        //connection.setReadTimeout(60000);
        //connection.setConnectTimeout(60000);

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
*/
