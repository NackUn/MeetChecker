package com.nackun.meetchecker.presentation.model

import com.nackun.meetchecker.domain.model.Checker

fun Checker.toModel() = com.nackun.meetchecker.presentation.model.Checker(
    dateString = this.dateString,
    check = this.check
)