package com.nackun.meetchecker.data.util

import com.nackun.meetchecker.data.checker.CheckerDto
import com.nackun.meetchecker.data.db.CheckerEntity
import com.nackun.meetchecker.domain.model.Checker
import java.util.Date

fun Checker.toDto() = CheckerDto(
    date = dateString.toDate(),
    check = check
)

fun CheckerDto.toModel() = Checker(
    dateString = date.toDateString(),
    check = check
)

// ?: Date() 수정 필요
fun CheckerEntity.toDto() = CheckerDto(
    date = date ?: Date(),
    check = check
)

fun CheckerDto.toEntity() = CheckerEntity(
    id = null,
    dateString = date.toDateString(),
    date = date,
    check = check,
    lastModifiedDate = getNowString(),
    createdDate = getNowString()
)