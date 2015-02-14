package pl.wasat.smarthma.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.octo.android.robospice.request.okhttp.simple.OkHttpBitmapRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpSpiceArrayAdapter;

import java.io.File;

import pl.wasat.smarthma.customviews.CollectionsGroupView;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.CollectionsGroup;

public class CollectionsGroupListAdapter extends
        OkHttpSpiceArrayAdapter<CollectionsGroup> {


    // --------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // --------------------------------------------------------------------------------------------

    public CollectionsGroupListAdapter(Context context,
                                       OkHttpBitmapSpiceManager spiceManagerBitmap,
                                       CollectionsGroup.List users) {
        super(context, spiceManagerBitmap, users.getCollectionsGroupList());
    }

    @Override
    public OkHttpBitmapRequest createRequest(CollectionsGroup group,
                                             int imageIndex, int requestImageWidth, int requestImageHeight) {
        File tempFile = new File(getContext().getCacheDir(),
                "THUMB_IMAGE_TEMP_" + group.getId());

        String url = Const.IMG_URL + "sat" + mod(group.getId(), 15)
                + ".jpeg";

        return new OkHttpBitmapRequest(url, requestImageWidth, requestImageHeight, tempFile);

    }

    @Override
    public SpiceListItemView<CollectionsGroup> createView(Context context,
                                                          ViewGroup parent) {
        return new CollectionsGroupView(getContext());
    }

    private int mod(int x, int y) {
        int result = x % 15;
        return result < 0 ? result + 15 : result;
    }
}
