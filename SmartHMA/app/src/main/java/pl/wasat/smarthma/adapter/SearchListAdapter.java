package pl.wasat.smarthma.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.FavouritesDbAdapter;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.utils.time.DateUtils;

public class SearchListAdapter extends ArrayAdapter<EntryISO> {

    private OnSlideElementListener listener;

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

/*        final String pubDate = "These data were published: %1$s  and updated: %1$s"
                + DateUtils.getISOPubDate(searchItem) + " and updated: "
                + searchItem.getUpdated();*/
        final String pubDate = String.format(activity.getString(R.string.these_data_are_published_and_updated),
                DateUtils.getISOPubDate(searchItem), searchItem.getUpdated());
        dateView.setText(pubDate);

        if (!searchItem.isNotRead()) {
            View row = rowView.findViewById(R.id.view_cell_collection_search_row_background);
            row.setBackgroundColor(ContextCompat.getColor(activity, R.color.row_selected));
        }

        final ImageView button = (ImageView) rowView.findViewById(R.id.star_button);
        updateFavourite(searchItem.isFavourite(), button, activity);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFavourite(!searchItem.isFavourite(), button, activity, searchItem);
                FavouritesDbAdapter dba = new FavouritesDbAdapter(activity);
                if (searchItem.isFavourite()) {
                    dba.openToWrite();
                    dba.insertEntry(searchItem);
                    dba.close();
                    Toast.makeText(activity, activity.getString(R.string.collection_added_to_favourites), Toast.LENGTH_LONG).show();
                } else {
                    dba.openToWrite();
                    dba.removeEntry(searchItem);
                    dba.close();
                }
                dba.openToRead();
                dba.close();
            }
        });
        SwipeDetector swipeDetector = new SwipeDetector(rowView, position);
        swipeDetector.setOnClickListener(listener);
        rowView.setOnTouchListener(swipeDetector);
        return rowView;
    }

    private void updateFavourite(boolean favourite, ImageView button, Activity activity) {
        if (favourite) {
            button.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_star_blue));
        } else {
            button.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_star));
        }
    }

    private void setFavourite(boolean favourite, ImageView button, Activity activity, EntryISO searchItem) {
        searchItem.setFavourite(favourite);
        updateFavourite(searchItem.isFavourite(), button, activity);
    }

    public void setListener(OnSlideElementListener listener) {
        this.listener = listener;
    }

}