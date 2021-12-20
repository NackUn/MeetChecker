package com.nackun.meetchecker.presentation.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getNowString(): String =
    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"))