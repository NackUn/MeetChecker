package com.nackun.meetchecker.domain.usecase

import com.nackun.meetchecker.domain.repository.CheckerRepository

class AddCheckerUseCase(
    private val checkerRepository: CheckerRepository
) {
    suspend operator fun invoke(check: Boolean) =
        checkerRepository.addChecker(check)
}