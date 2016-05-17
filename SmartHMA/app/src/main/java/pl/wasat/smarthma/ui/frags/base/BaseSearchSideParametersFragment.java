package pl.wasat.smarthma.ui.frags.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.api.client.http.GenericUrl;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.customviews.CatalogueArrayAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.osdd.OSDDMatcher;
import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.model.osdd.Option;
import pl.wasat.smarthma.model.osdd.Parameter;
import pl.wasat.smarthma.utils.request.FedeoOSDDRequest;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link null}
 * interface to handle interaction events. Use the
 * {@link BaseSearchSideParametersFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class BaseSearchSideParametersFragment extends BaseDateTimeAreaContainerFragment implements RequestListener<OpenSearchDescription> {
    private static TextView tvCatalogName;
    protected View rootView;
    protected static List<Parameter> osddParams;
    protected static FedeoRequestParams fedeoRequestParams;
    private OnBaseSearchSideParametersFragmentListener mListener;

    public BaseSearchSideParametersFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment SearchBasicInfoRightFragment.
     */
    public static BaseSearchSideParametersFragment newInstance() {
        return new BaseSearchSideParametersFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(
                R.layout.fragment_search_side_parameters, container, false);

        tvCatalogName = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_catalog_name);

        tvCatalogName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showCatalogueListDialog();
            }
        });

        final LinearLayout areaLayout = (LinearLayout) rootView
                .findViewById(R.id.search_frag_side_params_layout_area);
        areaLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDeviceAndLoadMapPicker(R.id.activity_base_details_container);
            }
        });
        final LinearLayout areaValuesLayout = (LinearLayout) rootView
                .findViewById(R.id.search_frag_side_params_layout_area_values);
        checkBoxArea = (CheckBox) rootView
                .findViewById(R.id.search_frag_side_params_checkbox_area);
        checkBoxArea.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setAreaViewParameters(areaValuesLayout, areaLayout, checkBoxArea.isChecked());
            }
        });
        setAreaViewParameters(areaValuesLayout, areaLayout, sharedPrefs.getAreaUse());
        tvAreaTitleNEPt = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_area_title_ne_pt);
        tvAreaNEPtLat = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_area_ne_lat);
        tvAreaNEPtLon = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_area_ne_lon);
        tvAreaTitleSWRad = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_area_title_sw_rad);
        tvAreaSWRadLat = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_area_sw_lat);
        tvAreaSWKmLon = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_area_sw_lon);


        final LinearLayout timeValuesLayout = (LinearLayout) rootView
                .findViewById(R.id.search_frag_side_params_layout_dt_values);
        checkBoxTime = (CheckBox) rootView
                .findViewById(R.id.search_frag_side_params_checkbox_time);
        checkBoxTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeViewParameters(timeValuesLayout, checkBoxTime.isChecked());
            }
        });
        setTimeViewParameters(timeValuesLayout, sharedPrefs.getTimeUse());
        tvStartDate = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_start_date);
        tvStartDate.setTag("tvStartDate");
        tvStartDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerDialog(calStart, tvStartDate);
            }
        });
        tvStartTime = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_start_time);
        tvStartTime.setTag("tvStartTime");
        tvStartTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showTimePickerDialog(calStart, tvStartTime);
            }
        });
        tvEndDate = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_end_date);
        tvEndDate.setTag("tvEndDate");
        tvEndDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(calEnd, tvEndDate);
            }
        });
        tvEndTime = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_end_time);
        tvEndTime.setTag("tvEndTime");
        tvEndTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showTimePickerDialog(calEnd, tvEndTime);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnBaseSearchSideParametersFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnBaseSearchSideParametersFragmentListener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        startAsyncLoadOsddData(new GenericUrl(Const.OSDD_BASE_URL));
    }

    @Override
    public void onResume() {
        tvCatalogName.setText(sharedPrefs.getCataloguePrefs());
        super.onResume();
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        parseRequestFailure(spiceException);
    }

    @Override
    public void onRequestSuccess(OpenSearchDescription openSearchDescription) {
        loadRequestSuccess(openSearchDescription);
        obtainGlobalSettings();
    }

    private void showCatalogueListDialog() {
        CatalogueListDialogFragment listDialFrag = new CatalogueListDialogFragment();
        listDialFrag.show(getActivity().getSupportFragmentManager(),
                CatalogueListDialogFragment.class.getSimpleName());
    }

    public void setCatalogue(String catalogue) {
        Parameter parameter = OSDDMatcher.findParentIdParam(osddParams);
        List<Option> cataloguesList = null;
        if (parameter != null) {
            cataloguesList = parameter.getOptions();
            for (int i = 0; i < cataloguesList.size(); i++) {
                if (catalogue.equalsIgnoreCase(cataloguesList.get(i).getValue())) {
                    setCatalogue(parameter, i);
                }
            }
        }
    }

    private static void setCatalogue(Parameter parentIdParam, int which) {
        Option choosenOpt = parentIdParam.getOptions().get(which);
        fedeoRequestParams.addOsddValue(parentIdParam.getValue(), choosenOpt.getValue());
        tvCatalogName.setText(parentIdParam.getOptions().get(which).getLabel());
        sharedPrefs.setCataloguePrefs(choosenOpt.getLabel());
    }

    public static class CatalogueListDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Parameter parentIdParam = OSDDMatcher.findParentIdParam(osddParams);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.eo_catalogue_list_title)
                    .setAdapter(createAdapter(parentIdParam != null ? parentIdParam.getOptions() : null),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int item) {
                                    setCatalogue(parentIdParam, item);
                                    dialog.dismiss();
                                }
                            });
            return builder.create();
        }

        private ListAdapter createAdapter(final List<Option> options) {
            return new CatalogueArrayAdapter(
                    getActivity().getApplicationContext(), R.layout.view_cell_cataloque, options);
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
    public interface OnBaseSearchSideParametersFragmentListener {
        void onBaseSearchSideParametersFragmentFedeoRequestParamsOsddChange(FedeoRequestParams fedeoRequestParams);
    }

    public void startAsyncLoadOsddData(GenericUrl fedeoDescUrl) {
        if (fedeoDescUrl != null) {
            getActivity().setProgressBarIndeterminateVisibility(true);
            getSpiceManager().execute(new FedeoOSDDRequest(fedeoDescUrl.toString()),
                    this);
        }
    }

    private void loadRequestSuccess(OpenSearchDescription osdd) {
        getActivity().setProgressBarIndeterminateVisibility(false);
        if (osdd != null) {
            loadCollectionsParameters(osdd);
            mListener.onBaseSearchSideParametersFragmentFedeoRequestParamsOsddChange(fedeoRequestParams);
        }
    }

    private void loadCollectionsParameters(OpenSearchDescription osdd) {
        osddParams = new ArrayList<>();
        fedeoRequestParams = new FedeoRequestParams();

        for (int i = 0; i < osdd.getUrl().size(); i++) {
            defineOsddCollSearchParams(osdd, i);
        }
    }

    private void defineOsddCollSearchParams(OpenSearchDescription osdd, int i) {
        if (osdd.getUrl().get(i).getType().equalsIgnoreCase(Link.TYPE_ATOM_XML) &&
                osdd.getUrl().get(i).getRel().equalsIgnoreCase(Link.REL_COLLECTION)) {

            osddParams = osdd.getUrl().get(i).getParameters();
            fedeoRequestParams.setTemplateUrl(osdd.getUrl().get(i).getTemplate());

            for (Parameter paramItem : osddParams) {
                if (paramItem.getName().equalsIgnoreCase(OSDDMatcher.PARAM_NAME_TYPE))
                    fedeoRequestParams.addOsddValue(paramItem.getValue(), OSDDMatcher.PARAM_VALUE_COLLECTION);
                else if (paramItem.getName().equalsIgnoreCase(OSDDMatcher.PARAM_NAME_PARENT_IDENTIFIER)) {
                    chooseInitCatalogue(paramItem);
                }
                //else if (paramItem.getName().equalsIgnoreCase(OSDDMatcher.PARAM_NAME_RECORD_SCHEMA))
                //    fedeoRequestParams.addOsddValue(paramItem.getValue(), "ISO");
            }
        }
    }

    private void chooseInitCatalogue(Parameter paramItem) {
        if (tvCatalogName.getText().length() == 0) {
            setCatalogue(paramItem, 0);
        } else {
            String optionValue = OSDDMatcher.findOptionValue(paramItem.getOptions(), tvCatalogName.getText().toString().trim());
            fedeoRequestParams.addOsddValue(paramItem.getValue(), optionValue);
        }
    }


}
