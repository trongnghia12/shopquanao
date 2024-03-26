package com.example.ShopOnism.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordActivity extends AppCompatActivity {
    DBHelper DB;
    String user;
    EditText pass, repass,oldpass;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        DB = new DBHelper(this);
        SessionManagement sessionManagement = new SessionManagement(ChangePasswordActivity.this);
        user = sessionManagement.getSession();
        UserDomain myuser = DB.getUser(user);
        toolbar();
        pass = findViewById(R.id.Password);
        repass = findViewById(R.id.rePassword);
        oldpass = findViewById(R.id.OldPassword);
        isEmpty(pass);
        isEmpty(repass);
        isEmpty(oldpass);
        btnUpdate = findViewById(R.id.btnUpdate);
        oldpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                System.out.println(oldpass.getText().toString().trim());
                System.out.println(myuser.getPassword().trim());
               if(!oldpass.getText().toString().trim().equals(myuser.getPassword().trim())){
                   oldpass.setError("Mật khẩu không đúng");
                   btnUpdate.setEnabled(false);
               }else{
                   btnUpdate.setEnabled(true);
               }
            }
        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isValidPass(pass.getText().toString()) == false){
                    pass.setError("Mật khẩu hợp lệ");
                    btnUpdate.setEnabled(false);
                }else{
                    btnUpdate.setEnabled(true);
                }
            }
        });
        repass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
              if(!pass.equals(repass)){
                  repass.setError("Mật khẩu mới không khớp");
                  btnUpdate.setEnabled(false);
              }else{
                  btnUpdate.setEnabled(true);
              }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DB.ChangePasswordUser(myuser.getUsername(),pass.getText().toString());
            }
        });
    }
    private void toolbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Đổi mật khẩu");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    boolean isValidPass(CharSequence password) {
        String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    private boolean isEmpty(EditText etText) {
        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etText.getText().toString().trim().length() == 0) {
                    etText.setError("Không thể để trống");
                    etText.findFocus();
                    btnUpdate.setEnabled(false);
                    return;

                } else {
                    btnUpdate.setEnabled(true);
                }
            }
        });
        return true;
    }
}