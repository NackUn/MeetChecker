package com.nackun.meetchecker.data.checker

import com.nackun.meetchecker.data.util.getNowDateTime
import com.nackun.meetchecker.data.util.toModel
import com.nackun.meetchecker.domain.model.Checker
import com.nackun.meetchecker.domain.repository.CheckerRepository

class CheckerRepositoryImpl(
    private val checkerDataSource: CheckerDataSource
) : CheckerRepository {
    override suspend fun getTodayChecker(): Checker =
        checkerDataSource.getTodayChecker().toModel()

    override suspend fun addChecker(check: Boolean) =
        checkerDataSource.addChecker(CheckerDto(getNowDateTime(), check))
}