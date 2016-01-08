package pl.wasat.smarthma.ui.activities;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.NewsArticleListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.model.NewsArticle;
import pl.wasat.smarthma.ui.activities.base.BaseSmartHMActivity;
import pl.wasat.smarthma.ui.frags.news.NewsDetailFragment;
import pl.wasat.smarthma.ui.frags.news.NewsListFragment;

public class NewsActivity extends BaseSmartHMActivity implements
        NewsListFragment.Callbacks {

    private boolean mTwoPane;
    private EoDbAdapter dba;
    private NewsListFragment newsListFragment;

    public NewsActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        TextView text = (TextView) findViewById(R.id.action_bar_title);
        text.setText(getString(R.string.esa_online));

        dba = new EoDbAdapter(this);

        if (findViewById(R.id.activity_base_details_container) != null) {
            mTwoPane = true;
            loadNewsListPanel();
        }
    }

    private void loadNewsListPanel() {
        newsListFragment = new NewsListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container,
                        newsListFragment).commit();
    }

    /*
 * (non-Javadoc)
 *
 * @see android.support.v4.app.FragmentActivity#onBackPressed()
 */
    @Override
    public void onBackPressed() {
        if (dismissMenuOnBackPressed()) return;
        FragmentManager fm = getSupportFragmentManager();
        int bsec = fm.getBackStackEntryCount();
        if (bsec > 1) {
            fm.popBackStack();
        } else {
            finish();
            super.onBackPressed();
        }
    }

    @Override
    public void onItemSelected(String id) {
        NewsArticle selected = (NewsArticle) ((NewsListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container)).getListAdapter().getItem(
                Integer.parseInt(id));

        dba.openToWrite();
        dba.markAsRead(selected.getGuid());
        dba.close();
        selected.setRead(true);
        NewsArticleListAdapter adapter = (NewsArticleListAdapter) ((NewsListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_base_list_container)).getListAdapter();
        adapter.notifyDataSetChanged();

        // load article details to main panel
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putSerializable(NewsArticle.KEY, selected);

            NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
            newsDetailFragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_base_details_container, newsDetailFragment,
                            NewsDetailFragment.class.getSimpleName())
                    .commit();

        }
    }

    public NewsListFragment getNewsListFragment() {
        return newsListFragment;
    }
}
