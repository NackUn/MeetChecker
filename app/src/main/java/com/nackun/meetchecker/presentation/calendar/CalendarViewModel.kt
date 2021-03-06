package com.nackun.meetchecker.presentation.calendar

import com.airbnb.mvrx.MavericksState
import com.nackun.meetchecker.domain.usecase.GetMonthCheckersUseCase
import com.nackun.meetchecker.presentation.base.BaseMavericksViewModel
import com.nackun.meetchecker.presentation.model.Checker
import com.nackun.meetchecker.presentation.model.toModel
import com.nackun.meetchecker.presentation.util.getNowLocalDate
import com.nackun.meetchecker.presentation.util.toMonthString
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate

class CalendarViewModel(
    private val state: CalendarState
) : BaseMavericksViewModel<CalendarState>(state), KoinComponent {

    private val getMonthCheckersUseCase: GetMonthCheckersUseCase by inject()

    init {
        setThisMonthCheckers()
    }

    fun setThisMonthCheckers() {
        setStateCheckers(state.now.toMonthString())
    }

    private fun setStateCheckers(likeMonth: String) {
        viewModelScope.launch {
            getMonthCheckersUseCase(likeMonth).also {
                setState {
                    copy(
                        checkers = it.map { checker ->
                            checker.toModel()
                        }
                    )
                }
            }
        }
    }
}

data class CalendarState(
    val now: LocalDate = getNowLocalDate(),
    val checkers: List<Checker> = emptyList(),
) : MavericksState
