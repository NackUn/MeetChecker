package com.nackun.meetchecker.data.di

import com.nackun.meetchecker.data.checker.CheckerRepositoryImpl
import com.nackun.meetchecker.data.time.TimeRepositoryImpl
import com.nackun.meetchecker.domain.repository.CheckerRepository
import com.nackun.meetchecker.domain.repository.TimeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CheckerRepository> { CheckerRepositoryImpl(get()) }
    single<TimeRepository> { TimeRepositoryImpl(get()) }
}
