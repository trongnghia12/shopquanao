package com.example.ShopOnism.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ShopOnism.Activity.DetailActivity;
import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewhoder> {

   ArrayList<ProductDomain> productDomains;
   UserDomain myuser;

    public PopularAdapter(ArrayList<ProductDomain> productDomains, UserDomain myuser) {
        this.productDomains = productDomains;
        this.myuser = myuser;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewhoder_popular,parent,false);
        return new Viewhoder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        holder.title.setText(productDomains.get(position).getTitle());
        holder.fee.setText(String.valueOf(productDomains.get(position).getFee()));
        double fee = productDomains.get(position).getFee();
        String feeVND = formatCurrency(fee);
        holder.fee.setText(feeVND);

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL(productDomains.get(position).getPic().trim());
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.connect();
            int resCode = httpConn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                holder.picPic.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        Glide.with(holder.itemView.getContext())
                .load(productDomains.get(position).getPic())
                .skipMemoryCache(true)
                .into(holder.picPic);

                holder.mainPopular.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id_user",myuser.getId());
                bundle.putSerializable("object",productDomains.get(position));
                intent.putExtras(bundle);

               holder.itemView.getContext().startActivity(intent);



        });
    }


    @Override
    public int getItemCount() {

       return productDomains.size();
    }
    private String formatCurrency(double amount) {
        // Tạo đối tượng NumberFormat với định dạng tiền tệ cho VND
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        format.setCurrency(Currency.getInstance("VND"));

        // Định dạng số tiền và trả về chuỗi đã định dạng
        return format.format(amount);
    }

    public class Viewhoder extends RecyclerView.ViewHolder{
       TextView title,fee;
       ImageView picPic;
       TextView addbtn;
       ConstraintLayout mainPopular;


        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            picPic = itemView.findViewById(R.id.pic);
            addbtn = itemView.findViewById(R.id.addbtn);
            mainPopular = itemView.findViewById(R.id.mainPopular);
        }
    }
}
