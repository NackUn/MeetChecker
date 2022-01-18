package com.nackun.meetchecker.presentation.checker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nackun.meetchecker.R
import com.nackun.meetchecker.presentation.ui.theme.MeetCheckerTheme

@Preview(
    showBackground = true
)
@Composable
fun CheckerAppPreview() {
    MeetCheckerTheme {
        CheckerApp(
            today = "2021-12-22",
            check = true,
            checkClick = {}
        )
    }
}

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
         * 향후 여러가지 Checker 타입 추가 예정
         * 가족, 친구, 연인 등 아이콘을 둘 예정
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
        CheckerImage(check, checkClick)
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
fun CheckerImage(check: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = if (check) {
                painterResource(R.drawable.checker_heart_on)
            } else {
                painterResource(R.drawable.checker_heart_off)
            },
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .size(160.dp)
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
