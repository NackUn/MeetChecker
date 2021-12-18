package com.nackun.meetchecker.presentation.checker

import android.view.View
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
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
        MeetCheckerTheme {
            Text(
                text = "1",
                fontSize = 100.sp
            )
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