package pl.wasat.smarthma.parser.database;

/**
 * Created by marcel on 2015-08-04 00:09.
 * Part of the project  SmartHMA
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import pl.wasat.smarthma.parser.Parser.BaseParser;
import pl.wasat.smarthma.parser.model.Category;
import pl.wasat.smarthma.parser.model.Mission;
import pl.wasat.smarthma.parser.model.Page;


public class ParserDb {


    // STALE POTRZEBNE DO POZNIEJSZYCH DEFINICJI , KOMEND SQLOWYCH , FUNKCJI

    private static final String DEBUG_TAG = "SqLite Db";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "wasat.db";
    private static final String CATEGORIES_TABLE = "categories";
    private static final String MISSIONS_TABLE = "missions";
    private static final String PAGES_TABLE = "pages";


    private static final String KEY_ID = "_id";
    private static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final int ID_COLUMN = 0;

    // stale do tabeli categories

    private static final String KEY_CATEGORY_NAME = "name";
    private static final String NAME_CATEGORY_OPTIONS = "TEXT NOT NULL";
    public static final int NAME_CATEGORY_COLUMN = 1;

    private static final String KEY_CATEGORY_DATA = "data";
    private static final String DATA_CATEGORY_OPTIONS = "TEXT";
    public static final int DATA_CATEGORY_COLUMN = 2;


    // stale do tabeli missions

    private static final String KEY_MISSIONS_NAME = "name";
    private static final String MISSIONS_NAME_OPTIONS = "TEXT NOT NULL";
    private static final int MISIONS_NAME_COLUMN = 1;

    private static final String KEY_MISSIONS_DATA = "data";
    private static final String MISSIONS_DATA_OPTIONS = "TEXT";
    private static final int MISSIONS_DATA_COLUMN = 2;

    private static final String KEY_MISSIONS_CATEGORY_ID = "category_id";
    private static final String MISSIONS_CATEGORY_ID_OPTIONS = "INTEGER DEFAULT -1";
    private static final int MISSIONS_CATEGORY_ID_COLUMN = 3;

    // stale do tabeli pages

    private static final String KEY_PAGES_NAME = "name";
    private static final String PAGES_NAME_OPTIONS = "TEXT NOT NULL";
    private static final int PAGES_NAME_COLUMN = 1;

    private static final String KEY_PAGES_DATA = "data";
    private static final String PAGES_DATA_OPTIONS = "TEXT";
    private static final int PAGES_DATA_COLUMN = 2;

    private static final String KEY_PAGES_CATEGORY_ID = "category_id";
    private static final String PAGES_CATEGORY_ID_OPTIONS = "INTEGER DEFAULT -1";
    private static final int PAGES_CATEGORY_ID_COLUMN = 3;

    private static final String KEY_PAGES_MISSION_ID = "mission_id";
    private static final String PAGES_MISSION_ID_OPTIONS = "INTEGER DEFAULT -1";
    private static final int PAGES_MISSION_ID_COLUMN = 4;

    private static final String TRUNCATE_TABLE_CATEGORIES = "DELETE FROM CATEGORIES;";
    private static final String TRUNCATE_TABLE_MISSIONS = "DELETE FROM MISSIONS;";
    private static final String TRUNCATE_TABLE_PAGES = "DELETE FROM PAGES;";

    // SQL KOMENDA TO TWORZENIA TABELI

    private static final String CATEGORIES_CREATE_TABLE =
            "CREATE TABLE " + CATEGORIES_TABLE + "( " +
                    KEY_ID + ", " +
                    KEY_CATEGORY_NAME + " " + NAME_CATEGORY_OPTIONS + ", " +
                    KEY_CATEGORY_DATA + " " + DATA_CATEGORY_OPTIONS +
                    ");";

    private static final String MISSIONS_CREATE_TABLE =
            "CREATE TABLE " + MISSIONS_TABLE + "( " +
                    KEY_ID + ", " +
                    KEY_MISSIONS_NAME + " " + MISSIONS_NAME_OPTIONS + ", " +
                    KEY_MISSIONS_DATA + " " + MISSIONS_DATA_OPTIONS + ", " +
                    KEY_MISSIONS_CATEGORY_ID + " " + MISSIONS_CATEGORY_ID_OPTIONS +
                    ");";

    private static final String PAGES_CREATE_TABLE =
            "CREATE TABLE " + PAGES_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_PAGES_NAME + " " + PAGES_NAME_OPTIONS + ", " +
                    KEY_PAGES_DATA + " " + PAGES_DATA_OPTIONS + ", " +
                    KEY_PAGES_CATEGORY_ID + " " + PAGES_CATEGORY_ID_OPTIONS + ", " +
                    KEY_PAGES_MISSION_ID + " " + PAGES_MISSION_ID_OPTIONS +
                    ");";


    private static final String DB_DROP_TABLE =
            "DROP TABLE IF EXISTS " + CATEGORIES_CREATE_TABLE + "," + MISSIONS_CREATE_TABLE +
                    "," + PAGES_CREATE_TABLE;

    private SQLiteDatabase db;
    private final Context context;
    private DatabaseHelper dbHelper;


    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // TWORZENIE TABEL ZA POMOCA KOMEND SQLOWYCH

            db.execSQL(CATEGORIES_CREATE_TABLE);
            db.execSQL(MISSIONS_CREATE_TABLE);
            db.execSQL(PAGES_CREATE_TABLE);

            // WRZUCANIE DO TABEL WARTOSCI JAKIE CHCEMY ABY BYLY NA POCZATKU
            // ID SIE PRZYPISUJE AUTOMATYCZNIE WIEC ID NIE RUSZAMY


            Log.d(DEBUG_TAG, "Tworzenie bazy...");
            Log.d(DEBUG_TAG, "Table " + CATEGORIES_CREATE_TABLE + "," + MISSIONS_CREATE_TABLE + "," +
                    PAGES_CREATE_TABLE + " ver." + DB_VERSION + " created");

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


            db.execSQL(DB_DROP_TABLE);
            // Create tables again
            onCreate(db);

            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, "Table " + CATEGORIES_CREATE_TABLE + "," + MISSIONS_CREATE_TABLE + "," +
                    PAGES_CREATE_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");
        }


    }

    // KONSTRUKTOR KLASY DB

    public ParserDb(Context context) {
        this.context = context;
    }

    // METODA OTWIERAJACA BAZE

    public ParserDb open() {
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }

        return this;
    }


    // METODA ZAMYKAJACA BAZE
    public void close() {
        dbHelper.close();
    }


    // METODY DO MODYFKACJI DANYCH W TABELACH


    public boolean addCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, category.getId());
        values.put(KEY_CATEGORY_NAME, category.getName());
        values.put(KEY_CATEGORY_DATA, category.getData());

        return db.insert(CATEGORIES_TABLE, null, values) > 0;
    }


    public boolean addMission(Mission mission) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, mission.getId());
        values.put(KEY_MISSIONS_NAME, mission.getName());
        values.put(KEY_MISSIONS_DATA, mission.getData());
        values.put(KEY_MISSIONS_CATEGORY_ID, mission.getCategory_id());

        return db.insert(MISSIONS_TABLE, null, values) > 0;
    }

    public boolean addPage(Page page) {
        ContentValues values = new ContentValues();
        values.put(KEY_PAGES_NAME, page.getName());
        values.put(KEY_PAGES_DATA, page.getData());
        values.put(KEY_PAGES_CATEGORY_ID, page.getCategory_id());
        values.put(KEY_PAGES_MISSION_ID, page.getMission_id());

        return db.insert(PAGES_TABLE, null, values) > 0;
    }

    public void truncateTables() {
        db.execSQL(TRUNCATE_TABLE_CATEGORIES);
        db.execSQL(TRUNCATE_TABLE_MISSIONS);
        db.execSQL(TRUNCATE_TABLE_PAGES);
    }

    public Mission getMission(int missionId) {

        Mission mission = new Mission();

        String[] columns = {KEY_ID, KEY_MISSIONS_NAME, KEY_MISSIONS_DATA, KEY_MISSIONS_CATEGORY_ID};
        Cursor cursor = db.query(MISSIONS_TABLE, columns, KEY_ID + "=" + missionId, null, null, null, null);
        if (cursor == null) {
            return mission;
        }

        if (cursor.moveToFirst()) {
            do {
                mission = new Mission(cursor.getInt(ID_COLUMN), cursor.getInt(MISSIONS_CATEGORY_ID_COLUMN),
                        cursor.getString(MISIONS_NAME_COLUMN), cursor.getString(MISSIONS_DATA_COLUMN));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return mission;
    }

    public ArrayList<Mission> getMissionsList(Category category) {
        ArrayList<Mission> list = new ArrayList<>();
        String[] columns = {KEY_ID, KEY_MISSIONS_NAME, KEY_MISSIONS_DATA, KEY_MISSIONS_CATEGORY_ID};
        Cursor cursor = db.query(MISSIONS_TABLE, columns, KEY_MISSIONS_CATEGORY_ID + "=" + category.getId(), null, null, null, KEY_ID);
        if (cursor == null) {
            return list;
        }

        if (cursor.moveToFirst()) {
            do {
                Mission mission = new Mission(cursor.getInt(ID_COLUMN), cursor.getInt(MISSIONS_CATEGORY_ID_COLUMN),
                        cursor.getString(MISIONS_NAME_COLUMN), cursor.getString(MISSIONS_DATA_COLUMN));
                mission.setImageUrl(getMissionImage(mission));
                mission.setData(getMainPageContentString(mission));
                list.add(mission);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    public Mission getMission(String missionName) {

        Mission mission = new Mission();

        String[] columns = {KEY_ID, KEY_MISSIONS_NAME, KEY_MISSIONS_DATA, KEY_MISSIONS_CATEGORY_ID};
        Cursor cursor = db.query(MISSIONS_TABLE, columns, KEY_MISSIONS_NAME + "=" + missionName, null, null, null, null);
        if (cursor == null) {
            return mission;
        }

        if (cursor.moveToFirst()) {
            do {
                mission = new Mission(cursor.getInt(ID_COLUMN), cursor.getInt(MISSIONS_CATEGORY_ID_COLUMN),
                        cursor.getString(MISIONS_NAME_COLUMN), cursor.getString(MISSIONS_DATA_COLUMN));

            } while (cursor.moveToNext());
        }

        cursor.close();
        return mission;
    }

    private String getMissionImage(Mission mission) {
        String results = "";


        String[] columns = {KEY_ID, KEY_PAGES_NAME, KEY_PAGES_DATA, KEY_PAGES_CATEGORY_ID, KEY_PAGES_MISSION_ID};
        Cursor cursor = db.query(PAGES_TABLE, columns, KEY_PAGES_MISSION_ID + "=" + mission.getId() + " AND " +
                KEY_PAGES_NAME + "='" + BaseParser.IMG_NAME + "'", null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                results = cursor.getString(PAGES_DATA_COLUMN);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return results;
    }

    public ArrayList<Page> getPageList(Mission mission) {
        ArrayList<Page> list = new ArrayList<>();
        String[] columns = {KEY_ID, KEY_PAGES_NAME, KEY_PAGES_DATA, KEY_PAGES_CATEGORY_ID, KEY_PAGES_MISSION_ID};
        Cursor cursor = db.query(PAGES_TABLE, columns, KEY_PAGES_MISSION_ID + "=" + mission.getId(), null, null, null, null);
        if (cursor == null) {
            return list;
        }

        if (cursor.moveToFirst()) {
            do {
                list.add(new Page(cursor.getInt(ID_COLUMN), cursor.getInt(PAGES_CATEGORY_ID_COLUMN),
                        cursor.getInt(PAGES_MISSION_ID_COLUMN), cursor.getString(PAGES_NAME_COLUMN), cursor.getString(PAGES_DATA_COLUMN)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;

    }

    public ArrayList<Page> getMainPageContentList(Mission mission) {
        ArrayList<Page> list = new ArrayList<>();
        String[] columns = {KEY_ID, KEY_PAGES_NAME, KEY_PAGES_DATA, KEY_PAGES_CATEGORY_ID, KEY_PAGES_MISSION_ID};
        Cursor cursor = db.query(PAGES_TABLE, columns, KEY_PAGES_MISSION_ID + "=" + mission.getId() + " AND " + KEY_PAGES_NAME + " LIKE [MAIN_INFO]%", null, null, null, null);
        if (cursor == null) {
            return list;
        }

        if (cursor.moveToFirst()) {
            do {
                list.add(new Page(cursor.getInt(ID_COLUMN), cursor.getInt(PAGES_CATEGORY_ID_COLUMN),
                        cursor.getInt(PAGES_MISSION_ID_COLUMN), cursor.getString(PAGES_NAME_COLUMN), cursor.getString(PAGES_DATA_COLUMN)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;

    }

    public int getMissionCount() {
        Cursor mCount = db.rawQuery("select count(*) from missions", null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }

    private String getMainPageContentString(Mission mission) {
        ArrayList<Page> list = new ArrayList<>();
        String[] columns = {KEY_ID, KEY_PAGES_NAME, KEY_PAGES_DATA, KEY_PAGES_CATEGORY_ID, KEY_PAGES_MISSION_ID};
        Cursor cursor = db.query(PAGES_TABLE, columns, KEY_PAGES_MISSION_ID + "=" + mission.getId() + " AND " + KEY_PAGES_NAME + " LIKE '[MAIN_INFO]%'", null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new Page(cursor.getInt(ID_COLUMN), cursor.getInt(PAGES_CATEGORY_ID_COLUMN),
                        cursor.getInt(PAGES_MISSION_ID_COLUMN), cursor.getString(PAGES_NAME_COLUMN), cursor.getString(PAGES_DATA_COLUMN)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        StringBuilder stringBuilder = new StringBuilder();
        for (Page page :
                list) {
            stringBuilder.append(page.getData());
        }
        return stringBuilder.toString();

    }


}