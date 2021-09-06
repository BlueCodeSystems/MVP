package com.bluecodesystems.mvpapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bluecodesystems.mvpapp.model.MyData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "testdb";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create guests table
        db.execSQL(MyData.CREATE_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + MyData.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void saveData(String uid, String firstname, String lastname, String province) {

        ContentValues values = new ContentValues();

        values.put(MyData.UID, uid);
        values.put(MyData.FIRSTNAME, firstname);
        values.put(MyData.LASTNAME, lastname);
        values.put(MyData.PROVINCE, province);

        SQLiteDatabase db = this.getReadableDatabase();
        db.insert(MyData.TABLE_NAME, null, values);

        db.close();
    }

    //get all Data from db
    public List<MyData> fetch() {
        List<MyData> data = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + MyData.TABLE_NAME + " ORDER BY " +
                MyData.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                MyData d = new MyData();
                d.setId(cursor.getInt(cursor.getColumnIndex(MyData.COLUMN_ID)));
                d.setUid(cursor.getString(cursor.getColumnIndex(MyData.UID)));
                d.setFirstname(cursor.getString(cursor.getColumnIndex(MyData.FIRSTNAME)));
                d.setLastname(cursor.getString(cursor.getColumnIndex(MyData.LASTNAME)));
                d.setProvince(cursor.getString(cursor.getColumnIndex(MyData.PROVINCE)));


                data.add(d);

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return data;
    }
}
