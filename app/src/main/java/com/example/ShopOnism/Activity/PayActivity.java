package com.example.ShopOnism.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ShopOnism.Helper.ManagementCart;
import com.example.ShopOnism.Adapter.PayAdapter;
import com.example.ShopOnism.Domain.CartDomain;
import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PayActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewPay;
    private ManagementCart managementCart;
    DBHelper DB;
    String user;
    Integer id_User;
    TextView date_Pay,num_CartPay,name_User, bigTotal,textViewgoHome;
    Button bill;
    private void toolbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin đơn hàng ");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    private String formatCurrency(double amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return format.format(amount);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        DB= new DBHelper(this);
        managementCart = new ManagementCart(this);
        anhxa();
        SessionManagement sessionManagement = new SessionManagement(PayActivity.this);
        user = sessionManagement.getSession();



        if(user != ""){
            UserDomain myuser = DB.getUser(user);
            id_User = myuser.getId();
            initPay(id_User);
            Integer id_cartpay = managementCart.getCartPay(id_User);
            CartDomain mycartpay = managementCart.getCartToPay(id_cartpay);
            initView(myuser,mycartpay);
            String date = gettodate();
            date_Pay.setText("Ngày: " + date);
            DB.updateDateCart(mycartpay.getId(),date);
            toolbar();
            double totalAmount = mycartpay.getTotal();
            bigTotal.setText(formatCurrency(totalAmount));
        }



    }

    public void getuser(){
        Intent intent = getIntent();
        user = intent.getStringExtra("username");
    }

    public void anhxa(){
        recyclerViewPay = findViewById(R.id.viewPay);
        date_Pay = findViewById(R.id.date_Pay);
        num_CartPay = findViewById(R.id.num_CartPay);
        name_User = findViewById(R.id.name_User);
        bigTotal = findViewById(R.id.bigTotal);


    }
    public void initView(UserDomain myuser, CartDomain mycartpay){
        num_CartPay.setText("Số HD: " + mycartpay.getId());
        name_User.setText("Khách hàng: " + myuser.getName());

        bigTotal.setText(String.valueOf(mycartpay.getTotal()));

    }

    public String gettodate(){
        DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy, HH:mm", Locale.US);
        String dateStr = df.format(Calendar.getInstance().getTime());

        // Chuyển đổi ngày tháng sang tiếng Việt
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy, HH:mm", new Locale("vi", "VN"));
        Date date;
        try {
            date = df.parse(dateStr);
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private void initPay(Integer id_User) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewPay.setLayoutManager(linearLayoutManager);
        Integer id_cart = managementCart.getCartPay(id_User);
        adapter = new PayAdapter(managementCart.getlistProductPay(id_User), managementCart.getListDetailsCart(id_cart));
        recyclerViewPay.setAdapter(adapter);
    }



}