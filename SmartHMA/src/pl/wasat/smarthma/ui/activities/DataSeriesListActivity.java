package pl.wasat.smarthma.ui.activities;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.DataSeriesListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.model.eo.Pos;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.ui.frags.FailureFragment.OnFailureFragmentListener;
import pl.wasat.smarthma.ui.frags.MapSearchFragment.OnMapSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.browse.BrowseDataSeriesIntroFragment.OnBrowseDataSeriesIntroFragmentListener;
import pl.wasat.smarthma.ui.frags.browse.CollectionsListFragment;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesDetailFragment;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesDetailFragment.OnDataSeriesDetailFragmentInteractionListener;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesListFragment;
import pl.wasat.smarthma.ui.frags.browse.DataSeriesListFragment.OnDataSeriesListFragmentListener;
import pl.wasat.smarthma.ui.frags.search.CollectionItemRightFragment;
import pl.wasat.smarthma.ui.frags.search.CollectionItemRightFragment.OnCollectionItemRightFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchListFragment.OnSearchListFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchProductsListFragment.OnSearchProductsListFragmentListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;

import com.google.android.gms.maps.model.LatLngBounds;

public class DataSeriesListActivity extends BaseSmartHMActivity implements
		OnDataSeriesListFragmentListener,
		OnDataSeriesDetailFragmentInteractionListener,
		OnFailureFragmentListener,
		OnMapSearchFragmentListener,
		OnCollectionItemRightFragmentListener,
		OnSearchListFragmentListener,
		OnSearchProductsListFragmentListener, OnBrowseDataSeriesIntroFragmentListener {

	private boolean mTwoPane;
	private EoDbAdapter dba;

	public DataSeriesListActivity() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_dataseries_list);

		Intent intent = getIntent();
		String message = intent
				.getStringExtra(CollectionsListFragment.KEY_COLLECTIONS_NAME);

		dba = new EoDbAdapter(this);

		if (findViewById(R.id.dataseries_detail_container) != null) {
			mTwoPane = true;

//			((DataSeriesListFragment) getSupportFragmentManager()
//					.findFragmentById(R.id.dataseries_list))
//					.setActivateOnItemClick(true);

			DataSeriesListFragment dsListFragment = DataSeriesListFragment
					.newInstance(message);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.dataseries_list, dsListFragment).commit();
		}
	}
	
	/* (non-Javadoc)
	 * @see pl.wasat.smarthma.ui.fragments.SearchListFragment.OnSearchListFragmentListener#onSearchListFragmentItemSelected(java.lang.String)
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

			DataSeriesDetailFragment fragment = new DataSeriesDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.dataseries_detail_container, fragment)
					.commit();
		} 
	}
	
	@Override
	public void onDataSeriesDetailFragmentInteraction() {
		// TODO Auto-generated method stub

	}


	@Override
	public void onFailureFragmentInteraction() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see pl.wasat.smarthma.ui.fragments.MapSearchFragment.OnMapSearchFragmentInteractionListener#onMapSearchFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onMapSearchFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see pl.wasat.smarthma.ui.fragments.MapSearchFragment.OnMapSearchFragmentInteractionListener#onMapSearchFragmentBoundsChange(com.google.android.gms.maps.model.LatLngBounds)
	 */
	@Override
	public void onMapSearchFragmentBoundsChange(LatLngBounds bounds) {
		CollectionItemRightFragment collRightFrag = (CollectionItemRightFragment) getSupportFragmentManager()
				.findFragmentByTag("CollectionItemRightFragment");

		if (collRightFrag != null) {
			// If article frag is available, we're in two-pane layout...

			// Call a method in the ArticleFragment to update its content
			collRightFrag.updateProductAreaBounds(bounds);
		}
		
	}

	/* (non-Javadoc)
	 * @see pl.wasat.smarthma.ui.fragments.CollectionItemRightFragment.OnCollectionItemRightFragmentInteractionListener#onCollectionItemRightFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onCollectionItemRightFragmentInteraction() {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see pl.wasat.smarthma.ui.fragments.DataSeriesListFragment.OnDataSeriesListFragmentListener#onDataSeriesFragmentItemSelected(java.lang.String)
	 */
	@Override
	public void onDataSeriesFragmentItemSelected(String id) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see pl.wasat.smarthma.ui.fragments.SearchProductsFeedsFragment.OnSearchProductsFeedFragmentListener#onSearchProductsFeedFragmentItemSelected(java.lang.String)
	 */
	@Override
	public void onSearchProductsListFragmentItemSelected(String id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see pl.wasat.smarthma.ui.frags.search.SearchProductsListFragment.OnSearchProductsListFragmentListener#onSearchProductsListFragmentFootprintSend(java.util.ArrayList)
	 */
	@Override
	public void onSearchProductsListFragmentFootprintSend(
			ArrayList<List<Pos>> footPrints) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see pl.wasat.smarthma.ui.frags.browse.BrowseDataSeriesIntroFragment.OnBrowseDataSeriesIntroFragmentListener#onBrowseDataSeriesIntroFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onBrowseDataSeriesIntroFragmentInteraction() {
		// TODO Auto-generated method stub
		
	}
}
