package com.example.juleeyahwright.opensesame.Report.Update

interface ReportUpdateServiceListener {
    fun reportUpdateSuccess()
    fun reportUpdateFailure(exception: Exception)
}