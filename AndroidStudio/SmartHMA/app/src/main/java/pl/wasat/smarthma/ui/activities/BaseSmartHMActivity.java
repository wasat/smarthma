/**
 *
 */
package pl.wasat.smarthma.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 */
public class BaseSmartHMActivity extends FragmentActivity {

    static final int REQUEST_NEW_SEARCH = 0;
    boolean stopNewSearch = false;

    public BaseSmartHMActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_base_two_panel);
        super.onCreate(savedInstanceState);
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
}
