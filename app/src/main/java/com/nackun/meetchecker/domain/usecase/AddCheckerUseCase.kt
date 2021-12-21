package com.nackun.meetchecker.domain.usecase

import com.nackun.meetchecker.domain.model.Checker
import com.nackun.meetchecker.domain.repository.CheckerRepository

class AddCheckerUseCase(
    private val checkerRepository: CheckerRepository
) {
    suspend operator fun invoke(dateString: String, check: Boolean) =
        checkerRepository.addChecker(Checker(dateString, check))
}