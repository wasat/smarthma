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

package pl.wasat.smarthma.adapter;


import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.news.NewsArticle;

/**
 * The type News article list adapter.
 */
public class NewsArticleListAdapter extends ArrayAdapter<NewsArticle> {

    private OnSlideElementListener listener;

    /**
     * Instantiates a new News article list adapter.
     *
     * @param activity       the activity
     * @param layoutResource the layout resource
     * @param articles       the articles
     */
    public NewsArticleListAdapter(Activity activity, int layoutResource, List<NewsArticle> articles) {
        super(activity, layoutResource, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) getContext();
        LayoutInflater inflater = activity.getLayoutInflater();

        convertView = inflater.inflate(R.layout.view_cell_article, null);

        SwipeDetector swipeDetector = new SwipeDetector(convertView, position);
        swipeDetector.setOnClickListener(listener);
        convertView.setOnTouchListener(swipeDetector);

        NewsArticle article = getItem(position);

        TextView textView = (TextView) convertView.findViewById(R.id.article_title_text);
        textView.setText(article.getTitle());

        TextView dateView = (TextView) convertView.findViewById(R.id.article_listing_smallprint);
        String pubDate = article.getPubDate();
        dateView.setText(pubDate);

        if (article.isRead()) {
            LinearLayout row = (LinearLayout) convertView.findViewById(R.id.view_cell_article_row_background);
            row.setBackgroundColor(ContextCompat.getColor(activity, R.color.row_selected));
        }
        return convertView;
    }

    /**
     * Sets listener.
     *
     * @param listener the listener
     */
    public void setListener(OnSlideElementListener listener) {
        this.listener = listener;
    }
}