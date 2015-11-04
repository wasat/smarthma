package pl.wasat.smarthma.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.octo.android.robospice.request.okhttp.simple.OkHttpBitmapRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpSpiceArrayAdapter;

import java.io.File;

import pl.wasat.smarthma.customviews.CollectionsGroupView;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.CollectionsGroup;

public class CollectionsGroupListAdapter extends
        OkHttpSpiceArrayAdapter<CollectionsGroup> {

    public OnSlideElementListener listener;

    public CollectionsGroupListAdapter(Context context,
                                       OkHttpBitmapSpiceManager spiceManagerBitmap,
                                       CollectionsGroup.List users, ListView listView) {
        super(context, spiceManagerBitmap, users.getCollectionsGroupList());
    }

    public void setOnClickListener(OnSlideElementListener listener) {
        this.listener = listener;
    }


    @Override
    public OkHttpBitmapRequest createRequest(CollectionsGroup group,
                                             int imageIndex, int requestImageWidth, int requestImageHeight) {
        File tempFile = new File(getContext().getCacheDir(),
                "THUMB_IMAGE_TEMP_" + group.getId());

        //String url = Const.IMG_URL + "eo_coll_gr_0" + mod(group.getId(), 6) + "_72x72.png";
        String url = Const.IMG_URL + "eo_coll_gr_02_72x72.png";

        return new OkHttpBitmapRequest(url, requestImageWidth, requestImageHeight, tempFile);
        //return null;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        SwipeDetector swipeDetector = new SwipeDetector(v, position);
        swipeDetector.setOnClickListener(listener);
        v.setOnTouchListener(swipeDetector);
        return v;
    }


    @Override
    public SpiceListItemView<CollectionsGroup> createView(Context context,
                                                          ViewGroup parent) {
        return new CollectionsGroupView(getContext());
    }


    private int mod(int x, int y) {
        int result = x % y;
        return result < 0 ? result + y : result;
    }
}
