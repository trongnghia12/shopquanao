package com.example.ShopOnism.Activity.User;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ShopOnism.Activity.BillActivity;
import com.example.ShopOnism.Adapter.HistoryAdapter;
import com.example.ShopOnism.Domain.CartDomain;
import com.example.ShopOnism.Domain.DetailCartDomain;
import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.Helper.ManagementCart;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    DBHelper DB;
    String user;
    UserDomain myuser;
    ManagementCart managementCart;
    ListView recyclerView;
    ArrayList<CartDomain> cartDomains = new ArrayList<>();
    ArrayList<DetailCartDomain> listcart = new ArrayList<>();
    ArrayList<ProductDomain> productDomains = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.Rcy_hítory);
        DB = new DBHelper(this);
        SessionManagement sessionManagement = new SessionManagement(HistoryActivity.this);
        user = sessionManagement.getSession();
        myuser = DB.getUser(user);
        cartDomains = DB.getALL_Cart_Type_True(myuser.getId());
        for (CartDomain cartDomain : cartDomains
        ) {
            System.out.println(cartDomain.getId());
        }
        HistoryAdapter historyAdapter = new HistoryAdapter(HistoryActivity.this,cartDomains);
        recyclerView.setAdapter(historyAdapter);
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HistoryActivity.this, BillActivity.class);
                intent.putExtra("id_cart", cartDomains.get(i).getId().toString());
                startActivity(intent);
            }
        });
        toolbar();
    }
    private void toolbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lịch sử đơn hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


}