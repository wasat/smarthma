package pl.wasat.smarthma.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

public class HistoryDbAdapter {

    private static final String KEY_ROWID = BaseColumns._ID;
    private static final String KEY_QUERY = "query";
    private static final String KEY_CATALOGUE = "catalogue";
    private static final String KEY_BBOX = "bbox";
    private static final String KEY_START_DATE = "startdate";
    private static final String KEY_END_DATE = "enddate";

    private static final String DATABASE_NAME = "search_history";
    private static final String DATABASE_TABLE = "paramslist";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_LIST_TABLE = "create table " + DATABASE_TABLE + " (" +
            KEY_ROWID + " integer primary key autoincrement, " +
            KEY_QUERY + " text not null, " +
            KEY_CATALOGUE + " text not null, " +
            KEY_BBOX + " text not null, " +
            KEY_START_DATE + " text not null, " +
            KEY_END_DATE + " text not null);";


    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;
    private final Context context;

    public HistoryDbAdapter(Context c) {
        context = c;
    }

    public HistoryDbAdapter openToRead() throws SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }

    public HistoryDbAdapter openToWrite() throws SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        sqLiteHelper.close();
    }

    public class SQLiteHelper extends SQLiteOpenHelper {
        public SQLiteHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_LIST_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public long insertEntry(SearchParams parameters) {
        ContentValues initialValues = new ContentValues();
        String searchPhrase = parameters.getSearchPhrase();
        String catalogue = parameters.getCatalogue();
        String bbox = parameters.getBbox();
        String startDate = parameters.getStartDate();
        String endDate = parameters.getEndDate();
        Log.d("ZX", searchPhrase);
        Log.d("ZX", catalogue);
        Log.d("ZX", bbox);
        Log.d("ZX", startDate);
        Log.d("ZX", endDate);
        initialValues.put(KEY_QUERY, parameters.getSearchPhrase());
        initialValues.put(KEY_CATALOGUE, parameters.getCatalogue());
        initialValues.put(KEY_BBOX, parameters.getBbox());
        initialValues.put(KEY_START_DATE, parameters.getStartDate());
        initialValues.put(KEY_END_DATE, parameters.getEndDate());
        return sqLiteDatabase.insert(DATABASE_TABLE, null, initialValues);
    }

    public void reduceNumberOfEntries(int newNumberOfEntries) {
        int numberOfEntries = getAll().size();
        while (numberOfEntries > newNumberOfEntries) {
            Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE, null);
            res.moveToFirst();
            sqLiteDatabase.delete(DATABASE_TABLE, KEY_ROWID + " = ? ", new String[]{res.getString(res.getColumnIndex(KEY_ROWID))});
            numberOfEntries--;
        }
    }

    public ArrayList<String[]> getAll() {
        ArrayList<String[]> result = new ArrayList();
        //hp = new HashMap();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String[] values = new String[6];
            values[0] = res.getString(res.getColumnIndex(KEY_ROWID));
            values[1] = res.getString(res.getColumnIndex(KEY_QUERY));
            values[2] = res.getString(res.getColumnIndex(KEY_CATALOGUE));
            values[3] = res.getString(res.getColumnIndex(KEY_BBOX));
            values[4] = res.getString(res.getColumnIndex(KEY_START_DATE));
            values[5] = res.getString(res.getColumnIndex(KEY_END_DATE));
            result.add(values);
            res.moveToNext();
        }
        return result;
    }

    public void recreateTable() {
        Log.d("ZX", "Recreating table");
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE, null);
        res.moveToLast();
        while (!res.isBeforeFirst()) {
            sqLiteDatabase.delete(DATABASE_TABLE, KEY_ROWID + " = ? ", new String[]{res.getString(res.getColumnIndex(KEY_ROWID))});
            res.moveToPrevious();
        }
    }
}