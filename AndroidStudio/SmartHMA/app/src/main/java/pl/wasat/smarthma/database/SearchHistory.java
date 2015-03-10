package pl.wasat.smarthma.database;

import android.content.Context;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.helper.Const;

/**
 * Created by Daniel Z. on 04.03.15.
 * Wasat Sp. z o.o.
 */
public class SearchHistory {
    Context context;

    public SearchHistory(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void addSearchParameters(SearchParams parameters) {
        HistoryDbAdapter dba = new HistoryDbAdapter(context);
        dba.openToWrite();
        dba.insertEntry(parameters);
        dba.reduceNumberOfEntries(Const.MAX_SEARCH_HISTORY_ENTRIES);
        dba.close();
    }

    public ArrayList<SearchParams> getSearchHistoryList(boolean reversedOrder) {
        ArrayList<SearchParams> result = new ArrayList<>();
        HistoryDbAdapter dba = new HistoryDbAdapter(context);
        dba.openToRead();
        ArrayList<String[]> all = dba.getAll();
        if (!reversedOrder) {
            for (String[] arr : all) {
                result.add(new SearchParams(arr[1], arr[2], arr[3], arr[4], arr[5]));
            }
        } else {
            for (String[] arr : all) {
                result.add(0, new SearchParams(arr[1], arr[2], arr[3], arr[4], arr[5]));
            }
        }
        dba.close();
        return result;
    }

    public String[] getSearchHistoryListAsStringArray(boolean reversedOrder) {
        String[] items;
        ArrayList<SearchParams> searchHistoryList = getSearchHistoryList(reversedOrder);
        if (searchHistoryList.size() > 0) {
            items = new String[searchHistoryList.size()];
            for (int i = 0; i < searchHistoryList.size(); i++) {
                items[i] = searchHistoryList.get(i).toString();
            }
        } else {
            items = new String[1];
            items[0] = SmartHMApplication.appSingleton.getString(R.string.search_history_empty);
        }
        return items;
    }

    public void clearHistory() {
        HistoryDbAdapter dba = new HistoryDbAdapter(context);
        dba.openToWrite();
        dba.recreateTable();
        dba.close();
    }

    /*
    public void test()
    {
        if (context == null)
        {
            return;
        }
        HistoryDbAdapter dba = new HistoryDbAdapter(context);
        dba.openToRead();
        ArrayList<String[]> all = dba.getAll();
        Log.d("ZX", "TEST:");
        for (String[] arr : all)
        {
            String str = "";
            for (int i=0; i<arr.length; i++)
            {
                str += arr[i] + " ";
            }
            Log.d("ZX", str);
        }
        dba.close();
    }
    */
}
