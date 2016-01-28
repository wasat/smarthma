package pl.wasat.smarthma.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.enums.CloudType;
import pl.wasat.smarthma.utils.conn.ConnectionDetector;
import pl.wasat.smarthma.utils.io.CloudSavingManager;

public class DownloadActivity extends Activity {
    private RadioGroup radioGroup;
    private CloudSavingManager cloudSavingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cloudSavingManager = new CloudSavingManager(this);
        setContentView(R.layout.activity_download);
        Button buttonChooseDownload = (Button) findViewById(R.id.buttonChooseDownload);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        buttonChooseDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ConnectionDetector detect = new ConnectionDetector(getApplicationContext());
                    if (detect.isWifiConnected()) {
                        CloudType cloudType;
                        if (radioGroup.getCheckedRadioButtonId() == R.id.dropbox) {
                            cloudType = CloudType.DROPBOX;
                        } else {
                            cloudType = CloudType.GOOGLE_DRIVE;
                        }
                        obtainProductSaveData();
                        cloudSavingManager.chooseCloudProvider(cloudType);
                    }
                } catch (Exception e) {
                    Log.e("exception", e.toString());
                }
            }
        });
    }

    private void obtainProductSaveData() {
        String name = "TEST_PRODUCT_NAME";
        String url = "http://67.20.63.5/test.zip";
        String meta = "TEST METADATA";
        cloudSavingManager.setCloudSaveParameters(name, url, meta);
    }

    @Override
    public void onResume() {
        super.onResume();
        cloudSavingManager.resumeDropboxService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_download, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cloudSavingManager.postActivityResult(requestCode, resultCode, data);
    }
}