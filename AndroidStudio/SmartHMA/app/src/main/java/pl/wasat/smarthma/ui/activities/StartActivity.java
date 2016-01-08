package pl.wasat.smarthma.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.frags.common.StartFragment;

public class StartActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (savedInstanceState == null) {
            StartFragment startFrag = StartFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, startFrag).commit();
        }
    }
}
