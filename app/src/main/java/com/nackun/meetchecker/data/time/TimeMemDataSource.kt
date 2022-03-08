package com.nackun.meetchecker.data.time

import com.nackun.meetchecker.data.time.TimeDataSource.Companion.simpleDateTimeFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class TimeMemDataSource(
    appScope: CoroutineScope
) : TimeDataSource {

    private val tomorrowFlow = MutableSharedFlow<Unit>()

    init {
        appScope.launch {
            var today = LocalDate.now()
            while (true) {
                if (today != LocalDate.now()) {
                    today = LocalDate.now()
                    tomorrowFlow.emit(Unit)
                }
                delay(100L)
            }
        }
    }

    override fun listenTomorrow(): Flow<Unit> =
        tomorrowFlow

    override fun getNowSimpleString(): String =
        LocalDateTime.now().format(simpleDateTimeFormatter)
}
