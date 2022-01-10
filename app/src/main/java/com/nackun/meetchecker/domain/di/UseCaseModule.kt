package com.nackun.meetchecker.domain.di

import com.nackun.meetchecker.domain.usecase.AddCheckerUseCase
import com.nackun.meetchecker.domain.usecase.GetMonthCheckersUseCase
import com.nackun.meetchecker.domain.usecase.GetTodayCheckerUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AddCheckerUseCase(get()) }
    factory { GetTodayCheckerUseCase(get()) }
    factory { GetMonthCheckersUseCase(get()) }
}