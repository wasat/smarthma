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
