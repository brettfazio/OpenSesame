package com.example.juleeyahwright.opensesame.ReportList;

import com.google.android.gms.maps.model.LatLng;
import java.lang.Math;

public class ReportListItem {
    private String reportName;
    private String reportLocation;
    private String reportDistance;
    private String reportDescription;
    private String uid;


    public ReportListItem(String name, String loc, LatLng latlng, String desc, String uid){
        this.reportName = name;
        this.reportLocation = loc;
        this.reportDistance = calculateReportDistance(latlng);
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

    private String calculateReportDistance(LatLng latlng){
        // Center of UCF, temporarily
        LatLng point1 = new LatLng(28.6024, -81.2001);
        double radius = 6371e3;
        double ang1 = Math.toRadians(point1.latitude);
        double ang2 = Math.toRadians(latlng.latitude);
        double delta = Math.toRadians(point1.longitude-latlng.longitude);
        double dist = Math.acos(Math.sin(ang1)*Math.sin(ang2)+
                Math.cos(ang1)*Math.cos(ang2)*Math.cos(delta))*radius;
        return Double.toString(dist);
    }

}
