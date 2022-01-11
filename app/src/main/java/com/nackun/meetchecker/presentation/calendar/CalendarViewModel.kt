package com.nackun.meetchecker.presentation.calendar

import android.util.Log
import com.nackun.meetchecker.domain.usecase.GetMonthCheckersUseCase
import com.nackun.meetchecker.presentation.base.BaseMavericksViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CalendarViewModel(
    state: CalendarState
) : BaseMavericksViewModel<CalendarState>(state), KoinComponent {

    private val getMonthCheckersUseCase: GetMonthCheckersUseCase by inject()

    init {
        // 해당 월의 Checker 리스트를 전부 가지고와서 보여줍니다.
        viewModelScope.launch {
            getMonthCheckersUseCase("2021-12").also {
                Log.d("aa12", "it : ${it.toString()}")
            }
        }
    }
}