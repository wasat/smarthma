/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wasat.smarthma.utils.request;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.utils.rss.OSDDHandler;

/**
 * The type Fedeo osdd request.
 */
public class FedeoOSDDRequest extends GoogleHttpClientSpiceRequest<OpenSearchDescription> {

    private final GenericUrl osddUrl;

    /**
     * Instantiates a new Fedeo osdd request.
     *
     * @param genericOsddUrl the generic osdd url
     */
    public FedeoOSDDRequest(GenericUrl genericOsddUrl) {
        super(null);
        osddUrl = genericOsddUrl;
    }

    /**
     * Instantiates a new Fedeo osdd request.
     *
     * @param stringOsddUrl the string osdd url
     */
    public FedeoOSDDRequest(String stringOsddUrl) {
        super(null);
        osddUrl = new GenericUrl(stringOsddUrl);
    }

    @Override
    public OpenSearchDescription loadDataFromNetwork() throws Exception {

        OSDDHandler rh = null;
        try {
            Log.i("OSDD_URL", osddUrl.toString());

            HttpRequest request = getHttpRequestFactory().buildGetRequest(osddUrl);
            HttpResponse response = request.execute();

            InputStream in;
            in = response.getContent();

            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            rh = new OSDDHandler();
            xr.setContentHandler(rh);
            InputSource inSour = new InputSource(in);
            xr.parse(inSour);

            Log.i("ASYNC", "PARSING FINISHED");
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Parser Config", e.toString());
        }
        return rh != null ? rh.getOSDD() : null;
    }

    private GenericUrl buildOsddUrl(String parentID) {
        return new GenericUrl(Const.OSDD_BASE_URL + "parentIdentifier=" + parentID);
    }

    @Override
    public Class<OpenSearchDescription> getResultType() {
        //return super.getResultType();
        return OpenSearchDescription.class;
    }
}

