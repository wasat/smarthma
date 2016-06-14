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

/**
 * Created by Daniel on 2015-07-31 01:16.
 * Part of the project  SmartHMA
 */
public class TextViewWithBoldFont extends TextViewWithFont {
    /**
     * Instantiates a new Text view with bold font.
     *
     * @param context the context
     */
    public TextViewWithBoldFont(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Text view with bold font.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public TextViewWithBoldFont(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Text view with bold font.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
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
