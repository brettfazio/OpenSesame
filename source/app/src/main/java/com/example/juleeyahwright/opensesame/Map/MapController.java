package com.example.juleeyahwright.opensesame.Map;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.Report.Get.ReportGetService;
import com.example.juleeyahwright.opensesame.Report.Get.ReportGetServiceListener;
import com.example.juleeyahwright.opensesame.ReportDetail.ReportDetailPresenter;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class MapController implements ReportGetServiceListener {

    Context context;

    public MapController(Context context) {
        this.context = context;

        ReportGetService service = new ReportGetService(this);

        service.getReports();
    }

    public Intent markerWasTapped(AppCompatActivity parent, Marker marker) {
        ReportDetailPresenter reportDetailPresenter = new ReportDetailPresenter(context);
        return reportDetailPresenter.presentReportDetailActivity(parent, (ReportReference) marker.getTag());
    }

    @Override
    public void reportRetrievalSuccess(@NotNull QuerySnapshot querySnapshot, @NotNull ReportReference[] reportReferences) {
    }

    @Override
    public void reportRetrievalFailure(@NotNull Exception exception) {

    }
}
