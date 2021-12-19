package com.nackun.meetchecker.presentation.checker

import com.airbnb.mvrx.MavericksState
import com.nackun.meetchecker.presentation.util.toStringYMD
import java.util.Calendar

data class CheckerState(
    val check: Boolean = false,
    val now: String = Calendar.getInstance().time.toStringYMD()
) : MavericksState
