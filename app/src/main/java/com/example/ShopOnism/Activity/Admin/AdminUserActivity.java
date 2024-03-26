package com.example.ShopOnism.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;

public class AdminUserActivity extends AppCompatActivity {

    Button btnupdate,btnview;
    EditText inputName,inputAddress,inputPhone;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        initView();
    }
    public void initView(){

        inputName = findViewById(R.id.inputName);
        inputAddress = findViewById(R.id.inputAddress);
        inputPhone = findViewById(R.id.inputPhone);
        btnupdate = findViewById(R.id.btnUpdate);
        btnview = findViewById(R.id.btnView);
    }
}