package com.example.juleeyahwright.opensesame.Report

import com.google.firebase.firestore.QuerySnapshot

interface ReportServiceListener {
    fun reportRetrievalSuccess(querySnapshot: QuerySnapshot, reportReferences: Array<ReportReference>)
    fun reportRetrievalFailure(exception: Exception)
}