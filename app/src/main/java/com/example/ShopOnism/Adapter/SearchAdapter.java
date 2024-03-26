package com.example.ShopOnism.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Viewhoder> implements Filterable {

    ArrayList<ProductDomain> searchDomains;
    ArrayList<ProductDomain> searchDomainsOld;
    UserDomain myuser;


    public SearchAdapter(ArrayList<ProductDomain> searchDomains, UserDomain myuser) {
        this.searchDomains = searchDomains;
        this.searchDomainsOld = searchDomains;
        this.myuser = myuser;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewhoder_search,parent,false);
        return new Viewhoder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {

        holder.namesearch.setText(searchDomains.get(position).getTitle());
        Glide.with(holder.itemView.getContext())
                .load(searchDomains.get(position).getPic())
                .skipMemoryCache(true)
                .into(holder.pic_product);
        holder.mainsearch.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id_user",myuser.getId());
                bundle.putSerializable("object",searchDomains.get(position));
                intent.putExtras(bundle);
                holder.itemView.getContext().startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        if(searchDomains !=null){
            return searchDomains.size();
        }
        return 0;
    }

    public class Viewhoder extends RecyclerView.ViewHolder{
        TextView namesearch;
        ImageView btnclear,pic_product;
        ConstraintLayout mainsearch;


        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            namesearch = itemView.findViewById(R.id.item_search);
            btnclear = itemView.findViewById(R.id.clear);
            mainsearch = itemView.findViewById(R.id.main_search);
            pic_product = itemView.findViewById(R.id.pic_product);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    searchDomains = searchDomainsOld;
                }else{
                    ArrayList<ProductDomain> searchlist = new ArrayList<>();
                    for (ProductDomain searchDomain : searchDomainsOld){
                        if(searchDomain.getTitle().toLowerCase().contains(strSearch.toLowerCase())){
                            searchlist.add(searchDomain);
                        }
                    }
                    searchDomains = searchlist;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = searchDomains;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                searchDomains = (ArrayList<ProductDomain>) results.values;
                notifyDataSetChanged();

            }
        };
    }


}
