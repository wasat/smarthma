package pl.wasat.smarthma.ui.frags.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.NewsArticleListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.model.NewsArticle;

public class NewsDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private NewsArticle displayedArticle;
    private EoDbAdapter db;

    public NewsDetailFragment() {
        setHasOptionsMenu(true); // this enables us to set actionbar from
        // fragment
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new EoDbAdapter(getActivity());
        if (getArguments().containsKey(NewsArticle.KEY)) {
            displayedArticle = (NewsArticle) getArguments().getSerializable(
                    NewsArticle.KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article_detail,
                container, false);
        if (displayedArticle != null) {
            String title = displayedArticle.getTitle();
            String pubDate = displayedArticle.getPubDate();

            String content = displayedArticle.getEncodedContent();
            ((TextView) rootView.findViewById(R.id.article_title))
                    .setText(title);
            ((TextView) rootView.findViewById(R.id.article_author))
                    .setText(pubDate);
            final WebView webArticleDetail = (WebView) rootView
                    .findViewById(R.id.article_web_detail);
            webArticleDetail.loadData(content, "text/html", "UTF-8");
            Button buttonMore = (Button) rootView
                    .findViewById(R.id.button_more);
            buttonMore.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
                            .parse(displayedArticle.getAuthor()));
                    startActivity(browserIntent);

                }
            });

        }
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detailmenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d("item ID : ", "onOptionsItemSelected Item ID" + id);
        if (id == R.id.actionbar_saveoffline) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "This article has been saved of offline reading.",
                    Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.actionbar_markunread) {
            db.openToWrite();
            db.markAsUnread(displayedArticle.getGuid());
            db.close();
            displayedArticle.setRead(false);
            NewsArticleListAdapter adapter = (NewsArticleListAdapter) ((NewsListFragment) getActivity()
                    .getSupportFragmentManager().findFragmentById(
                            R.id.article_list)).getListAdapter();
            adapter.notifyDataSetChanged();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}