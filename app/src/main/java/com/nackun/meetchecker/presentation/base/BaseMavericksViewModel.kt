package com.nackun.meetchecker.presentation.base

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel

open class BaseMavericksViewModel<S : MavericksState>(
    initialState: S
) : MavericksViewModel<S>(initialState)