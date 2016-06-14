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

package pl.wasat.smarthma.ui.activities;

import android.os.Bundle;
import android.widget.TextView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.activities.base.BaseProductsBrowserActivity;
import pl.wasat.smarthma.ui.frags.common.ProductsListFragmentOffline;
import pl.wasat.smarthma.ui.menus.OfflineProductsBrowserMenuHandler;

/**
 * Activity used to browse ans manage saved products.
 */
public class FavouriteProductsActivity extends BaseProductsBrowserActivity {

    private ProductsListFragmentOffline productsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = (TextView) findViewById(R.id.action_bar_title);
        text.setText(getString(R.string.activity_name_favourite_products_results));

        productsListFragment = ProductsListFragmentOffline.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container,
                        productsListFragment).commit();

        commonMenuHandler = new OfflineProductsBrowserMenuHandler(this, R.id.menu_button);
    }

    /**
     * Returns the list fragment associated with this object.
     *
     * @return A list fragment.
     */
    public ProductsListFragmentOffline getProductsListFragment() {
        return productsListFragment;
    }
}
