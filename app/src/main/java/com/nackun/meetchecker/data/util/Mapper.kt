package com.nackun.meetchecker.data.util

import com.nackun.meetchecker.data.checker.CheckerDto
import com.nackun.meetchecker.data.db.CheckerEntity
import com.nackun.meetchecker.domain.model.Checker

fun Checker.toDto() = CheckerDto(
    dateString = dateString,
    check = check
)

fun CheckerDto.toModel() = Checker(
    dateString = dateString,
    check = check
)

fun CheckerEntity.toDto() = CheckerDto(
    dateString = dateString,
    check = check
)