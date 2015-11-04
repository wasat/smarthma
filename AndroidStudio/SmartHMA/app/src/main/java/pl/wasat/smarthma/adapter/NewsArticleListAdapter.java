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
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.NewsArticle;


public class NewsArticleListAdapter extends ArrayAdapter<NewsArticle> {


    private OnSlideElementListener listener;


    public void setListener(OnSlideElementListener listener) {
        this.listener = listener;
    }

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
            row.setBackgroundColor(activity.getResources().getColor(R.color.row_selected));

            //textView.setTypeface(.DEFAULT_BOLD);
        }
        return convertView;

    }
}