package com.example.ShopOnism.Activity.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ShopOnism.Adapter.ProductManagementAdapter;
import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.Helper.ManagementAdmin;
import com.example.ShopOnism.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductManagementActivity extends AppCompatActivity {

    RecyclerView recyclerViewProList;
    RecyclerView.Adapter adapterpro;
    FloatingActionButton add;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_product);

        DB = new DBHelper(this);

        Intent intent_bundle = getIntent();
        Bundle bundle = intent_bundle.getExtras();
        bottomNavigation();


        recyclerViewPro();
        toolbar();
    }
    private void toolbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quản lý Sản Phẩm");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void bottomNavigation() {
        FloatingActionButton add = findViewById(R.id.product_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ProductManagementActivity.this ,AddProductActivity.class));
                Intent intent = new Intent(ProductManagementActivity.this,AddProductActivity.class);
                startActivity(intent);
            }
        });
    }

    private void recyclerViewPro() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewProList = findViewById(R.id.recyclerViewAdminUser);
        recyclerViewProList.setLayoutManager(linearLayoutManager);

        ArrayList<ProductDomain> prolist = DB.getProduct();

        adapterpro = new ProductManagementAdapter(prolist, this, new ManagementAdmin.ChangeItemsListener() {
            @Override
            public void changed() {
                reload();
            }
        });
        recyclerViewProList.setAdapter(adapterpro);


    }

    private void reload(){
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(intent);
    }


}