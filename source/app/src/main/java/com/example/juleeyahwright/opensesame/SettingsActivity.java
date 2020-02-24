package com.example.juleeyahwright.opensesame;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

/*
SettingsActivity: the settings page for the user's account
 */
public class SettingsActivity extends AppCompatActivity {

    // used for color inversion
    private static final float[] NEGATIVE = {
            -1.0f, 0, 0, 0, 255, // red
            0, -1.0f, 0, 0, 255, // green
            0, 0, -1.0f, 0, 255, // blue
            0, 0, 0, 1.0f, 0  // alpha
    };

    // sets up view to have the settings layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        // Show the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    // adds a menu to access account
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         // Close this activity if home is selected
         if (item.getItemId() == android.R.id.home) {
             finish();
         } else if (item.getItemId() == R.id.settings_option) {
             finish();
         } else if (item.getItemId() == R.id.sign_out_option) {
             finish();
         }

         return super.onOptionsItemSelected(item);
     }

}
