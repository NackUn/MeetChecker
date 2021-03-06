package com.nackun.meetchecker.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView

abstract class BaseMavericksFragment : Fragment(), MavericksView {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return initComposeView()
    }

    override fun invalidate() = Unit

    private fun initComposeView(): View =
        ComposeView(requireContext()).apply {
            setContent {
                FragmentView()
            }
        }

    @Composable
    abstract fun FragmentView()
}