package pl.wasat.smarthma.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.kindle.AmznExtendedMapFragment;
import pl.wasat.smarthma.kindle.AmznExtendedMapFragment.OnAmznExtendedMapFragmentListener;
import pl.wasat.smarthma.model.entry.SimpleMetadata;
import pl.wasat.smarthma.model.om.Footprint;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment.OnBaseShowProductsListFragmentListener;
import pl.wasat.smarthma.ui.frags.common.ExtendedMapFragment;
import pl.wasat.smarthma.ui.frags.common.ExtendedMapFragment.OnExtendedMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.ProductDetailsFragment.OnProductDetailsFragmentListener;
import pl.wasat.smarthma.ui.frags.common.ProductsListFragmentOffline;
import pl.wasat.smarthma.ui.frags.dialog.FacebookDialogFragment;
import pl.wasat.smarthma.ui.menus.OfflineProductsBrowserMenuHandler;

/**
 * Activity used to browse ans manage saved products.
 */
public class FavouriteProductsActivity extends BaseSmartHMActivity implements
        OnProductDetailsFragmentListener,
        OnExtendedMapFragmentListener, OnAmznExtendedMapFragmentListener, OnBaseShowProductsListFragmentListener {

    private ExtendedMapFragment extendedMapFragment;
    private AmznExtendedMapFragment amznExtendedMapFragment;
    private Footprint mFootprint;
    private String quicklookUrl;
    private SimpleMetadata simpleMeta;
    private ProductsListFragmentOffline productsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        TextView text = (TextView) findViewById(R.id.action_bar_title);
        text.setText(getString(R.string.activity_name_favourite_products_results));

        //FedeoRequestParams fedeoRequestParams = (FedeoRequestParams) intent.getSerializableExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS);

        productsListFragment = ProductsListFragmentOffline.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container,
                        productsListFragment).commit();

        commonMenuHandler = new OfflineProductsBrowserMenuHandler(this, R.id.menu_button);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        try {
            if (dismissMenuOnBackPressed()) return;
            FragmentManager fm = getSupportFragmentManager();

            int bsec = fm.getBackStackEntryCount();

            if (bsec > 0) {
                String bstEntry = fm.getBackStackEntryAt(bsec - 1).getName();
                if (bstEntry.equalsIgnoreCase("MetadataFragment")) {
                    fm.popBackStackImmediate("MetadataFragment",
                            FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else if (bstEntry.equalsIgnoreCase("ExtendedMapFragment")) {
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
        } catch (Exception e) {
            e.printStackTrace();
            super.onBackPressed();
        }
    }

    private void checkMapFragment() {
        try {
            if (!Const.IS_KINDLE && extendedMapFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .remove(extendedMapFragment).commit();
            }
            if (Const.IS_KINDLE && amznExtendedMapFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .remove(amznExtendedMapFragment).commit();
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onProductDetailsFragmentQuicklookShow(String url) {
        boolean isGalleryAvailable = true;
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("content://media/internal/images/media"));
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), "image/*");
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            isGalleryAvailable = false;
        } finally {
            if (!isGalleryAvailable) {
                Intent intentBrowseFiles = new Intent();
                intentBrowseFiles.setAction(android.content.Intent.ACTION_VIEW);
                intentBrowseFiles.setData(Uri.parse(url));
                startActivity(intentBrowseFiles);
            }
        }
    }

    @Override
    public void onProductDetailsFragmentExtendedMapShow(SimpleMetadata simpleMetadata) {
        //quicklookUrl = url;
        //mFootprint = footprint;
        simpleMeta = simpleMetadata;

        checkMapFragment();

        if (Const.IS_KINDLE) {
            amznExtendedMapFragment = AmznExtendedMapFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_base_details_container, amznExtendedMapFragment,
                            "ExtendedMapFragment")
                    .addToBackStack("ExtendedMapFragment").commit();
        } else {

            extendedMapFragment = ExtendedMapFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_base_details_container, extendedMapFragment,
                            "ExtendedMapFragment")
                    .addToBackStack("ExtendedMapFragment").commit();
        }

    }

    @Override
    public void onProductDetailsFragmentShareDialogShow(String url) {
        FacebookDialogFragment dFragment = FacebookDialogFragment.newInstance(url);
        dFragment.show(getSupportFragmentManager(), "FacebookDialogFragment");

    }

    @Override
    public void onMapReady() {
        if (extendedMapFragment != null) {
            extendedMapFragment.showQuicklookOnMap(simpleMeta);
        }
    }

    @Override
    public void onAmznMapReady() {
        if (amznExtendedMapFragment != null) {
            amznExtendedMapFragment.showQuicklookOnMap(simpleMeta);
        }
    }

    @Override
    public void onBaseShowProductsListFragmentFootprintSend() {
    }

    /**
     * Returns the list fragment associated with this object.
     * @return A list fragment.
     */
    public ProductsListFragmentOffline getProductsListFragment() {
        return productsListFragment;
    }
}
