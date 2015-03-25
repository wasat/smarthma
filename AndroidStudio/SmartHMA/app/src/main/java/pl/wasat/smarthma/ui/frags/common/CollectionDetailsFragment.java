package pl.wasat.smarthma.ui.frags.common;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.wunderlist.slidinglayer.SlidingLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.iso.EntryISO;
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
        // Required empty public constructor
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

        putParentIdToShared();

        String osddUrl = "";
        for (Link entityLink : displayedISOEntry.getLink()) {
            if (entityLink.getRel().equalsIgnoreCase("search")) {
                btnShowProducts.setEnabled(true);
            }
            if (entityLink.getRel().equalsIgnoreCase("search") && entityLink.getType().equalsIgnoreCase("application/opensearchdescription+xml")) {
                osddUrl = entityLink.getHref();
            }
        }

        btnShowProducts.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mListener != null) {
                    //mListener.onCollectionDetailsFragmentShowProducts(parentID);
                    mSlidingLayer.closeLayer(true);
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

        final String finalOsddUrl = osddUrl;

        mSlidingLayer.setOnInteractListener(new SlidingLayer.OnInteractListener() {
            @Override
            public void onOpen() {
                Log.i("SLIDER", "onOpen");
                if (waitForOsddLoad) {
                    startAsyncLoadOsddData(finalOsddUrl);
                }
            }

            @Override
            public void onShowPreview() {
                Log.i("SLIDER", "onShowPreview");
            }

            @Override
            public void onClose() {
                Log.i("SLIDER", "onClose");
                fedeoRequestParams.setParamsExtra(paramsMap);
                //mListener.onCollectionDetailsFragmentShowProducts(fedeoRequestParams);
            }

            @Override
            public void onOpened() {
                Log.i("SLIDER", "onOpened");
            }

            @Override
            public void onPreviewShowed() {
                Log.i("SLIDER", "onPreviewShowed");
            }

            @Override
            public void onClosed() {
                Log.i("SLIDER", "onClosed");
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
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

    private void putParentIdToShared() {
        final String parentID = displayedISOEntry.getIdentifier();
        SharedPrefs sharedPrefs = new SharedPrefs(getActivity().getApplicationContext());
        sharedPrefs.setParentIdPrefs(parentID);
    }

    private void startAsyncLoadOsddData(String fedeoDescUrl) {
        if (fedeoDescUrl != null) {
            getActivity().setProgressBarIndeterminateVisibility(true);
            getSpiceManager().execute(new FedeoOSDDRequest(fedeoDescUrl),
                    new OsddRequestListener());
        }
    }

    void loadRequestSuccess(OpenSearchDescription osdd) {
        getActivity().setProgressBarIndeterminateVisibility(false);
        initFedeoReq(osdd);
        addParameterSpinners(osdd);
    }

    private void addParameterSpinners(OpenSearchDescription osdd) {

        paramsMap = new HashMap<>();

        for (final Parameter param : osdd.getParameter()) {

            Spinner spinner = new Spinner(getActivity());
            spinner.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            spinner.setPadding(25, 2, 25, 2);
            //spinner.setPrompt(param.getName());

            List<String> optList = new ArrayList<>();
            optList.add("Choose " + param.getName() + "...");
            for (Option opt : param.getOption()) {
                optList.add(opt.getLabel());
            }

            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, optList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (l > 0) {
                        paramsMap.put(param.getName(), param.getOption().get(i - 1).getValue());

                        Toast.makeText(adapterView.getContext(),
                                "Item Selected : " + adapterView.getItemAtPosition(i).toString() + " ID: " + l,
                                Toast.LENGTH_LONG).show();
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

    private void initFedeoReq(OpenSearchDescription osdd) {
        fedeoRequestParams = new FedeoRequestParams();
        fedeoRequestParams.buildFromShared(getActivity().getApplicationContext());
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
        // public void onCollectionDetailsFragmentShowProducts(String parentID);

        public void onCollectionDetailsFragmentShowProducts(FedeoRequestParams fedeoRequestParams);

        public void onCollectionDetailsFragmentShowMetadata(EntryISO displayedEntry);

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
