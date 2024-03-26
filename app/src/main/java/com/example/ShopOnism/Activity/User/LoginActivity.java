package com.example.ShopOnism.Activity.User;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ShopOnism.Activity.Admin.AdminActivity;
import com.example.ShopOnism.Activity.MainActivity;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    TextView signup,forgot,nexthome;
    TextInputEditText password;
    Button signin;
    DBHelper myDB;
    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initView();
//        Finger();

//        nexthome = (TextView) findViewById(R.id.nexthome);

        myDB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ne");
                String user = username.getText().toString();
                System.out.println(user +"user name");
                String pass = password.getText().toString();
               if(user.equals("") || pass.equals("") ) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean result = myDB.checkusernamepassword(user,pass);
                   System.out.println(result);
                    if(result == true){
                        Integer type = myDB.set_admin(user,pass);
                        if(type == 1){
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                            sessionManagement.saveSession(username.getText().toString());
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập Thành công", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                            SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                            sessionManagement.saveSession(username.getText().toString());
                            startActivity(intent1);
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "Không hơp lệ!",Toast.LENGTH_SHORT).show();

                    }

               }
            }
        });


    }


    private void initView() {
        username = (EditText) findViewById(R.id.inputName1);
        password = (TextInputEditText) findViewById(R.id.inputPassword1);

        signin = (Button) findViewById(R.id.btnLogin);
        signup = (TextView) findViewById(R.id.newAccount);
//        btnfinger = findViewById(R.id.btnfinger);
    }


}
