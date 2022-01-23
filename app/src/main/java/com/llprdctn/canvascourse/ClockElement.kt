package com.llprdctn.canvascourse

import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset

import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import com.llprdctn.canvascourse.util.ClockStyle
import com.llprdctn.canvascourse.util.LineType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import java.util.*
import kotlin.coroutines.CoroutineContext
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

    var currentSecond by remember {
        mutableStateOf(second)
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

        //Draw IndicationLines
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

        //Draw Second
        val pointersEnd = Offset(
            x = center.x,
            y = center.y
        )


        val secondLineStart = Offset(
            x = style.secondPointerLength.toPx() * cos((currentSecond * 6 - 90) * (PI / 180f)).toFloat() + clockCenter.x,
            y = style.secondPointerLength.toPx() * sin((currentSecond * 6 - 90) * (PI / 180f)).toFloat() + clockCenter.y
        )

        drawLine(
            start = secondLineStart,
            end = pointersEnd,
            color = style.secondPointerColor,
            strokeWidth = 1.dp.toPx()
        )
    }
    LaunchedEffect(key1 = currentSecond ) {
        delay(1000L)
        if (currentSecond != 59) {
            currentSecond++
        } else {
            currentSecond = 0
        }
        Log.i("SECOND", currentSecond.toString())
    }

}


