package com.example.ShopOnism.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ShopOnism.Domain.CommentDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;

import java.util.ArrayList;

public class CommentAdminAdapter extends BaseAdapter {

    private ArrayList<CommentDomain> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private DBHelper db;
    public CommentAdminAdapter(Context aContext, ArrayList<CommentDomain> listData, DBHelper db) {
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
            convertView = layoutInflater.inflate(R.layout.list_item_comment, null);
            holder = new ViewHolder();
            holder.user = (TextView) convertView.findViewById(R.id.user);
            holder.title = (TextView) convertView.findViewById(R.id.titlecomment);
            holder.button = (Button) convertView.findViewById(R.id.btndelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CommentDomain cmt = this.listData.get(position);
        holder.user.setText("User : " + cmt.getUsername());
        holder.title.setText("Nội dung : " + cmt.getContent());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delectComment(cmt.getId());
                listData.remove(position);
                CommentAdminAdapter.this.notifyDataSetChanged();
            }
        });
        return convertView;
    }
    static class ViewHolder {
        TextView user,title;
        Button button;
    }
}