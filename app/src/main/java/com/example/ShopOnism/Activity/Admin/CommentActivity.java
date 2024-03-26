package com.example.ShopOnism.Activity.Admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ShopOnism.Adapter.CommentAdminAdapter;
import com.example.ShopOnism.Domain.CommentDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.Helper.ManagementCart;
import com.example.ShopOnism.R;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {

    Button btnupdate,btnview;
    ListView recyclerView;
    EditText inputName,inputAddress,inputPhone;
    DBHelper DB;
    ManagementCart managementCart;
    ArrayList<CommentDomain> cartDomains = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        recyclerView = findViewById(R.id.Rcy_hítory);
        DB = new DBHelper(this);
        CommentAdminAdapter historyAdapter = new CommentAdminAdapter(CommentActivity.this,cartDomains,DB);
        recyclerView.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();
    }
}