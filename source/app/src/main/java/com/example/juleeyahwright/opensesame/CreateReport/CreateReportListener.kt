package com.example.juleeyahwright.opensesame.CreateReport

import com.example.juleeyahwright.opensesame.Report.Report

interface CreateReportListener {
    fun reportCreateSuccess(report: Report)
    fun reportCreateFailure(report: Report, exception: Exception)
}