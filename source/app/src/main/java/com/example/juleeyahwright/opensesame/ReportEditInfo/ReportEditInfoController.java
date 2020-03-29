package com.example.juleeyahwright.opensesame.ReportEditInfo;

import android.content.Context;

import com.example.juleeyahwright.opensesame.Report.ReportReference;

public class ReportEditInfoController {

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

    public String getReportInformation() {
        return reportReference.getInformation();
    }

    public String getReportLocationInfo() {
        return reportReference.getLocationInfo();
    }

}
