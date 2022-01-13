package com.nackun.meetchecker.presentation.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.nackun.meetchecker.presentation.base.BaseMavericksFragment
import com.nackun.meetchecker.presentation.ui.theme.MeetCheckerTheme

class CalendarFragment : BaseMavericksFragment() {
    @Composable
    override fun FragmentView() {
        val calendarViewModel: CalendarViewModel = mavericksViewModel()

        val now by calendarViewModel.collectAsState(CalendarState::now)
        val checkers by calendarViewModel.collectAsState(CalendarState::checkers)

        MeetCheckerTheme {
            CalendarApp(
                localDate = now,
                checkers = checkers
            )
        }
    }
}