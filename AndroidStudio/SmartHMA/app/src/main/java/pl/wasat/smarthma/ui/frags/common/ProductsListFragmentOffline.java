package pl.wasat.smarthma.ui.frags.common;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.EntryImagesListAdapter;
import pl.wasat.smarthma.database.FavouritesDbAdapter;
import pl.wasat.smarthma.helper.DataSorter;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.entry.Summary;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link ProductsListFragmentOffline#newInstance} factory method to create an
 * instance of this fragment.
 */
public class ProductsListFragmentOffline extends ProductsListFragmentBase {
    protected Entry testEntry;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment SearchProductsFeedsFragment.
     */
    public static ProductsListFragmentOffline newInstance() {
        ProductsListFragmentOffline fragment = new ProductsListFragmentOffline();
        Bundle args = new Bundle();
        //args.putSerializable(KEY_PARAM_FEDEO_REQUEST, fedeoRequestParams);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ZX", "ProductsListFragmentOffline onViewCreated");
        populateList();
        loadProductItemDetails(entryList.get(0));
    }

    @Override
    public void loadProductItemDetails(Entry entry)
    {
        super.loadProductItemDetails(entry);
        entryList.remove(testEntry);
    }

    private void populateList() {
        FavouritesDbAdapter dba = new FavouritesDbAdapter(getActivity());
        dba.openToRead();
        entryList = null;
        entryList = dba.getOMEntries();
        dba.close();
        if (entryList == null)
        {
            entryList = new ArrayList();
        }
        if (entryList.size() <= 0)
        {
            testEntry = new Entry();
            testEntry.setTitle(getActivity().getString(R.string.empty_list));
            testEntry.setUpdated("");
            testEntry.setIdentifier("");
            testEntry.setId((""));
            testEntry.setDate("");
            testEntry.setPublished("");
            Summary summary = new Summary();
            summary.setCdata("");
            summary.setType("");
            testEntry.setSummary(summary);
            testEntry.setFavourite(true);
            entryList.add(0, testEntry);
        }

        DataSorter sorter = new DataSorter();
        sorter.sort(entryList);

        //View view = getView();
        //if (view != null) {

        if (entryList.isEmpty()) {
            //view.setVisibility(View.GONE);
            loadFailureFrag();
        } else {
            if (entryImagesListView != null) {
                entryImagesListAdapter = new EntryImagesListAdapter(getActivity()
                        .getBaseContext(), getBitmapSpiceManager(), entryList);
                entryImagesListView.setAdapter(entryImagesListAdapter);

                loadingView.setVisibility(View.GONE);
                entryImagesListAdapter.notifyDataSetChanged();
                entryImagesListView.setVisibility(View.VISIBLE);

                // Click event for single list row
                entryImagesListView
                        .setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> parent,
                                                    View view, int position, long id)
                            {
                                loadProductItemDetails(entryList.get(position));
                            }
                        });
            }
        }
        //}
    }

    public ProductsListFragmentOffline() {
        // Required empty public constructor
    }
}
