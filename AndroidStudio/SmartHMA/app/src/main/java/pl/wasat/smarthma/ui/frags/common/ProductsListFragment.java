package pl.wasat.smarthma.ui.frags.common;

import android.os.Bundle;

import pl.wasat.smarthma.model.FedeoRequestParams;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link ProductsListFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class ProductsListFragment extends ProductsListFragmentBase {
    private static final String KEY_PARAM_FEDEO_REQUEST = "pl.wasat.smarthma.KEY_PARAM_FEDEO_REQUEST";

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param fedeoRequestParams Parameter 1.
     * @return A new instance of fragment SearchProductsFeedsFragment.
     */
    public static ProductsListFragment newInstance(
            FedeoRequestParams fedeoRequestParams) {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_PARAM_FEDEO_REQUEST, fedeoRequestParams);
        fragment.setArguments(args);
        return fragment;
    }

    public ProductsListFragment() {
        // Required empty public constructor
    }
}
