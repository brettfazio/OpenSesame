package com.example.juleeyahwright.opensesame;

import android.app.Activity;
import android.content.SharedPreferences;

public class SharedPreferencesController extends Activity {
    private static final SharedPreferencesController ourInstance = new SharedPreferencesController();

    public static SharedPreferencesController getInstance() {
        return ourInstance;
    }

    private SharedPreferences sp;

    private SharedPreferencesController() {
        sp =  this.getSharedPreferences("Login", MODE_PRIVATE);
    }

    public String getEmail() {
        return sp.getString("email", null);
    }

    public String getPassword() {
        return sp.getString("password", null);
    }

    public void setEmail(String email) {
        SharedPreferences.Editor Ed = sp.edit();
        Ed.putString("email", email);
        Ed.commit();
    }

    public void setPassword(String password) {
        SharedPreferences.Editor Ed = sp.edit();
        Ed.putString("password", password);
        Ed.commit();
    }

}
