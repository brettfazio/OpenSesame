package com.example.juleeyahwright.opensesame.ReportDetail;

import com.example.juleeyahwright.opensesame.Report.ReportReference;

public class ReportDetailController {

    private final ReportReference reportReference;

    public ReportDetailController(ReportReference reportReference) {
        this.reportReference = reportReference;
    }

    public String getReportName() {
        return reportReference.getName();
    }

    public String getReportInformation() {
        return reportReference.getInformation();
    }

    public String getReportLocationInfo() {
        return reportReference.getLocationInfo();
    }
}
