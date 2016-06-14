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

/**
 * Created by Daniel on 2015-07-30 01:41.
 * Part of the project  SmartHMA
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.tooltips.Tooltip;

/**
 * The type Intro grid adapter.
 */
public class IntroGridAdapter extends BaseAdapter {
    private final Context mContext;
    private ArrayList<String> adapterNamesList = null;
    private ArrayList<String> adapterValuesList = null;
    private ArrayList<String> adapterTooltipsList = null;
    private Tooltip tooltip = null;

    /**
     * Instantiates a new Intro grid adapter.
     *
     * @param c                   the c
     * @param adapterNamesList    the adapter names list
     * @param adapterValuesList   the adapter values list
     * @param adapterTooltipsList the adapter tooltips list
     */
    public IntroGridAdapter(Context c, ArrayList<String> adapterNamesList, ArrayList<String> adapterValuesList, ArrayList<String> adapterTooltipsList) {
        this.mContext = c;
        this.adapterNamesList = adapterNamesList;
        this.adapterValuesList = adapterValuesList;
        this.adapterTooltipsList = adapterTooltipsList;
    }

    @Override
    public int getCount() {
        return adapterValuesList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = inflater.inflate(R.layout.view_grid_cell_intro, null);

            TextView tvNames = (TextView) grid.findViewById(R.id.intro_grid_cell_name);
            tvNames.setText(adapterNamesList.get(position));

            TextView tvValues = (TextView) grid.findViewById(R.id.intro_grid_cell_value);
            tvValues.setText(adapterValuesList.get(position));

            final ImageView tvImages = (ImageView) grid.findViewById(R.id.intro_grid_cell_img_info);
            tvImages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tooltip != null) {
                        tooltip.dismiss();
                    }
                    tooltip = new Tooltip(grid.getContext(), Tooltip.TYPE_ABOVE, adapterTooltipsList.get(position));
                    if (tooltip.isDisabled()) {
                        tooltip.show(tvImages);
                    }
                }
            });
        } else {
            grid = convertView;
        }
        return grid;
    }
}
