package com.nackun.meetchecker.presentation.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun CalendarApp(localDate: LocalDate) {
    Column {
        Month(localDate.month)
        DaysOfWeek()
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