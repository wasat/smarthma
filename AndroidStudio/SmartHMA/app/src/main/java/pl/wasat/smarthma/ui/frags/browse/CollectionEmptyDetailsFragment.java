package pl.wasat.smarthma.ui.frags.browse;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;

import com.google.api.client.http.GenericUrl;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.wunderlist.slidinglayer.SlidingLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.model.osdd.Option;
import pl.wasat.smarthma.model.osdd.Parameter;
import pl.wasat.smarthma.model.osdd.Url;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.frags.base.BaseViewAndBasicSettingsDetailFragment;
import pl.wasat.smarthma.utils.rss.FedeoOSDDRequest;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link pl.wasat.smarthma.ui.frags.browse.CollectionEmptyDetailsFragment.OnCollectionEmptyDetailsFragmentListener}
 * interface to handle interaction events. Use the
 * {@link CollectionEmptyDetailsFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class CollectionEmptyDetailsFragment extends
        BaseViewAndBasicSettingsDetailFragment {
    private static final String KEY_COLLECTION_NAME = "pl.wasat.smarthma.KEY_COLLECTION_NAME";

    private OnCollectionEmptyDetailsFragmentListener mListener;
    private boolean waitForOsddLoad = true;

    private FedeoRequestParams fedeoRequestParams;
    private HashMap<String, String> paramsMap;
    private boolean isSpinnersAdded;
    private boolean isCollectionTypeChosen = false;


    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param collectionName Parameter 1.
     * @return A new instance of fragment CollectionDetailsFragment.
     */
    public static CollectionEmptyDetailsFragment newInstance(String collectionName) {
        CollectionEmptyDetailsFragment fragment = new CollectionEmptyDetailsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_COLLECTION_NAME, collectionName);
        fragment.setArguments(args);
        return fragment;
    }

    public CollectionEmptyDetailsFragment() {
        // Required empty public constructor
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

        loadDefaultFedeoParams();

        loadSharedData();

        btnShowProducts.setEnabled(true);
        btnShowProducts.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    //TODO !!!
                    //mListener.onCollectionDetailsFragmentShowProducts(parentID);
                    mSlidingLayer.closeLayer(true);
                    //fedeoRequestParams.buildFromShared(getActivity());
                    fedeoRequestParams.setParentIdentifier(collectionName);
                    if (isCollectionTypeChosen) {
                        mListener.onCollectionEmptyDetailsFragmentShowCollections(fedeoRequestParams);
                    } else {
                        mListener.onCollectionEmptyDetailsFragmentShowProducts(fedeoRequestParams);
                    }
                }
            }
        });

        btnShowMetadata.setEnabled(false);

        tvParentId.setText(collectionName);

        String osddUrl = Const.OSDD_BASE_URL + "parentIdentifier=" + collectionName;
        //TODO !!!
        loadParamsSliderView(osddUrl);

        return rootView;
    }

    private void loadParamsSliderView(String osddUrl) {
        final GenericUrl finalOsddUrl = new GenericUrl(osddUrl);

        mSlidingLayer.setOnInteractListener(new SlidingLayer.OnInteractListener() {
            @Override
            public void onOpen() {
                if (waitForOsddLoad) {
                    startAsyncLoadOsddData(finalOsddUrl);
                }
            }

            @Override
            public void onShowPreview() {
            }

            @Override
            public void onClose() {
                if (paramsMap != null) {
                    fedeoRequestParams.setParamsExtra(paramsMap);
                    //mListener.onCollectionDetailsFragmentShowProducts(fedeoRequestParams);
                }
            }

            @Override
            public void onOpened() {
            }

            @Override
            public void onPreviewShowed() {
            }

            @Override
            public void onClosed() {
            }
        });
    }

    private void loadDefaultFedeoParams() {
        fedeoRequestParams = new FedeoRequestParams();
        //fedeoRequestParams.buildFromShared(getActivity().getApplicationContext());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnCollectionEmptyDetailsFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCollectionEmptyDetailsFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void putParentIdToShared() {
        String parentID = null;
        if (!(collectionName == null || collectionName.isEmpty())) {
            parentID = collectionName;
        }
        SharedPrefs sharedPrefs = new SharedPrefs(getActivity().getApplicationContext());
        sharedPrefs.setParentIdPrefs(parentID);
    }

    private void startAsyncLoadOsddData(GenericUrl fedeoDescUrl) {
        if (fedeoDescUrl != null) {
            getActivity().setProgressBarIndeterminateVisibility(true);
            getSpiceManager().execute(new FedeoOSDDRequest(fedeoDescUrl),
                    new OsddRequestListener());
        }
    }

    private void loadRequestSuccess(OpenSearchDescription osdd) {
        getActivity().setProgressBarIndeterminateVisibility(false);
        if (osdd != null) {
            initFedeoReq(osdd);
            addParameterSpinners(osdd);
        }
    }

    private void addParameterSpinners(OpenSearchDescription osdd) {

        paramsMap = new HashMap<>();

        // TODO - remove this loop and condition to fit to final version of OSDD based on geo.spacebel.be endpoint
        for (int i = 0; i < osdd.getUrl().size(); i++) {
            if (!isSpinnersAdded && osdd.getUrl().get(i).getType().equalsIgnoreCase("application/atom+xml")) {
                loadParametersToSpinner(osdd.getUrl().get(i));
            }
/*            else if (i == osdd.getUrl().size() - 1 && !osdd.getUrl().get(i).getParameters().isEmpty()) {
                loadParametersToSpinner(osdd.getUrl().get(i));
            }*/
        }
    }

    private void loadParametersToSpinner(Url osddUrl) {
        for (final Parameter param : osddUrl.getParameters()) {

            Spinner spinner = new Spinner(getActivity());
            spinner.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            spinner.setPadding(25, 10, 25, 10);

            List<String> optList = new ArrayList<>();
            optList.add("Choose " + param.getName() + "...");
            for (Option opt : param.getOption()) {
                optList.add(opt.getLabel());
            }

            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.custom_spinner_item /*android.R.layout.simple_spinner_item*/, optList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (l > 0) {
                        String paramName = param.getName();
                        String paramOption = param.getOption().get(i - 1).getValue();
                        paramsMap.put(paramName, paramOption);
                        validateChoosenOpt(paramOption);
                    } else {
                        paramsMap.put(param.getName(), "");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            layoutSpinners.addView(spinner);
            isSpinnersAdded = true;
        }
    }

    private void validateChoosenOpt(String option) {
        if (option.equalsIgnoreCase("collection")) {
            btnShowProducts.setText(R.string.show_collections);
            isCollectionTypeChosen = true;
        } else {
            btnShowProducts.setText(R.string.show_products);
            isCollectionTypeChosen = false;
        }

    }

    private void initFedeoReq(OpenSearchDescription osdd) {
        loadDefaultFedeoParams();
        String tmpltUrl = "";
        for (Url url : osdd.getUrl()) {
            if (url.getType().equalsIgnoreCase("application/atom+xml")) {
                tmpltUrl = url.getTemplate();
            }
        }
        fedeoRequestParams.setTemplateUrl(tmpltUrl);
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
        // public void onCollectionDetailsFragmentShowProducts(String parentID);

        void onCollectionEmptyDetailsFragmentShowProducts(FedeoRequestParams fedeoRequestParams);

        void onCollectionEmptyDetailsFragmentShowCollections(FedeoRequestParams fedeoRequestParams);

    }


    private final class OsddRequestListener implements RequestListener<OpenSearchDescription> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            parseRequestFailure(spiceException);
        }

        @Override
        public void onRequestSuccess(OpenSearchDescription osdd) {
            loadRequestSuccess(osdd);
            waitForOsddLoad = false;
        }
    }
}
