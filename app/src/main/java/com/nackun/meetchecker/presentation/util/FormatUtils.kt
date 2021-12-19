package com.nackun.meetchecker.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toStringYMD(): String =
    SimpleDateFormat(FORMAT_Y_M_D, Locale.getDefault()).format(this)

private const val FORMAT_Y_M_D = "yyyy-MM-dd"