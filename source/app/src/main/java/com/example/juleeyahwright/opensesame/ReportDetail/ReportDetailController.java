package com.example.juleeyahwright.opensesame.ReportDetail;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.ReportAddInfo.ReportAddInfoPresenter;

public class ReportDetailController {

    private final ReportReference reportReference;

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

    public Intent intentToAddInfoActivity(AppCompatActivity parent) {
        ReportAddInfoPresenter reportAddInfoPresenter = new ReportAddInfoPresenter(context);
        return reportAddInfoPresenter.presentReportAddInfoActivity(parent, reportReference);
    }
}
