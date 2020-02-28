package com.example.juleeyahwright.opensesame.ReportDetail;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.CreateReport.CreateReportActivity;
import com.example.juleeyahwright.opensesame.Report.ReportReference;

public class ReportDetailPresenter {

    Context context;

    public ReportDetailPresenter(Context context) {
        this.context = context;
    }

    public void presentReportDetailActivity(AppCompatActivity parent, ReportReference reportReference) {
        Intent intent = new Intent(parent, CreateReportActivity.class);
        intent.putExtra(ReportDetailActivity.REPORT_EXTRA, reportReference);
        context.startActivity(intent);
    }


}
