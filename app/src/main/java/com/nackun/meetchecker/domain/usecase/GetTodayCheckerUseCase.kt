package com.nackun.meetchecker.domain.usecase

import com.nackun.meetchecker.domain.model.Checker
import com.nackun.meetchecker.domain.repository.CheckerRepository
import com.nackun.meetchecker.domain.repository.TimeRepository

class GetTodayCheckerUseCase(
    private val checkerRepository: CheckerRepository,
    private val timeRepository: TimeRepository,
) {
    suspend operator fun invoke(): Checker =
        checkerRepository.getTodayChecker() ?: Checker(timeRepository.getNowSimpleString(), false)
}
