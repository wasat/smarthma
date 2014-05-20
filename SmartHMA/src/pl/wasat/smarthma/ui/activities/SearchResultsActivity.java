package pl.wasat.smarthma.ui.activities;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.SearchListAdapter;
import pl.wasat.smarthma.database.DbAdapter;
import pl.wasat.smarthma.model.dataseries.Entry;
import pl.wasat.smarthma.ui.fragments.FailureFragment.OnFailureFragmentInteractionListener;
import pl.wasat.smarthma.ui.fragments.SearchDetailFragment;
import pl.wasat.smarthma.ui.fragments.SearchDetailFragment.OnSearchDetailFragmentInteractionListener;
import pl.wasat.smarthma.ui.fragments.SearchListFragment;
import pl.wasat.smarthma.ui.fragments.SearchListFragment.OnSearchListFragmentInteractionListener;
import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;

public class SearchResultsActivity extends FragmentActivity implements
		OnSearchListFragmentInteractionListener,
		OnSearchDetailFragmentInteractionListener,
		OnFailureFragmentInteractionListener, SearchListFragment.Callbacks {

	private DbAdapter dba;
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_search_results);

		// get the action bar
		ActionBar actionBar = getActionBar();

		// Enabling Back navigation on Action Bar icon
		actionBar.setDisplayHomeAsUpEnabled(true);

		// txtQuery = (TextView) findViewById(R.id.txtQuery);
		// webView = (WebView) findViewById(R.id.webView1);

		if (findViewById(R.id.search_detail_container) != null) {
			mTwoPane = true;
		}

		handleIntent(getIntent());
		dba = new DbAdapter(this);
	}

	@Override
	public void onItemSelected(String id) {
		Entry selectedEntry = (Entry) ((SearchListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.search_list_container)).getListAdapter()
				.getItem(Integer.parseInt(id));

		// mark metadata as read
		dba.openToWrite();
		dba.markAsRead(selectedEntry.getGuid());
		dba.close();
		selectedEntry.setRead(true);
		SearchListAdapter adapter = (SearchListAdapter) ((SearchListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.search_list_container)).getListAdapter();
		adapter.notifyDataSetChanged();
		Log.e("CHANGE", "Changing to read: ");

		// load metadata details to main panel
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putSerializable(Entry.KEY_RSS_ENTRY, selectedEntry);

			SearchDetailFragment fragment = new SearchDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.search_detail_container, fragment).commit();

		} else {
			/*
			 * Intent detailIntent = new Intent(this,
			 * DataSeriesDetailActivity.class);
			 * detailIntent.putExtra(DataSeriesDetailFragment.ARG_ITEM_ID, id);
			 * startActivity(detailIntent);
			 */
		}
	}

	/**
	 * Handling intent data
	 */
	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);

			/**
			 * Use this query to display search results like 1. Getting the data
			 * from SQLite and showing in listview 2. Making webrequest and
			 * displaying the data For now we just display the query only
			 */
			// txtQuery.setText("Search Query: " + query);
			// webView.loadUrl("http://geo.spacebel.be/opensearch/request/?httpAccept=application/atom%2Bxml&type=collection&startRecord=1&maximumRecords=10&query=daniel");

			SearchListFragment searchListFragment = SearchListFragment
					.newInstance(query);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.search_list_container, searchListFragment)
					.commit();

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.fragments.SearchDetailFragment.
	 * OnSearchDetailFragmentInteractionListener
	 * #onSearchDetailFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onSearchDetailFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.fragments.SearchListFragment.
	 * OnSearchListFragmentInteractionListener
	 * #onSearchListFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onSearchListFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailureFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub
		
	}
}