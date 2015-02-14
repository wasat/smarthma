package pl.wasat.smarthma.customviews;

import android.content.Context;
import android.view.LayoutInflater;
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

    public EntryItemView(Context context) {
        super(context);
        inflateView(context);
    }

    private void inflateView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_cell_entry_item, this);
        this.tvEntryTitle = (TextView) this.findViewById(R.id.entry_item_title_textview);
        this.tvEntryDates = (TextView) this.findViewById(R.id.entry_item_dates_textview);
        this.thumbImageView = (ImageView) this.findViewById(R.id.entry_item_thumb_imageview);
    }

    @Override
    public void update(EntryOM entry) {
        this.entry = entry;
        tvEntryTitle.setText(entry.getTitle());
        tvEntryDates.setText(entry.getPublished());
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

}
