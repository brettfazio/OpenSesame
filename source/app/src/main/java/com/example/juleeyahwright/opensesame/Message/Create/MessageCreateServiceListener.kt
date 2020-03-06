package com.example.juleeyahwright.opensesame.Message.Create

interface MessageCreateServiceListener {
    fun messageCreateSuccess()
    fun messageCreateFailure(exception: Exception)
}