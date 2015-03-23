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
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.view_cell_collection, parent, false);

        TextView title = (TextView) vi.findViewById(R.id.collection_name);
        TextView artist = (TextView) vi.findViewById(R.id.collection_desc);

        TextView duration = (TextView) vi.findViewById(R.id.collection_id);
        ImageView thumb_image = (ImageView) vi
                .findViewById(R.id.collection_image);

        Collection collection;
        collection = collData.get(position);

        // Setting all values in listview
        title.setText(collection.getName());
        artist.setText(groupName);
        duration.setText("No: " + collection.getId());
        String url = Const.IMG_URL + "sat" + mod(collection.getId(), 15)
                + ".jpeg";
        Picasso.with(activity).load(url).resize(72, 72).centerCrop()
                .into(thumb_image);
        return vi;
    }

    private int mod(int x, int y) {
        int result = x % y;
        return result < 0 ? result + y : result;
    }
}