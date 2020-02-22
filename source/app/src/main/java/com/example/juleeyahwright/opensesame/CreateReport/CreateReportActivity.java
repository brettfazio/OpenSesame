package com.example.juleeyahwright.opensesame.CreateReport;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

/*
CreateReportActivity: displays the report for the user to fill out
 */
public class CreateReportActivity extends AppCompatActivity implements CreateReportListener {

    private CreateReportController controller;

    // sets up the layout for the create report
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_report_activity);

        controller = new CreateReportController(FirebaseFirestore.getInstance(), this);

        // Show the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button createReportButton = findViewById(R.id.createReportButton);
        createReportButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createReportClicked();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Close this activity if home is selected
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    // from the map activity, when the create a report button was pressed
    // the location that the user selected was stored to be picked up here
    private LatLng getLatLng() {
        return (LatLng) getIntent().getExtras().get("LOCATION");
    }

    // when the user tries to submit, verify that all fields are filled out
    private void createReportClicked() {
        String title = getEnteredTitle();
        String info = getEnteredInfo();

        if (title == null || title.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Title must be non-empty.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (info == null || info.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Information must be non-empty.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        controller.writeReport(title, info, getLatLng());
    }

    private String getEnteredTitle() {
        String text = ((EditText) findViewById(R.id.titleEditText)).getText().toString();

        return text;
    }

    private String getEnteredInfo() {
        String text = ((EditText) findViewById(R.id.infoEditText)).getText().toString();

        return text;
    }

    @Override
    public void reportCreateSuccess(@NotNull Report report) {
        finish();
    }

    @Override
    public void reportCreateFailure(@NotNull Report report, @NotNull Exception exception) {
        Toast.makeText(getApplicationContext(),
                "Failed to create report with error: " + exception.toString(),
                Toast.LENGTH_LONG).show();
    }
}
