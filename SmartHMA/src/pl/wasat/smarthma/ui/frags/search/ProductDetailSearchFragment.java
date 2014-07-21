package pl.wasat.smarthma.ui.frags.search;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.ui.frags.MapSearchFragment;
import pl.wasat.smarthma.ui.frags.MetadataFragment;
import android.app.Activity;
import android.net.Uri;
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

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link ProductDetailSearchFragment.OnProductDetailSearchFragmentListener}
 * interface to handle interaction events. Use the
 * {@link ProductDetailSearchFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class ProductDetailSearchFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String KEY_PRODUCT_ENTRY = "pl.wasat.smarthma.KEY_PRODUCT_ENTRY";

	// TODO: Rename and change types of parameters

	private Entry displayedEntry;

    private OnProductDetailSearchFragmentListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment ProductDetailSearchFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ProductDetailSearchFragment newInstance(Entry prodEntry) {
		ProductDetailSearchFragment fragment = new ProductDetailSearchFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_PRODUCT_ENTRY, prodEntry);
		fragment.setArguments(args);
		return fragment;
	}

	public ProductDetailSearchFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			displayedEntry = (Entry) getArguments().getSerializable(
					KEY_PRODUCT_ENTRY);
		}

        EoDbAdapter db = new EoDbAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(
				R.layout.fragment_product_detail_search, container, false);

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
                    MetadataFragment mapSearchFragment = MetadataFragment
                            .newInstance(displayedEntry);
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.search_results_detail_container,
                                    mapSearchFragment)
                            .addToBackStack("MetadataFragment").commit();

                }
            });

            Button showMapButton = (Button) rootView
                    .findViewById(R.id.product_frag_detail_button_show_map);
			showMapButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapSearchFragment mapSearchFragment = MapSearchFragment
                            .newInstance();
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.search_results_detail_container,
                                    mapSearchFragment)
                            .addToBackStack("MapSearchFragment").commit();

                }
            });
		}
		return rootView;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onProductDetailSearchFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnProductDetailSearchFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
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

	private void showQuickLook() {
		// TODO Auto-generated method stub
		
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
	public interface OnProductDetailSearchFragmentListener {
		// TODO: Update argument type and name
		public void onProductDetailSearchFragmentInteraction(Uri uri);
	}

}
