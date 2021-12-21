package com.nackun.meetchecker.presentation.checker

import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.nackun.meetchecker.presentation.base.BaseFragment
import com.nackun.meetchecker.ui.theme.MeetCheckerTheme

class CheckerFragment : BaseFragment() {

    override fun initComposeView(): View =
        ComposeView(requireContext()).apply {
            setContent {
                FragmentView()
            }
        }

    @Composable
    override fun FragmentView() {
        val checkerViewModel: CheckerViewModel = mavericksViewModel()

        val dateString by checkerViewModel.collectAsState(CheckerState::dateString)
        val check by checkerViewModel.collectAsState(CheckerState::check)

        MeetCheckerTheme {
            Row {
                Text(
                    text = dateString,
                    fontSize = 50.sp,
                    modifier = Modifier.clickable {
                        checkerViewModel.clickChecker(dateString, check)
                    }
                )
                Text(
                    text = check.toString(),
                    fontSize = 50.sp,
                    modifier = Modifier.clickable {
                        checkerViewModel.clickChecker(dateString, check)
                    }
                )
            }
        }
    }

    @Preview(
        showBackground = true
    )
    @Composable
    override fun FragmentPreView() {
        FragmentView()
    }
}