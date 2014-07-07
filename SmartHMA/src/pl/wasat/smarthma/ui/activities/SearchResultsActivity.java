package pl.wasat.smarthma.ui.activities;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.ProductsListAdapter;
import pl.wasat.smarthma.adapter.SearchListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.ui.frags.FailureFragment.OnFailureFragmentListener;
import pl.wasat.smarthma.ui.frags.MapSearchFragment.OnMapSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.MetadataFragment.OnMetadataFragmentListener;
import pl.wasat.smarthma.ui.frags.browse.CollectionItemRightFragment;
import pl.wasat.smarthma.ui.frags.browse.CollectionItemRightFragment.OnCollectionItemRightFragmentListener;
import pl.wasat.smarthma.ui.frags.search.ProductDetailSearchFragment;
import pl.wasat.smarthma.ui.frags.search.ProductDetailSearchFragment.OnProductDetailSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchDataSeriesIntroFragment.OnSearchDataSeriesIntroFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchDetailFragment;
import pl.wasat.smarthma.ui.frags.search.SearchDetailFragment.OnSearchDetailFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment.OnSearchListFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchProductsListFragment;
import pl.wasat.smarthma.ui.frags.search.SearchProductsListFragment.OnSearchProductsListFragmentListener;
import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.google.android.gms.maps.model.LatLngBounds;

public class SearchResultsActivity extends FragmentActivity implements
		OnSearchListFragmentListener, OnSearchDetailFragmentListener,
		OnSearchProductsListFragmentListener, OnFailureFragmentListener,
		OnProductDetailSearchFragmentListener,
		OnCollectionItemRightFragmentListener, OnMapSearchFragmentListener,
		OnSearchDataSeriesIntroFragmentListener, OnMetadataFragmentListener {

	private EoDbAdapter dba;
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

		if (findViewById(R.id.search_detail_container) != null) {
			mTwoPane = true;
		}

		handleIntent(getIntent());
		dba = new EoDbAdapter(this);
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
	 * @see pl.wasat.smarthma.ui.fragments.SearchListFragment.
	 * OnSearchListFragmentListener
	 * #onSearchListFragmentItemSelected(java.lang.String)
	 */
	@Override
	public void onSearchListFragmentItemSelected(String id) {
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

		// load metadata details to main panel
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putSerializable(Entry.KEY_RSS_ENTRY, selectedEntry);

			SearchDetailFragment fragment = new SearchDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.search_detail_container, fragment).commit();

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

	@Override
	public void onFailureFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCollectionItemRightFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProductDetailSearchFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapSearchFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapSearchFragmentBoundsChange(LatLngBounds bounds) {
		// The user selected the headline of an article from the
		// HeadlinesFragment
		// Do something here to display that article

		CollectionItemRightFragment collRightFrag = (CollectionItemRightFragment) getSupportFragmentManager()
				.findFragmentByTag("CollectionItemRightFragment");

		if (collRightFrag != null) {
			// If article frag is available, we're in two-pane layout...
			// Call a method in the ArticleFragment to update its content
			collRightFrag.updateProductAreaBounds(bounds);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.SearchDataSeriesIntroFragment.
	 * OnSearchDataSeriesIntroFragmentListener
	 * #onSearchDataSeriesIntroFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onSearchDataSeriesIntroFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.fragments.SearchProductsFeedsFragment.
	 * OnSearchProductsFeedFragmentListener
	 * #onSearchProductsFeedFragmentItemSelected(java.lang.String)
	 */
	@Override
	public void onSearchProductsListFragmentItemSelected(String id) {
		Entry selectedEntry = (Entry) ((SearchProductsListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.search_list_container)).getListAdapter()
				.getItem(Integer.parseInt(id));

		// mark metadata as read
		dba.openToWrite();
		dba.markAsRead(selectedEntry.getGuid());
		dba.close();
		selectedEntry.setRead(true);
		ProductsListAdapter adapter = (ProductsListAdapter) ((SearchProductsListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.search_list_container)).getListAdapter();
		adapter.notifyDataSetChanged();

		// load metadata details to main panel
		if (mTwoPane) {
			ProductDetailSearchFragment fragment = ProductDetailSearchFragment
					.newInstance(selectedEntry);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.search_detail_container, fragment).commit();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wasat.smarthma.ui.fragments.MetadataFragment.OnMetadataFragmentListener
	 * #onMetadataFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onMetadataFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}
}