package com.example.juleeyahwright.opensesame.Report;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;
/*
Report: a report that has the user input information about a problem on campus
 */
public class Report {

    public static final String DEFAULT_COLLECTION_PATH = "reports";

    protected static final String INFORMATION_FIELD_NAME = "information";
    protected static final String NAME_FIELD_NAME = "name";
    protected static final String LOCATION_FIELD_NAME = "location";
    protected static final String LOCATION_INFO_FIELD_NAME = "locationInfo";
    protected static final String USERID_FIELD_NAME="uid";

    private LatLng location;
    private String information, name, locationInfo;
    private String collectionPath;
    private Map<String, Object> map;
    private String uid;

    // constructor with default collection path
    public Report(String name, String information, String locationInfo, LatLng location, String uid) {
        this.location = location;
        this.name = name;
        this.information = information;
        this.locationInfo = locationInfo;
        this.collectionPath = DEFAULT_COLLECTION_PATH;
        this.uid = uid;
        this.map = makeMap();
    }

    // constructor with custom collection path
    public Report(String name, String information, String locationInfo, LatLng location, String uid, String collectionPath) {
        this.location = location;
        this.name = name;
        this.information = information;
        this.locationInfo = locationInfo;
        this.collectionPath = collectionPath;
        this.uid = uid;
        this.map = makeMap();
    }

    // updates map with new marker after a report has been made
    private Map<String, Object> makeMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(LOCATION_FIELD_NAME, location);
        map.put(INFORMATION_FIELD_NAME, information);
        map.put(NAME_FIELD_NAME, name);
        map.put(LOCATION_INFO_FIELD_NAME, locationInfo);
        map.put(USERID_FIELD_NAME, uid);
        return map;
    }

    public Map<String, Object> getFirebaseMap() {
        return map;
    }

    public String getCollectionPath() {
        return collectionPath;
    }

    public String getName() {
        return this.name;
    }

    public String getInformation() {
        return information;
    }

    public String getLocationInfo() {
        return locationInfo;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getUID() { return uid; }

}
