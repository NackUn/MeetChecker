package com.nackun.meetchecker.data.di

import com.nackun.meetchecker.data.checker.CheckerDataSource
import com.nackun.meetchecker.data.checker.CheckerLocalDataSource
import com.nackun.meetchecker.data.db.MeetCheckerDatabase
import org.koin.dsl.module

val localDataSourceModule = module {
    single { get<MeetCheckerDatabase>().checkerDao() }
    single<CheckerDataSource> { CheckerLocalDataSource(get()) }
}