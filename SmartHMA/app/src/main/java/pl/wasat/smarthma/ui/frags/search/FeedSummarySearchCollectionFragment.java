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

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.ui.frags.base.BaseFeedSummaryFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the {@link FeedSummarySearchCollectionFragment#newInstance}
 * factory method to create an instance of this fragment.
 */
public class FeedSummarySearchCollectionFragment extends BaseFeedSummaryFragment {

    /**
     * Instantiates a new Feed summary search collection fragment.
     */
    public FeedSummarySearchCollectionFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param feedParam Parameter 1.
     * @return A new instance of fragment FeedSummaryFragment.
     */
    public static FeedSummarySearchCollectionFragment newInstance(Feed feedParam) {
        FeedSummarySearchCollectionFragment fragment = new FeedSummarySearchCollectionFragment();
        Bundle args = new Bundle();
        args.putSerializable(BaseFeedSummaryFragment.KEY_FEED_SUMMARY, feedParam);
        fragment.setArguments(args);
        return fragment;
    }

    /* (non-Javadoc)
     * @see pl.wasat.smarthma.ui.frags.base.FeedSummaryFragment#loadNavSearch(java.lang.String)
     */
    @Override
    public void loadNavSearch(String linkHref) {
        super.loadNavSearch(linkHref);

        FedeoRequestParams request = new FedeoRequestParams(null);
        request.setUrl(linkHref);

        SearchListFragment searchListFragment = SearchListFragment
                .newInstance(request, false);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container, searchListFragment)
                .commit();

    }


}
