package com.nackun.meetchecker.presentation.checker

import com.airbnb.mvrx.MavericksState
import com.nackun.meetchecker.presentation.util.getNowString

data class CheckerState(
    val check: Boolean = false,
    val now: String = getNowString()
) : MavericksState