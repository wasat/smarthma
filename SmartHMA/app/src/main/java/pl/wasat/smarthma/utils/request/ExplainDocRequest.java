
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

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

import pl.wasat.smarthma.helper.Const;

/**
 * The type Explain doc request.
 */
public class ExplainDocRequest extends GoogleHttpClientSpiceRequest<String> {

    /**
     * Instantiates a new Explain doc request.
     */
    public ExplainDocRequest() {
        super(String.class);
    }

    @Override
    public String loadDataFromNetwork() throws Exception {
        HttpRequest request = getHttpRequestFactory()
                .buildGetRequest(
                        new GenericUrl(Const.FEDEO_SEARCH_BASE_URL));
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

