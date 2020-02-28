package com.example.juleeyahwright.opensesame.Report

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng

class ReportReference(private val name: String, private val information: String, private val locationInfo: String, private val location: LatLng, private val documentId: String) : Report(name, information, locationInfo, location), Parcelable {

    fun getDocumentId(): String {
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
        parcel.writeString(name)
        parcel.writeString(information)
        parcel.writeString(locationInfo)
        parcel.writeParcelable(location, flags)
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