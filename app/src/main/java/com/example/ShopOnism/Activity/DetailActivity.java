package com.example.ShopOnism.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ShopOnism.Activity.User.LoginActivity;
import com.example.ShopOnism.Domain.ProductDomain;

import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.Helper.ManagementCart;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    LinearLayout addToCardtn;
    RecyclerView.Adapter commentAdapter;
    RecyclerView recyclerViewcommentList;
    TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, nocmtTxt;
    EditText titleComment;
    ImageView addBtn, removeBtn, picProduct, naviback,btn_cmt;
    ProductDomain object;
    int numberOrder = 1;
    ManagementCart managementCart;
    DBHelper DB;
    Integer id_user;
    UserDomain myuser;
    String user;
    int product_id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DB = new DBHelper(this);
        managementCart = new ManagementCart(this);
        DB = new DBHelper(this);
        initView();
        SessionManagement sessionManagement = new SessionManagement(DetailActivity.this);
        user = sessionManagement.getSession();
        UserDomain  myuser = DB.getUser(user);
        Intent intent_bundle = getIntent();
        Bundle bundle = intent_bundle.getExtras();
        getBundle(myuser.getId(), bundle);
        toolbar();

    }

    private void toolbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chi tiết sản phẩm");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void getBigBundle(Bundle bundle) {
        if (bundle != null) {

        }
    }

    private void initView() {
        addToCardtn = findViewById(R.id.addToCardtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        addBtn = findViewById(R.id.addBtn);
        removeBtn = findViewById(R.id.removeBtn);
        picProduct = findViewById(R.id.productPic);
        nocmtTxt = findViewById(R.id.nocmtTxt);
    }
    private String formatCurrency(double amount) {
        // Tạo đối tượng NumberFormat với định dạng tiền tệ cho VND
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        format.setCurrency(Currency.getInstance("VND"));

        // Định dạng số tiền và trả về chuỗi đã định dạng
        return format.format(amount);
    }

    private void getBundle(Integer id_user, Bundle bundle) {
        object = (ProductDomain) bundle.getSerializable("object");
        product_id = object.getId();
//        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(object.getPic())
                .into(picProduct);
        titleTxt.setText(object.getTitle());
        feeTxt.setText(formatCurrency(object.getFee()) );
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCardtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (object.getNum() < numberOrder) {
                    Toast.makeText(DetailActivity.this, "Số lương không đủ !", Toast.LENGTH_SHORT).show();
                } else {

                    if (id_user == 0) {
                        Toast.makeText(DetailActivity.this, "Vui lòng đăng nhập !", Toast.LENGTH_SHORT).show();
                        Intent intentlog = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intentlog);
                    } else {
                        managementCart.insertCart(object, id_user, numberOrder);
                    }
                }

            }
        });
    }



}