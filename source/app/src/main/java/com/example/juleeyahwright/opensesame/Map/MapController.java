package com.example.juleeyahwright.opensesame.Map;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.ReportDetail.ReportDetailPresenter;
import com.google.android.gms.maps.model.Marker;

public class MapController {

    Context context;

    public MapController(Context context) {
        this.context = context;
    }

    public void markerWasTapped(AppCompatActivity parent, Marker marker) {
        ReportDetailPresenter reportDetailPresenter = new ReportDetailPresenter(context);

        reportDetailPresenter.presentReportDetailActivity(parent);
    }


}
