package pl.wasat.smarthma.utils.io;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.widget.Toast;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.utils.text.StringExt;

/**
 * Created by Daniel on 2015-11-07 01:02.
 * Part of the project  SmartHMA_home
 */
public class EODataDownloadManager {

    private Context context;
    private DownloadManager downloadManager;
    private long myDownloadReference;
    private BroadcastReceiver receiverDownloadComplete;
    private BroadcastReceiver receiverNotificationClicked;
    private String productName;

    public EODataDownloadManager(Context context) {
        this.context = context;
    }

    public void startDownload(String productName, String url) {

        try {
            this.productName = productName;
            String fileName = url.substring(url.lastIndexOf('/') + 1);
            // URLUtil.guessFileName(url, null, null)
            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setTitle(context.getString(R.string.eo_dataset) + fileName)
                    .setDescription(String.format(context.getString(R.string.file_is_downloading), fileName));
            //request.setDestinationInExternalPublicDir(Environment.
            //        DIRECTORY_DOWNLOADS, getSubPath(productName, fileName,0));
            //request.setDestinationInExternalFilesDir(context, null, getSubPath(productName, fileName, 1));
            request.setDestinationInExternalPublicDir(getRelativePath(Const.SMARTHMA_PATH_EO_DATA), getSubPath(productName, fileName, 1));
            request.setVisibleInDownloadsUi(true);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
            myDownloadReference = downloadManager.enqueue(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resumeDownloadManager() {
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        initReceiverNotificationClicked();
        initReceiverDownloadComplete();
    }

    private void initReceiverDownloadComplete() {
//        filter for download - on completion
        IntentFilter intentFilter = new IntentFilter(DownloadManager
                .ACTION_DOWNLOAD_COMPLETE);

        receiverDownloadComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long reference = intent.getLongExtra(DownloadManager
                        .EXTRA_DOWNLOAD_ID, -1);
                if (myDownloadReference == reference) {
//                    do something with the download file
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(reference);
                    Cursor cursor = downloadManager.query(query);

                    cursor.moveToFirst();
//                        get the status of the download
                    int columnIndex = cursor.getColumnIndex(DownloadManager
                            .COLUMN_STATUS);
                    int status = cursor.getInt(columnIndex);

                    //int fileNameIndex = cursor.getColumnIndex(DownloadManager
                    //        .COLUMN_LOCAL_FILENAME);
                    //String savedFilePath = cursor.getString(fileNameIndex);
//                        get the reason - more detail on the status
                    int columnReason = cursor.getColumnIndex(DownloadManager
                            .COLUMN_REASON);
                    int reason = cursor.getInt(columnReason);

                    getDownloadStatus(context, status, reason);
                    cursor.close();
                }
            }
        };

        context.registerReceiver(receiverDownloadComplete, intentFilter);
    }

    private void getDownloadStatus(Context context, int status, int reason) {
        switch (status) {
            case DownloadManager.STATUS_SUCCESSFUL:
                Toast.makeText(context,
                        String.format(context.getString(R.string.successful), productName),
                        Toast.LENGTH_LONG).show();
                break;
            case DownloadManager.STATUS_FAILED:
                Toast.makeText(context,
                        context.getString(R.string.failed) + reason,
                        Toast.LENGTH_LONG).show();
                break;
            case DownloadManager.STATUS_PAUSED:
                Toast.makeText(context,
                        context.getString(R.string.paused) + reason,
                        Toast.LENGTH_LONG).show();
                break;
            case DownloadManager.STATUS_PENDING:
                Toast.makeText(context,
                        R.string.pending,
                        Toast.LENGTH_LONG).show();
                break;
            case DownloadManager.STATUS_RUNNING:
                Toast.makeText(context,
                        R.string.running,
                        Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void initReceiverNotificationClicked() {
        IntentFilter filter = new IntentFilter(DownloadManager
                .ACTION_NOTIFICATION_CLICKED);

        receiverNotificationClicked = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String extraId = DownloadManager
                        .EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS;
                long[] references = intent.getLongArrayExtra(extraId);
                for (long reference : references) {
                    if (reference == myDownloadReference) {
//                        do something with the download file
                    }
                }
            }
        };

        context.registerReceiver(receiverNotificationClicked, filter);
    }

    public void unregisterReceivers() {
        context.unregisterReceiver(receiverDownloadComplete);
        context.unregisterReceiver(receiverNotificationClicked);
    }

    @NonNull
    private String getSubPath(String productName, String fileName, int rootType) {
        String pathDir = "";
        String subDir = StringExt.cleanDirName(productName) + "/";
        switch (rootType) {
            case 0:
                subDir = "/" + subDir;
                pathDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + subDir;
                break;
            case 1:
                pathDir = Const.SMARTHMA_PATH_EO_DATA + subDir;
                break;
            default:
                break;
        }
        FilesWriter.validateDir(pathDir);
        return subDir + fileName;
    }

    private String getRelativePath(String path) {
        return path.replaceFirst(android.os.Environment.getExternalStorageDirectory().getAbsolutePath(), "");
    }
}
