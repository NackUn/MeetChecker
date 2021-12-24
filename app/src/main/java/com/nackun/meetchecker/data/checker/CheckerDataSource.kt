package com.nackun.meetchecker.data.checker

interface CheckerDataSource {
    suspend fun getTodayChecker(): CheckerDto?
    suspend fun addChecker(checkerDto: CheckerDto)
}