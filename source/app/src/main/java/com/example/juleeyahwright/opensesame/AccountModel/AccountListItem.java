package com.example.juleeyahwright.opensesame.AccountModel;

import com.example.juleeyahwright.opensesame.ReportList.ReportListItem;
import com.google.android.gms.maps.model.LatLng;

public class AccountListItem extends ReportListItem {

    private String reportName;
    private String reportLocation;
    private String reportDistance = "Distance Coming Soon.";
    private String reportDescription;

    private LatLng reportLatLng;

    public AccountListItem(String name, String loc, LatLng latlng, String desc){
        super(name, loc, latlng, desc);
        this.reportName = name;
        this.reportLocation = loc;
        this.reportLatLng = latlng;
        this.reportDescription = desc;
    }

}
