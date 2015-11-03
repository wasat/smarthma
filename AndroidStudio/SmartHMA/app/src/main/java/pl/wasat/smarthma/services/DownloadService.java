package pl.wasat.smarthma.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.support.v4.app.NotificationCompat.Builder;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.drive.DriveScopes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import pl.wasat.smarthma.R;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadService extends IntentService {


    private static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String[] SCOPES = { DriveScopes.DRIVE_FILE };
    private Builder mBuilder;
    private int action;
    private NotificationManager mNotifyManager;

    // TODO: Rename parameters
    private static String FILE_URL = "http://67.20.63.5/test.zip";


    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            action = intent.getIntExtra("action", 0);
            downloadFile();
        }
    }

    private void downloadFile()
    {
        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("Download")
                .setContentText("Download in progress")
            .setSmallIcon(R.drawable.actionbar_logo);
        new DownloadFileAsync(action).execute();
    }

    class DownloadFileAsync extends AsyncTask<String, String, Boolean>
    {
        public static final String LOG_TAG = "DOWNLOAD FILE";
        private File rootDir = Environment.getExternalStorageDirectory();
        private String fileName = "file";
        private final String DROPBOX_DIR = "/SMARTHMA/";
        private File file;
        private int action;

        public DownloadFileAsync(int action)
        {
            this.action = action;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mBuilder.setProgress(100, 0, false);
            mNotifyManager.notify(1, mBuilder.build());
        }


        @Override
        protected Boolean doInBackground(String ... aurl)
        {
            try
            {
                StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
                long avaibleBlocks = stat.getAvailableBlocks();
                int blockSizeInBytes = stat.getBlockSize();
                long freeSpaceInMegabytes = avaibleBlocks * (blockSizeInBytes);

                //connecting to url
                URL u = new URL(FILE_URL);
                HttpURLConnection c = (HttpURLConnection) u.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();

                int lenghtOfFile = c.getContentLength();
                if(lenghtOfFile > freeSpaceInMegabytes) return false;
                checkAndCreateDirectory("/smartHMA");
                file = new File(rootDir + "/smartHMA", fileName);
                File file = new File(rootDir + "/smartHMA", fileName);
                FileOutputStream f = new FileOutputStream(file);


                InputStream in = c.getInputStream();

                byte[] buffer = new byte[1024];
                int len1 = 0;
                long total = 0;
                int progress = 0;
                while ((len1 = in.read(buffer)) > 0) {

                    total += len1; //total = total + len1

                    if(progress != (int)((total*100)/lenghtOfFile)) {
                        progress = (int) ((total * 100) / lenghtOfFile);
                        publishProgress("" + progress);
                    }
                    f.write(buffer, 0, len1);
                }
                f.close();

            } catch (Exception e) {
                Log.d(LOG_TAG, e.getMessage());
            }

            return true;
        }

        public void checkAndCreateDirectory(String dirName)
        {
            File new_dir = new File( rootDir + dirName );
            if(!new_dir.exists()) {
                new_dir.mkdirs();
            }
        }


        protected void onProgressUpdate(String ... progress)
        {
            super.onProgressUpdate(progress);
            mBuilder.setProgress(100, Integer.parseInt(progress[0]), false);
            mNotifyManager.notify(1, mBuilder.build());
        }

        @Override
        protected void onPostExecute(Boolean success)
        {
            super.onPostExecute(success);
            if(success) {

                GoogleAccountCredential mCredential;
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(DownloadService.this);

                mCredential = GoogleAccountCredential.usingOAuth2(
                        getApplicationContext(), Arrays.asList(SCOPES))
                        .setBackOff(new ExponentialBackOff())
                        .setSelectedAccountName( settings.getString(PREF_ACCOUNT_NAME, null));
                mBuilder.setContentText(getResources().getString(R.string.download_completed));
                mBuilder.setProgress(0, 0, false);
                mNotifyManager.notify(1, mBuilder.build());

                if(action == 0)
                new GoogleDriveUpload(mCredential, DownloadService.this).execute();
                else new DropboxUpload(DownloadService.this, DROPBOX_DIR, file).execute();
            }
            else
            {
                mBuilder.setContentText(getResources().getString(R.string.not_enough_memory));
                mBuilder.setProgress(0, 0, false);
                mNotifyManager.notify(1, mBuilder.build());
            }
        }
    }
}
