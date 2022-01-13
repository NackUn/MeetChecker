package com.nackun.meetchecker.presentation.calendar

import com.airbnb.mvrx.MavericksState
import com.nackun.meetchecker.presentation.model.Checker
import com.nackun.meetchecker.presentation.util.getNowLocalDate
import java.time.LocalDate

data class CalendarState(
    val now: LocalDate = getNowLocalDate(),
    val checkers: List<Checker> = emptyList(),
) : MavericksState