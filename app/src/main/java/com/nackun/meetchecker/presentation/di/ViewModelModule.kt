package com.nackun.meetchecker.presentation.di

import com.nackun.meetchecker.presentation.checker.CheckerState
import com.nackun.meetchecker.presentation.checker.CheckerViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { (state: CheckerState) ->
        CheckerViewModel(
            state,
            get(),
            get(),
            get(),
        )
    }
}
