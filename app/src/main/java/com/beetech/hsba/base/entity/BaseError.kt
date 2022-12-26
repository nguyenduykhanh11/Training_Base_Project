package com.beetech.hsba.base.entity
import androidx.annotation.StringRes
data class BaseError(var error: String, var code: Int = 1) : Exception(error)