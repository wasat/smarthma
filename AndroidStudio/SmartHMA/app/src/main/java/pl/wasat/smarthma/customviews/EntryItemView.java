package pl.wasat.smarthma.customviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.spicelist.SpiceListItemView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.FavouritesDbAdapter;
import pl.wasat.smarthma.model.entry.Entry;

public class EntryItemView extends RelativeLayout implements SpiceListItemView<Entry>, Animation.AnimationListener {

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

        titleAnimation();
        clickFavouriteStar();
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

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        titleAnimation();
    }

    private void clickFavouriteStar() {
        try {
            if (button != null) {
                updateFavourite(this.entry.isFavourite());

                button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setFavourite(!EntryItemView.this.entry.isFavourite());

                        FavouritesDbAdapter dba = new FavouritesDbAdapter(context);
                        if (!EntryItemView.this.entry.isFavourite()) {
                            dba.openToWrite();
                            int result = dba.removeEntry(EntryItemView.this.entry);
                            dba.close();
                        } else {
                            dba.openToWrite();
                            dba.insertEntry(EntryItemView.this.entry);
                            dba.close();
                            Toast.makeText(context, context.getString(R.string.product_added_to_favourites), Toast.LENGTH_LONG).show();
                        }
                        dba.openToRead();
                        dba.close();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void titleAnimation() {
        String title = this.entry.getTitle().replaceFirst("urn:ogc:def:", "");
        if (title.length() < 30) {
            tvEntryTitle.setText(title);
            return;
        }

        int stopAnimPos = -1000;
        int startAnimPos = 20;
        int duration = 30000;

        Animation animation = new TranslateAnimation(startAnimPos, stopAnimPos, 0, 0);
        animation.setDuration(duration);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setAnimationListener(this);

        tvEntryTitle.setText(title);
        tvEntryTitle.measure(0, 0);
        tvEntryTitle.setAnimation(animation);
    }
}
