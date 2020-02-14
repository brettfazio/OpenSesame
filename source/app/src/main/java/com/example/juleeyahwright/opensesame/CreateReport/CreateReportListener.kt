package com.example.juleeyahwright.opensesame.CreateReport

interface CreateReportListener {
    fun reportCreateSuccess(report: Report)
    fun reportCreateFailure(report: Report, exception: Exception)
}