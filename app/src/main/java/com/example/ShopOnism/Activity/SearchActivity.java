package com.example.ShopOnism.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ShopOnism.Adapter.SearchAdapter;
import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Helper.DBHelper;
import com.example.ShopOnism.R;
import com.example.ShopOnism.Session.SessionManagement;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    SearchView boxsearch;
    TextView emptyTxt;
    ScrollView scrollViewSearch;
    RecyclerView recyclerViewSearchList;
    SearchAdapter adaptersearch;
    DBHelper DB;
    UserDomain myuser;
    Integer id_user;
    String user;
    RecyclerView recyclerViewSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        DB = new DBHelper(this);
        SessionManagement sessionManagement = new SessionManagement(SearchActivity.this);
        user = sessionManagement.getSession();
        if(user != ""){
            myuser = DB.getUser(user);
           id_user = myuser.getId();
        }
        getUser();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        boxsearch = findViewById(R.id.box_search);
        boxsearch.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        boxsearch.setMaxWidth(Integer.MAX_VALUE);
        boxsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               adaptersearch.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                adaptersearch.getFilter().filter(newText);
                return false;
            }

        });

        recyclerViewSearch(myuser);
        toolbar();
    }
    private void toolbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tìm Kiếm");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    private void getUser() {
        Intent intent = getIntent();
        user = intent.getStringExtra("username");
    }
    private void recyclerViewSearch(UserDomain myuser) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewSearchList = findViewById(R.id.recyclerViewSearch);
        recyclerViewSearchList.setLayoutManager(linearLayoutManager);
        ArrayList<ProductDomain> searchlist = DB.getSearch();
        adaptersearch = new SearchAdapter(searchlist,myuser);
        recyclerViewSearchList.setAdapter(adaptersearch);
    }
}