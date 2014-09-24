package pl.wasat.smarthma.ui.frags.search;

import pl.wasat.smarthma.model.FedeoRequest;
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
 * {@link SearchResultProductsDetailsFragment.OnSearchResultCollectionDetailsFragmentListener}
 * interface to handle interaction events. Use the
 * {@link SearchResultProductsDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 
 */
public class SearchResultProductsDetailsFragment extends BaseViewAndBasicSettingsDetailFragment {

	private OnSearchResultCollectionDetailsFragmentListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param collectionName
	 *            Parameter 1.
	 * @param collectionDates
	 *            Parameter 2.
	 * @return A new instance of fragment SearchResultProductsDetailsFragment.
	 */
	public static SearchResultProductsDetailsFragment newInstance(
			Entry collectionEntry) {
		SearchResultProductsDetailsFragment fragment = new SearchResultProductsDetailsFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_COLLECTION_ENTRY, collectionEntry);
		fragment.setArguments(args);
		return fragment;
	}

	public SearchResultProductsDetailsFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		btnShowProducts.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mListener != null) {
                    FedeoRequest request = new FedeoRequest();
                    request.buildFromShared(getActivity());
                    //request.setDefaultParams();
                    //request.setParentIdentifier(displayedEntry.getIdentifier());
                    //request.setBbox(geoBounds);
                    mListener.onSearchResultCollectionDetailsFragmentShowProducts(request);
                }
            }
        });

        for (Link entityLink : displayedEntry.getLinks()) {
            if (entityLink.get_rel().equalsIgnoreCase("search")) {
                btnShowProducts.setEnabled(true);
            }

        }
		
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnSearchResultCollectionDetailsFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnSearchResultCollectionDetailsFragmentListener");
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
		public void onSearchResultCollectionDetailsFragmentShowProducts(FedeoRequest request);
	}

}
