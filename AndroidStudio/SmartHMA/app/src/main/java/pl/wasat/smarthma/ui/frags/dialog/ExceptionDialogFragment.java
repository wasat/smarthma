package pl.wasat.smarthma.ui.frags.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.activities.base.BaseSmartHMActivity;

/**
 * Created by Daniel on 2015-09-21.
 * This file is a part of module SmartHMA project.
 */
public class ExceptionDialogFragment extends DialogFragment {

    private static final String MESSAGE = "MESSAGE";
    private static final String RAW_MESSAGE = "RAW_MESSAGE";
    private static final String RAW_CAUSE = "RAW_CAUSE";

    public static ExceptionDialogFragment newInstance(String message, String exRawMessage, String exRawCause) {
        ExceptionDialogFragment frag = new ExceptionDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", R.string.alert_dialog_response_error);
        args.putString(MESSAGE, message);
        args.putString(RAW_MESSAGE, exRawMessage);
        args.putString(RAW_CAUSE, exRawCause);
        frag.setArguments(args);
        return frag;
    }

    private void showDialog(String messText, String exRawMessage, String exRawCause) {
        DialogFragment exceptionDialogFragment = ExceptionDialogFragment
                .newInstance(messText, exRawMessage, exRawCause);
        exceptionDialogFragment.show(getFragmentManager(), "ExceptionDialogFragment");
    }

    private String formatDetailMessage(String rawMess, String rawCause) {

        String NEW_LINE = System.getProperty("line.separator");
        String url = obtainFormattedUrl();
        return "URL:" + NEW_LINE +
                url + NEW_LINE + NEW_LINE +
                "EXCEPTION:" + NEW_LINE +
                rawMess + NEW_LINE + NEW_LINE +
                "CAUSE:" + NEW_LINE +
                rawCause + NEW_LINE;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        String exMess = getArguments().getString(MESSAGE);
        final String exRawMess = getArguments().getString(RAW_MESSAGE);
        final String exRawCause = getArguments().getString(RAW_CAUSE);

        GlobalPreferences globalPreferences = new GlobalPreferences(getActivity());

        AlertDialog.Builder exceptionAlertDialog = new AlertDialog.Builder(getActivity());
        exceptionAlertDialog.setIcon(R.drawable.ic_action_name)
                .setTitle(title)
                .setMessage(
                        exMess
                                + getString(R.string.please_correct_your_query_))
                .setPositiveButton(R.string.alert_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                ((BaseSmartHMActivity) getActivity())
                                        .doPositiveClick();
                            }
                        })
                .setNegativeButton(R.string.alert_dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                ((BaseSmartHMActivity) getActivity())
                                        .doNegativeClick();
                            }
                        });
        if (globalPreferences.getIsDebugMode()) {
            exceptionAlertDialog.setNeutralButton(R.string.alert_dialog_details, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int whichButton) {
                    String detailMessage = formatDetailMessage(exRawMess, exRawCause);
                    showDialog(detailMessage, exRawMess, exRawCause);
                }
            });
        }
        return exceptionAlertDialog.create();

    }

    private String obtainFormattedUrl() {
        SharedPrefs sharedPrefs = new SharedPrefs(getActivity());
        return sharedPrefs.getUrlPrefs();
    }
}
