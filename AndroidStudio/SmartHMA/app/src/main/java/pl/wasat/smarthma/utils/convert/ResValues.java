package pl.wasat.smarthma.utils.convert;

import android.content.Context;

/**
 * Created by Daniel on 2015-10-03 01:55.
 * Part of the project  SmartHMA
 */
public class ResValues {

    public static float pxToDp(final Context context, final float px) {
        return (px / context.getResources().getDisplayMetrics().density);
    }

    public static float dpToPx(final Context context, final float dp) {
        return (dp * context.getResources().getDisplayMetrics().density);
    }
}
