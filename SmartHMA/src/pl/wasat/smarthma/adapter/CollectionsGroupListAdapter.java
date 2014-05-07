package pl.wasat.smarthma.adapter;

import java.io.File;

import pl.wasat.smarthma.customviews.CollectionsGroupView;
import pl.wasat.smarthma.model.CollectionsGroup;
import android.content.Context;
import android.view.ViewGroup;

import com.octo.android.robospice.request.okhttp.simple.OkHttpBitmapRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpSpiceArrayAdapter;


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
		return new OkHttpBitmapRequest("http://89.250.194.14/smarthma/img/sat"
				+ group.getId() + ".jpeg", requestImageWidth,
				requestImageHeight, tempFile);

	}

	@Override
	public SpiceListItemView<CollectionsGroup> createView(Context context,
			ViewGroup parent) {
		return new CollectionsGroupView(getContext());
	}
}
