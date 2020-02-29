package com.example.juleeyahwright.opensesame;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.Map.MapActivity;

/*
SettingsActivity: the settings page for the user's account
 */
public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "Settings";

    // sets up view to have the settings layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        RadioGroup fontGroup = (RadioGroup)findViewById(R.id.fontSizeGroup);
        RadioButton checkedRadioButton = (RadioButton)fontGroup.findViewById(fontGroup.getCheckedRadioButtonId());

        fontGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup arg0, int id) {
            switch (id) {
            case R.id.smallRadioButton:
              Log.v("SmallFontButton", "Small font selected");
              break;
            case R.id.medRadioButton:
              Log.v("MediumFontButton", "Medium font selected");
              break;
            case R.id.largeRadioButton:
              Log.v("LargeFontButton", "Large font selected");
              break;
            default:
              break;
            }
        }
    });

        CheckBox showCompass = findViewById(R.id.show_compass);
        CheckBox showMapType = findViewById(R.id.satellite_hybrid);
        CheckBox showZoom = findViewById(R.id.show_zoom);

        showCompass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });

        showMapType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked) {
                    Log.v(TAG, "set compass");
                }
            }
        });

        showZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });

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

     public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.show_compass:
                if (checked) {
                    Intent i = new Intent(this, MapActivity.class);
                    i.putExtra("SHOW_COMPASS", true);
                    Log.v(TAG, "set compass");
                }
            case R.id.satellite_hybrid:
                if (checked) {
                    Intent i = new Intent(this, MapActivity.class);
                    i.putExtra("SATELLITE_HYBRID", true);
                    Log.v(TAG, "set satellite");
                }
            case R.id.show_zoom:
                if (checked) {
                    Intent i = new Intent(this, MapActivity.class);
                    i.putExtra("SHOW_ZOOM", true);
                    Log.v(TAG, "set zoom");
                }
        }
     }

}
