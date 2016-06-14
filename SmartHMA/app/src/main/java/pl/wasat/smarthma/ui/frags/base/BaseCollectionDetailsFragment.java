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

package pl.wasat.smarthma.ui.frags.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import com.wunderlist.slidinglayer.SlidingLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.osdd.OSDDMatcher;
import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.model.osdd.Option;
import pl.wasat.smarthma.model.osdd.Parameter;
import pl.wasat.smarthma.utils.io.PatternNumberKeyListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * interface to handle interaction events. Use the
 * {@link BaseCollectionDetailsFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class BaseCollectionDetailsFragment extends
        BaseViewAndBasicSettingsDetailFragment {

    private boolean isSliderEmpty = true;
    /**
     * The Type.
     */
    protected String type = "";

    /**
     * Instantiates a new Base collection details fragment.
     */
    public BaseCollectionDetailsFragment() {
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

        loadSharedData();

        return rootView;
    }

    @Override
    public void onRequestSuccess(OpenSearchDescription openSearchDescription) {
        super.onRequestSuccess(openSearchDescription);
        loadParamsSliderView();
        changeSearchBtn();
    }

    /**
     * Load params slider view.
     */
    protected void loadParamsSliderView() {
        mSlidingLayer.setOnInteractListener(new InteractiveSlidingLayer());
    }

    private void addParameterSpinners() {
        if (osddParamsList != null) {
            for (final Parameter param : osddParamsList) {
                if (param.getOptions().size() == 0) {
                    if (skipParameter(param)) continue;
                    //EditText editText = buildEditTextView(param);
                    //layoutSpinners.addView(editText);
                    AutoCompleteTextView autoText = buildAutoCompleteTextView(param);
                    layoutSpinners.addView(autoText);
                } else {
                    Spinner spinner = buildSpinnerView(param);
                    layoutSpinners.addView(spinner);
                }
            }
        }
    }

    @NonNull
    private EditText buildEditTextView(Parameter param) {
        EditText editText = new EditText(getActivity());

        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
        layoutParams.setMargins(40, 10, 40, 10);
        editText.setLayoutParams(layoutParams);
        editText.setHint(String.format(getActivity().getString(R.string.set_), param.getName()));
        editText.setHintTextColor(Color.GRAY);
        editText.setBackgroundColor(Color.WHITE);
        editText.addTextChangedListener(new EditTextViewInputWatcher(param));
        editText.setOnTouchListener(new EditTextViewInputWatcher(param));
        editText.setOnFocusChangeListener(new EditTextViewInputWatcher(param));
        editText.setTextSize(14);
        return editText;
    }

    @NonNull
    private AutoCompleteTextView buildAutoCompleteTextView(Parameter param) {
        String[] AUTO_PHRASES = new String[]{
                "[]", "[,]", "{}", "{,}", "1", "}", "]", ","
        };
        AutoCompleteTextView autoTextView = new AutoCompleteTextView(getActivity());
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
        layoutParams.setMargins(40, 10, 40, 10);
        autoTextView.setLayoutParams(layoutParams);
        autoTextView.setHint(String.format(getActivity().getString(R.string.set_), param.getName()));
        autoTextView.setHintTextColor(Color.GRAY);
        autoTextView.setBackgroundColor(Color.WHITE);
        autoTextView.setTextSize(14);

        autoTextView = resolvePattern(autoTextView, param.getPattern());
        autoTextView.addTextChangedListener(new EditTextViewInputWatcher(param));
        autoTextView.setOnTouchListener(new EditTextViewInputWatcher(param));
        autoTextView.setOnFocusChangeListener(new EditTextViewInputWatcher(param));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, AUTO_PHRASES);
        autoTextView.setAdapter(adapter);
        return autoTextView;
    }

    private AutoCompleteTextView resolvePattern(AutoCompleteTextView autoTextView, String pattern) {
        if (pattern == null) {
            autoTextView.setInputType(InputType.TYPE_CLASS_TEXT);
            return autoTextView;
        } else if (pattern.equalsIgnoreCase("[0-9]+")) {
            autoTextView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            return autoTextView;
        } else if (pattern.equalsIgnoreCase
                ("^[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]+)?(Z|[\\+\\-][0-9]{2}:[0-9]{2})$")) {
            autoTextView.setInputType(InputType.TYPE_CLASS_DATETIME);
            return autoTextView;
        } else if (pattern.equalsIgnoreCase
                ("(\\[|\\])(100|[0-9]\\d?),(100|[0-9]\\d?)(\\[|\\])|(\\[|\\])?(100|[0-9]\\d?)|(100|[0-9]\\d?)(\\[|\\])?|\\{(100|[0-9]\\d?),(100|[0-9]\\d?)\\}")) {
            //autoTextView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            autoTextView.setKeyListener(new PatternNumberKeyListener());
            return autoTextView;
        } else if (pattern.equalsIgnoreCase
                ("(\\[|\\])[0-9]+,[0-9]+(\\[|\\])|(\\[|\\])?[0-9]+|[0-9]+(\\[|\\])?|\\{[0-9]+,[0-9]+\\}")) {
            autoTextView.setKeyListener(new PatternNumberKeyListener());
            return autoTextView;
        } else if (pattern.equalsIgnoreCase
                ("(\\[|\\])[0-9]+(.[0-9]+)?,[0-9]+(.[0-9]+)?(\\[|\\])|(\\[|\\])?[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?(\\[|\\])?|\\{[0-9]+(.[0-9]+)?,[0-9]+(.[0-9]+)?\\}")) {
            autoTextView.setKeyListener(new PatternNumberKeyListener());
            return autoTextView;
        } else if (pattern.equalsIgnoreCase
                ("(\\[|\\])[0-9]+,[0-9]+(\\[|\\])|(\\[|\\])?[0-9]+|[0-9]+(\\[|\\])?|\\{[0-9]+,[0-9]+\\}")) {
            autoTextView.setKeyListener(new PatternNumberKeyListener());
            return autoTextView;
        }
        return autoTextView;
    }

/*    private void validateInput(Parameter parameter){
        InputFilter filter= new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    String checkMe = String.valueOf(source.charAt(i));

                    Pattern pattern = Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789_]*");
                    Matcher matcher = pattern.matcher(checkMe);
                    boolean valid = matcher.matches();
                    if(!valid){
                        Log.d("", "invalid");
                        return "";
                    }
                }
                return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }*/

    private boolean skipParameter(Parameter parameter) {
        if (sharedPrefs.getAreaUse() && parameter.getName().equalsIgnoreCase(OSDDMatcher.PARAM_NAME_BBOX))
            return true;
        else if (sharedPrefs.getTimeUse() && parameter.getName().equalsIgnoreCase(OSDDMatcher.PARAM_NAME_END_DATE))
            return true;
        else if (sharedPrefs.getTimeUse() && parameter.getName().equalsIgnoreCase(OSDDMatcher.PARAM_NAME_START_DATE))
            return true;
        return false;
    }

    @NonNull
    private Spinner buildSpinnerView(final Parameter param) {
        Spinner spinner = new Spinner(getActivity());
        spinner.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
        spinner.setPadding(20, 0, 20, 0);

        final List<String> optList = new ArrayList<>();
        optList.add(String.format(getContext().getString(R.string.choose_osdd_param), param.getName()));
        for (Option opt : param.getOptions()) {
            optList.add(opt.getLabel());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item_slider, optList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String optValue = "";
                if (l > 0) {
                    optValue = param.getOptions().get(i - 1).getValue();
                    checkParamType(param, optValue);
                }
                fedeoRequestParams.addOsddValue(param.getValue(), optValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });
        return spinner;
    }

    private void checkParamType(Parameter parameter, String optValue) {
        if (parameter.getName().equalsIgnoreCase(OSDDMatcher.PARAM_NAME_TYPE)) {
            type = optValue;
            changeSearchBtn();
        }
    }

    private void changeSearchBtn() {
        switch (type) {
            case OSDDMatcher.PARAM_VALUE_COLLECTION:
                btnShowProducts.setText(R.string.show_collections);
                break;
            case OSDDMatcher.PARAM_VALUE_DATASET:
                btnShowProducts.setText(R.string.show_products);
                break;
            default:
                if (collectionName != null && collectionName.equalsIgnoreCase("EOP:ESA:FEDEO"))
                    btnShowProducts.setText(R.string.show_collections);
                else
                    btnShowProducts.setText(R.string.show_products);
                break;
        }
    }


    private class InteractiveSlidingLayer implements SlidingLayer.OnInteractListener {

        @Override
        public void onOpen() {
            if (isSliderEmpty) {
                addParameterSpinners();
                isSliderEmpty = false;
            }
        }

        @Override
        public void onShowPreview() {
        }

        @Override
        public void onClose() {
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
    }

    private class EditTextViewInputWatcher implements TextWatcher, View.OnTouchListener, View.OnFocusChangeListener {
        /**
         * The Param.
         */
        final Parameter param;

        /**
         * Instantiates a new Edit text view input watcher.
         *
         * @param parameter the parameter
         */
        EditTextViewInputWatcher(Parameter parameter) {
            this.param = parameter;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            validatePatternMatcher(s);
            validateMinMax(s);


        }

        private void validateMinMax(Editable s) {
            try {
                if (!s.toString().isEmpty()) {
                    if (param.getMinInclusive() != null && !param.getMinInclusive().isEmpty()) {
                        if (Float.valueOf(s.toString()) < Float.valueOf(param.getMinInclusive())) {
                            s.clear();
                            s.append(param.getMinInclusive());
                        }
                    }
                    if (param.getMaxInclusive() != null && !param.getMaxInclusive().isEmpty()) {
                        if (Float.valueOf(s.toString()) > Float.valueOf(param.getMaxInclusive())) {
                            s.clear();
                            s.append(param.getMaxInclusive());
                        }
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        private void validatePatternMatcher(Editable s) {
            if (param.getPattern() != null && !param.getPattern().isEmpty() && !s.toString().isEmpty()) {
                //Pattern mPattern = Pattern.compile("^([1-9][0-9]{0,2})?(\\.[0-9]?)?$");
                Pattern mPattern = Pattern.compile(param.getPattern().trim());
                Matcher matcher = mPattern.matcher(s.toString().trim());
                if (!matcher.find()) {
                    Toast.makeText(getActivity(), R.string.value_not_fit_to_pattern, Toast.LENGTH_SHORT).show();
                    Log.d("PATTERN", param.getName() + " - " + getString(R.string.value_not_fit_to_pattern));
                    return;
                }
            }
            fedeoRequestParams.addOsddValue(param.getValue(), s.toString().trim());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            EditText editText = (EditText) v;
            editText.setHint("");
            return false;
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                EditText editText = (EditText) v;
                editText.setHint(String.format(getString(R.string.set_), param.getName()));
            }
        }
    }

}
