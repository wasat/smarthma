package pl.wasat.smarthma.ui.frags.search;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.FavouritesDbAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.helper.DataSorter;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.iso.Date;
import pl.wasat.smarthma.model.iso.EntryISO;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link SearchListFragmentOffline.OnSearchListFragmentListener} interface to handle
 * interaction events. Use the {@link SearchListFragmentOffline#newInstance} factory
 * method to create an instance of this fragment.
 */
public class SearchListFragmentOffline extends SearchListFragmentBase {

    protected EntryISO testEntry;
    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment SearchListFragment.
     */
    public static SearchListFragmentOffline newInstance(Boolean stopNewSearch) {
        SearchListFragmentOffline fragment = new SearchListFragmentOffline();
        Bundle args = new Bundle();
        //args.putSerializable(KEY_PARAM_SEARCH_FEDEO_REQUEST, fedeoRequestParams);
        args.putBoolean(Const.KEY_INTENT_RETURN_STOP_SEARCH, stopNewSearch);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchListFragmentOffline() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ZX", "SearchListFragmentOffline onCreate()");

        FavouritesDbAdapter dba = new FavouritesDbAdapter(getActivity());
        dba.openToRead();
        ArrayList<EntryISO> entries = dba.getISOEntries();
        dba.close();
        if (entries == null)
        {
            entries = new ArrayList<>();
        }
        if (entries.size() <= 0)
        {
            testEntry = new EntryISO();
            testEntry.setTitle(getActivity().getString(R.string.empty_list));
            testEntry.setDate(new Date());
            testEntry.setId("");
            testEntry.setIdentifier("");
            testEntry.setUpdated("");
            testEntry.setFavourite(true);
            entries.add(0, testEntry);
        }

        DataSorter sorter = new DataSorter();
        sorter.sort(entries);

        getActivity().setProgressBarIndeterminateVisibility(false);
        updateSearchListViewContent(entries);

        this.entries = entries;
    }

    public void clearEntries()
    {
        this.entries.clear();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Feed searchFeeds = new Feed();
        searchFeeds.setEntriesISO(entries);
        showDataSeriesIntro(searchFeeds);

        mListener.onSearchListFragmentItemSelected(String.valueOf(0));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (OnSearchListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSearchListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position,
                                long id) {
        super.onListItemClick(listView, view, position, id);
        mListener.onSearchListFragmentItemSelected(String.valueOf(position));
    }

    private void updateSearchListViewContent(List<EntryISO> searchFeedList) {
        Log.d("ZX", "updateSearchListViewContent()");
        //View view = getView();
        //if (view != null) {
        if (searchFeedList.isEmpty()) {
            Log.d("ZX", "searchFeedList empty");
            //view.setVisibility(View.GONE);
            showFailureFragment();
        } else {
            Log.d("ZX", "searchFeedList not empty");
            attachListAdapter(searchFeedList);
            //view.setVisibility(View.VISIBLE);
        }
        //entries.remove(testEntry);
        //}
    }
}
