package com.beetech.hsba.base.entity

data class BaseError(var error: String, var code: Int = 1) : Exception(error)