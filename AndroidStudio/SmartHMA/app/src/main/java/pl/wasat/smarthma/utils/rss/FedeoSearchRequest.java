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

/*    private Feed parseFeed(String url) throws IOException {
        long startTime = System.currentTimeMillis();
        FeedDataHandler feedDataHandler = null;
        try {
            InputStream inStreamFeed = obtainInputStreamResponse(url);

            startTime = System.currentTimeMillis();
            String inputString = IOUtils.toString(inStreamFeed);
            //String inputString = StringExt.inStreamReaderToString(inStreamFeed);
            //String inputString = StringExt.inStreamToIOUtilsString(inStreamFeed);
            //String inputString = StringExt.inStreamToStringBuffer(inStreamFeed);
            //String inputString = StringExt.inStreamToStringBuilder(inStreamFeed);
            //String inputString = new String(ByteStreams.toByteArray(inStreamFeed));

            Log.i("TO_STRING_TIME", String.valueOf(System.currentTimeMillis() - startTime));

            startTime = System.currentTimeMillis();
            SAXParserFactory spfFeed = SAXParserFactory.newInstance();
            SAXParser spFeed = spfFeed.newSAXParser();
            XMLReader xrFeed = spFeed.getXMLReader();

            InputSource inputSource = new InputSource(new StringReader(inputString));
            feedDataHandler = new FeedDataHandler(inputString);
            xrFeed.setContentHandler(feedDataHandler);
            //Log.i("SAX_HANDLER_TIME", String.valueOf(System.currentTimeMillis() - startTime));

            startTime = System.currentTimeMillis();
            xrFeed.parse(inputSource);

            Log.i("ASYNC", "PARSING FINISHED");
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        }
        Log.i("PARSE_TIME", String.valueOf(System.currentTimeMillis() - startTime));
        //noinspection ConstantConditions
        Feed feed = feedDataHandler.getFeeds();
        return feed;
    }


    private Feed parseOMFeed(String url) throws IOException {
        OMDataHandler rh = null;
        try {
            InputStream inStreamFeed = obtainInputStreamResponse(url);

            SAXParserFactory spfFeed = SAXParserFactory.newInstance();
            SAXParser spFeed = spfFeed.newSAXParser();
            XMLReader xrFeed = spFeed.getXMLReader();

            rh = new OMDataHandler();
            InputSource inputSource = new InputSource(inStreamFeed);
            xrFeed.setContentHandler(rh);
            xrFeed.parse(inputSource);

            Log.i("ASYNC", "PARSING FINISHED");
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        }

        return rh.getFeeds();
    }

    private Feed parseISOFeed(String url) throws IOException {
        ISODataHandler rh = null;
        try {
            InputStream in = obtainInputStreamResponse(url);

            long startTime = System.currentTimeMillis();
            String inputString = IOUtils.toString(in);
            //String inputString = StringExt.inStreamReaderToString(inStreamFeed);
            //String inputString = StringExt.inStreamToIOUtilsString(inStreamFeed);
            //String inputString = StringExt.inStreamToStringBuffer(inStreamFeed);
            //String inputString = StringExt.inStreamToStringBuilder(inStreamFeed);
            //String inputString = new String(ByteStreams.toByteArray(inStreamFeed));

            Log.i("TO_STRING_TIME", String.valueOf(System.currentTimeMillis() - startTime));

            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            rh = new ISODataHandler();
            xr.setContentHandler(rh);
            InputSource inSource = new InputSource(new StringReader(inputString));
            startTime = System.currentTimeMillis();
            xr.parse(inSource);
            Log.i("PARSE_META", String.valueOf(System.currentTimeMillis() - startTime));

            Log.i("ASYNC", "PARSING FINISHED");
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        }
        //noinspection ConstantConditions
        return rh.getFeeds();
    }*/


}
