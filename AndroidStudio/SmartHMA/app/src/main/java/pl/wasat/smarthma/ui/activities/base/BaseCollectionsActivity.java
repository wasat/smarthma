package pl.wasat.smarthma.ui.activities.base;

import android.content.Intent;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.ui.activities.ProductsBrowserActivity;
import pl.wasat.smarthma.ui.activities.SearchCollectionResultsActivity;
import pl.wasat.smarthma.ui.frags.common.MetadataISOFragment;

/**
 * Created by Daniel on 2015-04-27.
 * This file is a part of SmartHMA project.
 */
public class BaseCollectionsActivity extends BaseSmartHMActivity {

    protected void loadIsoMetadataFragment(EntryISO displayedEntry) {
        MetadataISOFragment metadataISOFragment = MetadataISOFragment
                .newInstance(displayedEntry);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        metadataISOFragment, "MetadataISOFragment")
                .addToBackStack("MetadataISOFragment").commit();
    }

    protected void startSearchingProductsProcess(FedeoRequestParams fedeoSearchProductsParams) {
        Intent showProductsIntent = new Intent(this,
                ProductsBrowserActivity.class);
        showProductsIntent.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS, fedeoSearchProductsParams);
        startActivityForResult(showProductsIntent, REQUEST_NEW_SEARCH);
    }

    protected void startSearchingCollectionsProcess(FedeoRequestParams fedeoSearchCollectionsParams) {
        Intent showCollectionsIntent = new Intent(this,
                SearchCollectionResultsActivity.class);
        showCollectionsIntent.setAction(Const.KEY_ACTION_SEARCH_COLLECTIONS);
        showCollectionsIntent.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS, fedeoSearchCollectionsParams);
        startActivityForResult(showCollectionsIntent, REQUEST_NEW_SEARCH);
    }
}
