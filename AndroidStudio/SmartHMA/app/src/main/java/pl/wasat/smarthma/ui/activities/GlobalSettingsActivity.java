package pl.wasat.smarthma.ui.activities;

import android.app.Activity;
import android.os.Bundle;

import pl.wasat.smarthma.ui.frags.common.GlobalSettingsFragment;

public class GlobalSettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new GlobalSettingsFragment()).commit();
    }

}
