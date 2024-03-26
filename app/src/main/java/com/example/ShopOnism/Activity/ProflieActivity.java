package com.example.ShopOnism.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;
//import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProflieActivity extends AppCompatActivity {

    DBHelper DB;
    String user;
    EditText inputName,inputAddress,inputPhone,inputGmail;
    TextView namepro;
    Button btnUpdate;
    ImageView imageView;
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proflie);
        imageView = findViewById(R.id.imageView7);
        DB = new DBHelper(this);
        initView();
        SessionManagement sessionManagement = new SessionManagement(ProflieActivity.this);
        user = sessionManagement.getSession();

        insetprofile();
        toolbar();
        UpdateProflie();
    }
    public static class Check_GmailUser{
        private static final String PHONE_PATTERN = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";
        Pattern pattern;
        public Check_GmailUser(){
            pattern = Pattern.compile(PHONE_PATTERN);
        }
        public boolean validate(final String hex){
            Matcher matcher = pattern.matcher(hex);
            return matcher.matches();
        }
    }

    private void UpdateProflie() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String password_ = password.getText().toString();
                Check_GmailUser check_gmailUser = new Check_GmailUser();
                String name_ = inputName.getText().toString();
                String gmail_ = inputGmail.getText().toString();
                String phone_ = inputPhone.getText().toString();
                String address_ = inputAddress.getText().toString();

                if (TextUtils.isEmpty(name_) || TextUtils.isEmpty(gmail_) ||TextUtils.isEmpty(phone_) || TextUtils.isEmpty(address_)) {
                    Toast.makeText(ProflieActivity.this, "Các trường không được bỏ trống !", Toast.LENGTH_SHORT).show();
                } else {

                    Boolean insert = DB.updateUserProfile(user, name_,gmail_, phone_, address_);
                    if (insert == true) {
                        insetprofile();
                        Toast.makeText(ProflieActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProflieActivity.this, "Cập nhật thất bại !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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

    public void initView(){
        namepro = findViewById(R.id.name_profile);
        inputName = findViewById(R.id.inputName);
        inputAddress = findViewById(R.id.inputAddress);
        inputPhone = findViewById(R.id.inputPhone);
        inputGmail = findViewById(R.id.inputGmail);
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    public void insetprofile() {
        UserDomain myuser = DB.getUser(user);
        String nameuser = myuser.getName();
        namepro.setText(nameuser);
        inputName.setText(myuser.getName());
        inputPhone.setText(myuser.getPhone());
        inputAddress.setText(myuser.getAddress());
        inputGmail.setText(myuser.getGmail());
    }

}