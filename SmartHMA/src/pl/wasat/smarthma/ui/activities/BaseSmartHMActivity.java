/**
 * 
 */
package pl.wasat.smarthma.ui.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 * 
 */
public class BaseSmartHMActivity extends FragmentActivity {

	public void doPositiveClick() {
		Intent i = new Intent(getBaseContext(), StartActivity.class);
		startActivity(i);
		finish();
	}

	public void doNegativeClick() {
	}
}
