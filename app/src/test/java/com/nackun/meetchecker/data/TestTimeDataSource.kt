package com.nackun.meetchecker.data

import com.nackun.meetchecker.data.time.TimeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.LocalDateTime

class TestTimeDataSource : TimeDataSource {

    private val tomorrowFlow = MutableSharedFlow<Unit>()
    private var today = LocalDateTime.now()

    override fun listenTomorrow(): Flow<Unit> =
        tomorrowFlow

    override fun getNowSimpleString(): String =
        today.format(TimeDataSource.simpleDateTimeFormatter)

    internal suspend fun setTomorrow() {
        today = today.plusDays(1)
        tomorrowFlow.emit(Unit)
    }
}
