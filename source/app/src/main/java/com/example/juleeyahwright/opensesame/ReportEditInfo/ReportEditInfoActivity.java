package com.example.juleeyahwright.opensesame.ReportEditInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.ReportDetail.ReportDetailController;

import java.util.Objects;

public class ReportEditInfoActivity extends BaseActivity {

    public static final String REPORT_EXTRA = "report";
    private ReportDetailController controller;

    private RecyclerView.LayoutManager layoutManager;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_edit_info_activity);

        // Show the back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        controller = new ReportDetailController(getApplicationContext(),
                (ReportReference) getIntent().getExtras().get(REPORT_EXTRA));

        // setFields();

//        Button addButton = findViewById(R.id.reportDetailEditButton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                saveNewInfo();
//            }
//        });
    }

    private void saveNewInfo() {
        // edit report stuff here
    }

//    private void setFields() {
//        setTitleField();
//        setInfoField();
//        setLocationInfoField();
//    }
//
//    private void setTitleField() {
//        TextView textView = findViewById(R.id.reportDetailHeader);
//        textView.setVisibility(View.GONE);
//        textView.setText(controller.getReportName());
//        String currentInfo = (String) textView.getText();
//        EditText editText = findViewById(R.id.editTitleHeader);
//        editText.setText(currentInfo);
//        editText.setVisibility(View.VISIBLE);
//    }
//
//    private void setInfoField() {
//        TextView textView = findViewById(R.id.reportDetailInfo);
//        textView.setVisibility(View.GONE);
//        textView.setText(controller.getReportInformation());
//        String currentInfo = (String) textView.getText();
//        EditText editText = findViewById(R.id.editReportDetailInfo);
//        editText.setText(currentInfo);
//        editText.setVisibility(View.VISIBLE);
//    }
//
//    private void setLocationInfoField() {
//        TextView textView = findViewById(R.id.reportDetailLocationInfo);
//        textView.setVisibility(View.GONE);
//        textView.setText(controller.getReportLocationInfo());
//        String currentInfo = (String) textView.getText();
//        EditText editText = findViewById(R.id.editReportLocInfo);
//        editText.setText(currentInfo);
//        editText.setVisibility(View.VISIBLE);
//    }
}
