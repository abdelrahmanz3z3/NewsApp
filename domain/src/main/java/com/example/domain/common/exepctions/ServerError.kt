package com.example.domain.common.exepctions

class ServerError(private val serverMessage: String) : Exception(serverMessage)