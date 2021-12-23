package com.nackun.meetchecker.data.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

private const val dateFormat = "yyyy-MM-dd kk:mm:ss SSS"
private const val dateStringFormat = "yyyy-MM-dd"
private val dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)

fun getNowString(): String = getNowLocalDateTime().format(dateTimeFormatter)
fun getNowDateTime(): Date = getNowLocalDateTime().toDate()
fun Date.toDateString(): String =
    SimpleDateFormat(dateStringFormat, Locale.getDefault()).format(this)

fun String.toDate(): Date =
    LocalDateTime.parse(this, dateTimeFormatter).toDate()

private fun getNowLocalDateTime(): LocalDateTime = LocalDateTime.now()
private fun LocalDateTime.toDate(): Date = Date.from(atZone(ZoneId.systemDefault()).toInstant())