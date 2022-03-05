package com.nackun.meetchecker.domain.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val simpleDateFormat = "yyyy-MM-dd"
private const val detailDateFormat = "yyyy-MM-dd kk:mm:ss SSS"
private val simpleDateTimeFormatter = DateTimeFormatter.ofPattern(simpleDateFormat)
private val detailDateTimeFormatter = DateTimeFormatter.ofPattern(detailDateFormat)

fun getNowSimpleString(): String = getNowLocalDateTime().format(simpleDateTimeFormatter)
fun getNowDetailString(): String = getNowLocalDateTime().format(detailDateTimeFormatter)

fun LocalDateTime.toSimpleString(): String = this.format(simpleDateTimeFormatter)

private fun getNowLocalDateTime(): LocalDateTime = LocalDateTime.now()
