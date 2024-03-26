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

import com.example.ShopOnism.Domain.CategoryDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;

public class CateUpdateActivity extends AppCompatActivity {
    EditText title, pic;
    Button button;
    DBHelper DB;
    TextView catelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cate);


        initView();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DB = new DBHelper(this);
        Intent a = getIntent();
        String id = a.getStringExtra("id_cate");
        CategoryDomain categoryDomain = DB.getCategory(id);

        title.setText(categoryDomain.getTitle());
        pic.setText(categoryDomain.getPic());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.updateCate(id,title.getText().toString(),pic.getText().toString());
                Toast.makeText(CateUpdateActivity.this, "Cập nhật Thành Công!", Toast.LENGTH_SHORT).show();
            }
        });
        catelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CateUpdateActivity.this,CateActivity.class));
            }
        });
    }

    private void initView() {
        title = findViewById(R.id.inputTitleCate);
        pic = findViewById(R.id.inputPic);
        button = findViewById(R.id.btnAddCate);
        catelist =findViewById(R.id.catelist);
    }
}