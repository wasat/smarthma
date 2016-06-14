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

package pl.wasat.smarthma.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.octo.android.robospice.request.okhttp.simple.OkHttpBitmapRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpSpiceArrayAdapter;

import java.io.File;
import java.util.List;

import pl.wasat.smarthma.customviews.EntryItemView;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.utils.request.EntryBitmapRequest;

/**
 * The type Entry images list adapter.
 */
public class EntryImagesListAdapter extends OkHttpSpiceArrayAdapter<Entry> {

    private static final String THUMB_ENTRY_IMG_TEMP = "THUMB_ENTRY_IMG_TEMP_";
    private static final String TEMP_DEFAULT_IMG_URL = "http://acsspace.acsys.it/joomla/images/stories/slideshow/toscanamer_fr_20100628.jpg";
    private OnSlideElementListener listener;

    /**
     * Instantiates a new Entry images list adapter.
     *
     * @param context            the context
     * @param spiceManagerBitmap the spice manager bitmap
     * @param entryList          the entry list
     */
    public EntryImagesListAdapter(Context context,
                                  OkHttpBitmapSpiceManager spiceManagerBitmap, List<Entry> entryList) {
        super(context, spiceManagerBitmap, entryList);
    }

    @Override
    public SpiceListItemView<Entry> createView(Context context, ViewGroup parent) {
        return new EntryItemView(getContext(), listener);
    }

    @Override
    public OkHttpBitmapRequest createRequest(Entry entry, int imageIndex,
                                             int requestImageWidth, int requestImageHeight) {
        String tmpFileName = THUMB_ENTRY_IMG_TEMP;
        String url = TEMP_DEFAULT_IMG_URL;
        File tempFile = null;
        try {
            tmpFileName = tmpFileName + entry.getTitle().replaceAll("/", "").replaceAll(":", "_");
            tempFile = new File(getContext().getCacheDir(), tmpFileName);
            if (entry.getSimpleMetadata().getThumbnailUrl() == null) {
                url = TEMP_DEFAULT_IMG_URL;
            } else if (!entry.getSimpleMetadata().getThumbnailUrl().isEmpty())
                url = entry.getSimpleMetadata().getThumbnailUrl();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return new EntryBitmapRequest(getContext(), url, requestImageWidth,
                requestImageHeight, tempFile);
    }

    /**
     * Sets listener.
     *
     * @param listener the listener
     */
    public void setListener(OnSlideElementListener listener) {
        this.listener = listener;
    }


}
