package com.example.juleeyahwright.opensesame.Report.Get

import com.example.juleeyahwright.opensesame.Report.ReportReference
import com.google.firebase.firestore.QuerySnapshot

interface ReportGetServiceListener {
    fun reportRetrievalSuccess(querySnapshot: QuerySnapshot, reportReferences: Array<ReportReference>)
    fun reportRetrievalFailure(exception: Exception)
}