package com.nackun.meetchecker.presentation.checker

import com.airbnb.mvrx.MavericksState
import com.nackun.meetchecker.domain.usecase.AddCheckerUseCase
import com.nackun.meetchecker.domain.usecase.GetTodayCheckerUseCase
import com.nackun.meetchecker.presentation.base.BaseMavericksViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate

class CheckerViewModel(
    state: CheckerState
) : BaseMavericksViewModel<CheckerState>(state), KoinComponent {

    private val addCheckerUseCase: AddCheckerUseCase by inject()
    private val getTodayCheckerUseCase: GetTodayCheckerUseCase by inject()

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

    fun clickChecker(dateString: String, check: Boolean) {
        updateStatus(dateString, !check)
        viewModelScope.launch {
            addCheckerUseCase(!check)
            setNow()
        }
    }
}

data class CheckerState(
    val today: String = "",
    val check: Boolean = false
) : MavericksState
