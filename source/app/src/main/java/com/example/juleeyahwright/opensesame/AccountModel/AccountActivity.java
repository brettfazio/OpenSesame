package com.example.juleeyahwright.opensesame.AccountModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juleeyahwright.opensesame.ReportList.ReportListActivity;
import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.Common.SharedPreferencesController;
import com.example.juleeyahwright.opensesame.R;

import com.example.juleeyahwright.opensesame.Report.Get.ReportGetService;
import com.example.juleeyahwright.opensesame.Report.Get.ReportGetServiceListener;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.Settings.SettingsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class AccountActivity extends BaseActivity implements ReportGetServiceListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<AccountListItem> reportArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);
        // Show the back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ReportGetService service = new ReportGetService(this);
        service.getReports();
    }

    public void createList() {
        mRecyclerView = findViewById(R.id.account_list_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AccountListAdapter(reportArray);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Close this activity if home is selected
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.settings_option) {
            Intent i = new Intent(AccountActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.sign_out_option) {
            finish();
        } else if (item.getItemId() == R.id.report_list_option) {
            Intent i = new Intent(AccountActivity.this, ReportListActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.account_option) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void reportRetrievalSuccess(@NotNull QuerySnapshot querySnapshot, @NotNull ReportReference[] reportReferences) {
        String email = SharedPreferencesController.getEmail(getApplicationContext());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for(ReportReference reportReference : reportReferences){
                String userId = user.getUid();
                if (userId != null && !userId.isEmpty()) {
                    if(reportReference.getUID() != null && reportReference.getUID().equals(userId)) {
                        reportArray.add(new AccountListItem(
                            reportReference.getName(),
                            reportReference.getLocationInfo(),
                            reportReference.getLocation(),
                            reportReference.getLocationInfo(),
                            reportReference.getUID()));
                    }
                }
            }
            createList();
        }
    }

    @Override
    public void reportRetrievalFailure(@NotNull Exception exception) {
    }
}
