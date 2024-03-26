package com.example.ShopOnism.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ShopOnism.Domain.DetailCartDomain;
import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.Helper.ManagementCart;
import com.example.ShopOnism.Interface.ChangeNumberItemsListener;
import com.example.ShopOnism.R;

import java.util.ArrayList;

//user
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<ProductDomain> listproductSelected;
    ArrayList<DetailCartDomain> listDetailsCart;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<ProductDomain> listproductSelected, ArrayList<DetailCartDomain> listDetailsCart, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listproductSelected = listproductSelected;
        this.listDetailsCart = listDetailsCart;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductDomain productDomain = managementCart.getIDProduct(listDetailsCart.get(position).getId_product());

        holder.title.setText(productDomain.getTitle());
        holder.title.setText(productDomain.getTitle());
        holder.feeEachItem.setText(formatCurrency(productDomain.getFee()) + " đ"); // Hiển thị giá tiền VND
        holder.totalEachItem.setText(formatCurrency(listDetailsCart.get(position).getNum() * productDomain.getFee()) + " đ"); // Hiển thị giá tiền VND        holder.totalEachItem.setText(Math.round((listDetailsCart.get(position).getNum() * productDomain.getFee()))+ "đ");
        holder.num.setText(String.valueOf(listDetailsCart.get(position).getNum()));



        Glide.with(holder.itemView.getContext())
                .load(productDomain.getPic())
                .skipMemoryCache(true)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(v -> managementCart.plusNumberFood(listDetailsCart, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

        holder.minusItem.setOnClickListener(v -> managementCart.minusNumberFood(listDetailsCart, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

    }


    @Override
    public int getItemCount() {
        return listDetailsCart.size();
    }
    private String formatCurrency(double amount) {
        // Định dạng số tiền theo tiền tệ VND
        return String.format("%,.0f", amount);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title2Txt);
            pic = itemView.findViewById(R.id.picCard);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.addCardBtn);
            minusItem = itemView.findViewById(R.id.removeCardBtn);
            num = itemView.findViewById(R.id.numberItemTxt);

        }
    }
}
