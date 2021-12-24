package com.nackun.meetchecker.domain.usecase

import com.nackun.meetchecker.domain.model.Checker
import com.nackun.meetchecker.domain.repository.CheckerRepository
import com.nackun.meetchecker.domain.util.getNowSimpleString

class GetTodayCheckerUseCase(
    private val checkerRepository: CheckerRepository
) {
    suspend operator fun invoke(): Checker =
        checkerRepository.getTodayChecker() ?: Checker(getNowSimpleString(), false)
}