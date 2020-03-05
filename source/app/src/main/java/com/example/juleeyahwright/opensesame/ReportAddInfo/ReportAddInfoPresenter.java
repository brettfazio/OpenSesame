package com.example.juleeyahwright.opensesame.ReportAddInfo;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.Report.ReportReference;

public class ReportAddInfoPresenter {

    Context context;

    public ReportAddInfoPresenter(Context context) {
        this.context = context;
    }

    public Intent presentReportAddInfoActivity(AppCompatActivity parent, ReportReference reportReference) {
        Intent intent = new Intent(parent, ReportAddInfoActivity.class);
        intent.putExtra(ReportAddInfoActivity.REPORT_EXTRA, reportReference);
        return intent;
    }


}
