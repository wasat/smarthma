package pl.wasat.smarthma.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.octo.android.robospice.request.okhttp.simple.OkHttpBitmapRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpSpiceArrayAdapter;

import java.io.File;
import java.util.List;

import pl.wasat.smarthma.customviews.EntryItemView;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.utils.request.EntryBitmapRequest;

public class EntryImagesListAdapter extends OkHttpSpiceArrayAdapter<Entry> {

    private static final String THUMB_ENTRY_IMG_TEMP = "THUMB_ENTRY_IMG_TEMP_";
    private static final String TEMP_DEFAULT_IMG_URL = "http://acsspace.acsys.it/joomla/images/stories/slideshow/toscanamer_fr_20100628.jpg";
    private OnSlideElementListener listener;

    public EntryImagesListAdapter(Context context,
                                  OkHttpBitmapSpiceManager spiceManagerBitmap, List<Entry> entryList) {
        super(context, spiceManagerBitmap, entryList);
    }

    @Override
    public SpiceListItemView<Entry> createView(Context context, ViewGroup parent) {
        return new EntryItemView(getContext(), listener);
    }

    @Override
    public OkHttpBitmapRequest createRequest(Entry entry, int imageIndex,
                                             int requestImageWidth, int requestImageHeight) {
        String tmpFileName = THUMB_ENTRY_IMG_TEMP;
        String url = TEMP_DEFAULT_IMG_URL;
        File tempFile = null;
        try {
            tmpFileName = tmpFileName + entry.getTitle().replaceAll("/", "").replaceAll(":", "_");
            tempFile = new File(getContext().getCacheDir(), tmpFileName);
            if (entry.getSimpleMetadata().getThumbnailUrl() == null) {
                url = TEMP_DEFAULT_IMG_URL;
            } else if (!entry.getSimpleMetadata().getThumbnailUrl().isEmpty())
                url = entry.getSimpleMetadata().getThumbnailUrl();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return new EntryBitmapRequest(getContext(), url, requestImageWidth,
                requestImageHeight, tempFile);
    }

    public void setListener(OnSlideElementListener listener) {
        this.listener = listener;
    }


}
