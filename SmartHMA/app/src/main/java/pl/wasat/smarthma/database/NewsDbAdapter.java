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

import pl.wasat.smarthma.model.news.NewsArticle;

/**
 * The type News db adapter.
 */
public class NewsDbAdapter {

    private static final String KEY_ROWID = BaseColumns._ID;
    private static final String KEY_GUID = "guid";
    private static final String KEY_READ = "read";
    private static final String KEY_OFFLINE = "offline";

    private static final String DATABASE_NAME = "blogposts";
    private static final String DATABASE_TABLE = "blogpostlist";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_LIST_TABLE = "create table " + DATABASE_TABLE + " (" +
            KEY_ROWID + " integer primary key autoincrement, " +
            KEY_GUID + " text not null, " +
            KEY_READ + " boolean not null, " +
            KEY_OFFLINE + " boolean not null);";
    private final Context context;
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    /**
     * Instantiates a new News db adapter.
     *
     * @param c the c
     */
    public NewsDbAdapter(Context c) {
        context = c;
    }

    /**
     * Open to read news db adapter.
     *
     * @return the news db adapter
     * @throws SQLException the sql exception
     */
    public NewsDbAdapter openToRead() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }

    /**
     * Open to write news db adapter.
     *
     * @return the news db adapter
     * @throws SQLException the sql exception
     */
    public NewsDbAdapter openToWrite() throws android.database.SQLException {
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
     * Insert blog listing long.
     *
     * @param guid the guid
     * @return the long
     */
    public long insertBlogListing(String guid) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_GUID, guid);
        initialValues.put(KEY_READ, false);
        initialValues.put(KEY_OFFLINE, false);
        return sqLiteDatabase.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Gets blog listing.
     *
     * @param guid the guid
     * @return the blog listing
     * @throws SQLException the sql exception
     */
    public NewsArticle getBlogListing(String guid) throws SQLException {
        Cursor mCursor =
                sqLiteDatabase.query(true, DATABASE_TABLE, new String[]{
                                KEY_ROWID,
                                KEY_GUID,
                                KEY_READ,
                                KEY_OFFLINE
                        },
                        KEY_GUID + "= '" + guid + "'",
                        null,
                        null,
                        null,
                        null,
                        null);
        if (mCursor != null && mCursor.getCount() > 0) {
            mCursor.moveToFirst();
            NewsArticle a = new NewsArticle();
            a.setGuid(mCursor.getString(mCursor.getColumnIndex(KEY_GUID)));
            a.setRead(mCursor.getInt(mCursor.getColumnIndex(KEY_READ)) > 0);
            a.setDbId(mCursor.getLong(mCursor.getColumnIndex(KEY_ROWID)));
            a.setOffline(mCursor.getInt(mCursor.getColumnIndex(KEY_OFFLINE)) > 0);
            return a;
        }
        return null;
    }

    /**
     * Mark as unread boolean.
     *
     * @param guid the guid
     * @return the boolean
     */
    public boolean markAsUnread(String guid) {
        ContentValues args = new ContentValues();
        args.put(KEY_READ, false);
        return sqLiteDatabase.update(DATABASE_TABLE, args, KEY_GUID + "='" + guid + "'", null) > 0;
    }

    /**
     * Mark as read boolean.
     *
     * @param guid the guid
     * @return the boolean
     */
    public boolean markAsRead(String guid) {
        ContentValues args = new ContentValues();
        args.put(KEY_READ, true);
        return sqLiteDatabase.update(DATABASE_TABLE, args, KEY_GUID + "='" + guid + "'", null) > 0;
    }

    /**
     * Save for offline boolean.
     *
     * @param guid the guid
     * @return the boolean
     */
    public boolean saveForOffline(String guid) {
        ContentValues args = new ContentValues();
        args.put(KEY_OFFLINE, true);
        return sqLiteDatabase.update(DATABASE_TABLE, args, KEY_GUID + "='" + guid + "'", null) > 0;
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
            db.execSQL(DATABASE_CREATE_LIST_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }
}