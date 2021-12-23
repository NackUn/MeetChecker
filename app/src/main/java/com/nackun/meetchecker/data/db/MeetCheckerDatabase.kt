package com.nackun.meetchecker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CheckerEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class MeetCheckerDatabase : RoomDatabase() {
    abstract fun checkerDao(): CheckerDao
}