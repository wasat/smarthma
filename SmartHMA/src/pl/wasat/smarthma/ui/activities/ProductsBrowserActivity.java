package pl.wasat.smarthma.ui.activities;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.eo.Footprint;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment.OnBaseShowProductsListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MapSearchFragment;
import pl.wasat.smarthma.ui.frags.common.MapSearchFragment.OnMapSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MetadataFragment.OnMetadataFragmentListener;
import pl.wasat.smarthma.ui.frags.common.ProductDetailsFragment.OnProductDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.common.ProductsListFragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.google.android.gms.maps.model.LatLngBounds;

public class ProductsBrowserActivity extends BaseSmartHMActivity implements
		OnProductDetailsFragmentListener, OnMetadataFragmentListener,
		OnMapSearchFragmentListener, OnBaseShowProductsListFragmentListener {

	MapSearchFragment mapSearchFragment;
	private Footprint mFootprint;
	private String quicklookUrl;
	private static int QUICKLOOK_MAP_MODE = 1;
	private static int FOOTPRINT_MAP_MODE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String parentId = intent.getStringExtra(Const.KEY_INTENT_PARENT_ID);

		SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
		sharedPrefs.setParentIdPrefs(parentId);

		FedeoRequest fedeoRequest = new FedeoRequest();
		fedeoRequest.buildFromShared(this);

		ProductsListFragment productsListFragment = ProductsListFragment
				.newInstance(fedeoRequest);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_list_container,
						productsListFragment).commit();
	}

	@Override
	public void onBackPressed() {
		FragmentManager fm = getSupportFragmentManager();
		int bsec = fm.getBackStackEntryCount();
		if (bsec > 0) {
			String bstEntry = fm.getBackStackEntryAt(bsec - 1).getName();

			if (bstEntry.equalsIgnoreCase("MetadataFragment")) {
				fm.popBackStackImmediate("MetadataFragment",
						FragmentManager.POP_BACK_STACK_INCLUSIVE);
			} else if (bstEntry.equalsIgnoreCase("MapSearchFragment")) {
				//fm.popBackStackImmediate();
				super.onBackPressed();
			}
			else {
				bsec = fm.getBackStackEntryCount();
				if (bsec > 1) {
					while (bsec > 1) {
						fm.popBackStackImmediate();
						bsec = fm.getBackStackEntryCount();
					}
				} else {
					Intent resultIntent = new Intent();
					resultIntent.putExtra(Const.KEY_INTENT_RETURN_STOP_SEARCH, true);
					setResult(Activity.RESULT_OK, resultIntent);
					finish();
					super.onBackPressed();
				}
			}
		}
	}

	private void checkMapFragment() {
		try {
			if (mapSearchFragment != null) {
				getSupportFragmentManager().beginTransaction()
						.remove(mapSearchFragment).commit();
			}

		} catch (IllegalStateException e) {
		}
	}

	@Override
	public void onMetadataFragmentInteraction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapSearchFragmentBoundsChange(LatLngBounds bounds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProductDetailsFragmentQuicklookShow(String url,
			Footprint footprint) {
		quicklookUrl = url;
		mFootprint = footprint;

		checkMapFragment();

		// mapSearchFragment = MapSearchFragment.newInstance(0);
		mapSearchFragment = MapSearchFragment.newInstance(QUICKLOOK_MAP_MODE);

		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.activity_base_details_container,
						mapSearchFragment, "MapSearchFragment")
				.addToBackStack("MapSearchFragment").commit();

	}

	@Override
	public void onProductDetailsFragmentMapShow(Footprint footprint) {
		mFootprint = footprint;

		checkMapFragment();

		// mapSearchFragment = MapSearchFragment.newInstance(0);
		mapSearchFragment = MapSearchFragment.newInstance(FOOTPRINT_MAP_MODE);

		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.activity_base_details_container, mapSearchFragment,
						"MapSearchFragment")
				.addToBackStack("MapSearchFragment").commit();
	}

	@Override
	public void onMapReady(int mapMode) {
		if (mapSearchFragment != null) {
			switch (mapMode) {
			case 1:
				mapSearchFragment.showQuicklookOnMap(quicklookUrl,
						mFootprint);
				break;
			case 2:
				mapSearchFragment.showFootPrints(mFootprint);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void onProductDetailsFragmentMetadataLoad() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBaseShowProductsListFragmentItemSelected(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBaseShowProductsListFragmentFootprintSend(
			ArrayList<Footprint> footPrints) {
		// TODO Auto-generated method stub

	}

}
