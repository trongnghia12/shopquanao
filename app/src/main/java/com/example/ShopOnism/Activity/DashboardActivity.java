package com.example.ShopOnism.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ShopOnism.Activity.User.HistoryActivity;
import com.example.ShopOnism.Activity.User.LoginActivity;
import com.example.ShopOnism.Domain.UserDomain;

import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;


public class DashboardActivity extends AppCompatActivity {

    DBHelper DB;
    String user;
    UserDomain myuser;
    ImageView imageView;
    TextView nameuserproflie,mailpro;
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        DB = new DBHelper(this);
        imageView = findViewById(R.id.imageView7);
        SessionManagement sessionManagement = new SessionManagement(DashboardActivity.this);
        user = sessionManagement.getSession();
        initView();
        myuser = DB.getUser(user);
        String nameuser = myuser.getName();
        String gamilsuser = myuser.getGmail();
        nameuserproflie.setText(nameuser);
        mailpro.setText(gamilsuser);
        settingProfile();
        toolbar();

    }
    private void initView() {
        nameuserproflie = findViewById(R.id.nameuserpro);
        mailpro = findViewById(R.id.gmailuser);
    }
    private void toolbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin cá nhân");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    private void settingProfile() {
        TextView linearHistoryCart = findViewById(R.id.linearHistory);
        TextView linearLogout = findViewById(R.id.linearlogout);
        TextView linearprofile = findViewById(R.id.linearprofile);
        //TextView linearchange = findViewById(R.id.linearchange);
        linearHistoryCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, HistoryActivity.class));
            }
        });

        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                SessionManagement sessionManagement = new SessionManagement(DashboardActivity.this);
                sessionManagement.removeSession();
                Toast.makeText(getApplicationContext(),"Đăng xuất thành công",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        linearprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentprofile = new Intent(DashboardActivity.this, ProflieActivity.class);
                startActivity(intentprofile);
            }
        });



    }

}