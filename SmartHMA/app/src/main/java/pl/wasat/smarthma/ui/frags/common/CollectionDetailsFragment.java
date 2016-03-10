package pl.wasat.smarthma.ui.frags.common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.model.osdd.OSDDMatcher;
import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.model.osdd.Parameter;
import pl.wasat.smarthma.ui.dialogs.LoginDialogFragment;
import pl.wasat.smarthma.ui.frags.base.BaseCollectionDetailsFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link CollectionDetailsFragment.OnCollectionDetailsFragmentListener}
 * interface to handle interaction events. Use the
 * {@link CollectionDetailsFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class CollectionDetailsFragment extends
        BaseCollectionDetailsFragment {

    private OnCollectionDetailsFragmentListener mListener;

    public CollectionDetailsFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param collectionEntry Parameter 1.
     * @return A new instance of fragment CollectionDetailsFragment.
     */
    public static CollectionDetailsFragment newInstance(EntryISO collectionEntry) {
        CollectionDetailsFragment fragment = new CollectionDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_COLLECTION_ENTRY, collectionEntry);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnCollectionDetailsFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnCollectionDetailsFragmentListener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (displayedISOEntry.getSpecLink(Link.REL_SEARCH, Link.TYPE_OSDD_XML).isEmpty())
            btnShowProducts.setEnabled(false);

        btnShowProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoginRequired()) {
                    mSlidingLayer.openLayer(true);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    LoginDialogFragment loginDialogFragment = LoginDialogFragment.newInstance();
                    loginDialogFragment.show(fm, LoginDialogFragment.class.getSimpleName());
                } else startProductSearch();
            }
        });

        btnShowMetadata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCollectionDetailsFragmentShowMetadata(displayedISOEntry);
            }
        });
        startAsyncLoadOsddData();
        return rootView;
    }

    public void sendLoginValues(String login, String password) {
        //login = "smarthma";
        //password = "Space2014!";
        fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_KEY_USERNAME, login);
        fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_KEY_PASSWORD, password);
        startProductSearch();
    }

    private void startProductSearch() {
        if (mListener != null) {
            mSlidingLayer.closeLayer(true);
            mListener.onCollectionDetailsFragmentShowProducts(fedeoRequestParams);
        }
    }

    private boolean isLoginRequired() {
        if (osddParamsList != null) {
            for (final Parameter param : osddParamsList) {
                if (param.getName().equalsIgnoreCase(OSDDMatcher.PARAM_NAME_USERNAME) ||
                        param.getName().equalsIgnoreCase(OSDDMatcher.PARAM_NAME_PASSWORD)) {
                    return true;
                }
            }
        }
        return false;
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
    public interface OnCollectionDetailsFragmentListener {

        void onCollectionDetailsFragmentShowProducts(FedeoRequestParams fedeoRequestParams);

        void onCollectionDetailsFragmentShowMetadata(EntryISO displayedEntry);
    }

    @Override
    public void onRequestSuccess(OpenSearchDescription openSearchDescription) {
        super.onRequestSuccess(openSearchDescription);
        btnShowProducts.setEnabled(true);
        loadParamsSliderView();
    }
}
