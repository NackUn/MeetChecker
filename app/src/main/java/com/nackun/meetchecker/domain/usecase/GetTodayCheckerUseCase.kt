package com.nackun.meetchecker.domain.usecase

import com.nackun.meetchecker.domain.model.Checker
import com.nackun.meetchecker.domain.repository.CheckerRepository

class GetTodayCheckerUseCase(
    private val checkerRepository: CheckerRepository
) {
    suspend operator fun invoke(): Checker =
        checkerRepository.getTodayChecker()
}