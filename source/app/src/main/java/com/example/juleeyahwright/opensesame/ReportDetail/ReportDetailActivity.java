package com.example.juleeyahwright.opensesame.ReportDetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.Report.ReportReference;

import java.util.Objects;

public class ReportDetailActivity extends BaseActivity {

    public static final String REPORT_EXTRA = "report";
    private ReportDetailController controller;

    private RecyclerView recyclerView;
    //private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_detail_activity);

        // Show the back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        controller = new ReportDetailController(getApplicationContext(),
                (ReportReference) getIntent().getExtras().get(REPORT_EXTRA));

        setFields();

        Button addButton = findViewById(R.id.reportDetailAddMessage);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addInfoTapped();
            }
        });

        // Recycler view set up
        recyclerView = findViewById(R.id.reportDetailMessageRecycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setUpMessages();
    }

    private void addInfoTapped() {
        Intent intent = controller.intentToAddInfoActivity(this);
        startActivity(intent);
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

    private void setUpMessages() {
        controller.refreshMessages(recyclerView);
    }

    private void setFields() {
        setTitleField();
        setInfoField();
        setLocationInfoField();
    }

    private void setTitleField() {
        TextView textView = findViewById(R.id.reportDetailHeader);
        textView.setText(controller.getReportName());
    }

    private void setInfoField() {
        TextView textView = findViewById(R.id.reportDetailInfo);
        textView.setText(controller.getReportInformation());
    }

    private void setLocationInfoField() {
        TextView textView = findViewById(R.id.reportDetailLocationInfo);
        textView.setText(controller.getReportLocationInfo());
    }

}
