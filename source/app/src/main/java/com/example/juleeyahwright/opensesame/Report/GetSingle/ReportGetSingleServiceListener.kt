package com.example.juleeyahwright.opensesame.Report.GetSingle

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot

interface ReportGetSingleServiceListener {
    fun reportRetrievalSuccess(reference: DocumentSnapshot)
    fun reportRetrievalFailure(exception: Exception)
}