package com.example.juleeyahwright.opensesame

import com.example.juleeyahwright.opensesame.Report.Report
import com.google.android.gms.maps.model.LatLng
import org.junit.Test

class ReportTest {
    /*
    Testing: When fields are input with dummy text, the text is stored in a report
    Pass Criteria: Report fields match input
     */
    @Test
    fun firebaseMap_reportTest() {
        val report = Report("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0))
        val map = report.firebaseMap
        assert(map["name"] != null)
        assert(map["information"] != null)
        assert(map["locationInfo"] != null)
        assert(map["location"] != null)
        assert(map.size == 4)
        assert(map["name"] == "dummy")
        assert(map["information"] == "this is a dummy report")
        assert(map["location"] == LatLng(0.0, 0.0))
        assert(map["locationInfo"] == "basement")
    }

    /*
    Testing: Report is matched with current location
    Pass Criteria: Report matches location
    */
    @Test
    fun collectionPath_test() {
        val report = Report("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0))
        assert(report.collectionPath == Report.DEFAULT_COLLECTION_PATH)
    }

    /*
    Testing: Report is matched with a custom location
    Pass Criteria: Report matches custom location
    */
    @Test
    fun customGetCollectionPath_test() {
        val report = Report("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "custom")
        assert(report.collectionPath == "custom")
    }

    /*
    Testing: getName getter returns proper value
    Pass Criteria: getName matches init value
    */
    @Test
    fun name_test() {
        val report = Report("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "custom")
        assert(report.name == "dummy")
    }

    /*
    Testing: getInformation getter returns proper value
    Pass Criteria: getInformation matches init value
    */
    @Test
    fun information_test() {
        val report = Report("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "custom")
        assert(report.information == "this is a dummy report")
    }

    /*
    Testing: getLocationInfo getter returns proper value
    Pass Criteria: getLocationInfo matches init value
    */
    @Test
    fun locationInfo_test() {
        val report = Report("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "custom")
        assert(report.locationInfo == "basement")
    }

    /*
    Testing: getLocation getter returns proper value
    Pass Criteria: getLocation matches init value
    */
    @Test
    fun location_test() {
        val report = Report("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "custom")
        assert(report.location == LatLng(0.0, 0.0))
    }
}