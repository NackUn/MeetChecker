package com.nackun.meetchecker.presentation.checker

import com.nackun.meetchecker.domain.usecase.AddCheckerUseCase
import com.nackun.meetchecker.domain.usecase.GetTodayCheckerUseCase
import com.nackun.meetchecker.presentation.base.BaseMavericksViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CheckerViewModel(
    state: CheckerState
) : BaseMavericksViewModel<CheckerState>(state), KoinComponent {

    private val addCheckerUseCase: AddCheckerUseCase by inject()
    private val getTodayCheckerUseCase: GetTodayCheckerUseCase by inject()

    init {
        viewModelScope.launch {
            setNow()
        }
    }

    private suspend fun setNow() {
        getTodayCheckerUseCase().run {
            updateStatus(dateString = dateString, check = check)
        }
    }

    private fun updateStatus(dateString: String, check: Boolean) = setState {
        copy(dateString = dateString, check = check)
    }

    private fun updateNow(dateString: String) = setState {
        copy(dateString = dateString)
    }

    private fun updateCheck() = setState {
        copy(check = !check)
    }

    fun clickChecker(dateString: String, check: Boolean) {
        updateStatus(dateString, !check)
        viewModelScope.launch {
            addCheckerUseCase(dateString, !check)
            setNow()
        }
    }
}