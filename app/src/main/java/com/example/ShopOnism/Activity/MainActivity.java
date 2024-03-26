package com.example.ShopOnism.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.ShopOnism.Activity.User.LoginActivity;
import com.example.ShopOnism.Adapter.CategoryAdapter;
import com.example.ShopOnism.Adapter.PopularAdapter;
import com.example.ShopOnism.Domain.CategoryDomain;
import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView.Adapter adapter,adapter2,adaptersale;
    RecyclerView recyclerViewCategoryList, recyclerViewPopularList,recyclerViewSaleList;
    ConstraintLayout mContraint;
    TextView recylerSale,recylerPopular;
    ViewFlipper viewFlipper;
    SearchView search;
    DBHelper DB;
    String user;
    UserDomain myuser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
         actionBar.hide();

        DB = new DBHelper(this);
        search = findViewById(R.id.search);
        initView();
        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        user = sessionManagement.getSession();
        if(user != ""){
            try{
                myuser = DB.getUser(user);
            }catch (Exception e){
                myuser = new UserDomain(0,"nam",null,null,null,null,0,null);

            }

            String nameuser =  myuser.getName();

        }else{
            myuser = new UserDomain(0,"nam",null,null,null,null,0,null);
        }
        recyclerViewCategory(myuser);
        recyclerViewPopular(myuser);

        search();
        bottomNavigation();
        ActionViewFlipper();
        next();



    }


    private void next() {
        recylerPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentproduct = new Intent(MainActivity.this, ProductActivity.class);
                intentproduct.putExtra("username", user);
                startActivity(intentproduct);
            }
        });
    }

    private void search() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentsearch = new Intent(MainActivity.this, SearchActivity.class);
                intentsearch.putExtra("username", user);
                startActivity(intentsearch);
            }
        });
    }
    private void initView() {
        search = findViewById(R.id.search);
        mContraint = findViewById(R.id.mContraint);
        recylerPopular = findViewById(R.id.reviewPopular);
        viewFlipper = findViewById(R.id.viewflipper);
    }

    private void recyclerViewCategory(UserDomain myuser) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        ArrayList<CategoryDomain> categoryList = DB.getCategory();
        adapter = new CategoryAdapter(categoryList,myuser);
        recyclerViewCategoryList.setAdapter(adapter);

    }

//    private void recyclerViewSaleProduct(UserDomain myuser) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewSaleList = findViewById(R.id.recyclerView3);
//        recyclerViewSaleList.setLayoutManager(linearLayoutManager);
//        ArrayList<ProductDomain> saletoplist = DB.getTopSale();
//        adaptersale = new SaleProductAdapter(saletoplist,myuser);
//        recyclerViewSaleList.setAdapter(adaptersale);
//
//    }

    private void recyclerViewPopular(UserDomain myuser) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<ProductDomain> productoptlist = DB.getTopProduct();
        adapter2 = new PopularAdapter(productoptlist,myuser);
        recyclerViewPopularList.setAdapter(adapter2);
    }



    ///Setting
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.item_account,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
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
//                startActivity(new Intent(MainActivity.this, CartListActivity.class));
                if(myuser.getId()==0){
                    Toast.makeText(MainActivity.this,"Vui lòng đăng nhập!",Toast.LENGTH_SHORT).show();
                    Intent intentlog = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intentlog);
                }else{
                    Intent intentcart = new Intent(MainActivity.this, CartListActivity.class);
                    intentcart.putExtra("username", user);
                    startActivity(intentcart);
                }

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,MainActivity.class));
                Intent intentprofile = new Intent(MainActivity.this, MainActivity.class);
                intentprofile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intentprofile.putExtra("username", user);
                startActivity(intentprofile);

            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                if(myuser.getId()==0) {
                    Toast.makeText(MainActivity.this, "Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
                    Intent intentlog = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intentlog);
                }else{
                    Intent intentprofile = new Intent(MainActivity.this,DashboardActivity.class);
                    intentprofile.putExtra("username",user);
                    startActivity(intentprofile);
                }

            }
        });
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                Intent intentsetting = new Intent(MainActivity.this, SettingActivity.class);
                intentsetting.putExtra("username", user);
                startActivity(intentsetting);
            }
        });
        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, AddProductActivity.class));
                Intent intentcontact = new Intent(MainActivity.this, ChatBoxActivity.class);
                intentcontact.putExtra("username", user);
                startActivity(intentcontact);
            }
        });
    }
    //sự kiện chạy quảng cáo
    private void ActionViewFlipper() {
        ArrayList<String> quangcao = new ArrayList<>();//tạo cái mảng quảng cáo (dạng chuỗi string)
        quangcao.add("https://instagram.fsgn5-12.fna.fbcdn.net/v/t51.2885-15/278365218_667473407839275_7045578089374973015_n.jpg?stp=dst-jpg_e35&efg=eyJ2ZW5jb2RlX3RhZyI6ImltYWdlX3VybGdlbi4xMTEzeDExMTMuc2RyIn0&_nc_ht=instagram.fsgn5-12.fna.fbcdn.net&_nc_cat=103&_nc_ohc=acVt-tBih-UAX92r0AD&edm=ABmJApABAAAA&ccb=7-5&ig_cache_key=MjgxNTUwNDAyNTIzMzkwMDQ5Ng%3D%3D.2-ccb7-5&oh=00_AfA8W5c2S9YmlLfhD6LkQmnzyHd-LaqvVfqkib90JQtbwA&oe=65A21225&_nc_sid=b41fef");//cấp phát vùng bộ nhớ
        quangcao.add("https://instagram.fsgn5-15.fna.fbcdn.net/v/t51.2885-15/278280595_672852067323287_2054653520429561240_n.jpg?stp=dst-jpg_e35&efg=eyJ2ZW5jb2RlX3RhZyI6ImltYWdlX3VybGdlbi4xMTMxeDExMzEuc2RyIn0&_nc_ht=instagram.fsgn5-15.fna.fbcdn.net&_nc_cat=111&_nc_ohc=0oFiMFoRQhwAX8fwkpf&edm=ABmJApABAAAA&ccb=7-5&ig_cache_key=MjgxNTUwMzY0ODc5NDk0MjI1Ng%3D%3D.2-ccb7-5&oh=00_AfB8VeapuBakPtInO6hRR5JS3htz9Pd4b6QvckeAdkS7Qg&oe=65A1B5A4&_nc_sid=b41fef");
        quangcao.add("https://instagram.fsgn5-2.fna.fbcdn.net/v/t51.2885-15/278227765_560128401968081_5113730391002495988_n.jpg?stp=dst-jpg_e35&efg=eyJ2ZW5jb2RlX3RhZyI6ImltYWdlX3VybGdlbi4xMTMxeDExMzEuc2RyIn0&_nc_ht=instagram.fsgn5-2.fna.fbcdn.net&_nc_cat=105&_nc_ohc=8CBwOM--GT8AX8lmI5A&edm=ABmJApABAAAA&ccb=7-5&ig_cache_key=MjgxNTUwMjU2MDgzNDQwMTYwNQ%3D%3D.2-ccb7-5&oh=00_AfAQy-9h8OvDxReFPkzzAiD66UY4OMAKCHMoZwOtMN-0ZA&oe=65A0EE91&_nc_sid=b41fef");


        for (int i = 0;i<quangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        Animation animation_chay_phaqua = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_phaiqua);
        Animation animation_chay_traiqua = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_quatrai);
        viewFlipper.setInAnimation(animation_chay_phaqua);
        viewFlipper.setInAnimation(animation_chay_traiqua);
    }


}