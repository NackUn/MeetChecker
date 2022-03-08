package com.nackun.meetchecker.data.di

import com.nackun.meetchecker.data.checker.CheckerDataSource
import com.nackun.meetchecker.data.checker.CheckerLocalDataSource
import com.nackun.meetchecker.data.db.MeetCheckerDatabase
import com.nackun.meetchecker.data.time.TimeDataSource
import com.nackun.meetchecker.data.time.TimeMemDataSource
import com.nackun.meetchecker.di.Qualifier
import org.koin.dsl.module

val dataSourceModule = module {
    single { get<MeetCheckerDatabase>().checkerDao() }
    single<CheckerDataSource> { CheckerLocalDataSource(get()) }

    single<TimeDataSource> { TimeMemDataSource(get(Qualifier.APP_SCOPE)) }
}
