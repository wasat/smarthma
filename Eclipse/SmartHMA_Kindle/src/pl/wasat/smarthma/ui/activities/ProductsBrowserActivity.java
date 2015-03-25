package pl.wasat.smarthma.ui.activities;

import java.util.ArrayList;
import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.eo.Footprint;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment.OnBaseShowProductsListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.ExtendedMapFragment.OnExtendedMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MetadataFragment.OnMetadataFragmentListener;
import pl.wasat.smarthma.ui.frags.common.ProductDetailsFragment.OnProductDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.common.ExtendedMapFragment;
import pl.wasat.smarthma.ui.frags.common.ProductsListFragment;
import pl.wasat.smarthma.ui.frags.dialog.FacebookDialogFragment;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public class ProductsBrowserActivity extends BaseSmartHMActivity implements
		OnProductDetailsFragmentListener, OnMetadataFragmentListener,
		OnExtendedMapFragmentListener, OnBaseShowProductsListFragmentListener {

	private ExtendedMapFragment extendedMapFragment;
	private Footprint mFootprint;
	private String quicklookUrl;

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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		FragmentManager manager = getSupportFragmentManager();
		FacebookDialogFragment facebookDialogFragment = (FacebookDialogFragment) manager
				.findFragmentByTag("FacebookDialogFragment");
		facebookDialogFragment.postOnActivityResult(requestCode, resultCode,
				data);
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
			} else if (bstEntry.equalsIgnoreCase("ExtendedMapFragment")) {
				// fm.popBackStackImmediate();
				super.onBackPressed();
			} else {
				bsec = fm.getBackStackEntryCount();
				if (bsec > 1) {
					while (bsec > 1) {
						fm.popBackStackImmediate();
						bsec = fm.getBackStackEntryCount();
					}
				} else {
					Intent resultIntent = new Intent();
					resultIntent.putExtra(Const.KEY_INTENT_RETURN_STOP_SEARCH,
							true);
					setResult(Activity.RESULT_OK, resultIntent);
					finish();
					super.onBackPressed();
				}
			}
		}
	}

	private void checkMapFragment() {
		try {
			if (extendedMapFragment != null) {
				getSupportFragmentManager().beginTransaction()
						.remove(extendedMapFragment).commit();
			}

		} catch (IllegalStateException e) {
		}
	}

	@Override
	public void onMetadataFragmentInteraction() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProductDetailsFragmentQuicklookShow(String url) {
		Intent intent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("content://media/internal/images/media"));
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse(url), "image/*");
		try {
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Intent intentBrowseFiles = new Intent();
			intentBrowseFiles.setAction(android.content.Intent.ACTION_VIEW);
			intentBrowseFiles.setData(Uri.parse(url));
			startActivity(intentBrowseFiles);
		}
	}

	@Override
	public void onProductDetailsFragmentExtendedMapShow(String url,
			Footprint footprint) {
		quicklookUrl = url;
		mFootprint = footprint;

		checkMapFragment();

		extendedMapFragment = ExtendedMapFragment.newInstance();

		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.activity_base_details_container, extendedMapFragment,
						"ExtendedMapFragment")
				.addToBackStack("ExtendedMapFragment").commit();

	}

	@Override
	public void onProductDetailsFragmentShareDialogShow(String url) {
		FacebookDialogFragment dFragment = FacebookDialogFragment
				.newInstance(url);
		dFragment.show(getSupportFragmentManager(), "FacebookDialogFragment");

	}

	/*
	 * @Override public void onProductDetailsFragmentMapShow(Footprint
	 * footprint) { mFootprint = footprint;
	 * 
	 * checkMapFragment();
	 * 
	 * extendedMapFragment = ExtendedMapFragment.newInstance();
	 * 
	 * getSupportFragmentManager() .beginTransaction()
	 * .add(R.id.activity_base_details_container, extendedMapFragment,
	 * "ExtendedMapFragment") .addToBackStack("ExtendedMapFragment").commit(); }
	 */

	@Override
	public void onMapReady() {
		if (extendedMapFragment != null) {
			extendedMapFragment.showQuicklookOnMap(quicklookUrl, mFootprint);
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
