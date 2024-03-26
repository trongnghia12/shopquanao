package com.example.ShopOnism.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ShopOnism.Domain.CategoryDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;

import java.util.ArrayList;

public class CateAdminAdapter extends BaseAdapter {

    private ArrayList<CategoryDomain> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private DBHelper db;
    public CateAdminAdapter(Context aContext, ArrayList<CategoryDomain> listData, DBHelper db) {
        this.context = aContext;
        this.listData = listData;
        this.db = db;
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
            convertView = layoutInflater.inflate(R.layout.list_item_category, null);
            holder = new ViewHolder();
            holder.pic = (TextView) convertView.findViewById(R.id.pic);
            holder.title = (TextView) convertView.findViewById(R.id.titlecate);
            holder.button = (TextView) convertView.findViewById(R.id.btndelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CategoryDomain cmt = this.listData.get(position);
        holder.pic.setText("Pic : " + cmt.getPic());
        holder.title.setText("Nội dung : " + cmt.getTitle());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delectCate(cmt.getId());
                listData.remove(position);
                CateAdminAdapter.this.notifyDataSetChanged();
            }
        });
        return convertView;
    }
    static class ViewHolder {
        TextView title,pic;
        TextView button;
    }
}