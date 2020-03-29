package com.example.juleeyahwright.opensesame.AccountModel;

import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.ReportList.ReportListItem;
import com.google.android.gms.maps.model.LatLng;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class AccountListItem extends ReportListItem {

    private ReportReference reportReference;

    public AccountListItem(ReportReference reportReference) {
        super(reportReference.getName(),
                reportReference.getLocationInfo(),
                reportReference.getLocation(),
                reportReference.getInformation(),
                reportReference.getUID());
        this.reportReference = reportReference;
    }

    private String calculateReportDistance(LatLng latlng){
        // Center of UCF, temporarily
        LatLng point1 = new LatLng(28.6024, -81.2001);
        double radius = 6371e3;
        double ang1 = Math.toRadians(point1.latitude);
        double ang2 = Math.toRadians(latlng.latitude);
        double delta = Math.toRadians(point1.longitude-latlng.longitude);
        double dist = Math.acos(Math.sin(ang1)*Math.sin(ang2)+
                Math.cos(ang1)*Math.cos(ang2)*Math.cos(delta))*radius;
        DecimalFormat df1 = new DecimalFormat("#");
        df1.setRoundingMode(RoundingMode.FLOOR);
        DecimalFormat df2 = new DecimalFormat("#.#");
        df2.setRoundingMode(RoundingMode.FLOOR);
        if (dist < 1000.0)
            return df1.format(dist) + "m";
        else if (dist > 1000.0 && dist < 10000.0)
            return df2.format(dist/1000.0) + "km";
        else
            return df1.format(dist/1000.0) + "km";
    }

    public ReportReference getReportReference() {
        return reportReference;
    }

}
