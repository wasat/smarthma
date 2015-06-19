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

import pl.wasat.smarthma.model.om.EntryOM;

public class FavoritesDbAdapter
{
    private static final String KEY_ROWID = BaseColumns._ID;
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE_PUBLISHED = "date_published";
    private static final String KEY_DATE_UPDATED = "date_updated";

    private static final String DATABASE_NAME = "favorites";
    private static final String DATABASE_TABLE_PRODUCTS = "products";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_LIST_TABLE = "create table " + DATABASE_TABLE_PRODUCTS + " (" +
            KEY_ROWID + " integer primary key autoincrement, " +
            KEY_TITLE + " text not null, " +
            KEY_DATE_PUBLISHED + " text not null, " +
            KEY_DATE_UPDATED + " text not null);";

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;
    private final Context context;

    public FavoritesDbAdapter(Context c) {
        context = c;
    }

    public FavoritesDbAdapter openToRead() throws SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }

    public FavoritesDbAdapter openToWrite() throws SQLException {
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
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PRODUCTS);
            onCreate(db);
        }
    }

    public long insertEntry(EntryOM entry)
    {
        ContentValues initialValues = new ContentValues();
        String title = entry.getTitle();
        String pubDate = entry.getPublished();
        String updDate = entry.getUpdated();
        if (entry == null)
        {
            Log.d("ZX", "Error: entry is null. Aborting database insertion.");
            return -2;
        }
        else if (title != null && pubDate != null && updDate != null)
        {
            initialValues.put(KEY_TITLE, title);
            initialValues.put(KEY_DATE_PUBLISHED, pubDate);
            initialValues.put(KEY_DATE_UPDATED, updDate);
            Log.d("ZX", "Inserting product: " + title + " " + pubDate + " " + updDate);
            return sqLiteDatabase.insert(DATABASE_TABLE_PRODUCTS, null, initialValues);
        }
        Log.d("ZX", "Error: entry contains invalid values. Aborting database insertion.");
        return -1;
    }

    public ArrayList<String[]> getAll() {
        ArrayList<String[]> result = new ArrayList();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE_PRODUCTS, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String[] values = new String[6];
            values[0] = res.getString(res.getColumnIndex(KEY_ROWID));
            values[1] = res.getString(res.getColumnIndex(KEY_TITLE));
            values[2] = res.getString(res.getColumnIndex(KEY_DATE_PUBLISHED));
            values[3] = res.getString(res.getColumnIndex(KEY_DATE_UPDATED));
            result.add(values);
            res.moveToNext();
        }
        return result;
    }

}