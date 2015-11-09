package pl.wasat.smarthma.services;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.ProgressListener;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.exception.DropboxFileSizeException;
import com.dropbox.client2.exception.DropboxIOException;
import com.dropbox.client2.exception.DropboxParseException;
import com.dropbox.client2.exception.DropboxPartialFileException;
import com.dropbox.client2.exception.DropboxServerException;
import com.dropbox.client2.exception.DropboxUnlinkedException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import pl.wasat.smarthma.R;

public class DropboxUpload extends AsyncTask<Void, Long, Boolean> {

    private DropboxAPI<?> mApi;
    private String mPath;
    private File mFile;
    private Context context;
    private long mFileLen;
    private DropboxAPI.UploadRequest mRequest;
    private String mErrorMsg;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotifyManager;
    private static final String ACCESS_KEY_NAME = "ACCESS_KEY";
    private static final String ACCESS_SECRET_NAME = "ACCESS_SECRET";


    public DropboxUpload(Context context, String dropboxPath, File file) {
        super();
        this.context = context;
        mFileLen = file.length();
        mPath = dropboxPath;
        mFile = file;
        buildSession();
    }

    private AndroidAuthSession buildSession() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String secret = prefs.getString(ACCESS_SECRET_NAME, null);
        AppKeyPair appKeyPair = new AppKeyPair(context.getString(R.string.APP_KEY), context.getString(R.string.APP_SECRET));

        AndroidAuthSession session = new AndroidAuthSession(appKeyPair);
        session.setOAuth2AccessToken(secret);
        mApi = new DropboxAPI<AndroidAuthSession>(session);
        loadAuth(session);
        return session;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle("Upload file to dropbox - " + mFile.getName())
                .setContentText("Upload to dropbox in progress. File: " + mFile.getName())
                .setSmallIcon(R.mipmap.ic_launcher_circle);
        mBuilder.setProgress(100, 0, false);
        mNotifyManager.notify(1, mBuilder.build());
    }


    private void loadAuth(AndroidAuthSession session) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String key = prefs.getString(ACCESS_KEY_NAME, null);
        String secret = prefs.getString(ACCESS_SECRET_NAME, null);
        if (key == null || secret == null || key.length() == 0 || secret.length() == 0) return;

        if (key.equals("oauth2:")) {
            session.setOAuth2AccessToken(secret);
        } else {
            // Still support using old OAuth 1 tokens.
            session.setAccessTokenPair(new AccessTokenPair(key, secret));
        }
    }


    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            // By creating a request, we get a handle to the putFile operation,
            // so we can cancel it later if we want to
            FileInputStream fis = new FileInputStream(mFile);
            String path = mPath + mFile.getName();
            //Log.d("UPLOAD_DATA", "NAME: " + mFile.getName() + "; PATH:" + mPath);
            mRequest = mApi.putFileOverwriteRequest(path, fis, mFile.length(),
                    new ProgressListener() {
                        @Override
                        public long progressInterval() {
                            // Update the progress bar every half-second or so
                            return 500;
                        }

                        @Override
                        public void onProgress(long bytes, long total) {
                            publishProgress(bytes);
                        }
                    });

            if (mRequest != null) {
                mRequest.upload();
                Log.d("UPLOAD_DATA", "FILE_PATH:" + mPath + mFile.getName());
                return true;
            }

        } catch (DropboxUnlinkedException e) {
            // This session wasn't authenticated properly or user unlinked
            mErrorMsg = "This app wasn't authenticated properly.";
        } catch (DropboxFileSizeException e) {
            // File size too big to upload via the API
            mErrorMsg = "This file is too big to upload";
        } catch (DropboxPartialFileException e) {
            // We canceled the operation
            mErrorMsg = "Upload canceled";
        } catch (DropboxServerException e) {
            if (e.error == DropboxServerException._401_UNAUTHORIZED) {
            } else if (e.error == DropboxServerException._403_FORBIDDEN) {
                // Not allowed to access this
            } else if (e.error == DropboxServerException._404_NOT_FOUND) {
                // path not found (or if it was the thumbnail, can't be
                // thumbnailed)
            } else if (e.error == DropboxServerException._507_INSUFFICIENT_STORAGE) {
                // user is over quota
            } else {
                // Something else
            }
            // This gets the Dropbox error, translated into the user's language
            mErrorMsg = e.body.userError;
            if (mErrorMsg == null) {
                mErrorMsg = e.body.error;
            }
        } catch (DropboxIOException e) {
            // Happens all the time, probably want to retry automatically.
            mErrorMsg = "Network error.  Try again.";
        } catch (DropboxParseException e) {
            // Probably due to Dropbox server restarting, should retry
            mErrorMsg = "Dropbox error.  Try again.";
        } catch (DropboxException e) {
            // Unknown error
            mErrorMsg = "Unknown error.  Try again.";
        } catch (FileNotFoundException e) {
            Log.e("", e.toString());
        }
        return false;
    }

    @Override
    protected void onProgressUpdate(Long... progress) {
        int percent = (int) (100.0 * (double) progress[0] / mFileLen + 0.5);
        mBuilder.setProgress(100, percent, false);
        mNotifyManager.notify(1, mBuilder.build());
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            //   mFile.delete();
        } else {
        }
        mBuilder.setContentText("Upload file " + mFile.getName() + " to dropbox completed");
        mBuilder.setProgress(0, 0, false);
        mNotifyManager.notify(1, mBuilder.build());
    }
}