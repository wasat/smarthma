package pl.wasat.smarthma.database;

import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.SubMenu;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.helper.Const;

/**
 * Used to manage user search history parameters stored in the database.
 */
public class SearchHistory {
    private final Context context;

    public SearchHistory(Context context) {
        this.context = context;
    }

    /**
     * Insert user search history parameters into the database.
     * @param parameters    a SearchParams object
     */
    public void addSearchParameters(SearchParams parameters) {
        Log.d("ZX", "addSearchParameters()");
        HistoryDbAdapter dba = new HistoryDbAdapter(context);
        dba.openToWrite();

        long dbaResult = dba.insertEntry(parameters);
        Log.d("ZX", "Database result on inserting entry: " + dbaResult);
        dba.reduceNumberOfEntries(Const.MAX_SEARCH_HISTORY_ENTRIES);
        dba.close();

        Log.d("ZX", "---");
        dba.openToRead();
        ArrayList<String[]> all = dba.getAll();
        for (String[] s : all) {
            String tmp = "";
            for (String value : s) {
                tmp += value + " ";
            }
            Log.d("ZX", tmp);
        }
        dba.close();
        Log.d("ZX", "---");
    }

    /**
     * Returns a list of recently used search history parameters stored in the database.
     * @param reversedOrder if true, the list will be sorted in reversed order (newer items first)
     * @return a list of SearchParams objects
     */
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

    /**
     * Returns an array of recently used search history parameters stored in the database.
     * @param reversedOrder if true, the list will be sorted in reversed order (newer items first)
     * @return an array of String representations of SearchParams objects
     */
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

    /**
     * Removes all user search history parameters stored in the database.
     */
    public void clearHistory() {
        HistoryDbAdapter dba = new HistoryDbAdapter(context);
        dba.openToWrite();
        dba.recreateTable();
        dba.close();
    }

    /**
     * Returns a list of recently used user search queries stored in the database.
     * @param reversedOrder if true, the list will be sorted in reversed order (newer items first)
     * @return a list of String representations of search queries
     */
    public ArrayList<String> getQueries(boolean reversedOrder) {
        ArrayList<SearchParams> searchHistoryList = getSearchHistoryList(reversedOrder);
        ArrayList<String> results = new ArrayList<>();
        for (SearchParams params : searchHistoryList) {
            results.add(params.getSearchPhrase());
        }
        removeDuplicates(results);
        return results;
    }

    /**
     * Returns a list of recently used catalogues stored in the database.
     * @param reversedOrder if true, the list will be sorted in reversed order (newer items first)
     * @return a list of catalogue names
     */
    public ArrayList<String> getCatalogues(boolean reversedOrder) {
        ArrayList<SearchParams> searchHistoryList = getSearchHistoryList(reversedOrder);
        ArrayList<String> results = new ArrayList<>();
        for (SearchParams params : searchHistoryList) {
            results.add(params.getCatalogue());
        }
        removeDuplicates(results);
        return results;
    }

    /**
     * Returns a list of recently used bounding boxes describing user selected areas.
     * @param reversedOrder if true, the list will be sorted in reversed order (newer items first)
     * @return a list of String representations of bounding boxes
     */
    public ArrayList<String> getBboxs(boolean reversedOrder) {
        ArrayList<SearchParams> searchHistoryList = getSearchHistoryList(reversedOrder);
        ArrayList<String> results = new ArrayList<>();
        for (SearchParams params : searchHistoryList) {
            results.add(params.getBbox());
        }
        removeDuplicates(results);
        return results;
    }

    /**
     * Returns a list of recently used start dates stored in the database.
     * @param reversedOrder if true, the list will be sorted in reversed order (newer items first)
     * @return a list of String representations of start dates
     */
    public ArrayList<String> getStartDates(boolean reversedOrder) {
        ArrayList<SearchParams> searchHistoryList = getSearchHistoryList(reversedOrder);
        ArrayList<String> results = new ArrayList<>();
        for (SearchParams params : searchHistoryList) {
            results.add(params.getStartDate());
        }
        removeDuplicates(results);
        return results;
    }

    /**
     * Returns a list of recently used end dates stored in the database.
     * @param reversedOrder if true, the list will be sorted in reversed order (newer items first)
     * @return a list of String representations of end dates
     */
    public ArrayList<String> getEndDates(boolean reversedOrder) {
        ArrayList<SearchParams> searchHistoryList = getSearchHistoryList(reversedOrder);
        ArrayList<String> results = new ArrayList<>();
        for (SearchParams params : searchHistoryList) {
            results.add(params.getEndDate());
        }
        removeDuplicates(results);
        return results;
    }

    /**
     * Removes duplicate items from a given list.
     * @param list  a list of String objects
     */
    private void removeDuplicates(ArrayList<String> list) {
        if (list.size() < 2) {
            return;
        }

        ArrayList<String> arr = new ArrayList<>();
        boolean doInsert;
        for (String str : list) {
            doInsert = true;
            for (String s : arr) {
                if (s.equals(str)) {
                    doInsert = false;
                    break;
                }
            }
            if (doInsert) {
                arr.add(str);
            }
        }
        list.clear();
        list.addAll(arr);
        /*
        HashSet hs = new HashSet();
        hs.addAll(list);
        list.clear();
        list.addAll(hs);
        */
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

    /**
     * Adds search parameters items to the given menu.
     * @param searchMenu            the menu item to which new items will be attached
     * @param MENU_QUERY_IDS        ID used to distinguish query items
     * @param MENU_CATALOGUE_IDS    ID used to distinguish catalogue items
     * @param MENU_BBOX_IDS         ID used to distinguish bounding box items
     * @param MENU_STARTDATE_IDS    ID used to distinguish start date items
     * @param MENU_ENDDATE_IDS      ID used to distinguish end date items
     * @param MENU_CLEAR_ID         ID used for the "Clear" item
     */
    public void createSearchMenu(SubMenu searchMenu, int MENU_QUERY_IDS, int MENU_CATALOGUE_IDS, int MENU_BBOX_IDS, int MENU_STARTDATE_IDS, int MENU_ENDDATE_IDS, int MENU_CLEAR_ID) {
        //ArrayList<SearchParams> searchHistoryList = searchHistory.getSearchHistoryList(true);
        ArrayList<String> queries = getQueries(true);
        ArrayList<String> catalogues = getCatalogues(true);
        ArrayList<String> bboxs = getBboxs(true);
        ArrayList<String> startDates = getStartDates(true);
        ArrayList<String> endDates = getEndDates(true);

        //Log.d("ZX", "item: "+menu.getItem(0).getSubMenu().getItem(0).getTitle());
        searchMenu.clear();

        SubMenu pickQueriesMenu = searchMenu.addSubMenu(context.getString(R.string.search_history_pick_query));
        for (String str : queries) {
            pickQueriesMenu.add(MENU_QUERY_IDS, Menu.NONE, Menu.NONE, str);
        }

        SubMenu pickCataloguesMenu = searchMenu.addSubMenu(context.getString(R.string.search_history_pick_catalogue));
        for (String str : catalogues) {
            pickCataloguesMenu.add(MENU_CATALOGUE_IDS, Menu.NONE, Menu.NONE, str);
        }

        SubMenu pickBboxsMenu = searchMenu.addSubMenu(context.getString(R.string.search_history_pick_bbox));
        for (String str : bboxs) {
            pickBboxsMenu.add(MENU_BBOX_IDS, Menu.NONE, Menu.NONE, str);
        }

        SubMenu pickStartDatesMenu = searchMenu.addSubMenu(context.getString(R.string.search_history_pick_start_date));
        for (String str : startDates) {
            pickStartDatesMenu.add(MENU_STARTDATE_IDS, Menu.NONE, Menu.NONE, str);
        }

        SubMenu pickEndDatesMenu = searchMenu.addSubMenu(context.getString(R.string.search_history_pick_end_date));
        for (String str : endDates) {
            pickEndDatesMenu.add(MENU_ENDDATE_IDS, Menu.NONE, Menu.NONE, str);
        }

        searchMenu.add(MENU_CLEAR_ID, MENU_CLEAR_ID, Menu.NONE, R.string.search_history_clear);

        //MenuItem clearItem = searchMenu.add(Menu.NONE, MENU_CLEAR_ID, Menu.NONE, getString(R.string.search_history_clear));
    }
}
