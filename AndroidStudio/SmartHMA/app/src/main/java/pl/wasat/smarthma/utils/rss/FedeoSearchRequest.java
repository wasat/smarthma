package pl.wasat.smarthma.utils.rss;

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

public class FedeoSearchRequest extends GoogleHttpClientSpiceRequest<Feed> {

    private final FedeoRequestParams fedeoRequestParams;
    private final int schemaMode;
    private final Context context;

    /**
     *
     */
    public FedeoSearchRequest(Context context, FedeoRequestParams fedeoRequestParams, int schema) {
        super(null);
        this.fedeoRequestParams = fedeoRequestParams;
        this.schemaMode = schema;
        this.context = context;
    }


    @Override
    public Feed loadDataFromNetwork() throws Exception {
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
        super.getResultType();
        return Feed.class;
    }

    private InputStream obtainInputStreamResponse(String url) throws IOException {
        long startTime = System.currentTimeMillis();
        HttpRequest request = getHttpRequestFactory().buildGetRequest(
                new GenericUrl(url));
        HttpResponse response = request.execute();

        InputStream inStreamFeed;
        inStreamFeed = response.getContent();

        Log.i("REQUEST_TIME", String.valueOf(System.currentTimeMillis() - startTime));
        return inStreamFeed;
    }

    private void sendFedeoUrlBroadcast(String url) {
        Intent intent = new Intent();
        intent.setAction(Const.KEY_ACTION_BROADCAST_FEDEO_REQUEST);
        intent.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_URL, url);
        context.sendBroadcast(intent);
    }


}
