package com.nackun.meetchecker.presentation.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.nackun.meetchecker.presentation.base.BaseMavericksFragment
import com.nackun.meetchecker.presentation.ui.theme.MeetCheckerTheme
import java.time.LocalDate

class CalendarFragment : BaseMavericksFragment() {
    @Composable
    override fun FragmentView() {
        val calendarViewModel: CalendarViewModel = mavericksViewModel()

        val now by calendarViewModel.collectAsState(CalendarState::now)

        MeetCheckerTheme {
            CalendarApp(localDate = now)
        }
    }

    @Preview(
        showBackground = true
    )
    @Composable
    override fun FragmentPreView() {
        MeetCheckerTheme {
            CalendarApp(localDate = LocalDate.now())
        }
    }
}