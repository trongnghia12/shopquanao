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

import com.example.ShopOnism.Activity.ProductActivity;
import com.example.ShopOnism.Domain.CategoryDomain;
import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewhoder> {

    ArrayList<CategoryDomain> categoryDomains;
    UserDomain myuser;


    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomains, UserDomain myuser) {
        this.categoryDomains = categoryDomains;
        this.myuser = myuser;
    }


    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewhoder_cat,parent,false);
        return new Viewhoder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        holder.categoryName.setText(categoryDomains.get(position).getTitle());

        holder.mainLayout.setOnClickListener(v -> {

                Intent intent = new Intent(holder.itemView.getContext(), ProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("check",true);
                bundle.putInt("id_user", myuser.getId());
                bundle.putString("category",categoryDomains.get(position).getTitle());
                bundle.putString("id",categoryDomains.get(position).getId());
                intent.putExtras(bundle);
                holder.itemView.getContext().startActivity(intent);

                holder.mainLayout.setBackgroundResource(R.drawable.background_rose);

        });

    }


    @Override
    public int getItemCount() {

       return categoryDomains.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
       TextView categoryName;
       ImageView categoryPic;
       ConstraintLayout mainLayout;


        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
//            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }


    }
}
