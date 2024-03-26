package com.example.ShopOnism.Activity.Admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;


public class UpdateProductActivity extends AppCompatActivity {

    ImageView imagepro;
    TextView listreview;
    EditText inputName,inputFee,inputDecrip,inputCate,inputNum,inputLink,link;
    Button update,choseimage;
    ProductDomain object;
//    int numberOrder = 50;
    DBHelper DB;
    private Integer id_product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        DB= new DBHelper(this);
        initView();

        Intent intent = getIntent();
        Integer id_Product = intent.getIntExtra("id_product",0);

        object = DB.getProductById(id_Product);

        getView();
        clickUpdate(id_Product);
        click();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }


    private void click() {
        listreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateProductActivity.this,ProductManagementActivity.class));
            }
        });
//        choseimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Glide.with(UpdateProductActivity.this)
//                        .load(object.getPic())
//                        .into(imagepro);
//
//            }
//
//        });
    }

    private void initView() {
        inputName = findViewById(R.id.inputName);
        inputFee = findViewById(R.id.inputPrice);
        inputDecrip = findViewById(R.id.inputDescrip);
        inputCate = findViewById(R.id.inputCategory);
        inputNum = findViewById(R.id.inputNum);
        inputLink = findViewById(R.id.inputLink);
        imagepro = findViewById(R.id.imagePro);
        update = findViewById(R.id.btnupdatePro);
        listreview = findViewById(R.id.productlist);
        link = findViewById(R.id.inputLink);
//        choseimage = findViewById(R.id.btnchoosePro);


    }
    public void getView( ) {
//        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(object.getPic())
                .into(imagepro);

        inputName.setText(object.getTitle());
        inputFee.setText(object.getFee()+"đ");
        inputDecrip.setText(object.getDescription());
        inputCate.setText(object.getId_category());
        inputNum.setText(String.valueOf(object.getNum()));
        inputLink.setText(object.getPic());
    }

    public void clickUpdate(Integer id_product){
        update.setOnClickListener(v -> {
            String title_ = inputName.getText().toString();
            String fee_ = inputFee.getText().toString();
            String description_ = inputDecrip.getText().toString();
            String id_category_ = inputCate.getText().toString();
            String num_ = inputNum.getText().toString();
            String link_ = link.getText().toString();
            if (TextUtils.isEmpty(title_) ||TextUtils.isEmpty(link_) || TextUtils.isEmpty(fee_) || TextUtils.isEmpty(description_) || TextUtils.isEmpty(id_category_) || TextUtils.isEmpty(num_)) {
                Toast.makeText(UpdateProductActivity.this, "Các trường không được bỏ trống !", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkupdate = DB.updateProduct(id_product, title_,link_, description_,fee_,id_category_, Integer.valueOf(num_));
                if (checkupdate == true) {
                    Toast.makeText(UpdateProductActivity.this, "Cập nhật Thành Công!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(UpdateProductActivity.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//    private void toolbar(){
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Chi Tiết");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}