package com.example.ShopOnism.Activity.Admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ShopOnism.Activity.User.LoginActivity;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;

public class AdminActivity extends AppCompatActivity {

    TextView nameadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initView();
        toolbar();

        LinearLayout cate = findViewById(R.id.linecate);
        cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, CateActivity.class));
            }
        });
        LinearLayout addproduct = findViewById(R.id.linearUser);
        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, UserManagementActivity.class));
            }
        });
        LinearLayout adduser = findViewById(R.id.lineaaddproduct);
        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ProductManagementActivity.class));
            }
        });
        LinearLayout comment = findViewById(R.id.linecomment);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, CommentActivity.class));
            }
        });
        LinearLayout cart = findViewById(R.id.linearcart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, CartActivity.class));
            }
        });
        LinearLayout logout = findViewById(R.id.linearlogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("id_user",-1);
                SessionManagement sessionManagement = new SessionManagement(AdminActivity.this);
                sessionManagement.removeSession();
                Toast.makeText(getApplicationContext(),"Đăng xuất thành công",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }

    private void toolbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quản lý");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        nameadmin = findViewById(R.id.nameadmin);
    }
}