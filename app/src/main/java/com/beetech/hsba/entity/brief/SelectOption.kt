package com.beetech.hsba.entity.brief

data class SelectOption(
    val id: Int,
    val title: String,
    var isSelected: Boolean = false
)
