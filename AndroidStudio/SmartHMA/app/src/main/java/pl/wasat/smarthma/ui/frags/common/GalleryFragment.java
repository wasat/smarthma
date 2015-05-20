/*
package pl.wasat.smarthma.ui.frags.common;

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

import java.lang.reflect.Field;

import pl.wasat.smarthma.R;

public class GalleryFragment extends Fragment {

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
        View view = getView();
        if (view != null) {
            LinearLayout myGallery = (LinearLayout) view.findViewById(R.id.mygallery);

            Field[] drawables = R.raw.class.getFields();
            for (Field f : drawables) {
                try {
                    int resId = f.getInt(null);
                    View photo = insertPhoto(resId);
                    myGallery.addView(photo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private View insertPhoto(int resId) {
        Bitmap bm = decodeSampledBitmapFromUri(resId);

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

    private Bitmap decodeSampledBitmapFromUri(int resId) {
        Bitmap bm;

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // BitmapFactory.decodeFile(path, options);
        BitmapFactory.decodeResource(getResources(), resId);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options
        );

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeResource(getResources(), resId, options);

        return bm;
    }

    private int calculateInSampleSize(BitmapFactory.Options options) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > 128 || width > 128) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) 128);
            } else {
                inSampleSize = Math.round((float) width / (float) 128);
            }
        }

        return inSampleSize;
    }

}
*/
