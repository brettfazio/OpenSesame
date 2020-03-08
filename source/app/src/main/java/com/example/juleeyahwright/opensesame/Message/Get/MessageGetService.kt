package com.example.juleeyahwright.opensesame.Message.Get

import com.example.juleeyahwright.opensesame.Message.MessageReference
import com.example.juleeyahwright.opensesame.Report.GetSingle.ReportGetSingleService
import com.example.juleeyahwright.opensesame.Report.GetSingle.ReportGetSingleServiceListener
import com.example.juleeyahwright.opensesame.Report.ReportReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class MessageGetService(private val db: FirebaseFirestore, private val listener: MessageGetServiceListener) : ReportGetSingleServiceListener {

    constructor(listener: MessageGetServiceListener) : this(FirebaseFirestore.getInstance(), listener)

    fun getMessages(reportReference: ReportReference) {
        val reportGetSingleService = ReportGetSingleService(this)

        reportGetSingleService.getReport(reportReference)
    }

    private fun internalGetMessages(reference: DocumentReference) {
        val collectionReference = reference.collection(MessageReference.DEFAULT_COLLECTION_PATH)

        collectionReference.get().addOnSuccessListener {
            listener.messageRetrievalSuccess(it, getArrayOfMessages(it))
        }.addOnFailureListener {
            listener.messageRetrievalFailure(it)
        }
    }

    private fun getArrayOfMessages(querySnapshot: QuerySnapshot) : Array<MessageReference?> {
        val result : Array<MessageReference?> = arrayOfNulls<MessageReference>(querySnapshot.size())

        for ((index, documentSnapshot) in querySnapshot.withIndex()) {
            result[index] = MessageReference(documentSnapshot)
        }

        return result
    }

    override fun reportRetrievalSuccess(reference: DocumentSnapshot) {
        internalGetMessages(reference.reference)
    }

    override fun reportRetrievalFailure(exception: Exception) {
        this.listener.messageRetrievalFailure(exception)
    }

}