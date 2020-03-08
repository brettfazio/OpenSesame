package com.example.juleeyahwright.opensesame.ReportList;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.juleeyahwright.opensesame.Common.BaseActivity;
import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.Report.Get.ReportGetService;
import com.example.juleeyahwright.opensesame.Report.Get.ReportGetServiceListener;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.ReportDetail.ReportDetailController;
import com.google.firebase.firestore.QuerySnapshot;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Objects;

public class ReportListActivity extends BaseActivity implements ReportGetServiceListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ReportListItem> reportArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_list_activity);
        // Show the back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ReportGetService service = new ReportGetService(this);
        service.getReports();
    }

    // adds a menu to access account
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
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
        ReportDetailController rdc;
        for(ReportReference r : reportReferences){
            rdc = new ReportDetailController(this, r);
            reportArray.add(new ReportListItem(
                    rdc.getReportName(),
                    rdc.getReportLocationInfo(),
                    rdc.getReportLatLng(),
                    rdc.getReportInformation()));
        }
        createList();
    }

    @Override
    public void reportRetrievalFailure(@NotNull Exception exception) {
    }
}
