package com.nackun.meetchecker.di

import com.nackun.meetchecker.data.TestCheckerDataSource
import com.nackun.meetchecker.data.checker.CheckerDataSource
import com.nackun.meetchecker.data.checker.CheckerRepositoryImpl
import com.nackun.meetchecker.data.db.MeetCheckerDatabase
import com.nackun.meetchecker.domain.repository.CheckerRepository
import com.nackun.meetchecker.domain.usecase.AddCheckerUseCase
import com.nackun.meetchecker.domain.usecase.GetTodayCheckerUseCase
import org.koin.dsl.module

internal val testAppModule = module {
    // datasource
    single { get<MeetCheckerDatabase>().checkerDao() }
    single<CheckerDataSource> { TestCheckerDataSource() }

    // repository
    single<CheckerRepository> { CheckerRepositoryImpl(get()) }

    // usecase
    factory { AddCheckerUseCase(get()) }
    factory { GetTodayCheckerUseCase(get()) }
}
