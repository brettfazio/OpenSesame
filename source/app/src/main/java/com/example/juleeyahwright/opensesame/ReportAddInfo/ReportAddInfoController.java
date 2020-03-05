package com.example.juleeyahwright.opensesame.ReportAddInfo;

import android.content.Context;

import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.Report.ReportService;
import com.example.juleeyahwright.opensesame.Report.ReportServiceListener;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class ReportAddInfoController implements ReportServiceListener {

    private final ReportReference reportReference;

    Context context;

    public ReportAddInfoController(Context context, ReportReference reportReference) {
        this.context = context;
        this.reportReference = reportReference;
    }

    public void addMessage(String message) {
        ReportService reportService = new ReportService(this);

        reportService.addMessageToReport(reportReference, message);
    }

    @Override
    public void reportRetrievalSuccess(@NotNull QuerySnapshot querySnapshot, @NotNull ReportReference[] reportReferences) {

    }

    @Override
    public void reportRetrievalFailure(@NotNull Exception exception) {

    }
}
