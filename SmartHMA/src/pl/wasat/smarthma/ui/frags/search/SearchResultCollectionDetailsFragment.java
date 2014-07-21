package pl.wasat.smarthma.ui.frags.search;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.customviews.SmHmaTimePickerDialog;
import pl.wasat.smarthma.customviews.TimePicker;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.ui.frags.MapSearchFragment;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link SearchResultCollectionDetailsFragment.OnSearchResultCollectionDetailsFragmentListener}
 * interface to handle interaction events. Use the
 * {@link SearchResultCollectionDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 
 */
public class SearchResultCollectionDetailsFragment extends Fragment {
	private static final String KEY_COLLECTION_ENTRY = "pl.wasat.smarthma.COLLECTION_NAME";
	private static final String KEY_TEXTVIEW_TAG = "pl.wasat.smarthma.KEY_TEXTVIEW_TAG";

	private TextView tvAreaSWLat;
	private TextView tvAreaSWLon;
	private TextView tvAreaNELat;
	private TextView tvAreaNELon;

	private static TextView tvParentId;

	private static Calendar calStart;
	private static Calendar calEnd;
	private static TextView tvFromDate;
	private static TextView tvFromTime;
	private static TextView tvToDate;
	private static TextView tvToTime;

    private Button btnAdvSettings;

	private Entry displayedEntry;

	private LatLngBounds geoBounds = null;

	private OnSearchResultCollectionDetailsFragmentListener mListener;

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
	public static SearchResultCollectionDetailsFragment newInstance(
			Entry collectionEntry) {
		SearchResultCollectionDetailsFragment fragment = new SearchResultCollectionDetailsFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_COLLECTION_ENTRY, collectionEntry);
		fragment.setArguments(args);
		return fragment;
	}

	public SearchResultCollectionDetailsFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			displayedEntry = (Entry) getArguments().getSerializable(
					KEY_COLLECTION_ENTRY);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(
				R.layout.fragment_search_result_collection_details, container,
				false);

		if (displayedEntry != null) {
			final String title = displayedEntry.getTitle();
			final String pubDate = "This data were published: "
					+ displayedEntry.getPublished() + " and updated: "
					+ displayedEntry.getUpdated();

			String content = displayedEntry.getSummary();
			((TextView) rootView
					.findViewById(R.id.frag_search_res_coll_det_tv_coll_name))
					.setText(title);
			((TextView) rootView
					.findViewById(R.id.frag_search_res_coll_det_tv_col_dates))
					.setText(pubDate);
			WebView detailWebView = (WebView) rootView
					.findViewById(R.id.frag_search_res_coll_det_web);
			detailWebView.loadData(content, "text/html", "UTF-8");

		}

		tvParentId = (TextView) rootView
				.findViewById(R.id.frag_search_res_coll_det_tv_parent_id);
		setParentIdPrefs(getActivity(), tvParentId.getText().toString());

		tvAreaSWLat = (TextView) rootView
				.findViewById(R.id.frag_search_res_coll_det_tv_area_sw_lat);
		tvAreaSWLon = (TextView) rootView
				.findViewById(R.id.frag_search_res_coll_det_tv_area_sw_lon);
		tvAreaNELat = (TextView) rootView
				.findViewById(R.id.frag_search_res_coll_det_tv_area_ne_lat);
		tvAreaNELon = (TextView) rootView
				.findViewById(R.id.frag_search_res_coll_det_tv_area_ne_lon);

		LinearLayout areaLayout = (LinearLayout) rootView
				.findViewById(R.id.frag_search_res_coll_det_layout_area);
		areaLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MapSearchFragment mapSearchFragment = MapSearchFragment
						.newInstance();
				getActivity()
						.getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.frag_search_res_coll_det_layout_top,
								mapSearchFragment)
						.addToBackStack("MapSearchFragment").commit();

			}
		});

		tvFromDate = (TextView) rootView
				.findViewById(R.id.frag_search_res_coll_det_tv_time_from_date);
		tvFromDate.setTag("tvFromDate");
		tvFromDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDatePickerDialog(tvFromDate);

			}
		});
		tvFromTime = (TextView) rootView
				.findViewById(R.id.frag_search_res_coll_det_tv_time_from_time);
		tvFromTime.setTag("tvFromTime");
		tvFromTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showTimePickerDialog(tvFromTime);

			}
		});
		tvToDate = (TextView) rootView
				.findViewById(R.id.frag_search_res_coll_det_tv_time_to_date);
		tvToDate.setTag("tvToDate");
		tvToDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDatePickerDialog(tvToDate);

			}
		});
		tvToTime = (TextView) rootView
				.findViewById(R.id.frag_search_res_coll_det_tv_time_to_time);
		tvToTime.setTag("tvToTime");
		tvToTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showTimePickerDialog(tvToTime);

			}
		});

		getParentIdPrefs();
		getBboxPrefs();
		getDateTimePrefs();

        Button btnSearch = (Button) rootView
                .findViewById(R.id.frag_search_res_coll_det_btn_search_product);
		btnSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mListener != null) {
                    FedeoRequest request = new FedeoRequest();
                    request.setDefaultParams();
                    request.setParentIdentifier(displayedEntry.getIdentifier());
                    request.setBbox(geoBounds);
                    mListener.onSearchResultCollectionDetailsFragmentShowProducts(request);
                }
            }
        });

        for (Link entityLink : displayedEntry.getLinks()) {
            if (entityLink.get_rel().equalsIgnoreCase("search")) {
                btnSearch.setEnabled(true);
            }

        }

        Button btnShowMap = (Button) rootView
                .findViewById(R.id.frag_search_res_coll_det_btn_show_map);
		btnShowMap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MapSearchFragment mapSearchFragment = MapSearchFragment
                        .newInstance();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_search_res_coll_det_layout_top,
                                mapSearchFragment)
                        .addToBackStack("MapSearchFragment").commit();

            }
        });

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnSearchResultCollectionDetailsFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnSearchBasicSettingsFragmentListener");
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
	public interface OnSearchResultCollectionDetailsFragmentListener {
		// TODO: Update argument type and name
		public void onSearchResultCollectionDetailsFragmentShowProducts(FedeoRequest request);
	}

	private void getDateTimePrefs() {
		SharedPreferences settings = getActivity().getSharedPreferences(
				Const.KEY_PREF_FILE, 0);
		tvFromTime.setText(settings.getString(Const.KEY_PREF_DATETIME_START,
				"1970.01.01"));
		tvToTime.setText(settings.getString(Const.KEY_PREF_DATETIME_END,
				"1970.01.01"));

	}

	private void getParentIdPrefs() {
		SharedPreferences settings = getActivity().getSharedPreferences(
				Const.KEY_PREF_FILE, 0);
		tvParentId.setText(settings
				.getString(Const.KEY_PREF_PARENT_ID, "Fedeo"));
	}

	private void getBboxPrefs() {
		SharedPreferences settings = getActivity().getSharedPreferences(
				Const.KEY_PREF_FILE, 0);

		float neLat = settings.getFloat(Const.KEY_PREF_BBOX_NORTH, 0.0f);
		float neLon = settings.getFloat(Const.KEY_PREF_BBOX_EAST, 0.0f);
		float swLat = settings.getFloat(Const.KEY_PREF_BBOX_SOUTH, 0.0f);
		float swLon = settings.getFloat(Const.KEY_PREF_BBOX_WEST, 0.0f);

		tvAreaNELat.setText(String.format(Locale.UK, "% 4f", (float) neLat));
		tvAreaNELon.setText(String.format(Locale.UK, "% 4f", (float) neLon));
		tvAreaSWLat.setText(String.format(Locale.UK, "% 4f", (float) swLat));
		tvAreaSWLon.setText(String.format(Locale.UK, "% 4f", (float) swLon));

		geoBounds = new LatLngBounds(new LatLng(swLat, swLon), new LatLng(
				neLat, neLon));

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
	public void updateSearchAreaBounds(LatLngBounds bounds) {
		geoBounds = bounds;
		tvAreaSWLat.setText(String.format(Locale.UK, "% 4f",
				bounds.southwest.latitude));
		tvAreaSWLon.setText(String.format(Locale.UK, "% 4f",
				bounds.southwest.longitude));
		tvAreaNELat.setText(String.format(Locale.UK, "% 4f",
				bounds.northeast.latitude));
		tvAreaNELon.setText(String.format(Locale.UK, "% 4f",
				bounds.northeast.longitude));
		setBboxPrefs();
	}

	/**
	 * 
	 */
	// private void setInitDateTime() {
	//
	// calStart = Calendar.getInstance();
	// calStart.roll(Calendar.HOUR_OF_DAY, -6);
	// tvFromDate.setText(formatDate(calStart));
	// tvFromTime.setText(formatTime(calStart));
	//
	// calEnd = Calendar.getInstance();
	// tvToDate.setText(formatDate(calEnd));
	// tvToTime.setText(formatTime(calEnd));
	//
	// setDateTimePrefs(getActivity());
	// }

    void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putString(KEY_TEXTVIEW_TAG, (String) v.getTag());
		newFragment.setArguments(args);
		newFragment.show(getActivity().getSupportFragmentManager(),
				"datePicker");
	}

	void showTimePickerDialog(View v) {
		DialogFragment newFragment = new MyTimePickerFragment();
		Bundle args = new Bundle();
		args.putString(KEY_TEXTVIEW_TAG, (String) v.getTag());
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
				buttonTag = getArguments().getString(KEY_TEXTVIEW_TAG);
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
				tvFromDate.setText(dateToSet);

			} else if (buttonTag.equalsIgnoreCase("btnToDate")) {
				calEnd.set(year, month, day);
				String dateToSet = formatDate(calEnd);
				tvToDate.setText(dateToSet);
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
				buttonTag = getArguments().getString(KEY_TEXTVIEW_TAG);
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
				tvFromTime.setText(timeToSet);

			} else if (buttonTag.equalsIgnoreCase("btnToTime")) {
				calEnd.set(calEnd.get(Calendar.YEAR),
						calEnd.get(Calendar.MONTH),
						calEnd.get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
						seconds);
				String timeToSet = formatTime(calEnd);
				tvToTime.setText(timeToSet);
			}
			setDateTimePrefs(getActivity());
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

}
