package com.nackun.meetchecker.domain.repository

import com.nackun.meetchecker.domain.model.Checker

interface CheckerRepository {
    suspend fun getTodayChecker(): Checker?
    suspend fun addChecker(checker: Checker)
    suspend fun getMonthCheckers(likeMonth: String): List<Checker>?
}