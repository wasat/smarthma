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
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.octo.android.robospice.spicelist.SpiceListItemView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.CollectionsGroup;

/**
 * The type Collections group view.
 */
public class CollectionsGroupView extends RelativeLayout implements SpiceListItemView<CollectionsGroup> {

    private TextView groupNameTextView;
    private TextView groupContentTextView;
    private ImageView thumbImageView;
    private CollectionsGroup group;

    /**
     * Instantiates a new Collections group view.
     *
     * @param context the context
     */
    public CollectionsGroupView(Context context) {
        super(context);
        inflateView(context);
    }

    private void inflateView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_cell_entry_item, this);
        this.groupNameTextView = (TextView) this.findViewById(R.id.entry_item_title_textview);
        this.groupContentTextView = (TextView) this.findViewById(R.id.entry_item_dates_textview);
        this.thumbImageView = (ImageView) this.findViewById(R.id.entry_item_thumb_imageview);
    }

    @Override
    public CollectionsGroup getData() {
        return group;
    }

    @Override
    public ImageView getImageView(int imageIndex) {
        return thumbImageView;
    }

    @Override
    public int getImageViewCount() {
        return 1;
    }

    @Override
    public void update(CollectionsGroup collectionGroup) {
        this.group = collectionGroup;
        groupNameTextView.setText(collectionGroup.getGroupName());
        groupContentTextView.setText(String.valueOf(collectionGroup.getStandard()));
    }
}
