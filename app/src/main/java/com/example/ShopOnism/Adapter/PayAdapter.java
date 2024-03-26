package com.example.ShopOnism.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ShopOnism.Domain.DetailCartDomain;
import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.ViewHolder>{
    ArrayList<ProductDomain> listProSelected;
    ArrayList<DetailCartDomain> listDetailsCart;
    private final DecimalFormat decimalFormat;

    public PayAdapter(ArrayList<ProductDomain> listProSelected, ArrayList<DetailCartDomain> listDetailsCart) {
        this.listProSelected = listProSelected;
        this.listDetailsCart = listDetailsCart;
        this.decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        decimalFormat.applyPattern("#,###,###,###");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pay, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(listProSelected.get(position).getTitle());
        holder.coin.setText(formatCurrency(listProSelected.get(position).getFee()));
        holder.total.setText(formatCurrency(Math.round((listDetailsCart.get(position).getNum() * listProSelected.get(position).getFee()))));
        holder.num.setText(String.valueOf(listDetailsCart.get(position).getNum()));
    }

    // Hàm chuyển đổi số tiền sang định dạng VND
    private String formatCurrency(double amount) {
        return decimalFormat.format(amount);
    }

    public int getItemCount() {
        return listProSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, num, coin, total;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleProductPay);
            num = itemView.findViewById(R.id.numberProductPay);
            coin = itemView.findViewById(R.id.coinProductPay);
            total = itemView.findViewById(R.id.totalEachItemProductPay);

        }
    }
}
