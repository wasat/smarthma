/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wasat.smarthma.ui.frags.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.osdd.OSDDMatcher;
import pl.wasat.smarthma.model.osdd.Option;
import pl.wasat.smarthma.model.osdd.Parameter;
import pl.wasat.smarthma.ui.frags.base.BaseSearchSideParametersFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * interface to handle interaction events. Use the
 * {@link SearchAdvancedParametersFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class SearchAdvancedParametersFragment extends BaseSearchSideParametersFragment {

    private static final int EDIT_TEXT_PLATFORM = 1;
    private static final int EDIT_TEXT_INSTRUMENT = 2;
    private static final int EDIT_TEXT_ORGANISATION = 3;
    private static final int EDIT_TEXT_TITLE = 4;

    /**
     * Instantiates a new Search advanced parameters fragment.
     */
    public SearchAdvancedParametersFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment SearchBasicInfoRightFragment.
     */
    public static SearchAdvancedParametersFragment newInstance() {
        return new SearchAdvancedParametersFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        LinearLayout layoutPlatform = (LinearLayout) rootView.findViewById(R.id.search_frag_side_params_layout_platform);
        layoutPlatform.setVisibility(View.VISIBLE);

        LinearLayout layoutInstrument = (LinearLayout) rootView.findViewById(R.id.search_frag_side_params_layout_instrument);
        layoutInstrument.setVisibility(View.VISIBLE);

        LinearLayout layoutOrg = (LinearLayout) rootView.findViewById(R.id.search_frag_side_params_layout_org);
        layoutOrg.setVisibility(View.VISIBLE);

/*        LinearLayout layoutTitle = (LinearLayout) rootView.findViewById(R.id.search_frag_side_params_layout_title);
        layoutTitle.setVisibility(View.VISIBLE);*/

        TextView tvPlatform = (TextView) rootView.findViewById(R.id.search_frag_side_params_tv_platform_header);
        tvPlatform.setVisibility(View.VISIBLE);

        TextView tvInstrument = (TextView) rootView.findViewById(R.id.search_frag_side_params_tv_instrument_header);
        tvInstrument.setVisibility(View.VISIBLE);

        TextView tvOrg = (TextView) rootView.findViewById(R.id.search_frag_side_params_tv_org_header);
        tvOrg.setVisibility(View.VISIBLE);

/*        TextView tvTitle = (TextView) rootView.findViewById(R.id.search_frag_side_params_tv_title_header);
        tvTitle.setVisibility(View.VISIBLE);*/

        AutoCompleteTextView autoTvEdtPlatform = (AutoCompleteTextView) rootView.findViewById(R.id.search_frag_side_params_editv_platform);
        List<String> autoOptListPlatform = OSDDMatcher.generateOptionValuesList(osddParams, OSDDMatcher.PARAM_KEY_PLATFORM);
        ArrayAdapter<String> adapterPlatform = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, autoOptListPlatform);
        autoTvEdtPlatform.setAdapter(adapterPlatform);
        autoTvEdtPlatform.setVisibility(View.VISIBLE);
        autoTvEdtPlatform.addTextChangedListener(new EditTextViewInputWatcher(EDIT_TEXT_PLATFORM));

        AutoCompleteTextView autoTvEdtInstrument = (AutoCompleteTextView) rootView.findViewById(R.id.search_frag_side_params_editv_instrument);
        autoTvEdtInstrument.setVisibility(View.VISIBLE);
        List<String> autoOptListInstrument = OSDDMatcher.generateOptionValuesList(osddParams, OSDDMatcher.PARAM_KEY_INSTRUMENT);
        ArrayAdapter<String> adapterInstrument = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, autoOptListInstrument);
        autoTvEdtInstrument.setAdapter(adapterInstrument);
        autoTvEdtInstrument.addTextChangedListener(new EditTextViewInputWatcher(EDIT_TEXT_INSTRUMENT));

        AutoCompleteTextView autoTvEdtOrganisation = (AutoCompleteTextView) rootView.findViewById(R.id.search_frag_side_params_editv_org);
        List<String> autoOptListOrg = OSDDMatcher.generateOptionValuesList(osddParams, OSDDMatcher.PARAM_KEY_ORGANISATION);
        ArrayAdapter<String> adapterOrg = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, autoOptListOrg);
        autoTvEdtOrganisation.setAdapter(adapterOrg);
        autoTvEdtOrganisation.setVisibility(View.VISIBLE);
        autoTvEdtOrganisation.addTextChangedListener(new EditTextViewInputWatcher(EDIT_TEXT_ORGANISATION));

/*        AutoCompleteTextView autoTvEdtTitle = (AutoCompleteTextView) rootView.findViewById(R.id.search_frag_side_params_editv_title);
        List<String> autoOptListTitle = OSDDMatcher.generateOptionValuesList(osddParams, OSDDMatcher.PARAM_KEY_TITLE);
        ArrayAdapter<String> adapterTitle = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, autoOptListTitle);
        autoTvEdtTitle.setAdapter(adapterTitle);
        autoTvEdtTitle.setVisibility(View.VISIBLE);
        autoTvEdtTitle.addTextChangedListener(new EditTextViewInputWatcher(EDIT_TEXT_TITLE));*/

        addParameterSpinners();

        return rootView;
    }

    private void addParameterSpinners() {
        //LinearLayout layoutSpinners = (LinearLayout) rootView.findViewById(R.id.search_frag_side_params_layout_spinners);
        //LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (osddParams != null) {
            for (final Parameter param : osddParams) {
                switch (param.getName()) {
                    case OSDDMatcher.PARAM_NAME_RECORD_SCHEMA:
                        generateSpinner(param, "Record Schema", 0);
                        break;
                    case OSDDMatcher.PARAM_NAME_TYPE:
                        generateSpinner(param, "Type", 2);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void generateSpinner(Parameter param, String header, int pos) {
        LinearLayout layoutSpinners = (LinearLayout) rootView.findViewById(R.id.search_frag_side_params_layout_spinners);
        LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View headerLayoutRecord = vi.inflate(R.layout.header_side, null);

        TextView spinnerTvRecord = (TextView) headerLayoutRecord.findViewById(R.id.search_frag_side_params_tv_spinner);
        spinnerTvRecord.setText(header);
        spinnerTvRecord.setVisibility(View.VISIBLE);

        layoutSpinners.addView(headerLayoutRecord, pos, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layoutSpinners.addView(buildSpinnerView(param));
        layoutSpinners.setVisibility(View.VISIBLE);
    }

    @NonNull
    private Spinner buildSpinnerView(final Parameter param) {
        Spinner spinner = new Spinner(getActivity());
        TableLayout.LayoutParams spinnerLayoutParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
        spinnerLayoutParams.setMargins(0, 0, 0, 20);
        spinner.setLayoutParams(spinnerLayoutParams);

        spinner.setPadding(20, 5, 20, 20);
        spinner.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.background_white));

        final List<String> optList = new ArrayList<>();
        optList.add(String.format(getContext().getString(R.string.choose_osdd_param), param.getName()));
        for (Option opt : param.getOptions()) {
            optList.add(opt.getLabel());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item_side, optList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String optValue = "";
                if (l > 0) {
                    optValue = param.getOptions().get(i - 1).getValue();
                    //checkParamType(param, optValue);
                }
                fedeoRequestParams.addOsddValue(param.getValue(), optValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });
        return spinner;
    }

    private class EditTextViewInputWatcher implements TextWatcher {

        private final int choosenEditTv;

        /**
         * Instantiates a new Edit text view input watcher.
         *
         * @param editTextView the edit text view
         */
        EditTextViewInputWatcher(int editTextView) {
            choosenEditTv = editTextView;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (choosenEditTv) {
                case EDIT_TEXT_PLATFORM:
                    fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_KEY_PLATFORM, s.toString());
                    break;
                case EDIT_TEXT_INSTRUMENT:
                    fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_KEY_INSTRUMENT, s.toString());
                    break;
                case EDIT_TEXT_ORGANISATION:
                    fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_KEY_ORGANISATION, s.toString());
                    break;
                case EDIT_TEXT_TITLE:
                    fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_KEY_TITLE, s.toString());
                    break;
                default:
                    break;
            }
        }
    }
}
