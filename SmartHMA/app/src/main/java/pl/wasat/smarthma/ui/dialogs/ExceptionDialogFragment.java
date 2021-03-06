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

package pl.wasat.smarthma.ui.dialogs;

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

    /**
     * New instance exception dialog fragment.
     *
     * @param message      the message
     * @param exRawMessage the ex raw message
     * @param exRawCause   the ex raw cause
     * @return the exception dialog fragment
     */
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
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                ((BaseSmartHMActivity) getActivity())
                                        .doPositiveClick();
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
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

    private void showDialog(String messText, String exRawMessage, String exRawCause) {
        DialogFragment exceptionDialogFragment = ExceptionDialogFragment
                .newInstance(messText, exRawMessage, exRawCause);
        exceptionDialogFragment.show(getFragmentManager(), ExceptionDialogFragment.class.getSimpleName());
    }

    private String obtainFormattedUrl() {
        SharedPrefs sharedPrefs = new SharedPrefs(getActivity());
        return sharedPrefs.getUrlPrefs();
    }


}
