package pl.wasat.smarthma.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.utils.time.DateUtils;

public class SearchListAdapter extends ArrayAdapter<EntryISO> {

    public SearchListAdapter(Activity activity, List<EntryISO> searchList) {
        super(activity, 0, searchList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) getContext();
        LayoutInflater inflater = activity.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.view_cell_collection_search, parent, false);
        final EntryISO searchItem = getItem(position);

        TextView textView = (TextView) rowView
                .findViewById(R.id.search_list_name);
        textView.setText(searchItem.getTitle());

        TextView dateView = (TextView) rowView
                .findViewById(R.id.search_listing_smallprint);

        final String pubDate = "These data were published: "
                + DateUtils.getISOPubDate(searchItem) + " and updated: "
                + searchItem.getUpdated();

        dateView.setText(pubDate);

        if (!searchItem.isNotRead()) {
            //textView.setTypeface(Typeface.DEFAULT_BOLD);
            View row = rowView.findViewById(R.id.view_cell_collection_search_row_background);
            row.setBackgroundColor(activity.getResources().getColor(R.color.row_selected));
        }

        final ImageView button = (ImageView) rowView.findViewById(R.id.star_button);
        updateFavourite(searchItem.isFavourite(), button, activity, searchItem);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setFavourite(!searchItem.isFavourite(), button, activity, searchItem);
                List<Link> links = searchItem.getLink();
                for (Link l : links)
                {
                    Log.d("ZX", "link href: "+l.getHref());
                }
                String id = searchItem.getId();
                String identifier = searchItem.getIdentifier();
                Log.d("ZX", "id: "+id);
                Log.d("ZX", "identifier: "+identifier);
            }
        });

        return rowView;
    }

    private void setFavourite(boolean favourite, ImageView button, Activity activity, EntryISO searchItem)
    {
        searchItem.setFavourite(favourite);
        updateFavourite(searchItem.isFavourite(), button, activity, searchItem);
    }

    private void updateFavourite(boolean favourite, ImageView button, Activity activity, EntryISO searchItem)
    {
        if (favourite)
        {
            button.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_star_blue));
        }
        else
        {
            button.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_star));
        }
    }
}