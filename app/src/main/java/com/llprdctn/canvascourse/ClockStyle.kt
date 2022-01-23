package com.llprdctn.canvascourse


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ClockStyle(
    val clockRadius: Dp = 150.dp,
    val fiveMinuteStrokeColor: Color = Color.Black,
    val normalMinuteStrokeColor: Color = Color.LightGray,
    val fiveMinuteStrokeLength: Dp = 25.dp,
    val normalMinuteStrokeLength: Dp = 15.dp
)
