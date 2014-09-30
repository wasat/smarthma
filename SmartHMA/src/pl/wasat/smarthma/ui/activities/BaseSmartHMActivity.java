/**
 * 
 */
package pl.wasat.smarthma.ui.activities;

import pl.wasat.smarthma.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 * 
 */
public class BaseSmartHMActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_base_two_panel);
		super.onCreate(savedInstanceState);
	}

	public void doPositiveClick() {
		finish();
	}

	public void doNegativeClick() {
		Intent i = new Intent(getBaseContext(), StartActivity.class);
		startActivity(i);
		finish();
	}
}
