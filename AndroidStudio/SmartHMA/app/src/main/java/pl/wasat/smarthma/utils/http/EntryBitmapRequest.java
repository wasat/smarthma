package pl.wasat.smarthma.utils.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.octo.android.robospice.request.okhttp.simple.OkHttpBitmapRequest;

import java.io.File;

import pl.wasat.smarthma.R;

/**
 * Created by Daniel on 2015-10-22 01:31.
 * Part of the project  SmartHMA_home
 */
public class EntryBitmapRequest extends OkHttpBitmapRequest {
    Context context;

    public EntryBitmapRequest(Context context, String url, int width, int height, File cacheFile) {
        super(url, width, height, cacheFile);
        this.context = context;
    }

    @Override
    public Bitmap loadDataFromNetwork() {

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_square);
        try {
            SSLCertificateHandler.nuke();
            bitmap = super.loadDataFromNetwork();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return bitmap;
    }
}
