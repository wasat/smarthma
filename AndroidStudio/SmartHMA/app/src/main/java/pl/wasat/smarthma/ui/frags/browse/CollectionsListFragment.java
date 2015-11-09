package pl.wasat.smarthma.ui.frags.browse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.HashMap;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.adapter.CollectionsListAdapter;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.Collection;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.ui.activities.CollectionsBrowserActivity;
import pl.wasat.smarthma.ui.frags.base.BaseSpiceFragment;
import pl.wasat.smarthma.ui.frags.common.CollectionDetailsFragment;
import pl.wasat.smarthma.utils.rss.FedeoSearchRequest;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link CollectionsListFragment.OnCollectionsListFragmentListener} interface
 * to handle interaction events. Use the
 * {@link CollectionsListFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class CollectionsListFragment extends BaseSpiceFragment {

    private static final String KEY_COLLECTIONS_GROUP_LIST_POSITION = "pl.wasat.smarthma.KEY_COLLECTIONS_GROUP_LIST_POSITION";
    private static final String KEY_COLLECTIONS_GROUP_NAME = "pl.wasat.smarthma.KEY_COLLECTIONS_GROUP_NAME";
    public static final String KEY_COLLECTIONS_NAME = "pl.wasat.smarthma.KEY_COLLECTIONS_NAME";

    private int parentListPos;
    private String selectGroupName;
    private String collName;
    private OnCollectionsListFragmentListener mListener;
    private ListView list;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param listPosParam Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static CollectionsListFragment newInstance(int listPosParam) {
        CollectionsListFragment fragment = new CollectionsListFragment();
        String groupName = SmartHMApplication.GlobalEODataList
                .getCollectionsGroupList().get(listPosParam).getGroupName();
        Bundle args = new Bundle();
        args.putInt(KEY_COLLECTIONS_GROUP_LIST_POSITION, listPosParam);
        args.putString(KEY_COLLECTIONS_GROUP_NAME, groupName);
        fragment.setArguments(args);
        return fragment;
    }

    public CollectionsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parentListPos = getArguments().getInt(
                    KEY_COLLECTIONS_GROUP_LIST_POSITION);
            selectGroupName = getArguments().getString(
                    KEY_COLLECTIONS_GROUP_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collections_list, container,
                false);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
     * android.os.Bundle)
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList();
    }

    private void initList() {
        list = (ListView) getView()
                .findViewById(R.id.collections_list);

        if (!SmartHMApplication.GlobalEODataList.getCollectionsGroupList()
                .isEmpty()) {
            final ArrayList<Collection> collections = SmartHMApplication.GlobalEODataList
                    .getCollectionsGroupList().get(parentListPos)
                    .getCollections();
            CollectionsListAdapter adapter = new CollectionsListAdapter(
                    getActivity(), collections, selectGroupName);
            list.setAdapter(adapter);
            adapter.setListener(new OnSlideElementListener() {
                @Override
                public void Catch(boolean swipeRight, int position) {
                    if (swipeRight)
                        Toast.makeText(getActivity(), "share CollectionsListAdapter" + position, Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "delete CollectionsListAdapter" + position, Toast.LENGTH_SHORT).show();
                }
            });

            list.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    collName = collections.get(position).getName();
                    loadCollectionDetailFragment(collName);
                }
            });
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnCollectionsListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCollectionsListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void loadDataSeriesFeedsActivity(String collName) {
        Intent dsFeedsIntent = new Intent(getActivity(),
                CollectionsBrowserActivity.class);
        dsFeedsIntent.putExtra(KEY_COLLECTIONS_NAME, collName);
        startActivity(dsFeedsIntent);
    }


    private void loadCollectionDetailFragment(String collName) {
        FedeoRequestParams fedeoRequestParams = new FedeoRequestParams();
        //fedeoRequestParams.buildFromShared(getActivity());

        HashMap<String, String> paramExtra = new HashMap<>();
        paramExtra.put("uid", "urn:ogc:def:" + collName);
        fedeoRequestParams.setParamsExtra(paramExtra);
        getActivity().setProgressBarIndeterminateVisibility(true);
        getSpiceManager().execute(new FedeoSearchRequest(getActivity(), fedeoRequestParams, 1),
                new FeedRequestListener());


    }

    private void loadEntryToDetailsFrag(Feed searchedCollectionFeed) {
        EntryISO entryISO;
        if (searchedCollectionFeed == null) {
            searchedCollectionFeed = new Feed();
        }
        if (!searchedCollectionFeed.getTotalResults().getText().isEmpty()) {
            entryISO = searchedCollectionFeed.getEntriesISO().get(0);
            CollectionDetailsFragment collectionDetailsFragment = CollectionDetailsFragment
                    .newInstance(entryISO);
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_base_details_container,
                            collectionDetailsFragment, "CollectionDetailsFragment")
                    .addToBackStack("CollectionDetailsFragment").commit();
        } else {
            CollectionEmptyDetailsFragment collectionEmptyDetailsFragment = CollectionEmptyDetailsFragment
                    .newInstance(collName);
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_base_details_container,
                            collectionEmptyDetailsFragment, "CollectionEmptyDetailsFragment")
                    .addToBackStack("CollectionEmptyDetailsFragment").commit();
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
    public interface OnCollectionsListFragmentListener {
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
            loadEntryToDetailsFrag(feed);
        }
    }

    public ListView getList() {
        return list;
    }
}
