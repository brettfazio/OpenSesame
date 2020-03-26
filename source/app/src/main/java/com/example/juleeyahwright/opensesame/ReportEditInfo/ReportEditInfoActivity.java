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
import com.example.juleeyahwright.opensesame.Report.Report;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.ReportDetail.ReportDetailController;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Objects;

public class ReportEditInfoActivity extends BaseActivity {

    private ReportDetailController controller;

    private RecyclerView.LayoutManager layoutManager;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_edit_info_activity);

        // Show the back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        final String uid = intent.getStringExtra("reportUID");
        String title = intent.getStringExtra("reportTitle");
        String desc = intent.getStringExtra("reportDescription");

        Query data = FirebaseDatabase.getInstance().getReference().child("reports").orderByChild("uid").equalTo("reportUID");

        FirebaseDatabase.getInstance().getReference().child("reports")
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        if(uid != null && snapshot.getValue("uid")) {
//
//                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

        setFields();

        Button addButton = findViewById(R.id.reportDetailEditButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveNewInfo() {
        // edit report stuff here
    }

    private void setFields() {
        setTitleField();
        setInfoField();
        setLocationInfoField();
    }

    private void setTitleField() {
        TextView textView = findViewById(R.id.reportDetailHeader);
        textView.setVisibility(View.GONE);
        EditText editText = null;
        String currentInfo = null;
        if(controller != null) {
            if(controller.getReportName() != null) {
                textView.setText(controller.getReportName());
                currentInfo = (String) textView.getText();
        }
        }
        editText = findViewById(R.id.editTitleHeader);
        if(currentInfo != null)
            editText.setText(currentInfo);
        editText.setVisibility(View.VISIBLE);
    }

    private void setInfoField() {
        TextView textView = findViewById(R.id.reportDetailInfo);
        textView.setVisibility(View.GONE);
        EditText editText = null;
        String currentInfo = null;
        if(controller != null) {
            if(controller.getReportInformation() != null) {
                textView.setText(controller.getReportInformation());
                currentInfo = (String) textView.getText();
            }
        }
        editText = findViewById(R.id.editReportDetailInfo);
        if(currentInfo != null)
            editText.setText(currentInfo);
        editText.setVisibility(View.VISIBLE);
    }

    private void setLocationInfoField() {
        TextView textView = findViewById(R.id.reportDetailLocationInfo);
        textView.setVisibility(View.GONE);
        EditText editText = null;
        String currentInfo = null;
        if(controller != null) {
            if(controller.getReportLocationInfo() != null) {
                textView.setText(controller.getReportLocationInfo());
                currentInfo = (String) textView.getText();
            }
        }
        editText = findViewById(R.id.editReportLocInfo);
        if(currentInfo != null)
            editText.setText(currentInfo);
        editText.setVisibility(View.VISIBLE);
    }
}
