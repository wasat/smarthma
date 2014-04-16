/*package pl.wasat.smarthma.ui.activities;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.fragments.CollectionListFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class SearchResultListActivity extends FragmentActivity {
	
	private CollectionListFragment wmsLayerListFrag;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_main);

		Bundle extras = getIntent().getExtras();

		if (savedInstanceState == null) {
			wmsLayerListFrag = new CollectionListFragment();
			wmsLayerListFrag.setArguments(extras);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.my_list_container, wmsLayerListFrag).commit();
		}
	}

	@Override
	public void onBackPressed() {
		if (wmsLayerListFrag != null) {
			wmsLayerListFrag.onBackPressed();
		}
		super.onBackPressed();
	}

}
*/