package pl.wasat.smarthma.ui.frags;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.activities.NewsActivity;
import pl.wasat.smarthma.ui.activities.MainSmartHMActivity;
import pl.wasat.smarthma.ui.activities.MissionsActivity;
import pl.wasat.smarthma.ui.activities.SearchActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link StartFragment.OnStartFragmentListener} interface to handle interaction
 * events. Use the {@link StartFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class StartFragment extends Fragment {

	private OnStartFragmentListener mListener;
	private Button buttonStartSearch;
	private Button buttonStartBrowse;
	private Button buttonStartMission;
	private Button buttonStartOnline;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		// return inflater.inflate(R.layout.fragment_start, container, false);

		View rootView = inflater.inflate(R.layout.fragment_start, container,
				false);

		buttonStartSearch = (Button) rootView
				.findViewById(R.id.start_frag_button_search);
		buttonStartSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent startIntent = new Intent(getActivity(),
						SearchActivity.class);
				startActivity(startIntent);

			}
		});

		buttonStartBrowse = (Button) rootView
				.findViewById(R.id.start_frag_button_browse);
		buttonStartBrowse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent startIntent = new Intent(getActivity(),
						MainSmartHMActivity.class);
				startActivity(startIntent);
			}
		});
		buttonStartMission = (Button) rootView
				.findViewById(R.id.start_frag_button_mission);
		buttonStartMission.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent startMissionIntent = new Intent(getActivity(),
						MissionsActivity.class);
				startActivity(startMissionIntent);
			}
		});
		buttonStartOnline = (Button) rootView
				.findViewById(R.id.start_frag_button_online);
		buttonStartOnline.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent startEsaNewsIntent = new Intent(getActivity(),
						NewsActivity.class);
				startActivity(startEsaNewsIntent);
			}
		});

		return rootView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
	 * android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// tvFailure = (TextView) getView().findViewById(R.id.failure_text);
		// tvFailure.setText(mParam1);
		super.onViewCreated(view, savedInstanceState);
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onStartFragmentInteraction(uri);
		}
	}

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
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnStartFragmentListener {
		// TODO: Update argument type and name
		public void onStartFragmentInteraction(Uri uri);
	}

}
