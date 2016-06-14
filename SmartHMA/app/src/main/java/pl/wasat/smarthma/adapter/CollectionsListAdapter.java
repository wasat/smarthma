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

/**
 *
 */
package pl.wasat.smarthma.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.Collection;

/**
 * The type Collections list adapter.
 *
 * @author Wasat Sp. z o.o
 */
public class CollectionsListAdapter extends ArrayAdapter<Collection> {

    private static LayoutInflater inflater = null;
    private final Activity activity;
    private final ArrayList<Collection> collData;
    private final String groupName;
    private OnSlideElementListener listener;


    /**
     * Instantiates a new Collections list adapter.
     *
     * @param activ  the activ
     * @param data   the data
     * @param grName the gr name
     */
    public CollectionsListAdapter(Activity activ, ArrayList<Collection> data,
                                  String grName) {
        super(activ, R.layout.view_cell_collection, data);
        activity = activ;
        collData = data;
        groupName = grName;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return collData.size();
    }

    public Collection getItem(int position) {
        return collData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.view_cell_collection, parent, false);

        SwipeDetector swipeDetector = new SwipeDetector(convertView, position);
        swipeDetector.setOnClickListener(listener);
        convertView.setOnTouchListener(swipeDetector);

        Collection collection = collData.get(position);
        ViewHolder holder = new ViewHolder();
        holder.title = (TextView) convertView.findViewById(R.id.collection_name);
        holder.artist = (TextView) convertView.findViewById(R.id.collection_desc);
        holder.thumb_image = (ImageView) convertView.findViewById(R.id.collection_image);

        holder.title.setText(collection.getName());
        holder.artist.setText(groupName);

        Picasso.with(activity).load(R.drawable.eo_coll_gr_03).resize(72, 72).centerCrop()
                .into(holder.thumb_image);

        convertView.setTag(holder);
        return convertView;
    }

    /**
     * Sets listener.
     *
     * @param listener the listener
     */
    public void setListener(OnSlideElementListener listener) {
        this.listener = listener;
    }

    private static class ViewHolder {
        /**
         * The Title.
         */
        public TextView title;
        /**
         * The Artist.
         */
        public TextView artist;
        /**
         * The Thumb image.
         */
        public ImageView thumb_image;
        /**
         * The Row.
         */
        public View row;
        /**
         * The Button.
         */
        public ImageView button;
    }
}