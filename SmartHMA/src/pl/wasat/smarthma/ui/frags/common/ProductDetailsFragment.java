package pl.wasat.smarthma.ui.frags.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.eo.Browse;
import pl.wasat.smarthma.model.feed.Entry;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link ProductDetailsFragment.OnProductDetailsFragmentListener} interface to
 * handle interaction events. Use the {@link ProductDetailsFragment#newInstance}
 * factory method to create an instance of this fragment.
 * 
 */
public class ProductDetailsFragment extends Fragment {
	private static final String KEY_PRODUCT_ENTRY = "pl.wasat.smarthma.KEY_PRODUCT_ENTRY";

	protected Entry displayedEntry;

	private OnProductDetailsFragmentListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @return A new instance of fragment ProductDetailsFragment.
	 */
	public static ProductDetailsFragment newInstance(Entry prodEntry) {
		ProductDetailsFragment fragment = new ProductDetailsFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_PRODUCT_ENTRY, prodEntry);
		fragment.setArguments(args);
		return fragment;
	}

	public ProductDetailsFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			displayedEntry = (Entry) getArguments().getSerializable(
					KEY_PRODUCT_ENTRY);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_product_details,
				container, false);

		if (displayedEntry != null) {
			final String title = displayedEntry.getTitle();
			final String pubDate = "This data were published: "
					+ displayedEntry.getPublished() + " and updated: "
					+ displayedEntry.getUpdated();

			String content = displayedEntry.getSummary();
			((TextView) rootView.findViewById(R.id.product_frag_detail_name))
					.setText(title);
			((TextView) rootView.findViewById(R.id.product_frag_detail_dates))
					.setText(pubDate);
			WebView detailWebView = (WebView) rootView
					.findViewById(R.id.product_frag_detail_content);
			detailWebView.loadData(content, "text/html", "UTF-8");

			Button quicklookButton = (Button) rootView
					.findViewById(R.id.product_frag_detail_button_quicklook);
			quicklookButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showQuickLook();
				}
			});

			Button metaButton = (Button) rootView
					.findViewById(R.id.product_frag_detail_button_show_meta);
			metaButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					loadMetadataFrag();
				}
			});

			Button showMapButton = (Button) rootView
					.findViewById(R.id.product_frag_detail_button_show_map);
			showMapButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					loadFootprint();
				}
			});
		}
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnProductDetailsFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnProductDetailsFragmentListener");
		}
	}
	
	

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_detail_twopane, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.actionbar_saveoffline) {
			Toast.makeText(getActivity().getApplicationContext(),
					"This metadata has been saved offline.", Toast.LENGTH_LONG)
					.show();
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * 
	 */
	public void loadMetadataFrag() {
		MetadataFragment mapSearchFragment = MetadataFragment
				.newInstance(displayedEntry);
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.activity_base_details_container, mapSearchFragment,
						"MetadataFragment").addToBackStack("MetadataFragment")
				.commit();
	}

	public void showQuickLook() {
		String url = "";
		//LatLngBounds bounds = null;

		List<Browse> browseList = displayedEntry.getEarthObservation()
				.getResult().getEarthObservationResult().getBrowseList();

		for (Iterator<Browse> iterator = browseList.iterator(); iterator
				.hasNext();) {
			Browse browse = (Browse) iterator.next();
			if (browse.getBrowseInformation().getType().get__text()
					.equalsIgnoreCase("QUICKLOOK")) {
				url = browse.getBrowseInformation().getFileName()
						.getServiceReference().get_xlink_href();
			}
		}

		//Builder boundsBuilder = new LatLngBounds.Builder();
		List<LatLng> footprintsPosList = extractLatLngFootprint();

		//for (int j = 0; j < footprintsPosList.size(); j++) {
		//	boundsBuilder.include(footprintsPosList.get(j));
		//}
		
		//bounds = boundsBuilder.build();

		mListener.onProductDetailsFragmentQuicklookShow(url, footprintsPosList);
	}

	private List<LatLng> extractLatLngFootprint() {
		List<LatLng> footprintsPosList = new ArrayList<LatLng>();
		
		footprintsPosList = displayedEntry.getEarthObservation()
				.getFeatureOfInterest().getFootprint().getMultiExtentOf()
				.getMultiSurface().getSurfaceMembers().getPolygon()
				.getExterior().getLinearRing().getPosLatLngList();
		return footprintsPosList;
	}

	public void loadFootprint() {
		List<LatLng> footprintsPosList = extractLatLngFootprint();
		mListener.onProductDetailsFragmentMapShow(footprintsPosList);
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
	public interface OnProductDetailsFragmentListener {

		public void onProductDetailsFragmentMapShow(List<LatLng> footprintPoints);

		public void onProductDetailsFragmentMetadataLoad();

		public void onProductDetailsFragmentQuicklookShow(String url,
				List<LatLng> footprintPoints);

	}

}