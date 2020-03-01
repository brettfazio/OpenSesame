package com.example.juleeyahwright.opensesame.ReportDetail

import com.example.juleeyahwright.opensesame.Report.ReportReference
import com.google.android.gms.maps.model.LatLng
import org.junit.Test

class ReportDetailControllerTest {
    private var report: ReportReference = ReportReference("dummy",
            "this is a dummy report",
            "basement",
            LatLng(0.0, 0.0),
            "eFz687FFHDHD")

    @Test
    fun reportName_test() {
        val controller = ReportDetailController(report)
        assert(controller.reportName == "dummy")
    }

    @Test
    fun reportInformation_test() {
        val controller = ReportDetailController(report)
        assert(controller.reportInformation == "this is a dummy report")
    }

    @Test
    fun reportLocationInfo_test() {
        val controller = ReportDetailController(report)
        assert(controller.reportLocationInfo == "basement")
    }
}