package pl.wasat.smarthma.ui.frags;

import java.lang.reflect.Field;

import pl.wasat.smarthma.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GalleryFragment extends Fragment {
	private LinearLayout myGallery;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_gallery, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initUI();
	}

	private void initUI() {
		myGallery = (LinearLayout) getView().findViewById(R.id.mygallery);

		Field[] drawables = R.raw.class.getFields();
		for (Field f : drawables) {
			try {
				String imgPath = "R.raw." + f.getName() + ".jpg";

				int resId = f.getInt(null);
				View photo = insertPhoto(imgPath, resId);
				myGallery.addView(photo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private View insertPhoto(String path, int resId) {
		Bitmap bm = decodeSampledBitmapFromUri(path, resId, 128, 128);

		LinearLayout layout = new LinearLayout(getActivity()
				.getApplicationContext());
		layout.setLayoutParams(new LayoutParams(160, 160));
		layout.setGravity(Gravity.CENTER);

		ImageView imageView = new ImageView(getActivity()
				.getApplicationContext());
		imageView.setLayoutParams(new LayoutParams(128, 128));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setImageBitmap(bm);

		layout.addView(imageView);
		return layout;
	}

	public Bitmap decodeSampledBitmapFromUri(String path, int resId,
			int reqWidth, int reqHeight) {
		Bitmap bm = null;

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// BitmapFactory.decodeFile(path, options);
		BitmapFactory.decodeResource(getResources(), resId);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeResource(getResources(), resId, options);

		return bm;
	}

	public int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}

		return inSampleSize;
	}

}
