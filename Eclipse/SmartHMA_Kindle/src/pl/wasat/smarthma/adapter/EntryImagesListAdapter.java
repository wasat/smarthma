package pl.wasat.smarthma.adapter;

import java.io.File;
import java.util.List;

import pl.wasat.smarthma.customviews.EntryItemView;
import pl.wasat.smarthma.model.feed.Entry;
import android.content.Context;
import android.view.ViewGroup;

import com.octo.android.robospice.request.okhttp.simple.OkHttpBitmapRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpSpiceArrayAdapter;

public class EntryImagesListAdapter extends OkHttpSpiceArrayAdapter<Entry> {

	// --------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// --------------------------------------------------------------------------------------------

	public EntryImagesListAdapter(Context context,
			OkHttpBitmapSpiceManager spiceManagerBitmap, List<Entry> entryList) {
		super(context, spiceManagerBitmap, entryList);
	}

	@Override
	public OkHttpBitmapRequest createRequest(Entry entry, int imageIndex,
			int requestImageWidth, int requestImageHeight) {
		File tempFile = new File(getContext().getCacheDir(),
				"THUMB_ENTRY_IMG_TEMP_" + entry.getGuid());

		String url = "http://daliis.spotimage.fr/wsTools/img/getImage.aspx?ST=C&amp;SN=12704197&amp;IT=QK&amp;CP=N&amp;SD=T&amp;FT=BMP&amp;CM=75";
		if (entry.getGroup() != null) {
			for (int i = 0; i < entry.getGroup().getContent().size(); i++) {
				if (entry.getGroup().getContent().get(i).getCategory()
						.get__text().equalsIgnoreCase("THUMBNAIL")) {
					url = entry.getGroup().getContent().get(i).get_url();
				}
			}
		}

		return new OkHttpBitmapRequest(url, requestImageWidth,
				requestImageHeight, tempFile);
	}

	@Override
	public SpiceListItemView<Entry> createView(Context context, ViewGroup parent) {
		return new EntryItemView(getContext());
	}

}
