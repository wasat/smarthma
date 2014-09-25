package pl.wasat.smarthma.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLngBounds;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.eo.Pos;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.base.BaseProductDetailsFragment.OnProductDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment.OnBaseShowProductsListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MapSearchFragment.OnMapSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.common.ProductsListFragment;
import pl.wasat.smarthma.ui.frags.common.MetadataFragment.OnMetadataFragmentListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public class ProductsBrowserActivity extends BaseSmartHMActivity implements
		OnBaseShowProductsListFragmentListener,
		OnProductDetailsFragmentListener, OnMetadataFragmentListener,
		OnMapSearchFragmentListener {

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
			} else {

				bsec = fm.getBackStackEntryCount();

				if (bsec > 1) {
					while (bsec > 1) {
						fm.popBackStackImmediate();
						bsec = fm.getBackStackEntryCount();
					}
				} else {
					finish();
					super.onBackPressed();
				}
			}
		}
	}

	@Override
	public void onBaseShowProductsListFragmentItemSelected(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBaseShowProductsListFragmentFootprintSend(
			ArrayList<List<Pos>> footPrints) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProductDetailsFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMetadataFragmentInteraction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapSearchFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMapSearchFragmentBoundsChange(LatLngBounds bounds) {
		// TODO Auto-generated method stub
		
	}
}
