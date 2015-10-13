package pl.wasat.smarthma.ui.frags.browse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.kindle.AmznAreaPickerMapFragment;
import pl.wasat.smarthma.ui.frags.base.BaseViewAndBasicSettingsDetailFragment;
import pl.wasat.smarthma.ui.frags.common.AreaPickerMapFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the. Use the
 * {@link BrowseCollectionFirstDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BrowseCollectionFirstDetailFragment extends
        BaseViewAndBasicSettingsDetailFragment {

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment BrowseCollectionFirstDetailFragment.
     */
    public static BrowseCollectionFirstDetailFragment newInstance() {
        return new BrowseCollectionFirstDetailFragment();
    }

    public BrowseCollectionFirstDetailFragment() {
        // Required empty public constructor
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.wasat.smarthma.ui.frags.base.MapAndBasicSettingsDetailFragment#
     * onCreateView(android.view.LayoutInflater, android.view.ViewGroup,
     * android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (Const.IS_KINDLE) {
            AmznAreaPickerMapFragment areaPickerMapFragment = AmznAreaPickerMapFragment
                    .newInstance();
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frag_search_res_coll_det_layout_top,
                            areaPickerMapFragment)
                    .addToBackStack("AreaPickerMapFragment").commit();
        } else {
            AreaPickerMapFragment areaPickerMapFragment = AreaPickerMapFragment
                    .newInstance();
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frag_search_res_coll_det_layout_top,
                            areaPickerMapFragment)
                            //.addToBackStack("AreaPickerMapFragment")
                    .commit();
        }

        obtainGlobalSettings();

        changeViewsSizeAndVisibility();

        return rootView;

    }

    private void changeViewsSizeAndVisibility() {
        ViewGroup.LayoutParams btnShowProductParams = btnShowProducts
                .getLayoutParams();
        btnShowProductParams.height = 0;
        btnShowProducts.setLayoutParams(btnShowProductParams);
        btnShowProducts.setVisibility(View.INVISIBLE);

        ViewGroup.LayoutParams btnShowMetaParams = btnShowMetadata
                .getLayoutParams();
        btnShowMetaParams.height = 0;
        btnShowMetadata.setLayoutParams(btnShowMetaParams);
        btnShowMetadata.setVisibility(View.INVISIBLE);

        LinearLayout parentNameArea = (LinearLayout) rootView
                .findViewById(R.id.frag_search_res_coll_det_layout_parent_name);
        ViewGroup.LayoutParams parentNameAreaParams = parentNameArea
                .getLayoutParams();
        parentNameAreaParams.height = 0;
        parentNameArea.setVisibility(View.INVISIBLE);
    }

}
