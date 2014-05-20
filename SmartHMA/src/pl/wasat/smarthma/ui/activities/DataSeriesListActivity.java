package pl.wasat.smarthma.ui.activities;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.DataSeriesListAdapter;
import pl.wasat.smarthma.database.DbAdapter;
import pl.wasat.smarthma.model.dataseries.Entry;
import pl.wasat.smarthma.ui.fragments.CollectionsListFragment;
import pl.wasat.smarthma.ui.fragments.DataSeriesDetailFragment;
import pl.wasat.smarthma.ui.fragments.DataSeriesDetailFragment.OnDataSeriesDetailFragmentInteractionListener;
import pl.wasat.smarthma.ui.fragments.DataSeriesListFragment;
import pl.wasat.smarthma.ui.fragments.DataSeriesListFragment.OnDataSeriesListFragmentInteractionListener;
import pl.wasat.smarthma.ui.fragments.FailureFragment.OnFailureFragmentInteractionListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;

public class DataSeriesListActivity extends FragmentActivity implements
		OnDataSeriesListFragmentInteractionListener,
		OnDataSeriesDetailFragmentInteractionListener,
		OnFailureFragmentInteractionListener,
		DataSeriesListFragment.Callbacks {

	private boolean mTwoPane;
	private DbAdapter dba;

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

		dba = new DbAdapter(this);

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

	@Override
	public void onItemSelected(String id) {
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
		Log.e("CHANGE", "Changing to read: ");

		// load metadata details to main panel
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putSerializable(Entry.KEY_RSS_ENTRY, selectedEntry);

			DataSeriesDetailFragment fragment = new DataSeriesDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.dataseries_detail_container, fragment)
					.commit();

		} else {
			Intent detailIntent = new Intent(this,
					DataSeriesDetailActivity.class);
			detailIntent.putExtra(DataSeriesDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}

	@Override
	public void onDataSeriesDetailFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDataSeriesFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailureFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub
		
	}
}
