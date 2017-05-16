package com.example.muhammad.protectyou1.Model;

/**
 * Created by Muhammad on 18/04/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.muhammad.protectyou1.EmergencyContacts.EmergencyContact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Used to obtain user account information
 */

public class AccountDataBaseAdapter {
    static final String DATABASE_NAME = "protect_yourselftmp12.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_USERS = "create table users(" +
                                            "id integer primary key autoincrement," +
                                            "username text," +
                                            "password text" +
                                        ");";

    static final String DATABASE_CONTACTS =  "create table contacts(" +
                                                "user_id integer," +
                                                "id integer primary key autoincrement," +
                                                "name text," +
                                                "relation text," +
                                                "phone text" +
                                                ");";

    static final String DATABASE_CURRENT_USER = "create table current_user(" +
                                                    "user_id integer," +
                                                    "date_time text" +
                                                ");";

    static final String DATABASE_MESSAGES = "create table messages(" +
                                                    "id integer primary key autoincrement," +
                                                    "user_id integer," +
                                                    "message text" +
                                                ");";

    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public AccountDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public AccountDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    /**
     *
     * User
     *
     */

    public void insertNewUser(String userName, String password) {
        insertCurrentUser(userName);
        ContentValues newValues = new ContentValues();
        newValues.put("username", userName);
        newValues.put("password", password);
        db.insert("users", null, newValues);
    }

    // current_user table should only contain 1 row: the active user.
    // This table should be cleared on logout and on app destroy.
    // It is not the best design, BUT it will do for now: using a table to
    // track the current user, to get data from contacts table etc by user_id.
    // An alternative option was to store the user_id in SharedPreferences,
    // but this is a security/ privacy risk
    public void insertCurrentUser(String userName) {
        clearCurrentUser();
        ContentValues newValues = new ContentValues();
        newValues.put("user_id", getUserId(userName));
        newValues.put("date_time", new Date().toString());
        db.insert("current_user", null, newValues);
    }

    public int getUserId(String userName) {
        Cursor cursor = db.query("users", null, " username=?",
                new String[] { userName }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return -1;
        }
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        cursor.close();
        return id;
    }

    public boolean usernameExists(String username) {
        Cursor cursor = db.query("users", null, " username=?",
                new String[] { username }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return false;
        }
        return true;
    }

    public int getCurrentUserId() {
        Cursor cursor = db.query("current_user", null, null,
                null, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return -1;
        }
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex("user_id"));
        cursor.close();
        return id;
    }

    public String getUserPasswordByUsername(String userName) {
        Cursor cursor = db.query("users", null, " username=?",
                new String[] { userName }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("password"));
        cursor.close();
        // TODO should be hashed in future
        return password;
    }

    public void updateUserByUsername(String userName, String password) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("username", userName);
        updatedValues.put("password", password);

        String where = "username = ?";
        db.update("users", updatedValues, where, new String[] { userName });
    }

    public int deleteUserByUsername(String UserName) {
        String where = "username=?";
        return db.delete("users", where,
                new String[] { UserName });
    }

    public boolean userIsLoggedIn() {
        Cursor cursor = db.query("current_user", null, null, null, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return false;
        }
        return true;
    }

    public int clearCurrentUser() {
        return db.delete("current_user", null, null);
    }


    /**
     *
     * Contacts
     *
     */

    public void insertContact(String name, String relation, String phone) {
        ContentValues newValues = new ContentValues();
        newValues.put("user_id", getCurrentUserId());
        newValues.put("name", name);
        newValues.put("relation", relation);
        newValues.put("phone", phone);
        db.insert("contacts", null, newValues);
    }

    public List<EmergencyContact> getAllContacts() {
        Cursor cursor = db.query("contacts", null, "user_id=" + getCurrentUserId(),
                null, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return null;
        }
        List<EmergencyContact> contacts = new ArrayList<EmergencyContact>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            contacts.add(new EmergencyContact(cursor.getInt(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("relation")), cursor.getString(cursor.getColumnIndex("phone"))));
        }

        cursor.close();
        return contacts;
    }
    public int deleteContact(int id) {
        String where = "username=?";
        return db.delete("contacts", "user_id=" + getCurrentUserId() + " AND id=" + id, null);
    }

    /**
     * Predefined Emergency Message
     */
    public void updateMessage(String msg) {
        clearMessage();
        ContentValues newValues = new ContentValues();
        newValues.put("user_id", getCurrentUserId());
        newValues.put("message", msg);
        db.insert("messages", null, newValues);
    }

    public String getUserMessage() {
        Cursor cursor = db.query("messages", null, "user_id=" + getCurrentUserId(),
                null, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        String message = cursor.getString(cursor.getColumnIndex("message"));
        cursor.close();
        return message;
    }

    public int clearMessage() {
        return db.delete("messages", "user_id=" + getCurrentUserId(), null);
    }


}