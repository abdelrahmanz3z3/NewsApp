package com.example.domain.common.exepctions

import java.io.IOException

class TimeOutError(private val errorMessage: String) : IOException(errorMessage)