package com.nackun.meetchecker.data.time

import com.nackun.meetchecker.domain.repository.TimeRepository
import kotlinx.coroutines.flow.Flow

class TimeRepositoryImpl(
    private val timeDataSource: TimeMemDataSource
) : TimeRepository {

    override suspend fun listenTomorrow(): Flow<Unit> =
        timeDataSource.listenTomorrow()
}
