/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wasat.smarthma.ui.frags.search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

    /**
     * Instantiates a new Search list fragment offline.
     */
    public SearchListFragmentOffline() {
        setHasOptionsMenu(true);
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param stopNewSearch the stop new search
     * @return A new instance of fragment SearchListFragment.
     */
    public static SearchListFragmentOffline newInstance(Boolean stopNewSearch) {
        SearchListFragmentOffline fragment = new SearchListFragmentOffline();
        Bundle args = new Bundle();
        args.putBoolean(Const.KEY_INTENT_RETURN_STOP_SEARCH, stopNewSearch);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;

        try {
            mListener = (OnSearchListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnSearchListFragmentListener.class.getSimpleName());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FavouritesDbAdapter dba = new FavouritesDbAdapter(getActivity());
        dba.openToRead();
        ArrayList<EntryISO> entries = dba.getISOEntries();
        dba.close();
        if (entries == null) {
            entries = new ArrayList<>();
        }
        if (entries.size() <= 0) {
            EntryISO testEntry = new EntryISO();
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void updateSearchListViewContent(List<EntryISO> searchFeedList) {
        if (searchFeedList.isEmpty()) {
            showFailureFragment();
        } else {
            attachListAdapter(searchFeedList);
        }
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
    public void onListItemClick(ListView listView, View view, int position,
                                long id) {
        super.onListItemClick(listView, view, position, id);
        mListener.onSearchListFragmentItemSelected(String.valueOf(position));
    }

    /**
     * Clear entries.
     */
    public void clearEntries() {
        this.entries.clear();
    }
}
