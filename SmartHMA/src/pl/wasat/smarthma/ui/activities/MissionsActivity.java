package pl.wasat.smarthma.ui.activities;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.mission.MissionItemData;
import pl.wasat.smarthma.ui.frags.missions.MissionsDetailsFragment;
import pl.wasat.smarthma.ui.frags.missions.MissionsDetailsFragment.OnMissionsDetailNewFragmentListener;
import pl.wasat.smarthma.ui.frags.missions.MissionsExtListFragment;
import pl.wasat.smarthma.ui.frags.missions.MissionsExtListFragment.OnExtendedListFragmentListener;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MissionsActivity extends BaseSmartHMActivity implements
		OnExtendedListFragmentListener, OnMissionsDetailNewFragmentListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

/*		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}*/

		FrameLayout listLayout = (FrameLayout) findViewById(R.id.activity_base_list_container);
		listLayout.setBackgroundColor(Color.parseColor("#D9D9D9"));
		
		MissionsExtListFragment extendedListFragment = MissionsExtListFragment
				.newInstance(null, null);

		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.activity_base_list_container, extendedListFragment,
						"ExtendedListFragment").commit();

		String objective = "The objective and continuous views of our planet supplied by satellite images and data provide scientists and decision makers with the information they need to understand and protect our environment. Among their many applications are monitoring the air, seas and land; providing the basis for accurate weather reports; and supplying national and international relief agencies with data when disasters strike";
		MissionItemData missionObjective = new MissionItemData(0,
				"ESA Earth Observation Missions",
				"https://earth.esa.int/web/guest/missions", "", objective);
		MissionsDetailsFragment missionsDetailNewFragment = MissionsDetailsFragment
				.newInstance(missionObjective);
		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.activity_base_details_container,
						missionsDetailNewFragment, "MissionsDetailNewFragment")
				.commit();
	}

	@Override
	public void onExtendedListFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onMissionsDetailNewFragmentSearchData(String missionName) {
		Intent showProductsIntent = new Intent(this,
				SearchCollectionResultsActivity.class);
		showProductsIntent.setAction(Const.KEY_ACTION_SEARCH_MISSION_DATA);
		showProductsIntent.putExtra(Const.KEY_INTENT_QUERY, missionName);
		startActivityForResult(showProductsIntent, REQUEST_NEW_SEARCH);
		
	}

}
