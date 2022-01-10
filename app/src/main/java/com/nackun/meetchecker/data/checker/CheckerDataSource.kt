package com.nackun.meetchecker.data.checker

interface CheckerDataSource {
    suspend fun getTodayChecker(): CheckerDto?
    suspend fun addChecker(checkerDto: CheckerDto)
    suspend fun getMonthCheckers(likeMonth: String): List<CheckerDto>?
}