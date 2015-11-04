/**
 *
 */
package pl.wasat.smarthma.ui.activities.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.activities.GlobalSettingsActivity;
import pl.wasat.smarthma.ui.activities.StartActivity;
import pl.wasat.smarthma.ui.menus.CommonMenuHandler;
import pl.wasat.smarthma.ui.menus.MenuHandler;
import pl.wasat.smarthma.utils.text.StringExt;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 */
public class BaseSmartHMActivity extends FragmentActivity {

    protected static final int REQUEST_NEW_SEARCH = 0;
    protected boolean stopNewSearch = false;
    protected MenuHandler commonMenuHandler;
    private FedeoSearchRequestReceiver fedeoSearchRequestReceiver;

    private final IntentFilter filter =
            new IntentFilter(Const.KEY_ACTION_BROADCAST_FEDEO_REQUEST);

    public BaseSmartHMActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_base_two_panel);
        super.onCreate(savedInstanceState);

        commonMenuHandler = new CommonMenuHandler(this, R.id.menu_button);

        fedeoSearchRequestReceiver = new FedeoSearchRequestReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(fedeoSearchRequestReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(fedeoSearchRequestReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent();
                intent.setClass(BaseSmartHMActivity.this, GlobalSettingsActivity.class);
                startActivityForResult(intent, Const.REQUEST_CODE_GLOBAL_SETTINGS);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void doPositiveClick() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(Const.KEY_INTENT_RETURN_STOP_SEARCH, true);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    public void doNegativeClick() {
        Intent i = new Intent(getBaseContext(), StartActivity.class);
        startActivity(i);
        finish();
    }

    protected boolean dismissMenuOnBackPressed() {
        if (commonMenuHandler.isPopupWindowVisible()) {
            commonMenuHandler.dismissPopupWindow();
            return true;
        }
        return false;
    }


    private class FedeoSearchRequestReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            GlobalPreferences globalPreferences = new GlobalPreferences(getApplicationContext());
            if (globalPreferences.getIsDebugMode()) {
                String url = intent.getStringExtra(Const.KEY_INTENT_FEDEO_REQUEST_URL);
                String formattedUrl = StringExt.formatUrl(url);
                SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
                sharedPrefs.setUrlPrefs(formattedUrl);
                Toast.makeText(context, formattedUrl, Toast.LENGTH_LONG).show();
            }
        }
    }
}
