package pl.wasat.smarthma.parser.Parser;

import android.content.Context;

/**
 * Created by marcel on 2015-09-07.
 */
public class ParserThread extends Thread {
	private Context mContext;
	public ParserThread(Context context) {
		mContext = context;

	}

	@Override
	public void run() {
		Parser parser = new Parser(mContext);

	}


}

