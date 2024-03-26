package com.example.ShopOnism.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ShopOnism.Activity.Admin.DetailUserActivity;
import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;

import java.util.ArrayList;

public class UserManagementAdapter extends RecyclerView.Adapter<UserManagementAdapter.Viewhoder> {

   ArrayList<UserDomain> adminDomains;
   UserDomain myuser;
   DBHelper DB;

    public UserManagementAdapter(ArrayList<UserDomain> adminDomains,UserDomain myuser) {
        this.adminDomains = adminDomains;
        this.myuser = myuser;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View i = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewhoder_show_user,parent,false);
        return new Viewhoder(i);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        holder.txtuserid.setText(String.valueOf(adminDomains.get(position).getId()));
        holder.txtusername.setText(adminDomains.get(position).getName());
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailUserActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("object",adminDomains.get(position));
                intent.putExtras(bundle);

                holder.itemView.getContext().startActivity(intent);
            }
        });

 }


    @Override
    public int getItemCount() {

       return adminDomains.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder{
       TextView txtuserid,txtusername,txtaddress,txtphone;
       ImageView clear;
       ConstraintLayout detail;


        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            txtuserid = itemView.findViewById(R.id.txtuserid);
            txtusername = itemView.findViewById(R.id.txtusername);
            clear = itemView.findViewById(R.id.clear);
            detail = itemView.findViewById(R.id.detailuser);

        }
    }
}
