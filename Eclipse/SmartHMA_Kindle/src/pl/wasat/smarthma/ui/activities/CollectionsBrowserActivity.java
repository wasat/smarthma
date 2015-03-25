package pl.wasat.smarthma.ui.activities;

import com.amazon.geo.mapsv2.model.LatLngBounds;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.DataSeriesListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.browse.CollectionsListFragment;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesDetailFragment;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesDetailFragment.OnDataSeriesDetailFragmentInteractionListener;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesListFragment;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesListFragment.OnDataSeriesListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment.OnAreaPickerMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment.OnCollectionDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MetadataFragment.OnMetadataFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment.OnSearchListFragmentListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;


public class CollectionsBrowserActivity extends BaseSmartHMActivity implements
		OnDataSeriesListFragmentListener,
		OnDataSeriesDetailFragmentInteractionListener,
		OnAreaPickerMapFragmentListener, OnSearchListFragmentListener,
		OnMetadataFragmentListener,
		OnCollectionDetailsFragmentListener {

	private boolean mTwoPane;
	private EoDbAdapter dba;

	public CollectionsBrowserActivity() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String collectionName = intent
				.getStringExtra(CollectionsListFragment.KEY_COLLECTIONS_NAME);

		SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
		sharedPrefs.setParentIdPrefs(collectionName);
		sharedPrefs.setQueryPrefs("");

		FedeoRequest fedeoRequest = new FedeoRequest();
		fedeoRequest.buildFromShared(this);

		dba = new EoDbAdapter(this);

		if (findViewById(R.id.activity_base_details_container) != null) {
			DataSeriesListFragment dsListFragment = DataSeriesListFragment
					.newInstance(fedeoRequest, stopNewSearch);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.activity_base_list_container, dsListFragment)
					.commit();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			stopNewSearch = data.getBooleanExtra(
					Const.KEY_INTENT_RETURN_STOP_SEARCH, true);
		} else {
			stopNewSearch = false;
		}

		DataSeriesListFragment dtSeriesListFragment = (DataSeriesListFragment) getSupportFragmentManager()
			.findFragmentById(R.id.activity_base_list_container);
		if (dtSeriesListFragment != null) {
			Bundle bundle = dtSeriesListFragment.getArguments();
			bundle.putBoolean(Const.KEY_INTENT_RETURN_STOP_SEARCH,
					stopNewSearch);
		}
		super.onActivityResult(requestCode, resultCode, data);
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
				.findFragmentById(R.id.activity_base_list_container))
				.getListAdapter().getItem(Integer.parseInt(id));

		// mark metadata as read
		dba.openToWrite();
		dba.markAsRead(selectedEntry.getGuid());
		dba.close();
		selectedEntry.setRead(true);
		DataSeriesListAdapter adapter = (DataSeriesListAdapter) ((DataSeriesListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.activity_base_list_container))
				.getListAdapter();
		adapter.notifyDataSetChanged();

		// load metadata details to main panel
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putSerializable(Entry.KEY_RSS_ENTRY, selectedEntry);

			DataSeriesDetailFragment dataSeriesDetailFragment = new DataSeriesDetailFragment();
			dataSeriesDetailFragment.setArguments(arguments);
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.activity_base_details_container,
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
				.findFragmentById(R.id.activity_base_list_container);
		Entry selectedEntry = (Entry) dataseriesListFragment.getListAdapter()
				.getItem(Integer.parseInt(id));

		// mark metadata as read
		dba.openToWrite();
		dba.markAsRead(selectedEntry.getGuid());
		dba.close();
		selectedEntry.setRead(true);
		DataSeriesListAdapter adapter = (DataSeriesListAdapter) ((DataSeriesListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.activity_base_list_container))
				.getListAdapter();
		adapter.notifyDataSetChanged();

		CollectionDetailsFragment collectionDetailsFragment = CollectionDetailsFragment
				.newInstance(selectedEntry);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_details_container,
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
	 * @see pl.wasat.smarthma.ui.fragments.BaseMapFragment.
	 * OnMapFragmentInteractionListener
	 * #onMapFragmentBoundsChange(com
	 * .google.android.gms.maps.model.LatLngBounds)
	 */
	@Override
	public void onMapFragmentBoundsChange(LatLngBounds bounds) {

		CollectionDetailsFragment collectionDetailsFragment = (CollectionDetailsFragment) getSupportFragmentManager()
				.findFragmentByTag("CollectionDetailsFragment");

		if (collectionDetailsFragment != null) {
			collectionDetailsFragment.updateAreaBounds(bounds);
		}

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
	public void onCollectionDetailsFragmentShowProducts(String parentID) {

		Intent showProductsIntent = new Intent(this,
				ProductsBrowserActivity.class);
		showProductsIntent.putExtra(Const.KEY_INTENT_PARENT_ID, parentID);
		startActivityForResult(showProductsIntent, REQUEST_NEW_SEARCH);
		//startActivity(showProductsIntent);

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
