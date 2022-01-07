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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nackun.meetchecker.R
import java.time.LocalDate
import java.time.Month
import kotlin.math.ceil

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
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (textSun, textMon, textTue, textWed, textThu, textFri, textSat) = createRefs()

        DaysOfWeekTextSun(
            text = stringResource(R.string.sun),
            modifier = Modifier.constrainAs(textSun) {
                start.linkTo(parent.start)
                end.linkTo(textMon.start)
            }
        )
        DaysOfWeekText(
            text = stringResource(R.string.mon),
            modifier = Modifier.constrainAs(textMon) {
                start.linkTo(textSun.end)
                end.linkTo(textTue.start)
            }
        )
        DaysOfWeekText(
            text = stringResource(R.string.tue),
            modifier = Modifier.constrainAs(textTue) {
                start.linkTo(textMon.end)
                end.linkTo(textWed.start)
            }
        )
        DaysOfWeekText(
            text = stringResource(R.string.wed),
            modifier = Modifier.constrainAs(textWed) {
                start.linkTo(textTue.end)
                end.linkTo(textThu.start)
            }
        )
        DaysOfWeekText(
            text = stringResource(R.string.thu),
            modifier = Modifier.constrainAs(textThu) {
                start.linkTo(textWed.end)
                end.linkTo(textFri.start)
            }
        )
        DaysOfWeekText(
            text = stringResource(R.string.fri),
            modifier = Modifier.constrainAs(textFri) {
                start.linkTo(textThu.end)
                end.linkTo(textSat.start)
            }
        )
        DaysOfWeekTextSat(
            text = stringResource(R.string.sat),
            modifier = Modifier.constrainAs(textSat) {
                start.linkTo(textFri.end)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
fun Weeks(monthInfo: MonthInfo) {
    val weeksCount = monthInfo.weeksCount
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (week1, week2, week3, week4, week5, week6) = createRefs()
        Week(
            modifier = Modifier.constrainAs(week1) {
                top.linkTo(parent.top)
                bottom.linkTo(week2.top)
            },
            monthInfo = monthInfo,
            thisWeek = 1,
        )
        Week(
            modifier = Modifier.constrainAs(week2) {
                top.linkTo(week1.bottom)
                bottom.linkTo(week3.top)
            },
            monthInfo = monthInfo,
            thisWeek = 2
        )
        Week(
            modifier = Modifier.constrainAs(week3) {
                top.linkTo(week2.bottom)
                bottom.linkTo(week4.top)
            },
            monthInfo = monthInfo,
            thisWeek = 3,
        )

        Week(
            modifier = Modifier.constrainAs(week4) {
                top.linkTo(week3.bottom)
                bottom.linkTo(
                    if (weeksCount == 4) {
                        parent.bottom
                    } else {
                        week5.top
                    }
                )
            },
            monthInfo = monthInfo,
            thisWeek = 4,
        )
        if (weeksCount >= 5) {
            Week(
                modifier = Modifier.constrainAs(week5) {
                    top.linkTo(week4.bottom)
                    bottom.linkTo(
                        if (weeksCount == 5) {
                            parent.bottom
                        } else {
                            week6.top
                        }
                    )
                },
                monthInfo = monthInfo,
                thisWeek = 5,
            )
        }
        if (weeksCount >= 6) {
            Week(
                modifier = Modifier.constrainAs(week6) {
                    top.linkTo(week5.bottom)
                    bottom.linkTo(parent.bottom)
                },
                monthInfo = monthInfo,
                thisWeek = 6,
            )
        }
    }
}

@Composable
fun Week(
    modifier: Modifier,
    monthInfo: MonthInfo,
    thisWeek: Int,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        when (thisWeek) {
            1 -> {
                // 첫 번째 줄을 표시
            }
            monthInfo.weeksCount -> {
                // 마지막 줄을 표시
            }
            else -> {
                // 나머지 줄을 표시
            }
        }
        // Preview 용
        Text(text = "aa")
    }
}

@Composable
fun Day() {
    // Preview 용
    Text(
        text = "a",
        modifier = Modifier.fillMaxSize()
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