package pl.wasat.smarthma.ui.frags.base;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.feed.Entry;
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
 * {@link BaseProductDetailsFragment.OnProductDetailSearchFragmentListener}
 * interface to handle interaction events. Use the
 * {@link BaseProductDetailsFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class BaseProductDetailsFragment extends Fragment {
	private static final String KEY_PRODUCT_ENTRY = "pl.wasat.smarthma.KEY_PRODUCT_ENTRY";

	protected Entry displayedEntry;

    private OnProductDetailSearchFragmentListener mListener;

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
                    loadMetadataFrag();
                }
            });

            Button showMapButton = (Button) rootView
                    .findViewById(R.id.product_frag_detail_button_show_map);
			showMapButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadMapFragment();

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
					+ " must implement OnProductDetailSearchFragmentListener");
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

	/**
	 * 
	 */
	public void loadMetadataFrag() {
	}
	
	public void showQuickLook() {
	}	
	
	public void loadMapFragment() {
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
