package pl.wasat.smarthma.ui.frags.search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
<<<<<<< HEAD

import java.util.ArrayList;
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.SearchHistory;
import pl.wasat.smarthma.database.SearchParams;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import pl.wasat.smarthma.helper.SimpleDate;
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
import pl.wasat.smarthma.helper.SimpleDate;
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link SearchFragment.OnSearchFragmentListener} interface to handle
 * interaction events. Use the {@link SearchFragment#newInstance} factory method
 * to create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private OnSearchFragmentListener mListener;
<<<<<<< HEAD
<<<<<<< HEAD
    private View rootView;
=======
    private SearchBasicInfoRightFragment rightPanel;
    private String[] lastBbox;
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
    private SearchBasicInfoRightFragment rightPanel;
    private String[] lastBbox;
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4

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

    public void setRightPanel(SearchBasicInfoRightFragment fragment) {
        this.rightPanel = fragment;
        updateAreaBounds();
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

<<<<<<< HEAD
<<<<<<< HEAD
        /*
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
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
<<<<<<< HEAD
<<<<<<< HEAD
        */
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4

        LinearLayout linearLayout1 = (LinearLayout) searchView.getChildAt(0);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getChildAt(2);
        LinearLayout linearLayout3 = (LinearLayout) linearLayout2.getChildAt(1);
        AutoCompleteTextView autoComplete = (AutoCompleteTextView) linearLayout3
                .getChildAt(0);
        autoComplete.setTextSize(32);

        SearchHistory searchHistory = new SearchHistory(getActivity());
        ArrayList<SearchParams> searchHistoryList = searchHistory.getSearchHistoryList(true);
        if (!searchHistoryList.isEmpty()) {
            SearchParams searchParams = searchHistoryList.get(0);
            searchView.setQuery(searchParams.getSearchPhrase(), false);
        }

        return rootView;
    }

<<<<<<< HEAD
<<<<<<< HEAD
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
=======
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    protected void refreshSearchHistoryList(Spinner spnParams) {
        SearchHistory searchHistory = new SearchHistory(getActivity());
        String[] items = searchHistory.getSearchHistoryListAsStringArray(true);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.search_history_spinner, items);
        spnParams.setAdapter(adapter);
    }

    protected void performSearchHistoryListAction(int position, SearchView searchView) {
        SearchHistory searchHistory = new SearchHistory(getActivity());
        ArrayList<SearchParams> searchHistoryList = searchHistory.getSearchHistoryList(true);
        if (rightPanel != null && searchHistoryList.size() > 0) {
            SearchParams params = searchHistoryList.get(position);

            if (searchView != null) {
                searchView.setQuery(params.getSearchPhrase(), false);
            }

            CharSequence[] cataloguesList = rightPanel.getCataloguesList();
            for (int i = 0; i < cataloguesList.length; i++) {
                //Log.d("ZX", cataloguesList[i].toString());
                if (params.getCatalogue().equalsIgnoreCase(cataloguesList[i].toString())) {
                    //Log.d("ZX", "Found match");
                    rightPanel.setCatalogue(i);
                }
            }

            Calendar calStart = Calendar.getInstance();
            SimpleDate dateStart = new SimpleDate(params.getStartDate());
            calStart.set(dateStart.getYear(), dateStart.getMonth(), dateStart.getDay(),
                    dateStart.getHours(), dateStart.getMinutes(), dateStart.getSeconds());
            Calendar calEnd = Calendar.getInstance();
            SimpleDate dateEnd = new SimpleDate(params.getEndDate());
            calEnd.set(dateEnd.getYear(), dateEnd.getMonth(), dateEnd.getDay(),
                    dateEnd.getHours(), dateEnd.getMinutes(), dateEnd.getSeconds());
            rightPanel.setDateTime(calStart, calEnd);

            if (!rightPanel.getAreaBoundsUpdated()) {
                String[] bbox = params.getBbox().split(",");
                rightPanel.setBounds(bbox[0], bbox[1], bbox[2], bbox[3]);
                lastBbox = bbox;
            }
            rightPanel.setAreaBoundsUpdated(false);
        }
    }

    protected void updateAreaBounds() {
        if (lastBbox != null) {
            rightPanel.setBounds(lastBbox[0], lastBbox[1], lastBbox[2], lastBbox[3]);
        }
    }

    public void onButtonPressed(Uri uri) {
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
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
