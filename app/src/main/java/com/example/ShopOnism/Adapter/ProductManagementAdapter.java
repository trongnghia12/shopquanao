package com.example.ShopOnism.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.ShopOnism.Activity.Admin.UpdateProductActivity;
import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.Helper.ManagementAdmin;
import com.example.ShopOnism.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ProductManagementAdapter extends RecyclerView.Adapter<ProductManagementAdapter.Viewhoder> {

   ArrayList<ProductDomain> adminpro;
   ManagementAdmin managementAdmin;
   ManagementAdmin.ChangeItemsListener changeItemsListener;

    public ProductManagementAdapter(ArrayList<ProductDomain> adminpro, Context context, ManagementAdmin.ChangeItemsListener changeItemsListener) {
        this.adminpro = adminpro;
        managementAdmin =  new ManagementAdmin(context);
        this.changeItemsListener = changeItemsListener;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View i = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewhoder_product_management,parent,false);
        return new Viewhoder(i);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        holder.title.setText(adminpro.get(position).getTitle());
        holder.fee.setText(String.valueOf(adminpro.get(position).getFee()));
        holder.pcs.setText(String.valueOf(adminpro.get(position).getNum()));
        double feeValue = adminpro.get(position).getFee();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String formattedFee = currencyVN.format(feeValue);

        // Hiển thị giá tiền đã được định dạng
        holder.fee.setText(formattedFee);

        Glide.with(holder.itemView.getContext())
                .load(adminpro.get(position).getPic())
                .skipMemoryCache(true)
                .into(holder.picPic);


        holder.update.setOnClickListener(v -> {

            Intent intent = new Intent(holder.itemView.getContext(),UpdateProductActivity.class);
            intent.putExtra("id_product",adminpro.get(position).getId());
            holder.itemView.getContext().startActivity(intent);

        });

        holder.delete.setOnClickListener(v -> managementAdmin.delectSP(adminpro, position, () -> {
            notifyDataSetChanged();
            changeItemsListener .changed();
        }));


    }


    @Override
    public int getItemCount() {

       return adminpro.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder{
       TextView title,fee,pcs;
       ImageView picPic;
       TextView delete,update;
       ConstraintLayout mainviewproduct;


        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title2Txt);
            fee = itemView.findViewById(R.id.feeEachItem);
            pcs = itemView.findViewById(R.id.pcs);
            picPic = itemView.findViewById(R.id.picCard);
            delete = itemView.findViewById(R.id.deletebtn);
            update = itemView.findViewById(R.id.updatebtn);
            mainviewproduct = itemView.findViewById(R.id.mainviewproduct);

        }
    }



}
