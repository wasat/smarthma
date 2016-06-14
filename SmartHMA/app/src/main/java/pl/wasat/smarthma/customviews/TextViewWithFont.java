/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wasat.smarthma.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * The type Text view with font.
 */
public class TextViewWithFont extends TextView {
    /**
     * Instantiates a new Text view with font.
     *
     * @param context the context
     */
    public TextViewWithFont(Context context) {
        super(context);
        init();
    }

    /**
     * Init.
     */
    void init() {
        Typeface font = Typeface
                .createFromAsset(getContext().getAssets(), "TitilliumWeb-Regular.ttf");
        setTypeface(font);
    }

    /**
     * Instantiates a new Text view with font.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public TextViewWithFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Instantiates a new Text view with font.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public TextViewWithFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


}
