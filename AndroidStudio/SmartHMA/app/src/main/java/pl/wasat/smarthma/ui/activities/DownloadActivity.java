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
/*    private GoogleAccountCredential mCredential;
    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    private static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String[] SCOPES = {DriveScopes.DRIVE_FILE};
    private static final String ACCESS_KEY_NAME = "ACCESS_KEY";
    private static final String ACCESS_SECRET_NAME = "ACCESS_SECRET";
    private boolean downloadDropbox;
    private boolean mLoggedIn;
    private DropboxAPI<AndroidAuthSession> mApi;*/

    private Button buttonChooseDownload;
    private RadioGroup radioGroup;
    private CloudSavingManager cloudSavingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //downloadDropbox = false;
        cloudSavingManager = new CloudSavingManager(this);
        setContentView(R.layout.activity_download);
        buttonChooseDownload = (Button) findViewById(R.id.buttonChooseDownload);
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
        String meta = "TEST METADTA";
        cloudSavingManager.setCloudSaveParameters(name, url, meta);
    }

    @Override
    public void onResume() {
        super.onResume();
        cloudSavingManager.resumeDropboxService();
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cloudSavingManager.postActivityResult(requestCode, resultCode, data);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

/*
    public void startDownloadDropbox() {
        Toast.makeText(getApplicationContext(),
                "Wybrano Dropbox", Toast.LENGTH_LONG).show();
        AndroidAuthSession session = buildSession();
        mApi = new DropboxAPI<AndroidAuthSession>(session);
        checkAppKeySetup();
        // Display the proper UI state if logged in or not
        setLoggedIn(mApi.getSession().isLinked());

        if (session.authenticationSuccessful()) {
            try {
                // Mandatory call to complete the auth
                session.finishAuthentication();

                // Store it locally in our app for later use
                storeAuth(session);
                setLoggedIn(true);
            } catch (IllegalStateException e) {
                showToast("Couldn't authenticate with Dropbox:" + e.getLocalizedMessage());
            }
        }
        setLoggedIn(mApi.getSession().isLinked());
        // Start the remote authentication
        if (mLoggedIn) {
            downloadDropbox = false;
            Intent downloadIntent = new Intent(getApplication(), DownloadService.class);
            downloadIntent.putExtra("action", 1);
            startService(downloadIntent);
        } else {
            mApi.getSession().startOAuth2Authentication(DownloadActivity.this);
        }
    }

    private void setLoggedIn(boolean loggedIn) {
        mLoggedIn = loggedIn;
    }*/




/*    private void chooseAccount() {
        startActivityForResult(
                mCredential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
    }*/



/*
    private AndroidAuthSession buildSession() {
        AppKeyPair appKeyPair = new AppKeyPair(getString(R.string.APP_KEY), getString(R.string.APP_SECRET));

        AndroidAuthSession session = new AndroidAuthSession(appKeyPair);
        loadAuth(session);
        return session;
    }

    private void storeAuth(AndroidAuthSession session) {
        // Store the OAuth 2 access token, if there is one.
        String oauth2AccessToken = session.getOAuth2AccessToken();
        if (oauth2AccessToken != null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString(ACCESS_KEY_NAME, "oauth2:");
            edit.putString(ACCESS_SECRET_NAME, oauth2AccessToken);
            edit.commit();
            return;
        }
        // Store the OAuth 1 access token, if there is one.  This is only necessary if
        // you're still using OAuth 1.
        AccessTokenPair oauth1AccessToken = session.getAccessTokenPair();
        if (oauth1AccessToken != null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString(ACCESS_KEY_NAME, oauth1AccessToken.key);
            edit.putString(ACCESS_SECRET_NAME, oauth1AccessToken.secret);
            edit.commit();
            return;
        }
    }

    private void showToast(String msg) {
        Toast error = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        error.show();
    }

    private void loadAuth(AndroidAuthSession session) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String key = prefs.getString(ACCESS_KEY_NAME, null);
        String secret = prefs.getString(ACCESS_SECRET_NAME, null);
        if (key == null || secret == null || key.length() == 0 || secret.length() == 0) return;

        if (key.equals("oauth2:")) {
            // If the key is set to "oauth2:", then we can assume the token is for OAuth 2.
            session.setOAuth2AccessToken(secret);
        } else {
            // Still support using old OAuth 1 tokens.
            session.setAccessTokenPair(new AccessTokenPair(key, secret));
        }
    }

    private void checkAppKeySetup() {
        // Check to make sure that we have a valid app key
        if (getString(R.string.APP_KEY).startsWith("CHANGE") ||
                getString(R.string.APP_SECRET).startsWith("CHANGE")) {
            showToast("You must apply for an app key and secret from developers.dropbox.com, and add them to the DBRoulette ap before trying it.");
            finish();
            return;
        }

        // Check if the app has set up its manifest properly.
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        String scheme = "db-" + getString(R.string.APP_KEY);
        String uri = scheme + "://" + AuthActivity.AUTH_VERSION + "/test";
        testIntent.setData(Uri.parse(uri));
        PackageManager pm = getPackageManager();
        if (0 == pm.queryIntentActivities(testIntent, 0).size()) {
            showToast("URL scheme in your app's " +
                    "manifest is not set up correctly. You should have a " +
                    "com.dropbox.client2.android.AuthActivity with the " +
                    "scheme: " + scheme);
            finish();
        }
    }*/


/*
    private boolean isGooglePlayServicesAvailable() {
        final int connectionStatusCode =
                GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (GooglePlayServicesUtil.isUserRecoverableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
            return false;
        } else if (connectionStatusCode != ConnectionResult.SUCCESS) {
            return false;
        }
        return true;
    }

    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode) {
        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
                connectionStatusCode,
                DownloadActivity.this,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }*/
}