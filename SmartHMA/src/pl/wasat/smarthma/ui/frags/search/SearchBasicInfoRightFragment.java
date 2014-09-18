package pl.wasat.smarthma.ui.frags.search;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.customviews.SmHmaTimePickerDialog;
import pl.wasat.smarthma.customviews.TimePicker;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.ui.frags.common.MapSearchFragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLngBounds;

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
	private static final String KEY_BUTTON_TAG = "pl.wasat.smarthma.KEY_BUTTON_TAG";

    private String paramCollName;

	private TextView tvAreaSWLat;
	private TextView tvAreaSWLon;
	private TextView tvAreaNELat;
	private TextView tvAreaNELon;

	private static TextView tvCatalogName;

	private static Calendar calStart;
	private static Calendar calEnd;
	private static Button btnFromDate;
	private static Button btnFromTime;
	private static Button btnToDate;
	private static Button btnToTime;

	private OnSearchBasicInfoRightFragmentListener mListener;

	private static final CharSequence[] cataloguesList = { "FEDEO",
			"FEDEO:COLLECTIONS", "GPOD-EO", "EO-VIRTUAL-ARCHIVE4",
			"REFERENCEDATA" };

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @return A new instance of fragment SearchBasicInfoRightFragment.
	 */
	public static SearchBasicInfoRightFragment newInstance() {
		SearchBasicInfoRightFragment fragment = new SearchBasicInfoRightFragment();
		Bundle args = new Bundle();
		args.putString(KEY_COLLECTION_NAME, "EO Dataset Series Search");
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

		tvCatalogName = (TextView) rootView
				.findViewById(R.id.search_frag_right_basic_tv_catalog_name);

		tvCatalogName.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showCatalogueListDialog();
			}
		});
		setParentIdPrefs(getActivity(), tvCatalogName.getText().toString());
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

		LinearLayout areaLayout = (LinearLayout) rootView
				.findViewById(R.id.search_frag_right_basic_layout_area);
		areaLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MapSearchFragment mapSearchFragment = MapSearchFragment
						.newInstance();
				getActivity()
						.getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.search_activ_left_container,
								mapSearchFragment)
						.addToBackStack("MapSearchFragment").commit();

			}
		});

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

		updateSearchAreaBounds();
		setInitDateTime();

		return rootView;
	}

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
			tvAreaSWLat.setText(String.format(Locale.UK, "% 4f",
					(float) (location.getLatitude() - 0.5)));
			tvAreaSWLon.setText(String.format(Locale.UK, "% 4f",
					(float) location.getLongitude() - 0.5));
			tvAreaNELat.setText(String.format(Locale.UK, "% 4f",
					(float) location.getLatitude() + 0.5));
			tvAreaNELon.setText(String.format(Locale.UK, "% 4f",
					(float) location.getLongitude() + 0.5));
		}

		setBboxPrefs();
	}

	/**
	 * 
	 */
	private void setInitDateTime() {

		calStart = Calendar.getInstance();
		calStart.roll(Calendar.YEAR, -1);
		btnFromDate.setText(formatDate(calStart));
		btnFromTime.setText(formatTime(calStart));

		calEnd = Calendar.getInstance();
		btnToDate.setText(formatDate(calEnd));
		btnToTime.setText(formatTime(calEnd));

		setDateTimePrefs(getActivity());
	}

	public void showCatalogueListDialog() {
		CatalogueListDialogFragment listDialFrag = new CatalogueListDialogFragment();
		listDialFrag.show(getActivity().getSupportFragmentManager(),
				"CatalogueListDialogFragment");
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putString(KEY_BUTTON_TAG, (String) v.getTag());
		newFragment.setArguments(args);
		newFragment.show(getActivity().getSupportFragmentManager(),
				"datePicker");
	}

	void showTimePickerDialog(View v) {
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

			if (buttonTag.equalsIgnoreCase("btnFromDate")) {
				calStart.set(year, month, day);
				String dateToSet = formatDate(calStart);
				btnFromDate.setText(dateToSet);

			} else if (buttonTag.equalsIgnoreCase("btnToDate")) {
				calEnd.set(year, month, day);
				String dateToSet = formatDate(calEnd);
				btnToDate.setText(dateToSet);
			}
			setDateTimePrefs(getActivity());

		}
	}

	public static class MyTimePickerFragment extends DialogFragment implements
			SmHmaTimePickerDialog.OnTimeSetListener {

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
			return new SmHmaTimePickerDialog(getActivity(), this, hour, minute,
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

			if (buttonTag.equalsIgnoreCase("btnFromTime")) {
				calStart.set(calStart.get(Calendar.YEAR),
						calStart.get(Calendar.MONTH),
						calStart.get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
						seconds);
				String timeToSet = formatTime(calStart);
				btnFromTime.setText(timeToSet);

			} else if (buttonTag.equalsIgnoreCase("btnToTime")) {
				calEnd.set(calEnd.get(Calendar.YEAR),
						calEnd.get(Calendar.MONTH),
						calEnd.get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
						seconds);
				String timeToSet = formatTime(calEnd);
				btnToTime.setText(timeToSet);
			}
			setDateTimePrefs(getActivity());
		}
	}

	public static class CatalogueListDialogFragment extends DialogFragment {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle(R.string.eo_catalogue_list_title).setItems(
					cataloguesList, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							tvCatalogName.setText(cataloguesList[which]);
							setParentIdPrefs(getActivity(),
									cataloguesList[which].toString());
						}
					});
			return builder.create();
		}
	}

	/**
	 * @param cal
	 * @return
	 */
	private static String formatDate(Calendar cal) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
		return df.format(cal.getTime());
	}

	/**
	 * @param cal
	 * @return
	 */
	private static String formatTime(Calendar cal) {
		SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm:ss", Locale.UK);
		return dfTime.format(cal.getTime());
	}

	private static String setDtISO(Calendar cal) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
				Locale.UK);
		return df.format(cal.getTime());
	}

	/**
	 * 
	 */
	private static void setDateTimePrefs(Context context) {
		SharedPreferences settings = context.getSharedPreferences(
				Const.KEY_PREF_FILE, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(Const.KEY_PREF_DATETIME_START, setDtISO(calStart));
		editor.putString(Const.KEY_PREF_DATETIME_END, setDtISO(calEnd));
		editor.apply();

		Log.i("DT", setDtISO(calStart) + " - " + setDtISO(calEnd));
	}

	private static void setParentIdPrefs(Context context, String parentId) {
		SharedPreferences settings = context.getSharedPreferences(
				Const.KEY_PREF_FILE, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(Const.KEY_PREF_PARENT_ID, "EOP:ESA:" + parentId);
		editor.apply();
	}

	private void setBboxPrefs() {

		SharedPreferences settings = getActivity().getSharedPreferences(
				Const.KEY_PREF_FILE, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putFloat(Const.KEY_PREF_BBOX_WEST,
				Float.valueOf(tvAreaSWLon.getText().toString()));
		editor.putFloat(Const.KEY_PREF_BBOX_SOUTH,
				Float.valueOf(tvAreaSWLat.getText().toString()));
		editor.putFloat(Const.KEY_PREF_BBOX_EAST,
				Float.valueOf(tvAreaNELon.getText().toString()));
		editor.putFloat(Const.KEY_PREF_BBOX_NORTH,
				Float.valueOf(tvAreaNELat.getText().toString()));
		editor.apply();
	}

	/**
	 * @param bounds
	 */
	public void updateCollectionsAreaBounds(LatLngBounds bounds) {
		tvAreaSWLat.setText(String.format(Locale.UK, "% 4f", bounds.southwest.latitude));
		tvAreaSWLon.setText(String.format(Locale.UK, "% 4f", bounds.southwest.longitude));
		tvAreaNELat.setText(String.format(Locale.UK, "% 4f", bounds.northeast.latitude));
		tvAreaNELon.setText(String.format(Locale.UK, "% 4f", bounds.northeast.longitude));

		setBboxPrefs();

	}
}
