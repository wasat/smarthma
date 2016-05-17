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

public class LoginDialogFragment extends DialogFragment {

    private OnLoginDialogFragmentListener mListener;
    private View rootView;
    private SharedPrefs sharedPrefs;


    public static LoginDialogFragment newInstance() {
        return new LoginDialogFragment();
    }

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

    public interface OnLoginDialogFragmentListener {
        void onLoginDialogSignIn(String login, String password);
    }
}
