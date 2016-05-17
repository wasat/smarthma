/*
package pl.wasat.smarthma.utils.request;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.octo.android.robospice.request.okhttp.OkHttpSpiceRequest;
import com.octo.android.robospice.retry.DefaultRetryPolicy;
import com.octo.android.robospice.retry.RetryPolicy;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.utils.rss.XmlSaxParser;

public class FedeoSearchOkHttpSpiceRequest extends OkHttpSpiceRequest<Feed> {

    private FedeoRequestParams fedeoRequestParams;
    private int schemaMode;
    private Context context;

    */
/**
     *
 *//*

    public FedeoSearchOkHttpSpiceRequest(Context context, FedeoRequestParams fedeoRequestParams, int schema) {
        super(Feed.class);
        this.fedeoRequestParams = fedeoRequestParams;
        this.schemaMode = schema;
        this.context = context;
        this.setRetryPolicy(new CustomRetryPolicy());
        //setPolicy();
    }

    @Override
    public Feed loadDataFromNetwork() throws Exception {
        SharedPrefs sharedPrefs = new SharedPrefs(context);
        sharedPrefs.setUrlPrefs("");

        String url = fedeoRequestParams.getUrl();
        sendFedeoUrlBroadcast(url);

        InputStream in = obtainInputStreamResponse(url);

        XmlSaxParser xmlSaxParser = new XmlSaxParser();
        Feed feed = null;
        switch (schemaMode) {
            case 1:
                feed = xmlSaxParser.parseISOFeed(in);
                break;
            case 2:
                feed = xmlSaxParser.parseFeed(in);
                break;
        }
        in.close();
        return feed;
    }

    @Override
    public Class<Feed> getResultType() {
        super.getResultType();
        return Feed.class;
    }

    private void sendFedeoUrlBroadcast(String url) {
        Intent intent = new Intent();
        intent.setAction(Const.KEY_ACTION_BROADCAST_FEDEO_REQUEST);
        intent.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_URL, url);
        context.sendBroadcast(intent);
    }

    private InputStream obtainInputStreamResponse(String url) throws Exception {
        long startTime = System.currentTimeMillis();
        Uri.Builder uriBuilder = Uri.parse(url).buildUpon();
        URI uri = new URI(uriBuilder.build().toString());

        OkHttpClient okHttpClient = getOkHttpClient();
        OkUrlFactory okUrlFactory = new OkUrlFactory(okHttpClient);
        HttpURLConnection connection = okUrlFactory.open(uri.toURL());

        InputStream inStreamFeed;
        inStreamFeed = connection.getInputStream();
        Log.i("REQUEST_TIME", String.valueOf(System.currentTimeMillis() - startTime));
        return inStreamFeed;
    }

    public String createCacheKey() {
        return "smarthma." + fedeoRequestParams.hashCode();
    }

    private OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            //OkHttpClient okHttpClient = new OkHttpClient();
            OkHttpClient okHttpClient = getOkHttpClient();
            //okHttpClient.setSocketFactory(sslSocketFactory);
            okHttpClient.setSslSocketFactory(sslSocketFactory);
            okHttpClient.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setPolicy() {
        RetryPolicy retryPolicy = new DefaultRetryPolicy(2, 35L * 1000, 1);
        this.setRetryPolicy(retryPolicy);
    }

    public class CustomRetryPolicy extends DefaultRetryPolicy {
        @Override
        public int getRetryCount() {
            return 2;
        }

        @Override
        public long getDelayBeforeRetry() {
            return 35L * 1000;
        }
    }
}
*/
