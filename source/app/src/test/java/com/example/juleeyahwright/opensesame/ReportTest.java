package com.example.juleeyahwright.opensesame;

import com.example.juleeyahwright.opensesame.CreateReport.Report;
import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;

import java.util.Map;

public class ReportTest {

    @Test
    public void firebaseMap_reportTest() {
        Report report = new Report("dummy", "this is a dummy report", new LatLng(0.0,0.0));

        Map<String, Object> map = report.getFirebaseMap();

        assert (map.get("name") != null);
        assert (map.get("information") != null);
        assert (map.get("location") != null);
        assert (map.size() == 3);

        assert(map.get("name").equals("dummy"));
        assert(map.get("information").equals("this is a dummy report"));
        assert(map.get("location").equals(new LatLng(0.0,0.0)));
    }

    @Test
    public void getCollectionPath_test() {
        Report report = new Report("dummy", "this is a dummy report", new LatLng(0.0,0.0));

        assert (report.getCollectionPath().equals(Report.DEFAULT_COLLECTION_PATH));
    }

    @Test
    public void customGetCollectionPath_test() {
        Report report = new Report("dummy", "this is a dummy report", new LatLng(0.0,0.0), "custom");

        assert (report.getCollectionPath().equals("custom"));
    }
}
