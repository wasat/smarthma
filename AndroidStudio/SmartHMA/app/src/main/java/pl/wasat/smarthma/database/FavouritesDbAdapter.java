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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.model.om.EntryOM;

public class FavouritesDbAdapter {

    private static final String KEY_ROWID = BaseColumns._ID;
    private static final String KEY_RAW_DATA = "raw_data";

    private static final String DATABASE_NAME = "favourites";
    private static final String DATABASE_TABLE_COLLECTIONS = "collections";
    private static final String DATABASE_TABLE_PRODUCTS = "products";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_TABLE_COLLECTIONS = "create table " + DATABASE_TABLE_COLLECTIONS + " (" +
            KEY_ROWID + " integer primary key autoincrement, " +
            KEY_RAW_DATA + " blob not null);";

    private static final String DATABASE_CREATE_TABLE_PRODUCTS = "create table " + DATABASE_TABLE_PRODUCTS + " (" +
            KEY_ROWID + " integer primary key autoincrement, " +
            KEY_RAW_DATA + " blob not null);";

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;
    private final Context context;

    public FavouritesDbAdapter(Context c) {
        context = c;
    }

    public FavouritesDbAdapter openToRead() throws SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }

    public FavouritesDbAdapter openToWrite() throws SQLException {
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
            db.execSQL(DATABASE_CREATE_TABLE_COLLECTIONS);
            db.execSQL(DATABASE_CREATE_TABLE_PRODUCTS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_COLLECTIONS);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PRODUCTS);
            onCreate(db);
        }
    }

    public long insertEntry(EntryISO item) {
        ContentValues initialValues = new ContentValues();
        Log.d("ZX", "Inserting collection " + item.getTitle() + " into database.");
        Gson gson = new Gson();
        byte[] bytes = gson.toJson(item).getBytes();
        initialValues.put(KEY_RAW_DATA, bytes);
        return sqLiteDatabase.insert(DATABASE_TABLE_COLLECTIONS, null, initialValues);
    }

    public long insertEntry(EntryOM item) {
        ContentValues initialValues = new ContentValues();
        Log.d("ZX", "Inserting product " + item.getTitle() + " into database.");
        Gson gson = new Gson();
        byte[] bytes = gson.toJson(item).getBytes();
        initialValues.put(KEY_RAW_DATA, bytes);
        return sqLiteDatabase.insert(DATABASE_TABLE_PRODUCTS, null, initialValues);
    }

    public int removeEntry(EntryISO item)
    {
        int result = 0;
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE_COLLECTIONS, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            byte[] blob = res.getBlob(res.getColumnIndex(KEY_RAW_DATA));
            String json = new String(blob);
            Gson gson = new Gson();
            EntryISO o = gson.fromJson(json, new TypeToken<EntryISO>()
            {
            }.getType());
            if (o.simpleEquals(item))
            {
                // don't stop, keep looking for duplicates
                result += sqLiteDatabase.delete(DATABASE_TABLE_COLLECTIONS, KEY_ROWID + " = ? ", new String[]{res.getString(res.getColumnIndex(KEY_ROWID))});
            }
            res.moveToNext();
        }
        return result;
    }

    public int removeEntry(EntryOM item)
    {
        int result = 0;
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE_PRODUCTS, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            byte[] blob = res.getBlob(res.getColumnIndex(KEY_RAW_DATA));
            String json = new String(blob);
            Gson gson = new Gson();
            EntryOM o = gson.fromJson(json, new TypeToken<EntryOM>()
            {
            }.getType());
            if (o.simpleEquals(item))
            {
                // don't stop, keep looking for duplicates
                result += sqLiteDatabase.delete(DATABASE_TABLE_PRODUCTS, KEY_ROWID + " = ? ", new String[]{res.getString(res.getColumnIndex(KEY_ROWID))});
            }
            res.moveToNext();
        }
        return result;
    }

    public void reduceNumberOfEntries(int newNumberOfEntries) {
        int numberOfEntries = getISOEntries().size();
        while (numberOfEntries > newNumberOfEntries) {
            Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE_COLLECTIONS, null);
            res.moveToFirst();
            sqLiteDatabase.delete(DATABASE_TABLE_COLLECTIONS, KEY_ROWID + " = ? ", new String[]{res.getString(res.getColumnIndex(KEY_ROWID))});
            numberOfEntries--;
        }
    }

    public ArrayList<EntryISO> getISOEntries() {
        ArrayList<EntryISO> result = new ArrayList();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE_COLLECTIONS, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            byte[] blob = res.getBlob(res.getColumnIndex(KEY_RAW_DATA));
            String json = new String(blob);
            Gson gson = new Gson();
            EntryISO o = gson.fromJson(json, new TypeToken<EntryISO>() {
            }.getType());
            result.add(o);
            res.moveToNext();
        }
        return result;
    }

    public ArrayList<EntryOM> getOMEntries() {
        ArrayList<EntryOM> result = new ArrayList();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE_PRODUCTS, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            byte[] blob = res.getBlob(res.getColumnIndex(KEY_RAW_DATA));
            String json = new String(blob);
            Gson gson = new Gson();
            EntryOM o = gson.fromJson(json, new TypeToken<EntryOM>() {
            }.getType());
            result.add(o);
            res.moveToNext();
        }
        return result;
    }

    public void recreateTable() {
        Log.d("ZX", "Recreating table");
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE_COLLECTIONS, null);
        res.moveToLast();
        while (!res.isBeforeFirst()) {
            sqLiteDatabase.delete(DATABASE_TABLE_COLLECTIONS, KEY_ROWID + " = ? ", new String[]{res.getString(res.getColumnIndex(KEY_ROWID))});
            res.moveToPrevious();
        }
    }
}