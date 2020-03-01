package com.example.juleeyahwright.opensesame.ReportList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.Map.MapActivity;
import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.Settings.SettingsActivity;

import java.util.Objects;

public class ReportListActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_list_activity);
        // Show the back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ReportListAdapter("Need", "To", "Do");

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    // adds a menu to access account
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Close this activity if home is selected
        if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent(ReportListActivity.this, MapActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.settings_option) {
            Intent i = new Intent(ReportListActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.sign_out_option) {
            finish();
        } else if (item.getItemId() == R.id.report_list_option) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
