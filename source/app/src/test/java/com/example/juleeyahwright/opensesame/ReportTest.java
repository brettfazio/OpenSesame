package com.example.juleeyahwright.opensesame;

import com.example.juleeyahwright.opensesame.Report.Report;
import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;

import java.util.Map;

public class ReportTest {

    /*
    Testing: When fields are input with dummy text, the text is stored in a report
    Pass Criteria: Report fields match input
     */
    @Test
    public void firebaseMap_reportTest() {
        Report report = new Report("dummy", "this is a dummy report", "basement", new LatLng(0.0, 0.0));

        Map<String, Object> map = report.getFirebaseMap();

        assert (map.get("name") != null);
        assert (map.get("information") != null);
        assert (map.get("locationInfo") != null);
        assert (map.get("location") != null);
        assert (map.size() == 4);

        assert (map.get("name").equals("dummy"));
        assert (map.get("information").equals("this is a dummy report"));
        assert (map.get("location").equals(new LatLng(0.0, 0.0)));
        assert (map.get("locationInfo").equals("basement"));
    }

    /*
    Testing: Report is matched with current location
    Pass Criteria: Report matches location
    */
    @Test
    public void getCollectionPath_test() {
        Report report = new Report("dummy", "this is a dummy report", "basement", new LatLng(0.0, 0.0));

        assert (report.getCollectionPath().equals(Report.DEFAULT_COLLECTION_PATH));
    }

    /*
    Testing: Report is matched with a custom location
    Pass Criteria: Report matches custom location
    */
    @Test
    public void customGetCollectionPath_test() {
        Report report = new Report("dummy", "this is a dummy report", "basement", new LatLng(0.0, 0.0), "custom");

        assert (report.getCollectionPath().equals("custom"));
    }

    /*
    Testing: getName getter returns proper value
    Pass Criteria: getName matches init value
    */
    @Test
    public void getName_test() {
        Report report = new Report("dummy", "this is a dummy report", "basement", new LatLng(0.0, 0.0), "custom");

        assert (report.getName().equals("dummy"));
    }

    /*
    Testing: getInformation getter returns proper value
    Pass Criteria: getInformation matches init value
    */
    @Test
    public void getInformation_test() {
        Report report = new Report("dummy", "this is a dummy report", "basement", new LatLng(0.0, 0.0), "custom");

        assert (report.getInformation().equals("this is a dummy report"));
    }

    /*
    Testing: getLocationInfo getter returns proper value
    Pass Criteria: getLocationInfo matches init value
    */
    @Test
    public void getLocationInfo_test() {
        Report report = new Report("dummy", "this is a dummy report", "basement", new LatLng(0.0, 0.0), "custom");

        assert (report.getLocationInfo().equals("basement"));
    }

    /*
    Testing: getLocation getter returns proper value
    Pass Criteria: getLocation matches init value
    */
    @Test
    public void getLocation_test() {
        Report report = new Report("dummy", "this is a dummy report", "basement", new LatLng(0.0, 0.0), "custom");

        assert (report.getLocation().equals(new LatLng(0.0, 0.0)));
    }
}
