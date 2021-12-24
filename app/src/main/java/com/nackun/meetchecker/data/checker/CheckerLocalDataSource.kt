package com.nackun.meetchecker.data.checker

import com.nackun.meetchecker.data.db.CheckerDao
import com.nackun.meetchecker.data.util.toDto
import com.nackun.meetchecker.data.util.toEntity

class CheckerLocalDataSource(
    private val checkerDao: CheckerDao
) : CheckerDataSource {
    override suspend fun getTodayChecker() =
        checkerDao.selectTodayChecker()?.toDto()

    override suspend fun addChecker(checkerDto: CheckerDto) =
        checkerDao.insertChecker(checkerDto.toEntity())
}