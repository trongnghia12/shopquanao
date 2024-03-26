package com.example.ShopOnism.Activity.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ShopOnism.Adapter.CateAdminAdapter;
import com.example.ShopOnism.Domain.CategoryDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.Helper.ManagementCart;
import com.example.ShopOnism.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CateActivity extends AppCompatActivity {

    FloatingActionButton add;
    ListView recyclerView;
    EditText inputName,inputAddress,inputPhone;
    DBHelper DB;
    ManagementCart managementCart;
    ArrayList<CategoryDomain> cartDomains = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cateadmin);


        DB = new DBHelper(this);
        cartDomains = DB.getCategory();
        initView();
        toolbar();
        CateAdminAdapter historyAdapter = new CateAdminAdapter(CateActivity.this,cartDomains,DB);
        recyclerView.setAdapter(historyAdapter);
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CateActivity.this, CateUpdateActivity.class);
                intent.putExtra("id_cate", cartDomains.get(i).getId());
                startActivity(intent);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
        //         startActivity(new Intent(CateActivity.this, CateAddActivity.class));
                 Intent intent = new Intent(CateActivity.this,CateAddActivity.class);
                 startActivity(intent);
             }
         });
                historyAdapter.notifyDataSetChanged();
            }

    private void initView() {
        add = findViewById(R.id.btn_addCate);
        recyclerView = findViewById(R.id.Rcy_cate);
    }
    private void toolbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quản lý Danh mục");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

//    private void reload(){
//        Intent intent = getIntent();
//        overridePendingTransition(0,0);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        finish();
//        overridePendingTransition(0,0);
//        startActivity(intent);
//    }
}