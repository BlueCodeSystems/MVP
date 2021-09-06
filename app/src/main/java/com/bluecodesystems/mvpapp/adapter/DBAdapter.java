package com.bluecodesystems.mvpapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluecodesystems.mvpapp.R;
import com.bluecodesystems.mvpapp.model.MyData;

import java.util.List;

public class DBAdapter extends RecyclerView.Adapter<DBAdapter.ViewHolder> {

    Context context;

    List<MyData> data;

    private static final String TAG = "DBAdapter";

    public DBAdapter(List<MyData> data, Context context){

        super();

        this.data = data;
        this.context = context;
    }

    @Override
    public DBAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_data, parent, false);

        DBAdapter.ViewHolder viewHolder = new DBAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DBAdapter.ViewHolder holder, final int position) {

        final MyData mData = data.get(position);

        holder.textName.setText(mData.getFirstname() + " " + mData.getLastname());
        holder.textProvince.setText(mData.getProvince());


        holder.lview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {


                    case (R.id.itemm):


                        break;
                }
            }

        });

    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textName, textProvince;
        LinearLayout lview;

        public ViewHolder(View itemView) {

            super(itemView);


            textName  = (TextView) itemView.findViewById(R.id.name);
            textProvince  = (TextView) itemView.findViewById(R.id.province);
            lview = (LinearLayout) itemView.findViewById(R.id.itemm);

        }

        // Click event for all items
        @Override
        public void onClick(View v) {

        }
    }
}
