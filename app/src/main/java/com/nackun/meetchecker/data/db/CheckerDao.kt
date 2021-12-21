package com.nackun.meetchecker.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CheckerDao {
    @Query("SELECT * FROM checker_table WHERE date_string = date('now','localtime') ORDER BY last_modified_date DESC LIMIT 1")
    suspend fun selectTodayChecker(): CheckerEntity?

    @Query("INSERT INTO checker_table ('date_string', 'check') VALUES(:now, :check)")
    suspend fun insertChecker(now: String, check: Boolean)
}