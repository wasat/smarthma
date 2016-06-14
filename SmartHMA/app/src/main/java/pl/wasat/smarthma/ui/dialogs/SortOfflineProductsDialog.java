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

package pl.wasat.smarthma.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.helper.DataSorter;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.ui.activities.FavouriteProductsActivity;
import pl.wasat.smarthma.ui.frags.common.ProductsListFragmentOffline;

/**
 * Handles user interaction related to sorting data.
 */
public class SortOfflineProductsDialog extends DialogFragment {
    private FavouriteProductsActivity activity;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.action_sort)
                .setItems(R.array.sorting_modes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            if (which == 0) {
                                SmartHMApplication.sortingType = Const.SORT_BY_TITLE_ASCENDING;
                            } else if (which == 1) {
                                SmartHMApplication.sortingType = Const.SORT_BY_TITLE_DESCENDING;
                            } else if (which == 2) {
                                SmartHMApplication.sortingType = Const.SORT_BY_DATE_ASCENDING;
                            } else if (which == 3) {
                                SmartHMApplication.sortingType = Const.SORT_BY_DATE_DESCENDING;
                            }
                            DataSorter sorter = new DataSorter();
                            ProductsListFragmentOffline productsListFragment = activity.getProductsListFragment();
                            List<Entry> entryList = productsListFragment.getEntryList();
                            sorter.sort(entryList);
                            productsListFragment.refreshList();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    /**
     * Sets activity.
     *
     * @param activity the activity
     */
    public void setActivity(FavouriteProductsActivity activity) {
        this.activity = activity;
    }
}
