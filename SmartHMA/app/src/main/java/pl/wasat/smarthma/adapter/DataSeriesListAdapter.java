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

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.utils.time.DateUtils;

/**
 * The type Data series list adapter.
 */
public class DataSeriesListAdapter extends ArrayAdapter<EntryISO> {

    private OnSlideElementListener listener;

    /**
     * Instantiates a new Data series list adapter.
     *
     * @param activity       the activity
     * @param dataSeriesList the data series list
     */
    public DataSeriesListAdapter(Activity activity, List<EntryISO> dataSeriesList) {
        super(activity, 0, dataSeriesList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Activity activity = (Activity) getContext();
        LayoutInflater inflater = activity.getLayoutInflater();
        EntryISO dataSeriesItem = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_cell_product, parent, false);

            SwipeDetector swipeDetector = new SwipeDetector(convertView, position);
            swipeDetector.setOnClickListener(listener);
            convertView.setOnTouchListener(swipeDetector);

            holder = new ViewHolder();
            holder.textView = (TextView) convertView
                    .findViewById(R.id.dataseries_list_name);
            holder.dateView = (TextView) convertView
                    .findViewById(R.id.dataseries_listing_smallprint);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(dataSeriesItem.getTitle());

        String pubDate = String.format(activity.getString(R.string.pub_date_and_updated),
                DateUtils.getISOPubDate(dataSeriesItem), dataSeriesItem.getUpdated());
        //String pubDate = "Date: " + DateUtils.getISOPubDate(dataSeriesItem) + ", updated: " + dataSeriesItem.getUpdated();
        holder.dateView.setText(pubDate);

        if (dataSeriesItem.isNotRead()) {
            holder.textView.setTypeface(Typeface.DEFAULT_BOLD);
        }

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
         * The Text view.
         */
        public TextView textView;
        /**
         * The Date view.
         */
        public TextView dateView;
    }
}