package com.example.miniproject_orifrommer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String USER_DB = "user.db";
    private static final int DB_VERSION = 1;

    private static final String USER_TABLE = "user_table";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_PASS = "user_pass";
    private static final String COLUMN_USER_ID = "userID";

    private SQLiteDatabase db;

    // constructor
    public DBHelper(@Nullable Context context) {
        super(context, USER_DB, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_PASS + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    // @input: USER type-user
    // @output: Boolean type - return true if user added to db, false otherwise
    public boolean addUser(User user) {

        db = this.getWritableDatabase(); // create and open a writable database

        // id not needed since it's set automatically
        ContentValues cv = new ContentValues(); // create new ContentValue-cv instance
        cv.put(COLUMN_USER_NAME, user.getName());
        cv.put(COLUMN_USER_PASS, user.getPass());

        long id = db.insert(USER_TABLE, null, cv); // insert the values into the table
        user.setId(id); // set user id according to the automatic inserted record
        db.close();
        return id != -1;
    }

    // @input: Long type-user id
    // @output: Boolean type - return true if user deleted from the db, false otherwise
    public boolean deleteUser(long id) {
        db = this.getWritableDatabase();
        long del = db.delete(USER_TABLE, COLUMN_USER_ID + " = " + id, null);
        db.close();
        return del != 0;
    }

    // @input: String type-user name
    // @output: ArrayList type - return a list with users matching the user name
    public ArrayList<User> searchUserName(String name) {
        ArrayList<User> users = new ArrayList<User>();

        db = this.getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE, null, COLUMN_USER_NAME + "=?", new String[]{name}, null, null, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int UserID = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID)); // same as above, but gets column index
                String UserName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME)); // same as above, but gets column index
                String UserPass = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PASS)); // same as above, but gets column index
                User p = new User(UserID, UserName, UserPass); // create a new instance of User using the values from the database
                users.add(p); // add User p to the list
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users; // return users list
    }

    // @input: String type-user password
    // @output: ArrayList type - return a list with users matching the user password
    public ArrayList<User> searchUserPassword(String password) {
        ArrayList<User> users = new ArrayList<User>();

        db = this.getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE, null, COLUMN_USER_PASS + "=?", new String[]{password}, null, null, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int UserID = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID)); // same as above, but gets column index
                String UserName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME)); // same as above, but gets column index
                String UserPass = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PASS)); // same as above, but gets column index
                User p = new User(UserID, UserName, UserPass); // create a new instance of User using the values from the database
                users.add(p); // add User p to the list
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users; // return users list
    }

    // @input: String type-user name, String type-user password
    // @output: ArrayList type - return a list with users matching the user name and password
    public ArrayList<User> searchUserName_Password(String username, String password) {
        ArrayList<User> users = new ArrayList<User>();

        db = this.getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE, null, COLUMN_USER_NAME + "=? AND " + COLUMN_USER_PASS + "=?", new String[]{username, password}, null, null, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int UserID = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID)); // same as above, but gets column index
                String UserName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME)); // same as above, but gets column index
                String UserPass = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PASS)); // same as above, but gets column index
                User p = new User(UserID, UserName, UserPass); // create a new instance of User using the values from the database
                users.add(p); // add User p to the list
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users; // return users list
    }

    public boolean userExists(String username, String password) {
        db = this.getReadableDatabase();
        boolean check = false;
//        String sql = "SELECT * FROM "+ USERS_TABLE  + " WHERE " + COLUMN_USER_NAME + " = ? AND " + COLUMN_USER_PASSWORD + " = ?";
//        Cursor cursor = db.rawQuery(sql , new String[] {username, password});
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USER_NAME + " = ? AND " + COLUMN_USER_PASS + " = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            check = true;
        cursor.close();
        db.close();
        return check;
    }

    // @input: USER type-user
    // @output: long type - return the user id from db
    public long getUserID(User user) {
        db = this.getReadableDatabase();
        long userID = -1;
        String query = "SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USER_NAME + " = ? AND " + COLUMN_USER_PASS + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{user.getName(), user.getPass()});
        //Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USER_NAME + " = ? AND " + COLUMN_USER_PASS + " = ?", new String[]{user.getName(),user.getPass()});
        if (cursor.moveToFirst()) {
            do {
                userID = cursor.getLong(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userID; // return users list
    }

    // @input: User type-user
    // Update the provided user name in the db
    public void updateUserName(User user) {
        db = this.getWritableDatabase();

//        String query = "UPDATE USER_TABLE SET COLUMN_USER_NAME=" + user.getName() + " WHERE COLUMN_USER_ID = " + user.getId();
//        db.execSQL(query);

        db.close();
    }

    // @input: User type-user
    // Update the provided user details in the db
    public void updateUser(User user) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_NAME, user.getName());
        cv.put(COLUMN_USER_PASS, user.getPass());
        db.update(USER_TABLE, cv, COLUMN_USER_ID + "=" + user.getId(), null);
        db.close();
    }

    // @output: ArrayList representing the users database content
    public ArrayList<User> viewAll() {

        ArrayList<User> users = new ArrayList<>(); // create an empty list to return
        db = this.getReadableDatabase(); // open the database for reading

        // Option 1: get data from the database using rawQuery method
        //String q = "SELECT * FROM " + User_TABLE; // create query string * indicates all
        //Cursor cursor = db.rawQuery(q, null); // create a cursor (result set) to the database using rawQuery

        // Option 2: get data from the database, using query method
        Cursor cursor = db.query(USER_TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            // loop through the cursor (result set), get the columns values, create a new User and put it into the returned list
            do {
                //int UserID = cursor.getInt(0); // ID is in column 0
                int UserID = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID)); // same as above, but gets column index
                //String UserName = cursor.getString(1); // Name is column 1
                String UserName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME)); // same as above, but gets column index
                //String UserPass = cursor.getInt(2); // Password is in column 2
                String UserPass = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PASS)); // same as above, but gets column index

                User p = new User(UserID, UserName, UserPass); // create a new instance of User using the values from the database
                users.add(p); // add User p to the list
            } while (cursor.moveToNext());
        } else {
            // failure. do nothing at this stage
        }
        // close both the cursor and database
        cursor.close();
        db.close();
        return users;
    }
}
