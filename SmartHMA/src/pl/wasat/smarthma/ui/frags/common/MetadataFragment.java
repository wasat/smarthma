package pl.wasat.smarthma.ui.frags.common;

import java.lang.reflect.Field;

import org.apache.commons.lang3.SystemUtils;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.feed.Entry;
import android.app.Activity;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MetadataFragment.OnMetadataFragmentListener} interface to handle
 * interaction events. Use the {@link MetadataFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class MetadataFragment extends Fragment {
	private static final String KEY_ENTRY_ITEM = "pl.wasat.smarthma.KEY_ENTRY_ITEM";

	private Entry entryItem;

	private OnMetadataFragmentListener mListener;

	private LinearLayout linearLayout;

	/*
	 * private LinearLayout itemLayout; private TextView itemTitle; private
	 * TextView itemValues;
	 */

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param entryItm
	 *            Parameter 1.
	 * @return A new instance of fragment MetadataFragment.
	 */
	public static MetadataFragment newInstance(Entry entryItm) {
		MetadataFragment fragment = new MetadataFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_ENTRY_ITEM, entryItm);
		fragment.setArguments(args);
		return fragment;
	}

	public MetadataFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			entryItem = (Entry) getArguments().getSerializable(KEY_ENTRY_ITEM);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_metadata, container,
				false);

		linearLayout = (LinearLayout) rootView
				.findViewById(R.id.metadata_fragment_layout_main_linear);
		((TextView) rootView.findViewById(R.id.metadata_frag_tv_prod_name))
				.setText(entryItem.getTitle());
		((TextView) rootView.findViewById(R.id.metadata_frag_tv_prod_dates))
				.setText("Date of publication: " + entryItem.getDate());

		/*
		 * itemLayout = (LinearLayout) rootView
		 * .findViewById(R.id.metadata_frag_layout_row); itemTitle = ((TextView)
		 * rootView .findViewById(R.id.metadata_frag_tv_row_title)); itemValues
		 * = ((TextView) rootView
		 * .findViewById(R.id.metadata_frag_tv_row_values));
		 */

		if (!entryItem.getEarthObservation().getMetaDataProperty()
				.getEarthObservationMetaData().getIdentifier().get__text()
				.isEmpty()) {
			setEOMetaDataViews("Earth Observation Metadata ", entryItem
					.getEarthObservation().getMetaDataProperty()
					.getEarthObservationMetaData());
		}
		if (!entryItem.getEarthObservation().getObservedProperty()
				.get_nilReason().isEmpty()) {
			setEOMetaDataViews("Earth Observation Observed Property ",
					entryItem.getEarthObservation().getObservedProperty());
		}
		if (entryItem.getEarthObservation().getFeatureOfInterest()
				.getFootprint() != null) {
			setEOMetaDataViews("Earth Observation Footprint", entryItem
					.getEarthObservation().getFeatureOfInterest()
					.getFootprint());
		}
		if (!entryItem.getEarthObservation().getProcedure()
				.getEarthObservationEquipment().getPlatform().isEmpty()) {
			setEOMetaDataViews("Earth Observation Equipment ", entryItem
					.getEarthObservation().getProcedure()
					.getEarthObservationEquipment());
		}
		if (entryItem.getEarthObservation().getResult()
				.getEarthObservationResult() != null) {
			setEOMetaDataViews("Earth Observation Result ", entryItem
					.getEarthObservation().getResult()
					.getEarthObservationResult());
		}
		if (entryItem.getEarthObservation().getPhenomenonTime() != null) {
			setEOMetaDataViews("Earth Observation Phenomenon Time ", entryItem
					.getEarthObservation().getPhenomenonTime().getTimePeriod());
		}
		if (!entryItem.getEarthObservation().getResultTime().getTimeInstant()
				.getTimePosition().get__text().isEmpty()) {
			setEOMetaDataViews("Earth Observation Result Time ", entryItem
					.getEarthObservation().getResultTime().getTimeInstant()
					.getTimePosition());
		}
		return rootView;
	}


	private void setEOMetaDataViews(String headerText, Object eOMetaDataObject) {

		TextView tvMetaHeader = new TextView(getActivity());
		tvMetaHeader.setLayoutParams(new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
		tvMetaHeader.setPadding(16, 2, 16, 16);
		tvMetaHeader.setTextSize(18);
		tvMetaHeader.setTypeface(Typeface.DEFAULT_BOLD);
		tvMetaHeader.setText(headerText);
		linearLayout.addView(tvMetaHeader);

		Class<?> c1 = eOMetaDataObject.getClass();
		// Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = c1.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			fields[i].setAccessible(true);
			Object value = null;
			try {
				value = fields[i].get(eOMetaDataObject);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (value != null) {
				addNewRowView(name, value.toString());
			}
		}
	}

	private void addNewRowView(String title, String value) {
		if (!title.equalsIgnoreCase("additionalProperties")
				&& !title.equalsIgnoreCase("_gml_id")
				&& !title.equalsIgnoreCase("serialVersionUID")
				&& !value.isEmpty())

		{
			TextView itemTvTitle = new TextView(getActivity());
			itemTvTitle.setLayoutParams(new TableLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 2f));
			itemTvTitle.setPadding(10, 2, 2, 2);
			itemTvTitle.setTextSize(16);
			itemTvTitle.setTextColor(0xffA9A9A9);
			itemTvTitle.setTypeface(Typeface.DEFAULT_BOLD);
			itemTvTitle.setText(title);

			TextView itemTvValue = new TextView(getActivity());
			itemTvValue.setLayoutParams(new TableLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
			itemTvValue.setPadding(10, 2, 2, 2);
			itemTvValue.setTextSize(14);
			itemTvValue.setTextColor(0xff646464);
			itemTvValue.setTypeface(Typeface.DEFAULT);
			itemTvValue.setText(cleanValue(value));

			LinearLayout itemLinearRow = new LinearLayout(getActivity());
			itemLinearRow.setLayoutParams(new TableLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1f));
			itemLinearRow.setPadding(10, 2, 2, 2);
			itemLinearRow.addView(itemTvTitle);
			itemLinearRow.addView(itemTvValue);

			linearLayout.addView(itemLinearRow);

		}

	}
	
	private String cleanValue(String value)
	{
		String cleanedValue = "";
		
		String doubleNewLine  = SystemUtils.LINE_SEPARATOR + SystemUtils.LINE_SEPARATOR;
		cleanedValue = value.replaceAll(doubleNewLine, SystemUtils.LINE_SEPARATOR);
		cleanedValue = cleanedValue.replaceAll("\\[", "");
		cleanedValue = cleanedValue.replaceAll("\\]", "");
		cleanedValue = cleanedValue.replaceAll(" _", "");
		return cleanedValue;
		
	}



	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onMetadataFragmentInteraction();
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnMetadataFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnMetadataFragmentListener");
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
	public interface OnMetadataFragmentListener {
		public void onMetadataFragmentInteraction();
	}

}
