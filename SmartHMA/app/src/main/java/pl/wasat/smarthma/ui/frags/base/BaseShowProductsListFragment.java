package pl.wasat.smarthma.ui.frags.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.EntryImagesListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.database.FavouritesDbAdapter;
import pl.wasat.smarthma.helper.DataSorter;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.om.Footprint;
import pl.wasat.smarthma.utils.rss.FedeoSearchRequest;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link BaseShowProductsListFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class BaseShowProductsListFragment extends BaseSpiceFragment {
    protected static final String KEY_PARAM_FEDEO_REQUEST = "pl.wasat.smarthma.KEY_PARAM_FEDEO_REQUEST";
    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    protected ListView entryImagesListView;
    protected View loadingView;
    protected EntryImagesListAdapter entryImagesListAdapter;
    protected List<Entry> entryList;
    protected FedeoRequestParams fedeoRequestParams;
    private int mActivatedPosition = ListView.INVALID_POSITION;

    public BaseShowProductsListFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param fedeoRequestParams Parameter 1.
     * @return A new instance of fragment SearchProductsFeedsFragment.
     */
    public static BaseShowProductsListFragment newInstance(
            FedeoRequestParams fedeoRequestParams, String osddUrl) {
        BaseShowProductsListFragment fragment = new BaseShowProductsListFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_PARAM_FEDEO_REQUEST, fedeoRequestParams);
        fragment.setArguments(args);
        return fragment;
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


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            entryImagesListView.setItemChecked(mActivatedPosition, false);
        } else {
            entryImagesListView.setItemChecked(position, true);
        }
        mActivatedPosition = position;
    }

    /**
     *
     */
    protected void startFedeoProductSearchRequest(FedeoRequestParams fedeoRequestParams) {
        if (fedeoRequestParams != null) {
            getActivity().setProgressBarIndeterminateVisibility(true);

            getSpiceManager().execute(new FedeoSearchRequest(getActivity(), fedeoRequestParams, 2),
                    new FeedRequestListener());
        }
    }

    public void setActivateOnItemClick(boolean activateOnItemClick) {
        entryImagesListView
                .setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
                        : ListView.CHOICE_MODE_NONE);
    }

    public EntryImagesListAdapter getEntryImagesListAdapter() {
        return entryImagesListAdapter;
    }

    public void clearList() {
        FavouritesDbAdapter dba = new FavouritesDbAdapter(getActivity());
        dba.openToWrite();
        dba.clearProducts();
        dba.close();
        clearEntries();
        refreshList();
    }

    private void clearEntries() {
        this.entryList.clear();
    }

    public void refreshList() {
        if (entryImagesListAdapter != null) {
            entryImagesListAdapter.notifyDataSetChanged();
        }
    }

    public List<Entry> getEntryList() {
        return entryList;
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
                entryImagesListAdapter.setListener(new OnSlideElementListener() {
                    @Override
                    public void Catch(boolean swipeRight, int position) {
                        if (swipeRight)
                            Toast.makeText(getContext(),
                                    getContext().getString(R.string.swipe_right)
                                            + position, Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getContext(),
                                    getContext().getString(R.string.swipe_left)
                                            + position, Toast.LENGTH_SHORT).show();
                    }
                });
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

    /**
     * @param searchProductFeeds searched Feed
     */
    protected void loadSearchResultProductsIntroDetailsFrag(
            Feed searchProductFeeds) {
    }

    protected void loadFailureFrag() {
    }

    protected void loadProductItemDetails(Entry entry) {
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
}
