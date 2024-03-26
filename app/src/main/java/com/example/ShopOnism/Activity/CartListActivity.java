package com.example.ShopOnism.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ShopOnism.Activity.User.LoginActivity;
import com.example.ShopOnism.Adapter.CartListAdapter;
//import com.example.giadung.Client.AppMoMoLib;
//import com.example.giadung.Client.MoMoParameterNamePayment;
import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.Helper.ManagementCart;
import com.example.ShopOnism.Interface.ChangeNumberItemsListener;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.Locale;


public class CartListActivity extends AppCompatActivity{

    RecyclerView.Adapter adapter;
    RecyclerView recyclerViewList;
    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt,check,momo;
    double tax;
    ScrollView scrollView;
    DBHelper DB;
    String user;
    Integer id_User;
    ManagementCart managementCart;
    UserDomain myuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
//        AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);
        DB = new DBHelper(this);
        managementCart = new ManagementCart(this);
        SessionManagement sessionManagement = new SessionManagement(CartListActivity.this);
        user = sessionManagement.getSession();
        ckeckuser(user);
        myuser = DB.getUser(user);
        id_User = myuser.getId();
        initView() ;
        run(id_User);
        bottomNavigation();
        checkorder();
        toolbar();

    }
    private void toolbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Giỏ hàng ");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    private void checkorder() {
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean CheckToPay = managementCart.updateCart(id_User);
                if(CheckToPay == true){
                    Toast.makeText(CartListActivity.this, "Đặt hàng thành công. Vui lòng đợi xác nhận!", Toast.LENGTH_SHORT).show();
                    Intent intentPay = new Intent(CartListActivity.this, PayActivity.class);
                    intentPay.putExtra("username", user);
                    startActivity(intentPay);
                    finish();
                }else {
                    Toast.makeText(CartListActivity.this, "Đặt hàng thất bại! Kiểm tra lại.", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(CartListActivity.this, "Success Order", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
    }

    public void getUser(){
        Intent intent = getIntent();
        user = intent.getStringExtra("username");
    }
    private void ckeckuser(String user_){
        if(user_ == null){
            Toast.makeText(CartListActivity.this, "Vui lòng đăng nhập !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cart_btn);
        LinearLayout homeBtn = findViewById(R.id.homebtn);
        LinearLayout profileBtn = findViewById(R.id.profilebtn);
        LinearLayout settingbtn = findViewById(R.id.settingbtn);
        LinearLayout chatbtn = findViewById(R.id.chatbtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
                Intent intentcart = new Intent(CartListActivity.this, CartListActivity.class);
                intentcart.putExtra("username", user);
                startActivity(intentcart);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(CartListActivity.this, MainActivity.class));
                Intent intentprofile = new Intent(CartListActivity.this, MainActivity.class);
                intentprofile.putExtra("username", user);
                startActivity(intentprofile);
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                Intent intentprofile = new Intent(CartListActivity.this,DashboardActivity.class);
                intentprofile.putExtra("username",user);
                startActivity(intentprofile);
            }
        });
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                Intent intentsetting = new Intent(CartListActivity.this, SettingActivity.class);
                intentsetting.putExtra("username", user);
                startActivity(intentsetting);
            }
        });
        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, AddProductActivity.class));
                Intent intentcontact = new Intent(CartListActivity.this, ContactActivity.class);
                intentcontact.putExtra("username", user);
                startActivity(intentcontact);
            }
        });
    }

    public void run(Integer id_User){
        Boolean CheckCart = DB.checkCart(id_User);
        if(CheckCart == true){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            initList(id_User);
            calculateCard(id_User);
        }

    }

    private void initList(Integer id_User) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        Integer id_cart = managementCart.getCart(myuser.getId());
        adapter = new CartListAdapter(managementCart.getlistProduct(id_User), managementCart.getListDetailsCart(id_cart), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() { calculateCard(id_User); }
        });


        if (managementCart.getlistProduct(myuser.getId()).isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

        recyclerViewList.setAdapter(adapter);
    }


    private void calculateCard(Integer id_User) {
        double percentTax = 0.02;
        double delivery = 10;
        double total = Math.round((managementCart.getTotalFee(id_User) + tax + delivery) * 100.0) / 100.0;
        Integer itemTotal = Math.round(managementCart.getTotalFee(id_User));

        // Định dạng số tiền theo tiền tệ Việt Nam
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String itemTotalFormatted = currencyFormat.format(itemTotal);

        totalFeeTxt.setText(itemTotalFormatted); // Hiển thị số tiền định dạng tiền tệ Việt Nam
        totalTxt.setText(itemTotalFormatted); // Hiển thị số tiền định dạng tiền tệ Việt Nam
    }
    private void initView() {

        recyclerViewList = findViewById(R.id.recyclerView3);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView4);
        check = findViewById(R.id.Checkout);
        momo = findViewById(R.id.checkoutmomo);
    }

}