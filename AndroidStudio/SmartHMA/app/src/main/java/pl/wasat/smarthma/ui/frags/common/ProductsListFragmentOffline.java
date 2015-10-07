package pl.wasat.smarthma.ui.frags.common;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.EntryImagesListAdapter;
import pl.wasat.smarthma.database.FavouritesDbAdapter;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.om.EntryOM;
import pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link ProductsListFragmentOffline#newInstance} factory method to create an
 * instance of this fragment.
 */
public class ProductsListFragmentOffline extends BaseShowProductsListFragment {
    private static final String KEY_PARAM_FEDEO_REQUEST = "pl.wasat.smarthma.KEY_PARAM_FEDEO_REQUEST";

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //populateList();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ZX", "ProductsListFragmentOffline onViewCreated");
        populateList();
    }

    private void populateList() {
        FavouritesDbAdapter dba = new FavouritesDbAdapter(getActivity());
        dba.openToRead();
        entryList = null;
        entryList = dba.getOMEntries();
        dba.close();
        if (entryList == null) {
            entryList = new ArrayList();
            EntryOM testEntry = new EntryOM();
            testEntry.setTitle(getActivity().getString(R.string.empty_list));
            testEntry.setUpdated("");
            testEntry.setIdentifier("");
            testEntry.setId((""));
            testEntry.setDate("");
            testEntry.setPublished("");
            entryList.add(0, testEntry);
        }

        //View view = getView();
        //if (view != null) {

        if (entryList.isEmpty()) {
            //view.setVisibility(View.GONE);
            loadFailureFrag();
        }
        else
        {
            if (entryImagesListView != null)
            {
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

    /*
     * (non-Javadoc)
     *
     * @see pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment#
     * loadProductItemDetails(pl.wasat.smarthma.model.eo.Entry)
     */
    @Override
    protected void loadProductItemDetails(EntryOM entry) {
        ProductDetailsFragment productDetailsFragment = ProductDetailsFragment
                .newInstance(entry);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        productDetailsFragment,
                        "ProductDetailsFragment")
                .addToBackStack("ProductDetailsFragment").commit();

        super.loadProductItemDetails(entry);
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment#
     * loadSearchResultProductsIntroDetailsFrag
     * (pl.wasat.smarthma.model.feed.Feed)
     */
    @Override
    public void loadSearchResultProductsIntroDetailsFrag(Feed searchProductFeeds) {
        Log.d("ZX", "loadSearchResultProductsIntroDetailsFrag");
        FeedSummaryProductsFragment feedSummaryProductsFragment = FeedSummaryProductsFragment
                .newInstance(searchProductFeeds);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_base_details_container,
                        feedSummaryProductsFragment, "FeedSummaryProductsFragment")
                .addToBackStack("FeedSummaryProductsFragment").commit();
        super.loadSearchResultProductsIntroDetailsFrag(searchProductFeeds);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.wasat.smarthma.ui.frags.base.BaseShowProductsListFragment#loadFailureFrag
     * ()
     */
    @Override
    protected void loadFailureFrag() {
        String searchFail = getActivity().getString(
                R.string.empty_list);

        FailureFragment failureFragment = FailureFragment
                .newInstance(searchFail);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_base_list_container, failureFragment)
                .commit();
        super.loadFailureFrag();
    }

}
