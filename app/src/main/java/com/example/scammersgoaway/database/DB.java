package com.example.scammersgoaway.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {

    private static final String DATABASE_NAME = "scammersgoaway.db";
    private static final int SCHEMA = 3;
    public static final String TABLE_RULES = "rules";

    public static final String RULES_COLUMN_ID = "_id";
    public static final String RULES_COLUMN_NAME = "name";
    public static final String RULES_COLUMN_TYPE = "type_id";
    public static final String RULES_COLUMN_VALUE = "value";
    public static final String RULES_COLUMN_ACTIVE = "is_active";

    private final Context mCtx;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DB(Context ctx) {
        mCtx = ctx;
    }

    public void open() {
        mDBHelper = new DBHelper(mCtx, DATABASE_NAME, null, SCHEMA);
        mDB = mDBHelper.getWritableDatabase();
    }

    public void close() {
        if (mDBHelper!=null) mDBHelper.close();
    }

    public Cursor getAllData() {
        return mDB.query(TABLE_RULES, null, null, null, null, null, null);
    }

    public void addRule(String name, String value, short type, boolean active) {
        ContentValues cv = new ContentValues();
        cv.put(RULES_COLUMN_NAME,name);
        cv.put(RULES_COLUMN_VALUE,value);
        cv.put(RULES_COLUMN_TYPE,type);
        cv.put(RULES_COLUMN_ACTIVE,active);
        mDB.insert(TABLE_RULES,null,cv);
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        // создаем и заполняем БД
        @Override
        public void onCreate(SQLiteDatabase db) {

            String rules_table = "create table if not exists "
                    + TABLE_RULES + " ("
                    + RULES_COLUMN_ID + " integer primary key autoincrement, "
                    + RULES_COLUMN_NAME + " text not null, "
                    + RULES_COLUMN_VALUE + " text not null, "
                    + RULES_COLUMN_TYPE + " short,"
                    + RULES_COLUMN_ACTIVE + " bool);";

            db.execSQL(rules_table);

            ContentValues cv = new ContentValues();
            cv.put(RULES_COLUMN_NAME,"TestRule");
            cv.put(RULES_COLUMN_VALUE,"6666666");
            cv.put(RULES_COLUMN_TYPE, 1);
            cv.put(RULES_COLUMN_ACTIVE, 1);
            db.insert(TABLE_RULES, null, cv);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RULES);
            onCreate(db);
        }
    }

}
