package pl.wasat.smarthma.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.mission.MissionItemData;
import pl.wasat.smarthma.ui.frags.missions.MissionsDetailsFragment;
import pl.wasat.smarthma.ui.frags.missions.MissionsDetailsFragment.OnMissionsDetailNewFragmentListener;
import pl.wasat.smarthma.ui.frags.missions.MissionsExtListFragment;
import pl.wasat.smarthma.ui.frags.missions.MissionsExtListFragment.OnExtendedListFragmentListener;

public class MissionsActivity extends BaseSmartHMActivity implements
        OnExtendedListFragmentListener, OnMissionsDetailNewFragmentListener {

    private MissionsExtListFragment extendedListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = (TextView) findViewById(R.id.action_bar_title);
        text.setText("ESA Missions");

        FrameLayout listLayout = (FrameLayout) findViewById(R.id.activity_base_list_container);
        listLayout.setBackgroundColor(Color.parseColor("#D9D9D9"));

        extendedListFragment = MissionsExtListFragment
                .newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_base_list_container, extendedListFragment,
                        "ExtendedListFragment").commit();

        String objective = getString(R.string.mission_activity_objective);
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
    public void onMissionsDetailNewFragmentSearchData(String missionName) {
        Intent showProductsIntent = new Intent(this,
                SearchCollectionResultsActivity.class);
        showProductsIntent.setAction(Const.KEY_ACTION_SEARCH_MISSION_DATA);
        showProductsIntent.putExtra(Const.KEY_INTENT_QUERY, missionName);
        startActivityForResult(showProductsIntent, REQUEST_NEW_SEARCH);

    }

    public MissionsExtListFragment getExtendedListFragment()
    {
        return extendedListFragment;
    }
}
