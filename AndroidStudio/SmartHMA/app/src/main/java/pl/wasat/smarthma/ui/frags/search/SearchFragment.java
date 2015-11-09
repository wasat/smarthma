package pl.wasat.smarthma.ui.frags.search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;

import java.util.HashMap;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.preferences.SharedPrefs;
import pl.wasat.smarthma.ui.activities.SearchCollectionResultsActivity;
import pl.wasat.smarthma.ui.tooltips.Tooltip;

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
    private Tooltip tooltip;
    private HashMap extraParams;

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
    }

    @Override
    public void startActivity(Intent intent) {
        Log.i("START_ACTV", "SearchFragment");
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            searchIntent = intent;
            searchIntent.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS_EXTRA, extraParams);
        }
        super.startActivity(searchIntent);
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

        searchIntent = getActivity().getIntent();

        LinearLayout linearLayout1 = (LinearLayout) searchView.getChildAt(0);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getChildAt(2);
        LinearLayout linearLayout3 = (LinearLayout) linearLayout2.getChildAt(1);
        AutoCompleteTextView autoComplete = (AutoCompleteTextView) linearLayout3
                .getChildAt(0);
        autoComplete.setTextSize(20);
        autoComplete.setTextColor(getResources().getColor(R.color.text_black));

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
                mListener.onSearchFragmentSendExtraParams(extraParams);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        tooltip = new Tooltip(getContext(), Tooltip.TYPE_BELOW, "Greetings from below!");

        final Button btnAdvanceParams = (Button) rootView.findViewById(R.id.search_frag_button_advance);
        btnAdvanceParams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSearchFragmentAdvanceParamsChoose();
/*
                if (!tooltip.isShown())
                    tooltip.show(btnAdvanceParams);*/
            }
        });

        Button btnGo = (Button) rootView.findViewById(R.id.search_frag_button_start);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSearchWithButton(searchView);
            }
        });

        extraParams = new HashMap();
        return rootView;
    }


    private void startSearchWithButton(SearchView searchView) {
        Intent intentBtnSearch = new Intent(getActivity(),
                SearchCollectionResultsActivity.class);
        intentBtnSearch.setAction("android.intent.action.SEARCH");
        intentBtnSearch.putExtra(SearchManager.QUERY, searchView.getQuery().toString());
        intentBtnSearch.putExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS_EXTRA, extraParams);

        mListener.onSearchFragmentStartSearchingWithButton(intentBtnSearch);
    }


    public void setAdditionalParams(String parameterKey, String parameterValue) {
        extraParams.put(parameterKey, parameterValue);
    }

    public void setQuery(String query) {
        if (rootView == null) return;
        SearchView searchView = (SearchView) rootView.findViewById(R.id.search_frag_searchview);
        if (searchView != null) searchView.setQuery(query, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
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
        void onSearchFragmentBasicParamsChoose();

        void onSearchFragmentAdvanceParamsChoose();

        void onSearchFragmentSendExtraParams(HashMap extra);

        void onSearchFragmentStartSearchingWithButton(Intent intent);

    }

}
