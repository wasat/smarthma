package pl.wasat.smarthma.ui.frags.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.activities.CollectionsDefinitionActivity;
import pl.wasat.smarthma.ui.activities.MissionsActivity;
import pl.wasat.smarthma.ui.activities.NewsActivity;
import pl.wasat.smarthma.ui.activities.SearchActivity;
import pl.wasat.smarthma.utils.conn.ConnectionDetector;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link StartFragment.OnStartFragmentListener} interface to handle interaction
 * events. Use the {@link StartFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class StartFragment extends Fragment {

    private OnStartFragmentListener mListener;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment FailureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartFragment newInstance() {
        StartFragment fragment = new StartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

        return rootView;
    }

/*    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onStartFragmentConnectionSetup();
        }
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnStartFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnStartFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnStartFragmentListener {
        //public void onStartFragmentConnectionSetup();
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
}
