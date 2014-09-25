/*package pl.wasat.smarthma.ui.frags.search;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.ui.frags.base.BaseProductDetailsFragment;
import pl.wasat.smarthma.ui.frags.common.MapSearchFragment;
import pl.wasat.smarthma.ui.frags.common.MetadataFragment;
import android.os.Bundle;

*//**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link ProductSearchDetailsFragment.OnProductDetailSearchFragmentListener}
 * interface to handle interaction events. Use the
 * {@link SearchProductDetailsFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 *//*
public class SearchProductDetailsFragment extends BaseProductDetailsFragment {
	private static final String KEY_PRODUCT_ENTRY = "pl.wasat.smarthma.KEY_PRODUCT_ENTRY";

	*//**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment ProductDetailSearchFragment.
	 *//*
	public static SearchProductDetailsFragment newInstance(Entry prodEntry) {
		SearchProductDetailsFragment fragment = new SearchProductDetailsFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_PRODUCT_ENTRY, prodEntry);
		fragment.setArguments(args);
		return fragment;
	}

	public SearchProductDetailsFragment() {
		// Required empty public constructor
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wasat.smarthma.ui.frags.base.BaseProductDetailsFragment#loadMetadataFrag
	 * ()
	 
	@Override
	public void loadMetadataFrag() {
		MetadataFragment mapSearchFragment = MetadataFragment
				.newInstance(displayedEntry);
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.activity_base_details_container, mapSearchFragment,
						"MetadataFragment").addToBackStack("MetadataFragment")
				.commit();
		super.loadMetadataFrag();
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wasat.smarthma.ui.frags.base.BaseProductDetailsFragment#showQuickLook
	 * ()
	 
	@Override
	public void showQuickLook() {
		super.showQuickLook();
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wasat.smarthma.ui.frags.base.BaseProductDetailsFragment#loadMapFragment
	 * ()
	 
	@Override
	public void loadMapFragment() {
		MapSearchFragment mapSearchFragment = MapSearchFragment.newInstance();
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_details_container,
						mapSearchFragment).addToBackStack("MapSearchFragment")
				.commit();
		super.loadMapFragment();
	}

}
*/