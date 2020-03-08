package com.example.juleeyahwright.opensesame.Message.Get

import com.example.juleeyahwright.opensesame.Message.MessageReference
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception

interface MessageGetServiceListener {
    fun messageRetrievalSuccess(querySnapshot: QuerySnapshot, messages: Array<MessageReference?>)
    fun messageRetrievalFailure(exception: Exception)
}