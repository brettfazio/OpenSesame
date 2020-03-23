package com.example.juleeyahwright.opensesame.AccountModel

import com.example.juleeyahwright.opensesame.Report.Report

interface AccountReportListener {
    fun reportCreateSuccess(report: Report)
    fun reportCreateFailure(report: Report, exception: Exception)
}