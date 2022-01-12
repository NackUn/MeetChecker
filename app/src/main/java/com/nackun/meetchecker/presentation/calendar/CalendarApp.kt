package com.nackun.meetchecker.presentation.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nackun.meetchecker.R
import com.nackun.meetchecker.presentation.ui.theme.MeetCheckerTheme
import java.time.LocalDate
import java.time.Month
import kotlin.math.ceil

@Preview(
    showBackground = true
)
@Composable
fun CalendarAppPreView() {
    MeetCheckerTheme {
        CalendarApp(localDate = LocalDate.now())
    }
}

@Composable
fun CalendarApp(localDate: LocalDate) {
    Column {
        Month(localDate.month)
        DaysOfWeek()
        Weeks(localDate.toMonthInfo())
    }
}

@Composable
fun Month(month: Month) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = month.value.toString(),
            fontSize = 52.sp,
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
        )
    }
}

@Composable
fun DaysOfWeekText(
    text: String,
    modifier: Modifier,
    color: Color = Color.Black
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 28.sp,
        color = color
    )
}

@Composable
fun DaysOfWeekTextSun(text: String, modifier: Modifier) {
    DaysOfWeekText(
        text = text,
        modifier = modifier,
        color = Color.Red
    )
}

@Composable
fun DaysOfWeekTextSat(text: String, modifier: Modifier) {
    DaysOfWeekText(
        text = text,
        modifier = modifier,
        color = Color.Blue
    )
}

@Composable
fun DaysOfWeek() {
    val daysOfWeek = stringArrayResource(R.array.days_of_week)

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(7) {
            when (it) {
                0 -> {
                    DaysOfWeekTextSun(
                        text = daysOfWeek[it],
                        modifier = Modifier.weight(1f)
                    )
                }
                6 -> {
                    DaysOfWeekTextSat(
                        text = daysOfWeek[it],
                        modifier = Modifier.weight(1f)
                    )
                }
                else -> {
                    DaysOfWeekText(
                        text = daysOfWeek[it],
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

fun getDayIndex(
    monthInfo: MonthInfo,
    week: Int,
    day: Int,
): Int? {
    val thisDay = (week * 7) + day
    val thisDayIndex = thisDay - monthInfo.firstEmptyDayCount

    return if (
        (thisDay < monthInfo.firstEmptyDayCount) ||
        (thisDayIndex >= monthInfo.lastDayOnMonth)
    ) {
        null
    } else {
        thisDayIndex
    }
}

@Composable
fun Weeks(monthInfo: MonthInfo) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        repeat(monthInfo.weeksCount) {
            Week(
                modifier = Modifier.weight(1f),
                monthInfo = monthInfo,
                week = it
            )
        }
    }
}

@Composable
fun Week(
    modifier: Modifier,
    monthInfo: MonthInfo,
    week: Int,
) {
    val days = stringArrayResource(R.array.days)
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        repeat(7) {
            Day(
                text = getDayIndex(
                    monthInfo = monthInfo,
                    week = week,
                    day = it
                )?.let { index ->
                    days[index]
                } ?: "",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun Day(
    text: String,
    modifier: Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append(text)
            }
        },
        fontSize = 30.sp,
        modifier = modifier
    )
}

private fun LocalDate.toMonthInfo() = MonthInfo(
    firstDayOfWeekOnMonth = this.withDayOfMonth(1).dayOfWeek.value,
    lastDayOnMonth = this.withDayOfMonth(this.lengthOfMonth()).dayOfMonth,
)

data class MonthInfo(
    // 해당 월의 첫 날의 요일의 숫자 값
    val firstDayOfWeekOnMonth: Int,
    // 해당 월의 마지막 날
    val lastDayOnMonth: Int,
) {
    // 첫 줄 빈 칸 채워야되는 수
    val firstEmptyDayCount: Int
        get() = firstDayOfWeekOnMonth % 7

    // 첫 줄에 채워진 날의 수
    private val firstDaysMinusCount: Int
        get() = firstDayOfWeekOnMonth - 7

    // 해당 월의 줄 수
    val weeksCount: Int
        get() = (ceil((lastDayOnMonth + firstDaysMinusCount) / 7.0) + 1).toInt()
}