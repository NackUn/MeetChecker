package com.nackun.meetchecker.data.checker

import com.nackun.meetchecker.data.db.CheckerDao
import com.nackun.meetchecker.data.util.getNowString
import com.nackun.meetchecker.data.util.toDto

class CheckerLocalDataSource(
    private val checkerDao: CheckerDao
) : CheckerDataSource {
    override suspend fun getTodayChecker() =
        checkerDao.selectTodayChecker()?.toDto() ?: CheckerDto(getNowString(), false)

    override suspend fun addChecker(checkerDto: CheckerDto) =
        checkerDao.insertChecker(checkerDto.dateString, checkerDto.check)
}