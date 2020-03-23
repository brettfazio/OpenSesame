package com.example.juleeyahwright.opensesame.Map;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.ReportDetail.ReportDetailPresenter;
import com.google.android.gms.maps.model.Marker;

public class MapController {

    private final Context context;

    public MapController(Context context) {
        this.context = context;
    }

    public Intent markerWasTapped(AppCompatActivity parent, Marker marker) {
        ReportDetailPresenter reportDetailPresenter = new ReportDetailPresenter(context);
        return reportDetailPresenter.presentReportDetailActivity(parent, (ReportReference) marker.getTag());
    }
}
