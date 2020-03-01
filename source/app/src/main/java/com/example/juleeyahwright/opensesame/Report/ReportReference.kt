package com.example.juleeyahwright.opensesame.Report

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng

class ReportReference(private val reportName: String?, private val reportInfo: String?, private val reportLocInfo: String?, private val reportLocation: LatLng?, private val documentId: String?) :
        Report(reportName, reportInfo, reportLocInfo, reportLocation), Parcelable {

    fun getDocumentId(): String? {
        return documentId
    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(LatLng::class.java.classLoader),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(reportName)
        parcel.writeString(reportInfo)
        parcel.writeString(reportLocInfo)
        parcel.writeParcelable(reportLocation, flags)
        parcel.writeString(documentId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReportReference> {
        override fun createFromParcel(parcel: Parcel): ReportReference {
            return ReportReference(parcel)
        }

        override fun newArray(size: Int): Array<ReportReference?> {
            return arrayOfNulls(size)
        }
    }

}