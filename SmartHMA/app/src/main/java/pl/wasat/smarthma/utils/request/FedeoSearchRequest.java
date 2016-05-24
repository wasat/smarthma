package pl.wasat.smarthma.utils.request;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

import java.io.IOException;
import java.io.InputStream;

import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.utils.rss.XmlSaxParser;

public class FedeoSearchRequest extends GoogleHttpClientSpiceRequest<Feed> {

    private final FedeoRequestParams fedeoRequestParams;
    private final int schemaMode;
    private final Context context;


    public FedeoSearchRequest(Context context, FedeoRequestParams fedeoRequestParams, int schema) {
        super(null);
        this.fedeoRequestParams = fedeoRequestParams;
        this.schemaMode = schema;
        this.context = context;
        this.setRetryPolicy(new CustomRetryPolicy());
    }
/*
    @Override
    public HttpRequestFactory getHttpRequestFactory() {
        HttpRequestFactory httpRequestFactory = super.getHttpRequestFactory();
        try {
            HttpRequest httpRequest = httpRequestFactory.buildGetRequest(new GenericUrl("http://fedeo.esa.int"));
            httpRequest.setReadTimeout(0);
            httpRequest.setConnectTimeout(0);
            httpRequest.setNumberOfRetries(4);
            //setHttpRequestFactory(httpRequestFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpRequestFactory;
    }*/


    @Override
    public Feed loadDataFromNetwork() throws Exception {
        SharedPrefs sharedPrefs = new SharedPrefs(context);
        sharedPrefs.setUrlPrefs("");

        //String url = fedeoRequestParams.getUrlWithoutTemplate();
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
        return feed;
    }

    @Override
    public Class<Feed> getResultType() {
        //super.getResultType();
        return Feed.class;
    }

    private void sendFedeoUrlBroadcast(String url) {
        Intent intent = new Intent();
        intent.setAction(Const.KEY_ACTION_BROADCAST_FEDEO_REQUEST);
        intent.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_URL, url);
        context.sendBroadcast(intent);
    }

    private InputStream obtainInputStreamResponse(String url) throws IOException {
        long startTime = System.currentTimeMillis();
        HttpRequest request = getHttpRequestFactory().buildGetRequest(
                new GenericUrl(url));
        request.setConnectTimeout(0);
        request.setReadTimeout(0);
        request.setNumberOfRetries(4);
/*        Log.i("REQUEST", request.getRequestMethod() + " - " +
                request.getConnectTimeout() + " - " +
                request.getReadTimeout() + " - " +
                request.getNumberOfRetries());*/
        HttpResponse response = request.execute();

        InputStream inStreamFeed;
        inStreamFeed = response.getContent();

        Log.i("REQUEST_TIME", String.valueOf(System.currentTimeMillis() - startTime));
        return inStreamFeed;
    }
}
