package com.example.juleeyahwright.opensesame.ReportList;

import com.google.android.gms.maps.model.LatLng;

public class ReportListItem {
    private String reportName;
    private String reportLocation;
    private String reportDistance = "Distance Coming Soon.";
    private String reportDescription;
    private String uid;

    private LatLng reportLatLng;

    public ReportListItem(String name, String loc, LatLng latlng, String desc, String uid){
        this.reportName = name;
        this.reportLocation = loc;
        this.reportLatLng = latlng;
        this.reportDescription = desc;
        this.uid = uid;
    }

    public String getReportName() {
        return reportName;
    }
    public String getReportLocation() {
        return reportLocation;
    }
    public String getReportDistance() {
        return reportDistance;
    }
    public String getReportDescription() {
        return reportDescription;
    }
    public String getReportUID() { return uid; }

    private double calculateReportDistance(LatLng latlng){
        return 0.0;
    }

}
