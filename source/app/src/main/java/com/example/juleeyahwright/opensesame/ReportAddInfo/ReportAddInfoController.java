package com.example.juleeyahwright.opensesame.ReportAddInfo;

import android.content.Context;
import android.widget.Toast;

import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.Report.Update.ReportUpdateService;
import com.example.juleeyahwright.opensesame.Report.Update.ReportUpdateServiceListener;

import org.jetbrains.annotations.NotNull;

public class ReportAddInfoController implements ReportUpdateServiceListener {

    private final ReportReference reportReference;

    Context context;

    public ReportAddInfoController(Context context, ReportReference reportReference) {
        this.context = context;
        this.reportReference = reportReference;
    }

    public void addMessage(String message) {
        ReportUpdateService reportUpdateService = new ReportUpdateService(this);

        reportUpdateService.addMessageToReport(reportReference, message);
    }

    @Override
    public void reportUpdateSuccess() {

    }

    @Override
    public void reportUpdateFailure(@NotNull Exception exception) {
        Toast.makeText(context,
                "Failed to add message with error: " + exception.getMessage(),
                Toast.LENGTH_LONG).show();
    }
}
