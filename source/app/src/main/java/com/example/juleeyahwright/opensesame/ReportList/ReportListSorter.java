package com.example.juleeyahwright.opensesame.ReportList;

import java.util.Comparator;

public class ReportListSorter implements Comparator<ReportListItem> {
    private static int compareType = 0;

    public ReportListSorter(int compareType) {
        this.compareType = compareType;
    }

    public ReportListSorter compareType(int compareType) {
        this.compareType = compareType;
        return this;
    }

    public int compare(ReportListItem rli1, ReportListItem rli2) {
        if (compareType == 0) {
            if (rli1.getReportName() == null) {
                return (rli2.getReportName() == null) ? 0 : 1;
            }
            if (rli2.getReportName() == null) {
                return 1;
            }
            return rli1.getReportName().compareTo(rli2.getReportName());
        } else if (compareType == 1) {
            if (rli1.getReportLocation() == null) {
                return (rli2.getReportLocation() == null) ? 0 : 1;
            }
            if (rli2.getReportLocation() == null) {
                return 1;
            }
            return rli1.getReportLocation().compareTo(rli2.getReportLocation());
        } else if (compareType == 2){
            if (rli1.getReportDescription() == null) {
                return (rli2.getReportDescription() == null) ? 0 : 1;
            }
            if (rli2.getReportDescription() == null) {
                return 1;
            }
            return rli1.getReportDescription().compareTo(rli2.getReportDescription());
        } else {
            if (rli1.getReportDistance() == null) {
                return (rli2.getReportDistance() == null) ? 0 : 1;
            }
            if (rli2.getReportDistance() == null) {
                return 1;
            }
            return (rli1.getReportDistance().compareTo(rli2.getReportDistance()));
        }
    }

}
