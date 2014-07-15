package pl.wasat.smarthma.ui.activities;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.NewsArticleListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.model.NewsArticle;
import pl.wasat.smarthma.ui.frags.news.NewsDetailFragment;
import pl.wasat.smarthma.ui.frags.news.NewsListFragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

public class NewsActivity extends BaseSmartHMActivity implements
		NewsListFragment.Callbacks {

	private boolean mTwoPane;
	private EoDbAdapter dba;

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
		setContentView(R.layout.activity_article_list);

		dba = new EoDbAdapter(this);

		if (findViewById(R.id.article_detail_container) != null) {
			mTwoPane = true;
			((NewsListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.article_list))
					.setActivateOnItemClick(true);
		}
	}

	@Override
	public void onItemSelected(String id) {
		NewsArticle selected = (NewsArticle) ((NewsListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.article_list)).getListAdapter().getItem(
				Integer.parseInt(id));

		// mark article as read
		dba.openToWrite();
		dba.markAsRead(selected.getGuid());
		dba.close();
		selected.setRead(true);
		NewsArticleListAdapter adapter = (NewsArticleListAdapter) ((NewsListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.article_list)).getListAdapter();
		adapter.notifyDataSetChanged();
		Log.e("CHANGE", "Changing to read: ");

		// load article details to main panel
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putSerializable(NewsArticle.KEY, selected);

			NewsDetailFragment fragment = new NewsDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.article_detail_container, fragment).commit();

		} 
	}
}
