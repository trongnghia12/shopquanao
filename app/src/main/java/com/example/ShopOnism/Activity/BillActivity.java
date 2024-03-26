package com.example.ShopOnism.Activity;
import java.text.DecimalFormat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ShopOnism.Adapter.PayAdapter;
import com.example.ShopOnism.Domain.CartDomain;
import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.Helper.ManagementCart;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BillActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewPay;
    private ManagementCart managementCart;
    DBHelper DB;
    String user;
    Integer id_User;
    View confirmOrder,orderPro,ReadyOrder;
    TextView date_Pay,num_CartPay,name_User, bigTotal,textViewgoHome;
    Button bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsbill);
        DB= new DBHelper(this);
        managementCart = new ManagementCart(this);
        anhxa();
        SessionManagement sessionManagement = new SessionManagement(BillActivity.this);
        user = sessionManagement.getSession();

        if(user != ""){
            UserDomain myuser = DB.getUser(user);
            id_User = myuser.getId();
            initPay(id_User);
            Intent a = getIntent();
            String id = a.getStringExtra("id_cart");
            int id_cartpay = Integer.parseInt(id);
            CartDomain mycartpay = managementCart.getCartToPay(id_cartpay);
            initView(myuser,mycartpay);
//            String date = gettodate();
//            date_Pay.setText("Ngày: " + date);
            switch(mycartpay.getType()){
                case 1:
                    confirmOrder.setBackgroundResource(R.drawable.shape_status_current);
                    orderPro.setBackgroundResource(R.drawable.shape_status_remaining);
                    ReadyOrder.setBackgroundResource(R.drawable.shape_status_remaining);
                    break;
                case 2:
                    confirmOrder.setBackgroundResource(R.drawable.shape_status_completed);
                    orderPro.setBackgroundResource(R.drawable.shape_status_current);
                    ReadyOrder.setBackgroundResource(R.drawable.shape_status_remaining);
                    break;
                case 3:
                    confirmOrder.setBackgroundResource(R.drawable.shape_status_completed);
                    orderPro.setBackgroundResource(R.drawable.shape_status_completed);
                    ReadyOrder.setBackgroundResource(R.drawable.shape_status_current);
                    break;

            }
        }

    }

    public void getuser(){
        Intent intent = getIntent();
        user = intent.getStringExtra("username");
    }

    public void anhxa(){
        recyclerViewPay = findViewById(R.id.viewPay);
        date_Pay = findViewById(R.id.datebill);
        num_CartPay = findViewById(R.id.num_CartPay);
        name_User = findViewById(R.id.name_User);
        bigTotal = findViewById(R.id.bigTotal);
        orderPro = findViewById(R.id.viewOrderPro);
        confirmOrder = findViewById(R.id.viewOrderConfirmed);
        ReadyOrder = findViewById(R.id.viewOrderReady);
    }
    public void initView(UserDomain myuser, CartDomain mycartpay){
        num_CartPay.setText("Số HD: " + mycartpay.getId());
        name_User.setText("Khách hàng: " + myuser.getName());
        // Sử dụng DecimalFormat để định dạng giá trị tiền tệ VND
        DecimalFormat decimalFormat = new DecimalFormat("#,### VNĐ");
        String totalFormatted = decimalFormat.format(mycartpay.getTotal());

        bigTotal.setText(totalFormatted);
    }

    public String gettodate(){
        DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }


    private void initPay(Integer id_User) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewPay.setLayoutManager(linearLayoutManager);
        Integer id_cart = managementCart.getCartPay(id_User);
        adapter = new PayAdapter(managementCart.getlistProductPay(id_User), managementCart.getListDetailsCart(id_cart));
        recyclerViewPay.setAdapter(adapter);
    }
    private void toolbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin đơn hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


}