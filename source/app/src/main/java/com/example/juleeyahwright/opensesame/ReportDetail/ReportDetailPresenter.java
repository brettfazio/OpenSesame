package com.example.juleeyahwright.opensesame.ReportDetail;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.CreateReport.CreateReportActivity;

public class ReportDetailPresenter {

    Context context;

    public ReportDetailPresenter(Context context) {
        this.context = context;
    }

    public void presentReportDetailActivity(AppCompatActivity parent) {
        Intent intent = new Intent(parent, CreateReportActivity.class);
        context.startActivity(intent);
    }


}
