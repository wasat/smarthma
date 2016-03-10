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
    private List<Option> options;
    private Context context;


    public CatalogueArrayAdapter(Context context, int resource, List<Option> objects) {
        super(context, resource, objects);
        this.options = objects;
        this.context = context;
    }

    ViewHolder holder;

    class ViewHolder {
        ImageView icon;
        TextView title;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getApplicationContext()
                .getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);

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
