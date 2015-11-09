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
import pl.wasat.smarthma.ui.frags.base.BaseSearchSideParametersFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link OnSearchAdvancedParametersFragmentListener}
 * interface to handle interaction events. Use the
 * {@link SearchAdvancedParametersFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class SearchAdvancedParametersFragment extends BaseSearchSideParametersFragment {

    private static final int EDIT_TEXT_TITLE = 1;
    private static final int EDIT_TEXT_ORGANISATION = 2;
    private static final int EDIT_TEXT_PLATFORM = 3;
    private static final CharSequence[] endpointsList = {"fedeo.esa.int",
            "geo.spacebel.be", "smaad.spacebel.be"};
    private static TextView tvEndpointName;

    private OnSearchAdvancedParametersFragmentListener mListener;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment SearchBasicInfoRightFragment.
     */
    public static SearchAdvancedParametersFragment newInstance() {
        return new SearchAdvancedParametersFragment();
    }

    public SearchAdvancedParametersFragment() {
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnSearchAdvancedParametersFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSearchAdvancedParametersFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private void showEndpointsListDialog() {
        EndpointsListDialogFragment endpointslistDialFrag = new EndpointsListDialogFragment();
        endpointslistDialFrag.show(getActivity().getSupportFragmentManager(),
                "EndpointsListDialogFragment");
    }

    public static class EndpointsListDialogFragment extends DialogFragment {
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
                default:
                    break;
            }
            tvEndpointName.setText(endpointsList[which]);
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
                    mListener.onSearchAdvancedParamsFragmentEditTextChange("title", s.toString());
                    break;
                case EDIT_TEXT_ORGANISATION:
                    mListener.onSearchAdvancedParamsFragmentEditTextChange("organisation", s.toString());
                    break;
                case EDIT_TEXT_PLATFORM:
                    mListener.onSearchAdvancedParamsFragmentEditTextChange("platform", s.toString());
                    break;
                default:
                    mListener.onSearchAdvancedParamsFragmentEditTextChange("", "");
                    break;
            }
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
    public interface OnSearchAdvancedParametersFragmentListener {
        void onSearchAdvancedParamsFragmentEditTextChange(String parameterKey, String parameterValue);
    }

}
