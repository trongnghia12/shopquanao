package com.example.ShopOnism.Activity.Admin;

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

public class CateAddActivity extends AppCompatActivity {
    EditText title, pic,id;
    Button button;
    DBHelper DB;
    TextView catelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cate);


        initView();
        DB = new DBHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.insertCate(id.getText().toString(),title.getText().toString(),pic.getText().toString());
                Toast.makeText(CateAddActivity.this, "Thêm danh mục Thành Công!", Toast.LENGTH_SHORT).show();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        catelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CateAddActivity.this,CateActivity.class));
            }
        });

    }

    private void initView() {
        id = findViewById(R.id.idCate);
        title = findViewById(R.id.inputTitleCate);
        pic = findViewById(R.id.inputPic);
        button = findViewById(R.id.btnAddCate);
        catelist = findViewById(R.id.catelist);
    }



//    public String XuLyLink(String link){
//        if(link.contains("https://docs.google.com/uc?id=") == true){
//            return link;
//        }else {
//            String getmylink = "https://docs.google.com/uc?id=";
//
//            String[] splits = link.split("https://drive.google.com/file/d/");
//            String splits_1 = splits[1];
//            String[] splits1 = splits_1.split("/");
//            String splits_2 = splits1[0];
//            String re = getmylink.concat(splits_2);
//            return re;
//        }
//    }
}