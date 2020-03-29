package com.example.juleeyahwright.opensesame.ReportEditInfo;

import android.content.Context;

import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.Report.Update.ReportUpdateService;
import com.example.juleeyahwright.opensesame.Report.Update.ReportUpdateServiceListener;

import org.jetbrains.annotations.NotNull;

public class ReportEditInfoController implements ReportUpdateServiceListener {

    private final ReportReference reportReference;

    Context context;

    public ReportEditInfoController(Context applicationContext, ReportReference reportReference) {
        this.context = applicationContext;
        this.reportReference = reportReference;
    }

    public String getReportName() {
        if(reportReference!=null)
            return reportReference.getName();
        return "";
    }

    public void updateReport(String newTitle, String newInfo, String newLocInfo) {
        ReportUpdateService updateService = new ReportUpdateService(this);

        updateService.updateReportFields(reportReference, newTitle, newInfo, newLocInfo);
    }

    public String getReportInformation() {
        return reportReference.getInformation();
    }

    public String getReportLocationInfo() {
        return reportReference.getLocationInfo();
    }

    @Override
    public void reportUpdateSuccess() {

    }

    @Override
    public void reportUpdateFailure(@NotNull Exception exception) {

    }
}
