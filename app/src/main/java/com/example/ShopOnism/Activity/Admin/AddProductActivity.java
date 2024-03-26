package com.example.ShopOnism.Activity.Admin;
import java.text.NumberFormat;
import java.util.Locale;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;

public class AddProductActivity extends AppCompatActivity {


    EditText name,price,description,id_catepro,numPro,link;
    Button addPro,chooseImage;
    TextView productlist;
    ImageView imageView;
    ProductDomain object;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        findid();
        DB = new DBHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        click();


    }

    private void click() {
        addPro.setOnClickListener(v -> {
            String title_ = name.getText().toString();
            String fee_ = price.getText().toString();
            String description_ = description.getText().toString();
            String id_category_ = id_catepro.getText().toString();
            String num_ = numPro.getText().toString();
            String link_ = link.getText().toString();

            if (TextUtils.isEmpty(title_) || TextUtils.isEmpty(link_) || TextUtils.isEmpty(description_) || TextUtils.isEmpty(fee_) || TextUtils.isEmpty(id_category_) || TextUtils.isEmpty(num_)) {
                Toast.makeText(AddProductActivity.this, "Các trường không được bỏ trống !", Toast.LENGTH_SHORT).show();
            } else {
                // Định dạng giá tiền theo tiền tệ Việt Nam
                double feeValue = Double.parseDouble(fee_);
                Locale localeVN = new Locale("vi", "VN");
                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                String formattedFee = currencyVN.format(feeValue);

                // Thêm sản phẩm với giá tiền đã định dạng
                Boolean checkupdate = DB.insertProduct(title_, XuLyLink(link_), description_, formattedFee, id_category_, Integer.valueOf(num_));
                if (checkupdate) {
                    Toast.makeText(AddProductActivity.this, "Thêm sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intentuser = new Intent(AddProductActivity.this, ProductManagementActivity.class);
                    intentuser.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentuser);
                    finish();
                } else {
                    Toast.makeText(AddProductActivity.this, "Thêm sản phẩm thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        productlist.setOnClickListener(v -> {
            startActivity(new Intent(AddProductActivity.this, ProductManagementActivity.class));
        });
    }




    private void findid(){
        name = findViewById(R.id.inputName);
        price = findViewById(R.id.inputPrice);
        description =  findViewById(R.id.inputDescrip);
        id_catepro = findViewById(R.id.inputCategory);
        numPro = findViewById(R.id.inputNum);
        link = findViewById(R.id.inputLink);

        imageView = findViewById(R.id.imagePro);
        productlist = findViewById(R.id.productlist);
        addPro = findViewById(R.id.btnAddPro);

    }

    public String XuLyLink(String link){
        if(link.contains("https://docs.google.com/uc?id=") == true){
            return link;
        }else {
            String getmylink = "https://docs.google.com/uc?id=";

            String[] splits = link.split("https://drive.google.com/file/d/");
            String splits_1 = splits[1];
            String[] splits1 = splits_1.split("/");
            String splits_2 = splits1[0];
            String re = getmylink.concat(splits_2);
            return re;
        }
    }






}