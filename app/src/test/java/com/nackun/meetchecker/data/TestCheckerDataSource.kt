package com.nackun.meetchecker.data

import com.nackun.meetchecker.data.checker.CheckerDataSource
import com.nackun.meetchecker.data.checker.CheckerDto
import com.nackun.meetchecker.data.db.CheckerEntity
import com.nackun.meetchecker.data.util.getNowString
import com.nackun.meetchecker.data.util.toDto
import com.nackun.meetchecker.data.util.toEntity

internal class TestCheckerDataSource : CheckerDataSource {
    private val checkerDtoList: MutableList<CheckerEntity> = mutableListOf()

    override suspend fun getTodayChecker(): CheckerDto? =
        checkerDtoList.filter {
            it.dateString == getNowString()
        }.ifEmpty {
            null
        }?.last()?.toDto()

    override suspend fun addChecker(checkerDto: CheckerDto) {
        checkerDtoList.add(checkerDto.toEntity())
    }

    override suspend fun getMonthCheckers(likeMonth: String): List<CheckerDto>? =
        checkerDtoList.filter {
            it.dateString.contains(likeMonth)
        }.ifEmpty {
            null
        }?.map {
            it.toDto()
        }
}
