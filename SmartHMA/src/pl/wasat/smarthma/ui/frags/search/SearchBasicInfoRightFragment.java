package pl.wasat.smarthma.ui.frags.search;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.customviews.MyTimePickerDialog;
import pl.wasat.smarthma.customviews.TimePicker;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link SearchBasicInfoRightFragment.OnSearchBasicInfoRightFragmentListener}
 * interface to handle interaction events. Use the
 * {@link SearchBasicInfoRightFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class SearchBasicInfoRightFragment extends Fragment {
	private static final String KEY_COLLECTION_NAME = "pl.wasat.smarthma.COLLECTION_NAME";
	private static final String KEY_BUTTON_TAG = "pl.wasat.smarthma.KEY_BUTTON_TAG";;

	private String paramCollName;

	private TextView tvAreaSWLat;
	private TextView tvAreaSWLon;
	private TextView tvAreaNELat;
	private TextView tvAreaNELon;
	private Calendar calStart;
	private Calendar calEnd;
	private static Button btnFromDate;
	private static Button btnFromTime;
	private static Button btnToDate;
	private static Button btnToTime;

	private OnSearchBasicInfoRightFragmentListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param collectionName
	 *            Parameter 1.
	 * @param collectionDates
	 *            Parameter 2.
	 * @return A new instance of fragment CollectionItemRightFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static SearchBasicInfoRightFragment newInstance(String collectionName) {
		SearchBasicInfoRightFragment fragment = new SearchBasicInfoRightFragment();
		Bundle args = new Bundle();
		args.putString(KEY_COLLECTION_NAME, collectionName);
		fragment.setArguments(args);
		return fragment;
	}

	public SearchBasicInfoRightFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			paramCollName = getArguments().getString(KEY_COLLECTION_NAME);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(
				R.layout.fragment_search_right_basic, container, false);

		((TextView) rootView.findViewById(R.id.search_frag_right_basic_tv_name))
				.setText(paramCollName);
		tvAreaSWLat = (TextView) rootView
				.findViewById(R.id.search_frag_right_basic_tv_area_sw_lat);
		tvAreaSWLon = (TextView) rootView
				.findViewById(R.id.search_frag_right_basic_tv_area_sw_lon);
		tvAreaNELat = (TextView) rootView
				.findViewById(R.id.search_frag_right_basic_tv_area_ne_lat);
		tvAreaNELon = (TextView) rootView
				.findViewById(R.id.search_frag_right_basic_tv_area_ne_lon);
		updateSearchAreaBounds();

		btnFromDate = (Button) rootView
				.findViewById(R.id.search_frag_right_basic_buton_time_from_date);
		btnFromDate.setTag("btnFromDate");
		btnFromDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDatePickerDialog(btnFromDate);

			}
		});
		btnFromTime = (Button) rootView
				.findViewById(R.id.search_frag_right_basic_buton_time_from_time);
		btnFromTime.setTag("btnFromTime");
		btnFromTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showTimePickerDialog(btnFromTime);

			}
		});
		btnToDate = (Button) rootView
				.findViewById(R.id.search_frag_right_basic_buton_time_to_date);
		btnToDate.setTag("btnToDate");
		btnToDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDatePickerDialog(btnToDate);

			}
		});
		btnToTime = (Button) rootView
				.findViewById(R.id.search_frag_right_basic_buton_time_to_time);
		btnToTime.setTag("btnToTime");
		btnToTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showTimePickerDialog(btnToTime);

			}
		});

		setInitDateTime();
		return rootView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onSearchBasicInfoRightFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnSearchBasicInfoRightFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnSearchBasicInfoRightFragmentListener");
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
	public interface OnSearchBasicInfoRightFragmentListener {
		// TODO: Update argument type and name
		public void onSearchBasicInfoRightFragmentInteraction(Uri uri);
	}

	private void updateSearchAreaBounds() {

		LocationManager locationManager = (LocationManager) getActivity()
				.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_LOW);

		Location location = locationManager
				.getLastKnownLocation(locationManager.getBestProvider(criteria,
						true));
		if (location != null) {
			tvAreaSWLat.setText(String.valueOf(location.getLatitude() - 0.1));
			tvAreaSWLon.setText(String.valueOf(location.getLongitude() - 0.15));
			tvAreaNELat.setText(String.valueOf(location.getLatitude() + 0.1));
			tvAreaNELon.setText(String.valueOf(location.getLongitude() + 0.15));
		}
	}

	/**
	 * 
	 */
	private void setInitDateTime() {
		int year = 0;
		int month = 0;
		int day = 0;
		int hourOfDay = 0;
		int minute = 0;
		int second = 0;

		calStart = Calendar.getInstance();
		calStart.roll(Calendar.HOUR_OF_DAY, -6);
		year = calStart.get(Calendar.YEAR);
		month = calStart.get(Calendar.MONTH);
		day = calStart.get(Calendar.DAY_OF_MONTH);
		hourOfDay = calStart.get(Calendar.HOUR_OF_DAY);
		minute = calStart.get(Calendar.MINUTE);
		second = calStart.get(Calendar.SECOND);
		btnFromDate.setText(formatDate(year, month, day));
		btnFromTime.setText(formatTime(hourOfDay, minute, second));

		calEnd = Calendar.getInstance();
		year = calEnd.get(Calendar.YEAR);
		month = calEnd.get(Calendar.MONTH);
		day = calEnd.get(Calendar.DAY_OF_MONTH);
		hourOfDay = calEnd.get(Calendar.HOUR_OF_DAY);
		minute = calEnd.get(Calendar.MINUTE);
		second = calEnd.get(Calendar.SECOND);
		btnToDate.setText(formatDate(year, month, day));
		btnToTime.setText(formatTime(hourOfDay, minute, second));
	}

	private String setDtISO(Calendar cal) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
				Locale.UK);
		String nowAsISO = df.format(cal.getTime());
		return nowAsISO;
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putString(KEY_BUTTON_TAG, (String) v.getTag());
		newFragment.setArguments(args);
		newFragment.show(getActivity().getSupportFragmentManager(),
				"datePicker");
	}

	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new MyTimePickerFragment();
		Bundle args = new Bundle();
		args.putString(KEY_BUTTON_TAG, (String) v.getTag());
		newFragment.setArguments(args);
		newFragment.show(getActivity().getSupportFragmentManager(),
				"timePicker");
	}

	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		private String buttonTag;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			if (getArguments() != null) {
				buttonTag = getArguments().getString(KEY_BUTTON_TAG);
			}

			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {

			String dateToSet = formatDate(year, month, day);

			// String dateToSet = year + "-" + month + "-" + day;
			setButtonDtText(dateToSet, buttonTag);

		}
	}

	public static class MyTimePickerFragment extends DialogFragment implements
			MyTimePickerDialog.OnTimeSetListener {

		private String buttonTag;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker

			if (getArguments() != null) {
				buttonTag = getArguments().getString(KEY_BUTTON_TAG);
			}
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			int second = c.get(Calendar.SECOND);

			// Create a new instance of TimePickerDialog and return it
			return new MyTimePickerDialog(getActivity(), this, hour, minute,
					second, true);

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ikovac.timepickerwithseconds.view.MyTimePickerDialog.
		 * OnTimeSetListener
		 * #onTimeSet(com.ikovac.timepickerwithseconds.view.TimePicker, int,
		 * int, int)
		 */
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute,
				int seconds) {
			String timeToSet = formatTime(hourOfDay, minute, seconds);

			// String timeToSet = hourOfDay + ":" + minute + ":" + seconds;
			setButtonDtText(timeToSet, buttonTag);
		}
	}

	/**
	 * @param dateToSet2
	 * @param buttonTag
	 */
	private static void setButtonDtText(String dtToSet, String buttonTag) {
		if (buttonTag.equalsIgnoreCase("btnFromDate")) {
			btnFromDate.setText(dtToSet);
		} else if (buttonTag.equalsIgnoreCase("btnFromTime")) {
			btnFromTime.setText(dtToSet);
		} else if (buttonTag.equalsIgnoreCase("btnToDate")) {
			btnToDate.setText(dtToSet);
		} else if (buttonTag.equalsIgnoreCase("btnToTime")) {
			btnToTime.setText(dtToSet);
		}
	}

	/**
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private static String formatDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
		String dateToSet = df.format(cal.getTime());
		return dateToSet;
	}

	/**
	 * @param hourOfDay
	 * @param minute
	 * @param seconds
	 * @return
	 */
	private static String formatTime(int hourOfDay, int minute, int seconds) {
		Calendar cal = Calendar.getInstance();
		//String dateTextBtn = but
		cal.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH,
				hourOfDay, minute, seconds);
		SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm:ss", Locale.UK);
		String timeToSet = dfTime.format(cal.getTime());
		return timeToSet;
	}

}
