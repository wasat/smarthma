package pl.wasat.smarthma.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.NewsArticle;


public class NewsArticleListAdapter extends ArrayAdapter<NewsArticle> {

    public NewsArticleListAdapter(Activity activity, List<NewsArticle> articles) {
        super(activity, 0, articles);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) getContext();
        LayoutInflater inflater = activity.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.view_cell_article, parent, false);
        NewsArticle article = getItem(position);


        TextView textView = (TextView) rowView.findViewById(R.id.article_title_text);
        textView.setText(article.getTitle());

        TextView dateView = (TextView) rowView.findViewById(R.id.article_listing_smallprint);
        String pubDate = article.getPubDate();
        dateView.setText(pubDate);


        if (!article.isRead()) {
            LinearLayout row = (LinearLayout) rowView.findViewById(R.id.article_row_layout);
            //row.setBackgroundColor(Color.WHITE);
            //textView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        return rowView;

    }
}