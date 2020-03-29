package com.example.juleeyahwright.opensesame.ReportList;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juleeyahwright.opensesame.AccountModel.AccountActivity;
import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.R;

import com.example.juleeyahwright.opensesame.Report.Get.ReportGetService;
import com.example.juleeyahwright.opensesame.Report.Get.ReportGetServiceListener;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.Settings.SettingsActivity;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ReportListActivity extends BaseActivity implements ReportGetServiceListener, AdapterView.OnItemSelectedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ReportListItem> reportArray = new ArrayList<>();
    private int sortType = -1;
    private boolean asc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_list_activity);
        // Show the back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Spinner optSpinner = findViewById(R.id.sort_type_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.sort_options, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optSpinner.setAdapter(adapter1);
        optSpinner.setOnItemSelectedListener(this);

        Spinner orderSpinner = findViewById(R.id.sort_order_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.sort_order, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderSpinner.setAdapter(adapter2);
        orderSpinner.setOnItemSelectedListener(this);

        ReportGetService service = new ReportGetService(this);
        service.getReports();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Close this activity if home is selected
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.settings_option) {
            Intent i = new Intent(ReportListActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.sign_out_option) {
            finish();
        } else if (item.getItemId() == R.id.report_list_option) {
            finish();
        } else if (item.getItemId() == R.id.account_option) {
            Intent i = new Intent(ReportListActivity.this, AccountActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void createList() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ReportListAdapter(reportArray);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void reportRetrievalSuccess(@NotNull QuerySnapshot querySnapshot, @NotNull ReportReference[] reportReferences) {
        for(ReportReference reportReference : reportReferences){
            reportArray.add(new ReportListItem(
                    reportReference.getName(),
                    reportReference.getLocationInfo(),
                    reportReference.getLocation(),
                    reportReference.getInformation(),
                    reportReference.getUID()));
        }
        Collections.sort(reportArray, new ReportListSorter(sortType));
        if(!asc){
            Collections.reverse(reportArray);
        }
        createList();
    }

    @Override
    public void reportRetrievalFailure(@NotNull Exception exception) {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        switch(item) {
            case "Title":
                sortType = 0;
                break;
            case "Description":
                sortType = 2;
                break;
            case "Location":
                sortType = 1;
                break;
            case "Distance":
                sortType = -1;
                break;
            case "Ascending":
                asc = true;
                break;
            case "Descending":
                asc = false;
                break;
        }
        Collections.sort(reportArray, new ReportListSorter(sortType));
        if(!asc){
            Collections.reverse(reportArray);
        }
        createList();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
    }
}
