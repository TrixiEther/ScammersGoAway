package com.example.scammersgoaway.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "scammersgoaway.db";
    private static final int SCHEMA = 1;
    public static final String TABLE_RULES = "rules";
    public static final String TABLE_TYPES = "types";

    public static final String RULES_COLUMN_ID = "_id";
    public static final String RULES_COLUMN_NAME = "name";
    public static final String RULES_COLUMN_TYPE = "type_id";
    public static final String RULES_COLUMN_VALUE = "value";
    public static final String RULES_COLUMN_ACTIVE = "is_active";

    public static final String TYPES_COLUMN_ID = "_id";
    public static final String TYPES_COLUMN_NAME = "name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RULES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPES);

        String rules_table = "create table if not exists "
                + TABLE_RULES + " ("
                + RULES_COLUMN_ID + " integer primary key autoincrement, "
                + RULES_COLUMN_NAME + " text not null, "
                + RULES_COLUMN_VALUE + " text not null, "
                + RULES_COLUMN_TYPE + " integer,"
                + RULES_COLUMN_ACTIVE + " bool,"
                + " FOREIGN KEY ("+ RULES_COLUMN_TYPE +") REFERENCES "+ TABLE_TYPES +"(" + TYPES_COLUMN_ID + "));";

        String types_table = "create table if not exists "
                + TABLE_TYPES + " ("
                + TYPES_COLUMN_ID + " integer primary key autoincrement, "
                + TYPES_COLUMN_NAME + " text not null);";

        String fill_table_types1 = "insert into "
                + TABLE_TYPES + "("
                + TYPES_COLUMN_NAME + ") values('Phone')";
        String fill_table_types2 = "insert into "
                + TABLE_TYPES + "("
                + TYPES_COLUMN_NAME + ") values('Code')";
        String fill_table_rules1 = "insert into "
                + TABLE_RULES + "("
                + RULES_COLUMN_NAME + ","
                + RULES_COLUMN_VALUE + ","
                + RULES_COLUMN_TYPE + ","
                + RULES_COLUMN_ACTIVE + ") values('TestRule','666-666','1','1')";

        db.execSQL(rules_table);
        db.execSQL(types_table);
        db.execSQL(fill_table_types1);
        db.execSQL(fill_table_types2);
        db.execSQL(fill_table_rules1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RULES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPES);
        onCreate(db);
    }

    public Cursor getRulesAllCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_RULES, null, null, null, null, null, null);
    }

}
