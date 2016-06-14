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

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.SwipeDetector;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.mission.MissionHeaderData;
import pl.wasat.smarthma.model.mission.MissionItemData;

/**
 * The type Expandable list adapter.
 */
@SuppressLint("InflateParams")
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private static final String EARTH_ESA_URL = "https://earth.esa.int";
    private final Context _context;
    private final List<MissionHeaderData> _listDataHeader; // header titles
    // child data in format of header title, child title
    private final HashMap<String, List<MissionItemData>> _listDataChild;

    /**
     * Instantiates a new Expandable list adapter.
     *
     * @param context        the context
     * @param listDataHeader the list data header
     * @param listDataChild  the list data child
     */
    public ExpandableListAdapter(Context context, List<MissionHeaderData> listDataHeader,
                                 HashMap<String, List<MissionItemData>> listDataChild) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String missKey = this._listDataHeader.get(groupPosition).getName();
        List<MissionItemData> hsh = this._listDataChild.get(missKey);
        return hsh.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getName())
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        MissionHeaderData obj = (MissionHeaderData) getGroup(groupPosition);
        String headerTitle = obj.getName();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.view_cell_missions_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.missions_list_header);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        MissionItemData childItem = (MissionItemData) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.view_cell_missions_list_item, null);
        }
        final String missionName = childItem.getName();
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.mission_child_list_item_tv);

        ImageView listItemImg = (ImageView) convertView
                .findViewById(R.id.mission_child_list_item_thmb_imgview);
        String childImgUrl = EARTH_ESA_URL + childItem.getImgLink();

        Picasso.with(_context)
                .load(childImgUrl)
                .resize(50, 50)
                .centerInside()
                .into(listItemImg);

        txtListChild.setText(missionName);

        SwipeDetector swipeDetector = new SwipeDetector(convertView, -1);
        swipeDetector.setOnClickListener(new OnSlideElementListener() {
            @Override
            public void Catch(boolean swipeRight, int position) {
                onDetectSwipe(swipeRight, missionName);
            }
        });
        convertView.setOnTouchListener(swipeDetector);
        return convertView;
    }

    private void onDetectSwipe(boolean swipeRight, String missionName) {
        try {
            OnSwipeListItemListener onSwipeListItemListener = (OnSwipeListItemListener) _context;
            onSwipeListItemListener.onSwipeChildItem(swipeRight, missionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    /**
     * The interface On swipe list item listener.
     */
    public interface OnSwipeListItemListener {
        /**
         * On swipe child item.
         *
         * @param swipeRight  the swipe right
         * @param missionName the mission name
         */
        void onSwipeChildItem(boolean swipeRight, String missionName);
    }

}
