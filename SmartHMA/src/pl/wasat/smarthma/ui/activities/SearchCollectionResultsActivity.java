package pl.wasat.smarthma.ui.activities;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.SearchListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.eo.Pos;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.base.BaseProductDetailsFragment.OnProductDetailSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment.OnBaseShowProductsListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment.OnCollectionDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MapSearchFragment;
import pl.wasat.smarthma.ui.frags.common.MapSearchFragment.OnMapSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MetadataFragment.OnMetadataFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment.OnSearchListFragmentListener;
import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLngBounds;

public class SearchCollectionResultsActivity extends BaseSmartHMActivity implements
		OnSearchListFragmentListener, OnBaseShowProductsListFragmentListener,
		OnProductDetailSearchFragmentListener,
		OnMapSearchFragmentListener,
		OnMetadataFragmentListener,
		OnCollectionDetailsFragmentListener {

	private EoDbAdapter dba;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// get the action bar
		ActionBar actionBar = getActionBar();

		// Enabling Back navigation on Action Bar icon
		actionBar.setDisplayHomeAsUpEnabled(true);

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
			
			SharedPrefs sharedPrefs = new SharedPrefs(this);
			sharedPrefs.setQueryPrefs(query);
			
			FedeoRequest fedeoRequest = new FedeoRequest();
			fedeoRequest.buildFromShared(this);

			SearchListFragment searchListFragment = SearchListFragment
					.newInstance(fedeoRequest);
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.activity_base_list_container, searchListFragment)
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
				.findFragmentById(R.id.activity_base_list_container))
				.getListAdapter().getItem(Integer.parseInt(id));

		// mark metadata as read
		dba.openToWrite();
		dba.markAsRead(selectedEntry.getGuid());
		dba.close();
		selectedEntry.setRead(true);
		SearchListAdapter adapter = (SearchListAdapter) ((SearchListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.activity_base_list_container))
				.getListAdapter();
		adapter.notifyDataSetChanged();

		CollectionDetailsFragment searchResultCollectionDetailsFragment = CollectionDetailsFragment
				.newInstance(selectedEntry);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_details_container,
						searchResultCollectionDetailsFragment,
						"SearchResultCollectionDetailsFragment")
				.addToBackStack("SearchResultCollectionDetailsFragment")
				.commit();

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

		CollectionDetailsFragment searchResultCollectionDetailsFragment = (CollectionDetailsFragment) getSupportFragmentManager()
				.findFragmentByTag("SearchResultCollectionDetailsFragment");

		if (searchResultCollectionDetailsFragment != null) {
			searchResultCollectionDetailsFragment
					.updateAreaBounds(bounds);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.fragments.SearchProductsFeedsFragment.
	 * OnSearchProductsFeedFragmentListener
	 * #onSearchProductsFeedFragmentItemSelected(java.lang.String)
	 */
	@Override
	public void onBaseShowProductsListFragmentItemSelected(String id) {
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
	public void onBaseShowProductsListFragmentFootprintSend(
			ArrayList<List<Pos>> footPrints) {
		MapSearchFragment mapSearchFragment = (MapSearchFragment) getSupportFragmentManager()
				.findFragmentByTag("MapSearchFragment");

		if (mapSearchFragment != null) {
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
/*	@Override
	public void onCollectionDetailsFragmentShowProducts(
			FedeoRequest request) {
		MapSearchFragment mapSearchFragment = MapSearchFragment.newInstance();
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_details_container,
						mapSearchFragment, "MapSearchFragment")
				.addToBackStack("MapSearchFragment").commit();

		SearchProductsListFragment searchProductsFeedsFragment = SearchProductsListFragment
				.newInstance(request);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_list_container,
						searchProductsFeedsFragment)
				.addToBackStack("SearchProductsListFragment2").commit();

	}*/

	@Override
	public void onCollectionDetailsFragmentShowProducts(String parentID) {
		Intent showProductsIntent = new Intent(this,
				ProductsBrowserActivity.class);
		showProductsIntent.putExtra(Const.KEY_INTENT_PARENT_ID, parentID);
		startActivity(showProductsIntent);
		
	}


}