package com.example.juleeyahwright.opensesame.Common;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.juleeyahwright.opensesame.R;

public class BaseActivity extends AppCompatActivity {

    SharedPreferences appPreferences;
    int appTheme;
    int appColor;
    int themeColor;
    Constant constant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateColors();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateColors();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateColors();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateColors();
    }

    // adds a menu to access account
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    protected void updateColors() {
        appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        appTheme = appPreferences.getInt("theme", 0);
        appColor = appPreferences.getInt("color", 0);
        themeColor = appColor;
        constant.color = appColor;

        if (appColor == 0 || themeColor == 0) {
            setTheme(Constant.appTheme);
        } else
            setTheme(appTheme);

        Theme.setColorTheme();
    }



}
