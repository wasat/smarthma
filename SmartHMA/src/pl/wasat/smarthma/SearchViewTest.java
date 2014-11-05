package pl.wasat.smarthma;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.SearchView;

public class SearchViewTest extends SearchView{

	public SearchViewTest(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.i("SEACHVIEW", "onKeyDown");
		return super.onKeyDown(keyCode, event);
	}
	
	

}
