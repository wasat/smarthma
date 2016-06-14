/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wasat.smarthma.customviews;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
import pl.wasat.smarthma.adapter.SwipeDetector;
import pl.wasat.smarthma.database.FavouritesDbAdapter;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.entry.Entry;

/**
 * The type Entry item view.
 */
public class EntryItemView extends RelativeLayout implements SpiceListItemView<Entry>, Animation.AnimationListener {

    private final OnSlideElementListener listener;
    private TextView tvEntryTitle;
    private TextView tvEntryDates;
    private ImageView thumbImageView;
    private Entry entry;
    private ImageView button;
    private Context context;

    /**
     * Instantiates a new Entry item view.
     *
     * @param context  the context
     * @param listener the listener
     */
    public EntryItemView(Context context, OnSlideElementListener listener) {
        super(context);
        this.listener = listener;
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

    /* (non-Javadoc)
     * @see com.octo.android.robospice.spicelist.SpiceListItemView#getData()
     */
    @Override
    public Entry getData() {
        return entry;
    }

    @Override
    public ImageView getImageView(int imageIndex) {
        return thumbImageView;
    }

    @Override
    public int getImageViewCount() {
        return 1;
    }

    @Override
    public void update(Entry entry) {
        this.entry = entry;
        tvEntryTitle.setText(this.entry.getTitle());
        tvEntryDates.setText(this.entry.getPublished());
        SwipeDetector swipeDetector = new SwipeDetector(this);
        swipeDetector.setOnClickListener(listener);
        setOnTouchListener(swipeDetector);
        titleAnimation();
        clickFavouriteStar();
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
                            dba.removeEntry(EntryItemView.this.entry);
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

    private void updateFavourite(boolean favourite) {
        if (favourite) {
            button.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_blue));
        } else {
            button.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star));
        }
    }

    private void setFavourite(boolean favourite) {
        entry.setFavourite(favourite);
        updateFavourite(entry.isFavourite());
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
}
