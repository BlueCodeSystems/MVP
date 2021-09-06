package com.bluecodesystems.mvpapp.presenter;

import android.content.Context;

import com.bluecodesystems.mvpapp.adapter.DBAdapter;
import com.bluecodesystems.mvpapp.contract.CasePlanContract;
import com.bluecodesystems.mvpapp.helper.DatabaseHelper;
import com.bluecodesystems.mvpapp.model.MyData;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class CasePlanPresenter implements CasePlanContract.Presenter {



    @Override
    public void saveForm(Context context, String jsonstring) {

        //TODO Save form data to local database

        try {
            JSONObject json = new JSONObject(jsonstring);


            JSONArray fields = json.getJSONObject("step1").getJSONArray("fields");

            String uid = UUID.randomUUID().toString();
            String firstname = fields.getJSONObject(0).get("value").toString();
            String lastname = fields.getJSONObject(1).get("value").toString();
            String province = fields.getJSONObject(2).get("value").toString();


            DatabaseHelper db = new DatabaseHelper(context);
            db.saveData(uid, firstname, lastname, province);


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    @Override
    public ArrayList<MyData> fetchData(Context context) {

        //TODO Fetch data from local database

        ArrayList<MyData> myList = new ArrayList<>();
        DatabaseHelper db = new DatabaseHelper(context);
        myList.addAll(db.fetch());


        return myList;
    }

}
