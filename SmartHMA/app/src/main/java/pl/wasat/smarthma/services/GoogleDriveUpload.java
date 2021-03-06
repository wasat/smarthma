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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.model.About;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.wasat.smarthma.R;

/**
 * The type Google drive upload.
 */
class GoogleDriveUpload extends AsyncTask<Void, Void, Boolean> {
    /**
     * The Root dir.
     */
    final java.io.File rootDir = Environment.getExternalStorageDirectory();
    private final NotificationCompat.Builder mBuilder;
    private final NotificationManager mNotifyManager;
    private final Context context;
    private com.google.api.services.drive.Drive mService = null;


    /**
     * Instantiates a new Google drive upload.
     *
     * @param credential the credential
     * @param context    the context
     */
    public GoogleDriveUpload(GoogleAccountCredential credential, Context context) {
        super();
        this.context = context;
        //String fileName = "file";
        //java.io.File file = new java.io.File(rootDir + "/smartHMA", fileName);
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        mService = new com.google.api.services.drive.Drive.Builder(
                transport, jsonFactory, credential)
                .setApplicationName("GoogleDriveSample")
                .build();
        mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle(context.getString(R.string.upload))
                .setContentText(context.getResources().getString(R.string.upload_to_google_drive_in_progress))
                .setSmallIcon(R.drawable.actionbar_logo);
        mNotifyManager.notify(1, mBuilder.build());
    }

    /**
     * Background task to call Drive API.
     *
     * @param params no parameters needed for this task.
     */


    @Override
    protected Boolean doInBackground(Void... params) {

        String file_type = "video/mp4"; //write your file type

        java.io.File rootDir = Environment.getExternalStorageDirectory();
        String fileName = "file";
        java.io.File file = new java.io.File(rootDir + "/smartHMA", fileName);

        com.google.api.services.drive.model.File body = new com.google.api.services.drive.model.File();

        File FileRtr;
        body.setTitle(file.getName());
        body.setId(null);
        body.setMimeType(file_type);

        try {
            body.setParents(Collections.singletonList(new ParentReference().setId(getRootId())));
            FileContent mediaContent = new FileContent(file_type, file);
            FileRtr = mService.files().insert(body, mediaContent).execute();

            if (FileRtr != null) {
                System.out.println(context.getString(R.string.file_uploaded) + FileRtr.getTitle());
            }
        } catch (Exception e) {
            Log.e("", e.toString());
            return false;
        }
        return true;
    }

    /**
     * Fetch a list of up to 10 file names and IDs.
     *
     * @return List of Strings describing files, or an empty list if no files
     * found.
     * @throws IOException
     */
    private String getRootId() throws IOException {
        try {
            About about = mService.about().get().execute();
            // Get a list of up to 10 files.
            List<String> fileInfo = new ArrayList<>();
            FileList result = mService.files().list()
                    .setMaxResults(10)
                    .execute();

            List<com.google.api.services.drive.model.File> files = result.getItems();
            if (files != null) {
                for (com.google.api.services.drive.model.File file : files) {
                    fileInfo.add(String.format("%s (%s)\n",
                            file.getTitle(), file.getId()));
                }
            }
            return about.getRootFolderId();
        } catch (UserRecoverableAuthIOException e) {
            Intent userIntent = e.getIntent();
            userIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(userIntent);
        }
        return null;
    }


    @Override
    protected void onPostExecute(Boolean output) {
        if (output) {
            mBuilder.setContentText(context.getResources().getString(R.string.upload_to_google_drive_completed));
            mNotifyManager.notify(1, mBuilder.build());
        }
    }

}

