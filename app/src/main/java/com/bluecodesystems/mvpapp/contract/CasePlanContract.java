package com.bluecodesystems.mvpapp.contract;

import android.content.Context;

import com.bluecodesystems.mvpapp.model.MyData;

import java.util.ArrayList;

public interface CasePlanContract {

    interface View {
        void initializeAdapter();
    }

    interface Presenter {

        void saveForm(Context context, String json);

        ArrayList<MyData> fetchData(Context context);

    }

}
