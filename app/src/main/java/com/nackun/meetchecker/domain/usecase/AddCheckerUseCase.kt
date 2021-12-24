package com.nackun.meetchecker.domain.usecase

import com.nackun.meetchecker.domain.model.Checker
import com.nackun.meetchecker.domain.repository.CheckerRepository
import com.nackun.meetchecker.domain.util.getNowDetailString

class AddCheckerUseCase(
    private val checkerRepository: CheckerRepository
) {
    suspend operator fun invoke(check: Boolean) =
        checkerRepository.addChecker(Checker(getNowDetailString(), check))
}