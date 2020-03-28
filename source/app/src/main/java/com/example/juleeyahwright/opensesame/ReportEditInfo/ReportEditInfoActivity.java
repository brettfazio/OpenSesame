package com.example.juleeyahwright.opensesame.ReportEditInfo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juleeyahwright.opensesame.AccountModel.AccountActivity;
import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.Report.Report;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.ReportDetail.ReportDetailController;
import com.example.juleeyahwright.opensesame.ReportList.ReportListActivity;
import com.example.juleeyahwright.opensesame.Settings.SettingsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Objects;

public class ReportEditInfoActivity extends BaseActivity {

    private ReportDetailController controller;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Report> reportsToEdit;
    Report reportToEdit = null;
    String intentUid = null;
    String intentTitle = null;
    String intentDesc = null;
    String reportUid = null;
    String reportTitle = null;
    String reportDesc = null;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_edit_info_activity);

        // Show the back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        intentUid = intent.getStringExtra("reportUID");
        intentTitle = intent.getStringExtra("reportTitle");
        intentDesc = intent.getStringExtra("reportDescription");
        Log.d("tags", "something please omfg");
        Log.d("tags", "info here: ");
        Log.d("tags", "uid: " + intentUid + "\ntitle: " + intentTitle + "\ndesc: " + intentDesc);

        Query data = FirebaseDatabase.getInstance().getReference().child("reports").orderByChild("uid").equalTo("reportUID");
        reportsToEdit = new ArrayList<>();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Report newReport = snapshot.getValue(Report.class);
                        reportsToEdit.add(newReport);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError dbError) {

            }
        };

        data.addListenerForSingleValueEvent(valueEventListener);

        if(reportsToEdit.size() < 1) {
            Log.d("myTags", "Report list was empty");
        }

        for(Report reportToEdit : reportsToEdit) {
            reportUid = reportToEdit.getUID();
            reportTitle = reportToEdit.getName();
            reportDesc = reportToEdit.getInformation();
        }

        if(reportToEdit != null) {
            setFields();
        }

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
        // setLocationInfoField();
    }

    private void setTitleField() {
        EditText editText = findViewById(R.id.editTitleHeader);
        editText.setText(reportToEdit.getName());
        editText.setVisibility(View.VISIBLE);
    }

    private void setInfoField() {
        EditText editText = findViewById(R.id.editReportDetailInfo);
        // editText.setText(reportToEdit.getInformation());
        editText.setText("HEREJKLDJKLJLDSKJLJFKLDSJFKDFJSDL F");
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
