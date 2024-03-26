package com.example.ShopOnism.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ShopOnism.Adapter.CategoryAdapter;
import com.example.ShopOnism.Adapter.ProductAdapter;
import com.example.ShopOnism.Domain.CategoryDomain;
import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    RecyclerView.Adapter adapter,adapter2,adapter3;
    RecyclerView recyclerViewCategoryList, recyclerViewPopularList,recyclerViewPopularCategoryList,recyclerViewSaleList;
    ConstraintLayout ContraintCategory;
    SearchView search;
    DBHelper DB;
    String user;
    Integer id_user;
    TextView name_category;
    UserDomain myuser,myuserbundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        DB = new DBHelper(this);
        SessionManagement sessionManagement = new SessionManagement(ProductActivity.this);
        user = sessionManagement.getSession();
        initView();
        search();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent intent_bundle = getIntent();
        Bundle bundle = intent_bundle.getExtras();
        if(user != ""){
            myuser = DB.getUser(user);
            get_Bundle(myuser,bundle);
        }else {
            getBigBundle(bundle);
        }


        bottomNavigation();
        recyclerViewCategory(myuser);



    }
    private void getBigBundle(Bundle bundle) {
        if(bundle != null){
            id_user = bundle.getInt("id_user",0);
            if (id_user.equals(0)){
                myuser = new UserDomain(0,"nam",null,null,null,null,0,null);
                get_Bundle(myuser,bundle);
            }else{
                myuserbundle = DB.getUserID(id_user);
                user = myuserbundle.getUsername();
                get_Bundle(myuserbundle,bundle);
            }
        }
    }

    private void get_Bundle(UserDomain myuser, Bundle bundle) {
        if (bundle != null){
            Boolean check = bundle.getBoolean("check", false);
            if(check == true){
                String id_category = bundle.getString("id",null);
                String nameCategory = bundle.getString("category",null);
                name_category.setText("Thể Loại: " + nameCategory);
                recyclerViewProductCategory(id_category,myuser);

//
            }else{

                recyclerViewPopular(myuser);
            }
        }
    }

    private void search() {
        //search
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(ProductActivity.this, SearchActivity.class));
                Intent intentsearch = new Intent(ProductActivity.this, SearchActivity.class);
                intentsearch.putExtra("username", user);
                startActivity(intentsearch);
            }
        });
    }


    private void initView() {
        search =  findViewById(R.id.search);
        name_category = findViewById(R.id.nameofcategory);
        ContraintCategory = findViewById(R.id.ContraintProduct);
    }

    private void getUser() {
        Intent intent = getIntent();
        user = intent.getStringExtra("username");
    }


    private void recyclerViewCategory(UserDomain myuser) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = DB.getCategory();

        adapter = new CategoryAdapter(categoryList,myuser);
        recyclerViewCategoryList.setAdapter(adapter);


    }

    private void recyclerViewProductCategory(String ID_category, UserDomain myuser) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewPopularCategoryList = findViewById(R.id.recyclerViewproductcategory);
        recyclerViewPopularCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<ProductDomain> produccategorytlist = DB.getProductCategory(ID_category);

        adapter3 = new ProductAdapter(produccategorytlist,myuser);
        recyclerViewPopularCategoryList.setAdapter(adapter3);
    }

    private void recyclerViewPopular(UserDomain myuser) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerViewproductcategory);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<ProductDomain> productlist = DB.getProduct();

        adapter2 = new ProductAdapter(productlist,myuser);
        recyclerViewPopularList.setAdapter(adapter2);
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
//                startActivity(new Intent(ProductActivity.this, CartListActivity.class));
                Intent intentcart = new Intent(ProductActivity.this, CartListActivity.class);
                intentcart.putExtra("username", user);
                startActivity(intentcart);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ProductActivity.this,MainActivity.class));
                Intent intentprofile = new Intent(ProductActivity.this, MainActivity.class);
                intentprofile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intentprofile.putExtra("username", user);
                startActivity(intentprofile);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                Intent intentprofile = new Intent(ProductActivity.this,DashboardActivity.class);
                intentprofile.putExtra("username",user);
                startActivity(intentprofile);
            }
        });
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ProductActivity.this,SettingActivity.class));
                Intent intentsetting = new Intent(ProductActivity.this, SettingActivity.class);
                intentsetting.putExtra("username", user);
                startActivity(intentsetting);
            }
        });
        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ProductActivity.this, AddProductActivity.class));
                Intent intentcontact = new Intent(ProductActivity.this, ChatBoxActivity.class);
                intentcontact.putExtra("username", user);
                startActivity(intentcontact);
            }
        });
    }
}