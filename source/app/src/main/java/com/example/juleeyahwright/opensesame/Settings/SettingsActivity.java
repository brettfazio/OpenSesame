package com.example.juleeyahwright.opensesame.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import androidx.preference.PreferenceManager;

import com.example.juleeyahwright.opensesame.AccountModel.AccountActivity;
import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.Common.Constant;
import com.example.juleeyahwright.opensesame.Common.SharedPreferencesController;
import com.example.juleeyahwright.opensesame.Common.Theme;
import com.example.juleeyahwright.opensesame.LoginActivity;
import com.example.juleeyahwright.opensesame.Map.MapActivity;
import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.ReportList.ReportListActivity;
import com.turkialkhateeb.materialcolorpicker.ColorChooserDialog;
import com.turkialkhateeb.materialcolorpicker.ColorListener;

/*
SettingsActivity: the settings page for the user's account
 */
public class SettingsActivity extends BaseActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent intent;
    private static String markerColor = "#9822ff";

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
                        markerColor = String.format("#%06X", (0xFFFFFF & color));;
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

        RadioGroup themeGroup = findViewById(R.id.theme_group);
        String theme = SharedPreferencesController.getMapTheme(getApplicationContext());
        if(theme != null) {
            switch(theme){
            case ("ucf"):
                themeGroup.check(R.id.ucf_theme);
                break;
            case("night"):
                themeGroup.check(R.id.night_theme);
                break;
            case("monochrome"):
                themeGroup.check(R.id.monochrome_theme);
                break;
            case("dark_blue"):
                themeGroup.check(R.id.dark_blue_theme);
                break;
            default:
                themeGroup.check(R.id.standard_theme);
                break;
            }
        } else
            themeGroup.check(R.id.standard_theme);

        themeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.ucf_theme:
                        SharedPreferencesController.setMapTheme(getApplicationContext(), "ucf");

                        break;
                    case R.id.monochrome_theme:
                        SharedPreferencesController.setMapTheme(getApplicationContext(), "monochrome");
                        break;
                    case R.id.night_theme:
                        SharedPreferencesController.setMapTheme(getApplicationContext(), "night");
                        break;
                    case R.id.dark_blue_theme:
                        SharedPreferencesController.setMapTheme(getApplicationContext(), "dark_blue");
                        break;
                    default:
                        SharedPreferencesController.setMapTheme(getApplicationContext(), "standard");
                        break;
                }
                editor.commit();
            }
        });

        // Show the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public static String getColor(){
        return markerColor;
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
            SharedPreferencesController.clearSignInData(getApplicationContext());
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.report_list_option) {
            Intent i = new Intent(SettingsActivity.this, ReportListActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.account_option) {
            Intent i = new Intent(SettingsActivity.this, AccountActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
