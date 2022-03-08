package com.nackun.meetchecker.data.time

import kotlinx.coroutines.flow.Flow
import java.time.format.DateTimeFormatter

interface TimeDataSource {
    fun listenTomorrow(): Flow<Unit>
    fun getNowSimpleString(): String

    companion object {
        private const val simpleDateFormat = "yyyy-MM-dd"
        val simpleDateTimeFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern(simpleDateFormat)
    }
}
