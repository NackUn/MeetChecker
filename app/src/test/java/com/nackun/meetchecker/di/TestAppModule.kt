package com.nackun.meetchecker.di

import com.nackun.meetchecker.data.TestCheckerDataSource
import com.nackun.meetchecker.data.TestTimeDataSource
import com.nackun.meetchecker.data.checker.CheckerDataSource
import com.nackun.meetchecker.data.checker.CheckerRepositoryImpl
import com.nackun.meetchecker.data.db.MeetCheckerDatabase
import com.nackun.meetchecker.data.time.TimeDataSource
import com.nackun.meetchecker.data.time.TimeRepositoryImpl
import com.nackun.meetchecker.domain.repository.CheckerRepository
import com.nackun.meetchecker.domain.repository.TimeRepository
import com.nackun.meetchecker.domain.usecase.AddCheckerUseCase
import com.nackun.meetchecker.domain.usecase.GetTodayCheckerUseCase
import com.nackun.meetchecker.domain.usecase.ListenTomorrowUseCase
import org.koin.dsl.module

internal val testAppModule = module {
    // datasource
    single { get<MeetCheckerDatabase>().checkerDao() }
    single<CheckerDataSource> { TestCheckerDataSource() }
    single<TimeDataSource> { TestTimeDataSource() }

    // repository
    single<CheckerRepository> { CheckerRepositoryImpl(get()) }
    single<TimeRepository> { TimeRepositoryImpl(get()) }

    // usecase
    factory { AddCheckerUseCase(get()) }
    factory { GetTodayCheckerUseCase(get(), get()) }
    factory { ListenTomorrowUseCase(get()) }
}
