package com.nackun.meetchecker.data.time

import kotlinx.coroutines.flow.Flow

interface TimeDataSource {
    fun listenTomorrow(): Flow<Unit>
}
