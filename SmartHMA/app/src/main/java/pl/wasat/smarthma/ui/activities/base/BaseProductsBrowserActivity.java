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

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.kindle.AmznExtendedMapFragment;
import pl.wasat.smarthma.kindle.AmznExtendedMapFragment.OnAmznExtendedMapFragmentListener;
import pl.wasat.smarthma.model.entry.SimpleMetadata;
import pl.wasat.smarthma.ui.dialogs.FacebookDialogFragment;
import pl.wasat.smarthma.ui.frags.common.ExtendedMapFragment;
import pl.wasat.smarthma.ui.frags.common.ExtendedMapFragment.OnExtendedMapFragmentListener;
import pl.wasat.smarthma.ui.frags.common.MetadataFragment;
import pl.wasat.smarthma.ui.frags.common.ProductDetailsFragment.OnProductDetailsFragmentListener;

/**
 * Activity used to browse ans manage saved products.
 */
public class BaseProductsBrowserActivity extends BaseSmartHMActivity implements
        OnProductDetailsFragmentListener,
        OnExtendedMapFragmentListener, OnAmznExtendedMapFragmentListener {

    //protected Footprint mFootprint;
    //protected String quicklookUrl;
    private ExtendedMapFragment extendedMapFragment;
    private AmznExtendedMapFragment amznExtendedMapFragment;
    private SimpleMetadata simpleMeta;

    @Override
    public void onBackPressed() {
        try {
            if (dismissMenuOnBackPressed()) return;
            FragmentManager fm = getSupportFragmentManager();

            int bsec = fm.getBackStackEntryCount();
            if (bsec == 0) {
                backToCollectionViewWithoutNewSearch();
                super.onBackPressed();
            }

            if (bsec > 0) {
                String bstEntry = fm.getBackStackEntryAt(bsec - 1).getName();
                if (bstEntry.equalsIgnoreCase(MetadataFragment.class.getSimpleName())) {
                    fm.popBackStackImmediate(MetadataFragment.class.getSimpleName(),
                            FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else if (bstEntry.equalsIgnoreCase(ExtendedMapFragment.class.getSimpleName())) {
                    super.onBackPressed();
                } else {
                    bsec = fm.getBackStackEntryCount();
                    if (bsec > 1) {
                        while (bsec > 1) {
                            fm.popBackStackImmediate();
                            bsec = fm.getBackStackEntryCount();
                        }
                    } else {
                        backToCollectionViewWithoutNewSearch();
                        super.onBackPressed();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.onBackPressed();
        }
    }

    private void backToCollectionViewWithoutNewSearch() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(Const.KEY_INTENT_RETURN_STOP_SEARCH, true);
        setResult(Activity.RESULT_OK, resultIntent);
        this.finish();
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
                            ExtendedMapFragment.class.getSimpleName())
                    .addToBackStack(ExtendedMapFragment.class.getSimpleName())
                    .commit();
        } else {

            extendedMapFragment = ExtendedMapFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_base_details_container, extendedMapFragment,
                            ExtendedMapFragment.class.getSimpleName())
                    .addToBackStack(ExtendedMapFragment.class.getSimpleName())
                    .commit();
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
                openInWebBrowser(url);
            }
        }
    }

    private void openInWebBrowser(String url) {
        Intent intentBrowseFiles = new Intent();
        intentBrowseFiles.setAction(Intent.ACTION_VIEW);
        intentBrowseFiles.setData(Uri.parse(url));
        startActivity(intentBrowseFiles);
    }

    @Override
    public void onProductDetailsFragmentShareDialogShow(String url) {
        FacebookDialogFragment dFragment = FacebookDialogFragment.newInstance(url);
        dFragment.show(getSupportFragmentManager(), FacebookDialogFragment.class.getSimpleName());

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

}
