package pl.wasat.smarthma.customviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.octo.android.robospice.spicelist.SpiceListItemView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.om.EntryOM;

public class EntryItemView extends RelativeLayout implements SpiceListItemView<EntryOM> {

    private TextView tvEntryTitle;
    private TextView tvEntryDates;
    private ImageView thumbImageView;
    private EntryOM entry;
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
    public void update(EntryOM newEntry) {
        this.entry = newEntry;
        tvEntryTitle.setText(entry.getTitle());
        tvEntryDates.setText(entry.getPublished());

        try {
            if (button != null) {
                updateFavourite(this.entry.isFavourite());

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setFavourite(!entry.isFavourite());
                        String id = entry.getId();
                        String identifier = entry.getIdentifier();
                        Log.d("ZX", "id: " + id);
                        Log.d("ZX", "identifier: " + identifier);
                    }
                });
            } else {
                Log.d("ZX", "Button is null.");
            }
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
    public EntryOM getData() {
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
