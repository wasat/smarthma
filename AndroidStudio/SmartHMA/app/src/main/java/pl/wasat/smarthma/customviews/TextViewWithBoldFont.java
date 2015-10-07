package pl.wasat.smarthma.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Daniel on 2015-07-31 01:16.
 * Part of the project  SmartHMA
 */
public class TextViewWithBoldFont extends TextViewWithFont {
    public TextViewWithBoldFont(Context context) {
        super(context);
    }

    public TextViewWithBoldFont(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewWithBoldFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        Typeface font = Typeface
                .createFromAsset(getContext().getAssets(), "TitilliumWeb-Bold.ttf");
        setTypeface(font);
    }
}
