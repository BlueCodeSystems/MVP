package com.bluecodesystems.mvpapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bluecodesystems.mvpapp.adapter.DBAdapter;
import com.bluecodesystems.mvpapp.contract.CasePlanContract;
import com.bluecodesystems.mvpapp.model.MyData;
import com.bluecodesystems.mvpapp.presenter.CasePlanPresenter;
import com.vijay.jsonwizard.activities.JsonWizardFormActivity;
import com.vijay.jsonwizard.constants.JsonFormConstants;
import com.vijay.jsonwizard.factory.FileSourceFactoryHelper;

import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.client.utils.domain.Form;

import java.util.ArrayList;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity implements CasePlanContract.View {

    private static final int REQUEST_CODE_GET_JSON = 1;
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private TextView emptyView;
    RecyclerView.Adapter recyclerViewadapter;
    ArrayList<MyData> dataList = new ArrayList<>();
    private CasePlanPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Views
        recyclerView = findViewById(R.id.recyclerView);
        emptyView = findViewById(R.id.empty);
        FloatingActionButton mBtnLaunch = findViewById(R.id.fab);

        //Initialize Presenter
        presenter = new CasePlanPresenter();
        initializeAdapter();

        //Logic for launching the form on Click
        mBtnLaunch.setOnClickListener(v -> {

            Intent intent = new Intent(this, JsonWizardFormActivity.class);

            JSONObject jsonForm = null;
            try {
                jsonForm = FileSourceFactoryHelper.getFileSource("").getFormFromFile(getApplicationContext(), "case_plan");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Timber.d("form is %s", jsonForm.toString());

            Form form = new Form();
            form.setName("VCA Case Plan Form");
            form.setWizard(true);
            form.setActionBarBackground(R.color.colorPrimary);
            form.setNavigationBackground(R.color.colorAccent);
            form.setHideSaveLabel(true);
            form.setNextLabel(getString(R.string.next));
            form.setPreviousLabel(getString(R.string.previous));
            form.setSaveLabel(getString(R.string.save));
            form.setBackIcon(R.drawable.ic_icon_positive);

            try {
                jsonForm.getJSONObject("step1").getJSONArray("fields").getJSONObject(2).put("value","Copperbelt");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            intent.putExtra(JsonFormConstants.JSON_FORM_KEY.FORM, form);
            intent.putExtra("json", jsonForm.toString());

            startActivityForResult(intent, REQUEST_CODE_GET_JSON);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_GET_JSON && resultCode == RESULT_OK) {

            presenter = new CasePlanPresenter();
            presenter.saveForm(this, data.getStringExtra("json"));

            //Refreshes the list
            initializeAdapter();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initializeAdapter() {

        //Load Data from Database
        dataList = presenter.fetchData(this);

        if (dataList.size() < 1) {
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }

        RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(eLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewadapter = new DBAdapter(dataList, MainActivity.this);
        recyclerView.setAdapter(recyclerViewadapter);
        recyclerViewadapter.notifyDataSetChanged();
    }
}