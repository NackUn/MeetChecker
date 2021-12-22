package com.nackun.meetchecker.presentation.checker

import com.airbnb.mvrx.MavericksState

data class CheckerState(
    val today: String = "",
    val check: Boolean = false
) : MavericksState