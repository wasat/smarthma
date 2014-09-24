package pl.wasat.smarthma.ui.activities;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.frags.common.MapSearchFragment.OnMapSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchBasicInfoRightFragment;
import pl.wasat.smarthma.ui.frags.search.SearchBasicInfoRightFragment.OnSearchBasicInfoRightFragmentListener;
import pl.wasat.smarthma.ui.frags.search.SearchFragment;
import pl.wasat.smarthma.ui.frags.search.SearchFragment.OnSearchFragmentListener;
import roboguice.util.temp.Ln;
import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.gms.maps.model.LatLngBounds;

public class SearchActivity extends BaseSmartHMActivity implements
		OnSearchBasicInfoRightFragmentListener, OnSearchFragmentListener,
		OnMapSearchFragmentListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Ln.getConfig().setLoggingLevel(Log.ERROR);
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null) {
		}

		if (findViewById(R.id.activity_base_list_container) != null) {

			loadRightPanel();
			loadLeftPanel();

		}
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_eo_map, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_pref1:
			break;
		case R.id.action_pref2:
			break;
		case R.id.action_pref3:
			break;
		case R.id.action_clear_all_settings:
			break;
		case R.id.action_exit:
			moveTaskToBack(true);
			finish();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}



	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		FragmentManager fm = getSupportFragmentManager();
		int bsec = fm.getBackStackEntryCount();
		if (bsec > 0) {
			fm.popBackStack();
		} else {
			finish();
			super.onBackPressed();
		}
	}
	/**
	 * 
	 */
	private void loadRightPanel() {
		SearchBasicInfoRightFragment rightInfoFragment = SearchBasicInfoRightFragment
				.newInstance();
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_list_container, rightInfoFragment,
						"SearchBasicInfoRightFragment")
				.commit();
	}

	private void loadLeftPanel() {
		SearchFragment searchLeftFragment = SearchFragment.newInstance();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.activity_base_details_container, searchLeftFragment)
				.commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wasat.smarthma.ui.frags.search.SearchBasicInfoRightFragment.
	 * OnSearchBasicInfoRightFragmentListener
	 * #onSearchBasicInfoRightFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onSearchBasicInfoRightFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wasat.smarthma.ui.frags.search.SearchFragment.OnSearchFragmentListener
	 * #onSearchFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onSearchFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wasat.smarthma.ui.frags.MapSearchFragment.OnMapSearchFragmentListener
	 * #onMapSearchFragmentInteraction(android.net.Uri)
	 */
	@Override
	public void onMapSearchFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wasat.smarthma.ui.frags.MapSearchFragment.OnMapSearchFragmentListener
	 * #onMapSearchFragmentBoundsChange
	 * (com.google.android.gms.maps.model.LatLngBounds)
	 */
	@Override
	public void onMapSearchFragmentBoundsChange(LatLngBounds bounds) {

		SearchBasicInfoRightFragment searchBasicInfoRightFragment = (SearchBasicInfoRightFragment) getSupportFragmentManager()
				.findFragmentByTag("SearchBasicInfoRightFragment");

		if (searchBasicInfoRightFragment != null) {
			// If article frag is available, we're in two-pane layout...
			// Call a method in the ArticleFragment to update its content
			searchBasicInfoRightFragment.updateCollectionsAreaBounds(bounds);
		}

	}

}
