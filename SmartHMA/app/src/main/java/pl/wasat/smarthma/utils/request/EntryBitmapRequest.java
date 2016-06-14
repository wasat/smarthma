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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.octo.android.robospice.request.okhttp.simple.OkHttpBitmapRequest;

import java.io.File;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.utils.http.SSLCertificateHandler;

/**
 * Created by Daniel on 2015-10-22 01:31.
 * Part of the project  SmartHMA_home
 */
public class EntryBitmapRequest extends OkHttpBitmapRequest {
    private final Context context;

    /**
     * Instantiates a new Entry bitmap request.
     *
     * @param context   the context
     * @param url       the url
     * @param width     the width
     * @param height    the height
     * @param cacheFile the cache file
     */
    public EntryBitmapRequest(Context context, String url, int width, int height, File cacheFile) {
        super(url, width, height, cacheFile);
        this.context = context;
    }

    @Override
    public Bitmap loadDataFromNetwork() {

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_square);
        try {
            SSLCertificateHandler.nuke();
            bitmap = super.loadDataFromNetwork();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
