package pl.wasat.smarthma.ui.frags.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.activities.CollectionsDefinitionActivity;
import pl.wasat.smarthma.ui.activities.MissionsActivity;
import pl.wasat.smarthma.ui.activities.NewsActivity;
import pl.wasat.smarthma.ui.activities.SearchActivity;
import pl.wasat.smarthma.ui.menus.CommonMenuHandler;
import pl.wasat.smarthma.utils.conn.ConnectionDetector;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the {@link StartFragment#newInstance}
 * factory method to create an instance of this fragment.
 */
public class StartFragment extends Fragment {

    public StartFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment FailureFragment.
     */
    public static StartFragment newInstance() {
        StartFragment fragment = new StartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isNotTabletSize()) showTabletWarningDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start, container,
                false);
        Button buttonStartSearch = (Button) rootView
                .findViewById(R.id.start_frag_button_search);
        buttonStartSearch.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    Intent startIntent = new Intent(getActivity(),
                            SearchActivity.class);
                    startActivity(startIntent);
                }
            }
        });

        Button buttonStartBrowse = (Button) rootView
                .findViewById(R.id.start_frag_button_browse);
        buttonStartBrowse.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    Intent startIntent = new Intent(getActivity(),
                            CollectionsDefinitionActivity.class);
                    startActivity(startIntent);
                }
            }
        });

        Button buttonStartMission = (Button) rootView
                .findViewById(R.id.start_frag_button_mission);
        buttonStartMission.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    Intent startMissionIntent = new Intent(getActivity(),
                            MissionsActivity.class);
                    startActivity(startMissionIntent);
                }
            }
        });
        Button buttonStartOnline = (Button) rootView
                .findViewById(R.id.start_frag_button_online);
        buttonStartOnline.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    Intent startEsaNewsIntent = new Intent(getActivity(),
                            NewsActivity.class);
                    startActivity(startEsaNewsIntent);
                }
            }
        });
        new CommonMenuHandler(rootView, getActivity(), R.id.menu_button);

        return rootView;
    }

    private boolean isConnected() {
        ConnectionDetector detect = new ConnectionDetector(getActivity());

        if (detect.isConnectingToInternet()) {
            return true;
        } else {
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            startActivity(intent);
            return false;
        }
    }

    public boolean isNotTabletSize() {

        double size = 0;
        try {
            DisplayMetrics dm = this.getResources().getDisplayMetrics();
            float screenWidth = dm.widthPixels / dm.xdpi;
            float screenHeight = dm.heightPixels / dm.ydpi;
            size = Math.sqrt(Math.pow(screenWidth, 2) +
                    Math.pow(screenHeight, 2));
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return size < 6;
    }

    private void showTabletWarningDialog() {
        TabletWarningDialogFragment tabletDialFrag = new TabletWarningDialogFragment();
        tabletDialFrag.show(getActivity().getSupportFragmentManager(),
                TabletWarningDialogFragment.class.getSimpleName());

        //((TextView)tabletDialFrag.getDialog().findViewById(android.R.id.message))
        //        .setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static class TabletWarningDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final TextView tvMessage = new TextView(getActivity());
            tvMessage.setPadding(30, 20, 30, 20);
            final SpannableString s =
                    new SpannableString(getActivity().getText(R.string.dev_size_warning_message));
            Linkify.addLinks(s, Linkify.WEB_URLS);
            tvMessage.setText(s);
            tvMessage.setMovementMethod(LinkMovementMethod.getInstance());

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder
                    .setIcon(R.mipmap.ic_launcher_circle)
                    .setTitle(R.string.dev_size_warning)
                            //.setMessage(s)
                    .setView(tvMessage)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finish();
                        }
                    });
            return builder.create();
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            super.onDismiss(dialog);
            getActivity().finish();
        }
    }
}
