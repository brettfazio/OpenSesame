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

public class CreateReportActivity extends AppCompatActivity implements CreateReportListener {

    private CreateReportController controller;

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
        // Close this activity
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private LatLng getLatLng() {
        return (LatLng) getIntent().getExtras().get("LOCATION");
    }

    private void createReportClicked() {
        String title = getEnteredTitle();

        if (title == null || title.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Title must be non-empty.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        controller.writeReport(new Report(title, "", new LatLng(0,0)));
    }

    private String getEnteredTitle() {
        String text = ((EditText) findViewById(R.id.titleEditText)).getText().toString();

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
