package com.example.juleeyahwright.opensesame.CreateReport;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

public class Report {

    public static final String DEFAULT_COLLECTION_PATH = "reports";

    private LatLng location;
    private String information, name;
    private String collectionPath;
    private Map<String, Object> map;

    public Report(String name, String information, LatLng location) {
        this.location = location;
        this.name = name;
        this.information = information;
        this.collectionPath = DEFAULT_COLLECTION_PATH;
        this.map = makeMap(name, information, location);
    }

    public Report(String name, String information, LatLng location, String collectionPath) {
        this.location = location;
        this.name = name;
        this.information = information;
        this.collectionPath = collectionPath;
        this.map = makeMap(name, information, location);
    }

    private Map<String, Object> makeMap(String name, String information, LatLng location) {
        Map<String, Object> map = new HashMap<>();
        map.put("location", location);
        map.put("information", information);
        map.put("name", name);

        return map;
    }

    public Map<String, Object> getFirebaseMap() {
        return map;
    }

    public String getCollectionPath() {
        return collectionPath;
    }

}
