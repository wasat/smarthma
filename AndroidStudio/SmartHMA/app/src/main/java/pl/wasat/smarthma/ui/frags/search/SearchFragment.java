package pl.wasat.smarthma.ui.frags.search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.SearchHistory;
import pl.wasat.smarthma.database.SearchParams;

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
    private Intent searchIntent;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void startActivity(Intent intent) {
        // check if search intent
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            searchIntent = intent;
        }
        super.startActivity(searchIntent);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.d("ZX", "SearchFragment: onCreateView()");
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_search, container,
                false);

        // Associate searchable configuration with the SearchView
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


        //if (Intent.ACTION_SEARCH.equals(getActivity().getIntent().getAction())) {
        searchIntent = getActivity().getIntent();
        //}

        //Intent queryIntent=getActivity().getIntent();
        /*
        final Spinner spnParams = (Spinner) rootView.findViewById(R.id.search_frag_prev_params_list);
        refreshSearchHistoryList(spnParams);
        spnParams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                performSearchHistoryListAction(position, searchView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btnParams = (Button) rootView.findViewById(R.id.search_frag_button_clear_history);
        btnParams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchHistory searchHistory = new SearchHistory(getActivity());
                searchHistory.clearHistory();
                refreshSearchHistoryList(spnParams);
            }
        });
        */

        LinearLayout linearLayout1 = (LinearLayout) searchView.getChildAt(0);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getChildAt(2);
        LinearLayout linearLayout3 = (LinearLayout) linearLayout2.getChildAt(1);
        AutoCompleteTextView autoComplete = (AutoCompleteTextView) linearLayout3
                .getChildAt(0);
        autoComplete.setTextSize(12);
        autoComplete.setTextColor(getResources().getColor(R.color.text_black));

        SearchHistory searchHistory = new SearchHistory(getActivity());
        ArrayList<SearchParams> searchHistoryList = searchHistory.getSearchHistoryList(true);
        if (!searchHistoryList.isEmpty()) {
            SearchParams searchParams = searchHistoryList.get(0);
            searchView.setQuery(searchParams.getSearchPhrase(), false);
        }

        return rootView;
    }


    public void setAdditionalParams(String parameterKey, String parameterValue) {
        searchIntent.putExtra(parameterKey, parameterValue);
    }

    public void setQuery(String query) {
        if (rootView == null) {
            return;
        }

        SearchView searchView = (SearchView) rootView.findViewById(R.id.search_frag_searchview);
        if (searchView != null) {
            searchView.setQuery(query, false);
        }
    }

/*    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSearchFragmentInteraction(uri);
        }
        Log.i("SEARCH_FRAG", "onButtonPressed");
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnSearchFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSearchFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    }

}
