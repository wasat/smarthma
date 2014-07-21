package pl.wasat.smarthma.ui.activities;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.ProductsListAdapter;
import pl.wasat.smarthma.adapter.SearchListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.eo.Pos;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.ui.frags.FailureFragment.OnFailureFragmentListener;
import pl.wasat.smarthma.ui.frags.MapSearchFragment;
import pl.wasat.smarthma.ui.frags.MapSearchFragment.OnMapSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.MetadataFragment.OnMetadataFragmentListener;
import pl.wasat.smarthma.ui.frags.search.CollectionItemRightFragment;
import pl.wasat.smarthma.ui.frags.search.CollectionItemRightFragment.OnCollectionItemRightFragmentListener;
import pl.wasat.smarthma.ui.frags.search.ProductDetailSearchFragment;
import pl.wasat.smarthma.ui.frags.search.ProductDetailSearchFragment.OnProductDetailSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchResultCollectionDetailsFragment;
import pl.wasat.smarthma.ui.frags.search.SearchResultCollectionDetailsFragment.OnSearchResultCollectionDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchDataSeriesIntroFragment.OnSearchDataSeriesIntroFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment.OnSearchListFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchProductsListFragment;
import pl.wasat.smarthma.ui.frags.search.SearchProductsListFragment.OnSearchProductsListFragmentListener;
import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;

import com.google.android.gms.maps.model.LatLngBounds;

public class SearchResultsActivity extends BaseSmartHMActivity implements
		OnSearchListFragmentListener, OnSearchProductsListFragmentListener,
		OnFailureFragmentListener, OnProductDetailSearchFragmentListener,
		OnCollectionItemRightFragmentListener, OnMapSearchFragmentListener,
		OnSearchDataSeriesIntroFragmentListener, OnMetadataFragmentListener,
		OnSearchResultCollectionDetailsFragmentListener {

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

		if (findViewById(R.id.search_results_detail_container) != null) {
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
			FedeoRequest request = buildSearchRequest(query);

			SearchListFragment searchListFragment = SearchListFragment
					.newInstance(request);
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.search_results_list_container, searchListFragment)
					.commit();

		}

	}

	/**
	 * @param query
	 * @return
	 */
	private FedeoRequest buildSearchRequest(String query) {

		SharedPreferences prefs = getSharedPreferences(Const.KEY_PREF_FILE, 0);

		FedeoRequest request = new FedeoRequest();
		request.setDefaultParams();
		request.setParentIdentifier(prefs.getString(Const.KEY_PREF_PARENT_ID,
				"EOP:ESA:FEDEO"));
		request.setStartDate(prefs
				.getString(Const.KEY_PREF_DATETIME_START, "0"));
		request.setEndDate(prefs.getString(Const.KEY_PREF_DATETIME_END, "0"));
		request.setBbox(prefs.getFloat(Const.KEY_PREF_BBOX_WEST, -180),
				prefs.getFloat(Const.KEY_PREF_BBOX_SOUTH, -90),
				prefs.getFloat(Const.KEY_PREF_BBOX_EAST, 180),
				prefs.getFloat(Const.KEY_PREF_BBOX_NORTH, 90));
		request.setQuery(query);

//		String queryUrl = Const.HTTP_REQUEST_BASE_URL
//				+ Const.URL_PARM_HTTP_ACCEPT + Const.URL_PARM_TYPE
//				+ Const.URL_PARM_PARENT_ID
//				+ prefs.getString(Const.KEY_PREF_PARENT_ID, "EOP:ESA:FEDEO")
//				+ Const.URL_PARM_START_RECORD + "1" + Const.URL_PARM_MAX_REC
//				+ "15" + Const.URL_PARM_START_DATE
//				+ prefs.getString(Const.KEY_PREF_DATETIME_START, "0")
//				+ Const.URL_PARM_END_DATE
//				+ prefs.getString(Const.KEY_PREF_DATETIME_END, "0")
//				+ Const.URL_PARM_BBOX
//				+ prefs.getFloat(Const.KEY_PREF_BBOX_WEST, -180) + ","
//				+ prefs.getFloat(Const.KEY_PREF_BBOX_SOUTH, -90) + ","
//				+ prefs.getFloat(Const.KEY_PREF_BBOX_EAST, 180) + ","
//				+ prefs.getFloat(Const.KEY_PREF_BBOX_NORTH, 90)
//				+ Const.URL_PARM_QUERY + query;

		return request;
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
				.findFragmentById(R.id.search_results_list_container))
				.getListAdapter().getItem(Integer.parseInt(id));

		// mark metadata as read
		dba.openToWrite();
		dba.markAsRead(selectedEntry.getGuid());
		dba.close();
		selectedEntry.setRead(true);
		SearchListAdapter adapter = (SearchListAdapter) ((SearchListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.search_results_list_container))
				.getListAdapter();
		adapter.notifyDataSetChanged();

		SearchResultCollectionDetailsFragment searchResultCollectionDetailsFragment = SearchResultCollectionDetailsFragment
				.newInstance(selectedEntry);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.search_results_detail_container,
						searchResultCollectionDetailsFragment,
						"SearchResultCollectionDetailsFragment")
				.addToBackStack("SearchResultCollectionDetailsFragment")
				.commit();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.fragments.SearchDetailFragment.
	 * OnSearchDetailFragmentInteractionListener
	 * #onSearchDetailFragmentInteraction(android.net.Uri)
	 */

	@Override
	public void onFailureFragmentInteraction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCollectionItemRightFragmentInteraction() {
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
				.findFragmentById(R.id.search_results_list_container))
				.getListAdapter().getItem(Integer.parseInt(id));

		// mark metadata as read
		dba.openToWrite();
		dba.markAsRead(selectedEntry.getGuid());
		dba.close();
		selectedEntry.setRead(true);
		ProductsListAdapter adapter = (ProductsListAdapter) ((SearchProductsListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.search_results_list_container))
				.getListAdapter();
		adapter.notifyDataSetChanged();

		// load metadata details to main panel
		if (mTwoPane) {
			ProductDetailSearchFragment fragment = ProductDetailSearchFragment
					.newInstance(selectedEntry);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.search_results_detail_container, fragment)
					.commit();
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
	public void onMetadataFragmentInteraction() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.frags.search.SearchProductsListFragment.
	 * OnSearchProductsListFragmentListener
	 * #onSearchProductsListFragmentFootprintSend(java.util.ArrayList)
	 */
	@Override
	public void onSearchProductsListFragmentFootprintSend(
			ArrayList<List<Pos>> footPrints) {
		MapSearchFragment mapSearchFragment = (MapSearchFragment) getSupportFragmentManager()
				.findFragmentByTag("MapSearchFragment");

		if (mapSearchFragment != null) {
			// If article frag is available, we're in two-pane layout...
			// Call a method in the ArticleFragment to update its content
			mapSearchFragment.showFootPrints(footPrints);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wasat.smarthma.ui.frags.search.SearchResultCollectionDetailsFragment
	 * .OnSearchResultCollectionDetailsFragmentListener
	 * #onSearchResultCollectionDetailsFragmentShowProducts(java.lang.String)
	 */
	@Override
	public void onSearchResultCollectionDetailsFragmentShowProducts(
			FedeoRequest request) {
		MapSearchFragment mapSearchFragment = MapSearchFragment.newInstance(
        );
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.search_results_detail_container,
						mapSearchFragment, "MapSearchFragment")
				.addToBackStack("MapSearchFragment").commit();

		SearchProductsListFragment searchProductsFeedsFragment = SearchProductsListFragment
				.newInstance(request);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.search_results_list_container,
						searchProductsFeedsFragment)
				.addToBackStack("SearchProductsListFragment").commit();

	}

}