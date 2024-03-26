package com.example.ShopOnism.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.ShopOnism.Adapter.UserManagementAdapter;
import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;

import java.util.ArrayList;

public class UserManagementActivity extends AppCompatActivity {

    RecyclerView recyclerViewUserList;
    RecyclerView.Adapter adapteruser;
    Button adduser,update;
    DBHelper DB;
    UserDomain myuser;


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        DB = new DBHelper(this);



        recyclerViewUser(myuser);
    }

    private void recyclerViewUser(UserDomain myuser) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewUserList = findViewById(R.id.recyclerViewAdminUser);
        recyclerViewUserList.setLayoutManager(linearLayoutManager);

        ArrayList<UserDomain> UserList = DB.getAllUser();
        adapteruser = new UserManagementAdapter(UserList,myuser);
        recyclerViewUserList.setAdapter(adapteruser);


    }






}