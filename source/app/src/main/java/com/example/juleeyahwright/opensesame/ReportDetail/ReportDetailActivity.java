package com.example.juleeyahwright.opensesame.ReportDetail;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.juleeyahwright.opensesame.Constant;
import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.Theme;

public class ReportDetailActivity extends AppCompatActivity {

    public static final String REPORT_EXTRA = "report";
    SharedPreferences sharedPreferences, appPreferences;
    SharedPreferences.Editor editor;
    int appTheme;
    int appColor;
    int themeColor;
    Constant constant;

    private ReportDetailController controller;

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

        setContentView(R.layout.report_detail_activity);

        // Show the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        controller = new ReportDetailController(
                (ReportReference) getIntent().getExtras().get(REPORT_EXTRA));
    }

    // End the activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Close this activity if home is selected
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
