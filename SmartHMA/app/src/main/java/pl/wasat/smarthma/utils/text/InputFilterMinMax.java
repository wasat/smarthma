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

package pl.wasat.smarthma.utils.text;

/**
 * Created by Daniel on 2016-06-03.
 * This file is a part of module SmartHMA project.
 */

import android.text.InputFilter;
import android.text.Spanned;

/**
 * The type Input filter min max.
 */
public class InputFilterMinMax implements InputFilter {

    private int min, max;

    /**
     * Instantiates a new Input filter min max.
     *
     * @param min the min
     * @param max the max
     */
    public InputFilterMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Instantiates a new Input filter min max.
     *
     * @param min the min
     * @param max the max
     */
    public InputFilterMinMax(String min, String max) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dStart, int dEnd) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
