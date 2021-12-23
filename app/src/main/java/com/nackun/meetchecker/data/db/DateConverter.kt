package com.nackun.meetchecker.data.db

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun fromLong(value: Long?): Date? =
        value?.let { Date(it) }

    @TypeConverter
    fun dateToLong(date: Date?): Long? =
        date?.time
}