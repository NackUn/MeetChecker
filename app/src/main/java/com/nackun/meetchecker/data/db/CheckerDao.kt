package com.nackun.meetchecker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CheckerDao {
    @Query("SELECT * FROM checker_table WHERE date_string = DATE('now','localtime') ORDER BY last_modified_date DESC LIMIT 1")
    suspend fun selectTodayChecker(): CheckerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChecker(checkerEntity: CheckerEntity)
}