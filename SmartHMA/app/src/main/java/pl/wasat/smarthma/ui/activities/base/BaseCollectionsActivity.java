/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    /**
     * Load iso metadata fragment.
     *
     * @param displayedEntry the displayed entry
     */
    protected void loadIsoMetadataFragment(EntryISO displayedEntry) {
        MetadataISOFragment metadataISOFragment = MetadataISOFragment
                .newInstance(displayedEntry);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        metadataISOFragment, MetadataISOFragment.class.getSimpleName())
                .addToBackStack(MetadataISOFragment.class.getSimpleName())
                .commit();
    }

    /**
     * Start searching products process.
     *
     * @param fedeoSearchProductsParams the fedeo search products params
     */
    protected void startSearchingProductsProcess(FedeoRequestParams fedeoSearchProductsParams) {
        Intent showProductsIntent = new Intent(this,
                ProductsBrowserActivity.class);
        showProductsIntent.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS, fedeoSearchProductsParams);
        startActivityForResult(showProductsIntent, REQUEST_NEW_SEARCH);
    }

    /**
     * Start searching collections process.
     *
     * @param fedeoSearchCollectionsParams the fedeo search collections params
     */
    protected void startSearchingCollectionsProcess(FedeoRequestParams fedeoSearchCollectionsParams) {
        Intent showCollectionsIntent = new Intent(this,
                SearchCollectionResultsActivity.class);
        showCollectionsIntent.setAction(Const.KEY_ACTION_SEARCH_COLLECTIONS);
        showCollectionsIntent.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS, fedeoSearchCollectionsParams);
        startActivityForResult(showCollectionsIntent, REQUEST_NEW_SEARCH);
    }
}
