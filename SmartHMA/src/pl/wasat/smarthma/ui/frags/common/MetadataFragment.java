package pl.wasat.smarthma.ui.frags.common;

import org.apache.commons.lang.SystemUtils;

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

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment MetadataFragment.
	 */
	// TODO: Rename and change types and number of parameters
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

		((TextView) rootView.findViewById(R.id.metadata_frag_tv_prod_name))
				.setText(entryItem.getTitle());
		((TextView) rootView.findViewById(R.id.metadata_frag_tv_prod_dates))
				.setText("Date of publication: " + entryItem.getDate());

		linearLayout = (LinearLayout) rootView
				.findViewById(R.id.metadata_fragment_layout_main_linear);

		if (!entryItem.getEarthObservation().getMetaDataProperty()
				.getEarthObservationMetaData().getIdentifier().get__text()
				.isEmpty()) {
			setMetaDataViews("Earth Observation Metadata ", entryItem
					.getEarthObservation().getMetaDataProperty()
					.getEarthObservationMetaData().toString());
		}
		if (!entryItem.getEarthObservation().getObservedProperty()
				.get_nilReason().isEmpty()) {
			setMetaDataViews("Earth Observation Observed Property ", entryItem
					.getEarthObservation().getObservedProperty().toString());
		}
		if (entryItem.getEarthObservation().getFeatureOfInterest()
				.getFootprint() != null) {
			setMetaDataViews("Earth Observation Footprint", entryItem
					.getEarthObservation().getFeatureOfInterest()
					.getFootprint().toString());
		}
		if (!entryItem.getEarthObservation().getProcedure()
				.getEarthObservationEquipment().getPlatform().isEmpty()) {
			setMetaDataViews("Earth Observation Equipment ", entryItem
					.getEarthObservation().getProcedure()
					.getEarthObservationEquipment().toString());
		}
		if (entryItem.getEarthObservation().getResult()
				.getEarthObservationResult() != null) {
			setMetaDataViews("Earth Observation Result ", entryItem
					.getEarthObservation().getResult()
					.getEarthObservationResult().toString());
		}
		if (entryItem.getEarthObservation().getPhenomenonTime() != null) {
			setMetaDataViews("Earth Observation Phenomenon Time ", entryItem
					.getEarthObservation().getPhenomenonTime().getTimePeriod()
					.toString());
		}
		if (!entryItem.getEarthObservation().getResultTime().getTimeInstant()
				.getTimePosition().get__text().isEmpty()) {
			setMetaDataViews("Earth Observation Result Time ", entryItem
					.getEarthObservation().getResultTime().getTimeInstant()
					.getTimePosition().get__text());
		}
		return rootView;
	}

	private void setMetaDataViews(String headerText, String values) {

        TextView tvMetaHeader = new TextView(getActivity());
		tvMetaHeader.setLayoutParams(new TableLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
		tvMetaHeader.setPadding(16, 2, 16, 16);
		tvMetaHeader.setTextSize(18);
		tvMetaHeader.setTypeface(Typeface.DEFAULT_BOLD);
		tvMetaHeader.setText(headerText);
		linearLayout.addView(tvMetaHeader);

		String newValues = values.replace(SystemUtils.LINE_SEPARATOR + " "
				+ SystemUtils.LINE_SEPARATOR + " ", "");
        TextView tvMetaContent = new TextView(getActivity());
		tvMetaContent.setLayoutParams(new TableLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
		tvMetaContent.setPadding(16, 2, 16, 16);
		tvMetaContent.setTextSize(16);
		tvMetaContent.setTextColor(0xffA9A9A9);
		tvMetaContent.setText(newValues);
		linearLayout.addView(tvMetaContent);
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
