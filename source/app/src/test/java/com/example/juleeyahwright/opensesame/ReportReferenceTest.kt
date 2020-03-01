package com.example.juleeyahwright.opensesame

import com.example.juleeyahwright.opensesame.Report.ReportReference
import com.google.android.gms.maps.model.LatLng
import org.junit.Test

class ReportReferenceTest {
    @Test
    fun customID_test() {
        val reference: ReportReference = ReportReference("report", "this is a report", "basement", LatLng(0.0, 0.0), "eFz687FFHDHD")
        assert(reference.getDocumentId() == "eFz687FFHDHD")
    }

    @Test
    fun getFirebaseMap_test() {
        val report : ReportReference = ReportReference("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "eFz687FFHDHD")

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
    Testing: getName getter returns proper value
    Pass Criteria: getName matches init value
    */
    @Test
    fun getName_test() {
        val report = ReportReference("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "eFz687FFHDHD")
        assert(report.name == "dummy")
    }

    /*
    Testing: getInformation getter returns proper value
    Pass Criteria: getInformation matches init value
    */
    @Test
    fun getInformation_test() {
        val report = ReportReference("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "eFz687FFHDHD")
        assert(report.information == "this is a dummy report")
    }

    /*
    Testing: getLocationInfo getter returns proper value
    Pass Criteria: getLocationInfo matches init value
    */
    @Test
    fun getLocationInfo_test() {
        val report = ReportReference("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "eFz687FFHDHD")
        assert(report.locationInfo == "basement")
    }

    /*
    Testing: getLocation getter returns proper value
    Pass Criteria: getLocation matches init value
    */
    @Test
    fun getLocation_test() {
        val report = ReportReference("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "eFz687FFHDHD")
        assert(report.location == LatLng(0.0, 0.0))
    }
}