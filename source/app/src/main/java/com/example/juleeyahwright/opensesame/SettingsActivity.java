package com.example.juleeyahwright.opensesame;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private static final float[] NEGATIVE = {
            -1.0f, 0, 0, 0, 255, // red
            0, -1.0f, 0, 0, 255, // green
            0, 0, -1.0f, 0, 255, // blue
            0, 0, 0, 1.0f, 0  // alpha
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        // Show the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
    }


    @Override
    public void onStart() {
        super.onStart();
    }
}
