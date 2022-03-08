package com.nackun.meetchecker.presentation.checker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.fragmentViewModel
import com.nackun.meetchecker.presentation.base.BaseMavericksFragment
import com.nackun.meetchecker.presentation.ui.theme.MeetCheckerTheme

class CheckerFragment : BaseMavericksFragment() {

    private val checkerViewModel: CheckerViewModel by fragmentViewModel()

    @Composable
    override fun FragmentView() {
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
}
