package com.nackun.meetchecker.data.di

import androidx.room.Room
import com.nackun.meetchecker.data.db.MeetCheckerDatabase
import org.koin.dsl.module

private const val DB_NAME = "meet_checker"

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), MeetCheckerDatabase::class.java, DB_NAME).build()
    }
}