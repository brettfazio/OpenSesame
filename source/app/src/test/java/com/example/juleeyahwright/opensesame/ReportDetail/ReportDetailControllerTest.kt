package com.example.juleeyahwright.opensesame.ReportDetail

import android.content.Context
import com.example.juleeyahwright.opensesame.Report.ReportReference
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ReportDetailControllerTest {

    @Mock
    private val context: Context? = null

    @Before
    fun setUp()
    {
        MockitoAnnotations.initMocks(this)
    }

    private var report: ReportReference = ReportReference("dummy",
            "this is a dummy report",
            "basement",
            LatLng(0.0, 0.0),
            "eFz687FFHDHD")

    @Test
    fun reportName_test() {
        val controller = ReportDetailController(context, report)
        assert(controller.reportName == "dummy")
    }

    @Test
    fun reportInformation_test() {
        val controller = ReportDetailController(context, report)
        assert(controller.reportInformation == "this is a dummy report")
    }

    @Test
    fun reportLocationInfo_test() {
        val controller = ReportDetailController(context, report)
        assert(controller.reportLocationInfo == "basement")
    }
}