package pl.wasat.smarthma.utils.rss;

import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pl.wasat.smarthma.model.dc.Dc;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.iso.MDMetadata;
import pl.wasat.smarthma.model.om.EarthObservation;

/**
 * Created by Daniel on 2015-10-12.
 * This file is a part of module SmartHMA project.
 */
public class XmlSaxParser {

    public Feed parseFeed(InputStream inputStreamFeed) throws IOException {
        long startTime = System.currentTimeMillis();
        FeedDataHandler feedDataHandler = null;
        try {
            startTime = System.currentTimeMillis();
            String inputString = IOUtils.toString(inputStreamFeed);
            //String inputString = StringExt.inStreamReaderToString(inStreamFeed);
            //String inputString = StringExt.inStreamToIOUtilsString(inStreamFeed);
            //String inputString = StringExt.inStreamToStringBuffer(inStreamFeed);
            //String inputString = StringExt.inStreamToStringBuilder(inStreamFeed);
            //String inputString = new String(ByteStreams.toByteArray(inStreamFeed));

            Log.i("TO_STRING_TIME", String.valueOf(System.currentTimeMillis() - startTime));

            startTime = System.currentTimeMillis();
            XMLReader xrFeed = defineXmlSaxParser();

            InputSource inputSource = new InputSource(new StringReader(inputString));
            feedDataHandler = new FeedDataHandler(inputString);
            xrFeed.setContentHandler(feedDataHandler);
            //Log.i("SAX_HANDLER_TIME", String.valueOf(System.currentTimeMillis() - startTime));

            startTime = System.currentTimeMillis();
            xrFeed.parse(inputSource);

        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        }
        Log.i("PARSE_FEED", String.valueOf(System.currentTimeMillis() - startTime));
        //noinspection ConstantConditions
        return feedDataHandler.getFeeds();
    }


    public Feed parseOMFeed(InputStream inputStreamFeed) {
        OMDataHandler omDataHandler = new OMDataHandler();
        omDataHandler = (OMDataHandler) processFeedHandler(inputStreamFeed, omDataHandler);
        return omDataHandler.getFeeds();
    }


    public Feed parseISOFeed(InputStream inputStreamFeed) {
        //parseISOMetadata("");

        ISODataHandler isoDataHandler = new ISODataHandler();
        isoDataHandler = (ISODataHandler) processFeedHandler(inputStreamFeed, isoDataHandler);
        return isoDataHandler.getFeeds();
    }

    public Entry parseOMMetadata(Entry entry) {
        EarthObservation eo = parseOMMetadata(entry.getRawMetadata());
        entry.setEarthObservation(eo);
        return entry;
    }

    private EarthObservation parseOMMetadata(String metadata) {
        OMMetadataHandler omMetadataHandler = new OMMetadataHandler();
        omMetadataHandler = (OMMetadataHandler) processMetadataHandler(metadata, omMetadataHandler);
        return omMetadataHandler.getOMMetadata();
    }

    public Entry parseISOMetadata(Entry entry) {
        MDMetadata mdMetadata = parseISOMetadata(entry.getRawMetadata());
        entry.setMDMetadata(mdMetadata);
        return entry;
    }

    private MDMetadata parseISOMetadata(String metadata) {
        ISOMetadataHandler isoMetadataHandler = new ISOMetadataHandler();
        isoMetadataHandler = (ISOMetadataHandler) processMetadataHandler(metadata, isoMetadataHandler);
        return isoMetadataHandler.getMdMetadata();
    }

    public Entry parseDCMetadata(Entry entry) {
        Dc dcMetadata = parseDCMetadata(entry.getRawMetadata());
        entry.setDc(dcMetadata);
        return entry;
    }

    private Dc parseDCMetadata(String metadata) {
        DCMetadataHandler dcMetadataHandler = new DCMetadataHandler();
        dcMetadataHandler = (DCMetadataHandler) processMetadataHandler(metadata, dcMetadataHandler);
        return dcMetadataHandler.getDC();
    }

    private DefaultHandler processFeedHandler(InputStream inputStream, DefaultHandler feedHandler) {
        try {
            long startTime = System.currentTimeMillis();
            //String inputString = IOUtils.toString(inputStreamFeed);
            //String inputString = StringExt.inStreamReaderToString(inStreamFeed);
            //String inputString = StringExt.inStreamToIOUtilsString(inStreamFeed);
            //String inputString = StringExt.inStreamToStringBuffer(inStreamFeed);
            //String inputString = StringExt.inStreamToStringBuilder(inStreamFeed);
            //String inputString = new String(ByteStreams.toByteArray(inStreamFeed));

            XMLReader xr = defineXmlSaxParser();
            xr.setContentHandler(feedHandler);

            InputSource inSource = new InputSource(inputStream);
            xr.parse(inSource);

            Log.i("PARSE_FEED", String.valueOf(System.currentTimeMillis() - startTime));
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return feedHandler;
    }

    private DefaultHandler processMetadataHandler(String metadata, DefaultHandler metadataHandler) {
        try {
            long startTime = System.currentTimeMillis();
            XMLReader xrFeed = defineXmlSaxParser();

            InputSource inputSource = new InputSource(new StringReader(metadata));
            xrFeed.setContentHandler(metadataHandler);

            xrFeed.parse(inputSource);
            Log.i("PARSE_META", String.valueOf(System.currentTimeMillis() - startTime));
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return metadataHandler;
    }

    private XMLReader defineXmlSaxParser() throws ParserConfigurationException, SAXException {
        SAXParserFactory spfFeed = SAXParserFactory.newInstance();
        SAXParser spFeed = spfFeed.newSAXParser();
        return spFeed.getXMLReader();
    }


/*    private MDMetadata parseISOMetadata(String metadata) {
        //long startTime = System.currentTimeMillis();
        ISOMetadataHandler metadataHandler = null;
        //metadataHandler = getIsoMetadataHandler(metadata, metadataHandler);
        try {
            //startTime = System.currentTimeMillis();
            XMLReader xrFeed = defineXmlSaxParser();

            InputSource inputSource = new InputSource(new StringReader(metadata));
            metadataHandler = new ISOMetadataHandler();
            xrFeed.setContentHandler(metadataHandler);

            //startTime = System.currentTimeMillis();
            xrFeed.parse(inputSource);

        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Log.i("PARSE_ISO_META", String.valueOf(System.currentTimeMillis() - startTime));
        //noinspection ConstantConditions
        MDMetadata isoMdMetadata = metadataHandler.getMdMetadata();
        return isoMdMetadata;
    }*/





/*    public EarthObservation parseOMMetadata(String metadata) {
        long startTime = System.currentTimeMillis();
        OMMetadataHandler metadataHandler = null;
        try {
            startTime = System.currentTimeMillis();
            XMLReader xrFeed = defineXmlSaxParser();

            InputSource inputSource = new InputSource(new StringReader(metadata));
            metadataHandler = new OMMetadataHandler();
            xrFeed.setContentHandler(metadataHandler);

            startTime = System.currentTimeMillis();
            xrFeed.parse(inputSource);

        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("PARSE_OM_META", String.valueOf(System.currentTimeMillis() - startTime));
        //noinspection ConstantConditions
        EarthObservation eo = metadataHandler.getOMMetadata();
        return eo;
    }*/


/*    public Feed parseISOFeed(InputStream inputStreamFeed) throws IOException {
        ISODataHandler dataHandler = null;
        try {
            long startTime = System.currentTimeMillis();
            //String inputString = IOUtils.toString(inputStreamFeed);
            //String inputString = StringExt.inStreamReaderToString(inStreamFeed);
            //String inputString = StringExt.inStreamToIOUtilsString(inStreamFeed);
            //String inputString = StringExt.inStreamToStringBuffer(inStreamFeed);
            //String inputString = StringExt.inStreamToStringBuilder(inStreamFeed);
            //String inputString = new String(ByteStreams.toByteArray(inStreamFeed));

            Log.i("TO_STRING_TIME", String.valueOf(System.currentTimeMillis() - startTime));

            XMLReader xr = defineXmlSaxParser();

            dataHandler = new ISODataHandler();
            xr.setContentHandler(dataHandler);
            InputSource inSource = new InputSource(inputStreamFeed);
            startTime = System.currentTimeMillis();
            xr.parse(inSource);
            Log.i("PARSE_ISO", String.valueOf(System.currentTimeMillis() - startTime));

        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        }
        //noinspection ConstantConditions
        return dataHandler.getFeeds();
    }*/


    /*    public Feed parseOMFeed(InputStream inputStreamFeed) throws IOException {
        OMDataHandler dataHandler = null;
        try {
            XMLReader xrFeed = defineXmlSaxParser();

            dataHandler = new OMDataHandler();
            InputSource inputSource = new InputSource(inputStreamFeed);
            xrFeed.setContentHandler(dataHandler);
            xrFeed.parse(inputSource);

        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        }

        return dataHandler.getFeeds();
    }*/
}
