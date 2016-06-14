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

/**
 * Created by Daniel on 2016-03-01.
 * This file is a part of module SmartHMA project.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.preferences.SharedPrefs;

/**
 * The type Login dialog fragment.
 */
public class LoginDialogFragment extends DialogFragment {

    private OnLoginDialogFragmentListener mListener;
    private View rootView;
    private SharedPrefs sharedPrefs;


    /**
     * New instance login dialog fragment.
     *
     * @return the login dialog fragment
     */
    public static LoginDialogFragment newInstance() {
        return new LoginDialogFragment();
    }

    /**
     * Instantiates a new Login dialog fragment.
     */
    public LoginDialogFragment() {
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        rootView = inflater.inflate(R.layout.dialog_signin, null);

        sharedPrefs = new SharedPrefs(getActivity());
        String username = sharedPrefs.getLoginPrefs();
        String password = sharedPrefs.getPasswordPrefs();

        ((EditText) rootView.findViewById(R.id.dialog_login_et_username))
                .setText(username);
        ((EditText) rootView
                .findViewById(R.id.dialog_login_et_pass)).setText(password);

        builder.setView(rootView)
                // Add action buttons
                .setPositiveButton(R.string.signin,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                onSignInBtnClick();
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                LoginDialogFragment.this.getDialog().cancel();
                            }
                        });


        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnLoginDialogFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnLoginDialogFragmentListener.class.getSimpleName());
        }
    }

    private void onSignInBtnClick() {
        String username = ((EditText) rootView.findViewById(R.id.dialog_login_et_username))
                .getText().toString();
        String password = ((EditText) rootView
                .findViewById(R.id.dialog_login_et_pass)).getText().toString();
        sharedPrefs.setLoginPrefs(username);
        sharedPrefs.setPasswordPrefs(password);
        mListener.onLoginDialogSignIn(username, password);
        dismiss();
    }

    /**
     * The interface On login dialog fragment listener.
     */
    public interface OnLoginDialogFragmentListener {
        /**
         * On login dialog sign in.
         *
         * @param login    the login
         * @param password the password
         */
        void onLoginDialogSignIn(String login, String password);
    }
}
