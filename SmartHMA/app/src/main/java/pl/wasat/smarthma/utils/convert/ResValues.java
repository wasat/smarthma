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

package pl.wasat.smarthma.utils.convert;

import android.content.Context;

/**
 * Created by Daniel on 2015-10-03 01:55.
 * Part of the project  SmartHMA
 */
class ResValues {

    /**
     * Px to dp float.
     *
     * @param context the context
     * @param px      the px
     * @return the float
     */
    public static float pxToDp(final Context context, final float px) {
        return (px / context.getResources().getDisplayMetrics().density);
    }

    /**
     * Dp to px float.
     *
     * @param context the context
     * @param dp      the dp
     * @return the float
     */
    public static float dpToPx(final Context context, final float dp) {
        return (dp * context.getResources().getDisplayMetrics().density);
    }
}
