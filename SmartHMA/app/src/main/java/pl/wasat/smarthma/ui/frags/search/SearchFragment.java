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
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.activities.SearchCollectionResultsActivity;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link SearchFragment.OnSearchFragmentListener} interface to handle
 * interaction events. Use the {@link SearchFragment#newInstance} factory method
 * to create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private OnSearchFragmentListener mListener;
    private View rootView;
    //private Intent searchIntent;
    private FedeoRequestParams fedeoRequestParams;

    /**
     * Instantiates a new Search fragment.
     */
    public SearchFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnSearchFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnSearchFragmentListener.class.getSimpleName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search, container,
                false);

        SearchManager searchManager = (SearchManager) getActivity()
                .getSystemService(Context.SEARCH_SERVICE);

        final SearchView searchView = (SearchView) rootView
                .findViewById(R.id.search_frag_searchview);
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));

        searchView.setSubmitButtonEnabled(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.clearFocus();

        GlobalPreferences globalPreferences = new GlobalPreferences(getActivity());
        if (globalPreferences.getIsParamsSaved()) {
            SharedPrefs sharedPrefs = new SharedPrefs(getActivity());
            searchView.setQuery(sharedPrefs.getQueryPrefs(), false);
        }

        //searchIntent = getActivity().getIntent();

        LinearLayout linearLayout1 = (LinearLayout) searchView.getChildAt(0);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getChildAt(2);
        LinearLayout linearLayout3 = (LinearLayout) linearLayout2.getChildAt(1);
        AutoCompleteTextView autoComplete = (AutoCompleteTextView) linearLayout3
                .getChildAt(0);
        autoComplete.setTextSize(20);
        autoComplete.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_black));

        Button btnBasicParams = (Button) rootView.findViewById(R.id.search_frag_button_basic);
        btnBasicParams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSearchFragmentBasicParamsChoose();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mListener.onSearchFragmentSendFedeoParams(fedeoRequestParams);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        final Button btnAdvanceParams = (Button) rootView.findViewById(R.id.search_frag_button_advance);
        btnAdvanceParams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSearchFragmentAdvanceParamsChoose();
            }
        });

        Button btnGo = (Button) rootView.findViewById(R.id.search_frag_button_start);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSearchWithButton(searchView);
            }
        });
        return rootView;
    }

    private void startSearchWithButton(SearchView searchView) {
        Intent intentBtnSearch = new Intent(getActivity(),
                SearchCollectionResultsActivity.class);
        intentBtnSearch.setAction("android.intent.action.SEARCH");
        intentBtnSearch.putExtra(SearchManager.QUERY, searchView.getQuery().toString());
        intentBtnSearch.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS_OSDD, fedeoRequestParams);

        mListener.onSearchFragmentStartSearchingWithButton(intentBtnSearch);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Sets fedeo request params.
     *
     * @param fedeoRequestParams the fedeo request params
     */
    public void setFedeoRequestParams(FedeoRequestParams fedeoRequestParams) {
        this.fedeoRequestParams = fedeoRequestParams;
    }

    /**
     * Sets query.
     *
     * @param query the query
     */
    public void setQuery(String query) {
        if (rootView == null) return;
        SearchView searchView = (SearchView) rootView.findViewById(R.id.search_frag_searchview);
        if (searchView != null) searchView.setQuery(query, false);
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
    public interface OnSearchFragmentListener {
        /**
         * On search fragment basic params choose.
         */
        void onSearchFragmentBasicParamsChoose();

        /**
         * On search fragment advance params choose.
         */
        void onSearchFragmentAdvanceParamsChoose();

        /**
         * On search fragment start searching with button.
         *
         * @param intent the intent
         */
        void onSearchFragmentStartSearchingWithButton(Intent intent);

        /**
         * On search fragment send fedeo params.
         *
         * @param fedeoRequestParams the fedeo request params
         */
        void onSearchFragmentSendFedeoParams(FedeoRequestParams fedeoRequestParams);
    }

}
