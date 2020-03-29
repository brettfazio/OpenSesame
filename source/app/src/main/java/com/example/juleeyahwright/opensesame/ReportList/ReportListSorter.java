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
            return rli1.getReportName().compareTo(rli2.getReportName());
        } else if (compareType == 1) {
            return rli1.getReportLocation().compareTo(rli2.getReportLocation());
        } else if (compareType == 2){
            return rli1.getReportDescription().compareTo(rli2.getReportDescription());
        } else {
            return (rli1.getReportDistance().compareTo(rli2.getReportDistance()));
        }
    }

}
