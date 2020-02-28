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
}