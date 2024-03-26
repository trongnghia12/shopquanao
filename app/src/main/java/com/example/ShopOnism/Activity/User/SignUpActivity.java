package com.example.ShopOnism.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {
    EditText username;
    TextView signin;
    TextInputEditText password, repassword;
    Button signup;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        myDB = new DBHelper(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        LOGIN();
    }
    private void LOGIN() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(SignUpActivity.this, "Điền đầy đủ các trường", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(repass)){
                        Boolean usercheckResult = myDB.checkusername(user);
                        if(usercheckResult == false){
                            Boolean regResult = myDB.insertData(user,pass);
                            if(regResult == true){
                                Toast.makeText(SignUpActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }else{
                                Toast.makeText(SignUpActivity.this, "Đăng ký thất bại",Toast.LENGTH_SHORT).show();

                            }
                        }else{
                            Toast.makeText(SignUpActivity.this,"Tên đã được sử dụng",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUpActivity.this,"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }

    private void initView() {
        username = (EditText) findViewById(R.id.inputName);
        password = (TextInputEditText) findViewById(R.id.inputPassword);
        repassword = (TextInputEditText) findViewById(R.id.inputRepassword);

        signup = (Button) findViewById(R.id.btnRegister);
        signin = (TextView) findViewById(R.id.signin);
    }


}