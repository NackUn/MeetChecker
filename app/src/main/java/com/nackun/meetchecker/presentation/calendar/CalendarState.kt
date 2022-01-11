package com.nackun.meetchecker.presentation.calendar

import com.airbnb.mvrx.MavericksState
import com.nackun.meetchecker.presentation.model.Checker

data class CalendarState(
    val list : List<Checker> = listOf(),
) : MavericksState