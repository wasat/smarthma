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

package pl.wasat.smarthma.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.iso.EntryISO;

/**
 * The type Favourites db adapter.
 */
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
    private final Context context;
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    /**
     * Instantiates a new Favourites db adapter.
     *
     * @param c the c
     */
    public FavouritesDbAdapter(Context c) {
        context = c;
    }

    /**
     * Open to read favourites db adapter.
     *
     * @return the favourites db adapter
     * @throws SQLException the sql exception
     */
    public FavouritesDbAdapter openToRead() throws SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }

    /**
     * Open to write favourites db adapter.
     *
     * @return the favourites db adapter
     * @throws SQLException the sql exception
     */
    public FavouritesDbAdapter openToWrite() throws SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }

    /**
     * Close.
     */
    public void close() {
        sqLiteHelper.close();
    }

    /**
     * Insert entry long.
     *
     * @param item the item
     * @return the long
     */
    public long insertEntry(Entry item) {
        ContentValues initialValues = new ContentValues();
        Gson gson = new Gson();
        Entry testEntry = item.safeClone();
        testEntry.setFavourite(true);

        byte[] bytes = gson.toJson(testEntry).getBytes();
        initialValues.put(KEY_RAW_DATA, bytes);
        return sqLiteDatabase.insert(DATABASE_TABLE_PRODUCTS, null, initialValues);
    }

    /**
     * Remove entry int.
     *
     * @param item the item
     * @return the int
     */
    public int removeEntry(Entry item) {
        int result = 0;
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE_PRODUCTS, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            byte[] blob = res.getBlob(res.getColumnIndex(KEY_RAW_DATA));
            String json = new String(blob);
            Gson gson = new Gson();
            Entry o = gson.fromJson(json, new TypeToken<Entry>() {
            }.getType());
            if (o.simpleEquals(item)) {
                // don't stop, keep looking for duplicates
                result += sqLiteDatabase.delete(DATABASE_TABLE_PRODUCTS, KEY_ROWID + " = ? ", new String[]{res.getString(res.getColumnIndex(KEY_ROWID))});
            }
            res.moveToNext();
        }
        return result;
    }

    /**
     * Replace entry.
     *
     * @param item the item
     */
    public void replaceEntry(EntryISO item) {
        removeEntry(item);
        insertEntry(item);
    }

    /**
     * Remove entry int.
     *
     * @param item the item
     * @return the int
     */
    public int removeEntry(EntryISO item) {
        int result = 0;
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE_COLLECTIONS, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            byte[] blob = res.getBlob(res.getColumnIndex(KEY_RAW_DATA));
            String json = new String(blob);
            Gson gson = new Gson();
            EntryISO o = gson.fromJson(json, new TypeToken<EntryISO>() {
            }.getType());
            if (o.simpleEquals(item)) {
                // don't stop, keep looking for duplicates
                result += sqLiteDatabase.delete(DATABASE_TABLE_COLLECTIONS, KEY_ROWID + " = ? ", new String[]{res.getString(res.getColumnIndex(KEY_ROWID))});
            }
            res.moveToNext();
        }
        return result;
    }

    /**
     * Insert entry long.
     *
     * @param item the item
     * @return the long
     */
    public long insertEntry(EntryISO item) {
        if (item.getTitle().equals(context.getString(R.string.empty_list))) {
            return -1;
        }
        ContentValues initialValues = new ContentValues();
        Gson gson = new Gson();
        byte[] bytes = gson.toJson(item).getBytes();
        initialValues.put(KEY_RAW_DATA, bytes);
        return sqLiteDatabase.insert(DATABASE_TABLE_COLLECTIONS, null, initialValues);
    }

    /**
     * Gets iso entries.
     *
     * @return the iso entries
     */
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

    /**
     * Gets om entries.
     *
     * @return the om entries
     */
    public ArrayList<Entry> getOMEntries() {
        ArrayList<Entry> result = new ArrayList();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE_PRODUCTS, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            byte[] blob = res.getBlob(res.getColumnIndex(KEY_RAW_DATA));
            String json = new String(blob);
            Gson gson = new Gson();
            Entry o = gson.fromJson(json, new TypeToken<Entry>() {
            }.getType());
            result.add(o);
            res.moveToNext();
        }
        return result;
    }

    /**
     * Clear collections.
     */
    public void clearCollections() {
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE_COLLECTIONS, null);
        res.moveToLast();
        while (!res.isBeforeFirst()) {
            sqLiteDatabase.delete(DATABASE_TABLE_COLLECTIONS, KEY_ROWID + " = ? ", new String[]{res.getString(res.getColumnIndex(KEY_ROWID))});
            res.moveToPrevious();
        }
    }

    /**
     * Clear products.
     */
    public void clearProducts() {
        Cursor res = sqLiteDatabase.rawQuery("select * from " + DATABASE_TABLE_PRODUCTS, null);
        res.moveToLast();
        while (!res.isBeforeFirst()) {
            sqLiteDatabase.delete(DATABASE_TABLE_PRODUCTS, KEY_ROWID + " = ? ", new String[]{res.getString(res.getColumnIndex(KEY_ROWID))});
            res.moveToPrevious();
        }
    }

    /**
     * The type Sq lite helper.
     */
    public class SQLiteHelper extends SQLiteOpenHelper {
        /**
         * Instantiates a new Sq lite helper.
         *
         * @param context the context
         * @param name    the name
         * @param factory the factory
         * @param version the version
         */
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
}
