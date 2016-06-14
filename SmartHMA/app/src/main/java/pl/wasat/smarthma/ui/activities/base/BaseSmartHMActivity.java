/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * The type Base smart hm activity.
 *
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 */
public class BaseSmartHMActivity extends FragmentActivity {

    /**
     * The constant REQUEST_NEW_SEARCH.
     */
    protected static final int REQUEST_NEW_SEARCH = 0;
    private final IntentFilter filter =
            new IntentFilter(Const.KEY_ACTION_BROADCAST_FEDEO_REQUEST);
    /**
     * The Stop new search.
     */
    protected boolean stopNewSearch = false;
    /**
     * The Common menu handler.
     */
    protected MenuHandler commonMenuHandler;
    private FedeoSearchRequestReceiver fedeoSearchRequestReceiver;

    /**
     * Instantiates a new Base smart hm activity.
     */
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
    protected void onPause() {
        super.onPause();
        unregisterReceiver(fedeoSearchRequestReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(fedeoSearchRequestReceiver, filter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.REQUEST_CODE_GLOBAL_SETTINGS) {
            GlobalPreferences globalPreferences = new GlobalPreferences(this);
            String fedeoHost = globalPreferences.getFedeoProviderHost();
            Const.setHttpBaseUrl(fedeoHost);
        }
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

    /**
     * Do positive click.
     */
    public void doPositiveClick() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(Const.KEY_INTENT_RETURN_STOP_SEARCH, true);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    /**
     * Do negative click.
     */
    public void doNegativeClick() {
        Intent i = new Intent(getBaseContext(), StartActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Dismiss menu on back pressed boolean.
     *
     * @return the boolean
     */
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
