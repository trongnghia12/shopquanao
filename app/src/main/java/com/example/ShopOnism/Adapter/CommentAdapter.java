package com.example.ShopOnism.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ShopOnism.Domain.CommentDomain;

import com.example.ShopOnism.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Viewhoder> {

   ArrayList<CommentDomain> commentDomains;
   String myuser;

    public CommentAdapter(ArrayList<CommentDomain> commentDomains, String myuser) {
        this.commentDomains = commentDomains;
        this.myuser = myuser;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View i = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_comment,parent,false);
        return new Viewhoder(i);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        try{
            if(commentDomains.get(position).getUsername().equals(myuser)){
                holder.name.setText("me");
            }else{
                holder.name.setText(commentDomains.get(position).getUsername());
            }
            holder.cmt.setText((commentDomains.get(position).getContent() + ""));
        }catch (Exception e){
            System.out.println(e);
        }

    }


    @Override
    public int getItemCount() {

       return commentDomains.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder{
       TextView name,cmt,datetime;
       ImageView picPic;
       ConstraintLayout mainviewproduct;


        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_bot);
            cmt = itemView.findViewById(R.id.bot_reply2);
            datetime = itemView.findViewById(R.id.time);
            mainviewproduct = itemView.findViewById(R.id.mainviewproduct);

        }
    }



}
