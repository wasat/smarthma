package pl.wasat.smarthma.ui.frags.common;

import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.ui.frags.base.BaseViewAndBasicSettingsDetailFragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link CollectionDetailsFragment.OnCollectionDetailsFragmentListener}
 * interface to handle interaction events. Use the
 * {@link CollectionDetailsFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class CollectionDetailsFragment extends
		BaseViewAndBasicSettingsDetailFragment {

	private OnCollectionDetailsFragmentListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param collectionName
	 *            Parameter 1.
	 * @param collectionDates
	 *            Parameter 2.
	 * @return A new instance of fragment CollectionDetailsFragment.
	 */
	public static CollectionDetailsFragment newInstance(Entry collectionEntry) {
		CollectionDetailsFragment fragment = new CollectionDetailsFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_COLLECTION_ENTRY, collectionEntry);
		fragment.setArguments(args);
		return fragment;
	}

	public CollectionDetailsFragment() {
		// Required empty public constructor
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wasat.smarthma.ui.frags.base.BaseViewAndBasicSettingsDetailFragment
	 * #onCreateView(android.view.LayoutInflater, android.view.ViewGroup,
	 * android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		for (Link entityLink : displayedEntry.getLinks()) {
			if (entityLink.get_rel().equalsIgnoreCase("search")) {
				btnShowProducts.setEnabled(true);
			}
		}

		btnShowProducts.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (mListener != null) {
					String parentID = displayedEntry.getIdentifier();
					mListener.onCollectionDetailsFragmentShowProducts(parentID);
				}
			}
		});

		return rootView;

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnCollectionDetailsFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnCollectionDetailsFragmentListener");
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
	public interface OnCollectionDetailsFragmentListener {
		public void onCollectionDetailsFragmentShowProducts(String parentID);
	}

}
