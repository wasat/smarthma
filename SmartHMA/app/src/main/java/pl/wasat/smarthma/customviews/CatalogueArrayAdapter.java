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

package pl.wasat.smarthma.customviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.osdd.Option;

/**
 * Created by Daniel on 2016-02-02.
 * This file is a part of module SmartHMA project.
 */
public class CatalogueArrayAdapter extends ArrayAdapter {
    private final List<Option> options;
    private final Context context;


    /**
     * Instantiates a new Catalogue array adapter.
     *
     * @param context  the context
     * @param resource the resource
     * @param objects  the objects
     */
    public CatalogueArrayAdapter(Context context, int resource, List<Option> objects) {
        super(context, resource, objects);
        this.options = objects;
        this.context = context;
    }

    /**
     * The type View holder.
     */
    class ViewHolder {
        /**
         * The Icon.
         */
        ImageView icon;
        /**
         * The Title.
         */
        TextView title;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getApplicationContext()
                .getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(
                    R.layout.view_cell_cataloque, null);

            holder = new ViewHolder();
            holder.icon = (ImageView) convertView
                    .findViewById(R.id.view_cell_catalogue_icon);
            holder.title = (TextView) convertView
                    .findViewById(R.id.view_cell_catalogue_title);
            convertView.setTag(holder);
        } else {
            // view already defined, retrieve view holder
            holder = (ViewHolder) convertView.getTag();
        }

        Drawable drawable = context.getResources().getDrawable(R.drawable.eo_coll_gr_03); //this is an image from the drawables folder

        String title = options.get(position).getLabel();
        holder.title.setText(title);
        holder.icon.setImageDrawable(drawable);

        return convertView;
    }
}
