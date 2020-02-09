package com.example.juleeyahwright.opensesame;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

public class Report {

    private LatLng location;
    private String information, name;

    public Report(String name, String information, LatLng location) {
        this.location = location;
        this.name = name;
        this.information = information;
    }

    public Map<String, Object> getFirebaseMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("location", location);
        map.put("information", information);
        map.put("name", name);

        return map;
    }

}
