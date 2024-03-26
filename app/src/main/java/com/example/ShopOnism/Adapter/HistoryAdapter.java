package com.example.ShopOnism.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ShopOnism.Domain.CartDomain;
import com.example.ShopOnism.R;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HistoryAdapter extends BaseAdapter {

    private ArrayList<CartDomain> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public HistoryAdapter(Context aContext, ArrayList<CartDomain> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        int size = 0;
        try{
            size = listData.size();
        }catch (Exception  e){

        }
        return size;
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
            convertView = layoutInflater.inflate(R.layout.list_item_history, null);
            holder = new ViewHolder();
            holder.Bill_id = (TextView) convertView.findViewById(R.id.bill_id);
            holder.Bill_total = (TextView) convertView.findViewById(R.id.total_price_bill);
            holder.Bill_date = (TextView) convertView.findViewById(R.id.datebill);
            holder.Bill_status = (TextView) convertView.findViewById(R.id.status);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CartDomain bill = this.listData.get(position);
        holder.Bill_id.setText("Mã hóa đơn : " + bill.getId());
        holder.Bill_total.setText("Tổng giá : " + NumberFormat.getNumberInstance(Locale.US).format(bill.getTotal())+"VNĐ");
        Date temp_date = null;
        String Date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy, HH:mm", Locale.ENGLISH);
        SimpleDateFormat output = new SimpleDateFormat("EEE, dd MMM yyyy, HH:mm", new Locale("vi", "VN"));


        try{
            temp_date = sdf.parse(bill.getDate());
            Date = output.format(temp_date);
        }catch (Exception e){

        }
        holder.Bill_date.setText("Ngày : " + listData.get(position).getDate());
        String Status;
        if(bill.getType() == 1){
            Status = "Chờ Xác Nhận";
            holder.Bill_status.setText("Tình Trạng : " + Status );
        }else if(bill.getType() == 2){
            Status = "Đang Giao";
            holder.Bill_status.setText("Tình Trạng : " + Status );
        }else if(bill.getType() == 3){
            Status = "Đã Hoàn Thành";
            holder.Bill_status.setText("Tình Trạng : " + Status );
        }


        return convertView;
    }
    static class ViewHolder {
        TextView Bill_id,Bill_total,Bill_date, Bill_status;
    }
}