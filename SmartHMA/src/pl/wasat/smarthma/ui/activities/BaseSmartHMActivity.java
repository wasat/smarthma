/**
 * 
 */
package pl.wasat.smarthma.ui.activities;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 * 
 */
public class BaseSmartHMActivity extends FragmentActivity {

	public void doPositiveClick() {
		// Do stuff here.
		Log.i("FragmentAlertDialog", "Positive click!");
	}

	public void doNegativeClick() {
		// Do stuff here.
		Log.i("FragmentAlertDialog", "Negative click!");
	}

}
