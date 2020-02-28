package com.example.juleeyahwright.opensesame.Report

import com.google.android.gms.maps.model.LatLng

class ReportReference(name: String?, information: String?, locationInfo: String?, location: LatLng?, documentId: String) : Report(name, information, locationInfo, location) {
    var documentId: String? = documentId
}