package com.nackun.meetchecker.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toStringYMD(): String {
    val format = SimpleDateFormat(FORMAT_Y_M_D, Locale.getDefault())
    return format.format(this)
}

const val FORMAT_Y_M_D = "yyyy-MM-dd"