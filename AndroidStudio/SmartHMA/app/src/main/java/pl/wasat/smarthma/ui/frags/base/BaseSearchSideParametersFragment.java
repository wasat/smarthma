package pl.wasat.smarthma.ui.frags.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.wasat.smarthma.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link null}
 * interface to handle interaction events. Use the
 * {@link BaseSearchSideParametersFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class BaseSearchSideParametersFragment extends BaseDateTimeAreaContainerFragment {
    protected View rootView;
    private static TextView tvCatalogName;

    private static final CharSequence[] cataloguesList = {"EOP:ESA:FEDEO",
            "EOP:ESA:FEDEO:COLLECTIONS", "EOP:ESA:GPOD-EO", "EOP:ESA:EO-VIRTUAL-ARCHIVE4",
            "EOP:ESA:SMOS", "EOP:JAXA:CATS-I", "EOP:NASA:ECHO"};

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment SearchBasicInfoRightFragment.
     */
    public static BaseSearchSideParametersFragment newInstance() {
        return new BaseSearchSideParametersFragment();
    }

    public BaseSearchSideParametersFragment() {
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

        tvAreaSWLat = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_area_sw_lat);
        tvAreaSWLon = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_area_sw_lon);
        tvAreaNELat = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_area_ne_lat);
        tvAreaNELon = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_area_ne_lon);

        LinearLayout areaLayout = (LinearLayout) rootView
                .findViewById(R.id.search_frag_side_params_layout_area);
        areaLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDeviceAndLoadMapPicker(R.id.activity_base_details_container);
            }
        });

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

        obtainGlobalSettings();
        return rootView;
    }


    @Override
    public void onResume() {
        sharedPrefs.setParentIdPrefs(tvCatalogName.getText().toString());
        super.onResume();
    }

    private void showCatalogueListDialog() {
        CatalogueListDialogFragment listDialFrag = new CatalogueListDialogFragment();
        listDialFrag.show(getActivity().getSupportFragmentManager(),
                "CatalogueListDialogFragment");
    }

    public static class CatalogueListDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.eo_catalogue_list_title).setItems(
                    cataloguesList, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            setCatalogue(which);
                        }
                    });
            return builder.create();
        }
    }

    private static void setCatalogue(int which) {
        tvCatalogName.setText(cataloguesList[which]);
        sharedPrefs.setParentIdPrefs(cataloguesList[which].toString());
    }

    public void setCatalogue(String catalogue) {
        CharSequence[] cataloguesList = getCataloguesList();
        for (int i = 0; i < cataloguesList.length; i++) {
            if (catalogue.equalsIgnoreCase(cataloguesList[i].toString())) {
                setCatalogue(i);
            }
        }
    }

    @SuppressWarnings("SameReturnValue")
    private static CharSequence[] getCataloguesList() {
        return cataloguesList;
    }

}
