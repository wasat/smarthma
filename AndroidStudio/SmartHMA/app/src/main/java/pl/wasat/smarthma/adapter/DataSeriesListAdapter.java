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
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.utils.time.DateUtils;

public class DataSeriesListAdapter extends ArrayAdapter<EntryISO> {

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
/*        String dateStr = dataSeriesItem.getDate().toString();
        if (dateStr.isEmpty()) {
            dateStr = "1970-01-01T00:00:00.000Z";
        } else {
            dateStr = dataSeriesItem.getDate().getCIDate().getDateInCIDate().getDateGco().getText();
        }*/

        String pubDate = "Date: " + DateUtils.getISOPubDate(dataSeriesItem)
                + ", updated: " + dataSeriesItem.getUpdated();
        holder.dateView.setText(pubDate);

        if (dataSeriesItem.isNotRead()) {
            holder.textView.setTypeface(Typeface.DEFAULT_BOLD);
        }

        return convertView;

    }

    private static class ViewHolder {
        public TextView textView;
        public TextView dateView;
    }
}