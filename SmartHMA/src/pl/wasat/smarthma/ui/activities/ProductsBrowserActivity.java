package pl.wasat.smarthma.ui.activities;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.eo.Pos;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.base.BaseProductDetailsFragment.OnProductDetailSearchFragmentListener;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment.OnBaseShowProductsListFragmentListener;
import pl.wasat.smarthma.ui.frags.browse.BrowseProductsListFragment;
import pl.wasat.smarthma.ui.frags.common.MetadataFragment.OnMetadataFragmentListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class ProductsBrowserActivity extends BaseSmartHMActivity implements
		OnBaseShowProductsListFragmentListener,
		OnProductDetailSearchFragmentListener, OnMetadataFragmentListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String parentId = intent.getStringExtra(Const.KEY_INTENT_PARENT_ID);

		SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
		sharedPrefs.setParentIdPrefs(parentId);

		FedeoRequest fedeoRequest = new FedeoRequest();
		fedeoRequest.buildFromShared(this);

		BrowseProductsListFragment browseProductsListFragment = BrowseProductsListFragment
				.newInstance(fedeoRequest);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_list_container,
						browseProductsListFragment).commit();
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
	public void onProductDetailSearchFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMetadataFragmentInteraction() {
		// TODO Auto-generated method stub

	}
}
