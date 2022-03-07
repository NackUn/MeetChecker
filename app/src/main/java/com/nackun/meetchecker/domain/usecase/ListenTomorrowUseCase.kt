package com.nackun.meetchecker.domain.usecase

import com.nackun.meetchecker.domain.repository.TimeRepository
import kotlinx.coroutines.flow.Flow

class ListenTomorrowUseCase(
    private val timeRepository: TimeRepository
) {
    suspend operator fun invoke(): Flow<Unit> =
        timeRepository.listenTomorrow()
}
