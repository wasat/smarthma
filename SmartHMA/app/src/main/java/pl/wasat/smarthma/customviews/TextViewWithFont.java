package pl.wasat.smarthma.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewWithFont extends TextView {
    public TextViewWithFont(Context context) {
        super(context);
        init();
    }

    void init() {
        Typeface font = Typeface
                .createFromAsset(getContext().getAssets(), "TitilliumWeb-Regular.ttf");
        setTypeface(font);
    }

    public TextViewWithFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewWithFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


}
