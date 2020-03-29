package com.example.juleeyahwright.opensesame.ReportEditInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.Report.Report;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.ReportList.ReportListActivity;
import com.example.juleeyahwright.opensesame.Settings.SettingsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ReportEditInfoActivity extends BaseActivity {

    public static final String REPORT_EXTRA = "report";

    private ReportEditInfoController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_edit_info_activity);

        // Show the back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        controller = new ReportEditInfoController(getApplicationContext(),
                (ReportReference) getIntent().getExtras().get(REPORT_EXTRA));

//        Query data = FirebaseDatabase.getInstance().getReference().child("reports");
//        data.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Report report = snapshot.getValue(Report.class);
//                    //if(report.getUID() == intentUid)
//                    if (report.getName() == controller.getReportName()) {
//                        if (report.getInformation() == controller.getReportInformation()) {
//                            //reportToEdit = report;
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError dbError) {
//
//            }
//        });

        if (controller.getReportInformation() != null) {
            setFields();
        } else {
        }

        Button addButton = findViewById(R.id.reportDetailEditButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveNewInfo();
                finish();
            }
        });
    }

    private void saveNewInfo() {
        if (!allFieldsFilledOut()) return;
        controller.updateReport(getTitleField(), getInfoField(), getLocationInfoField());
        finish();
    }

    private boolean allFieldsFilledOut() {
        String title = getTitleField();
        String info = getInfoField();
        String location = getLocationInfoField();

        if (title == null || title.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Title must be non-empty.",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (info == null || info.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Information must be non-empty.",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (location == null || location.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Location must be non-empty.",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void setFields() {
        setTitleField();
        setInfoField();
        setLocationInfoField();
    }

    private void setTitleField() {
        EditText editText = findViewById(R.id.editTitleHeader);
        editText.setText(controller.getReportName());
        editText.setVisibility(View.VISIBLE);
    }

    private void setInfoField() {
        EditText editText = findViewById(R.id.editReportDetailInfo);
        editText.setText(controller.getReportInformation());
        editText.setVisibility(View.VISIBLE);
    }

    private void setLocationInfoField() {
        EditText editText = findViewById(R.id.editReportLocInfo);
        editText.setText(controller.getReportLocationInfo());
        editText.setVisibility(View.VISIBLE);
    }

    private String getTitleField() {
        EditText editText = findViewById(R.id.editTitleHeader);
        return editText.getText().toString();
    }

    private String getInfoField() {
        EditText editText = findViewById(R.id.editReportDetailInfo);
        return editText.getText().toString();
    }

    private String getLocationInfoField() {
        EditText editText = findViewById(R.id.editReportLocInfo);
        return editText.getText().toString();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Close this activity if home is selected
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.settings_option) {
            Intent i = new Intent(ReportEditInfoActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.sign_out_option) {
            finish();
        } else if (item.getItemId() == R.id.report_list_option) {
            Intent i = new Intent(ReportEditInfoActivity.this, ReportListActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.account_option) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
