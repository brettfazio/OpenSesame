package com.example.juleeyahwright.opensesame.ReportAddInfo;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.Report.ReportReference;

public class ReportAddInfoActivity extends BaseActivity {

    public static final String REPORT_EXTRA = "report";

    private ReportAddInfoController controller;

    // sets up the layout for the create report
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_add_info_activity);

        // Show the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        controller = new ReportAddInfoController(getApplicationContext(),
                (ReportReference) getIntent().getExtras().get(REPORT_EXTRA));

        Button addButton = findViewById(R.id.createMessageButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createButtonClicked();
            }
        });
    }

    private String getEnteredInfo() {
        return ((EditText) findViewById(R.id.messageInfoEditText)).getText().toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Close this activity if home is selected
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void createButtonClicked() {
        String message = getEnteredInfo();

        if (message == null || message.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Information must be non-empty.",
                    Toast.LENGTH_LONG).show();
        }

        controller.addMessage(message);

        finish();
    }

}
