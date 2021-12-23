package com.nackun.meetchecker.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "checker_table")
data class CheckerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "date_string")
    val dateString: String,

    @ColumnInfo(name = "date")
    val date: Date?,

    @ColumnInfo(name = "check")
    val check: Boolean = false,

    @ColumnInfo(name = "last_modified_date")
    val lastModifiedDate: String,

    @ColumnInfo(name = "created_date")
    val createdDate: String
)
