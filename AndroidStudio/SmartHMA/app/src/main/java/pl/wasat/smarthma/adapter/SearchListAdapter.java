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

public class SearchListAdapter extends ArrayAdapter<EntryISO> {

    public SearchListAdapter(Activity activity, List<EntryISO> searchList) {
        super(activity, 0, searchList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) getContext();
        LayoutInflater inflater = activity.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.view_cell_collection_search, parent, false);
        EntryISO searchItem = getItem(position);

        TextView textView = (TextView) rowView
                .findViewById(R.id.search_list_name);
        textView.setText(searchItem.getTitle());

        TextView dateView = (TextView) rowView
                .findViewById(R.id.search_listing_smallprint);
        String pubDate = "Date: " + searchItem.getDate().getCIDate().getDateInCIDate().getDateGco().getText() + ", updated: "
                + searchItem.getUpdated();

        dateView.setText(pubDate);

        if (!searchItem.isRead()) {
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        return rowView;

    }
}