package com.nackun.meetchecker.domain.usecase

import com.nackun.meetchecker.domain.model.Checker
import com.nackun.meetchecker.domain.repository.CheckerRepository

class GetMonthCheckersUseCase(
    private val checkerRepository: CheckerRepository
) {
    suspend operator fun invoke(likeMonth: String): List<Checker> =
        checkerRepository.getMonthCheckers(likeMonth) ?: emptyList()
}