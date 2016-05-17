package pl.wasat.smarthma.ui.frags.browse;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.osdd.OSDDMatcher;
import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.ui.frags.base.BaseCollectionDetailsFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link pl.wasat.smarthma.ui.frags.browse.CollectionEmptyDetailsFragment.OnCollectionEmptyDetailsFragmentListener}
 * interface to handle interaction events. Use the
 * {@link CollectionEmptyDetailsFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class CollectionEmptyDetailsFragment extends
        BaseCollectionDetailsFragment {
    private static final String KEY_COLLECTION_NAME = "pl.wasat.smarthma.KEY_COLLECTION_NAME";

    private OnCollectionEmptyDetailsFragmentListener mListener;

    public CollectionEmptyDetailsFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param collectionName Parameter 1.
     * @return A new instance of fragment CollectionEmptyDetailsFragment.
     */
    public static CollectionEmptyDetailsFragment newInstance(String collectionName) {
        CollectionEmptyDetailsFragment fragment = new CollectionEmptyDetailsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_COLLECTION_NAME, collectionName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnCollectionEmptyDetailsFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnCollectionEmptyDetailsFragmentListener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            collectionName = getArguments().getString(KEY_COLLECTION_NAME);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.wasat.smarthma.ui.frags.base.BaseViewAndBasicSettingsDetailFragment
     * #onCreateView(android.view.LayoutInflater, android.view.ViewGroup,
     * android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        btnShowProducts.setEnabled(true);
        btnShowProducts.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mSlidingLayer.closeLayer(true);
                    if (fedeoRequestParams == null) fedeoRequestParams = new FedeoRequestParams();
                    fedeoRequestParams.setParentIdentifier(collectionName);
                    chooseSearchType();
                }
            }
        });

        btnShowMetadata.setEnabled(false);

        tvParentId.setText(collectionName);
        String osddUrl = Const.OSDD_BASE_URL + "parentIdentifier=" + collectionName;
        startAsyncLoadOsddData(osddUrl);

        return rootView;
    }

    @Override
    public void onRequestSuccess(OpenSearchDescription openSearchDescription) {
        super.onRequestSuccess(openSearchDescription);
        if (osddParamsList.size() == 0)
            loadOsddParameters(openSearchDescription, Link.REL_COLLECTION);
        mSlidingLayer.openLayer(true);
    }

    private void chooseSearchType() {
        switch (type) {
            case OSDDMatcher.PARAM_VALUE_COLLECTION:
                mListener.onCollectionEmptyDetailsFragmentShowCollections(fedeoRequestParams);
                break;
            case OSDDMatcher.PARAM_VALUE_DATASET:
                mListener.onCollectionEmptyDetailsFragmentShowProducts(fedeoRequestParams);
                break;
            default:
                if (collectionName.equalsIgnoreCase("EOP:ESA:FEDEO"))
                    mListener.onCollectionEmptyDetailsFragmentShowCollections(fedeoRequestParams);
                else
                    mListener.onCollectionEmptyDetailsFragmentShowProducts(fedeoRequestParams);
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCollectionEmptyDetailsFragmentListener {

        void onCollectionEmptyDetailsFragmentShowProducts(FedeoRequestParams fedeoRequestParams);

        void onCollectionEmptyDetailsFragmentShowCollections(FedeoRequestParams fedeoRequestParams);

    }
}
