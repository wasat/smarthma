/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wasat.smarthma.services;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

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

/**
 * The type Dropbox upload.
 */
class DropboxUpload extends AsyncTask<Void, Long, Boolean> {

    private static final String ACCESS_KEY_NAME = "ACCESS_KEY";
    private static final String ACCESS_SECRET_NAME = "ACCESS_SECRET";
    private final String mPath;
    private final File mFile;
    private final Context context;
    private final long mFileLen;
    private DropboxAPI<?> mApi;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotifyManager;

    /**
     * Instantiates a new Dropbox upload.
     *
     * @param context     the context
     * @param dropboxPath the dropbox path
     * @param file        the file
     */
    public DropboxUpload(Context context, String dropboxPath, File file) {
        super();
        this.context = context;
        mFileLen = file.length();
        mPath = dropboxPath;
        mFile = file;
        buildSession();
    }

    private void buildSession() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String secret = prefs.getString(ACCESS_SECRET_NAME, null);
        AppKeyPair appKeyPair = new AppKeyPair(context.getString(R.string.APP_KEY), context.getString(R.string.APP_SECRET));

        AndroidAuthSession session = new AndroidAuthSession(appKeyPair);
        assert secret != null;
        session.setOAuth2AccessToken(secret);
        mApi = new DropboxAPI<>(session);
        loadAuth(session);
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
        String mErrorMsg;
        try {
            // By creating a request, we get a handle to the putFile operation,
            // so we can cancel it later if we want to
            FileInputStream fis = new FileInputStream(mFile);
            String path = mPath + mFile.getName();
            DropboxAPI.UploadRequest mRequest = mApi.putFileOverwriteRequest(path, fis, mFile.length(),
                    new ProgressListener() {
                        @Override
                        public void onProgress(long bytes, long total) {
                            publishProgress(bytes);
                        }

                        @Override
                        public long progressInterval() {
                            // Update the progress bar every half-second or so
                            return 500;
                        }
                    });

            if (mRequest != null) {
                mRequest.upload();
                Log.d("UPLOAD_DATA", "FILE_PATH:" + mPath + mFile.getName());
                return true;
            }

        } catch (DropboxUnlinkedException e) {
            // This session wasn't authenticated properly or user unlinked
            mErrorMsg = context.getString(R.string.this_app_wasnt_auth);
            showToast(mErrorMsg);
        } catch (DropboxFileSizeException e) {
            // File size too big to upload via the API
            mErrorMsg = context.getString(R.string.file_is_too_big);
            showToast(mErrorMsg);
        } catch (DropboxPartialFileException e) {
            // We canceled the operation
            mErrorMsg = context.getString(R.string.upload_canceled);
            showToast(mErrorMsg);
        } catch (DropboxServerException e) {
            if (e.error == DropboxServerException._401_UNAUTHORIZED) {
                showToast(getDropboxServerError(e));
                showToast(e.reason);
            } else if (e.error == DropboxServerException._403_FORBIDDEN) {
                // Not allowed to access this
                showToast(getDropboxServerError(e));
                showToast(e.reason);
            } else if (e.error == DropboxServerException._404_NOT_FOUND) {
                // path not found (or if it was the thumbnail, can't be
                // thumbnailed)
                showToast(getDropboxServerError(e));
                showToast(e.reason);
            } else if (e.error == DropboxServerException._507_INSUFFICIENT_STORAGE) {
                // user is over quota
                showToast(getDropboxServerError(e));
                showToast(e.reason);
            } else {
                // Something else
                showToast(getDropboxServerError(e));
                showToast(e.reason);
            }

        } catch (DropboxIOException e) {
            // Happens all the time, probably want to retry automatically.
            mErrorMsg = context.getString(R.string.network_error);
            showToast(mErrorMsg);
        } catch (DropboxParseException e) {
            // Probably due to Dropbox server restarting, should retry
            mErrorMsg = context.getString(R.string.dropbox_error);
            showToast(mErrorMsg);
        } catch (DropboxException e) {
            // Unknown error
            mErrorMsg = context.getString(R.string.unknown_error);
            showToast(mErrorMsg);
        } catch (FileNotFoundException e) {
            Log.e("", e.toString());
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle(context.getString(R.string.upload_file_to_dropbox) + mFile.getName())
                .setContentText(context.getString(R.string.upload_to_dropbox_in_progress) + mFile.getName())
                .setSmallIcon(R.mipmap.ic_launcher_circle);
        mBuilder.setProgress(100, 0, false);
        mNotifyManager.notify(1, mBuilder.build());
    }

    @Override
    protected void onPostExecute(Boolean result) {
        mBuilder.setContentText(String.format(context.getString(R.string.upload_file_to_dropbox_completed), mFile.getName()));
        mBuilder.setProgress(0, 0, false);
        mNotifyManager.notify(1, mBuilder.build());
    }

    @Override
    protected void onProgressUpdate(Long... progress) {
        int percent = (int) (100.0 * (double) progress[0] / mFileLen + 0.5);
        mBuilder.setProgress(100, percent, false);
        mNotifyManager.notify(1, mBuilder.build());
    }

    private void showToast(String toastMsg) {
        Toast.makeText(context, toastMsg, Toast.LENGTH_LONG).show();
    }

    private String getDropboxServerError(DropboxServerException e) {
        String mErrorMsg;// This gets the Dropbox error, translated into the user's language
        mErrorMsg = e.body.userError;
        if (mErrorMsg == null) {
            mErrorMsg = e.body.error;
        }
        return mErrorMsg;
    }
}