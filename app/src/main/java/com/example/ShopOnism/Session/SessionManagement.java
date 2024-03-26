package com.example.ShopOnism.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(String customerID){
        //save session of user whenever user is logged in
        String id = customerID;
        editor.putString(SESSION_KEY,id).commit();
    }

    public String getSession(){
        //return user id whose session is saved
        return sharedPreferences.getString(SESSION_KEY,"");
    }

    public void removeSession(){
        editor.putString(SESSION_KEY,"-1").commit();
    }
}