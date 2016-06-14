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

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.SearchListAdapter;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.ui.frags.base.BaseSpiceListFragment;
import pl.wasat.smarthma.ui.frags.common.FailureFragment;

/**
 * The type Search list fragment base.
 */
public abstract class SearchListFragmentBase extends BaseSpiceListFragment {
    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    /**
     * The Search request.
     */
    FedeoRequestParams searchRequest;
    /**
     * The Entries.
     */
    List<EntryISO> entries;
    /**
     * The Feed summary search collection fragment.
     */
    FeedSummarySearchCollectionFragment feedSummarySearchCollectionFragment;
    /**
     * The M listener.
     */
    OnSearchListFragmentListener mListener;
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * Instantiates a new Search list fragment base.
     */
    public SearchListFragmentBase() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState
                    .getInt(STATE_ACTIVATED_POSITION));
        }
        getListView().setDivider(null);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }
        mActivatedPosition = position;
    }

    @Override
    public void onRequestSuccess(Feed searchFeeds) {
    }

    /**
     * Gets entries.
     *
     * @return the entries
     */
    public List<EntryISO> getEntries() {
        return entries;
    }


    /**
     * Show failure fragment.
     */
    void showFailureFragment() {
        String searchFail = getActivity().getString(
                R.string.nothing_to_display_please_search_again_);

        FailureFragment failureFragment = FailureFragment
                .newInstance(searchFail);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        failureFragment).commit();
    }

    /**
     * Attach list adapter.
     *
     * @param searchFeedList the search feed list
     */
    void attachListAdapter(final List<EntryISO> searchFeedList) {
        SearchListAdapter adapter = new SearchListAdapter(getActivity(),
                searchFeedList);
        adapter.setListener(new OnSlideElementListener() {
            @Override
            public void Catch(boolean swipeRight, int position) {
                if (swipeRight) {
                    Toast.makeText(getContext(),
                            getContext().getString(R.string.swipe_right)
                                    + position, Toast.LENGTH_SHORT).show();
                    mListener.onSearchListFragmentRightSwipeItem(searchFeedList.get(position));
                } else {
                    Toast.makeText(getContext(),
                            getContext().getString(R.string.swipe_left)
                                    + position, Toast.LENGTH_SHORT).show();
                    mListener.onSearchListFragmentLeftSwipeItem(searchFeedList.get(position));
                }
            }
        });
        this.setListAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * Show data series intro.
     *
     * @param searchResultFeed - Dataseries Feed
     */
    void showDataSeriesIntro(Feed searchResultFeed) {
        feedSummarySearchCollectionFragment = FeedSummarySearchCollectionFragment
                .newInstance(searchResultFeed);
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
    public interface OnSearchListFragmentListener {
        /**
         * On search list fragment item selected.
         *
         * @param id the id
         */
        void onSearchListFragmentItemSelected(String id);

        /**
         * On search list fragment right swipe item.
         *
         * @param selectedEntry the selected entry
         */
        void onSearchListFragmentRightSwipeItem(EntryISO selectedEntry);

        /**
         * On search list fragment left swipe item.
         *
         * @param selectedEntry the selected entry
         */
        void onSearchListFragmentLeftSwipeItem(EntryISO selectedEntry);
    }
}
