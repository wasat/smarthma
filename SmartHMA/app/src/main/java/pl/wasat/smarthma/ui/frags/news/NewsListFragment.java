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

package pl.wasat.smarthma.ui.frags.news;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.services.NewsRssServiceNoAsync;
import pl.wasat.smarthma.ui.frags.base.BaseSpiceListFragment;
import pl.wasat.smarthma.utils.request.FedeoSearchRequest;


/**
 * The type News list fragment.
 */
public class NewsListFragment extends BaseSpiceListFragment {

    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    private static OnNewsListFragListener mListener;
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * Instantiates a new News list fragment.
     */
    public NewsListFragment() {
        setHasOptionsMenu(true);    //this enables us to set actionbar from fragment
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnNewsListFragListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnNewsListFragListener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
        getListView().setDivider(null);
        mListener.onItemSelected(String.valueOf(0));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshListAsync();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.news_refreshmenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actionbar_refresh) {
            refreshListAsync();
            return true;
        }
        return false;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        mListener.onItemSelected(String.valueOf(position));
    }

    @Override
    public void onRequestSuccess(Feed feed) {
        super.onRequestSuccess(feed);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    /**
     * Sets activate on item click.
     *
     * @param activateOnItemClick the activate on item click
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void refreshListAsync() {
        NewsRssServiceNoAsync rssService = new NewsRssServiceNoAsync(this);
        rssService.exec();
    }

    private void loadDataSeriesFeedResponse(FedeoRequestParams browseRequest) {
        if (browseRequest != null) {
            getActivity().setProgressBarIndeterminateVisibility(true);
            getSpiceManager().execute(new FedeoSearchRequest(getActivity(), browseRequest, 1), this);
        }
    }

    /**
     * The interface On news list frag listener.
     */
    public interface OnNewsListFragListener {
        /**
         * On item selected.
         *
         * @param id the id
         */
        void onItemSelected(String id);
    }
}
