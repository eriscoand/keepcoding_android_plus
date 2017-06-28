package com.erisco.madridshops.domain.managers.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.erisco.madridshops.domain.managers.db.ActivityDAO.ActivityDBConstants;
import com.erisco.madridshops.domain.managers.db.ShopDAO.ShopDBConstants;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_FILE_NAME = "madridshops.sqlite";
    public static final int DATABASE_VERSION = 1;
    public static final SQLiteDatabase.CursorFactory NO_CURSOR_FACTORY = null;
    public static final long INVALID_ID = -1;


    // convenience
    public DBHelper(Context context) {
        this(context, DATABASE_FILE_NAME, NO_CURSOR_FACTORY, DATABASE_VERSION);
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        // if API LEVEL > 16
        db.setForeignKeyConstraintsEnabled(true);

        // db.execSQL("PRAGMA foreing_keys = ON");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (String script : ShopDBConstants.CREATE_DATABASE_SCRIPTS) {
            sqLiteDatabase.execSQL(script);
        }
        for (String script : ActivityDBConstants.CREATE_DATABASE_SCRIPTS) {
            sqLiteDatabase.execSQL(script);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) { // v1 --> v2
            sqLiteDatabase.execSQL(ShopDBConstants.UPDATE_DATABASE_SCRIPTS);
            sqLiteDatabase.execSQL(ActivityDBConstants.UPDATE_DATABASE_SCRIPTS);
        } else if (oldVersion == 1 && newVersion == 3) {

        }
    }

    // utility methods

    public static int convertBooleanToInt(boolean b) {
        return b ? 1 : 0;
    }

    public static boolean booleanconvertintToBoolean(int i) {
        return i != 0;
    }
}
