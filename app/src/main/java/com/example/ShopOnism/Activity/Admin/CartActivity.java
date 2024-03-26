package com.example.ShopOnism.Activity.Admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ShopOnism.Adapter.HistoryAdminAdapter;
import com.example.ShopOnism.Domain.CartDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.Helper.ManagementCart;
import com.example.ShopOnism.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    Button btnupdate,btnview;
    ListView recyclerView;
    EditText inputName,inputAddress,inputPhone;
    DBHelper DB;
    ManagementCart managementCart;
    ArrayList<CartDomain> cartDomains = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartadmin);

        recyclerView = findViewById(R.id.Rcy_hítory);
        DB = new DBHelper(this);
        cartDomains = DB.getALL_Cart_Admin();
        HistoryAdminAdapter historyAdapter = new HistoryAdminAdapter(CartActivity.this,cartDomains,DB);
        recyclerView.setAdapter(historyAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}