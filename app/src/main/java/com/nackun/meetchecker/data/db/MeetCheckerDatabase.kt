package com.nackun.meetchecker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CheckerEntity::class], version = 1)
abstract class MeetCheckerDatabase : RoomDatabase() {
    abstract fun checkerDao(): CheckerDao
}