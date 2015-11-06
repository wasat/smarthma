package pl.wasat.smarthma.utils.io;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.android.AuthActivity;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.drive.DriveScopes;

import java.util.Arrays;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.helper.enums.CloudType;
import pl.wasat.smarthma.services.DownloadService;

/**
 * Created by Daniel on 2015-11-05.
 * This file is a part of module SmartHMA NavIn project.
 */
public class CloudSavingManager {

    private Context context;
    private String urlData;
    private String rawMetadata;
    private String productName;
    private GoogleAccountCredential mCredential;

    private static final int REQUEST_ACCOUNT_PICKER = 1000;
    private static final int REQUEST_AUTHORIZATION = 1001;
    private static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    private static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String[] SCOPES = {DriveScopes.DRIVE_FILE};
    private static final String ACCESS_KEY_NAME = "ACCESS_KEY";
    private static final String ACCESS_SECRET_NAME = "ACCESS_SECRET";

    private boolean downloadDropbox;
    private boolean mLoggedIn;
    private DropboxAPI<AndroidAuthSession> mApi;

    public CloudSavingManager(Context context) {
        this.context = context;

        downloadDropbox = false;
    }

    public void setCloudSaveParameters(String name, String url, String metadata) {
        this.urlData = url;
        this.rawMetadata = metadata;
        this.productName = name;
    }

    public void chooseCloudProvider(CloudType type) {
        switch (type) {
            case GOOGLE_DRIVE:
                startDownloadGoogleDrive();
                break;
            case DROPBOX:
                downloadDropbox = true;
                startDownloadDropbox();
                break;
            default:
                break;
        }
    }


    private void startDownloadGoogleDrive() {
        SharedPreferences settings = ((Activity) context).getPreferences(Context.MODE_PRIVATE);
        mCredential = GoogleAccountCredential.usingOAuth2(
                context, Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff())
                .setSelectedAccountName(settings.getString(PREF_ACCOUNT_NAME, null));
        chooseAccount();
    }

    public void startDownloadDropbox() {
        Toast.makeText(context,
                R.string.you_choose_dropbox, Toast.LENGTH_LONG).show();
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
                showToast(context.getResources().getString(R.string.couldnt_authenticate) + e.getLocalizedMessage());
            }
        }
        setLoggedIn(mApi.getSession().isLinked());
        // Start the remote authentication
        if (mLoggedIn) {
            downloadDropbox = false;
            Intent downloadIntent = new Intent(context, DownloadService.class);
            downloadIntent.putExtra(Const.KEY_ACTION_CLOUD_DOWNLOAD_SERVICE, 1);
            downloadIntent.putExtra(Const.KEY_INTENT_CLOUD_PRODUCT_NAME, productName.toString());
            downloadIntent.putExtra(Const.KEY_INTENT_CLOUD_PRODUCT_URL, urlData.toString());
            downloadIntent.putExtra(Const.KEY_INTENT_CLOUD_PRODUCT_METADATA, rawMetadata.toString());
            context.startService(downloadIntent);
        } else {
            mApi.getSession().startOAuth2Authentication(context);
        }
    }

    private void setLoggedIn(boolean loggedIn) {
        mLoggedIn = loggedIn;
    }

    private void chooseAccount() {
        Activity activity = (Activity) context;
        activity.startActivityForResult(
                mCredential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
    }

    private AndroidAuthSession buildSession() {
        AppKeyPair appKeyPair = new AppKeyPair(context.getString(R.string.APP_KEY), context.getString(R.string.APP_SECRET));

        AndroidAuthSession session = new AndroidAuthSession(appKeyPair);
        loadAuth(session);
        return session;
    }

    private void storeAuth(AndroidAuthSession session) {
        // Store the OAuth 2 access token, if there is one.
        String oauth2AccessToken = session.getOAuth2AccessToken();
        if (oauth2AccessToken != null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString(ACCESS_KEY_NAME, "oauth2:");
            edit.putString(ACCESS_SECRET_NAME, oauth2AccessToken);
            edit.apply();
            return;
        }
        // Store the OAuth 1 access token, if there is one.  This is only necessary if
        // you're still using OAuth 1.
        AccessTokenPair oauth1AccessToken = session.getAccessTokenPair();
        if (oauth1AccessToken != null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString(ACCESS_KEY_NAME, oauth1AccessToken.key);
            edit.putString(ACCESS_SECRET_NAME, oauth1AccessToken.secret);
            edit.apply();
        }
    }

    private void showToast(String msg) {
        Toast error = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        error.show();
    }

    private void loadAuth(AndroidAuthSession session) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
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
        if (context.getString(R.string.APP_KEY).startsWith("CHANGE") ||
                context.getString(R.string.APP_SECRET).startsWith("CHANGE")) {
            showToast(context.getResources().getString(R.string.you_must_apply_for_key));
            //finish();
            return;
        }

        // Check if the app has set up its manifest properly.
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        String scheme = "db-" + context.getString(R.string.APP_KEY);
        String uri = scheme + "://" + AuthActivity.AUTH_VERSION + "/test";
        testIntent.setData(Uri.parse(uri));
        PackageManager pm = context.getPackageManager();
        if (0 == pm.queryIntentActivities(testIntent, 0).size()) {
            showToast(context.getResources().getString(R.string.url_scheme_in_your_apps) + scheme);
        }
    }

    private boolean isGooglePlayServicesAvailable() {
        final int connectionStatusCode =
                GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
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
                (Activity) context,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }


    public void postActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != Activity.RESULT_OK) {
                    isGooglePlayServicesAvailable();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == Activity.RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        mCredential.setSelectedAccountName(accountName);
                        SharedPreferences settings =
                                PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                        Intent downloadIntent = new Intent(context, DownloadService.class);
                        downloadIntent.putExtra(Const.KEY_ACTION_CLOUD_DOWNLOAD_SERVICE, 0);
                        downloadIntent.putExtra(Const.KEY_INTENT_CLOUD_PRODUCT_NAME, productName.toString());
                        downloadIntent.putExtra(Const.KEY_INTENT_CLOUD_PRODUCT_URL, urlData.toString());
                        downloadIntent.putExtra(Const.KEY_INTENT_CLOUD_PRODUCT_METADATA, rawMetadata.toString());
                        context.getApplicationContext().startService(downloadIntent);
                    }
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode != Activity.RESULT_OK) {
                    chooseAccount();
                }
                break;
        }
    }

    public void resumeDropboxService() {
        if (downloadDropbox && mApi != null && mApi.getSession().authenticationSuccessful()) {
            startDownloadDropbox();
        }
    }

}
