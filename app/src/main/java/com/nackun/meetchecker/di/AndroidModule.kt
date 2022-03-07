package com.nackun.meetchecker.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val androidModule = module {
    single(Qualifier.APP_SCOPE) {
        CoroutineScope(SupervisorJob() + Dispatchers.Main)
    }
}
