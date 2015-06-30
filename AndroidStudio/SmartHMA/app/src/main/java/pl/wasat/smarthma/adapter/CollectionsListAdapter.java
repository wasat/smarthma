/**
 *
 */
package pl.wasat.smarthma.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.Collection;

/**
 * @author Wasat Sp. z o.o
 */
public class CollectionsListAdapter extends BaseAdapter {

    private final Activity activity;
    private final ArrayList<Collection> collData;
    private final String groupName;
    private static LayoutInflater inflater = null;

    public CollectionsListAdapter(Activity activ, ArrayList<Collection> data,
                                  String grName) {
        activity = activ;
        collData = data;
        groupName = grName;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return collData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_cell_collection, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.collection_name);
            holder.artist = (TextView) convertView.findViewById(R.id.collection_desc);

            //TextView duration = (TextView) vi.findViewById(R.id.collection_id);
            holder.thumb_image = (ImageView) convertView
                    .findViewById(R.id.collection_image);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }


        Collection collection;
        collection = collData.get(position);

        // Setting all values in listview
        holder.title.setText(collection.getName());
        holder.artist.setText(groupName);
        //duration.setText("No: " + collection.getId());
        String url = Const.IMG_URL + "sat" + mod(collection.getId(), 15)
                + ".jpeg";
        Picasso.with(activity).load(url).resize(72, 72).centerCrop()
                .into(holder.thumb_image);

        //TODO Infoapps style for row which has been read

        /*if(collection.isRead()){
            holder.row = convertView.findViewById(R.id.view_cell_collection_search_row_background);
            holder.row.setBackgroundColor(activity.getResources().getColor(R.color.row_selected));
            holder.button = (ImageView) convertView.findViewById(R.id.star_button);
            holder.button.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_star_blue));
        }*/

        return convertView;
    }

    private int mod(int x, int y) {
        int result = x % y;
        return result < 0 ? result + y : result;
    }

    private static class ViewHolder {
        public TextView title;
        public TextView artist;
        public ImageView thumb_image;
        public View row;
        public ImageView button;
    }
}