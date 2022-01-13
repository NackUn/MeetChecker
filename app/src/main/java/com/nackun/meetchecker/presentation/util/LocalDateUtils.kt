package com.nackun.meetchecker.presentation.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val simpleDateFormat = "yyyy-MM"
private val simpleDateTimeFormatter = DateTimeFormatter.ofPattern(simpleDateFormat)

fun getNowLocalDate(): LocalDate = LocalDate.now()
fun LocalDate.toMonthString(): String = this.format(simpleDateTimeFormatter)
