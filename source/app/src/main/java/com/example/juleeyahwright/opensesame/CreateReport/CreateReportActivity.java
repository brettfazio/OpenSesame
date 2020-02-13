package com.example.juleeyahwright.opensesame.CreateReport;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.Observable;
import java.util.Observer;

public class CreateReportActivity extends AppCompatActivity implements Observer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_report_activity);

        // Show the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    @Override
    public void update(Observable observable, Object o) {

    }
}
