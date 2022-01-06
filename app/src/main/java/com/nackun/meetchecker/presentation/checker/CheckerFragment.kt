package com.nackun.meetchecker.presentation.checker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.nackun.meetchecker.presentation.base.BaseMavericksFragment
import com.nackun.meetchecker.presentation.ui.theme.MeetCheckerTheme

class CheckerFragment : BaseMavericksFragment() {
    @Composable
    override fun FragmentView() {
        val checkerViewModel: CheckerViewModel = mavericksViewModel()

        val today by checkerViewModel.collectAsState(CheckerState::today)
        val check by checkerViewModel.collectAsState(CheckerState::check)

        MeetCheckerTheme {
            CheckerApp(
                today = today,
                check = check,
                checkClick = {
                    checkerViewModel.clickChecker(today, check)
                }
            )
        }
    }

    @Preview(
        showBackground = true
    )
    @Composable
    override fun FragmentPreView() {
        MeetCheckerTheme {
            CheckerApp(
                today = "2021-12-22",
                check = true,
                checkClick = {}
            )
        }
    }
}