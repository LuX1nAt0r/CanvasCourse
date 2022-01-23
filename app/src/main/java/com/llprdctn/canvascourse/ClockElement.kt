package com.llprdctn.canvascourse

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset

import androidx.compose.ui.graphics.nativeCanvas

import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun Clock(
    modifier: Modifier = Modifier,
    second: Int,
    minute: Int,
    hour: Int,
    style: ClockStyle = ClockStyle()
) {

    val angle by remember {
        mutableStateOf(0f)
    }

    Canvas(
        modifier = modifier

    )  {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val clockCenter = Offset(
            canvasWidth / 2,
            canvasHeight / 2
        )
        val radius: Float = style.clockRadius.toPx()
        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                clockCenter.x,
                clockCenter.y,
                style.clockRadius.toPx(),
                Paint().apply {
                    color = Color.WHITE
                    setStyle(Paint.Style.FILL_AND_STROKE)
                    setShadowLayer(
                        100.dp.toPx(),
                        0f,
                        0f,
                        Color.argb(0.5f, 0f, 0f, 0f)
                    )
                }
            )
        }

        for (i in 0..59) {

            val angleInRad = (i * 6) * (PI / 180f).toFloat()

            val lineType = when {
                i % 5 == 0 -> LineType.FiveStep
                else -> LineType.Normal
            }

            val lineLength = when(lineType) {
                LineType.FiveStep -> style.fiveMinuteStrokeLength.toPx()
                LineType.Normal -> style.normalMinuteStrokeLength.toPx()
            }

            val lineColor = when(lineType) {
                LineType.FiveStep -> style.fiveMinuteStrokeColor
                LineType.Normal -> style.normalMinuteStrokeColor
            }

            val lineStart = Offset(
                x = (radius - lineLength) * cos(angleInRad) + clockCenter.x,
                y = (radius - lineLength) * sin(angleInRad) + clockCenter.y
            )
            val lineEnd = Offset(
                x = radius * cos(angleInRad) + clockCenter.x,
                y = radius * sin(angleInRad) + clockCenter.y
            )

            drawLine(
                color = lineColor,
                start = lineStart,
                end = lineEnd,
                strokeWidth = 1.dp.toPx()
            )

        }

    }

}


/*
@Preview
@Composable
fun DefaultPreview() {
    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        Clock(
            modifier = Modifier.align(Alignment.Center),
            second = 1,
            minute = 1,
            hour = 1,
            clockRadius = 100.dp
        )
    }
}*/
