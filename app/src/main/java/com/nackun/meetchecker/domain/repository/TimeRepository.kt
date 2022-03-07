package com.nackun.meetchecker.domain.repository

import kotlinx.coroutines.flow.Flow

interface TimeRepository {
    suspend fun listenTomorrow(): Flow<Unit>
}
