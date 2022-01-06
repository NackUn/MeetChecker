package com.nackun.meetchecker.presentation.checker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun CheckerApp(today: String, check: Boolean, checkClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopContent(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            today = today,
            check = check,
            checkClick = checkClick
        )

        /**
         * 향후 BottomContent 추가 예정
         * 식사, 카페 등 이벤트 아이콘을 둘 예정
         */

        /**
         * 향후 BottomContent 추가 예정
         * 식사, 카페 등 이벤트 아이콘을 둘 예정
         */
        /*
        BottomContent(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        )
         */
    }
}

@Composable
fun TopContent(modifier: Modifier, today: String, check: Boolean, checkClick: () -> Unit) {
    Column(
        modifier = modifier
    ) {
        TitleToday(today)
        CheckImage(check, checkClick)
    }
}

@Composable
fun TitleToday(today: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = today,
            fontSize = 50.sp
        )
    }
}

@Composable
fun CheckImage(check: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = check.toString(),
            fontSize = 50.sp,
            modifier = Modifier.clickable {
                onClick()
            }
        )
    }
}

@Composable
fun BottomContent(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
    }
}