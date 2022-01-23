package com.llprdctn.canvascourse.util


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ClockStyle(
    val clockRadius: Dp = 150.dp,
    val fiveMinuteStrokeColor: Color = Color.Black,
    val normalMinuteStrokeColor: Color = Color.LightGray,
    val fiveMinuteStrokeLength: Dp = 25.dp,
    val normalMinuteStrokeLength: Dp = 15.dp,
    val minutePointerLength: Dp = 100.dp,
    val secondPointerLength: Dp = 110.dp,
    val secondPointerColor: Color = Color.Red,
    val hourPointerLength: Dp = 80.dp
)
