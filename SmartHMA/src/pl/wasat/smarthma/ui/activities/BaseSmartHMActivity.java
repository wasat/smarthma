/**
 * 
 */
package pl.wasat.smarthma.ui.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 * 
 */
public class BaseSmartHMActivity extends FragmentActivity {

	public void doPositiveClick() {
		Intent i = new Intent(getBaseContext(), SearchActivity.class);                      
		startActivity(i);
		Log.i("FragmentAlertDialog", "Positive click!");
		finish();
	}

	public void doNegativeClick() {
		// Do stuff here.
		Log.i("FragmentAlertDialog", "Negative click!");
	}

}
