package pl.wasat.smarthma.customviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.spicelist.SpiceListItemView;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.FavouritesDbAdapter;
import pl.wasat.smarthma.model.entry.Entry;

public class EntryItemView extends RelativeLayout implements SpiceListItemView<Entry> {

    private TextView tvEntryTitle;
    private TextView tvEntryDates;
    private ImageView thumbImageView;
    private Entry entry;
    private ImageView button;
    private Context context;

    public EntryItemView(Context context) {
        super(context);
        inflateView(context);
    }

    private void inflateView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_cell_entry_item, this);
        this.tvEntryTitle = (TextView) this.findViewById(R.id.entry_item_title_textview);
        this.tvEntryDates = (TextView) this.findViewById(R.id.entry_item_dates_textview);
        this.thumbImageView = (ImageView) this.findViewById(R.id.entry_item_thumb_imageview);
        this.button = (ImageView) this.findViewById(R.id.star_button);
    }

    @Override
    public void update(Entry entry) {
        this.entry = entry;
        tvEntryTitle.setText(this.entry.getTitle());
        tvEntryDates.setText(this.entry.getPublished());

        try {
            if (button != null) {
                updateFavourite(this.entry.isFavourite());

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setFavourite(!EntryItemView.this.entry.isFavourite());

                        FavouritesDbAdapter dba = new FavouritesDbAdapter(context);
                        //String title = EntryItemView.this.entry.getTitle();
                        //Log.d("ZX", "title: " + title);
                        //String published = EntryItemView.this.entry.getPublished();
                        //Log.d("ZX", "published: " + published);
                        //String updated = EntryItemView.this.entry.getUpdated();
                        //Log.d("ZX", "updated: " + updated);
                        //String id = EntryItemView.this.entry.getId();
                        //String identifier = EntryItemView.this.entry.getIdentifier();
                        //Log.d("ZX", "id: " + id);
                        //Log.d("ZX", "identifier: " + identifier);
                        //Log.d("ZX", "-");
                        if (!EntryItemView.this.entry.isFavourite()) {
                            dba.openToWrite();
                            dba.removeEntry(EntryItemView.this.entry);
                            //Log.d("ZX", "result: "+result);
                            dba.close();
                        } else {
                            dba.openToWrite();
                            //long dbaResult =
                            dba.insertEntry(EntryItemView.this.entry);
                            dba.close();
                            Toast.makeText(context, context.getString(R.string.product_added_to_favourites), Toast.LENGTH_LONG).show();
                        }
                        //Log.d("ZX", "--");
                        dba.openToRead();
                        ArrayList<Entry> all = dba.getOMEntries();
/*                        for (Entry o : all) {
                            //Log.d("ZX", " " + o.getTitle());
                        }*/
                        dba.close();
                        //Log.d("ZX", "---");
                    }
                });
            } /*else {
                //Log.d("ZX", "Button is null.");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ImageView getImageView(int imageIndex) {
        return thumbImageView;
    }

    @Override
    public int getImageViewCount() {
        return 1;
    }

    /* (non-Javadoc)
     * @see com.octo.android.robospice.spicelist.SpiceListItemView#getData()
     */
    @Override
    public Entry getData() {
        return entry;
    }

    private void setFavourite(boolean favourite) {
        entry.setFavourite(favourite);
        updateFavourite(entry.isFavourite());
    }

    private void updateFavourite(boolean favourite) {
        if (favourite) {
            button.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_blue));
        } else {
            button.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star));
        }
    }

}
