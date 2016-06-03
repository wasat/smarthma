package pl.wasat.smarthma.ui.frags.common;

import android.os.Bundle;
import android.view.View;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.model.osdd.Url;
import pl.wasat.smarthma.utils.request.FedeoOSDDRequest;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link ProductsListFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class ProductsListFragment extends ProductsListFragmentBase {
    private static final String KEY_OSDD_URL = "pl.wasat.smarthma.KEY_OSDD_URL";
    private String osddUrl;

    public ProductsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param fedeoRequestParams Parameter 1.
     * @return A new instance of fragment SearchProductsFeedsFragment.
     */
    public static ProductsListFragment newInstance(
            FedeoRequestParams fedeoRequestParams, String osddUrl) {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_PARAM_FEDEO_REQUEST, fedeoRequestParams);
        args.putString(KEY_OSDD_URL, osddUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            osddUrl = getArguments().getString(
                    KEY_OSDD_URL);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (osddUrl != null) startAsyncLoadOsddData(osddUrl);
        else startFedeoProductSearchRequest(fedeoRequestParams);
    }


    private void startAsyncLoadOsddData(String osddUrl) {
        if (!osddUrl.isEmpty()) {
            //GenericUrl fedeoDescUrl = new GenericUrl(osddUrl);
            getActivity().setProgressBarIndeterminateVisibility(true);
            getSpiceManager().execute(new FedeoOSDDRequest(osddUrl),
                    new FeedRequestListener());
        }
    }

    private void getTemplateUrl(OpenSearchDescription osdd) {
        for (Url osddUrl : osdd.getUrl()) {
            if (osddUrl.getType().equalsIgnoreCase(Link.TYPE_ATOM_XML) &&
                    osddUrl.getRel().equalsIgnoreCase(Link.REL_RESULTS)) {
                fedeoRequestParams.setTemplateUrl(osddUrl.getTemplate());
            }
        }
    }


    private final class FeedRequestListener implements RequestListener<OpenSearchDescription> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {

        }

        @Override
        public void onRequestSuccess(OpenSearchDescription openSearchDescription) {
            if (fedeoRequestParams != null) {
                getTemplateUrl(openSearchDescription);
                getActivity().setProgressBarIndeterminateVisibility(false);
                startFedeoProductSearchRequest(fedeoRequestParams);
            }
        }
    }
}
