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

package pl.wasat.smarthma.utils.io;

import android.text.InputType;
import android.text.method.NumberKeyListener;

/**
 * Created by Daniel on 2016-05-31.
 * This file is a part of module SmartHMA project.
 */
public class PatternNumberKeyListener extends NumberKeyListener {
    private final char[] chAccepted_chars = "0123456789.,[]{},"
            .toCharArray();

    @Override
    protected char[] getAcceptedChars() {
        return chAccepted_chars;
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_TEXT;
    }
}
