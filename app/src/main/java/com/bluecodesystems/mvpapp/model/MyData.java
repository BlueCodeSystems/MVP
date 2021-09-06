package com.bluecodesystems.mvpapp.model;

import com.bluecodesystems.mvpapp.helper.DatabaseHelper;

import java.io.Serializable;

public class MyData implements Serializable {

    public static final String TABLE_NAME = "caseplans";
    public static final String COLUMN_ID = "id";
    public static final String UID = "uid";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String PROVINCE = "province";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + UID + " TEXT,"
                    + FIRSTNAME + " TEXT,"
                    + LASTNAME + " TEXT,"
                    + PROVINCE + " TEXT"
                    + ")";

    public MyData() {

    }

    public MyData (int id, String uid, String firstname, String lastname, String province) {
        this.id = id;
        this.uid = uid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.province = province;
    }


    private int id;
    private String uid;
    private String firstname;
    private String lastname;
    private String province;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
