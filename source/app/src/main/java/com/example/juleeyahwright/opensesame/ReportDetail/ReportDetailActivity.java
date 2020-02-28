package com.example.juleeyahwright.opensesame.ReportDetail;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.Report.ReportReference;

public class ReportDetailActivity extends AppCompatActivity {

    public static final String REPORT_EXTRA = "report";

    private ReportDetailController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
