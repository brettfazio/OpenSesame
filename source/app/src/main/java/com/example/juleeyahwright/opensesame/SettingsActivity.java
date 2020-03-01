package com.example.juleeyahwright.opensesame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.juleeyahwright.opensesame.Map.MapActivity;
import com.example.juleeyahwright.opensesame.ReportList.ReportListActivity;
import com.turkialkhateeb.materialcolorpicker.ColorChooserDialog;
import com.turkialkhateeb.materialcolorpicker.ColorListener;

/*
SettingsActivity: the settings page for the user's account
 */
public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences, appPreferences;
    SharedPreferences.Editor editor;
    int appTheme;
    int appColor;
    int themeColor;
    int textColor;
    Constant constant;

    Button getColorButton;

    private static final String TAG = "Settings";

    // sets up view to have the settings layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        setContentView(R.layout.setting_activity);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        getColorButton = (Button) findViewById(R.id.color_button);

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
                        Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

                colorDialog.show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
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
            Intent i = new Intent(SettingsActivity.this, MapActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.settings_option) {
            finish();
        } else if (item.getItemId() == R.id.sign_out_option) {
            finish();
        } else if (item.getItemId() == R.id.report_list_option) {
            Intent i = new Intent(SettingsActivity.this, ReportListActivity.class);
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
