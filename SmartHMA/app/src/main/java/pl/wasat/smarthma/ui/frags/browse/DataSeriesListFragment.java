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

package pl.wasat.smarthma.ui.frags.browse;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.adapter.DataSeriesListAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.helper.DataSorter;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.ui.frags.base.BaseSpiceListFragment;
import pl.wasat.smarthma.utils.request.FedeoSearchRequest;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link DataSeriesListFragment.OnDataSeriesListFragmentListener} interface to
 * handle interaction events. Use the {@link DataSeriesListFragment#newInstance}
 * factory method to create an instance of this fragment.
 */
public class DataSeriesListFragment extends BaseSpiceListFragment {

    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    private static final String KEY_PARAM_BROWSE_FEDEO_REQUEST = "pl.wasat.smarthma.KEY_PARAM_BROWSE_FEDEO_REQUEST";
    private FedeoRequestParams browseRequest;
    private int mActivatedPosition = ListView.INVALID_POSITION;

    private OnDataSeriesListFragmentListener mListener;

    /**
     * Instantiates a new Data series list fragment.
     */
    public DataSeriesListFragment() {
        setHasOptionsMenu(true);
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param fedeoRequestParams Parameter 1.
     * @param stopNewSearch      the stop new search
     * @return A new instance of fragment DataSeriesListFragment.
     */
    public static DataSeriesListFragment newInstance(FedeoRequestParams fedeoRequestParams, Boolean stopNewSearch) {
        DataSeriesListFragment fragment = new DataSeriesListFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_PARAM_BROWSE_FEDEO_REQUEST, fedeoRequestParams);
        args.putBoolean(Const.KEY_INTENT_RETURN_STOP_SEARCH, stopNewSearch);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnDataSeriesListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnDataSeriesListFragmentListener.class.getSimpleName());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            browseRequest = (FedeoRequestParams) getArguments().getSerializable(KEY_PARAM_BROWSE_FEDEO_REQUEST);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_refresh, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_sort_by_title_asc:
                SmartHMApplication.sortingType = Const.SORT_BY_TITLE_ASCENDING;
                refreshList();
                break;
            case R.id.action_sort_by_title_desc:
                SmartHMApplication.sortingType = Const.SORT_BY_TITLE_DESCENDING;
                refreshList();
                break;
            case R.id.action_sort_by_date_asc:
                SmartHMApplication.sortingType = Const.SORT_BY_DATE_ASCENDING;
                refreshList();
                break;
            case R.id.action_sort_by_date_desc:
                SmartHMApplication.sortingType = Const.SORT_BY_DATE_DESCENDING;
                refreshList();
                break;
            case R.id.actionbar_refresh:
                loadDataSeriesFeedResponse(browseRequest);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void refreshList() {
        loadDataSeriesFeedResponse(browseRequest);
        Toast.makeText(getActivity(), getActivity().getString(R.string.refreshing_list), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        // TODO: Find solution - why fragment is called twice
        stopSearch = getArguments().getBoolean(Const.KEY_INTENT_RETURN_STOP_SEARCH);
        if (browseRequest != null && !stopSearch) {
            loadDataSeriesFeedResponse(browseRequest);
        }
    }

    /**
     *
     */
    private void loadDataSeriesFeedResponse(FedeoRequestParams browseRequest) {
        if (browseRequest != null) {
            getActivity().setProgressBarIndeterminateVisibility(true);
            getSpiceManager().execute(new FedeoSearchRequest(getActivity(), browseRequest, 1), this);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.octo.android.robospice.request.listener.RequestListener#onRequestSuccess
     * (java.lang.Object)
     */
    @Override
    public void onRequestSuccess(Feed dataSeriesFeeds) {
        getActivity().setProgressBarIndeterminateVisibility(false);

        List<EntryISO> entries = dataSeriesFeeds.getEntriesISO();
        DataSorter sorter = new DataSorter();
        sorter.sort(entries);

        updateEOListViewContent(dataSeriesFeeds.getEntriesISO());
        loadIntroFeedInfo(dataSeriesFeeds);
    }

    private void updateEOListViewContent(List<EntryISO> dataSeriesFeedList) {
        View view = getView();
        if (view != null) {
            if (dataSeriesFeedList.isEmpty()) {
                view.setVisibility(View.GONE);
            } else {
                DataSeriesListAdapter adapter = new DataSeriesListAdapter(
                        getActivity(), dataSeriesFeedList);
                adapter.setListener(new OnSlideElementListener() {
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
                this.setListAdapter(adapter);
                adapter.notifyDataSetChanged();
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * @param dataSeriesFeeds - DataSeries Feed
     */
    private void loadIntroFeedInfo(Feed dataSeriesFeeds) {
        FeedSummaryBrowseCollectionsFragment feedSummaryBrowseCollectionsFragment = FeedSummaryBrowseCollectionsFragment
                .newInstance(dataSeriesFeeds);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        feedSummaryBrowseCollectionsFragment)
                .addToBackStack(FeedSummaryBrowseCollectionsFragment.class.getSimpleName())
                .commit();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState
                    .getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position,
                                long id) {
        super.onListItemClick(listView, view, position, id);
        mListener.onDataSeriesFragmentItemSelected(String.valueOf(position));
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
        getListView().setChoiceMode(
                activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
                        : ListView.CHOICE_MODE_NONE);
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
    public interface OnDataSeriesListFragmentListener {
        /**
         * On data series fragment item selected.
         *
         * @param id the id
         */
        void onDataSeriesFragmentItemSelected(String id);
    }
}
