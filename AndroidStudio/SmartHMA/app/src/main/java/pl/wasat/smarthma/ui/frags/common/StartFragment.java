package pl.wasat.smarthma.ui.frags.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

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

        View menu = rootView.findViewById(R.id.menu_button);
        menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // openMenu(v);

                int[] location = new int[2];
                //currentRowId = position;
                //currentRow = v;
                // Get the x, y location and store it in the location[] array
                // location[0] = x, location[1] = y.
                v.getLocationOnScreen(location);

                //Initialize the Point with x, and y positions
                Point point = new Point();
                point.x = location[0];
                point.y = location[1];
                showMenuPopup(getActivity(), point);

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

    private void openMenu(View menu){

        PopupMenu popup = new PopupMenu(getActivity(), menu);

        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.example_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.sort:

                        break;
                    case R.id.favourite:


                        break;

                    case R.id.refresh:


                        break;

                    default:
                        break;
                }


                return true;
            }
        });

        popup.show();//showing popup menu



    }

    private void showMenuPopup(final Activity context, Point p) {

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup_menu_layout);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_menu, null);

        // Creating the PopupWindow
        PopupWindow changeStatusPopUp = new PopupWindow(context);
        changeStatusPopUp.setContentView(layout);
        changeStatusPopUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeStatusPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeStatusPopUp.setFocusable(true);

        // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
        int OFFSET_X = 0; //-77;
        int OFFSET_Y = 0; //120;

        //Clear the default translucent background
        changeStatusPopUp.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        changeStatusPopUp.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
    }
}
