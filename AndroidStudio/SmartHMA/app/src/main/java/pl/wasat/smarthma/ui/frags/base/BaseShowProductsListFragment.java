package pl.wasat.smarthma.ui.frags.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.EntryImagesListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.database.FavouritesDbAdapter;
import pl.wasat.smarthma.helper.DataSorter;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.om.Footprint;
import pl.wasat.smarthma.utils.rss.FedeoSearchRequest;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link BaseShowProductsListFragment.OnBaseShowProductsListFragmentListener}
 * interface to handle interaction events. Use the
 * {@link BaseShowProductsListFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class BaseShowProductsListFragment extends BaseSpiceFragment {
    private static final String KEY_PARAM_FEDEO_REQUEST = "pl.wasat.smarthma.KEY_PARAM_FEDEO_REQUEST";

    private FedeoRequestParams fedeoRequestParams;

    private OnBaseShowProductsListFragmentListener mListener;

    protected ListView entryImagesListView;
    protected View loadingView;

    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    private int mActivatedPosition = ListView.INVALID_POSITION;
    protected EntryImagesListAdapter entryImagesListAdapter;
    protected List<Entry> entryList;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param fedeoRequestParams Parameter 1.
     * @return A new instance of fragment SearchProductsFeedsFragment.
     */
    public static BaseShowProductsListFragment newInstance(
            FedeoRequestParams fedeoRequestParams) {
        BaseShowProductsListFragment fragment = new BaseShowProductsListFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_PARAM_FEDEO_REQUEST, fedeoRequestParams);
        fragment.setArguments(args);
        return fragment;
    }

    public BaseShowProductsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fedeoRequestParams = (FedeoRequestParams) getArguments().getSerializable(
                    KEY_PARAM_FEDEO_REQUEST);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
     * android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_collections_group_list,
                container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState
                    .getInt(STATE_ACTIVATED_POSITION));
        }

        entryImagesListView = (ListView) view.findViewById(
                R.id.listview_collections_group);
        loadingView = view.findViewById(R.id.loading_layout);

        if (fedeoRequestParams != null) {
            loadSearchProductsFeedResponse(fedeoRequestParams);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnBaseShowProductsListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnBaseShowProductsListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    public void setActivateOnItemClick(boolean activateOnItemClick) {
        entryImagesListView
                .setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
                        : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            entryImagesListView.setItemChecked(mActivatedPosition, false);
        } else {
            entryImagesListView.setItemChecked(position, true);
        }
        mActivatedPosition = position;
    }

    private void updateShowProductsListViewContent(final List<Entry> entryList) {
        this.entryList = entryList;
        View view = getView();
        if (view != null) {

            if (entryList.isEmpty()) {
                view.setVisibility(View.GONE);
                loadFailureFrag();

            } else {
                for (Entry a : entryList) {
                    EoDbAdapter dba = new EoDbAdapter(getActivity());
                    dba.openToRead();
                    Entry fetchedSearch = dba.getBlogListing(a.getGuid());
                    dba.close();
                    if (fetchedSearch == null) {
                        dba = new EoDbAdapter(getActivity());
                        dba.openToWrite();
                        dba.insertBlogListing(a.getGuid());
                        dba.close();
                    } else {
                        a.setDbId(fetchedSearch.getDbId());
                        a.setOffline(fetchedSearch.isOffline());
                        a.setRead(fetchedSearch.isRead());
                    }
                }

                entryImagesListAdapter = new EntryImagesListAdapter(getActivity()
                        .getBaseContext(), getBitmapSpiceManager(), entryList);
                entryImagesListView.setAdapter(entryImagesListAdapter);

                loadingView.setVisibility(View.GONE);
                entryImagesListAdapter.notifyDataSetChanged();
                entryImagesListView.setVisibility(View.VISIBLE);

                // Click event for single list row
                entryImagesListView
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent,
                                                    View view, int position, long id) {
                                loadProductItemDetails(entryList.get(position));
                            }
                        });
            }
        }
    }

    protected void loadFailureFrag() {
    }

    protected void loadProductItemDetails(Entry entry) {
    }

    /**
     * @param searchProductFeeds searched Feed
     */
    protected void loadSearchResultProductsIntroDetailsFrag(
            Feed searchProductFeeds) {
    }

    /**
     *
     */
    private void loadSearchProductsFeedResponse(FedeoRequestParams fedeoRequestParams) {
        if (fedeoRequestParams != null) {
            getActivity().setProgressBarIndeterminateVisibility(true);

            getSpiceManager().execute(new FedeoSearchRequest(getActivity(), fedeoRequestParams, 2),
                    new FeedRequestListener());
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
    public interface OnBaseShowProductsListFragmentListener {

        void onBaseShowProductsListFragmentFootprintSend();
    }


    private void loadRequestSuccess(Feed searchProductFeeds) {
        getActivity().setProgressBarIndeterminateVisibility(false);
        if (searchProductFeeds == null) {
            searchProductFeeds = new Feed();
        }
        List<Entry> entries = searchProductFeeds.getEntries();
        DataSorter sorter = new DataSorter();
        sorter.sort(entries);

        updateShowProductsListViewContent(searchProductFeeds.getEntries());
        loadSearchResultProductsIntroDetailsFrag(searchProductFeeds);
/*        ArrayList<Footprint> footPrints = getFootprints(searchProductFeeds
                .getEntriesEO());*/
        mListener.onBaseShowProductsListFragmentFootprintSend();
    }

    /**
     * @param searchProductFeeds - Product Feed
     * @return footPrintsArr
     */
    private ArrayList<Footprint> getFootprints(List<Entry> searchProductFeeds) {
        ArrayList<Footprint> footPrintsArr = new ArrayList<>();
        for (Entry searchProductFeed : searchProductFeeds) {
            if (searchProductFeed.getEarthObservation() != null) {
                footPrintsArr.add(searchProductFeed.getEarthObservation()
                        .getFeatureOfInterest().getFootprint());
            }
        }
        return footPrintsArr;
    }

    private final class FeedRequestListener implements RequestListener<Feed> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            parseRequestFailure(spiceException);
        }

        @Override
        public void onRequestSuccess(Feed feed) {
            if (feed == null) {
                parseRequestFailure(null);
                return;
            }
            loadRequestSuccess(feed);
        }


    }

    public EntryImagesListAdapter getEntryImagesListAdapter() {
        return entryImagesListAdapter;
    }

    public void refreshList() {
        if (entryImagesListAdapter != null) {
            entryImagesListAdapter.notifyDataSetChanged();
        } else {
            Log.d("ZX", "Warning: BaseShowProductsListFragment: refreshList(): entryImagesListAdapter is null.");
        }
    }

    protected void clearEntries()
    {
        this.entryList.clear();
    }

    public void clearList()
    {
        FavouritesDbAdapter dba = new FavouritesDbAdapter(getActivity());
        dba.openToWrite();
        dba.clearProducts();
        dba.close();
        clearEntries();
        refreshList();
    }

    public List<Entry> getEntryList() {
        return entryList;
    }
}
