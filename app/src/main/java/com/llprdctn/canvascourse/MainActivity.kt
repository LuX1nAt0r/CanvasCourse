package com.llprdctn.canvascourse

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Animation()
        }
    }
}

@Preview
@Composable
fun Animation() {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {


        val deltaXAnim = rememberInfiniteTransition()
        val dx by deltaXAnim.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(500, easing = LinearEasing)
            )
        )

        val screenWidthPx = with(LocalDensity.current) {
            (LocalConfiguration.current.screenHeightDp * density)
        }
        val animTranslate by animateFloatAsState(
            targetValue = screenWidthPx,
            animationSpec = TweenSpec(10000, easing = LinearEasing)
        )

        val waveHeight by animateFloatAsState(
            targetValue = 0f,
            animationSpec = TweenSpec(10000, easing = LinearEasing)
        )

        val animColor by animateColorAsState(
            targetValue = if (init) green200 else red700,
            animationSpec = TweenSpec(if (init) 0 else timeSpec.toInt(), easing = LinearEasing)
        )

        val waveWidth = 200
        val originalY = 150f
        val path = Path()



        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                translate(top = animTranslate) {
                    drawPath(path = path, color = animColor)
                    path.reset()
                    val halfWaveWidth = waveWidth / 2
                    path.moveTo(-waveWidth + (waveWidth * dx), originalY.dp.toPx())

                    for (i in -waveWidth..(size.width.toInt() + waveWidth) step waveWidth) {
                        path.relativeQuadraticBezierTo(
                            halfWaveWidth.toFloat() / 2,
                            -waveHeight,
                            halfWaveWidth.toFloat(),
                            0f
                        )
                        path.relativeQuadraticBezierTo(
                            halfWaveWidth.toFloat() / 2,
                            waveHeight,
                            halfWaveWidth.toFloat(),
                            0f
                        )
                    }

                    path.lineTo(size.width, size.height)
                    path.lineTo(0f, size.height)
                    path.close()
                }
            }
        )

    }



}