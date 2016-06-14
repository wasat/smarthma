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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.octo.android.robospice.request.okhttp.simple.OkHttpBitmapRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpSpiceArrayAdapter;

import java.io.File;

import pl.wasat.smarthma.customviews.CollectionsGroupView;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.CollectionsGroup;

/**
 * The type Collections group list adapter.
 */
public class CollectionsGroupListAdapter extends
        OkHttpSpiceArrayAdapter<CollectionsGroup> {

    private OnSlideElementListener listener;

    /**
     * Instantiates a new Collections group list adapter.
     *
     * @param context            the context
     * @param spiceManagerBitmap the spice manager bitmap
     * @param users              the users
     * @param listView           the list view
     */
    public CollectionsGroupListAdapter(Context context,
                                       OkHttpBitmapSpiceManager spiceManagerBitmap,
                                       CollectionsGroup.List users, ListView listView) {
        super(context, spiceManagerBitmap, users.getCollectionsGroupList());
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        SwipeDetector swipeDetector = new SwipeDetector(v, position);
        swipeDetector.setOnClickListener(listener);
        v.setOnTouchListener(swipeDetector);
        return v;
    }

    @Override
    public SpiceListItemView<CollectionsGroup> createView(Context context,
                                                          ViewGroup parent) {
        return new CollectionsGroupView(getContext());
    }

    @Override
    public OkHttpBitmapRequest createRequest(CollectionsGroup group,
                                             int imageIndex, int requestImageWidth, int requestImageHeight) {
        File tempFile = new File(getContext().getCacheDir(),
                "THUMB_IMAGE_TEMP_" + group.getId());
        String url = Const.IMG_URL + "eo_coll_gr_02_72x72.png";
        return new OkHttpBitmapRequest(url, requestImageWidth, requestImageHeight, tempFile);
    }

    /**
     * Sets on click listener.
     *
     * @param listener the listener
     */
    public void setOnClickListener(OnSlideElementListener listener) {
        this.listener = listener;
    }

    private int mod(int x, int y) {
        int result = x % y;
        return result < 0 ? result + y : result;
    }
}
