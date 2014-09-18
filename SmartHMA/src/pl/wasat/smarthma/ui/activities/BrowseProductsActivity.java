package pl.wasat.smarthma.ui.activities;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.DataSeriesListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.eo.Pos;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.base.BaseProductDetailsFragment.OnProductDetailSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment.OnBaseShowProductsListFragmentListener;
import pl.wasat.smarthma.ui.frags.browse.BrowseProductsListFragment;
import pl.wasat.smarthma.ui.frags.browse.CollectionsListFragment;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesDetailFragment;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesDetailFragment.OnDataSeriesDetailFragmentInteractionListener;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesListFragment;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesListFragment.OnDataSeriesListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment.OnCollectionDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MapSearchFragment.OnMapSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MetadataFragment.OnMetadataFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment.OnSearchListFragmentListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Window;

import com.google.android.gms.maps.model.LatLngBounds;

public class BrowseProductsActivity extends BaseSmartHMActivity implements
		OnDataSeriesListFragmentListener,
		OnDataSeriesDetailFragmentInteractionListener,
		OnMapSearchFragmentListener, OnSearchListFragmentListener,
		OnBaseShowProductsListFragmentListener,
		OnProductDetailSearchFragmentListener, OnMetadataFragmentListener,
		OnCollectionDetailsFragmentListener {

	private boolean mTwoPane;
	private EoDbAdapter dba;

	public BrowseProductsActivity() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_dataseries_list);

		Intent intent = getIntent();
		String collectionName = intent
				.getStringExtra(CollectionsListFragment.KEY_COLLECTIONS_NAME);
		
		SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
		sharedPrefs.setParentIdPrefs(collectionName);
		
		FedeoRequest fedeoRequest = new FedeoRequest();
		fedeoRequest.buildFromShared(this);

		dba = new EoDbAdapter(this);

		if (findViewById(R.id.dataseries_detail_container) != null) {
			DataSeriesListFragment dsListFragment = DataSeriesListFragment
					.newInstance(fedeoRequest);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.dataseries_list, dsListFragment).commit();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		FragmentManager fm = getSupportFragmentManager();
		int bsec = fm.getBackStackEntryCount();
		if (bsec > 1) {
			while (bsec > 1) {
				fm.popBackStackImmediate();
				bsec = fm.getBackStackEntryCount();
			}
		} else {
			finish();
			super.onBackPressed();
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
		Entry selectedEntry = (Entry) ((DataSeriesListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.dataseries_list)).getListAdapter()
				.getItem(Integer.parseInt(id));

		// mark metadata as read
		dba.openToWrite();
		dba.markAsRead(selectedEntry.getGuid());
		dba.close();
		selectedEntry.setRead(true);
		DataSeriesListAdapter adapter = (DataSeriesListAdapter) ((DataSeriesListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.dataseries_list)).getListAdapter();
		adapter.notifyDataSetChanged();

		// load metadata details to main panel
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putSerializable(Entry.KEY_RSS_ENTRY, selectedEntry);

			DataSeriesDetailFragment dataSeriesDetailFragment = new DataSeriesDetailFragment();
			dataSeriesDetailFragment.setArguments(arguments);
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.dataseries_detail_container,
							dataSeriesDetailFragment,
							"DataSeriesDetailFragment")
					.addToBackStack("DataSeriesDetailFragment").commit();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.fragments.DataSeriesListFragment.
	 * OnDataSeriesListFragmentListener
	 * #onDataSeriesFragmentItemSelected(java.lang.String)
	 */
	@Override
	public void onDataSeriesFragmentItemSelected(String id) {

		FragmentManager fm = getSupportFragmentManager();
		DataSeriesListFragment dataseriesListFragment = (DataSeriesListFragment) fm
				.findFragmentById(R.id.dataseries_list);
		Entry selectedEntry = (Entry) dataseriesListFragment.getListAdapter()
				.getItem(Integer.parseInt(id));

		// mark metadata as read
		dba.openToWrite();
		dba.markAsRead(selectedEntry.getGuid());
		dba.close();
		selectedEntry.setRead(true);
		DataSeriesListAdapter adapter = (DataSeriesListAdapter) ((DataSeriesListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.dataseries_list)).getListAdapter();
		adapter.notifyDataSetChanged();

		CollectionDetailsFragment collectionDetailsFragment = CollectionDetailsFragment
				.newInstance(selectedEntry);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.dataseries_detail_container,
						collectionDetailsFragment, "CollectionDetailsFragment")
				.addToBackStack("CollectionDetailsFragment").commit();

	}

	@Override
	public void onDataSeriesDetailFragmentInteraction() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.fragments.MapSearchFragment.
	 * OnMapSearchFragmentInteractionListener
	 * #onMapSearchFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onMapSearchFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.fragments.MapSearchFragment.
	 * OnMapSearchFragmentInteractionListener
	 * #onMapSearchFragmentBoundsChange(com
	 * .google.android.gms.maps.model.LatLngBounds)
	 */
	@Override
	public void onMapSearchFragmentBoundsChange(LatLngBounds bounds) {

		CollectionDetailsFragment collectionDetailsFragment = (CollectionDetailsFragment) getSupportFragmentManager()
				.findFragmentByTag("CollectionDetailsFragment");

		if (collectionDetailsFragment != null) {
			collectionDetailsFragment.updateAreaBounds(bounds);
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
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wasat.smarthma.ui.frags.search.SearchResultCollectionDetailsFragment
	 * .OnSearchResultCollectionDetailsFragmentListener
	 * #onSearchResultCollectionDetailsFragmentShowProducts
	 * (pl.wasat.smarthma.model.FedeoRequest)
	 */
	@Override
	public void onCollectionDetailsFragmentShowProducts(FedeoRequest request) {
		BrowseProductsListFragment browseProductsListFragment = BrowseProductsListFragment
				.newInstance(request);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.dataseries_list, browseProductsListFragment)
				.commit();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.frags.search.ProductDetailSearchFragment.
	 * OnProductDetailSearchFragmentListener
	 * #onProductDetailSearchFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onProductDetailSearchFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wasat.smarthma.ui.frags.common.MetadataFragment.OnMetadataFragmentListener
	 * #onMetadataFragmentInteraction()
	 */
	@Override
	public void onMetadataFragmentInteraction() {
		// TODO Auto-generated method stub

	}
}
