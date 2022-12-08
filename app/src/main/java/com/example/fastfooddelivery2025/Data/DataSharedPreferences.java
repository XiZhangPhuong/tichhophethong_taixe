package com.example.fastfooddelivery2025.Data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fastfooddelivery2025.Model.Staff;
import com.google.gson.Gson;

public class DataSharedPreferences {
    private static final String MY_KEY = "MY_KEY";

/*    public static void setList(Context context, List<Notification> list, String Key){
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);

        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key, jsonString);
        editor.apply();
    }
    public static List<Notification> getList(Context context,String Key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_KEY, Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString(Key, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Notification>>(){}.getType();
        List<Notification> list = gson.fromJson(jsonString, type);
        return list;
    }*/
    public static void setUser(Context context, Staff user, String KEY){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(KEY,json);
        editor.apply();
    }
    public static  Staff getUser(Context context, String KEY){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_KEY, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(KEY,"");
        Gson gson = new Gson();
        Staff user = gson.fromJson(json,Staff.class);
        return user;
    }
}
