package com.example.muhammad.protectyou1.DataAccess;

/**
 * Created by Muhammad on 18/04/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Assists in connecting to SQLite in app database.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(AccountDataBaseAdapter.DATABASE_USERS);
        _db.execSQL(AccountDataBaseAdapter.DATABASE_CONTACTS);
        _db.execSQL(AccountDataBaseAdapter.DATABASE_CURRENT_USER);
        _db.execSQL(AccountDataBaseAdapter.DATABASE_MESSAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to "
                + _newVersion + ", which will destroy all old data");
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");

        onCreate(_db);
    }

}