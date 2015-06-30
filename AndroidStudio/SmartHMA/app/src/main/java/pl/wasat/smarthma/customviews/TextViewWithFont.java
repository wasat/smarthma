package pl.wasat.smarthma.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewWithFont extends TextView {
   /*
    private int defaultDimension = 0;
    private int TYPE_BOLD = 1;
    private int TYPE_ITALIC = 2;
    private int FONT_ARIAL = 1;
    private int FONT_OPEN_SANS = 2;
    private int fontType;
    private int fontName;
    */
    public TextViewWithFont(Context context) {
        super(context);
        init(null, 0);
    }
    public TextViewWithFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }
    public TextViewWithFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }
    private void init(AttributeSet attrs, int defStyle) {
            Typeface font = Typeface
                    .createFromAsset(getContext().getAssets(), "TitilliumWeb-Regular.ttf");
            setTypeface(font);

    }

}
