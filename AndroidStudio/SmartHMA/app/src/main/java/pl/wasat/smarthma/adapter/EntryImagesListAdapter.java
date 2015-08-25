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
import pl.wasat.smarthma.model.om.EntryOM;

public class EntryImagesListAdapter extends OkHttpSpiceArrayAdapter<EntryOM> {

    public EntryImagesListAdapter(Context context,
                                  OkHttpBitmapSpiceManager spiceManagerBitmap, List<EntryOM> entryList) {
        super(context, spiceManagerBitmap, entryList);
    }

    @Override
    public OkHttpBitmapRequest createRequest(EntryOM entry, int imageIndex,
                                             int requestImageWidth, int requestImageHeight) {
        File tempFile = new File(getContext().getCacheDir(),
                "THUMB_ENTRY_IMG_TEMP_" + entry.getGuid());

        String url = "http://daliis.spotimage.fr/wsTools/img/getImage.aspx?ST=C&amp;SN=12704197&amp;IT=QK&amp;CP=N&amp;SD=T&amp;FT=BMP&amp;CM=75";
        if (entry.getGroup() != null) {
            for (int i = 0; i < entry.getGroup().getContent().size(); i++) {
                if (entry.getGroup().getContent().get(i).getCategory()
                        .get_text().equalsIgnoreCase("THUMBNAIL")) {
                    url = entry.getGroup().getContent().get(i).get_url();
                }
            }
        }

        return new OkHttpBitmapRequest(url, requestImageWidth,
                requestImageHeight, tempFile);
    }

    @Override
    public SpiceListItemView<EntryOM> createView(Context context, ViewGroup parent) {
        return new EntryItemView(getContext());
    }

}
