package com.example.ShopOnism.Activity.Admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.TextView;


import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;

public class DetailUserActivity extends AppCompatActivity {
    DBHelper DB;
    String user;
    EditText inputName,inputAddress,inputPhone,inputGmail;
    TextView namepro;
    UserDomain object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);


        DB = new DBHelper(this);
        Intent intent_bundle = getIntent();
        Bundle bundle = intent_bundle.getExtras();
//        UserDomain myuser = DB.getUser(user);
//        String nameuser = myuser.getName();
//        namepro.setText(nameuser);
        initView();

        toolbar();
        getBundle(bundle);
    }

    private void getBundle(Bundle bundle) {
        object = (UserDomain) bundle.getSerializable("object");

        inputName.setText(object.getName());
        inputPhone.setText(object.getPhone());
        inputAddress.setText(object.getAddress());
        inputGmail.setText(object.getGmail());
        namepro.setText(object.getName());
    }


    private void toolbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Trang cá nhân");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void initView(){
        namepro = findViewById(R.id.name_profile);
        inputName = findViewById(R.id.inputName);
        inputAddress = findViewById(R.id.inputAddress);
        inputPhone = findViewById(R.id.inputPhone);
        inputGmail = findViewById(R.id.inputGmail);

    }

}