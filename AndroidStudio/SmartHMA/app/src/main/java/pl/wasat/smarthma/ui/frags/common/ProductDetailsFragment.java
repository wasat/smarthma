package pl.wasat.smarthma.ui.frags.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
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

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.entry.SimpleMetadata;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link ProductDetailsFragment.OnProductDetailsFragmentListener} interface to
 * handle interaction events. Use the {@link ProductDetailsFragment#newInstance}
 * factory method to create an instance of this fragment.
 */
public class ProductDetailsFragment extends Fragment implements Target {
    private static final String KEY_PRODUCT_ENTRY = "pl.wasat.smarthma.KEY_PRODUCT_ENTRY";

    private Entry displayedEntry;

    private OnProductDetailsFragmentListener mListener;

    private static final CharSequence[] shareList = {"Facebook", "Other"};

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param prodEntry Parameter 1.
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
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_product_details,
                container, false);

        if (displayedEntry != null) {
            final String title = displayedEntry.getTitle();
            final String pubDate = "This data were published: "
                    + displayedEntry.getPublished() + " and updated: "
                    + displayedEntry.getUpdated();

            String content = displayedEntry.getSummary().getCdata();
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
                    showQuicklookGallery();
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
                    showExtendedMap();
                }
            });

            Button shareQLookButton = (Button) rootView
                    .findViewById(R.id.product_frag_detail_button_share);
            shareQLookButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showShareListDialog();
                    //openShareDialog();
                }
            });
        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnProductDetailsFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnProductDetailsFragmentListener");
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
        } else if (id == R.id.actionbar_share) {
            startQLookTarget();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void startQLookTarget() {
        Target quicklookTarget = this;
        Picasso.with(getActivity()).load(getQuicklookUrl())
                .into(quicklookTarget);
    }

    private void sendIntentShareUrl() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, getQuicklookUrl());
        startActivity(Intent.createChooser(shareIntent, "Share Quicklook"));
    }

    private void sendIntentShareImg(Bitmap bitmapImg) {
        String path = Images.Media.insertImage(getActivity()
                .getContentResolver(), bitmapImg, "title", null);
        Uri qlookUri = Uri.parse(path);

        final Intent shareIntent = new Intent(
                android.content.Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.putExtra(Intent.EXTRA_STREAM, qlookUri);
        shareIntent.setType("image/*");

        startActivity(Intent.createChooser(shareIntent, "Share Quicklook"));
    }

    /**
     *
     */
    private void loadMetadataFrag() {
        MetadataFragment metadataOMFragment = MetadataFragment
                .newInstance(displayedEntry);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        metadataOMFragment, "MetadataFragment")
                .addToBackStack("MetadataFragment").commit();
    }

    private void showExtendedMap() {
        //String url = getQuicklookUrl();
        //Footprint footprint = displayedEntry.getEarthObservation()
        //       .getFeatureOfInterest().getFootprint();
        //ArrayList<LatLngExt> footprint = displayedEntry.getSimpleMetadata().getFootprint();

        mListener.onProductDetailsFragmentExtendedMapShow(displayedEntry.getSimpleMetadata());
    }

    private void showQuicklookGallery() {
        String qLookUrl = getQuicklookUrl();
        mListener.onProductDetailsFragmentQuicklookShow(qLookUrl);
    }

    private String getQuicklookUrl() {
        String url = displayedEntry.getSimpleMetadata().getQuickLookUrl();
        if (url == null) url = "";

/*        List<Content> content = displayedEntry.getGroup().getContent();
        for (Content cont : content) {
            if (cont.getCategory().get_text().equalsIgnoreCase("QUICKLOOK")) {
                url = cont.get_url();
                return url;
            }
        }

        List<Browse> browseList = displayedEntry.getEarthObservation()
                .getResult().getEarthObservationResult().getBrowseList();

        for (Browse browse : browseList) {
            if (browse.getBrowseInformation().getType().get_text()
                    .equalsIgnoreCase("QUICKLOOK")) {
                url = browse.getBrowseInformation().getFileName()
                        .getServiceReference().get_xlink_href();
            }
        }*/
        return url;
    }

    private void openShareDialog() {
        String qLookUrl = getQuicklookUrl();
        mListener.onProductDetailsFragmentShareDialogShow(qLookUrl);
    }

    private void showShareListDialog() {
        ShareListDialogFragment listDialFrag = new ShareListDialogFragment();
        listDialFrag.show(getActivity().getSupportFragmentManager(),
                "ShareListDialogFragment");
    }

    public static class ShareListDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.share_options).setItems(
                    shareList, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //setCatalogue(which);
                            ((ProductDetailsFragment) getActivity().getSupportFragmentManager()
                                    .findFragmentByTag("ProductDetailsFragment")).chooseShareType(which);
                        }
                    });
            return builder.create();
        }


    }

    private void chooseShareType(int which) {
        switch (which) {
            case 0:
                openShareDialog();
                break;
            case 1:
                startQLookTarget();
                break;
            default:
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnProductDetailsFragmentListener {

        void onProductDetailsFragmentExtendedMapShow(SimpleMetadata simpleMetadata);

        void onProductDetailsFragmentQuicklookShow(String url);

        void onProductDetailsFragmentShareDialogShow(String url);

    }

    @Override
    public void onBitmapFailed(Drawable arg0) {
        sendIntentShareUrl();
    }

    @Override
    public void onBitmapLoaded(Bitmap image, LoadedFrom arg1) {

        Bitmap scaled = Bitmap.createScaledBitmap(image, 480, 480, true);
        sendIntentShareImg(scaled);

    }

    @Override
    public void onPrepareLoad(Drawable arg0) {
    }

}
