package pl.wasat.smarthma.parser.Parser;

import android.content.Context;

/**
 * Created by marcel on 2015-09-07 00:09.
 * Part of the project  SmartHMA
 */
class ParserThread extends Thread {
    private final Context mContext;

    public ParserThread(Context context) {
        mContext = context;

    }

    @Override
    public void run() {
        //Parser parser =
        new Parser(mContext);

    }


}

