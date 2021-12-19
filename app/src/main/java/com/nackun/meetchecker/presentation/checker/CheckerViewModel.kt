package com.nackun.meetchecker.presentation.checker

import com.nackun.meetchecker.presentation.base.BaseMavericksViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CheckerViewModel(
    checkerState: CheckerState
) : BaseMavericksViewModel<CheckerState>(checkerState) {

    init {
        viewModelScope.launch {
            while (true) {
                updateCheck()
                delay(2_000)
            }
        }
    }

    private fun updateCheck() = setState {
        copy(check = !check)
    }
}