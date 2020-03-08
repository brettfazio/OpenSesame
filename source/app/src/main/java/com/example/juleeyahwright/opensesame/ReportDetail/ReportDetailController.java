package com.example.juleeyahwright.opensesame.ReportDetail;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juleeyahwright.opensesame.Message.Get.MessageGetService;
import com.example.juleeyahwright.opensesame.Message.Get.MessageGetServiceListener;
import com.example.juleeyahwright.opensesame.Message.MessageReference;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.ReportAddInfo.ReportAddInfoPresenter;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class ReportDetailController implements MessageGetServiceListener {

    private final ReportReference reportReference;

    private RecyclerView temporaryRecyclerView;

    private ReportDetailAdapter mAdapter;

    Context context;

    public ReportDetailController(Context context, ReportReference reportReference) {
        this.context = context;
        this.reportReference = reportReference;
    }

    public String getReportName() {
        return reportReference.getName();
    }

    public String getReportInformation() {
        return reportReference.getInformation();
    }

    public String getReportLocationInfo() {
        return reportReference.getLocationInfo();
    }

    public LatLng getReportLatLng() { return reportReference.getLocation(); }

    public Intent intentToAddInfoActivity(AppCompatActivity parent) {
        ReportAddInfoPresenter reportAddInfoPresenter = new ReportAddInfoPresenter(context);
        return reportAddInfoPresenter.presentReportAddInfoActivity(parent, reportReference);
    }

    public void setUpAdapter(RecyclerView recyclerView) {
        MessageGetService getService = new MessageGetService(this);

        temporaryRecyclerView = recyclerView;

        getService.getMessages(reportReference);
    }

    @Override
    public void messageRetrievalSuccess(@NotNull QuerySnapshot querySnapshot, @NotNull MessageReference[] messages) {
        if (mAdapter == null) {
            mAdapter = new ReportDetailAdapter(messages);
        }
        temporaryRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void messageRetrievalFailure(@NotNull Exception exception) {

    }
}
