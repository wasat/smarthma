package pl.wasat.smarthma.ui.frags.common;

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
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.model.osdd.Option;
import pl.wasat.smarthma.model.osdd.Parameter;
import pl.wasat.smarthma.model.osdd.Url;
import pl.wasat.smarthma.ui.frags.base.BaseViewAndBasicSettingsDetailFragment;
import pl.wasat.smarthma.utils.rss.FedeoOSDDRequest;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link CollectionDetailsFragment.OnCollectionDetailsFragmentListener}
 * interface to handle interaction events. Use the
 * {@link CollectionDetailsFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class CollectionDetailsFragment extends
        BaseViewAndBasicSettingsDetailFragment {


    private OnCollectionDetailsFragmentListener mListener;
    private boolean waitForOsddLoad = true;

    private FedeoRequestParams fedeoRequestParams;
    private HashMap<String, String> paramsMap;


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

    public CollectionDetailsFragment() {
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

        String osddUrl = "";
        for (Link entityLink : displayedISOEntry.getLink()) {
            if (entityLink.getRel().equalsIgnoreCase("search") || Const.HTTP_BASE_URL.equals(Const.HTTP_SPACEBEL_BASE_URL)) {
                btnShowProducts.setEnabled(true);
            }
            if (entityLink.getRel().equalsIgnoreCase("search") && entityLink.getType().equalsIgnoreCase("application/opensearchdescription+xml")) {
                osddUrl = entityLink.getHref();
            }
        }

        if (osddUrl == null || osddUrl.isEmpty()) {
            osddUrl = Const.OSDD_BASE_URL + "parentIdentifier=" + displayedISOEntry.getIdentifier();
        }

        btnShowProducts.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    // TODO !!!
                    mSlidingLayer.closeLayer(true);
                    //fedeoRequestParams.buildFromShared(getActivity());
                    fedeoRequestParams.setParentIdentifier(displayedISOEntry.getIdentifier());
                    mListener.onCollectionDetailsFragmentShowProducts(fedeoRequestParams);
                }
            }
        });

        btnShowMetadata.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCollectionDetailsFragmentShowMetadata(displayedISOEntry);
            }
        });
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
            mListener = (OnCollectionDetailsFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCollectionDetailsFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
            if (osdd.getUrl().get(i).getType().equalsIgnoreCase("application/atom+xml")) {

                for (final Parameter param : osdd.getUrl().get(i).getParameters()) {

                    Spinner spinner = new Spinner(getActivity());
                    spinner.setLayoutParams(new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
                    spinner.setPadding(25, 10, 25, 10);
                    //spinner.setBackgroundColor(R.color.background_gray);
                    //android:background="@color/background_gray"
                    //spinner.setPrompt(param.getName());

                    List<String> optList = new ArrayList<>();
                    optList.add("Choose " + param.getName() + "...");
                    for (Option opt : param.getOption()) {
                        optList.add(opt.getLabel());
                    }

                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.custom_spinner_item, optList);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(spinnerAdapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (l > 0) {
                                paramsMap.put(param.getName(), param.getOption().get(i - 1).getValue());
                            } else {
                                paramsMap.put(param.getName(), "");
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                    layoutSpinners.addView(spinner);
                }
            }
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
    public interface OnCollectionDetailsFragmentListener {

        void onCollectionDetailsFragmentShowProducts(FedeoRequestParams fedeoRequestParams);

        void onCollectionDetailsFragmentShowMetadata(EntryISO displayedEntry);

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
