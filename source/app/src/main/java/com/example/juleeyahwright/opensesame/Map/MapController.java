package com.example.juleeyahwright.opensesame.Map;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.Report.ReportService;
import com.example.juleeyahwright.opensesame.Report.ReportServiceListener;
import com.example.juleeyahwright.opensesame.ReportDetail.ReportDetailPresenter;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class MapController implements ReportServiceListener {

    Context context;

    public MapController(Context context) {
        this.context = context;

        ReportService service = new ReportService(this);

        service.getReports();
    }

    public void markerWasTapped(AppCompatActivity parent, Marker marker) {
        ReportDetailPresenter reportDetailPresenter = new ReportDetailPresenter(context);
        reportDetailPresenter.presentReportDetailActivity(parent, (ReportReference) marker.getTag());
    }

    @Override
    public void reportRetrievalSuccess(@NotNull QuerySnapshot querySnapshot) {
    }

    @Override
    public void reportRetrievalFailure(@NotNull Exception exception) {

    }
}
