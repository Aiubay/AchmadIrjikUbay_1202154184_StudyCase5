package com.example.ubay.achmadirjikubay_1202154184_modul5;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

/**
 * Created by black on 3/25/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {
    private LayoutInflater minflanter;
    private List<com.example.ubay.achmadirjikubay_1202154184_modul5.List>mModel;
    private Context mContext;
    int id;
    DbHelper dbHelper;
    MainActivity main = new MainActivity();
    String warna;

    public Adapter(Context context,List<com.example.ubay.achmadirjikubay_1202154184_modul5.List> listData){
        minflanter = LayoutInflater.from(context);//inisiasi inflater
        this.mModel = listData;//arraylist
    }
    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = minflanter.inflate(R.layout.daftar_item, parent, false);
        //inflate class lainnya
        return new AdapterViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(Adapter.AdapterViewHolder holder, int position){
        com.example.ubay.achmadirjikubay_1202154184_modul5.List listModel = this.mModel.get(position);
        id=listModel.getID();
        holder.mTitle.setText(listModel.getName());
        holder.mDesc.setText(listModel.getDesc());
        holder.mPriority.setText(listModel.getPriority());
         //Glide.with(mContext).load();
        switch (warna){
            case"Merah":holder.bgColor.setBackgroundResource(R.color.colorAccent);break;
            case"Biru":holder.bgColor.setBackgroundResource(R.color.biru);break;
        }
    }

    @Override
    public int getItemCount() {
        return mModel.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        //        private TextView mID;
        private TextView mDesc;
        private TextView mPriority;
        public RelativeLayout bgColor;


        public AdapterViewHolder(View itemview) {
            super(itemview);
//            mID = (TextView) itemView.findViewById(R.id.IDtxt) ;
            mTitle = (TextView) itemView.findViewById(R.id.judul);
            mDesc = (TextView) itemView.findViewById(R.id.descripsi);
            mPriority = (TextView) itemView.findViewById(R.id.priorityTxt);
            bgColor = (RelativeLayout) itemview.findViewById(R.id.layout_background); //refrencing variable
            SharedPreferences Preference = PreferenceManager.getDefaultSharedPreferences(itemview.getContext()); //set shared preferences
            warna = Preference.getString("chosenColor","-1"); //set shared preferences ke variable warna



        }


    }
    
}
