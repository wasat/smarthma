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

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.SearchHistory;
import pl.wasat.smarthma.database.SearchParams;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.helper.DataSorter;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.utils.request.FedeoSearchRequest;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link SearchListFragment.OnSearchListFragmentListener} interface to handle
 * interaction events. Use the {@link SearchListFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class SearchListFragment extends SearchListFragmentBase {
    private static final String KEY_PARAM_SEARCH_FEDEO_REQUEST = "pl.wasat.smarthma.KEY_PARAM_SEARCH_FEDEO_REQUEST";

    /**
     * Instantiates a new Search list fragment.
     */
    public SearchListFragment() {
        setHasOptionsMenu(true);
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param fedeoRequestParams Parameter 2.
     * @param stopNewSearch      the stop new search
     * @return A new instance of fragment SearchListFragment.
     */
    public static SearchListFragment newInstance(FedeoRequestParams fedeoRequestParams, Boolean stopNewSearch) {
        SearchListFragment fragment = new SearchListFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_PARAM_SEARCH_FEDEO_REQUEST, fedeoRequestParams);
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
        if (getArguments() != null) {
            searchRequest = (FedeoRequestParams) getArguments().getSerializable(
                    KEY_PARAM_SEARCH_FEDEO_REQUEST);

            stopSearch = getArguments().getBoolean(Const.KEY_INTENT_RETURN_STOP_SEARCH);
            if (searchRequest != null && !stopSearch) {
                loadSearchFeedResponse(searchRequest);
            }

            SearchHistory searchHistory = new SearchHistory(getActivity());
            searchHistory.addSearchParameters(new SearchParams(searchRequest));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void loadSearchFeedResponse(FedeoRequestParams fedeoRequestParams) {
        if (fedeoRequestParams != null) {
            getActivity().setProgressBarIndeterminateVisibility(true);
            FedeoSearchRequest fedeoSearchRequest = new FedeoSearchRequest(getActivity(), fedeoRequestParams, 1);
            //fedeoSearchRequest.setRetryPolicy(new CustomRetryPolicy());

            //String lastRequestCacheKey = fedeoSearchRequest.createCacheKey();
            //getSpiceManager().execute(fedeoSearchRequest, lastRequestCacheKey, DurationInMillis.ALWAYS_EXPIRED, this);
            getSpiceManager().execute(fedeoSearchRequest, this);
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        mListener.onSearchListFragmentItemSelected(String.valueOf(position));
    }

    private void updateSearchListViewContent(List<EntryISO> searchFeedList) {
        View view = getView();
        if (view != null) {
            if (searchFeedList.isEmpty()) {
                view.setVisibility(View.GONE);
                showFailureFragment();
            } else {
                attachListAdapter(searchFeedList);
                view.setVisibility(View.VISIBLE);
            }
        }
    }


    /**
     * @param searchResultFeed - Dataseries Feed
     */
    void showDataSeriesIntro(Feed searchResultFeed) {
        super.showDataSeriesIntro(searchResultFeed);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        feedSummarySearchCollectionFragment, FeedSummarySearchCollectionFragment.class.getSimpleName())
                .addToBackStack(FeedSummarySearchCollectionFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onRequestSuccess(Feed searchFeeds) {
        entries = searchFeeds.getEntriesISO();
        DataSorter sorter = new DataSorter();
        sorter.sort(entries);

        getActivity().setProgressBarIndeterminateVisibility(false);
        updateSearchListViewContent(searchFeeds.getEntriesISO());

        showDataSeriesIntro(searchFeeds);
    }


}
