package pl.wasat.smarthma.services;


import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.model.About;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.wasat.smarthma.R;

public class GoogleDriveUpload extends AsyncTask<Void, Void, Boolean> {
    private com.google.api.services.drive.Drive mService = null;
    private Exception mLastError = null;
    private File file;
    private NotificationCompat.Builder mBuilder;
    GoogleAccountCredential mCredential;
    java.io.File rootDir = Environment.getExternalStorageDirectory();
    private NotificationManager mNotifyManager;
    private Context context;


    public GoogleDriveUpload(GoogleAccountCredential credential, Context context) {
        super();
        this.context = context;
        String fileName = "file";
        java.io.File file = new java.io.File(rootDir + "/smartHMA", fileName);
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        mService = new com.google.api.services.drive.Drive.Builder(
                transport, jsonFactory, credential)
                .setApplicationName("GoogleDriveSample")
                .build();
        mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle("Upload")
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

        com.google.api.services.drive.model.File FileRtr = null;
        body.setTitle(file.getName());
        body.setId(null);
        body.setMimeType(file_type);

        try {
            body.setParents(Arrays.asList(new ParentReference().setId(getRootId())));
            FileContent mediaContent = new FileContent(file_type, file);
            FileRtr = mService.files().insert(body, mediaContent).execute();

            if (FileRtr != null) {
                System.out.println("File uploaded: " + FileRtr.getTitle());
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
        About about = mService.about().get().execute();
        // Get a list of up to 10 files.
        List<String> fileInfo = new ArrayList<String>();
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
    }


    @Override
    protected void onPostExecute(Boolean output) {
        if (output) {
            mBuilder.setContentText(context.getResources().getString(R.string.upload_to_google_drive_completed));
            mNotifyManager.notify(1, mBuilder.build());
        }
    }

}

