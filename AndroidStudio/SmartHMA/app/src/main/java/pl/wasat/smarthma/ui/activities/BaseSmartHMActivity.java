/**
 *
 */
package pl.wasat.smarthma.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.ui.menus.CommonMenuHandler;
import pl.wasat.smarthma.ui.menus.MenuHandler;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 */
public class BaseSmartHMActivity extends FragmentActivity {

    static final int REQUEST_NEW_SEARCH = 0;
    boolean stopNewSearch = false;
    private MenuHandler commonMenuHandler;

    public BaseSmartHMActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_base_two_panel);
        super.onCreate(savedInstanceState);

        commonMenuHandler = new CommonMenuHandler(this, R.id.menu_button);
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

    boolean dismissMenuOnBackPressed() {
        if (commonMenuHandler.isPopupWindowVisible()) {
            commonMenuHandler.dismissPopupWindow();
            return true;
        }
        return false;
    }
}
