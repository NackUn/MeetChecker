package com.nackun.meetchecker.presentation.checker

import com.airbnb.mvrx.MavericksState
import com.nackun.meetchecker.di.KoinMavericksViewModelFactory
import com.nackun.meetchecker.domain.usecase.AddCheckerUseCase
import com.nackun.meetchecker.domain.usecase.GetTodayCheckerUseCase
import com.nackun.meetchecker.presentation.base.BaseMavericksViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate

class CheckerViewModel constructor(
    state: CheckerState,
    val addCheckerUseCase: AddCheckerUseCase,
    val getTodayCheckerUseCase: GetTodayCheckerUseCase,
) : BaseMavericksViewModel<CheckerState>(state) {

    init {
        viewModelScope.launch {
            setNow()
            checkToday()
        }
    }

    private suspend fun setNow() {
        getTodayCheckerUseCase().run {
            updateStatus(dateString = dateString, check = check)
        }
    }

    private suspend fun checkToday() {
        var today = LocalDate.now()
        while (true) {
            if (today != LocalDate.now()) {
                today = LocalDate.now()
                setNow()
            }
            delay(100L)
        }
    }

    private fun updateStatus(dateString: String, check: Boolean) = setState {
        copy(today = dateString, check = check)
    }

    // TODO 리펙토링 필요
    fun clickChecker(dateString: String, check: Boolean) {
        updateStatus(dateString, !check)
        viewModelScope.launch {
            addCheckerUseCase(!check)
            setNow()
        }
    }

    companion object :
        KoinMavericksViewModelFactory<CheckerViewModel, CheckerState>(CheckerViewModel::class.java)
}

data class CheckerState(
    val today: String = "",
    val check: Boolean = false
) : MavericksState
