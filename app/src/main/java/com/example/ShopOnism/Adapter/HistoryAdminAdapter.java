package com.example.ShopOnism.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ShopOnism.Domain.CartDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HistoryAdminAdapter extends BaseAdapter {

    private ArrayList<CartDomain> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private DBHelper db;

    public HistoryAdminAdapter(Context aContext, ArrayList<CartDomain> listData, DBHelper db) {
        this.context = aContext;
        this.listData = listData;
        this.db = db;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_historyadmin, null);
            holder = new ViewHolder();
            holder.Bill_id = convertView.findViewById(R.id.bill_id);
            holder.Bill_total = convertView.findViewById(R.id.total_price_bill);
            holder.Bill_date = convertView.findViewById(R.id.datebill);
            holder.Bill_status = convertView.findViewById(R.id.status);
            holder.button = convertView.findViewById(R.id.button);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CartDomain bill = this.listData.get(position);
        holder.Bill_id.setText("Mã hóa đơn : " + bill.getId());
        holder.Bill_total.setText("Tổng giá : " + NumberFormat.getNumberInstance(Locale.US).format(bill.getTotal()));

        String Status;
        switch (bill.getType()) {
            case 1:
                Status = "Đã xác nhận";
                holder.Bill_status.setText("Tình Trạng : " + Status);
                holder.button.setText("Đang Giao Hàng");
                holder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.updateDateCartType(bill.getId(), "2");
                        HistoryAdminAdapter.this.notifyDataSetChanged();
                        showToast("Đã xác nhận thành công");
                    }
                });
                break;
            case 2:
                Status = "Đang giao hàng";
                holder.Bill_status.setText("Tình Trạng : " + Status);
                holder.button.setText("Đã Giao Hàng");
                holder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.updateDateCartType(bill.getId(), "3");
                        HistoryAdminAdapter.this.notifyDataSetChanged();
                        showToast("Đã giao hàng thành công");
                    }
                });
                break;
            case 3:
                Status = "Đã Hoàn Thành";
                holder.Bill_status.setText("Tình Trạng : " + Status);
                holder.button.setVisibility(View.VISIBLE);
                break;
        }
        HistoryAdminAdapter.this.notifyDataSetChanged();

        return convertView;
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    static class ViewHolder {
        TextView Bill_id, Bill_total, Bill_date, Bill_status;
        Button button;
    }
}
