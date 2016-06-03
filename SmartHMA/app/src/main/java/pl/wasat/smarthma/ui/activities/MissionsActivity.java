package pl.wasat.smarthma.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.customviews.ExpandableListAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.mission.MissionItemData;
import pl.wasat.smarthma.parser.Parser.Parser;
import pl.wasat.smarthma.parser.database.ParserDb;
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.activities.base.BaseSmartHMActivity;
import pl.wasat.smarthma.ui.frags.missions.MissionsDetailsFragment;
import pl.wasat.smarthma.ui.frags.missions.MissionsDetailsFragment.OnMissionsDetailNewFragmentListener;
import pl.wasat.smarthma.ui.frags.missions.MissionsExtListFragment;
import pl.wasat.smarthma.ui.frags.missions.MissionsExtListFragment.OnExtendedListFragmentListener;

public class MissionsActivity extends BaseSmartHMActivity implements
        OnExtendedListFragmentListener, OnMissionsDetailNewFragmentListener, ExpandableListAdapter.OnSwipeListItemListener, Parser.OnParserListener {

    private MissionsExtListFragment missionsExtListFragment;
    private ProgressTask progressTask;
    private Handler myHandler;
    private String taskMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = (TextView) findViewById(R.id.action_bar_title);
        text.setText(getString(R.string.esa_missions));

        FrameLayout listLayout = (FrameLayout) findViewById(R.id.activity_base_list_container);
        listLayout.setBackgroundColor(Color.parseColor("#D9D9D9"));
        ParserDb parserDb = new ParserDb(this);
        parserDb.open();

        //jesli jest w bazie 60 misji, to ladujemy fragment
        int count = parserDb.getMissionCount();
        if (parserDb.getMissionCount() == 60 && !isSyncRequired()) {
            missionsExtListFragment = MissionsExtListFragment
                    .newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_base_list_container, missionsExtListFragment,
                            MissionsExtListFragment.class.getSimpleName()).commit();

            String objective = getString(R.string.mission_activity_objective);
            MissionItemData missionObjective = new MissionItemData(0,
                    "ESA Earth Observation Missions",
                    "https://earth.esa.int/web/guest/missions", "", objective);

            MissionsDetailsFragment missionsDetailNewFragment = MissionsDetailsFragment
                    .newInstance(missionObjective);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_base_details_container,
                            missionsDetailNewFragment, MissionsDetailsFragment.class.getSimpleName())
                    .commit();
        } else {
            progressTask = new ProgressTask(this);
            progressTask.execute();
        }
    }

    @Override
    public void onParserItemFinish(int taskCount) {
        progressTask.onProgressUpdate(taskMsg, String.valueOf(taskCount));
    }

    private boolean isSyncRequired() {
        GlobalPreferences globalPreferences = new GlobalPreferences(this);
        long syncPeriod = globalPreferences.getMissionSyncTime() * 3600000;
        SharedPrefs sharedPrefs = new SharedPrefs(this);
        long lastSync = sharedPrefs.getLastSyncPrefs();
        long currTime = System.currentTimeMillis();
        long syncDiff = currTime - lastSync;
        return syncDiff > syncPeriod;
    }

    @Override
    public void onMissionsDetailNewFragmentSearchData(String missionName) {
        sendSearchCollectionIntent(missionName, false);
    }

    private void sendSearchCollectionIntent(String missionName, boolean isPlatformParam) {
        Intent searchCollectionsIntent = new Intent(this,
                SearchCollectionResultsActivity.class);
        searchCollectionsIntent.setAction(Const.KEY_ACTION_SEARCH_MISSION_DATA);
        searchCollectionsIntent.putExtra(Const.KEY_INTENT_MISSION_NAME, missionName);
        searchCollectionsIntent.putExtra(Const.KEY_INTENT_MISSION_PARAM, isPlatformParam);
        startActivityForResult(searchCollectionsIntent, REQUEST_NEW_SEARCH);
    }

    @Override
    public void onSwipeChildItem(boolean swipeRight, String missionName) {
        if (swipeRight) {
            Toast.makeText(this, getString(R.string.start_search_of) + missionName, Toast.LENGTH_SHORT).show();
            sendSearchCollectionIntent(missionName, true);
        } else {
            Toast.makeText(this, getString(R.string.start_search_of) + missionName, Toast.LENGTH_SHORT).show();
            sendSearchCollectionIntent(missionName, false);
        }
    }

    public MissionsExtListFragment getMissionsExtListFragment() {
        return missionsExtListFragment;
    }

    public class ProgressTask extends AsyncTask<String, String, Boolean> {

        /**
         * progress dialog to show user that the backup is processing.
         */
        private final ProgressDialog dialog;
        private final MissionsActivity activity;
        private final ParserDb parserDb;
        private MissionsExtListFragment extendedListFragment;


        public ProgressTask(MissionsActivity activity) {
            this.activity = activity;
            dialog = new ProgressDialog(activity);
            parserDb = new ParserDb(activity);
            parserDb.open();
        }

        protected Boolean doInBackground(final String... args) {
            try {
                Parser parser = new Parser(activity);

                //publishProgress(getString(R.string.loading_esa_eo_missions), "2");
                taskMsg = getString(R.string.loading_esa_eo_missions);
                parser.cat0();

                //publishProgress(getString(R.string.loading_esa_future_missions), "9");
                taskMsg = getString(R.string.loading_esa_future_missions);
                parser.cat1();

                //publishProgress(getString(R.string.loading_third_party_missions), "16");
                taskMsg = getString(R.string.loading_third_party_missions);
                parser.cat2();

                //publishProgress(getString(R.string.loading_historical_missions), "41");
                taskMsg = getString(R.string.loading_historical_missions);
                parser.cat3();

                //publishProgress(getString(R.string.loading_potential_missions), "50");
                taskMsg = getString(R.string.loading_potential_missions);
                parser.cat4();

                //publishProgress(getString(R.string.loading_other_missions), "56");
                taskMsg = getString(R.string.loading_other_missions);
                parser.cat5();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPreExecute() {
            myHandler = new Handler();
            dialog.setMessage(getString(R.string.loading_esa_eo_missions));
            dialog.setMax(58);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            extendedListFragment = MissionsExtListFragment
                    .newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_base_list_container, extendedListFragment,
                            MissionsExtListFragment.class.getSimpleName()).commit();

            String objective = getString(R.string.mission_activity_objective);
            MissionItemData missionObjective = new MissionItemData(0,
                    "ESA Earth Observation Missions",
                    "https://earth.esa.int/web/guest/missions", "", objective);

            MissionsDetailsFragment missionsDetailNewFragment = MissionsDetailsFragment
                    .newInstance(missionObjective);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_base_details_container,
                            missionsDetailNewFragment, MissionsDetailsFragment.class.getSimpleName())
                    .commit();

            parserDb.close();

            SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
            sharedPrefs.setLastSyncPrefs(System.currentTimeMillis());
        }

        @Override
        protected void onProgressUpdate(final String... message) {
            super.onProgressUpdate();
            if (message != null && message.length > 0) {
                myHandler.post(new Runnable() {
                    public void run() {
                        dialog.setMessage(message[0]);
                        dialog.setProgress(Integer.parseInt(message[1]));
                    }
                });
            }
            //dialog.setMessage(message[0]);
            //dialog.setProgress(Integer.parseInt(message[1]));
        }
    }
}
