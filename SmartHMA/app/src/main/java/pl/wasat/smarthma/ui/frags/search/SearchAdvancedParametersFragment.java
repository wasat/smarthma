package pl.wasat.smarthma.ui.frags.search;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.osdd.OSDDMatcher;
import pl.wasat.smarthma.ui.frags.base.BaseSearchSideParametersFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * interface to handle interaction events. Use the
 * {@link SearchAdvancedParametersFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class SearchAdvancedParametersFragment extends BaseSearchSideParametersFragment {

    private static final int EDIT_TEXT_TITLE = 1;
    private static final int EDIT_TEXT_ORGANISATION = 2;
    private static final int EDIT_TEXT_PLATFORM = 3;
    private static final CharSequence[] endpointsList = {"fedeo.esa.int",
            "geo.spacebel.be", "smaad.spacebel.be", "obeos.spacebel.be"};
    private static TextView tvEndpointName;

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

        tvEndpointName = (TextView) rootView
                .findViewById(R.id.search_frag_side_params_tv_endpoint);
        tvEndpointName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEndpointsListDialog();
            }
        });

        LinearLayout layoutEndpoint = (LinearLayout) rootView.findViewById(R.id.search_frag_side_params_layout_endpoint);
        layoutEndpoint.setVisibility(View.VISIBLE);

        LinearLayout layoutTitle = (LinearLayout) rootView.findViewById(R.id.search_frag_side_params_layout_title);
        layoutTitle.setVisibility(View.VISIBLE);

        LinearLayout layoutOrg = (LinearLayout) rootView.findViewById(R.id.search_frag_side_params_layout_org);
        layoutOrg.setVisibility(View.VISIBLE);

        LinearLayout layoutPlatform = (LinearLayout) rootView.findViewById(R.id.search_frag_side_params_layout_platform);
        layoutPlatform.setVisibility(View.VISIBLE);

        TextView tvTitle = (TextView) rootView.findViewById(R.id.search_frag_side_params_tv_title_header);
        tvTitle.setVisibility(View.VISIBLE);

        TextView tvOrg = (TextView) rootView.findViewById(R.id.search_frag_side_params_tv_org_header);
        tvOrg.setVisibility(View.VISIBLE);

        TextView tvPlatform = (TextView) rootView.findViewById(R.id.search_frag_side_params_tv_platform_header);
        tvPlatform.setVisibility(View.VISIBLE);

        EditText edtTitle = (EditText) rootView.findViewById(R.id.search_frag_side_params_editv_title);
        edtTitle.setVisibility(View.VISIBLE);
        edtTitle.addTextChangedListener(new EditTextViewInputWatcher(EDIT_TEXT_TITLE));

        EditText edtOrganisation = (EditText) rootView.findViewById(R.id.search_frag_side_params_editv_org);
        edtOrganisation.setVisibility(View.VISIBLE);
        edtOrganisation.addTextChangedListener(new EditTextViewInputWatcher(EDIT_TEXT_ORGANISATION));

        EditText edtPlatform = (EditText) rootView.findViewById(R.id.search_frag_side_params_editv_platform);
        edtPlatform.setVisibility(View.VISIBLE);
        edtPlatform.addTextChangedListener(new EditTextViewInputWatcher(EDIT_TEXT_PLATFORM));

        return rootView;
    }

    private void showEndpointsListDialog() {
        EndpointsListDialogFragment endpointslistDialFrag = new EndpointsListDialogFragment();
        endpointslistDialFrag.show(getActivity().getSupportFragmentManager(),
                EndpointsListDialogFragment.class.getSimpleName());
    }


    public static class EndpointsListDialogFragment extends DialogFragment {

        OnEndpointsListDialogFragListener mListener;

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);

            Activity activity = context instanceof Activity ? (Activity) context : null;
            try {
                mListener = (OnEndpointsListDialogFragListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + activity.getString(R.string.must_implement)
                        + OnEndpointsListDialogFragListener.class.getSimpleName());
            }
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.endpoint_list_title).setItems(
                    endpointsList, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            setBaseUrl(which);
                        }
                    });
            return builder.create();
        }

        private void setBaseUrl(int which) {
            switch (which) {
                case 0:
                    Const.setHttpEsaBaseUrl();
                    break;
                case 1:
                    Const.setHttpSpacebelBaseUrl();
                    break;
                case 2:
                    Const.setHttpSmaadBaseUrl();
                    break;
                case 3:
                    Const.setHttpObeosBaseUrl();
                    break;
                default:
                    break;
            }
            mListener.OnEndpointsListDialogFragClose();

            tvEndpointName.setText(endpointsList[which]);
        }

        public interface OnEndpointsListDialogFragListener {
            void OnEndpointsListDialogFragClose();
        }
    }

    private class EditTextViewInputWatcher implements TextWatcher {

        private final int choosenEditTv;

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
                case EDIT_TEXT_TITLE:
                    fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_KEY_TITLE, s.toString());
                    break;
                case EDIT_TEXT_ORGANISATION:
                    fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_KEY_ORGANISATION, s.toString());
                    break;
                case EDIT_TEXT_PLATFORM:
                    fedeoRequestParams.addOsddValue(OSDDMatcher.PARAM_KEY_PLATFORM, s.toString());
                    break;
                default:
                    break;
            }
        }
    }
}
