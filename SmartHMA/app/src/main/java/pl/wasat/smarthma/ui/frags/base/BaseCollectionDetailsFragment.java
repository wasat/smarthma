package pl.wasat.smarthma.ui.frags.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;

import com.wunderlist.slidinglayer.SlidingLayer;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.osdd.OSDDMatcher;
import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.model.osdd.Option;
import pl.wasat.smarthma.model.osdd.Parameter;

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
    protected String type = "";

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

    public void loadParamsSliderView() {
        mSlidingLayer.setOnInteractListener(new InteractiveSlidingLayer());
    }

    private void addParameterSpinners() {
        if (osddParamsList != null) {
            for (final Parameter param : osddParamsList) {
                if (param.getOptions().size() == 0) {
                    if (skipParameter(param)) continue;
                    EditText editText = buildEditTextView(param);
                    layoutSpinners.addView(editText);
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

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.custom_spinner_item, optList);
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
        Parameter param;

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
