package com.example.juleeyahwright.opensesame.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.preference.PreferenceManager;

import com.example.juleeyahwright.opensesame.AccountModel.AccountActivity;
import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.Common.Constant;
import com.example.juleeyahwright.opensesame.Common.SharedPreferencesController;
import com.example.juleeyahwright.opensesame.Common.Theme;
import com.example.juleeyahwright.opensesame.Map.MapActivity;
import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.ReportList.ReportListActivity;
import com.turkialkhateeb.materialcolorpicker.ColorChooserDialog;
import com.turkialkhateeb.materialcolorpicker.ColorListener;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;

/*
SettingsActivity: the settings page for the user's account
 */
public class SettingsActivity extends BaseActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent intent;
    boolean isSatellite = false;

    Button getColorButton;
    CheckBox showMapType;

    private static final String TAG = "Settings";

    // sets up view to have the settings layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        getColorButton = findViewById(R.id.color_button);

        getColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorChooserDialog colorDialog = new ColorChooserDialog(SettingsActivity.this);
                colorDialog.setTitle("Choose Primary Theme Color");
                colorDialog.setColorListener(new ColorListener() {
                    @Override
                    public void OnColorClick(View v, int color) {
                        Constant.color = color;
                        Theme.setColorTheme();
                        editor.putInt("color", color);
                        editor.putInt("theme", Constant.appTheme);
                        editor.commit();
                        intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

                colorDialog.show();
            }
        });


        showMapType = findViewById(R.id.satellite_hybrid);
        showMapType.setChecked(SharedPreferencesController.getSatelliteChecked(getApplicationContext()));
//        CheckBox showZoom = findViewById(R.id.show_zoom);
//
//
        showMapType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked) {
                    SharedPreferencesController.setMapType(getApplicationContext(), "hybrid");
                    SharedPreferencesController.isSatelliteChecked(getApplicationContext(), true);
                } else {
                    SharedPreferencesController.setMapType(getApplicationContext(), "normal");
                    SharedPreferencesController.isSatelliteChecked(getApplicationContext(), false);
                }
            }
        });
//
//        showZoom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onCheckboxClicked(v);
//            }
//        });

        // Show the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Close this activity if home is selected
        if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent(SettingsActivity.this, MapActivity.class);

            startActivity(i);
        } else if (item.getItemId() == R.id.settings_option) {
            finish();
        } else if (item.getItemId() == R.id.sign_out_option) {
            finish();
        } else if (item.getItemId() == R.id.report_list_option) {
            Intent i = new Intent(SettingsActivity.this, ReportListActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.account_option) {
            Intent i = new Intent(SettingsActivity.this, AccountActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
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
