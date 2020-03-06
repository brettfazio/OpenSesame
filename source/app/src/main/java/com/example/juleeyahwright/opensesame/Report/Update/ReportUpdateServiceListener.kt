package com.example.juleeyahwright.opensesame.Report.Update

import com.google.firebase.firestore.DocumentSnapshot

interface ReportUpdateServiceListener {
    fun reportUpdateSuccess()
    fun reportUpdateFailure(exception: Exception)
}